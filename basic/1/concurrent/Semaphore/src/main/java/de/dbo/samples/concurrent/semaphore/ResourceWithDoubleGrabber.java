package de.dbo.samples.concurrent.semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceWithDoubleGrabber implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(ResourceWithDoubleGrabber.class);
	
	   private Semaphore first;
	   private Semaphore second;
	   private final CountDownLatch countDownLatch;

	   public ResourceWithDoubleGrabber(final Semaphore s1, final Semaphore s2, final CountDownLatch countDownLatch) {
	       first = s1;
	       second = s2;
	       this.countDownLatch = countDownLatch;
	   }

	   public void run() {
		   final Thread t = Thread.currentThread();
	       try {
	           
	           first.acquire();
	           log.info("acquired " + first);

	           Thread.sleep(29); // to demonstrate deadlock

	           second.acquire();
	           log.info("acquired " + second);

	           second.release();
	           log.info("released " + second);

	           first.release();
	           log.info("released " + first);
	           
	       } catch (InterruptedException e) {
	           log.error("Failure running "+t+":",e);
	       } finally {
	    	   countDownLatch.countDown();
	       }
	   }
}
