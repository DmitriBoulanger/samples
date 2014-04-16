package de.dbo.samples.gui.swing.treetable.data;
 
import de.dbo.samples.gui.swing.treetable.Window;

import java.awt.Dimension;

import javax.swing.SwingUtilities;

public class DataWindow extends Window {
	private static final long serialVersionUID = 3060095373306266571L;

	public DataWindow() {
        super( new DataStructureTreeTableModel( DataStructure.instance())
        	,"Tree Table Sample (adapted from http://www.hameister.org/JavaSwingTreeTable.html)");

        treeTable.setRootVisible(true);
        treeTable.setIntercellSpacing(new Dimension(1, 1)); 
        treeTable.setColumnWidthNonresizable(1, 120);
        treeTable.setColumnWidthNonresizable(2, 280);
    }
	
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(runnable(new DataWindow()));
    }
}