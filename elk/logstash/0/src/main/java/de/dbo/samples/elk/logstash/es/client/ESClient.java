package de.dbo.samples.elk.logstash.es.client;
 

import java.util.Date;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.joda.time.DateTime;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeFilterBuilder;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple client for ElasticSearch Server.
 * This client uses indices created from Logstash
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class ESClient {
	private final static Logger log = LoggerFactory.getLogger(ESClient.class);
	
	private final Settings settings;
	private final InetSocketTransportAddress inetSocketTransportAddress;
	
	private TransportClient client = null;
	 
	public ESClient() {
		settings = ImmutableSettings.settingsBuilder()
		        .put("cluster.name", "elasticsearch-hombach")
		        .build();
		inetSocketTransportAddress = 
				new InetSocketTransportAddress("127.0.0.1", 9300);
		log.info("settings and server address set");
	}
	
	/**
	 * open this client.
	 * To run a query, client should be already opened
	 */
	public final void open() {
		if (null==client) {
			client = new TransportClient(settings); 
			client.addTransportAddress(inetSocketTransportAddress);
			log.info("opened");
		} 
	}
	
	/**
	 * closes this client.
	 * When query is finished, this client should be closed
	 */
	public void close() {
		if (null!=client) {
			client.close(); 
			client = null;
			log.info("closed");
		} 
	}
	
	public QueryBuilder query() {
	     return QueryBuilders.matchAllQuery();
	}
	
	
	public RangeFilterBuilder range(final int minFromNow) {
		 final DateTime dt1 =  DateTime.now().minus(1000*60*minFromNow);
	     final DateTime dt2 =  DateTime.now(); 
	     log.info("range from=" + new Date(dt1.getMillis())  + " to=" + new Date(dt2.getMillis()) );
	     return FilterBuilders.rangeFilter("@timestamp").from(dt1).to(dt2);
	}
	
	public void run(final QueryBuilder queryBuilder) {
		if (null==client) {
			log.warn("can't run query: no client opend");
			return;
		}
		log.info("query ... ");
		final SearchResponse response = client
				.prepareSearch("logstash-2014.03.23")
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .setQuery(queryBuilder)
                .setExplain(false)
                .execute()
                .actionGet();
		final SearchHit[] searchHits = response.getHits().getHits();
		log.info("query hits: " + searchHits.length);
		for (final SearchHit searchHit :searchHits) {
			log.info(searchHit.getId());
		}
	}
    
}