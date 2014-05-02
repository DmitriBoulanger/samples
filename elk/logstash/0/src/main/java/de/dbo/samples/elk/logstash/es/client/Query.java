package de.dbo.samples.elk.logstash.es.client;

import static dbo.samples.elk.logstash.config.ConfigurationFactory.logstash;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.prefixQuery;
import static org.elasticsearch.index.query.QueryBuilders.wildcardQuery;
import static org.elasticsearch.search.sort.SortBuilders.fieldSort;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeFilterBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Package-private utilities used in the client-implementation
 *
 * @author Dmitri Boulanger
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */
final class Query {
    private static final Logger log = LoggerFactory.getLogger(Query.class);

    public static final String                   LOGGER_NAME             = "logger_name";
    public static final String                   JSON_EXECUTION_ID       = "json.executionId";


    private static final QueryStringQueryBuilder JSON_EXECUTION_ID_QUERY(final String id) {
        return new QueryStringQueryBuilder(id).field(JSON_EXECUTION_ID);
    }

    private static final QueryBuilder NO_QUERIES[] = new QueryBuilder[]{
                                                   prefixQuery(LOGGER_NAME, "org")
                                                   , prefixQuery(LOGGER_NAME, "com")
                                                                             //                                                                     , new QueryStringQueryBuilder(q2("cartezianproduct.Solver")).field(LOGGER_NAME)
                                                                             //                                                                     , MANAGER_QUERY
                                                                             //                                                                     , SERVER_QUERY
                                                                     };



    /**
     * Query that checks expected logger-name
     */
    static final QueryBuilder loggerNameQuery(final String namePrefix) {
        return prefixQuery(LOGGER_NAME, namePrefix);
    }

    /**
     * Query that checks expected logger-name
     */
    static final QueryBuilder loggerNameQuery2(final String namePrefix) {
        return wildcardQuery(LOGGER_NAME, q2(namePrefix) + "*");
    }

    static final QueryBuilder loggerNameQuery3(final String namePrefix) {
        return prefixQuery(LOGGER_NAME, q2(namePrefix) + "*");
    }

    static final QueryBuilder loggerNameQuery4(final String namePrefix) {
        return new QueryStringQueryBuilder(q2(namePrefix)).field(LOGGER_NAME);
    }

    /**
     * Query that checks expected logger-type
     */
    static final MatchQueryBuilder       LOGGER_TYPE_QUERY = matchQuery("type", logstash().getLogType());

    /**
     * query to finds all events with the specified UUID that occur in the last milliseconds
     * @param uuid UUID of the debugging thread
     * @param lastMilliseconds
     * @return ES-query
     *
     * @see LoggerThread#renameThreadAsDebuggerThread()
     */
    static final QueryBuilder queryUsingThreadUuid(final String logger, final String uuid
            , final long fromMilliseconds, final long toMilliseconds) {
        final QueryBuilder query = negative()
                .must(matchQuery("logger_name", "INSIGHT"))
                .must(JSON_EXECUTION_ID_QUERY(uuid))
                .must(LOGGER_TYPE_QUERY)
                .must(matchQuery("priority", "INFO"))
        ;
        return QueryBuilders.filteredQuery(query, millisecondsFilter(fromMilliseconds, toMilliseconds));
    }

    private static final BoolQueryBuilder negative() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (QueryBuilder negative : NO_QUERIES) {
            boolQueryBuilder = boolQueryBuilder.mustNot(negative);
        }
        return boolQueryBuilder;
    }


    /**
     * query-filter to pick-up events occurred in the last milliseconds
     *
     * @param milliseconds
     * @return filer to be used in ES-queries
     */
    @SuppressWarnings("unused")
    private static final FilterBuilder millisecondsFilter(final long milliseconds) {
        final DateTime lastMillisecondsFrom = DateTime.now().minus(milliseconds);
        final RangeFilterBuilder rangeFilterBuilder = FilterBuilders.rangeFilter("@timestamp");
        rangeFilterBuilder.from(lastMillisecondsFrom);
        final FilterBuilder rangeFilter = FilterBuilders.andFilter(rangeFilterBuilder);
        return rangeFilter;
    }

    private static final FilterBuilder millisecondsFilter(final long fromMilliseconds, final long toMilliseconds) {
        final DateTime from = new DateTime(fromMilliseconds);
        final DateTime to = new DateTime(toMilliseconds);
        final RangeFilterBuilder rangeFilterBuilder = FilterBuilders.rangeFilter("@timestamp");
        rangeFilterBuilder.from(from);
        rangeFilterBuilder.to(to);
        final FilterBuilder rangeFilter = FilterBuilders.andFilter(rangeFilterBuilder);
        return rangeFilter;
    }

    @SuppressWarnings("unused")
    private static final FieldSortBuilder timesort() {
        final FieldSortBuilder fieldSortBuilder = fieldSort("@timestamp");
        fieldSortBuilder.order(SortOrder.ASC);
        return fieldSortBuilder;
    }


}
