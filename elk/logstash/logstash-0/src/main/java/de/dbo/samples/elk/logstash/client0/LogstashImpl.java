package de.dbo.samples.elk.logstash.client0;

import de.dbo.samples.elk.logstash.Logstash;

public final class LogstashImpl implements Logstash  {

	/*
	 * standard values from Logstash-configuration
	 */
    private final String logType  = "log4j";
    private String       indexNameExrension = "log4j";
    private final String indexSufffix = "YYYY.MM.dd";

    /**
     * Standard Logstash fields and settings
     */
    public LogstashImpl() {
    }

    @Override
    public String getLogType() {
        return logType;
    }

    @Override
    public String getIndexNameExrension() {
        return indexNameExrension;
    }

    @Override
    public String getIndexSufffix() {
        return indexSufffix;
    }

    @Override
    public void setIndexNameExrension(final String indexNameExrension) {
    	this.indexNameExrension = indexNameExrension;
    }

    @Override
    public void setIndexSufffix(final String indexSufffix) {

    }

    @Override
    public void setLogType(final String logType) {

    }
}
