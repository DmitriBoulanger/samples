package de.dbo.samples.gui.swing.treetable;
 
import de.dbo.samples.gui.swing.treetable.api.TreeTableModel;
import de.dbo.samples.gui.swing.treetable.api.TreeTableModelAbstraction;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public final class DataStructureTreeTableModel extends TreeTableModelAbstraction {
	private static final Logger log = LoggerFactory.getLogger(DataStructureTreeTableModel.class);
	
    // Names of the columns
    private static  String[] columnNames = { 
    	"Ex-Tag path", "Component",  "UUID", "Datum", "Integer" };
 
    // Types of the columns
    private static Class<?>[] columnTypes = { 
    	TreeTableModel.class, String.class,  String.class,  Date.class, Integer.class };
 
    /**
     * 
     * @param rootNode complete data-structure
     */
    public DataStructureTreeTableModel(Object rootNode) {
        super(rootNode);
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
            return ((DataNode) node).getCapital();
        case 2:
            return ((DataNode) node).getUUID();
        case 3:
            return ((DataNode) node).getDeclared();
        case 4:
            return ((DataNode) node).getArea();
            
        default:
            throw new RuntimeException(
            	"Incorrect column in getValueAt(Object node="+node.toString()+", int column="+column+")");
        }
        
    }
 
    @Override
    public boolean isCellEditable(Object node, int column) {
        return true; // Important to activate TreeExpandListener
    }
 
    @Override
    public void setValueAt(Object aValue, Object node, int column) {
    	 switch (column) {
         case 0:
        	 break;
         case 1:
             break;
         case 2:
             ((DataNode) node).setUUID((String)aValue);
             break;
         case 3:
        	 throw new RuntimeException(
                 	"Cannot do setValueAt(Object node="+node.toString()+", int column="+column+")");

         case 4:
        	 ((DataNode) node).setArea((Integer) aValue);
        	 break;
         
         default:
             throw new RuntimeException(
                 	"Incorrect column in setValueAt(Object node="+node.toString()+", int column="+column+")");

             
         }
    }
 
}