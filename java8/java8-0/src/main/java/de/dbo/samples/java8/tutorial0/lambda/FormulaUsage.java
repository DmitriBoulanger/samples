package de.dbo.samples.java8.tutorial0.lambda;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class FormulaUsage {

    public static void main(String[] args) {
	final Formula formula = new Formula() {
	    @Override
	    public double calculate(int a) {
		return sqrt(a * 100);
	    }
	};

	System.out.println(formula.calculate(100));     // 100.0
	System.out.println(formula.sqrt(-23));          // 0.0
	System.out.println(Formula.positive(-4));       // 0.0

    }

}