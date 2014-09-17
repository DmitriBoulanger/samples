package de.dbo.samples.concurrent.semaphore;

import java.util.concurrent.Semaphore;

public final class ResourceWithSemaphore {
	
	private final Semaphore semaphore = new Semaphore(1);
	
	public final void enter() throws InterruptedException {
		semaphore.acquire();
	}
	
	public final void exit() throws InterruptedException {
		semaphore.release();
	}

}
