package de.dbo.samples.gui.swing.treetable.api.records;

import static de.dbo.samples.gui.swing.treetable.api.records.RecordTimestampFormat.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Formatter for relative time-stamps
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class RecordRelativeTimestampTest {
	private static final Logger log = LoggerFactory.getLogger(RecordRelativeTimestampTest.class);
	
	public static final long SEC = 1000;
	public static final long MIN = 60 * SEC;
	public static final long HOUR = 60 * MIN;
	
	@Test
	public void show() {
		final long milliseconds = 352635;
	
		log.info("Cannonical format:");
		log.info(TIMESTAMP_ERROR.print(FORMAT_CANONICAL).toString());
		log.info(TIMESTAMP_NULL.print(FORMAT_CANONICAL).toString());
		log.info(newInstance(0).print(FORMAT_CANONICAL).toString());
		log.info(newInstance(milliseconds).print(FORMAT_CANONICAL).toString());
		log.info(newInstance(milliseconds-635).print(FORMAT_CANONICAL).toString());
		log.info(newInstance(milliseconds-635-52*SEC).print(FORMAT_CANONICAL).toString());
		log.info(newInstance(milliseconds-52*SEC).print(FORMAT_CANONICAL).toString());
		log.info(newInstance(milliseconds-5*MIN).print(FORMAT_CANONICAL).toString());
		log.info(newInstance(milliseconds-5*MIN-52*SEC).print(FORMAT_CANONICAL).toString());
		log.info(newInstance(milliseconds-5*MIN-52*SEC + 3*HOUR).print(FORMAT_CANONICAL).toString());
		
		log.info("Cannonical format minimized:");
		log.info(TIMESTAMP_ERROR.print(FORMAT_CANONICAL_MINIMIZED).toString());
		log.info(TIMESTAMP_NULL.print(FORMAT_CANONICAL_MINIMIZED).toString());
		log.info(newInstance(0).print(FORMAT_CANONICAL_MINIMIZED).toString());
		log.info(newInstance(milliseconds).print(FORMAT_CANONICAL_MINIMIZED).toString());
		log.info(newInstance(milliseconds-635).print(FORMAT_CANONICAL_MINIMIZED).toString());
		log.info(newInstance(milliseconds-635-52*SEC).print(FORMAT_CANONICAL_MINIMIZED).toString());
		log.info(newInstance(milliseconds-52*SEC).print(FORMAT_CANONICAL_MINIMIZED).toString());
		log.info(newInstance(milliseconds-5*MIN).print(FORMAT_CANONICAL_MINIMIZED).toString());
		log.info(newInstance(milliseconds-5*MIN-52*SEC).print(FORMAT_CANONICAL_MINIMIZED).toString());
		log.info(newInstance(milliseconds-5*MIN-52*SEC + 3*HOUR).print(FORMAT_CANONICAL_MINIMIZED).toString());

		log.info("Cannonical format no zeros:");
		log.info(TIMESTAMP_ERROR.print(FORMAT_CANONICAL_NO_ZERO).toString());
		log.info(TIMESTAMP_NULL.print(FORMAT_CANONICAL_NO_ZERO).toString());
		log.info(newInstance(0).print(FORMAT_CANONICAL_NO_ZERO).toString());
		log.info(newInstance(milliseconds).print(FORMAT_CANONICAL_NO_ZERO).toString());
		log.info(newInstance(milliseconds-635).print(FORMAT_CANONICAL_NO_ZERO).toString());
		log.info(newInstance(milliseconds-635-52*SEC).print(FORMAT_CANONICAL_NO_ZERO).toString());
		log.info(newInstance(milliseconds-52*SEC).print(FORMAT_CANONICAL_NO_ZERO).toString());
		log.info(newInstance(milliseconds-5*MIN).print(FORMAT_CANONICAL_NO_ZERO).toString());
		log.info(newInstance(milliseconds-5*MIN-52*SEC).print(FORMAT_CANONICAL_NO_ZERO).toString());
		log.info(newInstance(milliseconds-5*MIN-52*SEC + 3*HOUR).print(FORMAT_CANONICAL_NO_ZERO).toString());
		
		log.info("Cannonical format single:");
		log.info(TIMESTAMP_ERROR.print(FORMAT_COMPRESSED).toString());
		log.info(TIMESTAMP_NULL.print(FORMAT_COMPRESSED).toString());
		log.info(newInstance(0).print(FORMAT_COMPRESSED).toString());
		log.info(newInstance(milliseconds).print(FORMAT_COMPRESSED).toString());
		log.info(newInstance(milliseconds-635).print(FORMAT_COMPRESSED).toString());
		log.info(newInstance(milliseconds-635-52*SEC).print(FORMAT_COMPRESSED).toString());
		log.info(newInstance(milliseconds-52*SEC).print(FORMAT_COMPRESSED).toString());
		log.info(newInstance(milliseconds-5*MIN).print(FORMAT_COMPRESSED).toString());
		log.info(newInstance(milliseconds-5*MIN-52*SEC).print(FORMAT_COMPRESSED).toString());
		log.info(newInstance(milliseconds-5*MIN-52*SEC + 3*HOUR).print(FORMAT_COMPRESSED).toString());
	}

}
