package de.dbo.samples.elk.client0;

import static de.dbo.samples.elk.client0.Time.subtractDays;
import static de.dbo.samples.elk.client0.Time.subtractHours;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.elk.es.ElasticSearch;
import de.dbo.samples.elk.logstash.Logstash;

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
