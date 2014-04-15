package de.dbo.samples.gui.swing.treetable;
 
import de.dbo.samples.gui.swing.treetable.api.TreeTableModel;
import de.dbo.samples.gui.swing.treetable.api.TreeTableModelAbstraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public final class DataStructureTreeTableModel extends TreeTableModelAbstraction {
	private static final Logger log = LoggerFactory.getLogger(DataStructureTreeTableModel.class);
	
    // Names of the columns
    private static  String[] columnNames = { 
    	"PathImpl", "Timestamp", "UUID" ,"Tag/Component", "Object" };
 
    // Types of the columns
    private static Class<?>[] columnTypes = { 
    	TreeTableModel.class, Long.class,  String.class,  String.class, Object.class };
 
    /**
     * 
     * @param root complete data-structure 
     */
    public DataStructureTreeTableModel(Object root) {
        super(root);
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
    public int getColumnCount() {
        return columnNames.length;
    }
 
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
 
    @Override
    public Class<?> getColumnClass(int column) {
        return columnTypes[column];
    }
 
    @Override
    public Object getValueAt(Object node, int column) {
        switch (column) {
        case 0:
            return ((DataNode) node).getName();
        case 1:
            return ((DataNode) node).getTimestamp();
        case 2:
            return ((DataNode) node).getUUID();
        case 3:
            return ((DataNode) node).getCapital();
        case 4:
            return ((DataNode) node).getObject();
            
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
    	 log.debug("setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+") ...");
    	  
    	 switch (column) {
         case 0:
         case 1:
        	 log.debug("setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+") rejected");
        	 break;
        	 
         case 2:
        	 ((DataNode) node).setTimestamp( (Long) value);
         
         case 3:
             ((DataNode) node).setUUID((String)value);
             break;
             
         case 4:
        	 ((DataNode) node).setObject(value);
        	 break;
         
         default:
             throw new RuntimeException(
                 	"Incorrect column in setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+")");

       
             
         }
    }
 
}