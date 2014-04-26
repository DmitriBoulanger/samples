package de.dbo.samples.gui.swing.treetable.api.gui;
 
import javax.swing.event.EventListenerList;
//import javax.swing.event.TreeModelEvent;
//import javax.swing.event.TreeModelListener;
//import javax.swing.tree.TreePath;

import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * Default or basic tree-table model
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public abstract class XTreetableModelAbstraction 
	extends AbstractTreeTableModel  implements XTreetableModel{
	protected static final Logger log = LoggerFactory.getLogger(XTreetableModel.class);
	
    protected Object root;
   
    private final XTreetableColumns treetableColumns;
    
    protected XTreetableModelAbstraction(final XTreetableColumns treetableColumns) {
    	if (null==treetableColumns) {
    		throw new TreetableException("xtreetableColumns is null");
    	}
    	this.treetableColumns = treetableColumns;
    	log.trace("created");
    }
    
    @Override
    public final Object getRoot() {
        return root;
    }
    
    @Override
    public final void setRoot(Object root) {
        this.root = root;
    }
    
	@Override
    public final boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
    }
    
    @Override
    public final int getColumnCount() {
        return treetableColumns.getColumnNames().length;
    }
 
    @Override
    public final String getColumnName(int column) {
        return treetableColumns.getColumnNames()[column];
    }
 
    @Override
    public final Class<?> getColumnClass(int column) {
        return treetableColumns.getColumnTypes()[column];
    }
 
}