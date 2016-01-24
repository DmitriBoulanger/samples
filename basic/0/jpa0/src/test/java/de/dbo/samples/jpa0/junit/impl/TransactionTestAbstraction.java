package de.dbo.samples.jpa0.junit.impl;

import static de.dbo.tools.utils.print.Profiler.elapsed;
import org.slf4j.Logger;

abstract class TransactionTestAbstraction {
    
    protected static long START = System.currentTimeMillis();
    
    protected static final void logTestTitle(final String name, final Logger log) {
	final StringBuilder sb = new StringBuilder();
	sb.append(LINE);
	sb.append("\n\t\t" + name);
	sb.append(LINE);
	log.info(sb.toString());
    }
    
    protected static final void logTestEnd(final Logger log) {
   	log.info(elapsed(START));
    }
    
    private static final String LINE = "\n"
	    + "==================================================================";

}
