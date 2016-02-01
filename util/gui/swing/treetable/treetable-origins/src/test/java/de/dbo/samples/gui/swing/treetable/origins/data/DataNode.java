package de.dbo.samples.gui.swing.treetable.origins.data;
 
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.NodeAbsraction;

import java.util.List;

public class DataNode extends NodeAbsraction {
	 
    private final Data data;
    
    public DataNode(final String treename, final String capital,  List<Node> children) {
        super();
        super.setTreename(treename);
        data = new Data(treename,capital);
        if (children != null) {
            super.getChildren().addAll(children);
        } 
    }
    
    public void init(final String treename, final Object o) {
    	// we do this in the constructor
	}
    
    public Data getContents() {
        return data;
    }
    
    @Override
    public void setContents(Object o) {
    	
    }
 
	@Override
	public StringBuilder print() {
		final StringBuilder sb =  new StringBuilder();
		return sb;
	}

	@Override
	public Long getSequence() {
		// TODO Auto-generated method stub
		return 0L;
	}

	@Override
	public void setSequence(final Long o) {
		// TODO Auto-generated method stub
	}

}