package de.dbo.samples.gui.swing.treetable.records.api;

import java.util.List;

public interface Node extends Comparable<Node>{
	
	/**
	 * tree-name of the tree-root
	 */
	public static final String ROOT = "ROOT";

	/**
	 * name to appear in the tree-table as node-name
	 * @return
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
	public abstract Object getObject();
	
	public abstract void setObject(Object o);
	
	/**
	 * pretty-print of this node.
	 * 
	 * @return readable string representing this node
	 */
	public abstract StringBuilder print();
	
}