package de.dbo.util0;;

public final class Profiler {
	
	private static final String ZERO = "00";

	public  static String formatMs(long time) {
		
		final int milliseconds = (int)(time % 1000);
		final int seconds =      (int)((time/1000) % 60);
		final int minutes =      (int)((time/60000) % 60);
		final int hours =        (int)((time/3600000) % 24);
		
		final String millisecondsStr = (milliseconds<10 ? "00" : (milliseconds<100 ? "0" : ""))+milliseconds;
		final String secondsStr      = (seconds<10      ? "0" : "") + seconds;
		final String minutesStr      = (minutes<10      ? "0" : "") + minutes;
		final String hoursStr        = (hours<10        ? "0" : "") + hours;
		
		if (hoursStr.equals(ZERO) && minutesStr.equals(ZERO)) {
			return new String(secondsStr+" sec. "+millisecondsStr+ " ms.");
		}
		else if (hoursStr.equals(ZERO)) {
			return new String(minutesStr+" min. "+secondsStr+" sec. ");
		}
		else {
			return new String(hoursStr+"h. "+minutesStr+" min. ");
		}
	}
	
	public static String elapsed(long start) {
		return new String("Elapsed: "+formatMs(System.currentTimeMillis()-start));
	}
}
