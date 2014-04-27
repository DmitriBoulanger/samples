package de.dbo.samples.gui.swing.treetable.api.guix;

import de.dbo.samples.gui.swing.treetable.api.TreetableUI;
import de.dbo.samples.gui.swing.treetable.api.records.Node;

import javax.swing.Icon;
import javax.swing.UIManager;

import org.jdesktop.swingx.icon.EmptyIcon;
import org.jdesktop.swingx.renderer.IconValue;

public class TreeIcon implements IconValue {
	private static final long serialVersionUID = 8218192615206835643L;

	private static final Icon LEAF_ICON = new EmptyIcon();
	
	private final TreetableUI treetableUI;
	
	public TreeIcon(final TreetableUI treetableUI) {
		this.treetableUI = treetableUI;
	}

	@Override
	public Icon getIcon(Object node) {
		if (null == node) {
			return null;
		}
		else if (((Node) node).getChildren().isEmpty()) {
			return LEAF_ICON;
		}
		
		// try to get special node-icon
		final Icon nodeIcon =  treetableUI.getIcon((Node) node);
		if (null!=nodeIcon) {
			return nodeIcon;
		} else {
			return null;
		}
	}
}
