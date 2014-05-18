package de.dbo.samples.gui.swing.treetable.api;

public interface TreetableColumns {
	
	/**
	 * sets-up column properties.
	 * This method is called while loading treetable
	 * 
	 * @param treetable
	 */
	public void setupColumns(final TreetableUIManager treetable);
	
	/**
	 * column types.
	 * Static constant
	 * 
	 * @return column types
	 */
	public String[] getColumnNames();
	
	/**
	 * cached column-widths 
	 * 
	 * @return column-widths
	 */
	public Integer[] getColumnWidths();
	
	/**
	 * column types.
	 * Static constant
	 * 
	 * @return column types
	 */
	public Class<?>[] getColumnTypes();
	
	/**
	 * saves current column-widths.
	 * Column-widths can be changed from the UI
	 * 
	 * @param treetableUIManaher treetable instance
	 */
	public void saveColumnWidths(final TreetableUIManager treetableUIManaher);
}
