package de.dbo.tuprolog.junit;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.tuprolog.Solver;
import de.dbo.tuprolog.junit.impl.PathTests;


public class MainMock extends PathTests {
	protected static final Logger log = LoggerFactory.getLogger(MainMock.class);
	 
	/* default solver that uses mock-theory */
	static final Solver solver() {
		try {
			final Solver solver = new Solver();
			solver.loadTheory("rules-mock.pl");
			solver.loadTheory("rules.pl");
			solver.loadTheory("db.pl");
			return solver;
		} catch (Exception e) {
			throw new RuntimeException("cannot create solver",e);
		}
	}
	
	@BeforeClass
	public static void init() {
		solver = solver();
		log.debug("Libraries: " + solver.printLibraries() );
	}
}
