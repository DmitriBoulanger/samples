package de.dbo.samples.gui.swing.treetable.api.gui;

import de.dbo.samples.gui.swing.treetable.api.gui.Treetable;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableColumns;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TreetableColumnsAbstraction implements TreetableColumns {
	protected static final Logger log = LoggerFactory.getLogger(TreetableColumns.class);
	
	protected Integer[] columnWidths; 
	
	public TreetableColumnsAbstraction (final String[] columnNames) {
		log.trace("created: " + Arrays.toString(columnNames));
	}

	@Override
	public final void setColumnWidths(final Treetable treetable) {
		if (null==treetable) {
			return;
		}
		for (int column=0; column<getColumnWidths().length; column++) {
			columnWidths[column] = treetable.getColunWidth(column);
		}
	}
	
//	TODO
//	@Override 
//	public final TreetableColumns clone() {
//		final TreetableColumns ret = this;
//		
//		return ret;
//	}
	
}
