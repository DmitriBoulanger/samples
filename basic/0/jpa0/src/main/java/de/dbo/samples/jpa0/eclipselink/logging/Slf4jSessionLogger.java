/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package de.dbo.samples.jpa0.eclipselink.logging;

import static de.dbo.samples.jpa0.eclipselink.logging.Slf4Print.print;
import static de.dbo.samples.jpa0.eclipselink.logging.Slf4jLoggingLevel.mapEclipseLinkLogLevelToSlf4j;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;

/**
 * <p>
 * This is a wrapper class for SLF4J. It is used when messages need to be logged
 * through SLF4J.
 * </p>
 * <p>
 * Use the following configuration for using SLF4J with EclipseLink
 * <code>eclipselink.logging.logger</code> and the value
 * <code>org.eclipse.persistence.logging.Slf4jSessionLogger</code>
 * </p>
 * <p>
 * Use the following categories from EclipseLink 
 * (eclipselink.logging.timestamp, eclipselink.logging.thread, 
 * eclipselink.logging.session, eclipselink.logging.connection 
 * y eclipselink.logging.parameters).
 * 
 * <p>
 * Logging categories available are:
 * <p>
 * <ul>
 * <li>org.eclipse.persistence.logging.default
 * <li>org.eclipse.persistence.logging.sql
 * <li>org.eclipse.persistence.logging.transaction
 * <li>org.eclipse.persistence.logging.event
 * <li>org.eclipse.persistence.logging.connection
 * <li>org.eclipse.persistence.logging.query
 * <li>org.eclipse.persistence.logging.cache
 * <li>org.eclipse.persistence.logging.propagation
 * <li>org.eclipse.persistence.logging.sequencing
 * <li>org.eclipse.persistence.logging.ejb
 * <li>org.eclipse.persistence.logging.ejb_or_metadata
 * <li>org.eclipse.persistence.logging.weaver
 * <li>org.eclipse.persistence.logging.properties
 * <li>org.eclipse.persistence.logging.server
 * </ul>
 * </p>
 * 
 * <p>
 * Mapping of Java Log Level to SLF4J Log Level:
 * </p>
 * <ul>
 * <li>ALL,FINER,FINEST -> TRACE
 * <li>FINE -> DEBUG
 * <li>CONFIG,INFO -> INFO
 * <li>WARNING -> WARN
 * <li>SEVERE -> ERROR
 * </ul>
 * </p>
 * <p>
 * 
 * @see https://github.com/PE-INTERNATIONAL/org.eclipse.persistence.logging.slf4j/blob/master/README.md
 */
public final class Slf4jSessionLogger extends AbstractSessionLog {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Slf4jSessionLogger.class);

    public static final String DEFAULT_CATEGORY  = "eclipseLink";
    public static final String ECLIPSELINK_NAMESPACE  = "org.eclipse.persistence.logging";
    public static final String DEFAULT_ECLIPSELINK_NAMESPACE  = ECLIPSELINK_NAMESPACE + "."  + DEFAULT_CATEGORY;

    /*
     * Map to provide the SLFF4J-Logger for a category (namespace).
     * This map is initialized in the default constructor
     */
    private static Map<String, org.slf4j.Logger> SLF4J_LOGGERS = null;

    public Slf4jSessionLogger() {
	super();
	if (null!=SLF4J_LOGGERS) {
	    return;
	} else {
	    SLF4J_LOGGERS = new HashMap<String, org.slf4j.Logger>();
	}

	/* Initialize SLF4J-loggers eagerly */
	for (final String eclipseLinkLoggerCategory : SessionLog.loggerCatagories) {
	    final String namespace =  ECLIPSELINK_NAMESPACE + "." + eclipseLinkLoggerCategory;
	    SLF4J_LOGGERS.put(eclipseLinkLoggerCategory, org.slf4j.LoggerFactory.getLogger(namespace));
	}

	/* Default SLF4J-logger */
	final String defaultLoggerCategory = DEFAULT_CATEGORY;
	final String defaultNamespace = DEFAULT_ECLIPSELINK_NAMESPACE;
	SLF4J_LOGGERS.put(defaultLoggerCategory, org.slf4j.LoggerFactory.getLogger(defaultNamespace));
	if (log.isTraceEnabled()) {
	    log.trace("SLF4Logger for eclipseLink namespaces: " + print(SLF4J_LOGGERS));
	}
    }
   

    /**
     * do the logging of the eclipseLink message
     */
    @Override
    public final void log(final SessionLogEntry eclipseLinkLogEntry) {
	final int eclipseLinkLogLevel  = eclipseLinkLogEntry.getLevel();
	if (eclipseLinkLogLevel==SessionLog.OFF) {
	    return;
	}
	final Slf4jLoggingLevel slf4jLogLevel = mapEclipseLinkLogLevelToSlf4j(eclipseLinkLogLevel);
	if (slf4jLogLevel==Slf4jLoggingLevel.OFF) {
	    return;
	}

	final String namespace = eclipseLinkLogEntry.getNameSpace();
	final org.slf4j.Logger logger = getSlf4jLogger(namespace);
	if (!shouldLog(eclipseLinkLogLevel, namespace)) {
	    return;
	}
	
	// log the eclipseLink message as a SLF4J message
	eclipseLinkLogEntry.setNameSpace("ccccccccccc");
	final StringBuilder message = new StringBuilder();
	switch (slf4jLogLevel) {
	case TRACE:
	    message.append(getSupplementDetailString(eclipseLinkLogEntry));
	    message.append(formatMessage(eclipseLinkLogEntry));
	    logger.trace(message.toString());
	    break;
	case DEBUG:
	    message.append(formatMessage(eclipseLinkLogEntry));
	    logger.debug(message.toString());
	    break;
	case INFO:
	    message.append(formatMessage(eclipseLinkLogEntry));
	    logger.info(message.toString());
	    break;
	case WARN:
	    message.append(formatMessage(eclipseLinkLogEntry));
	    logger.warn(message.toString());
	    break;
	case ERROR:
	    message.append(formatMessage(eclipseLinkLogEntry));
	    logger.error(message.toString());
	    break;
	case OFF:
	    break;

	default:
	    log.error("Should never happen: SLF4J logging level " + slf4jLogLevel + " is unknown!");
	}
    }
    
    @Override
    public final boolean shouldLog(final int eclipseLinkLoggingLevel, final String category) {
	final org.slf4j.Logger logger = getSlf4jLogger(category);
	final Slf4jLoggingLevel slf4jLogLevel = mapEclipseLinkLogLevelToSlf4j(eclipseLinkLoggingLevel);

	switch (slf4jLogLevel) {
	case TRACE:
	    return logger.isTraceEnabled();
	case DEBUG:
	    return logger.isDebugEnabled();
	case INFO:
	    return logger.isInfoEnabled();
	case WARN:
	    return logger.isWarnEnabled();
	case ERROR:
	    return logger.isErrorEnabled();

	default:
	    if (log.isTraceEnabled()) {
		log.trace("No logging for categpry ["+category+"]");
	    }
	    return false;
	}
    }

    @Override
    public final boolean shouldLog(int eclipseLinkLoggingLevel) {
	return shouldLog(eclipseLinkLoggingLevel, DEFAULT_CATEGORY);
    }

    /**
     * Return true if SQL logging should log visible bind parameters. 
     * If the shouldDisplayData is not set, return false.
     */
    @Override
    public boolean shouldDisplayData() {
	if (super.shouldDisplayData != null) {
	    return super.shouldDisplayData.booleanValue();
	} else {
	    return false;
	}
    }

    /*
     * Return the Logger for the given category
     */
    private org.slf4j.Logger getSlf4jLogger(final String namespace) {
	if (!nn(namespace) || !SLF4J_LOGGERS.containsKey(namespace)) {
	    return SLF4J_LOGGERS.get(DEFAULT_CATEGORY);
	} else {
	    return SLF4J_LOGGERS.get(namespace);
	}
    }

    //
    // HELPERS
    // 

    private static boolean nn(final String string) {
	return string != null && 0!=string.length();
    }
}