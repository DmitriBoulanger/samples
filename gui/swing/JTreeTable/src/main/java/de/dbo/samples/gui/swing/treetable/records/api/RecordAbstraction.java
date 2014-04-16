package de.dbo.samples.gui.swing.treetable.records.api;

import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

/**
 * Reference (or basic) implementation of a data-record.
 * Any record has at least path, tree-name and sequence.
 * Optionally a record has a contents-object.
 * Attributes of a contents are shown as values in the table-cells
 * of a Tree-Table
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public abstract class RecordAbstraction implements Record {
	
	private final Path path;
	private Long sequence = null;
	private Object contents = null;
	
	public RecordAbstraction(final Path path) {
		if (null==path) {
			throw new RecordException("Record must have non-null path");
		}
		this.path = path;
	}
	
	/**
	 * Non-null path of this record
	 */
	@Override
	public Path getPath() {
		return path;
	}
	
	/**
	 * records are compared using their sequence-value.
	 * Therefore, any record must have sequence.
	 */
	@Override
	public int compareTo(Record another) {
		return sequence.compareTo(another.getSequence());
	}
	
	/**
	 * sequence-value of this record.
	 * Records are compared using this value.
	 * Therefore, this record must have the sequence-attribute.
	 * A typical implementation is the timestamp of the record
	 */
	@Override
	public Long getSequence() {
		return sequence;
	}

	@Override
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * contents of this record
	 */
	@Override
	public Object getContents() {
		return contents;
	}

	@Override
	public void setContents(Object contents) {
		this.contents = contents;
	}

	/**
	 * checks the specified depth.
	 * This method is only used while building the tree.
	 * 
	 * @return true only if the specified depth is the tree-depth of this record
	 */
	@Override
	public boolean isDataDepth(final int depth) {
		 return depth == getPath().depth();
	}


}
