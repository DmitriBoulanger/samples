package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.NodeAbsraction;
import de.dbo.samples.gui.swing.treetable.api.records.NodeException;
import de.dbo.samples.gui.swing.treetable.api.records.Record;

public class NodeImpl extends NodeAbsraction {
	
    private Record record;
    private Long sequence;
    
    public NodeImpl() {
        super();
    }
    
    @Override
    public void init(final String treename, final Object o) {
    	if (o==null) {
    		record = null;
    	}
    	else if (!(o instanceof Record)) {
    		throw new NodeException("init(final String treename="+treename
    				+", final Object o="+o+"): Object has to be a Record");
    		
    	} else {
    		this.record = (Record) o;
    	}
		setTreename(treename);
		
		if (null!=record) {
			sequence = this.record.getSequence();
		}
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
		if (null==o) {
			this.record = null;
		} else if (o instanceof String) {
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
		final StringBuilder sb = new StringBuilder(getTreename() + ": ");
		final StringBuilder sb2 = new StringBuilder();
		for (final Node node : getChildren()) {
			sb2.append(node.getTreename());
			sb2.append(" ");
		}
		sb.append("children=");
		sb.append("<");
		sb.append(sb2.toString().trim());
		sb.append(">");
		return sb;
	}
}
