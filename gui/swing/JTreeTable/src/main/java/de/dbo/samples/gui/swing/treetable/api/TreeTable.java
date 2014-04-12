package de.dbo.samples.gui.swing.treetable.api;
 
import java.awt.Dimension;
 







import javax.swing.JTable;
 
/**
 * TreeTable as extension of the JTable
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class TreeTable extends JTable {
	private static final long serialVersionUID = -5203756529846423026L;
	
	private final TreeTableCellRenderer tree;
     
	/**
	 * @param treeTableModel tree-table data-model 
	 */
    public TreeTable(TreeTableModel treeTableModel) {
        super();
 
        // JTree erstellen.
        tree = new TreeTableCellRenderer(this, treeTableModel);
         
        // Modell setzen.
        super.setModel(new TreeTableModelAdapter(treeTableModel, tree));
         
        // Gleichzeitiges Selektieren fuer Tree und Table.
        TreeTableSelectionModel selectionModel = new TreeTableSelectionModel();
        tree.setSelectionModel(selectionModel); //For the tree
        setSelectionModel(selectionModel.getListSelectionModel()); //For the table
 
         
        // Renderer fuer den Tree.
        setDefaultRenderer(TreeTableModel.class, tree);
        // Editor fuer die TreeTable
        setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor(tree, this));
         
        // Kein Grid anzeigen.
        setShowGrid(false);
 
        // Keine Abstaende.
        setIntercellSpacing(new Dimension(0, 0));
 
    }
}