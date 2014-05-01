package de.dbo.samples.elk.logstash;

import de.dbo.samples.elk.logstash.es.client.ESClient;

import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ESClientTest {
	   final static Logger logger = LoggerFactory.getLogger(ESClientTest.class);
	   
	    public static void main(String[] args) {
	    	final ESClient esClient = new ESClient();
	    	final QueryBuilder query = esClient.query();
	    	esClient.open();
	    	esClient.run(query);
	    	esClient.close();
	    }
	          
}
