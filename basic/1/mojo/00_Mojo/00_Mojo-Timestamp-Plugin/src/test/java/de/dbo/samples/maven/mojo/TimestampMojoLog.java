package de.dbo.samples.maven.mojo;

import org.apache.maven.plugin.logging.SystemStreamLog;
 
public final class TimestampMojoLog extends SystemStreamLog {
	
	/* message accumulator */
	private final StringBuffer log;

	/**
	 * @param log message accumulator 
	 */
	public TimestampMojoLog(final StringBuffer log) {
		this.log = log;
	}

	@Override
	public void info(final CharSequence message) {
		log.append(message);
	}
}