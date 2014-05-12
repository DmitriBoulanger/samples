package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.TreetableColumnsAbstraction;
import de.dbo.samples.gui.swing.treetable.api.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.TreetableUIManager;

import java.awt.Dimension;

public final class TreetableColumnsImpl extends TreetableColumnsAbstraction {
	
    private static String[] COLUMN_NAMES = {"Path", "NR", "Timestamp", "Record"};
    private static Class<?>[] COLUMN_TYPES = { 
        TreetableModel.class, Long.class, String.class, Object.class};
    
    private static final int SMALL = 24;
    private final Integer[] columnWidths = new Integer[]{140,SMALL,90,300};
    
    public TTreetableColumnsImpl () {
        super(COLUMN_NAMES);
    }

    @Override
    public void setupColumns(final TreetableUIManager treetable) {
        treetable.setIntercellSpacing(new Dimension(1, 1));
        treetable.setColumnWidth(0, columnWidths[0]);
        treetable.setColumnWidthNonresizable(1, columnWidths[1]);
        treetable.setColumnWidthNonresizable(2, columnWidths[2]);
        treetable.setColumnWidthMin(3, columnWidths[3]);
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
