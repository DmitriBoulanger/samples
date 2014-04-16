package de.dbo.samples.gui.swing.treetable.records;

import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.NodeAbsraction;
import de.dbo.samples.gui.swing.treetable.records.api.Record;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeImpl extends NodeAbsraction {
	private static final Logger log = LoggerFactory.getLogger(NodeImpl.class);
	
    private Record record;
    
    private final Long sequence;
   
    public NodeImpl(final String treename, Record record, List<Node> children) {
        super(treename,children);
        this.record = record;
        this.sequence = null!=record? record.getSequence() : Long.MAX_VALUE;
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
		Long ret = this.sequence;
		for (Node child:children) {
			final Long childSequence = child.getSequence();
			if (ret > childSequence) {
				ret = childSequence;
			}
		}
		return ret;
	}

	@Override
	public void setSequence(Long sequence) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String title() {
		// TODO Auto-generated method stub
		return null;
	}
 
}
