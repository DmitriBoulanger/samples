package de.dbo.samples.elk.client0;

import static de.dbo.samples.elk.client0.Time.HOUR;
import static de.dbo.samples.elk.client0.Time.MIN;
import static de.dbo.samples.elk.logstash.LogstashLog4jFields.NAME_FIELD;
import static de.dbo.samples.elk.logstash.LogstashLog4jFields.PRIORITY_FIELD;
import static de.dbo.samples.elk.logstash.LogstashLog4jFields.TIMESTAMP_FIELD;
import static de.dbo.samples.elk.logstash.LogstashLog4jFields.TYPE_FIELD;
import static org.elasticsearch.index.query.FilterBuilders.andFilter;
import static org.elasticsearch.index.query.FilterBuilders.rangeFilter;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.filteredQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.prefixQuery;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.RangeFilterBuilder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.elk.logstash.Logstash;

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

    private static final QueryBuilder NEGATIVE_QUERIES[] = new QueryBuilder[] {
                    prefixQuery(NAME_FIELD, "org")
                  , prefixQuery(NAME_FIELD, "com") };


    public static final QueryBuilder messages(final String logger
    		, final String priority
            , final long fromMilliseconds, final long toMilliseconds
            , final Logstash logstash) {
        return messages(logger, priority
        		, timeRange(fromMilliseconds, toMilliseconds), logstash);
    }

    public static final QueryBuilder messages(final String logger
    		, final String priority , final FilterBuilder filter
            , final Logstash logstash) {
    	BoolQueryBuilder boolQuery = boolQuery();
    	 for (QueryBuilder negative : NEGATIVE_QUERIES) {
             boolQuery = boolQuery.mustNot(negative);
         }
        final QueryBuilder query = boolQuery
                .must(matchQuery(NAME_FIELD, logger))
                .must(matchQuery(TYPE_FIELD, logstash.getLogType()))
                .must(matchQuery(PRIORITY_FIELD, priority));
        return filteredQuery(query, filter);
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
        log.trace("time-range: " + from + "<==>" + to);
        return rangeFilter;
    }
}
