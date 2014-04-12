package de.dbo.samples.gui.swing.treetable.api;
 
import java.awt.Dimension;
 







import javax.swing.JTable;
 
public class TreeTable extends JTable {
	private static final long serialVersionUID = -5203756529846423026L;
	
	private final TreeTableCellRenderer tree;
     
    public TreeTable(TreeTableModelAbstraction treeTableModel) {
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