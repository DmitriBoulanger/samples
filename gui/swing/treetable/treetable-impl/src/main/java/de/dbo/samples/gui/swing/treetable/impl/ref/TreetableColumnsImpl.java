package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.gui.Treetable;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableColumnsAbstraction;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;

import java.awt.Dimension;

public final class TreetableColumnsImpl extends TreetableColumnsAbstraction {
	
    private static String[] COLUMN_NAMES = {"Path", "NR", "MM", "SS", "MS", "Record"};
 
    private static Class<?>[] COLUMN_TYPES = { 
    	TreetableModel.class, Long.class, Integer.class, Integer.class ,  Integer.class, Object.class};
    
	private static final int SMALL = 24;
	private final Integer[] columnWidths =  new Integer[]{140, SMALL, SMALL, SMALL, SMALL, 300};
	
	public TreetableColumnsImpl() {
		super(COLUMN_NAMES);
	}

	@Override
	public void arrangeColumnWidths(final Treetable treetable) {
		if (null==treetable) {
			return;
		}
		treetable.setIntercellSpacing(new Dimension(0, 1));
		treetable.setColumnWidth(0, columnWidths[0]);
		treetable.setColumnWidthNonresizable(1, columnWidths[1]);
		treetable.setColumnWidthNonresizable(2, columnWidths[2]);
		treetable.setColumnWidthNonresizable(3, columnWidths[3]);
		treetable.setColumnWidthNonresizable(4, columnWidths[4]);
		treetable.setColumnWidthMin(5, columnWidths[5]);
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
