package de.dbo.samples.elk.logstash.client0;

import static de.dbo.samples.elk.logstash.Logstash.MESSAGE_FIELD;
import static de.dbo.samples.elk.logstash.Logstash.STACK_TRACE_FIELD;
import static de.dbo.samples.elk.logstash.Logstash.TIMESTAMP_FIELD;
import static de.dbo.samples.elk.logstash.client0.Query.messages;
import static de.dbo.samples.elk.logstash.client0.Query.timeRangeBeforeMinutes;
import static de.dbo.samples.elk.logstash.client0.Tool.nn;

import de.dbo.samples.elk.es.ElasticSearch;
import de.dbo.samples.elk.logstash.Logstash;
import de.dbo.samples.elk.logstash.client0.ElasticSearchClient;
import de.dbo.samples.elk.logstash.client0.ElasticSearchImpl;
import de.dbo.samples.elk.logstash.client0.LogstashImpl;

import java.util.Map;

import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ElasticSearchClientTest {
	   final static Logger log = LoggerFactory.getLogger(ElasticSearchClientTest.class);
	   
	   private ElasticSearch es;
	   private Logstash logstash;
	   private FilterBuilder filter;
		
		@Before
		public void init() {
			logstash = new LogstashImpl();
		    es = new ElasticSearchImpl("elasticsearch-hombach", "localhost", 9300);
	    	logstash.setIndexNameExrension("test");
	    	filter = timeRangeBeforeMinutes(3);
		}
		
		/**
		 * messages from LOGGER1
		 * @see ApplicationMessages#LOGGER1
		 */
		@Test
		public void pickupMessages1() {
			final ElasticSearchClient esClient = new ElasticSearchClient(logstash,es);
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
		 * @see ApplicationMessages#LOGGER2
		 */
		@Test
		public void pickupMessages2() {
			final ElasticSearchClient esClient =  new ElasticSearchClient(logstash,es);
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
	    	final ElasticSearchClientTest test = new ElasticSearchClientTest();
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
