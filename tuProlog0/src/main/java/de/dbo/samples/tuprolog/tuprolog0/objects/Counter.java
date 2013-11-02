package de.dbo.samples.tuprolog.tuprolog0.objects;

/**
 * Counter-object to be used by the solver 
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
public class Counter {
	
	private int value;
	
	public Counter() {
		value = 0;
	}
	
	public void reset() {
		value = 0;
	}
	
	public int incr() {
		value++;
		return value();
	}
	
	public int value() {
		return value;
	}

}
