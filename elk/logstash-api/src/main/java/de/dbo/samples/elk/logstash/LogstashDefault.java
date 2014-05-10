package de.dbo.samples.elk.logstash;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogstashDefault implements Logstash {
	
	/*
	 * default values from Logstash-configuration
	 */
    protected String logType  = "log4j";
    protected String indexNameExrension = "log4j";
    protected String indexSufffix = "YYYY.MM.dd";
    
    @Override
    public final String indexName() {
        final String indexNameExtension = getIndexNameExrension();
        final StringBuilder ret = new StringBuilder(Logstash.LOGSTASH);
        if (null != indexNameExtension && 0 != indexNameExtension.trim().length()) {
            ret.append("-" + indexNameExtension.trim());
        }
        return ret.toString();
    }
    
    public final String index(final Date date) {
        final SimpleDateFormat formatter = new SimpleDateFormat(getIndexSufffix());
        return indexName() + "-" + formatter.format(date);
    }

    /**
     * Standard Logstash fields and settings
     */
    public LogstashDefault() {
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
    	this.indexSufffix = indexSufffix;
    }

    @Override
    public void setLogType(final String logType) {
    	this.logType = logType; 
    }
}
