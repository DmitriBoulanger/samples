package de.dbo.samples.gui.swing.treetable.api.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * Rendering of the tree-node cell. The class determines tree-icons as well
 * 
 * @author Dmitri Boulanger, Hombach
 * 
 *         D. Knuth: Programs are meant to be read by humans and only
 *         incidentally for computers to execute
 * 
 */
public class TreeCellRenderer extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = -8468286287964002501L;

	private static final Icon NODE_ICON = UIManager.getIcon("FileView.directoryIcon");
	private static final Icon LEAF_ICON = null;

	private Color selectionBackground;

	public TreeCellRenderer(JTree tree) {
		final BasicTreeUI ui = (BasicTreeUI) tree.getUI();
		ui.setCollapsedIcon(null);
		ui.setExpandedIcon(null);
		// ui.setRightChildIndent(3);
	}

	/**
	 * sets the selection background. It has to be the same as in the table
	 * 
	 * @param backgroundSelection
	 *            selection background in the table
	 */
	public void setBackgroundselection(Color backgroundselection) {
		this.selectionBackground = backgroundselection;
	}

	@Override
	public Component getTreeCellRendererComponent(final JTree tree,
			Object value, boolean selected, boolean expanded, boolean isLeaf,
			int row, boolean focused) {

		final Component component = super.getTreeCellRendererComponent(tree,
				value, selected, expanded, isLeaf, row, focused);

		if (isLeaf) {
			setIcon(LEAF_ICON);
		} else {
			setIcon(NODE_ICON);
		}

		if (selected) {
			this.setOpaque(false);
			setBackgroundSelectionColor(selectionBackground);
		} else {
			this.setOpaque(true);
		}

		return component;
	}
}
