package de.dbo.samples.gui.swing.treetable;
 
import de.dbo.samples.gui.swing.treetable.records.api.Node;

import java.util.ArrayList;
import java.util.List;

public class DataNode extends Data implements Node {
	 
    final private List<DataNode> children = new ArrayList<DataNode>();
 
    public DataNode(final String name, final String capital,  List<DataNode> children) {
        super(name,capital);
        if (children != null) {
            this.children.addAll(children);
        } 
    }
    
    @Override
    public String toString() {
    	return getName(); // important for the tree-path
    }
 
    @Override
    public Data getObject() {
        return null;
    }
    
    @Override
    public void setObject(Object o) {
    	
    }
 
    public List<DataNode> getChildren() {
        return children;
    }

	@Override
	public List<Node> children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuilder print() {
		// TODO Auto-generated method stub
		return null;
	}
}