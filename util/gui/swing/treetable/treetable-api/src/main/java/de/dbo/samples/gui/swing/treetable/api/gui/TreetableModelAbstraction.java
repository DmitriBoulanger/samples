package de.dbo.samples.gui.swing.treetable.api.gui;
 
import de.dbo.samples.gui.swing.treetable.api.TreetableColumns;
import de.dbo.samples.gui.swing.treetable.api.TreetableException;
import de.dbo.samples.gui.swing.treetable.api.TreetableModel;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

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
public abstract class TreetableModelAbstraction implements TreetableModel {
	protected static final Logger log = LoggerFactory.getLogger(TreetableModel.class);
	
	protected Long timestamp;
    protected Object root;
    protected final EventListenerList listenerList = new EventListenerList();
 
    private static final int CHANGED = 0;
    private static final int INSERTED = 1;
    private static final int REMOVED = 2;
    private static final int STRUCTURE_CHANGED = 3;
    
    private final TreetableColumns treetableColumns;
    
    protected TreetableModelAbstraction(final TreetableColumns treetableColumns) {
    	if (null==treetableColumns) {
    		throw new TreetableException("treetableColumns is null");
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
    public void valueForPathChanged(TreePath path, Object newValue) {
    }
 
    /**
     * Die Methode wird normalerweise nicht aufgerufen.
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return 0;
    }
 
    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listenerList.add(TreeModelListener.class, l);
    }
 
    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listenerList.remove(TreeModelListener.class, l);
    }
 
    protected void fireTreeNodesChanged(Object source, Object[] path, int[] childIndices, Object[] children) {
        fireTreeNode(CHANGED, source, path, childIndices, children);
    }
 
    protected void fireTreeNodesInserted(Object source, Object[] path, int[] childIndices, Object[] children) {
        fireTreeNode(INSERTED, source, path, childIndices, children);
    }
 
    protected void fireTreeNodesRemoved(Object source, Object[] path, int[] childIndices, Object[] children) {
        fireTreeNode(REMOVED, source, path, childIndices, children);
    }
 
    protected void fireTreeStructureChanged(Object source, Object[] path, int[] childIndices, Object[] children) {
        fireTreeNode(STRUCTURE_CHANGED, source, path, childIndices, children);
    }
    
    private final void fireTreeNode(int changeType, Object source, Object[] path, int[] childIndices, Object[] children) {
    	log.trace("fireTreeNode(int changeType="+changeType+", Object source, Object[] path="+path+", int[] childIndices, Object[] children)");
    	Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = new TreeModelEvent(source, path, childIndices, children);
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == TreeModelListener.class) {
 
                switch (changeType) {
                case CHANGED:
                    ((TreeModelListener) listeners[i + 1]).treeNodesChanged(e);
                    break;
                case INSERTED:
                    ((TreeModelListener) listeners[i + 1]).treeNodesInserted(e);
                    break;
                case REMOVED:
                    ((TreeModelListener) listeners[i + 1]).treeNodesRemoved(e);
                    break;
                case STRUCTURE_CHANGED:
                    ((TreeModelListener) listeners[i + 1]).treeStructureChanged(e);
                    break;
                default:
                    break;
                }
            }
        }
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
    
    // from X
    @Override
    public int getHierarchicalColumn() {
        if (getColumnCount() == 0) {
            return -1;
        }
        
        return 0;
    }
 
}