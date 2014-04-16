package de.dbo.samples.gui.swing.treetable.records.api;

import java.util.List;

/**
 * Node of the record-tree.
 * It is used to plug-in records into the Tree-Table
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Node extends Comparable<Node>{
	
	/**
	 * tree-name of the tree-root.
	 * Typically the tree-GUI doesn't show it
	 */
	public static final String ROOT = "ROOT";

	/**
	 * name to appear in the tree-table as node-name.
	 * This name is non-null and non-empty
	 * @return tree-name of this node
	 */
	public abstract String treename();
	
	/**
	 * children of this node.
	 * The value can be empty list but never null
	 * @return
	 */
	public abstract List<Node> getChildren();
	
	public abstract Long getSequence();
	
	public abstract void setSequence(Long o);
	
	
	/**
	 * Node contents.
	 * Attributes of the contents appear as values in the table-cells.
	 * Node can have no contents. In this case the contents is null
	 * @return
	 */
	public abstract Object getContents();
	
	public abstract void setContents(Object o);
	
	/**
	 * pretty-print of this node.
	 * 
	 * @return readable string representing this node
	 */
	public abstract StringBuilder print();
	
}