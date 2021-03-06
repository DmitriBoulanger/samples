package de.dbo.samples.gui.swing.treetable.api;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TreetableColumnsAbstraction implements TreetableColumns {
	protected static final Logger log = LoggerFactory.getLogger(TreetableColumns.class);
	
	public TreetableColumnsAbstraction (final String[] columnNames) {
		log.trace("created: " + Arrays.toString(columnNames));
	}

	@Override
	public final void saveColumnWidths(final TreetableUIManager treetable) {
		if (null==treetable) {
			return;
		}
		final Integer[] columnWidths = getColumnWidths();
		for (int column=0; column<getColumnWidths().length; column++) {
			columnWidths[column] = treetable.getColunWidth(column);
		}
	}
}
