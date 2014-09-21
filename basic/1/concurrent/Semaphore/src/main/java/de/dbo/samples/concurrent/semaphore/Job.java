package de.dbo.samples.concurrent.semaphore;

import java.text.DecimalFormat;
import java.util.concurrent.CountDownLatch;

import org.slf4j.*;

public final class Job implements Runnable{
	private static final Logger log = LoggerFactory.getLogger(Job.class);
	
	/* protected resource (critical section) */
	private final ResourceWithSemaphore resourceWithSemaphore;
	
	/* count-down for logging/debugging */
	private final CountDownLatch countDownLatch;
	
	
	private final int name;
	private final  String jobname;
	
	public Job(ResourceWithSemaphore resourceWithSemaphore, final int name, final CountDownLatch countDownLatch) {
		this.resourceWithSemaphore = resourceWithSemaphore;
		this.countDownLatch = countDownLatch;
		this.name = name;
		this.jobname = "Job" + new DecimalFormat("000").format(name);
	}

	@Override
	public final void run() {
		try {
			doIt(ms(name),"1");
			try {
				resourceWithSemaphore.enter();
				doItWithResource(ms(name));
			} catch (Throwable e) {
				log.error(jobname + " failure: ",e);
			} finally {
				resourceWithSemaphore.exit();
			}
			doIt(ms(name),"2");
		} finally {
			countDownLatch.countDown();
		}
	}
	
	private final void doItWithResource(final long ms) {
		try {
			log.info(jobname + " running # with resource");
			Thread.sleep(ms);
			log.info(jobname + " running # with resource done");
		} catch (InterruptedException e) {
			log.error(jobname + " running # with resource failure: ",e);
		}
		
		/*
		 * Test-error
		 */
		if (8==name || 0==name%11 || 0==name%19) {
			throw new RuntimeException(jobname + " running # with resource test-failure");
		}
	}
	
	private final void doIt(final long ms, final String comment) {
		try {
			log.info(jobname + " running " + comment);
			Thread.sleep(ms);
			log.info(jobname + " running " + comment + " done");
		} catch (InterruptedException e) {
			log.error(jobname + " running " + comment + " failure: ",e);
		}
	}
	
	// HELPERS
	
	/**
	 * covert name into milliseconds pause
	 */
	private final long ms(final int i) {
		if (0==i%2) {
			return 50;
		} else if (0==i%3) {
			return 60;
		} else if (0==i%5) {
			return 70;
		} else if (0==i%7) {
			return 900;
		} else {
			return 100;
		}
	}
}
