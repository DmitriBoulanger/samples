package de.dbo.samples.gui.swing.treetable.api.records;

import java.util.List;

public interface RecordProvider {
	
	/**
	 * total clean-up 
	 */
	public abstract void clear();
	
	public abstract Integer recordCounter();
	
	/**
	 * sets the transaction ID
	 * @param transaction transaction ID to be monitored
	 */
	public abstract void setTransaction(final String transaction);
	
	/**
	 * gets the current transaction ID
	 */
	public abstract String getTransaction();

	/**
	 * retrieves all transaction records
	 * @return record-list with all transaction records
	 */
	public abstract List<Record> transactionRecords();

	/**
	 * retrieves new transaction records
	 * @return update of the current transaction records
	 */
	public abstract List<Record> transactionRecordsUpdate();
	
	/**
	 * action if transaction ID changed
	 */
	public abstract void transactionChanged();

}