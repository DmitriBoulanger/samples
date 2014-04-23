package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.records.RecordRelativeTimestamp;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordsRelativeTimestampTest {
	private static final Logger log = LoggerFactory.getLogger(RecordsRelativeTimestampTest.class);
	
	private static final long SEC = 1000;
	private static final long MIN = 60 * SEC;
	private static final long HOUR = 60 * MIN;
	
	@Test
	public void show() {
		final long milliseconds = 352635;
		
		log.info(RecordRelativeTimestamp.newInstance(milliseconds).print().toString());
		log.info(RecordRelativeTimestamp.newInstance(milliseconds-635).print().toString());
		log.info(RecordRelativeTimestamp.newInstance(milliseconds-635-52*SEC).print().toString());
		log.info(RecordRelativeTimestamp.newInstance(milliseconds-5*MIN).print().toString());
		
		log.info(RecordRelativeTimestamp.newInstance(milliseconds).print(true).toString());
		log.info(RecordRelativeTimestamp.newInstance(milliseconds-635).print(true).toString());
		log.info(RecordRelativeTimestamp.newInstance(milliseconds-635-52*SEC).print(true).toString());
		log.info(RecordRelativeTimestamp.newInstance(milliseconds-5*MIN).print(true).toString());
		log.info(RecordRelativeTimestamp.newInstance(milliseconds-5*MIN-52*SEC).print(true).toString());
		log.info(RecordRelativeTimestamp.newInstance(milliseconds-5*MIN-52*SEC + 3*HOUR).print(true).toString());
	}

}
