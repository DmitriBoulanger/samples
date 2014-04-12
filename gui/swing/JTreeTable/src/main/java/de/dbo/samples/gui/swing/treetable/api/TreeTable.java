package de.dbo.samples.gui.swing.treetable.api;
 
import java.awt.Dimension;

import javax.swing.JTable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * TreeTable as extension of the JTable.
 * The JTree-extension is implemented as JTree-based table-cell renderer
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class TreeTable extends JTable {
	private static final long serialVersionUID = -5203756529846423026L;
	private static final Logger log = LoggerFactory.getLogger(TreeTable.class);
	
	private final TreeTableCellRenderer tree;
     
	/**
	 * @param treeTableModel tree-table data-model 
	 */
    public TreeTable(TreeTableModel treeTableModel) {
        super();
 
        // JTree-extension
        tree = new TreeTableCellRenderer(this, treeTableModel);
         
        // Model
        super.setModel(new TreeTableModelAdapter(treeTableModel, tree));
         
        // Simultaneous selections for JTable and JTree
        final TreeTableSelectionModel selectionModel = new TreeTableSelectionModel();
        tree.setSelectionModel(selectionModel); // For the tree
        setSelectionModel(selectionModel.getListSelectionModel()); //For the table
 
        // Renderer for JTree
        setDefaultRenderer(TreeTableModel.class, tree);
        
        // Editor for the TreeTable
        setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor(tree, this));
         
        // No grid show
        setShowGrid(false);
 
        // No margins between cells
        setIntercellSpacing(new Dimension(0, 0));
 
        log.debug("created");
    }
}