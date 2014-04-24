package de.dbo.samples.gui.swing.treetable.api.factory;

import de.dbo.samples.gui.swing.treetable.api.gui.TreetableColumns;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableUI;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;

/**
 * Treetable needs Nodes, Records and Models.
 * An tree-application should provide implementations for all these interfaces.  
 * A factory delivers instances
 *  
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Factory  {
	
	 /**
	  * tree-name of the root.
	  * 
	  * @return string to appear in the UI as a root-name
	  */
	 public String getRootName();
 
	 public void setRootName(String rootName);

	/**
	 * creates a new node possibly having a data-record
	 * 
	 * @param treename node-name to appear in tree
	 * @param record data of the node (can be null)
	 * 
	 * @return Node-instance
	 */
	public Node newNode(final String treename, final Record record);
	
	/**
	 * creates a new root-node possibly having no children and data-records
	  
	 * @return Node-instance
	 */
	public Node newRoot();
	
	/**
	 * creates a new model for a tree-table.
	 * 
	 * @param root root-node of the tree. The complete
	 *         tree is accessible using its children recursively
	 *         
	 * @return mode-instance
	 */
	public TreetableModel newTreeTableModel(final Node root);

	// configuration methods
	
    public Class<?> getNodeClass();

	public void setNodeClass(final Class<?> nodeClass);

	public Class<?> getTreetableModelClass();

	public void setTreetableModelClass(final Class<?> treetableModelClass);
	
	public RecordProvider getRecordProvider();
	
	public void setRecordProvider(final RecordProvider recordProvider);
	
	public TreetableColumns getTreetableColumns();
	
	public void setTreetableColumns(TreetableColumns treetableColumns);
	
	public void setTreetableUI(TreetableUI treetableUI);
	
	public TreetableUI getTreetableUI();
	
}
