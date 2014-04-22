package de.dbo.samples.gui.swing.treetable.api.records;

import java.util.List;

public interface RecordProvider {
	
	/**
	 * action to perform total clean-up. All caches are cleaned 
	 */
	public abstract void clear();
	
	/**
	 * current record counter
	 * @return null if internal caches are cleaned.
	 * 		Otherwise number of records in cache
	 */
	public abstract Integer recordCounter();
	
	/**
	 * sets the transaction ID
	 * @param transaction transaction ID to be monitored
	 */
	public abstract void setTransaction(final String transaction);
	
	/**
	 * gets the current transaction ID
	 * @return null if transaction ID is not set
	 */
	public abstract String getTransaction();

	/**
	 * retrieves all transaction records
	 * @return null if transaction ID is not set.
	 * 		Otherwise possibly empty record-list with all transaction records
	 */
	public abstract List<Record> transactionRecords();

	/**
	 * retrieves new transaction records
	 * @return null if transaction ID is not set.
	 *     Otherwise  possibly empty record-list with update of 
	 *     the current transaction records
	 */
	public abstract List<Record> transactionRecordsUpdate();
	
	/**
	 * action to be performed if transaction ID changed.
	 */
	public abstract void transactionChanged();

}