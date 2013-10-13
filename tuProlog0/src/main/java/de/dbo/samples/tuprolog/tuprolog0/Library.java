package de.dbo.samples.tuprolog.tuprolog0;

import de.dbo.samples.tuprolog.tuprolog0.objects.Counter;
import de.dbo.samples.tuprolog.tuprolog0.objects.Timer;
import alice.tuprolog.Term;

/**
 * Solver-library containing special Prolog-predicates and Java-objects
 * It is used as an extension of an application rules
 * 
 * @author Dmitri Boulanger, Hombach
 *
 */
public final class Library extends alice.tuprolog.Library {
    private static final long serialVersionUID = 5009215062246299601L;
    
    /* internal Prolog-logger. It receives messages from the solver*/
    private final SolverLogger      log;

    /** 
     * 
     * @param loggerName name to be used for the internal library-logger 
     */
    public Library(final String loggerName) {
        log = new SolverLogger(loggerName);
    }

    /**
     * name of this library
     */
    @Override
    public String getName() {
        return "tuPrologSampleLibrary";
    }
    
    /**
     * library theory
     */
    public final String getTheory() {
        final StringBuilder sb = new StringBuilder();
        sb.append( getObjectInitializationSubTheory());
        sb.append( getSimplePerdicatesSubTheory());
        return sb.toString();
    }

    /**
     * Built-in predicate log/1
     * @param well-formed message-term of the form err|warn|info|debug (.....)
     *           e.g. info( info("something strange ...") )
     * @return true
     * @see SolverLogger
     */
    public final boolean log_1(final Term messageTerm) {
        log.log(messageTerm);
        return true;
    }

    /**
     * theory to initialize Java-objects
     * 
     * @return
     */
    public final StringBuilder getObjectInitializationSubTheory() {
    	final StringBuilder sb = new StringBuilder();

    	sb.append("init(Timer,Counter) :- timer(Timer), counter(Counter).\n");
        sb.append("timer(X) :- java_object('" + Timer.class.getName() + "' ,[] ,X).\n");
        sb.append("counter(X) :- java_object('" + Counter.class.getName() + "' ,[] ,X).\n");
      
        return sb;
    }
    
    /**
     *  theory with Prolog predicates
     *  
     * @return
     */
    public final StringBuilder getSimplePerdicatesSubTheory() {
    	final StringBuilder sb = new StringBuilder();

        sb.append("size([],0).\n");
        sb.append("size([_|T],Total) :- !, size(T,N), Total is N+1.");
        
        return sb;
    }
}
