package de.dbo.samples.gui.swing.treetable.api.guix;

import de.dbo.samples.gui.swing.treetable.api.TreetableUI;
import de.dbo.samples.gui.swing.treetable.api.records.Node;

import javax.swing.Icon;
import javax.swing.UIManager;

import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.icon.EmptyIcon;

public class TreeIcon implements IconValue {
	private static final long serialVersionUID = 8218192615206835643L;

	private static final Icon NODE_ICON = UIManager.getIcon("FileView.directoryIcon");
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

		if (((Node) node).getChildren().isEmpty()) {
			return LEAF_ICON;
		}
		
		// node-icons
		final Icon nodeIcon =  treetableUI.getIcon((Node) node);
		if (null!=nodeIcon) {
			return nodeIcon;
		}
		
		return NODE_ICON;

	}
 

}
