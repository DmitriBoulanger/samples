package de.dbo.samples.tuprolog.tuprolog0.objects;

import static java.lang.System.currentTimeMillis;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

/**
 * Timer-object to be used by the solver 
 * Objects are used and initialized in the solver-library
 * 
 * @see de.dbo.samples.tuprolog.tuprolog0.Library#getObjectInitializationSubTheory()
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class Timer {
	private static final Logger log = getLogger(Timer.class);

	private long start;
	
	public Timer() {
		start = currentTimeMillis();
		log.debug("timer created.");
	}
	
	public void reset() {
		start = currentTimeMillis();
	}
	
	public long elapsed()  {
		return currentTimeMillis() - start;
	}
}
