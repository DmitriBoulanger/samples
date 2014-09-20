package de.dbo.samples.concurrent.semaphore;

import static de.dbo.samples.util.print.Profiler.elapsed;
import static de.dbo.samples.util.print.Profiler.formatMs;
import java.util.concurrent.CountDownLatch;


import org.slf4j.*;

public class JobRunner {
	private static final Logger log = LoggerFactory.getLogger(JobRunner.class);
	
	public static void main(String[] args) {
//		new JobRunner().runThreads();
		new JobRunner().runJobs();
	}
	
	private  final int threadCnt = 100;
	private  final CountDownLatch countDownLatch = new CountDownLatch(threadCnt);
	private  final ResourceWithSemaphore resourceWithSemaphore = new ResourceWithSemaphore();
	private  final Job[] jobs = new Job[threadCnt];
	private  final Thread[] threads = new Thread[threadCnt];
	
	public JobRunner() {
		/*
		 * Create jobs and threads
		 */
		for(int i=0; i<threadCnt; i++) {
			jobs[i] = new Job(resourceWithSemaphore,i,countDownLatch);
		}
		for(int i=0; i<threadCnt; i++) {
			threads[i] = new Thread(jobs[i]);
		}
		
	}

	public  void runThreads() {
		final long start = System.currentTimeMillis();
		for (final Thread thread:threads) {
			thread.start();		
		}
		
		/* 
		 * Wait until all jobs are finished
		 */
		try {
			countDownLatch.await();
			logElapsed(start, threadCnt);
		} catch (Throwable e) {
			log.error("Failure waiting job-threads."
					+" Are there still " + countDownLatch.getCount() + " runing jobs?", e);
		}
	}
	
	public  void runJobs() {
		final long start = System.currentTimeMillis();
		for (final Job job:jobs) {
			job.run();		
		}
		logElapsed(start,threadCnt);
	}
	
	private  final void logElapsed(final long start, int threadCnt) {
		final long end = System.currentTimeMillis();
		final double duration = (double) end-start;
		final long average = (long) ( duration / (double) threadCnt );
		log.info("All " + threadCnt + " jobs finised. " + elapsed(start) + " Average: " + formatMs(average));
	}
}
