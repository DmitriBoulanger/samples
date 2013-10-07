package de.dbo.samples.util0.junit;
 
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dbo.samples.util0.Profiler;

/**
 *
 * @author Dmitri Boulanger, Hombach
 *
 */
public class ProfilerTest {
	private static final Logger log = LoggerFactory.getLogger(ProfilerTest.class);
	
	@Test
	public void test_formatMs() {
		 log.debug( "         11 ms = " + Profiler.formatMs(11L));
		 log.debug( "        100 ms = " + Profiler.formatMs(100L));
		 log.debug( "       1001 ms = " + Profiler.formatMs(1001L));
		 log.debug( "      10002 ms = " + Profiler.formatMs(10002L));
		 log.debug( "     100003 ms = " + Profiler.formatMs(100003L));
		 log.debug( "    1000004 ms = " + Profiler.formatMs(1000004L));
		 log.debug( "   10000005 ms = " + Profiler.formatMs(10000035L));
		 log.debug( "  100000006 ms = " + Profiler.formatMs(100000336L));
		 log.debug( " 1000000007 ms = " + Profiler.formatMs(1000000007L));
		 log.debug( "10674000008 ms = " + Profiler.formatMs(10000000008L));
		 log.debug( " negative = " + Profiler.formatMs(-23));
	}
	
	@Test
	public void test_elapsed() {
		final long start = System.currentTimeMillis() - 10000;
		log.debug(Profiler.elapsed(start));
	}
	
	

}
