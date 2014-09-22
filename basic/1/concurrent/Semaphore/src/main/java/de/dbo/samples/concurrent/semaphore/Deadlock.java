package de.dbo.samples.concurrent.semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Deadlock {
	private static final Logger log = LoggerFactory.getLogger(Deadlock.class);
	
	public static void main(String[] args) throws Exception {
		   final long duration = 0;
		   log.info("Job duration is about " + duration + " milliseconds");
		   final Semaphore s1 = new Semaphore(1);
		   final Semaphore s2 = new Semaphore(1);
		   final CountDownLatch countDownLatch = new CountDownLatch(2);

		   final Thread t = new Thread(new ResourceWithDoubleGrabber(duration, s1, s2, countDownLatch));
		   
		   /*
		    * now reverse them ... here comes the trouble!
		    */
		   final Thread t2 = new Thread(new ResourceWithDoubleGrabber(duration, s2, s1, countDownLatch));

		   log.info("Starting 2 jobs ...");
		   t.start();
		   t2.start();

		   final long wait = 3*duration + 100;
		   final boolean ok = countDownLatch.await(wait, TimeUnit.MILLISECONDS);
		   if (!ok) {
			   log.error("Deadlock occurs! Has been waiting "+ wait + " milliseconds ...");
			   log.info("Exit ...");
			   System.exit(-1);
			   return;
		   } else {
			  log.info("We got lucky!");
		   }
		}

}
