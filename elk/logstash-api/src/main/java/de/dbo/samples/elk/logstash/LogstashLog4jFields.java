package de.dbo.samples.elk.logstash;

/**
 * Logstash field-names if the log4j Plug-in is used
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface LogstashLog4jFields {
	
	/**
	 * logstash field containing logger-name
	 */
	public static final String NAME_FIELD = "logger_name";
	
	/**
	 * logstash field containing message itself
	 */
	public static final String MESSAGE_FIELD = "message";
	
	/**
	 * logstash field containing logger-type, e.g. log4j
	 */
	public static final String TYPE_FIELD = "type";
	
	/**
	 * logstash field containing message priority
	 */
	public static final String PRIORITY_FIELD = "priority";
	
	/**
	 * logstash field containing timestamp
	 */
	public static final String TIMESTAMP_FIELD = "@timestamp";
	
	/**
	 * logstash field containing timestamp
	 */
	public static final String EXCEPTION_FIELD = "stack_trace";

}
