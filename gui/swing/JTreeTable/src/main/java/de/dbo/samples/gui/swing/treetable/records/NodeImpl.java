package de.dbo.samples.gui.swing.treetable.records;

import de.dbo.samples.gui.swing.treetable.records.api.Node;

import java.util.ArrayList;
import java.util.List;

public class NodeImpl implements Node {
    private final String name;
    private Object o;
    
    private final List<Node> children = new ArrayList<Node>();
 
    public NodeImpl(final String name, Object o, List<Node> children) {
        this.name = name;
        this.o = o;
        if (children != null) {
            this.children.addAll(children);
        } 
    }
    
    public StringBuilder print() {
    	final StringBuilder sb = new StringBuilder(name+": ");
    	final StringBuilder sb2 = new StringBuilder();
    	for (final Node node:children) {
    		sb2.append(node.getName()+" ");
    	}
    	sb.append("<");
    	sb.append(sb2.toString().trim());
    	sb.append(">");
    	return sb;
    }
 
    @Override
	public String getName() {
        return name;
    }
 
    
    @Override
	public Object getObject() {
        return o;
    }
    
    @Override
	public void setObject(Object o) {
    	 this.o = o;
    }
 
    @Override
	public List<Node> children() {
        return children;
    }
 
    @Override
	public final String toString() {
        return name;
    }
}
