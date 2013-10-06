package de.dbo.tuprolog;

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
