package de.dbo.samples.gui.swing.treetable.api.records;

import java.util.List;

/**
 * Data-record provider for Treetable.
 * Records are supposed to belong to a transaction.
 * Typically this provider follows the specified transaction
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface RecordProvider {
	
	/**
	 * action to perform total clean-up. All internal caches 
	 * and histories are cleaned. After this action this provider.
	 * 
	 * takes initial empty state 
	 */
	public abstract void clear();
	
	/**
	 * current record counter
	 * @return null if internal caches are cleaned.
	 * 		Otherwise number of records in the cache
	 */
	public abstract Integer recordCounter();
	
	/**
	 * sets the transaction ID
	 * @param transaction transaction ID to be monitored
	 */
	public abstract void setTransaction(final String transaction);
	
	/**
	 * gets the current transaction ID.
	 * 
	 * @return null if transaction ID is not set.
	 * 			Otherwise the current transaction ID
	 */
	public abstract String getTransaction();

	/**
	 * retrieves all available transaction records.
	 * The current records in the cache are lost
	 * 
	 * @return null if transaction ID is not set.
	 * 		Otherwise possibly empty record-list with all transaction records
	 */
	public abstract List<Record> transactionRecords();

	/**
	 * retrieves new transaction records.
	 * These records are appended to already existing records
	 * 
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