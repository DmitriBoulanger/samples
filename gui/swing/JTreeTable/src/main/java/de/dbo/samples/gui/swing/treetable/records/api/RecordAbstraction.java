package de.dbo.samples.gui.swing.treetable.records.api;

import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

public abstract class RecordAbstraction implements Record {
	
	private final Path path;
	
	private Object contents = null;
	
	private Long sequence = null;
	
	public RecordAbstraction() {
		this((Path) null);
	}
	
	public RecordAbstraction(final Path path) {
		this.path = path;
	}
	
	@Override
	public boolean isDataDepth(final int depth) {
		 return depth == getPath().depth();
	}

	@Override
	public Path getPath() {
		return path;
	}
	
	@Override
	public int compareTo(Record another) {
		return sequence.compareTo(another.getSequence());
	}
	
	@Override
	public Long getSequence() {
		return sequence;
	}

	@Override
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	@Override
	public Object getContents() {
		return contents;
	}

	@Override
	public void setContents(Object contents) {
		this.contents = contents;
	}
}
