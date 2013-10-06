package de.dbo.tuprolog;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends PathTests {
	protected static final Logger log = LoggerFactory.getLogger(Main.class);
	 
	/* solver with extension library */
	static final Solver solver() {
		try {
			final Solver solver = new Solver( new Library( "de.dbo.Prolog" ) );
			solver.loadTheory("rules.pl");
			solver.loadTheory("db.pl");
			solver.loadTheory("col1.pl");
			solver.loadTheory("col2.pl");
			solver.loadTheory("col3.pl");
			solver.loadTheory("table.pl");
			return solver;
		} catch (Exception e) {
			throw new RuntimeException("cannot create solver",e);
		}
	}
	
	@BeforeClass
	public static void init() {
		solver = solver();
		log.info("Libraries: " + solver.printLibraries() );
	}
}
