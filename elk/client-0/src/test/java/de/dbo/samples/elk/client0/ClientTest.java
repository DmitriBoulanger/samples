package de.dbo.samples.elk.client0;

import static de.dbo.samples.elk.client0.Query.messages;
import static de.dbo.samples.elk.client0.Query.timeRangeBeforeMinutes;
import static de.dbo.samples.elk.client0.Time.MIN;
import static de.dbo.samples.elk.client0.Time.SEC;
import static de.dbo.samples.elk.client0.Time.formatMs;

import static de.dbo.samples.elk.logstash.LogstashLog4jFields.EXCEPTION_FIELD;
import static de.dbo.samples.elk.logstash.LogstashLog4jFields.MESSAGE_FIELD;
import static de.dbo.samples.elk.logstash.LogstashLog4jFields.TIMESTAMP_FIELD;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.elk.es.ElasticSearch;
import de.dbo.samples.elk.es.ElasticSearchDefault;
import de.dbo.samples.elk.logstash.Logstash;
import de.dbo.samples.elk.logstash.LogstashDefault;

public class ClientTest {
	final static Logger log = LoggerFactory.getLogger(ClientTest.class);

	private final ElasticSearch es;
	private final Logstash logstash;
	private FilterBuilder filter;
	private final List<String> expectedFieldNames = new ArrayList<String>();
	private final Client0 esClient;

    private final long          beforeMin;

    private static final long   PAUSE_AFTER_INDEX_DELETE = 7 * SEC;

	/**
	 * Test for default client
	 */
	public ClientTest() {
		logstash = new LogstashDefault();
		es = new ElasticSearchDefault();
		esClient = new Client0(logstash, es);

		// standard log4j-fields
		expectedFieldNames.add(TIMESTAMP_FIELD);
		expectedFieldNames.add(MESSAGE_FIELD);
		expectedFieldNames.add(EXCEPTION_FIELD);

        beforeMin = 3;
	}

    /**
     * installs important test-parameters.
     * The values should be customized
     */
	@Before
	public void init() {
        es.setCluster("elasticsearch-hombach");
        logstash.setIndexSufffix("YYYY.MM.dd.HH");
        filter = timeRangeBeforeMinutes(3 /* how many minutes ago should be taken into account*/);
	}

	/**
	 * messages from LOGGER1
	 *
	 * @see Messages#LOGGER1
	 */
	@Test
    public void pickupMessages1() throws Exception {
		final QueryBuilder query = messages("AnotherLogger", "WARN", filter, logstash);
		final int messageCnt = pickupMessages(query);
		assertTrue("Picked-up too few messages. Expected at least 3 messages",
				messageCnt > 2);
	}

	/**
	 * messages from LOGGER2
	 *
	 * @see Messages#LOGGER2
	 */
	@Test
    public void pickupMessages2() throws Exception {
		final QueryBuilder query = messages("AnotherLoggerX", "ERROR", filter, logstash);
		final int messageCnt = pickupMessages(query);
		assertTrue("Picked-up too few messages. Expected at least 3 messages",
				messageCnt > 2);
	}

	@Test
	public void deleteIndex() {
		esClient.open();
		final String index = logstash.beforeThisHourIndex(0).toString();
		final boolean done = esClient.deleteIndex(index);
		log.info("indices: " + esClient.printIndices().toString());
		assertTrue("Index " + index + " was not deleted", done);
		esClient.close();
		try {
            log.info("pause " + formatMs(PAUSE_AFTER_INDEX_DELETE) + " ...");
            Thread.sleep(PAUSE_AFTER_INDEX_DELETE);
		} catch (InterruptedException e) {
			log.error("Pause interrupred", e);
		}
	}

    private int pickupMessages(final QueryBuilder query) throws Exception {
		esClient.open();
		final SearchHit[] hits = esClient.run(query,logstash.beforeThisHourIndex(0).toString());
		esClient.close();

		final int hitsCnt = hits.length;
		log.info("query hits: " + hitsCnt);
		for (final SearchHit hit : hits) {
            final Long timestamp = es.timestamp((String) hit.getSource().get(TIMESTAMP_FIELD));
            final long delay = System.currentTimeMillis() - timestamp;
            log.info("search hit delay " + formatMs(delay));
            assertTrue("Hit-delay is too long " + delay + ". It has to be less than " + beforeMin + " min."
                    , delay < beforeMin * MIN);
			final Map<String, Object> hitValues = hit.getSource();
            log.info("search hit:" + logstash.print(hitValues, expectedFieldNames));
		}
		return hitsCnt;
	}

    public static void main(String[] args) throws Exception {
		final ClientTest test = new ClientTest();
		test.init();
		try {
			test.pickupMessages1();
		} catch (AssertionError e) {
			log.error("test1 failure: ", e);
		}
		try {
			test.pickupMessages2();
		} catch (AssertionError e) {
			log.error("test2 failure: ", e);
		}
		test.deleteIndex();
	}
}
