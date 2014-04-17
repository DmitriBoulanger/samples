package de.dbo.samples.gui.swing.treetable.records.api;

import de.dbo.samples.gui.swing.treetable.records.api.Node;

import java.util.ArrayList;
import java.util.List;


/**
 * Reference (basic) implementation of tree-nodes
 * 
 * @see Node
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public abstract class NodeAbsraction implements Node {
	 
    private String treename = null;
    private final List<Node> children = new ArrayList<Node>();
 
    public NodeAbsraction() {
    
    }
    
	/**
	 * name to appear in the Tree-Table
	 */
	@Override
	public final String getTreename() {
		if (null==treename || 0==treename.trim().length()) {
    		throw new NodeException("getTreename: Node must have non-null non-empty treename");
    	}
		return treename;
	}
	
	@Override
	public final void setTreename(final String treename) {
		if (null==treename || 0==treename.trim().length()) {
    		throw new NodeException("setTreename: Node must have non-null non-empty treename");
    	}
		this.treename = treename;
	}

	/**
	 * tree-name used in the Tree-Table
	 */
	@Override
	public final String toString() {
		return this.treename;
	}
    
    /**
     * nodes are compared using their sequence-attributes
     */
	@Override
	public final int compareTo(final Node another) {
		return getSequence().compareTo(another.getSequence());
	}
    
	/**
	 * ordered list of children
	 */
    @Override
    public final List<Node> getChildren() {
		return children;
	}
}
