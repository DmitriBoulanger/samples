package de.dbo.samples.gui.swing.treetable.origins.data;
 
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModelAbstraction;

public final class DataTreetableModel extends TreetableModelAbstraction {
	
    /**
     * 
     * @param root complete data-structure 
     */
    public DataTreetableModel(Object root) {
        super(new DataTreetableColumns()); 
        setRoot(root);
    }
    
    @Override
    public Object getChild(Object parent, int index) {
        return ((DataNode) parent).getChildren().get(index);
    }
 
    @Override
    public int getChildCount(Object parent) {
        return ((DataNode) parent).getChildren().size();
    }
 
    @Override
    public Object getValueAt(Object node, int column) {
    	final DataNode datanode = (DataNode) node;
        switch (column) {
        case 0:
            return datanode.getTreename();
        case 1:
            return datanode.getContents().getTimestamp();
        case 2:
            return datanode.getContents().getUUID();
        case 3:
            return datanode.getContents().getCapital();
        case 4:
            return ((DataNode) node).getContents();
            
        default:
            throw new RuntimeException(
            	"Incorrect column in getValueAt(Object node="+node.toString()+", int column="+column+")");
        }
        
    }
 
    @Override
    public boolean isCellEditable(Object node, int column) {
    	 switch (column) {
         case 0:
        	 return true; // Important to activate TreeExpandListener
         case 1:
        	 return false;
         case 2:
        	 return false;
         case 3:
        	 return true;	
         case 4:
        	 return true;
         
         default:
             throw new RuntimeException(
                 	"Incorrect column in isCellEditable(Object node="+node.toString()+", int column="+column+")");
         }
    }
 
    @Override
    public void setValueAt(Object value, Object node, int column) {
    	 log.trace("setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+") ...");
    	 final DataNode datanode = (DataNode) node;
    	 switch (column) {
         case 0:
         case 1:
        	 log.trace("setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+") rejected");
        	 break;
        	 
         case 2:
        	 datanode.getContents().setTimestamp( (Long) value);
         
         case 3:
        	 datanode.getContents().setUUID((String)value);
             break;
             
         case 4:
        	 datanode.setContents(value);
        	 break;
         
         default:
             throw new RuntimeException(
                 	"Incorrect column in setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+")");
 
         }
    }

	@Override
	public int getHierarchicalColumn() {
		// TODO Auto-generated method stub
		return 0;
	}
  
}