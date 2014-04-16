package de.dbo.samples.gui.swing.treetable.records.impl;

import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.NodeAbsraction;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeImpl extends NodeAbsraction {
	private static final Logger log = LoggerFactory.getLogger(NodeImpl.class);
	
    private Record record;
    private Long sequence;
   
    public NodeImpl(String treename, Record record, List<Node> children) {
        super(treename, children);
        this.record = record;
        this.sequence = null!=record? record.getSequence() : null;
        log.debug("created. Tree-name: " + treename);
    }
    
	public Object getObject() {
        return record;
    }
    
	public void setObject(Object o) {
    	 this.record = (Record) o;
    }

	@Override
	public Long getSequence() {
		return sequence;
	}

	@Override
	public void setSequence(Long sequence) {
		if (null==this.sequence) {
			this.sequence = sequence;
		}
	}
	
	@Override
	public StringBuilder print() {
		final StringBuilder sb = new StringBuilder(treename() + ": ");
		final StringBuilder sb2 = new StringBuilder();
		for (final Node node : getChildren()) {
			sb2.append(node.treename() + " ");
		}
		sb.append("children=");
		sb.append("<");
		sb.append(sb2.toString().trim());
		sb.append(">");
		return sb;
	}
	 

 
}
