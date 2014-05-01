package de.dbo.samples.gui.swing.treetable.impl.ref.testrecords;

import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProviderAbstraction;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordProviderImpl extends RecordProviderAbstraction {
	private static final Logger log = LoggerFactory.getLogger(RecordProvider.class);

	final Records records = new Records();
	
	public RecordProviderImpl() {
		log.trace("created");
	}
	
	@Override
	public void transactionChanged() {

	}

	@Override
	protected final List<Record> getRecords() {
		if (!nn(transaction)) {
			return null;
		}
		final List<Record> ret = new ArrayList<Record>();
		final long start = System.currentTimeMillis();
		doLoadRecordsInWorkerThread(ret);
		log.debug("elapsed " + (System.currentTimeMillis()-start)+ " ms. loading records");
		return ret;
	}
	
	/*
	 * TEST-IMPLEMENTATION
	 */

	private void doLoadRecordsInWorkerThread(final List<Record> ret) {
//		final CountDownLatch countDown = new CountDownLatch(1);
//		final Thread workerThread = new Thread(new Runnable() {
//			@Override
//			public void run() {
				if ("0".equals(transaction)) {
					loadWithDelay(ret, records);
				} else if ("1".equals(transaction)) {
					loadWithDelay(ret, new Records1(records));
				} else if ("2".equals(transaction)) {
					loadWithDelay(ret, new Records2(records));
				} else if ("3".equals(transaction)) {
					loadWithDelay(ret, records);
				}
//				countDown.countDown();
//			}
//		});
//		workerThread.start();
//		try {
//			countDown.await();
//		} catch (InterruptedException e) {
//			throw new RecordProviderException("Can't wait for worker-thread: ", e);
//		}
	}

	private static final void loadWithDelay(final List<Record> ret, final List<Record> records) {
		final Long firstTimestamp = System.currentTimeMillis();
		for (final Record record : records) {
			try {
				Thread.sleep(110);
			} catch (InterruptedException e) {
				log.warn("Can't sleep: " + e);
			}
			record.setTimestamp(System.currentTimeMillis(),firstTimestamp);
			ret.add(record);
		}
	}
	
}
