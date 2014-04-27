package de.dbo.samples.gui.swing.treetable.api;

public interface Treetable extends TreetableUIManager  {
	
	public abstract void expandAll();

	public abstract void collapseAll();

	public abstract Integer getColunWidth(int column);
	
	public abstract void setColumnWidthMin(int column, int width);
	
	public abstract void setColumnWidth(int column, int preferredWidth);

	public abstract void setColumnWidthNonresizable(int column, int width);

	
}