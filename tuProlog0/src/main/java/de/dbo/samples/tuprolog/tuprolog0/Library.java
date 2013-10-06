package de.dbo.samples.tuprolog.tuprolog0;

import de.dbo.samples.tuprolog.tuprolog0.objects.Counter;
import de.dbo.samples.tuprolog.tuprolog0.objects.Timer;
import alice.tuprolog.Term;

public final class Library extends alice.tuprolog.Library {
	private static final long serialVersionUID = 5009215062246299601L;
	private final Logger log;
	
	public Library( final String logger ){
		log = new Logger( logger );
	}
	
	@Override
	public String getName() {
		return "de.dbo.Library";
	}
	
	/**
	 * predicate log/1
	 * e.g. info( info("something strange ...") )
	 * @param well-formed message-term of the form err|warn|info|debug (.....)
	 * @return true
	 */
	public boolean log_1( final Term messageTerm ){
		log.log( messageTerm );
		return true;
	}
	
	/**
     * library theory
     */
    public String getTheory() {
    	final StringBuilder sb = new StringBuilder();
    	
    	// Java objects
    	sb.append("timer(X) :- java_object('"   + Timer.class.getName()   +"' ,[] ,X).\n");
    	sb.append("counter(X) :- java_object('" + Counter.class.getName() +"' ,[] ,X).\n");
    	sb.append("init(Timer,Counter) :- timer(Timer), counter(Counter).\n");
    	 
    	// Prolog predicates
    	sb.append("size([],0).\n");
    	sb.append("size([_|T],Total) :- !, size(T,N), Total is N+1.");
    	return sb.toString();
    }
}
