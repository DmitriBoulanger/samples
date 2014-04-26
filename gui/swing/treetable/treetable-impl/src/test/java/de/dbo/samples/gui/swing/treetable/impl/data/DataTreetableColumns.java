package de.dbo.samples.gui.swing.treetable.impl.data;

import de.dbo.samples.gui.swing.treetable.api.TreetableColumnsAbstraction;
import de.dbo.samples.gui.swing.treetable.api.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.TreetableUIManager;

public final class DataTreetableColumns extends TreetableColumnsAbstraction {
	
    private static String[] COLUMN_NAMES = {
    	"Path", "Timestamp", "UUID" ,"Tag/Component", "Object"};
 
    private static Class<?>[] COLUMN_TYPES = {  
    	TreetableModel.class, Long.class,  String.class,  String.class, Object.class };
    
	private final Integer[] columnWidths =  new Integer[]{
			140, 100, 100, 100, 300};
	
	public DataTreetableColumns() {
		super(COLUMN_NAMES);
	}

	@Override
	public void arrangeColumnWidths(final TreetableUIManager treetable) {
		
	}

	@Override
	public String[] getColumnNames() {
		return COLUMN_NAMES;
	}

	@Override
	public Class<?>[] getColumnTypes() {
		return COLUMN_TYPES;
	}
	

	@Override
	public Integer[] getColumnWidths() {
		return columnWidths;
	}
}
