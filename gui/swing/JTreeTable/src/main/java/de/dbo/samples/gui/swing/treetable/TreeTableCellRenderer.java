package de.dbo.samples.gui.swing.treetable;
 
import de.dbo.samples.gui.swing.treetable.api.TreeTable;

import java.awt.Component;
import java.awt.Graphics;
 

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreeModel;
 
public class TreeTableCellRenderer extends JTree implements TableCellRenderer {
	private static final long serialVersionUID = 7501441646955414246L;
	
    private final TreeTable treeTable;
    
    /** Die letzte Zeile, die gerendert wurde. */
    protected int visibleRow;
     
    public TreeTableCellRenderer(TreeTable treeTable, TreeModel model) {
        super(model);
        this.treeTable = treeTable;
         
        // Setzen der Zeilenhoehe fuer die JTable
        // Muss explizit aufgerufen werden, weil treeTable noch
        // null ist, wenn super(model) setRowHeight aufruft!
        setRowHeight(getRowHeight());
    }
 
    /**
     * Tree und Table muessen die gleiche Hoehe haben.
     */
    @Override
    public void setRowHeight(int rowHeight) {
        if (rowHeight > 0) {
            super.setRowHeight(rowHeight);
            if (treeTable != null && treeTable.getRowHeight() != rowHeight) {
                treeTable.setRowHeight(getRowHeight());
            }
        }
    }
 
    /**
     * Tree muss die gleiche Hoehe haben wie Table.
     */
    @Override
    public void setBounds(int x, int y, int w, int h) {
        super.setBounds(x, 0, w, treeTable.getHeight());
    }
 
    /**
     * Sorgt fuer die Einrueckung der Ordner.
     */
    @Override
    public void paint(Graphics g) {
        g.translate(0, -visibleRow * getRowHeight());
         
        super.paint(g);
    }
     
    /**
     * Liefert den Renderer mit der passenden Hintergrundfarbe zurueck.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected)
            setBackground(table.getSelectionBackground());
        else
            setBackground(table.getBackground());
 
        visibleRow = row;
        return this;
    }
}