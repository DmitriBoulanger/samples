package de.dbo.samples.gui.swing.treetable.api.records;

import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RecordProviderAbstraction implements RecordProvider {
	protected static final Logger log = LoggerFactory.getLogger(RecordProvider.class);
	
	private String previousTransaction;
	protected String transaction;
	protected List<Record> records; 
	
	@Override
	public final Integer recordCounter() {
		if (null==records) {
			return null;
		}
		return records.size();
	}
	
	@Override
	public void clear() {
		transaction = null;
		previousTransaction = null;
		records = null;
		counter();
	}
	
	@Override
	public String getTransaction() {
		return transaction;
	}

	/**
	 * sets transaction ID.
	 * If it is a new one, the method transactionChanged() is called
	 * 
	 * @param transaction new (possibly null) transaction ID
	 * @see #transactionChanged()
	 */
	@Override
	public void setTransaction(final String transaction) {
		this.previousTransaction = this.transaction;
		this.transaction = nnv(transaction);
		final boolean diff = diff(this.previousTransaction, this.transaction);
		if (diff) {
			transactionChanged();
			log.debug("transaction: " + this.transaction + " changed=" + diff);
		}
	}

	/**
	 * retrieves all records for the transaction
	 */
	@Override
	public final List<Record> transactionRecords() {
		if (!nn(transaction)) {
			return null;
		}
		records = getRecords();
		counter();
		return records;
	}
	
	/**
	 * new records for the transaction
	 */
	@Override
	public final List<Record> transactionRecordsUpdate() {
		if (!nn(transaction)) {
			return null;
		}
		if (null==records) {
			return transactionRecords();
		} 
		
		int cnt = 0;
		final List<Record> recordsUpdate = getRecords();
		if (null!=recordsUpdate) {
			for (final Record record:recordsUpdate) {
				if (records.contains(record)) {
					continue;
				}
				records.add(record);
				cnt++;
			}
		} 
		log.debug("records update: " + cnt);
		counter();
		return records;
	}
	
	/**
	 * records for the transaction
	 * 
	 * @return null if no transaction is available.
	 * 			Otherwise possibly empty record-list 
	 */
	protected abstract List<Record> getRecords();
	
	protected final void loadRecordsInWorkerThread(final List<Record> ret) {
		final CountDownLatch countDown = new CountDownLatch(1);
		final Thread workerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				final List<Record> records = getRecords();
				if (null!=records && !records.isEmpty()) {
					ret.addAll(records);
				}
				countDown.countDown();
			}
		});
		workerThread.start();
		try {
			countDown.await();
		} catch (InterruptedException e) {
			throw new RecordProviderException("Can't wait for worker-thread: ", e);
		}
	}
		 
	
	protected final void counter() {
		log.debug("records: " + (null!=records? records.size() : "NULL"));
	} 
	
	// HELPERS
	
	protected static final boolean nn(final String x) {
		return null!=x && 0!=x.trim().length();
	}
	
	protected static final String nnv(final String x) {
		if (nn(x)) {
			return x.trim();
		}
		return null;
	}
	
	private static final boolean diff(final String x, final String x2) {
		if (null==x && null==x2) {
			return false;
		}
		if (null!=x) {
			return ! x.equals(x2);
		}
		else {
			return ! x2.equals(x);
		}
	}
}
