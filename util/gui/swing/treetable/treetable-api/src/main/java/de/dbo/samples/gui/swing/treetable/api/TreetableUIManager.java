package de.dbo.samples.gui.swing.treetable.api;

import java.awt.Dimension;

/**
 * Treetable UI features that can be configured
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface TreetableUIManager {

	    public Integer getColunWidth(int column);
	    
	    public void setColumnWidthNonresizable(int column, int width);
	    
	    public void setColumnWidthMin(int column, int width);
	    
	    public void setColumnWidth(int column, int preferredWidth);
	    
	    public void setBasicUI(final TreetableUI ui);
	     
	    public void setIntercellSpacing(final Dimension dimension);	 
	    
	    public void setAutoResizeMode(int jTableAutoResizeMode);

}
