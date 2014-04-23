package de.dbo.samples.gui.swing.treetable.api.gui;

public interface TreetableColumns {
	
	public void arrangeColumnWidths(final Treetable treetable);
	
	public String[] getColumnNames();
	
	public Integer[] getColumnWidths();
	
	public Class<?>[] getColumnTypes();
	
	public void setColumnWidths(final Treetable treetable);
}
