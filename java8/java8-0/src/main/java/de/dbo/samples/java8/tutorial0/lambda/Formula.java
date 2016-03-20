package de.dbo.samples.java8.tutorial0.lambda;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */

public interface Formula {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(positive(a));
    }

    static int positive(int a) {
        return a > 0 ? a : 0;
    }
}
