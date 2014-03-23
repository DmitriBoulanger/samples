package de.dbo.samples.elasticsearch.logging.client;
 

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

public class AndOrFiltering {
    final static Logger logger = LoggerFactory.getLogger(AndOrFiltering.class);
    public static void main(String[] args) {
//          Node node = null;
          Client client = null;
          
         logger.info("Initializing Search Server - Bootstrap ...");
//         node = nodeBuilder().node();
          
         client = new TransportClient() .addTransportAddress(new
        			InetSocketTransportAddress("127.0.0.1", 9300));
         logger.info("Initializing Search Server - Bootstrap - DONE");
         
//         if (true) return;

//        XContentBuilder indexSettings = null;

//        FilterBuilder typeFilter = FilterBuilders.andFilter(
 
//        FilterBuilders.termsFilter("classification","schedule"),
//                       FilterBuilders.termsFilter("type","outlookcalendar") ).filterName("classify");
//

       
        DateTime dt1 =  DateTime.now().minus(1000*60*15);
        DateTime dt2 =  DateTime.now(); 
        RangeFilterBuilder rangeFilterBuilder = FilterBuilders.rangeFilter("@timestamp");
        rangeFilterBuilder.from(dt1).to(dt2);
//
//        AndFilterBuilder queryFilters = FilterBuilders.andFilter();
//                queryFilters.add(FilterBuilders.termFilter("classification","scehdule"));
//        queryFilters.add(FilterBuilders.termFilter("type","excalendar"));
//
//        

//        FilterBuilder aggFilter = FilterBuilders.andFilter(queryFilters,rangeFilterBuilder);
        FilterBuilder aggFilter = FilterBuilders.andFilter(rangeFilterBuilder);

//        QueryBuilder queryBuilder = QueryBuilders.filteredQuery(QueryBuilders.queryString("coffee"),
//                                           aggFilter);
        
        QueryBuilder queryBuilder = QueryBuilders.filteredQuery(QueryBuilders.queryString("*"),
                aggFilter);

        logger.info("running ...");
        SearchResponse response = client.prepareSearch("logstash-2014.03.23")
                                                   .setSearchType(SearchType.QUERY_AND_FETCH)
                                                   .setQuery(queryBuilder)
                                        
//                                                   .setFilter(aggFilter)
//                                                   .setFrom(0).setSize(50).setExplain(true)
//                                                   .setScroll(new TimeValue(600000))
                                                   .setExplain(false)
                                                   .execute()
                                                   .actionGet();

//   SearchHits hits = response.getHits();
//   for (SearchHit hit : hits.getHits() ) {
//	   Map<String, ?> map =  hit
//	   System.out.println(map);
//   }
      

        System.out.println(response);
    } 
    
}