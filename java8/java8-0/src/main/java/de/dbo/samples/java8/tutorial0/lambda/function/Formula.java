package de.dbo.samples.java8.tutorial0.lambda.function;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */

@FunctionalInterface
public interface Formula {

    /* abstraction to be implemented */
    abstract double calculate(int a);

    public default double sqrt(int a) {
	return Math.sqrt(positive(a));
    }

    public default int positive(int a) {
	return a > 0 ? a : 0;
    }
    
//    static int positiveStaic(int a) {
//        return a > 0 ? a : 0;
//    }
    
}
