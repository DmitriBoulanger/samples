package de.dbo.samples.concurrent.semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Deadlock {
	private static final Logger log = LoggerFactory.getLogger(Deadlock.class);
	
	public static void main(String[] args) throws Exception {
		   final Semaphore s1 = new Semaphore(1);
		   final Semaphore s2 = new Semaphore(1);
		   final CountDownLatch countDownLatch = new CountDownLatch(2);

		   final Thread t = new Thread(new ResourceWithDoubleGrabber(s1, s2, countDownLatch));
		   
		   /*
		    * now reverse them ... here comes trouble!
		    */
		   final Thread t2 = new Thread(new ResourceWithDoubleGrabber(s2, s1, countDownLatch));

		   t.start();
		   t2.start();

		  countDownLatch.await();
		   log.info("We got lucky!");
		}

}
