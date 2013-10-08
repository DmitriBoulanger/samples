package de.dbo.samples.util0;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 *  Converter for elapsed duration in milliseconds
 * 
 * @author Dmitri Boulanger, Hombach
 *
 */
public final class Profiler {
	
	private static final String DF2 = "00";
	private static final String DF3 = "000";

    private Profiler() {
        // should be never initialized as an instance
    }

    /**
     * Convert a millisecond duration to a string format
     * 
     * @param time A duration in milliseconds to convert to a string form
     * @return A string of the form "X h. Y min. Z sec. W ms. ".
     */
    public static String formatMs(final long time) {
        if (time < 0) {
            return "?";
        }
        final DecimalFormat df2 = new DecimalFormat(DF2);
        final DecimalFormat df3 = new DecimalFormat(DF3);
        long millliseconds = time;

        final long hours = TimeUnit.MILLISECONDS.toHours(millliseconds);
        millliseconds -= TimeUnit.HOURS.toMillis(hours);

        final long minutes = TimeUnit.MILLISECONDS.toMinutes(millliseconds);
        millliseconds -= TimeUnit.MINUTES.toMillis(minutes);

        final long seconds = TimeUnit.MILLISECONDS.toSeconds(millliseconds);
        millliseconds -= TimeUnit.SECONDS.toMillis(seconds);

        final StringBuilder sb = new StringBuilder(64);
        if (0 < hours) {
            sb.append(df2.format(hours));
            sb.append(" h. ");
        }
        if (0 < minutes) {
            sb.append(df2.format(minutes));
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

    /**
     * Convert elapsed  duration to a string format
     * 
     * @param time A duration in milliseconds to convert to a string form
     * @return A string of the form "Elapsed: 2 h. 10 min. 29 sec. 130 ms. ".
     */
    public static String elapsed(long start) {
        return new String("Elapsed: " + formatMs(System.currentTimeMillis() - start));
    }

}
