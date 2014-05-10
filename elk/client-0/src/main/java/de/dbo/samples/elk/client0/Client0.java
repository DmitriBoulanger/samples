package de.dbo.samples.elk.client0;
 
import static de.dbo.samples.elk.client0.Tool.formatMs;
import static de.dbo.samples.elk.client0.Tool.todayLogstashIndex;

import de.dbo.samples.elk.es.ElasticSearch;
import de.dbo.samples.elk.es.ElasticSearchException;
import de.dbo.samples.elk.logstash.Logstash;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.NoNodeAvailableException;
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
public class Client0 {
	private final static Logger log = LoggerFactory.getLogger(Client0.class);
	
    private final Logstash logstash;
    private final ElasticSearch es;
	private Client client = null;
	 
	public Client0(final Logstash logstash, final ElasticSearch es) {
		this.logstash = logstash;
		this.es = es;
		log.trace("created. " + this.es.print());
		open();
	}
	
	/**
	 * open this client.
	 * To run a query, client should be already opened
	 */
	public final void open() {
		if (null==client) {
			client =  es.elasticsearchClient();
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
		final String index = todayLogstashIndex(logstash);
		final String queryInfo = "query "+ index;
		try {
			log.trace(queryInfo + " ... ");
			final SearchHit[] ret;
			if (null==client) {
				ret = new SearchHit[]{};
				log.warn(queryInfo + "rejected: no client opend");
			} else {
				final SearchResponse response = client
					.prepareSearch(index)
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
			throw new ElasticSearchException(
					"Can't run query for " + es.print() + "\nThe ES-server is down?"
					,e );
		}
	 }
}