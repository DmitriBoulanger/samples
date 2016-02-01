package de.dbo.samples.gui.swing.treetable.api.gui;
 
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class TreetableCellEditor extends AbstractCellEditor implements TableCellEditor {
	private static final long serialVersionUID = -6289597470847709283L;
	private static final Logger log = LoggerFactory.getLogger(TreetableCellEditor.class);
	
	private final JTree tree;
    private final JTable table;
 
    public TreetableCellEditor(JTree tree, JTable table) {
        this.tree = tree;
        this.table = table;
    }
 
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int r, int c) {
    	 log.trace("getTableCellEditorComponent(JTable table, Object value="+value+", boolean isSelected="+isSelected+", int r, int c)");
    	return tree;
    }
 

    @Override
    public boolean isCellEditable(final EventObject e) {
        if (e instanceof MouseEvent) {
            int colunm1 = 0;
            MouseEvent me = (MouseEvent) e;
            int doubleClick = 2;
            final MouseEvent newME = 
            		new MouseEvent(tree, me.getID(), me.getWhen(), me.getModifiers(), me.getX() - table.getCellRect(0, colunm1, true).x, me.getY(), doubleClick, me.isPopupTrigger());
            log.trace("isCellEditable(EventObject e=" + e +"): dispatching ...");
            tree.dispatchEvent(newME);
        } else {
        	 log.trace("isCellEditable(EventObject e="+e+"): ignoring ...");
        }
        return false;
    }
 
    @Override
    public Object getCellEditorValue() {
    	log.trace("getCellEditorValue(): returning null");
        return null;
    }
 
}