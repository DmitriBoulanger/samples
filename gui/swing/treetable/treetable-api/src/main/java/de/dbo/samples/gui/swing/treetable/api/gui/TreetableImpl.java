package de.dbo.samples.gui.swing.treetable.api.gui;
 
import de.dbo.samples.gui.swing.treetable.api.Treetable;
import de.dbo.samples.gui.swing.treetable.api.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.TreetableUI;
import de.dbo.samples.gui.swing.treetable.api.TreetableUIManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * Treetable as extension of the JTable.
 * The JTree-extension is implemented as JTree-based table-cell renderer
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class TreetableImpl extends JTable implements Treetable, TreetableUIManager  {
	private static final long serialVersionUID = -5203756529846423026L;
	private static final Logger log = LoggerFactory.getLogger(TreetableImpl.class);
	
	private final TreetableCell tree;
	
	/**
	 * @param model tree-table data-model 
	 */
    public TreetableImpl(TreetableModel model) {
        super();
        
        // JTree-extension
        tree = new TreetableCell(this, model);
         
        // Model
        super.setModel(new TreetableModelAdapter(model, tree));
         
        // Simultaneous selections for JTable and JTree
        final TreetableSelectionModel selectionModel = new TreetableSelectionModel();
        tree.setSelectionModel(selectionModel); // For the tree
        setSelectionModel(selectionModel.getListSelectionModel()); //For the table
 
        // Renderer for JTree
        setDefaultRenderer(TreetableModel.class, tree);
        
        // Editor for the Treetable
        setDefaultEditor(TreetableModel.class, new TreetableCellEditor(tree, this));
         
        // No grid show
        setShowGrid(false);
        
        // No focus show
        setFocusable(false);
        
        // No margins between cells
        setIntercellSpacing(new Dimension(0, 0)); 
        
        getTableHeader().setReorderingAllowed(false);
        
        log.trace("created: " + this.toString());
    }
    
    //
    // Customization
    //
    
    public void expandAll() {
        int row = 0;
        while (row < getRowCount()) {
            tree.expandRow(row);
            row++;
        }
    }
    
    public void collapseAll() {
        int row = 0;
        while (row < getRowCount()) {
            tree.collapseRow(row);
            row++;
        }
    }
    
    public void setRootVisible(boolean visible) {
    	tree.setRootVisible(visible);
    }
    
    public final Integer getColunWidth(int column) {
    	final TableColumn tableColumn =  getColumnModel().getColumn(column);
    	return tableColumn.getWidth();
    }
    
    public final void setColumnWidthNonresizable(int column, int width) {
    	final TableColumn tableColumn =  getColumnModel().getColumn(column);
    	tableColumn.setMaxWidth(width);
    	tableColumn.setMinWidth(width);
    	tableColumn.setResizable(false);
    }
    
    public final void setColumnWidthMin(int column, int width) {
    	final TableColumn tableColumn =  getColumnModel().getColumn(column);
    	tableColumn.setMinWidth(width);
    }
    
    public final void setColumnWidth(int column, int preferredWidth) {
    	final TableColumn tableColumn =  getColumnModel().getColumn(column);
    	tableColumn.setPreferredWidth(preferredWidth);
    }
    
    public void setBasicUI(final TreetableUI ui) {
     	
		final DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();

		if (null != ui.getBackground()) {
			final Color background = ui.getBackground();
			getTableHeader().setBackground(background);
			renderer.setBackground(background);
		}
		if (null != ui.getForeround()) {
			final Color foreground = ui.getForeround();
			setSelectionForeground(foreground);
			getTableHeader().setForeground(foreground);
			setForeground(foreground);
			renderer.setForeground(foreground);
			renderer.setTextSelectionColor(foreground);
			renderer.setTextNonSelectionColor(foreground);
		}
		if (null != ui.getFont()) {
			final Font font =  ui.getFont();
			setFont(font);
			getTableHeader().setFont(font);
			renderer.setFont(font);
		}
		if (null != ui.getBackgroundSelection()) {
			final Color background = ui.getBackgroundSelection();
			setSelectionBackground(background);
			renderer.setBackgroundSelectionColor(background);
			tree.getTreeCellRenderer().setBackgroundselection(background);
		}
		if (null != ui.getBackgroundTreetable()) {
			final Color background = ui.getBackgroundTreetable();
			setBackground(background);
			getTableHeader().setBackground(background);
			renderer.setBackground(background);
		}
	}
}