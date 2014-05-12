package de.dbo.samples.elk.client0;

import static de.dbo.samples.elk.client0.Time.formatMs;
import static de.dbo.samples.elk.client0.Tool.padLeft;
import static de.dbo.samples.elk.client0.Tool.padRight;
import static org.elasticsearch.action.search.SearchType.QUERY_AND_FETCH;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.status.IndexStatus;
import org.elasticsearch.action.admin.indices.status.IndicesStatusResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.NoNodeAvailableException;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.indices.IndexMissingException;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.elk.es.ElasticSearch;
import de.dbo.samples.elk.es.ElasticSearchException;
import de.dbo.samples.elk.logstash.Logstash;

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

	public StringBuilder printIndices() {
		final Map<String, IndexStatus> indicesStatus =  getIndicesStatus();
		if (null==indicesStatus) {
			return  new StringBuilder("NULL");
		}

		final StringBuilder sb = new StringBuilder();
		final SortedSet<String> names = new TreeSet<String>( indicesStatus.keySet() );
		for (final String key : names) {
			sb.append("\n\t - ");
			sb.append(padRight(key,35));
			sb.append(padLeft(""+ indicesStatus.get(key).getStoreSize().getKb(), 8) );
			sb.append(" KB.");
		}
		return sb;
	}

	public  Map<String, IndexStatus> getIndicesStatus() {
		final AdminClient adminClient = client.admin();
		if (null == adminClient) {
			log.error("Can't get admin-client");
			return null;
		}
		final IndicesAdminClient indicesAdminClient = client.admin().indices();
		if (null == indicesAdminClient) {
			log.error("Can't get indices from admin-client");
			return null;
		}

		final IndicesStatusResponse indicesStatusResponse = indicesAdminClient.prepareStatus().get();
		final Map<String, IndexStatus> indicesStatus = indicesStatusResponse.getIndices();
		return indicesStatus;
	}

	public boolean deleteIndex(final String index) {
		final AdminClient adminClient = client.admin();
		if (null == adminClient) {
			log.error("Can't get admin-client");
			return false;
		}
		final IndicesAdminClient indicesAdminClient = client.admin().indices();
		if (null == indicesAdminClient) {
			log.error("Can't get admin-indices");
			return false;
		}

		log.trace("index " + index + " delete ...");
		try {
			final IndicesStatusResponse indicesStatusResponse = indicesAdminClient.prepareStatus().get();
			final IndexStatus indexStatus = indicesStatusResponse.getIndex(index);
			if (null==indexStatus) {
				log.trace("index " + index + " not found");
				return false;
			}
		} catch (IndexMissingException e) {
			 log.trace(e.toString());
			 return false;
		}

		final DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index.toString());
		final ActionFuture<DeleteIndexResponse> delete = indicesAdminClient.delete(deleteIndexRequest);
		if (!delete.actionGet().isAcknowledged()) {
			log.error("Index wasn't deleted");
			return false;
		} else {
			log.trace("index " + index + " delete done");
			return true;
		}
	}

	public SearchHit[] run(final QueryBuilder query, final String index) {
		final long start = System.currentTimeMillis();
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