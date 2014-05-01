package de.dbo.samples.elk.logstash;

import de.dbo.samples.elk.logstash.es.client.ESClient;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.joda.time.DateTime;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeFilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ESClientTest {
	   final static Logger logger = LoggerFactory.getLogger(ESClient.class);
	   
	    public static void main(String[] args) {
	          Client client = null;
	          
	          final Settings settings = ImmutableSettings.settingsBuilder()
	        	        .put("cluster.name", "elasticsearch-hombach")
	        	        .build();
	         logger.info("cluster.name = " + settings.get("cluster.name"));

	         logger.info("Initializing Search Server - Bootstrap ...");
	         client = new TransportClient(settings) .addTransportAddress(new
	        			InetSocketTransportAddress("127.0.0.1", 9300));
	         logger.info("Initializing Search Server - Bootstrap - DONE");
	         
//	         if (true) return;

//	        XContentBuilder indexSettings = null;

//	        FilterBuilder typeFilter = FilterBuilders.andFilter(
	 
//	        FilterBuilders.termsFilter("classification","schedule"),
//	                       FilterBuilders.termsFilter("type","outlookcalendar") ).filterName("classify");
	//

	       
	        DateTime dt1 =  DateTime.now().minus(1000*60*15);
	        DateTime dt2 =  DateTime.now(); 
	        RangeFilterBuilder rangeFilterBuilder = FilterBuilders.rangeFilter("@timestamp");
	        rangeFilterBuilder.from(dt1).to(dt2);
	//
//	        AndFilterBuilder queryFilters = FilterBuilders.andFilter();
//	                queryFilters.add(FilterBuilders.termFilter("classification","scehdule"));
//	        queryFilters.add(FilterBuilders.termFilter("type","excalendar"));
	//
//	        

//	        FilterBuilder aggFilter = FilterBuilders.andFilter(queryFilters,rangeFilterBuilder);
	        FilterBuilder aggFilter = FilterBuilders.andFilter(rangeFilterBuilder);

//	        QueryBuilder queryBuilder = QueryBuilders.filteredQuery(QueryBuilders.queryString("coffee"),
//	                                           aggFilter);
	        
	        QueryBuilder queryBuilder = QueryBuilders.filteredQuery(QueryBuilders.queryString("*"),
	                aggFilter);

	        logger.info("running ...");
	        SearchResponse response = 
	        		client.prepareSearch("logstash-2014.05.01")
	                .setSearchType(SearchType.QUERY_AND_FETCH)
	                .setQuery(queryBuilder)
	                                        
//	                                                   .setFilter(aggFilter)
//	                                                   .setFrom(0).setSize(50).setExplain(true)
//	                                                   .setScroll(new TimeValue(600000))
	                 .setExplain(false) /* no ranking */
	                 .execute()
	                 .actionGet();
	        client.close();

	//   SearchHits hits = response.getHits();
	//   for (SearchHit hit : hits.getHits() ) {
//		   Map<String, ?> map =  hit
//		   System.out.println(map);
	//   }
	      

	        System.out.println(response);
	    } 
}
