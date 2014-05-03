package de.dbo.samples.elk.logstash.es.client;
 
import static de.dbo.samples.elk.logstash.es.client.Tool.formatMs;
import static de.dbo.samples.elk.logstash.es.client.Tool.todayLogstashIndex;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.NoNodeAvailableException;
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
	
    /**
     * creates transport client for remote ES-server
     *
     * @param esServer parameters of the remote ElasticSearch server
     * @return new transport client
     */
    public static final TransportClient elasticsearchClient(final ElasticSearch esServer) {
        final Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", esServer.getCluster())
                .put("network.host", esServer.getHost())
                .build();
        
        final TransportClient ret = new TransportClient(settings);
        ret.addTransportAddress(new InetSocketTransportAddress(esServer.getHost(), esServer.getPort()));
        return ret;
    }
	
	 
	private TransportClient client = null;
	 
	public ESClient() {
		client = elasticsearchClient(es);
		log.trace("settings and server address set. " + es.print());
	}
	
	/**
	 * open this client.
	 * To run a query, client should be already opened
	 */
	public final void open() {
		if (null==client) {
			client =  elasticsearchClient(es);
			log.trace("opened");
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
			log.trace("closed");
		} 
	}
	
	public SearchHit[] run(final QueryBuilder query) {
		final long start = System.currentTimeMillis();
		final String queryInfo = "query "+ todayLogstashIndex(logstash);
		try {
			log.trace(queryInfo + " ... ");
			final SearchHit[] ret;
			if (null==client) {
				ret = new SearchHit[]{};
				log.warn(queryInfo + "rejected: no client opend");
			} else {
				final SearchResponse response = client
					.prepareSearch(todayLogstashIndex(logstash))
			        .setSearchType(SearchType.QUERY_AND_FETCH)
			        .setQuery(query)
			        .setExplain(false)
			        .execute()
			        .actionGet();
			   ret = response.getHits().getHits();
			   log.trace(queryInfo + " done. Elapsed " + formatMs(System.currentTimeMillis()-start));
			}
			return ret;
		} catch (NoNodeAvailableException e) {
			throw new ESClientException(
					"Can't run query for " + es.print() + "\nThe ES-server is down?"
					,e );
		}
	 }
}