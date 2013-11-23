package de.dbo.samples.tuprolog.tuprolog0.junit.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.dbo.samples.tuprolog0.Solver;

import alice.tuprolog.Int;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import alice.tuprolog.Var;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public abstract class PathTests {
	 
	protected static Solver solver = null;
	
	// :- find(X,Y,Path,maxLength).
	@Test
	public final void allPaths() throws Exception {
		final Var output = new Var( "Path" );
		final Int maxLength = new Int(50); 
		final Term goal = paths(output, maxLength);
		assertTrue("all paths should have solutions"
				,solver.solve(goal, maxLength, output) );
	}
	
	// :- find(1,Y,Path,maxLength).
	@Test
	public final void pathFrom() throws Exception {
		final Var output = new Var( "Path" );
		final Int maxLength = new Int(50); 
		final Term goal = pathFrom(1, output, maxLength);
		assertTrue("path from 1 should have solutions"
				,solver.solve(goal, maxLength, output) );
	}
	
	// :- find(X,6,Path,maxLength).
	@Test
	public final void pathTo() throws Exception {
		final Var output = new Var( "Path" );
		final Int maxLength = new Int(50); 
		final Term goal = pathTo(6, output, maxLength);
		assertTrue("path to 6 should have solutions"
				,solver.solve(goal, maxLength, output) );
	}
	
	// :- find(1,6,Path,maxLength).
	@Test
	public final void pathFromTo() throws Exception {
		final Var output = new Var( "Path" );
		final Int maxLength = new Int(50); 
		final Term goal = pathFromTo(1, 6, output, maxLength);
		assertTrue("path from 1 to 6 should have solutions"
				,solver.solve(goal, maxLength, output) );
	}
	
	// :- find(X,X,Path,maxLength).
	@Test
	public final void pathFromToMe() throws Exception {
		final Var output = new Var( "Path" );
		final Int maxLength = new Int(50); 
		final Term goal = pathFromToMe(output, maxLength);
		assertFalse("path from to me should NOT have solutions"
				,solver.solve(goal, maxLength, output) );
	}
	
	// :- findCycle(X,Y,PathForward,maxLength).
	@Test
	public final void allCycles() throws Exception {
			final Var output = new Var( "Path" );
			final Int maxLength = new Int(50); 
			final Term goal = cycles(output, maxLength);
			assertFalse("path from to me should NOT have solutions"
					,solver.solve(goal, maxLength, output) );
	}
	
	// goals
	private static final Term pathFrom( int from,  Var output, Int maxLength) {
		return new Struct("find", new Int(from), new Var("To"), output, maxLength);
	}
	private static final Term pathFromTo( int from, int to, Var output, Int maxLength) {
		return new Struct("find", new Int(from), new Int(to), output, maxLength);
	}
	private static final Term pathFromToMe( Var output, Int maxLength) {
		return new Struct("find",  new Var("X"), new Var("X"), output, maxLength);
	}
	private static final Term pathTo( int to, Var output, Int maxLength) {
		return new Struct("find", new Var("From"), new Int(to), output, maxLength);
	}
	private static final Term cycles( Var output, Int maxLength) {
		return new Struct("findCycle", new Var("From"), new Var("To"), output, maxLength);
	}
	private static final Term paths( Var output, Int maxLength) {
		return new Struct("find", new Var("From"), new Var("To"), output, maxLength);
	}
	
}
