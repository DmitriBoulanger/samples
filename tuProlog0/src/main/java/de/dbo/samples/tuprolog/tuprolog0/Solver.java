package de.dbo.samples.tuprolog.tuprolog0;

import static java.lang.System.currentTimeMillis;
import static de.dbo.samples.util0.Profiler.elapsed;

import java.io.IOException;

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

public final class Solver {
    private static final Logger log    = LoggerFactory.getLogger(Solver.class);

    private final Prolog        engine = new Prolog();

    public Solver() {
    }

    public Solver(final Library library) throws InvalidLibraryException {
        engine.loadLibrary(library);
        engine.setSpy(true);
    }

    public final String printLibraries() {
        final String[] libraries = engine.getCurrentLibraries();
        final StringBuilder sb = new StringBuilder();
        for (String name : libraries) {
            sb.append("\n\t - " + name);
        }
        return sb.toString().trim();
    }

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

    public boolean solve(Term goal, Int maxLength, Var output) throws Exception {
        log.debug("running " + goal.toString() + "...");
        final long start = currentTimeMillis();
        SolveInfo info = engine.solve(goal);
        int i = 0;

        while (info.isSuccess()) {
            i++;
            log.debug("solution " + i + ": "+ info.getVarValue("Path"));
            if (engine.hasOpenAlternatives()) {
                info = engine.solveNext();
            }
            else {
                break;
            }
            if (i > 100) {
                break;
            }
        }
        if (0 == i) {
            log.info("No solutions. " + elapsed(start));
            return false;
        }
        else {
            log.info(i + " solution(s). " + elapsed(start));
            return true;
        }
    }
}
