package de.dbo.samples.gui.swing.treetable.records.api;


public interface Record extends Comparable<Record> {

	public abstract Path getPath();

	public abstract Long getTimestamp();

	public abstract Integer getSequence();

	public abstract void setSequence(Integer sequence);

	public abstract Object getContents();

	public abstract void setContents(Object contents);

}