package de.dbo.samples.gui.swing.treetable.impl.ref;
 
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableException;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModelAbstraction;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
 
public final class TreetableModelImpl extends TreetableModelAbstraction {
	private static final Logger log = LoggerFactory.getLogger(TreetableModelImpl.class);
	
    // Names of the columns
    private static  String[] columnNames = { 
    	"Path", "min", "sec", "ms", "Record" };
 
    // Types of the columns
    private static Class<?>[] columnTypes = { 
    	TreetableModel.class, Integer.class, Integer.class, Integer.class, Object.class };
    
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
         case 2:
         case 3:
        	 return false;
         case 4:
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
        case 2:
        case 3:
        	final String[] timestamp = getTimestamp( (Node) node) .split("-");
        	return integer(timestamp[column-1]);
        case 4:
       	     return ((Node) node).getContents();
            
        default:
            throw new TreetableException(
            	"Incorrect column in getValueAt(Object node="+node.toString()+", int column="+column+")");
        } 
    }
    
    private static final Integer integer(final String value) {
    	if (null==value || 0==value.trim().length()) {
    		return null;
    	}
    	return Integer.parseInt(value.trim());
    }
    
   
    private final String getTimestamp(final Node node) {
    	if (null==node) {
    		return TIMESTAMP_NULL;
    	}
    	final Long firstTimestamp = getFirstTimestamp();
    	if (null==firstTimestamp) {
    		return TIMESTAMP_NULL;
    	}
    	final Record record = (Record) node.getContents();
    	if (null==record) {
    		return TIMESTAMP_NULL;
    	}
    	return formatMs( record.getTimestamp() - firstTimestamp) ;
    }
    
    @Override
    public void setValueAt(Object value, Object node, int column) {
    	 log.debug("setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+") ...");
    	 switch (column) {
         case 0:
         case 1:
         case 2:
         case 3:
        	 log.error("setValueAt(Object value="+value+", Object node="+node.toString()+", int column="+column+") rejected");
        	 break;
        	 
         case 4:
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
    
    private static final String TIMESTAMP_SEPARATOR = "-";
    private static final String TIMESTAMP_NULL = " "
    		+ " " + TIMESTAMP_SEPARATOR
    		+ " " + TIMESTAMP_SEPARATOR
    		+ " " + TIMESTAMP_SEPARATOR
            + " " + TIMESTAMP_SEPARATOR;
    
    /**
     * Convert a millisecond duration to a string format
     * 
     * @param time A duration in milliseconds to convert to a string form
     * @return A string of the form "X h. Y min. Z sec. W ms. ".
     */
    private static String formatMs(final long time) {
        if (time < 0) {
            return TIMESTAMP_NULL;
        }
        
        long millliseconds = time;

        final long hours = MILLISECONDS.toHours(millliseconds);
        millliseconds -= HOURS.toMillis(hours);

        final long minutes = MILLISECONDS.toMinutes(millliseconds);
        millliseconds -= MINUTES.toMillis(minutes);

        final long seconds = MILLISECONDS.toSeconds(millliseconds);
        millliseconds -= SECONDS.toMillis(seconds);
      
        final StringBuilder sb = new StringBuilder();
        sb.append(0 < minutes? padRight(minutes,2) : "");
        sb.append(TIMESTAMP_SEPARATOR);
        sb.append(0 < seconds? padRight(seconds,2) : "");
        sb.append(TIMESTAMP_SEPARATOR);
        sb.append(padRight(millliseconds,3));

        return (sb.toString());
    }
    
    private static String padRight(final long s, int n) {
        return String.format("%1$-" + n + "s", s);
      }
}