package de.dbo.samples.gui.swing.treetable.impl.ref;
 
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableException;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModelAbstraction;
import de.dbo.samples.gui.swing.treetable.api.records.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public final class TreetableModelImpl extends TreetableModelAbstraction {
	private static final Logger log = LoggerFactory.getLogger(TreetableModelImpl.class);
	
    // Names of the columns
    private static  String[] columnNames = { 
    	"Path", "Sequence", "Record" };
 
    // Types of the columns
    private static Class<?>[] columnTypes = { 
    	TreetableModel.class, Long.class, Object.class };
 
    /**
     * 
     * @param root complete data-structure 
     */
    public TreetableModelImpl() {
        super();
    }
    
    @Override
    public boolean isCellEditable(Object node, int column) {
    	 switch (column) {
         case 0:
        	 return true; // Important to activate the TreeExpandListener
         case 1:
        	 return false;
         case 2:
        	 return true;
         
         default:
             throw new TreetableException(
                 	"Incorrect column in isCellEditable(Object node="+node.toString()+", int column="+column+")");
         }
    }
    
    @Override
    public Object getValueAt(Object node, int column) {
        switch (column) {
        case 0:
            return ((Node) node).getTreename();
        case 1:
            return ((Node) node).getSequence();
        case 2:
            return ((Node) node).getContents();
            
        default:
            throw new TreetableException(
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
        	 ((Node) node).setContents(value);
        	 break;
         
         default:
             throw new TreetableException(
                 	"Incorrect column in setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+")");
         }
    }
    
    @Override
    public Object getChild(Object parent, int index) {
        return ((Node) parent).getChildren().get(index);
    }
 
    @Override
    public int getChildCount(Object parent) {
        return ((Node) parent).getChildren().size();
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