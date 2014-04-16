package de.dbo.samples.gui.swing.treetable.records.impl;
 
import de.dbo.samples.gui.swing.treetable.Window;

import java.awt.Dimension;

import javax.swing.SwingUtilities;
 
public class RecordWindow extends Window {
	private static final long serialVersionUID = -8057497879381935564L;

	public RecordWindow() {
        super( new RecordStructureTreeTableModel( RecordStructure.instance() )
        	,"Record Tree-Table - Reference implementation");
         
        treeTable.setRootVisible(true);
        treeTable.setIntercellSpacing(new Dimension(1, 1)); 
        treeTable.setColumnWidthNonresizable(1, 65);
        treeTable.setColumnWidthNonresizable(2, 600);
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(runnable( new RecordWindow() ));
    }
}