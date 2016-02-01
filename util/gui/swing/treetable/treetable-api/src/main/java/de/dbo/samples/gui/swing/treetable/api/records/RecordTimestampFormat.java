package de.dbo.samples.gui.swing.treetable.api.records;

import java.text.DecimalFormat;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 
 * Convert a millisecond duration to a string format.
 * Canonical format is "HH h. MM min. SS sec. SSS ms. ".
 * 
 * @author Dmitri Boulanger, Hombach
 * 
 * D. Knuth: Programs are meant to be read by humans and only
 * incidentally for computers to execute
 * 
 */
public final class RecordTimestampFormat {
	
	public static final int FORMAT_CANONICAL = 0;
	public static final int FORMAT_CANONICAL_MINIMIZED = 1;
	public static final int FORMAT_CANONICAL_NO_ZERO = 2;
	
	public static final int FORMAT_COMPRESSED = 10;
	
	public static final RecordTimestampFormat TIMESTAMP_NULL = 
			new RecordTimestampFormat("","","","");
	
	public static final RecordTimestampFormat TIMESTAMP_ERROR =
			new RecordTimestampFormat("99","99", "99","999");
	
	public static final RecordTimestampFormat newInstance(long time) {
		if (time < 0) {
			return RecordTimestampFormat.TIMESTAMP_ERROR;
		}

		long millliseconds = time;

		final long hours = MILLISECONDS.toHours(millliseconds);
		millliseconds -= HOURS.toMillis(hours);

		final long minutes = MILLISECONDS.toMinutes(millliseconds);
		millliseconds -= MINUTES.toMillis(minutes);

		final long seconds = MILLISECONDS.toSeconds(millliseconds);
		millliseconds -= SECONDS.toMillis(seconds);
		
		return new RecordTimestampFormat(
				   0 < hours   ? "" + hours   : "0"
				,  0 < minutes ? "" + minutes : "0"
				,  0 < seconds ? "" + seconds : "0"
				, "" + millliseconds);
	}

	private final Integer hh;
	private final Integer mm;
	private final Integer ss;
	private final Integer sss;
	
	private RecordTimestampFormat(final String hh, final String mm, final String ss, final String sss) {
		this.hh = integer(hh);
		this.mm = integer(mm);
		this.ss = integer(ss);
		this.sss = integer(sss);
	}
	
	/**
	 * formatted representation of this timestamp.
	 * 
	 * @param format format-index
	 * @return formatted string
	 * 
	 * @see #FORMAT_CANONICAL
	 * @see #FORMAT_CANONICAL_MINIMIZED
	 * @see #FORMAT_CANONICAL_NO_ZERO
	 * @see #FORMAT_COMPRESSED
	 * 
	 * 
	 */
	public StringBuilder print(final int format) {
		switch (format) {
		case FORMAT_CANONICAL:
			return canonical();
		case FORMAT_CANONICAL_MINIMIZED: 
			return canonicalMinimized();
		case FORMAT_CANONICAL_NO_ZERO: 
			return canonicalNoZero();
		case FORMAT_COMPRESSED: 
			return compressed();
		
		default:
				throw new RecordException("Unknown format:" + format);
		}
	}
	
	/**
	 * index values: 0:HH, 1:MM, 2:SS, 3:SSS.
	 * 
	 * @param position-index
	 * 
	 * @return specified attribute of this timestamp
	 */
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
	 * canonical representation as HH h. MM min. SS sec. SSS ms.
	 */
	private StringBuilder canonical() {
		final boolean ishh = null != hh;
		final boolean ismm = null != mm;
		final boolean isss = null != ss;
		final boolean issss = null != sss;

		final StringBuilder ret = new StringBuilder();
		ret.append(ishh ? padLeft(hh, 3) + " h." : "");
		ret.append(ismm ? padLeft(mm, 3) + " min." : "");
		ret.append(isss ? padLeft(ss, 3) + " sec." : "");
		ret.append(issss ? padLeft(sss, 4) + " ms." : "");

		return ret;
	}
	
	/**
	 * canonical representation as HH.MM.SS:SSS
	 */
	private StringBuilder compressed() {
		final boolean ishh = null != hh;
		final boolean ismm = null != mm;
		final boolean isss = null != ss;
		final boolean issss = null != sss;

		final StringBuilder ret = new StringBuilder();
		ret.append(ishh ? df(hh, DF2) + "." : "");
		ret.append(ismm ? df(mm, DF2) + "." : "");
		ret.append(isss ? df(ss, DF2) + ":" : "");
		ret.append(issss ? df(sss, DF3) : "");
		return ret;
	}
	
	/**
	 * canonical representation as HH h. MM min. SS sec. SSS ms. 
	 * but zero-values are omitted is a natural way
	 */
	private StringBuilder canonicalMinimized() {
		 
		final boolean ishh =  null != hh  && 0 != hh.intValue();
		final boolean ismm =  null != mm  && 0 != mm.intValue();
		final boolean isss =  null != ss  && 0 != ss.intValue();
		final boolean issss = null != sss && 0 != sss.intValue();
		
		final boolean ishh0 =  null != hh  && 0 == hh.intValue();
		final boolean ismm0 =  null != mm  && 0 == mm.intValue();
		final boolean isss0 =  null != ss  && 0 == ss.intValue();
		final boolean issss0 = null != sss && 0 == sss.intValue();
		
		final StringBuilder ret = new StringBuilder();
		if (ishh) {
			ret.append(padLeft(hh, 3) + " h.");
		}
		if (ismm || ishh) {
			ret.append(padLeft(mm, 3) + " min.");
		}
		if ( isss  ||  ((ishh || ismm) && issss)  ) {
			ret.append(padLeft(ss, 3) + " sec.");
		}
		if (issss) {
			ret.append(padLeft(sss, 4) + " ms.");
		}
		if ( ishh0  && ismm0 && isss0 && issss0) {
			ret.append(padLeft(0, 4) + " ms.");
		}
		return ret;
	}
	
	/**
	 * canonical representation as HH h. MM min. SS sec. SSS ms. 
	 * but all zero-values are omitted
	 */
	private StringBuilder canonicalNoZero() {
		 
		final boolean ishh =  null != hh  && 0 != hh.intValue();
		final boolean ismm =  null != mm  && 0 != mm.intValue();
		final boolean isss =  null != ss  && 0 != ss.intValue();
		final boolean issss = null != sss && 0 != sss.intValue();
		
		final boolean ishh0 =  null != hh  && 0 == hh.intValue();
		final boolean ismm0 =  null != mm  && 0 == mm.intValue();
		final boolean isss0 =  null != ss  && 0 == ss.intValue();
		final boolean issss0 = null != sss && 0 == sss.intValue();
		
		final StringBuilder ret = new StringBuilder();
		if (ishh) {
			ret.append(padLeft(hh, 3) + " h.");
		}
		if (ismm) {
			ret.append(padLeft(mm, 3) + " min.");
		}
		if (isss) {
			ret.append(padLeft(ss, 3) + " sec.");
		}
		if (issss) {
			ret.append(padLeft(sss, 4) + " ms.");
		}
		if ( ishh0  && ismm0 && isss0 && issss0) {
			ret.append(padLeft(0, 4) + " ms.");
		}
		return ret;
	}
	
	// HELPERS
	
	private static final Integer integer(final String value) {
		if (null == value || 0 == value.trim().length()) {
			return null;
		}
		return Integer.parseInt(value.trim());
	}
	
	private static final String DF2 = "00";
	private static final String DF3 = "000";
	
	private static final String df(final Integer value, final String df) {
		return new DecimalFormat(df).format(value);
	}
	
    private static String padLeft(final int s, int n) {
        return String.format("%1$" + n + "s", s);
    }
}
