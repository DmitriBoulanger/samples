package de.dbo.samples.gui.swing.treetable.api.records;


/**
 * Record is an object attached to leaf-nodes in the Tree-Table
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Record extends Comparable<Record> {
	
	/**
	 * tree-name of the data.
	 * It appears as a name of the corresponding non-expandable node
	 * that contains record-data as values of the table-cells  
	 */
	public abstract String treename();
	
	/**
	 * path of the record
	 */
	public abstract Path getPath();

	/**
	 * tree-depth of this record.
	 * This method is only needed while building a tree
	 * 
	 * @param depth depth of a tree-node
	 * @return true only if the depth is the depth of the record data
	 */
	public abstract boolean isDataDepth(final int depth);

	/**
	 * typically timestamp
	 * @return sequence of this record
	 */
	public abstract Long getSequence();

	public abstract void setSequence(final Long sequence);
	
	public abstract Object getContents();
	
	public abstract void setContents(Object contents);

	public abstract void setTimestamp(final Long timestamp, final Long firstTimestamp);
	
	public abstract Long getTimestamp();
	
	public RecordTimestampFormat relativeTimestamp();

	/**
	 * pretty-print of this record.
	 * Mostly used for debugging/logging
	 * 
	 * @return printable string representing this record
	 */
	public abstract StringBuilder print();
}