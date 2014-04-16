package de.dbo.samples.gui.swing.treetable.records.api;

import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NodeAbsraction implements Node {
	 
    private final String name;
    
    protected final List<Node> children = new ArrayList<Node>();
 
    public NodeAbsraction(final String name, List<Node> children) {
        this.name = name;
        if (children != null) {
            this.children.addAll(children);
        } 
    }
    
    @Override
    public final List<Node> getChildren() {
		return children;
	}

    @Override
	public StringBuilder print() {
    	final StringBuilder sb = new StringBuilder(name+": ");
    	final StringBuilder sb2 = new StringBuilder();
    	for (final Node node:children) {
    		sb2.append(node.treeName()+" ");
    	}
    	sb.append("<");
    	sb.append(sb2.toString().trim());
    	sb.append(">");
    	return sb;
    }
 
    @Override
	public final String treeName() {
        return name;
    }
    
    @Override
	public final String toString() {
        return treeName();
    }
 
	@Override
	public final int compareTo(Node another) {
		return getSequence().compareTo(another.getSequence());
	}

}
