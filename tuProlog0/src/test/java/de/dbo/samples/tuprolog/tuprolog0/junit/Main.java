package de.dbo.samples.tuprolog.tuprolog0.junit;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.tuprolog.tuprolog0.Library;
import de.dbo.samples.tuprolog.tuprolog0.Solver;
import de.dbo.samples.tuprolog.tuprolog0.junit.impl.PathTests;

public final class Main extends PathTests {
    protected static final Logger log = LoggerFactory.getLogger(Main.class);

    /* solver with extension library */
    static final Solver solver() {
    	final String libraryLoggerName =
    			"de.dbo.samples.tuprolog.tuprolog0.tuprolog0.PrologLogger";
        try {
            final Solver solver = new Solver("Main", new Library(libraryLoggerName));
            solver.loadTheory("rules.pl");
            solver.loadTheory("db.pl");
            return solver;
        }
        catch(Exception e) {
            throw new RuntimeException("cannot create solver", e);
        }
    }

    @BeforeClass
    public static void init() {
        solver = solver();
        log.debug("Libraries: " + solver.printLibraries());
    }
}
