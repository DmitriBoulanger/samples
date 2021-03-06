package de.dbo.samples.gui.swing.treetable.impl.ref.x;
 
import de.dbo.samples.gui.swing.treetable.api.TreetableException;
import de.dbo.samples.gui.swing.treetable.api.guix.TreetableModelAbstraction;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordTimestampFormat;
import de.dbo.samples.gui.swing.treetable.api.TreetableColumns;

public final class TreetableModelImpl2 extends TreetableModelAbstraction {
	
	 public TreetableModelImpl2(final TreetableColumns treetableColumnsUI) {
	        super(treetableColumnsUI);
	 }
    
    @Override
    public boolean isCellEditable(Object node, int column) {
    	 switch (column) {
         case 0:
        	 return true; // Important to activate the TreeExpandListener
         case 1:
         case 2:
        	 return false; // sequence/timestamp-attributes are immutable
         case 3:
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
        	final Record record = (Record) ((Node) node).getContents();
        	final RecordTimestampFormat timestamp;
        	if (null!=record) {
				timestamp = record.relativeTimestamp();
			} else {
				timestamp =  RecordTimestampFormat.TIMESTAMP_NULL;
			}
        	return timestamp.print(RecordTimestampFormat.FORMAT_COMPRESSED);
        	
		case 3:
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
        	 log.error("setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+") rejected");
        	 break;
        	 
         case 3:
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
    
    @Override
    public int getIndexOfChild(Object parent, Object child) {
    	return ((Node) parent).getChildren().indexOf(child);
    }
 
}