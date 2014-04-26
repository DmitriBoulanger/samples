package de.dbo.samples.gui.swing.treetable.api.xgui;
 
import de.dbo.samples.gui.swing.treetable.api.Treetable;
import de.dbo.samples.gui.swing.treetable.api.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.TreetableUI;
import de.dbo.samples.gui.swing.treetable.api.TreetableUIManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.table.TableColumn;








import org.jdesktop.swingx.JXTreeTable;
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
public class TreetableXImpl extends JXTreeTable implements TreetableUIManager, Treetable {
	private static final long serialVersionUID = -5203756529846423026L;
	private static final Logger log = LoggerFactory.getLogger(TreetableXImpl.class);
	
//	private final TreetableCell tree;
	
	/**
	 * @param model tree-table data-model 
	 */
    public TreetableXImpl(TreetableModel model) {
        super(model);
        
        // JTree-extension
//        tree = new TreetableCell(this, model);
         
        // Model
//        super.setModel(new TreetableModelAdapter(model, tree));
         
        // Simultaneous selections for JTable and JTree
//        final TreetableSelectionModel selectionModel = new TreetableSelectionModel();
//        tree.setSelectionModel(selectionModel); // For the tree
//        setSelectionModel(selectionModel.getListSelectionModel()); //For the table
 
        // Renderer for JTree
//        setDefaultRenderer(TreetableModel.class, tree);
        
        // Editor for the Treetable
//        setDefaultEditor(TreetableModel.class, new TreetableCellEditor(tree, this));
         
        // No grid show
        setShowGrid(false);
        
        // No focus show
        setFocusable(false);
        
        // No margins between cells
        setIntercellSpacing(new Dimension(0, 0)); 
        
        getTableHeader().setReorderingAllowed(false);
        
        log.trace("created");
    }
    
    //
    // Customization
    //
    
    /* (non-Javadoc)
	 * @see de.dbo.samples.gui.swing.treetable.api.xgui.Treetable#expandAll()
	 */
    @Override
	public void expandAll() {
        int row = 0;
        while (row < getRowCount()) {
            expandRow(row);
            row++;
        }
    }
    
    @Override
	public void collapseAll() {
        int row = 0;
        while (row < getRowCount()) {
            collapseRow(row);
            row++;
        }
    }
    
    @Override
	public final Integer getColunWidth(int column) {
    	final TableColumn tableColumn =  getColumnModel().getColumn(column);
    	return tableColumn.getWidth();
    }
    
    @Override
	public final void setColumnWidthNonresizable(int column, int width) {
    	final TableColumn tableColumn =  getColumnModel().getColumn(column);
    	tableColumn.setMaxWidth(width);
    	tableColumn.setMinWidth(width);
    	tableColumn.setResizable(false);
    }
    
    @Override
	public final void setColumnWidthMin(int column, int width) {
    	final TableColumn tableColumn =  getColumnModel().getColumn(column);
    	tableColumn.setMinWidth(width);
    }
    
    @Override
	public final void setColumnWidth(int column, int preferredWidth) {
    	final TableColumn tableColumn =  getColumnModel().getColumn(column);
    	tableColumn.setPreferredWidth(preferredWidth);
    }
    
    @Override
	public void setBasicUI(final TreetableUI ui) {
    	setBasicUI(ui.getBackground(),ui.getSelection(), ui.getForeround(), ui.getFont());
    }
    
    @Override
	public void setBasicUI(Color background, Color selection,  Color foreground, Font font) {
    	 // JTable
    	 setFont(font);
    	 setBackground(background);
    	 setForeground(foreground);
    	 setSelectionBackground(selection);
         setSelectionForeground(foreground);
         
         // JTable Header
         getTableHeader().setBackground(background);
         getTableHeader().setFont(font);
         getTableHeader().setForeground(foreground);

//    	// JTree
//        final DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) getCellRenderer();
//        renderer.setFont(font);
//    	renderer.setBackground(background);
//    	renderer.setForeground(foreground);
//    	renderer.setTextSelectionColor(foreground);
//    	renderer.setTextNonSelectionColor(foreground);
//        renderer.setBackgroundSelectionColor(selection);
//        
//        // Node in the tree
//        getTreeCellRenderer().setBackgroundselection(selection);
    }
}