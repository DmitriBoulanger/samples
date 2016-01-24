package de.dbo.samples.jpa0.eclipselink.logging;

import static de.dbo.tools.utils.print.Print.padRight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

final class Slf4Print {

    static final StringBuilder print (final Map<String, Logger> slf4jLoggers) {
	final StringBuilder sb = new StringBuilder();
	final List<String> namespaces = new ArrayList<String>(slf4jLoggers.keySet());
	Collections.sort(namespaces);
	for (final String namespace: namespaces) {
	    sb.append("\n\t - " + padRight(namespace,15) 
	    + padRight(""+print(slf4jLoggers.get(namespace)),4) );
	}
	return sb;
    }

    private static final StringBuilder print(final Logger slf4jLogger) {
	final StringBuilder sb = new StringBuilder();
	if (slf4jLogger.isTraceEnabled()) {
	    sb.append(Slf4jLoggingLevel.TRACE);

	} else if (slf4jLogger.isDebugEnabled()) {
	    sb.append(print(Slf4jLoggingLevel.DEBUG));
	}
	else if (slf4jLogger.isInfoEnabled()) {
	    sb.append(print(Slf4jLoggingLevel.INFO));

	}  else if (slf4jLogger.isWarnEnabled()) {
	    sb.append(print(Slf4jLoggingLevel.WARN));

	} else if (slf4jLogger.isErrorEnabled()) {
	    sb.append(print(Slf4jLoggingLevel.ERROR));	     
	} else {
	    sb.append(print(Slf4jLoggingLevel.OFF));
	}
	sb.append( slf4jLogger.getName() );
	return sb;
    }


    private static final String print(Slf4jLoggingLevel slf4jLoggingLevel) {
	return padRight(""+ slf4jLoggingLevel,6);
    }

}
