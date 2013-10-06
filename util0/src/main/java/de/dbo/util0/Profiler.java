package de.dbo.util0;

import java.util.concurrent.TimeUnit;

public final class Profiler {
	
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
        if(time < 0){
           return "?";
        }
        
        long millliseconds = time;

        final long hours = TimeUnit.MILLISECONDS.toHours(millliseconds);
        millliseconds -= TimeUnit.HOURS.toMillis(hours);
        
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(millliseconds);
        millliseconds -= TimeUnit.MINUTES.toMillis(minutes);
        
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(millliseconds);
        millliseconds -= TimeUnit.SECONDS.toMillis(seconds);

        final StringBuilder sb = new StringBuilder(64);
        if (0<hours) {
			sb.append(zeros(1,hours));
			sb.append(" h. ");
		}
		if (0<minutes) {
			sb.append(zeros(1,minutes));
			sb.append(" min. ");
		}
		if (0<seconds) {
			sb.append(zeros(1, seconds));
			sb.append(" sec. ");
		}
		sb.append(zeros(2,millliseconds));
        sb.append(" ms. ");

        return(sb.toString());
    }

	private static final String zeros(final int cnt, final long x) {
		switch (cnt) {
		case 2:
		if (x<10) {
			return "00" + x;
		} else if (x<100) {
			return "0" + x;
		} else {
			return "" + x;
		}
		case 1:
			if (x<10) {
				return "0" + x;
			} else {
				return "" + x;
			}
			default: return "" +x;
		}
	}
	
	/**
     * Convert elapsed  duration to a string format
     * 
     * @param time A duration in milliseconds to convert to a string form
     * @return A string of the form "Elapsed: X h. Y min. Z sec. W ms. ".
     */
	public static String elapsed(long start) {
		return new String("Elapsed: " + formatMs(System.currentTimeMillis()-start));
	}
}
