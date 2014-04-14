package de.dbo.samples.gui.swing.treetable.api;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeCellRenderer extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = -8468286287964002501L;
	
	private Icon nodeIcon = UIManager.getIcon("FileView.directoryIcon");
    private Icon leafIcon = UIManager.getIcon("Tree.leafIcon");
    private Color backgroundselection;
    
    public TreeCellRenderer() {
    	 
    }
    
    
    public Color getBackgroundselection() {
		return backgroundselection;
	}

	public void setBackgroundselection(Color backgroundselection) {
		this.backgroundselection = backgroundselection;
	}



	@Override
    public Component getTreeCellRendererComponent(final JTree tree,
            Object value, boolean selected, boolean expanded,
            boolean isLeaf, int row, boolean focused) {
    	
        final Component component = super.getTreeCellRendererComponent(tree, value,
                selected, expanded, isLeaf, row, focused);
        
        if (isLeaf) {
        	setIcon(leafIcon);
        } else {
        	setIcon(nodeIcon);
        }
        
        if (selected) {
        	this.setOpaque(false);
        	setBackgroundSelectionColor(backgroundselection);
        } else {
        	this.setOpaque(true);
        }
          
		return component;
    }
}
