package de.dbo.samples.elk.logstash.es.client;

import static de.dbo.samples.elk.logstash.es.client.Logstash.LOGGER_NAME_FIELD;
import static de.dbo.samples.elk.logstash.es.client.Logstash.LOGGER_TYPE_FIELD;
import static de.dbo.samples.elk.logstash.es.client.Logstash.PRIORITY_FIELD;
import static de.dbo.samples.elk.logstash.es.client.Logstash.TIMESTAMP_FIELD;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.RangeFilterBuilder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.elasticsearch.index.query.FilterBuilders.andFilter;
import static org.elasticsearch.index.query.FilterBuilders.rangeFilter;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.filteredQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.prefixQuery;

/**
 * Package-private utilities used in the client-implementation
 *
 * @author Dmitri Boulanger
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */
public final class Query {
	private final static Logger log = LoggerFactory.getLogger(Query.class);
	
	private static final long SEC = 1000;
	private static final long MIN = 60*SEC;
	private static final long HOUR = 60*MIN;
   
    private static final QueryBuilder NEGATIVE_QUERIES[] = new QueryBuilder[] {
                    prefixQuery(LOGGER_NAME_FIELD, "org")
                  , prefixQuery(LOGGER_NAME_FIELD, "com") };

   
    public static final QueryBuilder messages(final String logger
    		, final String priority
            , final long fromMilliseconds, final long toMilliseconds
            , final Logstash logstash) {
        return messages(logger, priority
        		, timeRange(fromMilliseconds, toMilliseconds), logstash);
    }
    
    public static final QueryBuilder messages(final String logger
    		, final String priority
            , final FilterBuilder filterBuilder
            , final Logstash logstash) {
        final QueryBuilder query = negative()
                .must(matchQuery(LOGGER_NAME_FIELD, logger))
                .must(matchQuery(LOGGER_TYPE_FIELD, logstash.getLogType()))
                .must(matchQuery(PRIORITY_FIELD, priority));
        return filteredQuery(query, filterBuilder);
    }
    
    private static final BoolQueryBuilder negative() {
        BoolQueryBuilder boolQuery = boolQuery();
        for (QueryBuilder negative : NEGATIVE_QUERIES) {
            boolQuery = boolQuery.mustNot(negative);
        }
        return boolQuery;
    }
    
    public static FilterBuilder timeRangeBeoreHours(final int hours) {
		 final DateTime from =  DateTime.now().minus(hours*HOUR);
	     final DateTime to =  DateTime.now(); 
	     return timeRange(from, to);
	}
    
    public static FilterBuilder timeRangeBeforeMinutes(final int minFromNow) {
		 final DateTime from =  DateTime.now().minus(MIN*minFromNow);
	     final DateTime to =  DateTime.now(); 
	     return timeRange(from, to);
	}

    public static final FilterBuilder timeRange(final long fromMillis, final long toMillis) {
        final DateTime from = new DateTime(fromMillis);
        final DateTime to = new DateTime(toMillis);
        return timeRange(from, to);
    }
    
    private static final FilterBuilder timeRange(final DateTime from, final DateTime to) {
        final RangeFilterBuilder rangeFilterBuilder = rangeFilter(TIMESTAMP_FIELD);
        rangeFilterBuilder.from(from);
        rangeFilterBuilder.to(to);
        final FilterBuilder rangeFilter = andFilter(rangeFilterBuilder);
        log.trace("time-range: " + from + "  --  " + to);
        return rangeFilter;
    }
}
