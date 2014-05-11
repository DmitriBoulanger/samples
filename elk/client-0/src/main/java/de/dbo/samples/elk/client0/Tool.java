package de.dbo.samples.elk.client0;

import de.dbo.samples.elk.es.ElasticSearch;
import de.dbo.samples.elk.logstash.Logstash;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Utilities used in the client-implementation
 *
 * @author Dmitri Boulanger
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */
public final class Tool {
    private static final Logger log = LoggerFactory.getLogger(Tool.class);
    
    /**
     * converts ES-timestamp into standard milliseconds
     *
     * @param timestampES
     * @return milliseconds
     *
     * @throws ParseException
     */
    public static final long timestamp(final String timestampES, final ElasticSearch ES) 
    		throws ParseException {
        final long ret = ES.timestamp(timestampES);
        log.trace("timestamp.es " + ES.getTimezone() + " : " + timestampES + "  => " + ret);
        return ret;
    }

    public static final StringBuilder todayLogstashIndex(final Logstash logstash) {
        return logstash.index(new Date());
    }

    public static final StringBuilder beforeThisDayLogstashIndex(final Logstash logstash
    		, final int n) {
        return logstash.index(subtractDays(new Date(), n));
    }
    
    public static final StringBuilder beforeThisHourLogstashIndex(final Logstash logstash
    		, final int n) {
        return logstash.index(subtractHours(new Date(), n));
    }

    private static Date subtractDays(final Date date, final int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -n);
        return cal.getTime();
    }
    
    private static Date subtractHours(final Date date, final int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, -n);
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
