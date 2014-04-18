package de.dbo.samples.gui.swing.treetable.api.gui;
 
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreePath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class TreetableModelAdapter extends AbstractTableModel {
	private static final long serialVersionUID = 7118812279021590634L;
	private static final Logger log = LoggerFactory.getLogger(TreetableModelAdapter.class);
	
	final JTree tree;
    final TreetableModel treeTableModel;
 
    public TreetableModelAdapter(TreetableModel treeTableModel, JTree tree) {
        this.tree = tree;
        this.treeTableModel = treeTableModel;
 
        tree.addTreeExpansionListener(new TreeExpansionListener() {
        	@Override
            public void treeExpanded(TreeExpansionEvent event) {
        		log.debug("treeExpanded(TreeExpansionEvent event"+event.toString()+") ...");
                fireTableDataChanged();
            }
        	
        	@Override
            public void treeCollapsed(TreeExpansionEvent event) {
        		log.debug("fireTableDataChanged() event"+event.toString()+") ...");
                fireTableDataChanged();
            }
        });
    }
 
 
    @Override
    public int getColumnCount() {
        return treeTableModel.getColumnCount();
    }
 
    @Override
    public String getColumnName(int column) {
        return treeTableModel.getColumnName(column);
    }
 
    @Override
    public Class<?> getColumnClass(int column) {
        return treeTableModel.getColumnClass(column);
    }
 
    @Override
    public int getRowCount() {
        return tree.getRowCount();
    }
 
    protected Object nodeForRow(int row) {
        TreePath treePath = tree.getPathForRow(row);
        return treePath.getLastPathComponent();
    }
 
    @Override
    public Object getValueAt(int row, int column) {
        return treeTableModel.getValueAt(nodeForRow(row), column);
    }
 
    @Override
    public boolean isCellEditable(int row, int column) {
        return treeTableModel.isCellEditable(nodeForRow(row), column);
    }
 
    @Override
    public void setValueAt(Object value, int row, int column) {
    	log.debug("setValueAt(Object value="+value+", int row="+row+", int column="+column+") ...");
        treeTableModel.setValueAt(value, nodeForRow(row), column);
    }
}