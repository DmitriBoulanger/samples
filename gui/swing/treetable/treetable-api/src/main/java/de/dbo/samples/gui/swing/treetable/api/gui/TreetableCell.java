package de.dbo.samples.gui.swing.treetable.api.gui;
 
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreeModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * JTree-extension  as a table-cell renderer.
 * This renderer comes as a first column in the table
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class TreetableCell extends JTree implements TableCellRenderer {
	private static final long serialVersionUID = 7501441646955414246L;
	private static final Logger log = LoggerFactory.getLogger(TreetableCell.class);

    private final Treetable treetable;
    private final TreeCellRenderer treeCellRenderer;
    
	/** Die letzte Zeile, die gerendert wurde. */
    protected int visibleRow;
    
    private final int verticalMargin = 1;
    
   
	public TreetableCell(Treetable treeTable, TreeModel model) {
        super(model);
        
        this.treetable = treeTable;
        this.treeCellRenderer = new TreeCellRenderer(this);
         
        // Setzen der Zeilenhoehe fuer die JTable
        // Muss explizit aufgerufen werden, weil treetable noch null ist, wenn super(model) setRowHeight aufruft!
        setRowHeight(getRowHeight());
        
        setCellRenderer(treeCellRenderer);
    }
	
	 
    public TreeCellRenderer getTreeCellRenderer() {
		return treeCellRenderer;
	}
    
    
 
    /**
     * Tree und Table muessen die gleiche Hoehe haben.
     */
    @Override
    public void setRowHeight(int rowHeight) {
        if (rowHeight > 0) {
            super.setRowHeight(rowHeight + verticalMargin);
            if (treetable != null && treetable.getRowHeight() != rowHeight) {
                treetable.setRowHeight(getRowHeight());
            }
        }
    }
 
    /**
     * Tree muss die gleiche Hoehe haben wie Table.
     */
    @Override
    public void setBounds(int x, int y, int w, int h) {
        super.setBounds(x, 0, w, treetable.getHeight());
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
        log.trace("getTableCellRendererComponent(JTable table, Object value="+value+", boolean isSelected="+isSelected+", boolean hasFocus="+hasFocus+", int row="+ row +", int column="+column+")");
    	if (isSelected) {
            setBackground(table.getSelectionBackground());
        }
        else {
            setBackground(table.getBackground());
        }
        
 
        visibleRow = row;
        return this;
    }
    
   
}