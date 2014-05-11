package de.dbo.samples.elk.client0;

import static de.dbo.samples.elk.client0.Query.messages;
import static de.dbo.samples.elk.client0.Query.timeRangeBeforeMinutes;

import static de.dbo.samples.elk.logstash.LogstashLog4jFields.MESSAGE_FIELD;
import static de.dbo.samples.elk.logstash.LogstashLog4jFields.EXCEPTION_FIELD;
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

import de.dbo.samples.elk.client0.Client0;
import de.dbo.samples.elk.es.ElasticSearch;
import de.dbo.samples.elk.es.ElasticSearchDefault;
import de.dbo.samples.elk.logstash.Logstash;
import de.dbo.samples.elk.logstash.LogstashDefault;

public class ClientTest {
	final static Logger log = LoggerFactory.getLogger(ClientTest.class);

	private ElasticSearch es;
	private Logstash logstash;
	private FilterBuilder filter;
	private final List<String> expectedFieldNames = new ArrayList<String>();
	private Client0 esClient;

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
	}

	@Before
	public void init() {
		es.setCluster("elasticsearch-hombach");
		filter = timeRangeBeforeMinutes(3);
	}

	/**
	 * messages from LOGGER1
	 * 
	 * @see Messages#LOGGER1
	 */
	@Test
	public void pickupMessages1() {
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
	public void pickupMessages2() {
		final QueryBuilder query = messages("AnotherLoggerX", "ERROR", filter, logstash);
		final int messageCnt = pickupMessages(query);
		assertTrue("Picked-up too few messages. Expected at least 3 messages",
				messageCnt > 2);
	}

	private int pickupMessages(final QueryBuilder query) {
		esClient.open();
		final SearchHit[] hits = esClient.run(query);
		esClient.close();
		final int hitsCnt = hits.length;
		log.info("query hits: " + hitsCnt);
		for (final SearchHit hit : hits) {
			final Map<String, Object> hitValues = hit.getSource();
			log.info("search hit:" + logstash.print(hitValues, expectedFieldNames));
		}
		return hitsCnt;
	}

	public static void main(String[] args) {
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
	}
}
