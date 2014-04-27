package de.dbo.samples.gui.swing.treetable.origins.xexamples.treetable;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class PhotoRenderer extends JLabel implements TableCellRenderer {
	private static final long serialVersionUID = -8002360634064245051L;

	public Component getTableCellRendererComponent(JTable table, Object photo,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (photo != null) {
			ImageIcon imageIcon = null;
			setIcon(imageIcon);
		} else {
			setIcon(null);
		}
		return this;
	}
}
