package de.dbo.samples.gui.swing.treetable.records.api;


public interface Record extends Comparable<Record> {
	
	public abstract String name();
	
	public abstract boolean isData(final int depth);

	public abstract Path getPath();

	public abstract Long getTimestamp();

	public abstract Long getSequence();

	public abstract void setSequence(Long sequence);

	public abstract Object getContents();

	public abstract void setContents(Object contents);
	
	public abstract StringBuilder print();

}