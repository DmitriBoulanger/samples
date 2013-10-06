package de.dbo.tuprolog;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import alice.tuprolog.Int;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;

public abstract class TableTests {
	 
	protected static Solver solver = null;
	
	// :- find(X,Y,Path,maxLength).
	@Test
	public final void allRows() throws Exception {
		final Var output = new Var( "Path" );
		final Int maxLength = new Int(50); 
		final Term goal = rows(output, maxLength);
		assertTrue("all paths should have solutions"
				,solver.solve(goal, maxLength, output) );
	}
	
	// table_row(Value1,Value2,Value3)
	
	
	private static final Term rows( Var output, Int maxLength) {
		return  new Struct("table_row"
				, new Var("Value1") 
				, new Var("Value2")
				, new Var("Value3"));
	}
	
}
