package de.dbo.samples.gui.swing.treetable.records.impl.data;
 
import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.api.NodeAbsraction;

import java.util.List;

public class DataNode extends NodeAbsraction {
	 
    private final Data data;
    public DataNode(final String name, final String capital,  List<Node> children) {
        super(name,children);
        data = new Data(name,capital);
    }
    
    public Data getContents() {
        return data;
    }
    
    public void setContents(Object o) {
    	
    }
 
	@Override
	public StringBuilder print() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSequence() {
		// TODO Auto-generated method stub
		return 0L;
	}

	@Override
	public void setSequence(Long o) {
		// TODO Auto-generated method stub
	}

}