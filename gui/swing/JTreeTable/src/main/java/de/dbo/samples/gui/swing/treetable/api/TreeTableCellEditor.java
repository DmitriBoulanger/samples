package de.dbo.samples.gui.swing.treetable.api;
 
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class TreeTableCellEditor extends AbstractCellEditor implements TableCellEditor {
	private static final long serialVersionUID = -6289597470847709283L;
	private static final Logger log = LoggerFactory.getLogger(TreeTableCellEditor.class);
	
	private final JTree tree;
    private final JTable table;
 
    public TreeTableCellEditor(JTree tree, JTable table) {
        this.tree = tree;
        this.table = table;
    }
 
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int r, int c) {
    	 log.debug("getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int r, int c)");
    	return tree;
    }
 
    @Override
    public boolean isCellEditable(EventObject e) {
        if (e instanceof MouseEvent) {
            int colunm1 = 0;
            MouseEvent me = (MouseEvent) e;
            int doubleClick = 2;
            MouseEvent newME = new MouseEvent(tree, me.getID(), me.getWhen(), me.getModifiers(), me.getX() - table.getCellRect(0, colunm1, true).x, me.getY(), doubleClick, me.isPopupTrigger());
            log.debug("isCellEditable(EventObject e="+e+"): dispatching ...");
            tree.dispatchEvent(newME);
        } else {
        	 log.debug("isCellEditable(EventObject e="+e+"): ignoring ...");
        }
        return false;
    }
 
    @Override
    public Object getCellEditorValue() {
    	log.debug("getCellEditorValue(): returning null");
        return null;
    }
 
}