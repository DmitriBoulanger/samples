package de.dbo.samples.elk.logstash.es.client;

public final class Logstash  {
	
	/*
	 * Standard fields used by the Logstash with Log4j-logger
	 */
	 public static final String LOGGER_NAME_FIELD = "logger_name";
	 public static final String MESSAGE_FIELD = "message";
	 public static final String LOGGER_TYPE_FIELD = "type";
	 public static final String PRIORITY_FIELD = "priority";
	 public static final String TIMESTAMP_FIELD = "@timestamp";
	 public static final String STACK_TRACE_FIELD = "stack_trace";

    private String logType  = "log4j";
    private String indexNameExrension = "";
    private String indexSufffix = "YYYY.MM.dd";

    public Logstash() {
    }
    
    public String getLogType() {
        return logType;
    }

    public String getIndexNameExrension() {
        return indexNameExrension;
    }

    public String getIndexSufffix() {
        return indexSufffix;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public void setIndexNameExrension(String indexNameExrension) {
        this.indexNameExrension = indexNameExrension;
    }

    public void setIndexSufffix(String indexSufffix) {
        this.indexSufffix = indexSufffix;
    }

}
