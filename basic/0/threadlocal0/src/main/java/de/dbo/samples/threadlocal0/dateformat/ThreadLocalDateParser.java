package de.dbo.samples.threadlocal0.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * The thread-local approach offers a third way by creating an object per thread, 
 * so different threads cannot interfere with each other's objects. 
 * This approach maintains liveness,  but not at the expense of resources 
 * because only so many instances of threads are created.
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class ThreadLocalDateParser implements DateParser {
	
	private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT_THREAD_LOCAL = 
			new ThreadLocal<SimpleDateFormat>() {
	
		        @Override
				protected final SimpleDateFormat initialValue() {
						return new SimpleDateFormat("dd/MM/yyyy");
				}
	};

	public Date parse(String text) throws ParseException {
		return SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().parse(text);
	}
}
