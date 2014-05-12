package de.dbo.samples.elk.logstash;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LogstashDefault implements Logstash {

	/*
	 * default values from Logstash-configuration
	 */
    protected String logType  = "log4j";
    protected String indexNameExrension = "log4j";
    protected String indexSufffix = "YYYY.MM.dd";

    @Override
	public final StringBuilder print(final Map<String,Object> fieldValues, final List<String> names) {
		final StringBuilder sb = new StringBuilder();
		for (final String name:names) {
			final Object value = fieldValues.get(name);
			if (nn(value)) {
				sb.append("\n - "+name+": " + value);
			}
		}
		return sb;
	}

    protected static final boolean nn(final Object o) {
        return null!=o && 0!= ((String)o).trim().length();
    }

    @Override
    public final StringBuilder indexName() {
        final String indexNameExtension = getIndexNameExrension();
        final StringBuilder ret = new StringBuilder(Logstash.LOGSTASH);
        if (null != indexNameExtension && 0 != indexNameExtension.trim().length()) {
            ret.append("-" + indexNameExtension.trim());
        }
        return ret;
    }

    @Override
    public final StringBuilder index(final Date date) {
        final StringBuilder ret = new StringBuilder(indexName());
        ret.append("-");
        ret.append(new SimpleDateFormat(getIndexSufffix()).format(date));
        return ret;
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
