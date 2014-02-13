package de.dbo.samples.tuprolog0;

import static java.lang.System.currentTimeMillis;
import static de.dbo.samples.util0.Profiler.elapsed;
import static de.dbo.samples.util0.Print.lines;

import java.io.IOException;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import alice.tuprolog.Int;
import alice.tuprolog.InvalidLibraryException;
import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import alice.tuprolog.Var;

/**
 * Prolog-based solver
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class Solver {
    private static final Logger log    = LoggerFactory.getLogger(Solver.class);

    private static final String DF3 = "000";
    private final Prolog        engine;
    private final String        name;

    /**
     * Empty (standard) Prolog-solver
     */
    public Solver(final String name) throws InvalidLibraryException{
    	this(name,null);
    }

    /**
     * Solver with loaded library
     * 
     * @param library
     * @throws InvalidLibraryException
     */
    public Solver(final String name, final Library library) throws InvalidLibraryException {
    	this.name = name;
    	this.engine = new Prolog();
    	this.engine.setSpy(true);
        if (null!=library) {
			this.engine.loadLibrary(library);
		}
		
    }

    public final StringBuilder printLibraries() {
    	return lines(engine.getCurrentLibraries());
    }

    /**
     * loads a theory into this solver
     * @param resource relative path of the resource containing Prolog-rules
     * @throws IOException
     */
    public void loadTheory(final String resource) throws IOException {
    	 log.debug("loading resource " + resource + " ...");
        final Theory theory;
        try {
            theory = new Theory(Solver.class.getResourceAsStream(resource));
        }
        catch(Exception e) {
            log.error("error while reading " + resource + ": " + e.getMessage());
            return;
        }
        try {
            engine.addTheory(theory);
            log.debug("theory loaded: \n" + theory.toString());
        }
        catch(InvalidTheoryException e) {
            log.error("error while parsing " + resource + ": " + e.getMessage());
        }
    }

    /**
     * runs the specifies goal.
     * The method tries to generate all solutions.
     * Generated solutions are only shown as log-messages
     * 
     * @param goal
     * @param maxLength
     * @param output variable that contains solution
     * @return true if at least one solution found
     * @throws Exception
     */
    public boolean solve(final Term goal, final Int maxLength, final Var output) throws Exception {
        final long start = currentTimeMillis();
        
        log.debug("running " + goal.toString() + "...");
        final String outputVarname = output.getName();
        SolveInfo info = engine.solve(goal);
        int solutionCounter = 0;
        while (info.isSuccess()) {
            solutionCounter++;
            log.debug(name + " - solution " + solutionCounter + ": "+ info.getVarValue(outputVarname));
            if (engine.hasOpenAlternatives()) {
                info = engine.solveNext();
            }
            else {
                break;
            }
            if (solutionCounter > 100) {
                break;
            }
        }
        if (0 == solutionCounter) {
            log.info(name + " - No  solutions.   " + elapsed(start));
            return false;
        }
        else {
            log.info(name + " - " + new DecimalFormat(DF3).format(solutionCounter) + " solution(s). " 
            							+ elapsed(start));
            return true;
        }
    }
}
