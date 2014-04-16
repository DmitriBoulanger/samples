package de.dbo.samples.gui.swing.treetable.records.impl;
 
import de.dbo.samples.gui.swing.treetable.api.TreeTableModel;
import de.dbo.samples.gui.swing.treetable.api.TreeTableModelAbstraction;
import de.dbo.samples.gui.swing.treetable.records.api.Node;
import de.dbo.samples.gui.swing.treetable.records.impl.NodeImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public final class RecordStructureTreeTableModel extends TreeTableModelAbstraction {
	private static final Logger log = LoggerFactory.getLogger(RecordStructureTreeTableModel.class);
	
    // Names of the columns
    private static  String[] columnNames = { 
    	"Path", "Sequence", "Record" };
 
    // Types of the columns
    private static Class<?>[] columnTypes = { 
    	TreeTableModel.class, Long.class, Object.class };
 
    /**
     * 
     * @param root complete data-structure 
     */
    public RecordStructureTreeTableModel(Object root) {
        super(root);
    }
    
    @Override
    public boolean isCellEditable(Object node, int column) {
    	 switch (column) {
         case 0:
        	 return true; // Important to activate TreeExpandListener
         case 1:
        	 return false;
         case 2:
        	 return true;
         
         default:
             throw new RuntimeException(
                 	"Incorrect column in isCellEditable(Object node="+node.toString()+", int column="+column+")");
         }
    }
    
    @Override
    public Object getValueAt(Object node, int column) {
        switch (column) {
        case 0:
            return ((Node) node).treename();
        case 1:
            return ((Node) node).getSequence();
        case 2:
            return ((NodeImpl) node).getContents();
            
        default:
            throw new RuntimeException(
            	"Incorrect column in getValueAt(Object node="+node.toString()+", int column="+column+")");
        } 
    }
    
    @Override
    public void setValueAt(Object value, Object node, int column) {
    	 log.debug("setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+") ...");
    	 switch (column) {
         case 0:
         case 1:
        	 log.error("setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+") rejected");
        	 break;
        	 
         case 2:
        	 ((NodeImpl) node).setContents(value);
        	 break;
         
         default:
             throw new RuntimeException(
                 	"Incorrect column in setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+")");
         }
    }
    
    @Override
    public Object getChild(Object parent, int index) {
        return ((NodeImpl) parent).getChildren().get(index);
    }
 
    @Override
    public int getChildCount(Object parent) {
        return ((NodeImpl) parent).getChildren().size();
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
}