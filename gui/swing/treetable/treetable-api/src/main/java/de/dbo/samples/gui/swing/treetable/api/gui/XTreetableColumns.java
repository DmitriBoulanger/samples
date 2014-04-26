package de.dbo.samples.gui.swing.treetable.api.gui;

public interface XTreetableColumns {
	
	public void arrangeColumnWidths(final XTreetable treetable);
	
	public String[] getColumnNames();
	
	public Integer[] getColumnWidths();
	
	public Class<?>[] getColumnTypes();
	
	public void setColumnWidths(final XTreetable treetable);
}
