package de.dbo.samples.elk.es;

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

	public abstract StringBuilder print();

	/**
	 * host of the ElasticSearch-server
	 */
	public abstract String getHost();

	/**
	 * port used  in the ElasticSearch-server.
	 * Default is 9300. 
	 * Consult/change ES-configuration in <ES-home>\config\elasticsearch.yml
	 */
	public abstract Integer getPort();

	/**
	 * port used in the ElasticSearch-server.
	 * Default is elasticsearch. 
	 * Consult/change ES-configuration in <ES-home>\config\elasticsearch.yml
	 */
	public abstract String getCluster();

	public abstract int getMaxReturn();

	public abstract String getTimestampFormat();

	public abstract String getTimezone();
	
	
	
	
	

	public abstract void setHost(String host);

	public abstract void setPort(Integer port);

	public abstract void setCluster(String cluster);

	public abstract void setMaxReturn(int maxReturn);

	public abstract void setTimestampFormat(String timestampFormat);

	public abstract void setTimezone(String timezone);

}