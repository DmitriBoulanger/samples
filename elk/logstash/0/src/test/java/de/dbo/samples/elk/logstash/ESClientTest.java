package de.dbo.samples.elk.logstash;

import de.dbo.samples.elk.logstash.es.client.ESClient;
import de.dbo.samples.elk.logstash.es.client.Logstash;

import java.util.Map;

import static de.dbo.samples.elk.logstash.es.client.Query.*;
import static de.dbo.samples.elk.logstash.es.client.Tool.*;
import static de.dbo.samples.elk.logstash.es.client.Logstash.*;

import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ESClientTest {
	   final static Logger log = LoggerFactory.getLogger(ESClientTest.class);
	   
	    public static void main(String[] args) {
	    	final ESClient esClient = new ESClient();
	    	final Logstash logstash = new Logstash();
	    	final FilterBuilder filter = timeRangeBeforeMinutes(300);
	    	final QueryBuilder query = messages("AnotherLogger","WARN",filter,logstash);
	    	esClient.open();
	    	final SearchHit[] searchHits = esClient.run(query);
	    	esClient.close();
	    	
	    	log.info("query hits: " + searchHits.length);
	    	
	    	
			for (final SearchHit searchHit : searchHits) {
				final Map<String,Object> fieldValues = searchHit.getSource();
				final String timestamp = (String) fieldValues.get(TIMESTAMP_FIELD);
				final String message = (String) fieldValues.get(MESSAGE_FIELD);
				final String exception = (String) fieldValues.get(STACK_TRACE_FIELD);
				log.info(timestamp
				 + "\n\t - message: " + message
				 + (nn(exception)? "\n\t - exception: " +  exception: "" )
				 );
			}
	    	
	    }
	          
}
