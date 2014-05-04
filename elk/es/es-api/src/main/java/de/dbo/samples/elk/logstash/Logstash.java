package de.dbo.samples.elk.logstash;

public interface Logstash {

	/**
	 * standard logstash field containing logger-name
	 */
	public static final String LOGGER_NAME_FIELD = "logger_name";
	
	/**
	 * standard logstash field containing message itself
	 */
	public static final String MESSAGE_FIELD = "message";
	
	/**
	 * standard logstash field containing logger-type, e.g. log4j
	 */
	public static final String LOGGER_TYPE_FIELD = "type";
	
	/**
	 * standard logstash field containing message priority
	 */
	public static final String PRIORITY_FIELD = "priority";
	
	/**
	 * standard logstash field containing timestamp
	 */
	public static final String TIMESTAMP_FIELD = "@timestamp";
	
	/**
	 * standard logstash field possibly containing stack-trace of an exception
	 */
	public static final String STACK_TRACE_FIELD = "stack_trace";

	/**
	 * optional name-extension of the logstash index-name. Default is no extension.
	 * If extension is set, e.g as "my", then the corresponding index-name is "logstash-my".
	 * The complete logstash index-name is the index-name plus indexSufffix.
	 * Its format: logstash-[-IndexNameExtension]YYYY.MM.DD, e.g. logstash-my-2014.05.04
	 * 
	 * @see #getIndexSufffix()
	 */
	public abstract String getIndexNameExrension();

	/**
	 * logstash index-suffix.
	 * Default is YYYY.MM.dd
	 */
	public abstract String getIndexSufffix();

	public abstract void setIndexNameExrension(String indexNameExrension);

	public abstract void setIndexSufffix(String indexSufffix);

}