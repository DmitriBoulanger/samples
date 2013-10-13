package de.dbo.samples.util0;

import static de.dbo.samples.util0.Profiler.elapsed;
import static de.dbo.samples.util0.Profiler.formatMs;

import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;
import static java.lang.System.currentTimeMillis;

import org.junit.Test;
import org.slf4j.Logger;

/**
 *
 * @author Dmitri Boulanger, Hombach
 *
 */
public class ProfilerTest {
    private static final Logger log = getLogger(ProfilerTest.class);

    @Test
    public void test_formatMs() {
    	final long start = System.currentTimeMillis();
        log.debug("         11 ms = " + formatMs(11L));
        log.debug("        100 ms = " + formatMs(100L));
        log.debug("       1001 ms = " + formatMs(1001L));
        log.debug("      10002 ms = " + formatMs(10002L));
        log.debug("     100003 ms = " + formatMs(100003L));
        log.debug("    1000004 ms = " + formatMs(1000004L));
        log.debug("   10000005 ms = " + formatMs(10000035L));
        log.debug("  100000006 ms = " + formatMs(100000336L));
        log.debug(" 1000000007 ms = " + formatMs(1000000007L));
        log.debug("10674000008 ms = " + formatMs(10000000008L));
        log.debug(" negative = " + formatMs(-23));
        log.debug(elapsed(start));
        assertTrue("Incorret formatted value for " + 10000035L
        		, -1!=formatMs(10000035L).indexOf("02 h. 46 min. 40 sec. 035 ms."));
    }

    private static final long INTERVAL = 12733L;
    private static final String ELAPSED_INTERVAL_EXPECTED = "Elapsed: 12 sec. 73";
    @Test
    public void test_elapsed() {
        final String elapsed = elapsed(currentTimeMillis() - INTERVAL);
        log.debug(elapsed);
        assertTrue("Incorret elapse value for " + INTERVAL
        		, -1 != elapsed.indexOf(ELAPSED_INTERVAL_EXPECTED));
    }

}
