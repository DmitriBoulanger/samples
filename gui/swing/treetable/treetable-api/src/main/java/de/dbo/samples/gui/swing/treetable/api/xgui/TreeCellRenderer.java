package de.dbo.samples.gui.swing.treetable.api.xgui;

import java.awt.Component;

import javax.swing.JTree;

import org.jdesktop.swingx.renderer.DefaultTreeRenderer;

/**
 * Rendering of the tree-node cell. The class determines tree-icons as well
 * 
 * @author Dmitri Boulanger, Hombach
 * 
 *         D. Knuth: Programs are meant to be read by humans and only
 *         incidentally for computers to execute
 * 
 */
public class TreeCellRenderer extends DefaultTreeRenderer {
	private static final long serialVersionUID = -8468286287964002501L;
	
   public TreeCellRenderer () {
	   super(new TreeIcon());
   }
   
   @Override
   public Component getTreeCellRendererComponent(JTree tree, Object value,
           boolean selected, boolean expanded, boolean leaf, int row,
           boolean hasFocus) {
       
       final Component comp = super.getTreeCellRendererComponent(tree, value,
               selected, expanded, leaf, row,hasFocus);
       
       return comp;
   }

}
