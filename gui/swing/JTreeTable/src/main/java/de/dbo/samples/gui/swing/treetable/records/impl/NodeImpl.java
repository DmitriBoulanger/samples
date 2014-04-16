package de.dbo.samples.gui.swing.treetable.records.impl;

import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.NodeAbsraction;
import de.dbo.samples.gui.swing.treetable.records.api.NodeException;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

import java.util.List;

public class NodeImpl extends NodeAbsraction {
	
    private Record record;
    private Long sequence;
    
    public NodeImpl(String treename, Record record, List<Node> children) {
        super(treename, children);
        this.record = record;
        this.sequence = null!=record? record.getSequence() : null;
    }
    
	public Record getContents() {
        return record;
    }
    
	/*
	 * just for fun ...
	 * In real application, it should use contents of the record
	 * and split it in several table-cells
	 */
	public void setContents(Object o) {
		 if (o instanceof String) {
			 if (null!=record) {
				 ((RecordImpl) record).setSmthAsUUID((String)o);
			 } else {
				 this.record = new RecordImpl("smth");
				 ((RecordImpl) record).setSmthAsUUID((String)o);
			 }
				 
		 } else if (o instanceof Record) {
			 this.record = (Record) o;
		 } else {
			 throw new NodeException("Can't set object: " + o);
		 }
    }

	@Override
	public Long getSequence() {
		return sequence;
	}

	/**
	 * sequence is immutable.
	 * It should be initialized and then should be never changed
	 */
	@Override
	public void setSequence(Long sequence) {
		if (null==this.sequence) {
			this.sequence = sequence;
		}
	}
	
	/*
	 * for debugging ...
	 */
	@Override
	public StringBuilder print() {
		final StringBuilder sb = new StringBuilder(treename() + ": ");
		final StringBuilder sb2 = new StringBuilder();
		for (final Node node : getChildren()) {
			sb2.append(node.treename());
			sb2.append(" ");
		}
		sb.append("children=");
		sb.append("<");
		sb.append(sb2.toString().trim());
		sb.append(">");
		return sb;
	}
}
