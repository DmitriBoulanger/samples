package de.dbo.samples.gui.swing.treetable.api.guix;
 
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
public class TreetableImpl extends JXTreeTable implements TreetableUIManager, Treetable {
	private static final long serialVersionUID = -5203756529846423026L;
	private static final Logger log = LoggerFactory.getLogger(TreetableImpl.class);
	
	/**
	 * @param model tree-table data-model 
	 */
    public TreetableImpl(TreetableModel model, final TreetableUI treetableUI) {
        super(model);
 
        // No grid show
        setShowGrid(false);
        
        // No focus show
        setFocusable(false);
        
        // No margins between cells
        setIntercellSpacing(new Dimension(0, 0)); 
        
        getTableHeader().setReorderingAllowed(false);
        
        setTreeCellRenderer(new TreeCellRenderer(treetableUI));
        
        log.trace("created: " + this.toString());
    }
    
    //
    // Customization
    //
    
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
    
		if (null != ui.getBackground()) {
			final Color background = ui.getBackground();
//			setBackground(background);
			getTableHeader().setBackground(background);
		}
		if (null != ui.getForeround()) {
			final Color foreground = ui.getForeround();
			setSelectionForeground(foreground);
			getTableHeader().setForeground(foreground);
			setForeground(foreground);
		}
		if (null != ui.getFont()) {
			final Font font = ui.getFont();
			setFont(font);
			getTableHeader().setFont(font);
		}
		
		if (null != ui.getBackgroundSelection()) {
			final Color selection = ui.getBackgroundSelection();
			setSelectionBackground(selection);
		}
	}
}