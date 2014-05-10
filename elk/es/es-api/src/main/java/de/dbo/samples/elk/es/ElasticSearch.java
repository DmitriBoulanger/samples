package de.dbo.samples.elk.es;

import java.text.ParseException;

import org.elasticsearch.client.Client;

/**
 * Parameters of a ElasticSearch server
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface ElasticSearch {
	
	 /**
     * creates new transport client
     *
     * @param esServer parameters of the remote ElasticSearch server
     * @return new transport client
     */
	public abstract Client elasticsearchClient();

	public abstract StringBuilder print();
	
	 /**
     * converts ES-timestamp into standard milliseconds
     *
     * @param timestampES
     * @return milliseconds
     *
     * @throws ParseException
     */
	 public long timestamp(final String timestampES) throws ParseException;

	/**
	 * host of the ElasticSearch-server
	 */
	public abstract String getHost();
	
	/**
	 * name of the node if it is set in <ES-home>\config\elasticsearch.yml
	 */
	public abstract String getNode();

	/**
	 * port used  in the ElasticSearch-server.
	 * Default is 9300. 
	 * Consult/change ES-configuration in <ES-home>\config\elasticsearch.yml
	 */
	public abstract Integer getPort();

	/**
	 * name of the cluster. Default is elasticsearch. 
	 * Consult/change ES-configuration in [ES-home]\config\elasticsearch.yml
	 */
	public abstract String getCluster();

	public abstract int getMaxReturn();

	public abstract String getTimestampFormat();

	public abstract String getTimezone();
	
	public abstract void setHost(String host);
	
	public abstract void setNode(String node);

	public abstract void setPort(Integer port);

	public abstract void setCluster(String cluster);

	public abstract void setMaxReturn(int maxReturn);

	public abstract void setTimestampFormat(String timestampFormat);

	public abstract void setTimezone(String timezone);

}