package de.dbo.samples.elk.client0;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application messages.
 * Messages are generated using SLF4J-Loggers with different names.
 * Actual logger-resource is Log4J that is configured in log4j.properties
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and
 *           only incidentally for computers to execute
 *
 */
public final class Messages {
	private static final Logger log = LoggerFactory.getLogger(Messages.class);

	public static final Logger LOGGER1 = LoggerFactory.getLogger("AnotherLogger");
	public static final Logger LOGGER2 = LoggerFactory.getLogger("AnotherLoggerX");

	public static final void main(final String[] args) throws Exception {
		while (true) {
			LOGGER1.warn("Info: Hallo!" + "\n\t - again hello"
					+ "\n\t - again hello" + "\n\t - again hello"
					+ "\n\t - again hello" + "\n\t - again hello"
					+ "\n\t - again hello" + "\n\t - again hello"

			);
			log.warn("Warning: Hallo!");
			LOGGER1.warn("SOS!", new Exception("test"));

			try {
				Class.forName("hashhha");
			} catch (final ClassNotFoundException e) {
				LOGGER2.error("no class " + UUID.randomUUID().toString(), e);
			}
			Thread.sleep(2000);
		}
	}
}
