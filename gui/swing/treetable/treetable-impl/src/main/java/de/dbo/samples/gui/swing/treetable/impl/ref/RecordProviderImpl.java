package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProviderAbstraction;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProviderException;
import de.dbo.samples.gui.swing.treetable.impl.ref.data.Records;
import de.dbo.samples.gui.swing.treetable.impl.ref.data.Records1;
import de.dbo.samples.gui.swing.treetable.impl.ref.data.Records2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RecordProviderImpl extends RecordProviderAbstraction {

	final Records records = new Records();
	
	public RecordProviderImpl() {
		super();
	}
	
	@Override
	public void transactionChanged() {

	}

	protected final List<Record> getRecords() {
		if (!nn(transaction)) {
			return null;
		}
		final List<Record> ret = new ArrayList<Record>();
		final long start = System.currentTimeMillis();
		loadRecordsInWorkerThread(ret);
		log.info("elapsed " +(System.currentTimeMillis()-start)+ " ms. loading records");
		return ret;
	}

	
	
	
	private void loadRecordsInWorkerThread(final List<Record> ret) {
		final CountDownLatch countDown = new CountDownLatch(1);
		final Thread workerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				if ("0".equals(transaction)) {
					loadWithDelay(ret, records);
				} else if ("1".equals(transaction)) {
					loadWithDelay(ret, new Records1(records));
				} else if ("2".equals(transaction)) {
					loadWithDelay(ret, new Records2(records));
				} else if ("3".equals(transaction)) {
					loadWithDelay(ret, records);
				}
				countDown.countDown();
			}
		});
		workerThread.start();
		try {
			countDown.await();
		} catch (InterruptedException e) {
			throw new RecordProviderException("Can't wait for worker-thread: ",
					e);
		}
	}

	private static final void loadWithDelay(final List<Record> ret, final List<Record> records) {
		for (final Record record : records) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				log.warn("Can't sleep: " + e);
			}
			ret.add(record);
		}
	}


}
