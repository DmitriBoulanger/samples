package de.dbo.samples.gui.swing.treetable.api.records;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

public final class RecordRelativeTimestamp {
	
	public static final RecordRelativeTimestamp TIMESTAMP_NULL = 
			new RecordRelativeTimestamp("","","","");
	
	public static final RecordRelativeTimestamp TIMESTAMP_ERROR =
			new RecordRelativeTimestamp("99","999", "99","999");
	
	/**
	 * Convert a millisecond duration to a string format
	 * 
	 * @param time
	 *            A duration in milliseconds to convert to a string form
	 * @return A string of the form "X h. Y min. Z sec. W ms. ".
	 */
	public static final RecordRelativeTimestamp newInstance(long time) {
		if (time < 0) {
			return RecordRelativeTimestamp.TIMESTAMP_ERROR;
		}

		long millliseconds = time;

		final long hours = MILLISECONDS.toHours(millliseconds);
		millliseconds -= HOURS.toMillis(hours);

		final long minutes = MILLISECONDS.toMinutes(millliseconds);
		millliseconds -= MINUTES.toMillis(minutes);

		final long seconds = MILLISECONDS.toSeconds(millliseconds);
		millliseconds -= SECONDS.toMillis(seconds);
		
		return new RecordRelativeTimestamp(
				   0 < hours   ? padRight(hours, 2)     : "0"
				,  0 < minutes ? padRight(minutes, 2) : "0"
				,  0 < seconds ? padRight(seconds, 2) : "0"
				, padRight(millliseconds, 3));
		 
	}

	private final Integer hh;
	private final Integer mm;
	private final Integer ss;
	private final Integer sss;
	
	
	private RecordRelativeTimestamp(final String hh, final String mm, final String ss, final String sss) {
		this.hh = integer(hh);
		this.mm = integer(mm);
		this.ss = integer(ss);
		this.sss = integer(sss);
	}
	
	public RecordRelativeTimestamp(final RecordRelativeTimestamp relativeTimestamp) {
		this.hh = relativeTimestamp.hh;
		this.mm = relativeTimestamp.mm;
		this.ss = relativeTimestamp.ss;
		this.sss = relativeTimestamp.sss;
	}
	
	public Integer get(final int index) {
		 switch(index) {
		 case 0:
			 return hh;
		 case 1:
			 return mm;
		 case 2:
			 return ss;
		 case 3:
			 return sss;
		default:
				throw new RecordException("Index " + index + " is not known in RecordRelativeTimestamp"); 
		 }
	 }
	
	/**
	 * canonical representation as HH MM SS SSS
	 * @return
	 */
	public StringBuilder print() {
		final StringBuilder ret = new StringBuilder();
			ret.append(null != hh ? padLeft(hh, 3) + " h." : "");
		    ret.append(null != mm ? padLeft(mm, 3) + " min." : "");
		    ret.append(null != ss ? padLeft(ss, 3) + " sec." : "");
		    ret.append(padLeft(sss, 4) + " ms.");
		return ret;
	}
	
	/**
	 * minimized representation of HH MM SS SSS.
	 * Zero-values are omitted is a natural way
	 * 
	 * @param optimization flag
	 * 
	 * @return 
	 */
	public StringBuilder print(boolean minimized) {
		if (!minimized) {
			return print();
		}
		final boolean ishh =  null != hh  && 0 != hh.intValue();
		final boolean ismm =  null != mm  && 0 != mm.intValue();
		final boolean isss =  null != ss  && 0 != ss.intValue();
		final boolean issss = null != sss && 0 != sss.intValue();
		
		final StringBuilder ret = new StringBuilder();
		if (ishh) {
			ret.append(padLeft(hh, 3) + " h.");
		}
		if (ismm || ishh) {
			ret.append(padLeft(mm, 3) + " min.");
		}
		if ( isss || issss ) {
			ret.append(padLeft(ss, 3) + " sec.");
		}
		if (issss) {
			ret.append(padLeft(sss, 4) + " ms.");
		}
		return ret;
	}
	
	private static final Integer integer(final String value) {
		if (null == value || 0 == value.trim().length()) {
			return null;
		}
		return Integer.parseInt(value.trim());
	}
	 
	private static String padRight(final long s, int n) {
		return String.format("%1$-" + n + "s", s);
	}
	
    private static String padLeft(final int s, int n) {
        return String.format("%1$" + n + "s", s);
    }

}
