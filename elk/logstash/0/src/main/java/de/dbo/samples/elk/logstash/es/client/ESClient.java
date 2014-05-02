package de.dbo.samples.elk.logstash.es.client;
 
import static de.dbo.samples.elk.logstash.es.client.Tool.todayLogstashIndex;
import static de.dbo.samples.elk.logstash.es.client.Query.*;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
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
	
	private static final ElasticSearch es = new ElasticSearch("elasticsearch-hombach", "localhost", 9300);
	private static final Logstash logstash = new Logstash();
	
	private final Settings settings;
	private final InetSocketTransportAddress inetSocketTransportAddress;
	
	private TransportClient client = null;
	 
	public ESClient() {
		settings = ImmutableSettings.settingsBuilder()
		        .put("cluster.name", es.getCluster())
		        .build();
		inetSocketTransportAddress = 
				new InetSocketTransportAddress(es.getHost(), es.getPort());
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
	     return messages("AnotherLogger", "WARN"
	    		 , timeRange(1)
	    		 , logstash);
	}
	
	public void run(final QueryBuilder query) {
		if (null==client) {
			log.warn("can't run query: no client opend");
			return;
		}
		log.info("query ... ");
		final SearchResponse response = client
				.prepareSearch(todayLogstashIndex(logstash))
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .setQuery(query)
                .setExplain(false)
                .execute()
                .actionGet();
		final SearchHit[] searchHits = response.getHits().getHits();
		log.info("query hits: " + searchHits.length);
		for (final SearchHit searchHit : searchHits) {
			log.info(searchHit.getId() +":" +searchHit.getSourceAsString());
		}
	}
    
}