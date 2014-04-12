package de.dbo.samples.gui.swing.treetable;
 
import de.dbo.samples.gui.swing.treetable.api.TreeTableModel;
import de.dbo.samples.gui.swing.treetable.api.TreeTableModelAbstraction;

import java.util.Date;
 
public class TreeTableModelImpl extends TreeTableModelAbstraction {
    // Spalten Name.
    static protected String[] columnNames = { "Knotentext", "String",  "UUUID", "Datum", "Integer" };
 
    // Spalten Typen.
    static protected Class<?>[] columnTypes = { TreeTableModel.class, String.class,  String.class, Date.class, Integer.class };
 
    public TreeTableModelImpl(DataNode rootNode) {
        super(rootNode);
    }
 
    @Override
    public Object getChild(Object parent, int index) {
        return ((DataNode) parent).getChildren().get(index);
    }
 
    @Override
    public int getChildCount(Object parent) {
        return ((DataNode) parent).getChildren().size();
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
 
    @Override
    public Object getValueAt(Object node, int column) {
        switch (column) {
        case 0:
            return ((DataNode) node).getName();
        case 1:
            return ((DataNode) node).getCapital();
        case 2:
            return ((DataNode) node).getUUID();
        case 3:
            return ((DataNode) node).getDeclared();
        case 4:
            return ((DataNode) node).getArea();
        default:
            break;
        }
        return null;
    }
 
    @Override
    public boolean isCellEditable(Object node, int column) {
        return true; // Important to activate TreeExpandListener
    }
 
    @Override
    public void setValueAt(Object aValue, Object node, int column) {
    }
 
}