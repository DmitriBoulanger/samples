package de.dbo.samples.tuprolog.tuprolog0.objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Timer {
	private static final Logger log = LoggerFactory.getLogger(Timer.class);

	private long start;
	
	public Timer() {
		start = System.currentTimeMillis();
		log.debug("timer created.");
	}
	
	public void reset() {
		start = System.currentTimeMillis();
	}
	
	public long elapsed()  {
		return System.currentTimeMillis() - start;
	}
}
