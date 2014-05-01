package de.dbo.samples.elk.logstash.es.client;
 

import java.util.Date;
import java.util.Map;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
//import org.elasticsearch.action.admin.indices.settings.UpdateSettingsRequestBuilder;
//import org.elasticsearch.action.admin.indices.settings.UpdateSettingsResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.joda.time.DateTime;
import org.elasticsearch.common.joda.time.format.DateTimeFormat;
import org.elasticsearch.common.joda.time.format.DateTimeFormatter;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.*;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.FilterBuilders.andFilter;
import static org.elasticsearch.index.query.FilterBuilders.orFilter;
import static org.elasticsearch.index.query.FilterBuilders.rangeFilter;
import static org.elasticsearch.index.query.QueryBuilders.filteredQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

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
	
	public final void open() {
		if (null==client) {
			client = new TransportClient(settings); 
			client.addTransportAddress(inetSocketTransportAddress);
			log.info("opened");
		} 
	}
	
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
		final SearchResponse response = client.prepareSearch("logstash-2014.03.23")
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