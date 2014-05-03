package de.dbo.samples.elk.logstash.es.client;


import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Package-private utilities used in the client-implementation
 *
 * @author Dmitri Boulanger
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */
public final class Tool {
    private static final Logger log = LoggerFactory.getLogger(Tool.class);

    /**
     * converts Log4j-timestamp into standard milliseconds
     *
     * @param timestampLog4j
     * @return milliseconds
     *
     * @throws ParseException
     */
    public static final long timestamp(final String timestampLog4j, final ElasticSearch es) 
    		throws ParseException {
        final DateTimeZone esDateTimeZone = DateTimeZone.forID(es.getTimezone());
        final String estTmestampPattern = es.getTimestampFormat();
        final DateTimeFormatter formatter = DateTimeFormat.forPattern(estTmestampPattern).withZone(esDateTimeZone);
        final long ret = formatter.parseDateTime(timestampLog4j).getMillis();
        log.trace("timestamp.log4j " + esDateTimeZone + " : " + timestampLog4j + "  => " + ret);
        return ret;
    }




    public static final String todayLogstashIndex(final Logstash logstash) {
        return logstashIndex(new Date(), logstash);
    }

    public static final String beforetodayLogstashIndex(final Logstash logstash) {
        return logstashIndex(subtractDays(new Date(), 1), logstash);
    }

    public static final String logstashIndex(final Date date, final Logstash logstash) {
        final SimpleDateFormat formatter = new SimpleDateFormat(logstash.getIndexSufffix());
        return indexName(logstash) + "-" + formatter.format(date);
    }

    public static final StringBuilder indexName(final Logstash logstash) {
        final String indexNameExtension = logstash.getIndexNameExrension();
        final StringBuilder ret = new StringBuilder("logstash");
        if (null != indexNameExtension && 0 != indexNameExtension.trim().length()) {
            ret.append("-" + indexNameExtension.trim());
        }
        return ret;
    }

    public static Date subtractDays(final Date date, final int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -n);
        return cal.getTime();
    }

    private static final String DF2 = "00";
    private static final String DF3 = "000";

    public static final String formatMs(final long time) {
        if (time < 0) {
            return "?";
        }
        final DecimalFormat df2 = new DecimalFormat(DF2);
        final DecimalFormat df3 = new DecimalFormat(DF3);
        long millliseconds = time;

        final long hours = MILLISECONDS.toHours(millliseconds);
        millliseconds -= HOURS.toMillis(hours);

        final long minutes = MILLISECONDS.toMinutes(millliseconds);
        millliseconds -= MINUTES.toMillis(minutes);

        final long seconds = MILLISECONDS.toSeconds(millliseconds);
        millliseconds -= SECONDS.toMillis(seconds);

        final StringBuilder sb = new StringBuilder(64);
        if (0 < hours) {
            sb.append(df2.format(hours));
            sb.append(" h. ");
        }
        if (0 < minutes) {
            sb.append(df2.format(minutes));
            sb.append(" min. ");
        }
        else {
            sb.append(df2.format(0));
            sb.append(" min. ");
        }
        if (0 < seconds) {
            sb.append(df2.format(seconds));
            sb.append(" sec. ");
        }
        sb.append(df3.format(millliseconds));
        sb.append(" ms. ");

        return (sb.toString());
    }

    public static String padLeft(final String s, final int n) {
        return String.format("%1$" + n + "s", s);
    }

    public static String padRight(final String s, final int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static final String q2(final String x) {
        final StringBuilder ret = new StringBuilder();
        ret.append("\"");
        ret.append(x);
        ret.append("\"");
        return ret.toString();
    }
    
    public static final boolean nn(final String x) {
        return null!=x && 0!=x.trim().length();
    }
}
