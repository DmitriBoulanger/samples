package de.dbo.samples.gui.swing.treetable.api;

public interface TreetableColumns {
	
	public void arrangeColumnWidths(final TreetableUIManager treetable);
	
	public String[] getColumnNames();
	
	public Integer[] getColumnWidths();
	
	public Class<?>[] getColumnTypes();
	
	public void setColumnWidths(final TreetableUIManager treetableUIManaher);
}
