package de.dbo.samples.gui.swing.treetable.api.records;

import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RecordProviderAbstraction implements RecordProvider {
	protected static final Logger log = LoggerFactory.getLogger(RecordProvider.class);
	
	protected String transaction;
	protected List<Record> records; 
	
	@Override
	public void clear() {
		transaction = null;
		records = null;
		counter();
	}
	
	@Override
	public String getTransaction() {
		return transaction;
	}

	@Override
	public void setTransaction(final String transaction) {
		this.transaction = transaction;
	}

	@Override
	public final List<Record> transactionRecords() {
		records = getRecords();
		counter();
		return records;
	}
	
	@Override
	public final List<Record> transactionRecordsUpdate() {
		if (null==records) {
			return transactionRecords();
		} 
		
		final List<Record> recordsUpdate = getRecords();
		if (null!=recordsUpdate) {
			records.addAll(recordsUpdate);
		} 
		
		counter();
		return records;
	}
	
	protected abstract List<Record> getRecords();
		 
	protected final void counter() {
		log.debug("records: " + (null!=records? records.size() : "NULL"));
	} 
	
	protected static final boolean nn(final String x) {
		return null!=x && 0!=x.trim().length();
	}
}
