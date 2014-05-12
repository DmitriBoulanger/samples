package de.dbo.samples.elk.logstash;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTimeZone;

public class LogstashDefault implements Logstash {

	/*
	 * default values from Logstash-configuration
	 */
    protected String logType  = "log4j";
    protected String indexNameExrension = "log4j";
    protected String indexSufffix = "YYYY.MM.dd";
    
    /**
     * Standard Logstash fields and settings
     */
    public LogstashDefault() {
    	
    }

    @Override
    public final StringBuilder todayIndex() {
        return index(new Date());
    }

    @Override
    public final StringBuilder beforeThisDayIndex(final int n) {
        return index(subtractDays(new Date(), n));
    }

    @Override
    public final StringBuilder beforeThisHourIndex(final int n) {
    	final long now = System.currentTimeMillis();
    	final DateTimeZone timezone = DateTimeZone.getDefault();
    	final long offset = timezone.getOffsetFromLocal(now);
        return index(subtractHours(new Date(now-offset), n));
    }
  
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

    @Override
    public final StringBuilder indexName() {
        final String indexNameExtension = getIndexNameExrension();
        final StringBuilder ret = new StringBuilder(Logstash.LOGSTASH);
        if (nn(indexNameExtension)) {
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
    
    protected static final boolean nn(final Object o) {
        return null!=o && 0!= ((String)o).trim().length();
    }
    
    protected static Date subtractDays(final Date date, final int n) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -n);
        return cal.getTime();
    }

    protected static Date subtractHours(final Date date, final int n) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, -n);
        return cal.getTime();
    }
    
}
