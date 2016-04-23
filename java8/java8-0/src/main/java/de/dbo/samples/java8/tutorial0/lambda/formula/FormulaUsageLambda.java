package de.dbo.samples.java8.tutorial0.lambda.formula;


/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class FormulaUsageLambda   {
    
    public static void main(String[] args) {
        final Formula formula = (a) ->  a * 100;
        
        //Default methods cannot be accessed from within lambda expressions. 
        //The following code does not compile:
        //final Formula formula = (a) ->  sqrt(a * 100);

	System.out.println(formula.calculate(100));     // 100.0
	System.out.println(formula.sqrt(-23));          // 0.0
	System.out.println(formula.positive(-4));       // 0.0
    }
 

}