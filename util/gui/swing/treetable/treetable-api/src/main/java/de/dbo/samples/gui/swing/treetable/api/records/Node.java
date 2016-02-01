package de.dbo.samples.gui.swing.treetable.api.records;

import java.util.List;

/**
 * Node of the record-tree.
 * It is used to plug-in records into the Tree-Table.
 * Any node has its tree-name, possibly empty list of children 
 * and sequence attribute. Nodes are ordered using the sequence-attribute,
 * e.g. children of a node is an ordered list
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Node extends Comparable<Node>{
	
	/**
	 * initialization method
	 * 
	 * @param treename
	 * @param contents
	 */
	public void init(final String treename, final Object contents);

	/**
	 * name to appear in the tree-table as node-name.
	 * This name is non-null and non-empty
	 * @return tree-name of this node
	 */
	public abstract String getTreename();
	
	public abstract void setTreename(final String treename);
	
	/**
	 * children of this node.
	 * The value can be empty list but never null
	 * @return
	 */
	public abstract List<Node> getChildren();
	
	/**
	 * non-null sequence of this node.
	 * Nodes are ordered using the sequence-attribute
	 * @return
	 */
	public abstract Long getSequence();
	
	public abstract void setSequence(Long o);
	
	/**
	 * Node contents.
	 * Attributes of the contents appear as values in the table-cells.
	 * Node can have no contents. In this case the contents is null.
	 * Typically contents is a wrapper/interface of the node-record
	 * 
	 * @return contents of this node
	 */
	public abstract Object getContents();
	
	public abstract void setContents(Object o);
	
	/**
	 * pretty-print of this node.
	 * Used for logging/debugging
	 * 
	 * @return readable string representing this node
	 */
	public abstract StringBuilder print();
	
}