package de.dbo.samples.gui.swing.treetable.records.api;

import de.dbo.samples.gui.swing.treetable.records.api.Node;

import java.util.ArrayList;
import java.util.List;


public abstract class NodeAbsraction implements Node {
	 
    private final String treename;
    private final List<Node> children = new ArrayList<Node>();
 
    public NodeAbsraction(final String treename, List<Node> children) {
    	if (null==treename || 0==treename.trim().length()) {
    		throw new NodeException("Node must have non-null non-empty treename");
    	}
        this.treename = treename;
        if (children != null) {
            this.children.addAll(children);
        } 
    }
    
	@Override
	public final int compareTo(Node another) {
		return getSequence().compareTo(another.getSequence());
	}
    
    @Override
    public final List<Node> getChildren() {
		return children;
	}

    @Override
	public final String treename() {
        return treename;
    }
    
    @Override
	public final String toString() {
        return this.treename;
    }
 
}
