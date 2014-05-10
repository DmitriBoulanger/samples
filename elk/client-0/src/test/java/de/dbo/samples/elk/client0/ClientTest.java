package de.dbo.samples.elk.client0;

import static de.dbo.samples.elk.client0.Query.messages;
import static de.dbo.samples.elk.client0.Query.timeRangeBeforeMinutes;
import static de.dbo.samples.elk.client0.Tool.nn;
import static de.dbo.samples.elk.logstash.Logstash.MESSAGE_FIELD;
import static de.dbo.samples.elk.logstash.Logstash.STACK_TRACE_FIELD;
import static de.dbo.samples.elk.logstash.Logstash.TIMESTAMP_FIELD;

import static org.junit.Assert.assertTrue;

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

		@Before
		public void init() {
			
			logstash = new LogstashDefault();
			logstash.setIndexNameExrension("log4j");
			
			es = new ElasticSearchDefault();
			es.setCluster("elasticsearch-hombach");
	    	
	    	filter = timeRangeBeforeMinutes(3);
		}

		/**
		 * messages from LOGGER1
		 * @see Messages#LOGGER1
		 */
		@Test
		public void pickupMessages1() {
			final Client0 esClient = new Client0(logstash,es);
	    	final QueryBuilder query = messages("AnotherLogger", "WARN", filter, logstash);
	    	esClient.open();
	    	final SearchHit[] searchHits = esClient.run(query);
	    	esClient.close();
	    	final int messageCnt = searchHits.length;

	    	log.info("query hits: " + messageCnt);
			for (final SearchHit searchHit : searchHits) {
				final Map<String,Object> fieldValues = searchHit.getSource();
				log.info("search hit:" + print(fieldValues));
			}
			assertTrue("Picked-up too few messages. Expected at least 3 messages"
	    			,messageCnt>2);
		}

		/**
		 * messages from LOGGER2
		 * @see Messages#LOGGER2
		 */
		@Test
		public void pickupMessages2() {
			final Client0 esClient =  new Client0(logstash,es);
	    	final QueryBuilder query = messages("AnotherLoggerX","ERROR",filter,logstash);
	    	esClient.open();
	    	final SearchHit[] searchHits = esClient.run(query);
	    	esClient.close();
	    	final int messageCnt = searchHits.length;

	    	log.info("query hits: " + messageCnt);
			for (final SearchHit searchHit : searchHits) {
				final Map<String,Object> fieldValues = searchHit.getSource();
				log.info("search hit:" + print(fieldValues));
			}
			assertTrue("Picked-up too few messages. Expected at least 3 messages"
	    			,messageCnt>2);
		}

		/*
		 * prints values in standard Logstash-fields
		 */
		private static final StringBuilder print(final Map<String,Object> fieldValues) {
			final StringBuilder sb = new StringBuilder();
			final String timestamp = (String) fieldValues.get(TIMESTAMP_FIELD);
			final String message = (String) fieldValues.get(MESSAGE_FIELD);
			final String exception = (String) fieldValues.get(STACK_TRACE_FIELD);
			sb.append("\n\t - timestamp: " + timestamp);
			sb.append("\n\t - message: " + message);
			if (nn(exception)) {
				sb.append( "\n\t - exception: " +  exception);
			};
			return sb;
		}

	    public static void main(String[] args) {
	    	final ClientTest test = new ClientTest();
	    	test.init();
	    	try {
				test.pickupMessages1();
			} catch (AssertionError e) {
				log.error("test1 failure: ",e);
			}
	    	try {
				test.pickupMessages2();
			} catch (AssertionError e) {
				log.error("test2 failure: ",e);
			}

	    }
}
