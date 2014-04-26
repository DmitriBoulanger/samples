package de.dbo.samples.gui.swing.treetable.api.xgui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;

public class TableCellRenderer extends JLabel implements  javax.swing.table.TableCellRenderer {
	private static final long serialVersionUID = -8002360634064245051L;

	public Component getTableCellRendererComponent(JTable table, Object o,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (o != null) {
			ImageIcon imageIcon = null;
			setIcon(imageIcon);
		} else {
			setIcon(null);
		}
		return this;
	}
}
