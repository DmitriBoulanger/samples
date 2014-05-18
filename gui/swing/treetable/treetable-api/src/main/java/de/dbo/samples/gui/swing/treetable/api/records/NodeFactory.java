package de.dbo.samples.gui.swing.treetable.api.records;


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
public interface NodeFactory  {
	
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
	
}
