package de.dbo.samples.elk.client0;
 
import static de.dbo.samples.elk.client0.Tool.formatMs;
import static de.dbo.samples.elk.client0.Tool.todayLogstashIndex;

import de.dbo.samples.elk.es.ElasticSearch;
import de.dbo.samples.elk.es.ElasticSearchException;
import de.dbo.samples.elk.logstash.Logstash;

import org.elasticsearch.action.search.SearchResponse;

import static org.elasticsearch.action.search.SearchType.*;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.NoNodeAvailableException;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.indices.IndexMissingException;
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
	}
	
	/**
	 * open this client.
	 * To run a query, client should be already opened
	 */
	public final void open() {
		if (null==client) {
			client =  es.elasticsearchClient();
			log.trace("opened. " + this.es.print());
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
		final StringBuilder index = todayLogstashIndex(logstash);
		final String queryInfo = "query "+ index;
		try {
			log.trace(queryInfo + " ... ");
			final SearchHit[] ret;
			if (null==client) {
				ret = new SearchHit[]{};
				log.warn(queryInfo + "rejected: no client opend");
			} else {
				final SearchResponse response = client
					.prepareSearch(index.toString())
			        .setSearchType(QUERY_AND_FETCH)
			        .setQuery(query)
			        .setExplain(false)
			        .execute()
			        .actionGet();
			   ret = response.getHits().getHits();
			   log.trace(queryInfo + " done. Elapsed " + formatMs(System.currentTimeMillis()-start));
			}
			return ret;
		} catch(IndexMissingException e) {
			final String msg = "Can't run query. " + es.print() + ". No index found?";
			log.error(msg,e);
			throw new ElasticSearchException(msg,e);
		} catch (NoNodeAvailableException e) {
			final String msg = "Can't run query. " + es.print() + ". Server is down?";
			log.error(msg,e);
			throw new ElasticSearchException(msg,e);
		}
	 }
}