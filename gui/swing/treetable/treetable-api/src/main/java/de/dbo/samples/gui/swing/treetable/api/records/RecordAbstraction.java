package de.dbo.samples.gui.swing.treetable.api.records;

import static de.dbo.samples.gui.swing.treetable.api.records.RecordTimestampFormat.TIMESTAMP_NULL;

/**
 * Reference (or basic) abstract implementation of a data-record.
 * Any record has at least path, tree-name and sequence.
 * Optionally a record has a contents-object.
 * Attributes of a contents are shown as values in the table-cells of a Tree-Table
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and
 *           only incidentally for computers to execute
 *
 */
public abstract class RecordAbstraction implements Record {

    protected Long                  sequence                = null;
    protected Object                contents                = null;
    protected Long                  timestamp               = null;
    protected RecordTimestampFormat recordRelativeTimestamp = null;

	private final Path path;

	protected RecordAbstraction(final Path path) {
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
	 * records are compared using their sequence-values.
	 * Therefore, any record must have sequence.
	 */
	@Override
	public int compareTo(final Record another) {
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

	/**
	 * sets sequence attribute if its current value is null.
	 * Sequence of a record is immutable
	 */
	@Override
	public void setSequence(final Long sequence) {
		if (null==this.sequence) {
			this.sequence = sequence;
		}
	}

	@Override
	public Long getTimestamp() {
		return timestamp;
	}

	@Override
	public void setTimestamp(final Long timestamp, final Long firstTimestamp) {
		this.timestamp = timestamp;
		this.recordRelativeTimestamp = RecordTimestampFormat.newInstance( timestamp - firstTimestamp );
	}

	@Override
	public RecordTimestampFormat relativeTimestamp() {
		return null != recordRelativeTimestamp? recordRelativeTimestamp : TIMESTAMP_NULL;
	}

	/**
	 * contents of this record
	 */
	@Override
	public Object getContents() {
		return contents;
	}

	@Override
	public void setContents(final Object contents) {
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
