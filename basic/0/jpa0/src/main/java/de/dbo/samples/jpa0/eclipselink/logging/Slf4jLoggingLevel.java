package de.dbo.samples.jpa0.eclipselink.logging;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.persistence.logging.SessionLog;

/**
 * SLF4J log levels and their mappings
 */

enum Slf4jLoggingLevel {
    TRACE, DEBUG, INFO, WARN, ERROR, OFF;

    /* mapping of the eclipseLink log levels into the slf4j */
    private static Map<Integer, Slf4jLoggingLevel> MAP_LEVELS = new HashMap<Integer, Slf4jLoggingLevel>();
    static {
	MAP_LEVELS.put(SessionLog.ALL, TRACE);
	MAP_LEVELS.put(SessionLog.FINEST, TRACE);
	MAP_LEVELS.put(SessionLog.FINER, TRACE);
	MAP_LEVELS.put(SessionLog.FINE, DEBUG);
	MAP_LEVELS.put(SessionLog.CONFIG, INFO);
	MAP_LEVELS.put(SessionLog.INFO, INFO);
	MAP_LEVELS.put(SessionLog.WARNING, WARN);
	MAP_LEVELS.put(SessionLog.SEVERE, ERROR);
    }
    
    private Slf4jLoggingLevel() {
	
    }
    
    /*
     * Return the corresponding Slf4j Level for a given EclipseLink level.
     */
    static Slf4jLoggingLevel mapEclipseLinkLogLevelToSlf4j(final Integer eclipseLinkLoggingLevel) {
	final Slf4jLoggingLevel logLevel = MAP_LEVELS.get(eclipseLinkLoggingLevel);
	if (logLevel == null) {
	    return OFF;
	} else {
	    return logLevel;
	}
    }
}
