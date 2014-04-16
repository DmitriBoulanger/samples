package de.dbo.samples.gui.swing.treetable;
 
import de.dbo.samples.gui.swing.treetable.records.api.Node;

import java.util.ArrayList;
import java.util.List;

public class DataNode extends Data implements Node {
	 
    final private List<Node> children = new ArrayList<Node>();
 
    public DataNode(final String name, final String capital,  List<Node> children) {
        super(name,capital);
        if (children != null) {
            this.children.addAll(children);
        } 
    }
    
    @Override
    public String toString() {
    	return treeName(); // important for the tree-path
    }
 

    public Data getObject() {
        return null;
    }
    

    public void setObject(Object o) {
    	
    }
 
    public List<Node> getChildren() {
        return children;
    }

	
	@Override
	public StringBuilder print() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(Node another) {
		return getSequence().compareTo(another.getSequence());
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

	@Override
	public String title() {
		// TODO Auto-generated method stub
		return "title";
	}
}