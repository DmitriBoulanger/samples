package de.dbo.samples.elk.logstash;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Logstash with Log4J-Logger.
 * Standard fields and options
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Logstash {
	
	public static final String LOGSTASH = "logstash";

	public abstract StringBuilder indexName();
	
	public abstract StringBuilder index(final Date date);
	
	/**
	 * prints values in the hit-fields
	 *   
	 * @param fieldValues map with name-value pairs from the server
	 * @param fieldNames expected field-names to print out
	 * 
	 */
	public abstract StringBuilder print(final Map<String, Object> fieldValues, final List<String> fieldNames);

	/**
	 * optional name-extension of the logstash index-name. 
	 * Default is no extension.
	 * If an extension is set, e.g as "my", then the corresponding index-name is "logstash-my".
	 * The complete logstash index-name is the index-name plus indexSufffix.
	 * Its format: logstash-[IndexNameExtension-]YYYY.MM.dd, e.g. logstash-my-2014.05.04
	 * 
	 * @see #getIndexSufffix()
	 */
	public abstract String getIndexNameExrension();

	/**
	 * logstash dynamic index-suffix.
	 * The actual value is changed automatically, i.e it rolls over at midnight UTC. 
	 * Default is YYYY.MM.dd
	 */
	public abstract String getIndexSufffix();
	
	/**
	 * logger type used in the Logstash-configuration
	 * Default is log4j
	 */
	public abstract String getLogType();
	    
	public abstract void setIndexNameExrension(String indexNameExrension);

	public abstract void setIndexSufffix(String indexSufffix);
	
	public abstract void setLogType(final String logType);

}