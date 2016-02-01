package de.dbo.samples.gui.swing.treetable.impl.ref;
 
import de.dbo.samples.gui.swing.treetable.api.TreetableColumns;
import de.dbo.samples.gui.swing.treetable.api.TreetableException;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModelAbstraction;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordTimestampFormat;

public final class TreetableModelImpl extends TreetableModelAbstraction {
	
    /**
     * 
     */
    public TreetableModelImpl(final TreetableColumns treetableColumns) {
        super(treetableColumns);
    }
    
    @Override
    public boolean isCellEditable(Object node, int column) {
    	 switch (column) {
         case 0:
        	 return true; // Important to activate the TreeExpandListener
         case 1:
         case 2:
         case 3:
         case 4:
        	 return false; // sequence/timestamp-attributes are immutable
         case 5:
        	 return true;
        	 
         default:
             throw new TreetableException(
                "Incorrect column in isCellEditable(Object node="+node.toString()+", int column="+column+")");
         }
    }
    
    @Override
    public Object getValueAt(Object node, int column) {
    	log.trace("setValueAt(Object node="+node.toString()+", int column="+column+")");
        switch (column) {
        case 0:
            return ((Node) node).getTreename();
        case 1:
        	return ((Node) node).getSequence();
        case 2:
        case 3:
        case 4:
        	final Record record = (Record) ((Node) node).getContents();
        	final RecordTimestampFormat timestamp;
        	if (null!=record) {
				timestamp = record.relativeTimestamp();
			} else {
				timestamp =  RecordTimestampFormat.TIMESTAMP_NULL;
			}
        	return timestamp.get(column-1);
		case 5:
       	     return ((Node) node).getContents();
            
        default:
            throw new TreetableException(
            	"Incorrect column in getValueAt(Object node="+node.toString()+", int column="+column+")");
        } 
    }
   
    @Override
    public void setValueAt(Object value, Object node, int column) {
    	 switch (column) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
        	 log.error("setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+") rejected");
        	 break;
        	 
         case 5:
        	 log.trace("setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+")");
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
 
  
}