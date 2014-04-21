package de.dbo.samples.gui.swing.treetable.api.records;

import java.util.List;

public interface RecordProvider {
	
	public abstract void clear();
	
	public abstract void setTransaction(final String transaction);
	
	public abstract String getTransaction();

	public abstract List<Record> transactionRecords();

	public abstract List<Record> transactionRecordsUpdate();

}