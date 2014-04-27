package de.dbo.samples.gui.swing.treetable.api;

import de.dbo.samples.gui.swing.treetable.api.records.Node;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public interface TreetableUI {
	
	public abstract Icon getIcon(Node node);
	
	/**
	 * 16x16 icon
	 * 
	 * @return icon
	 */
	public abstract ImageIcon getIconRefresh();

	/**
	 * 16x16 icon
	 * 
	 * @return icon
	 */
	public abstract ImageIcon getIconUpdate();

	/**
	 * 16x16 icon
	 * 
	 * @return icon
	 */
	public abstract ImageIcon getIconExpand();

	/**
	 * 16x16 icon
	 * 
	 * @return icon
	 */
	public abstract ImageIcon getIconCollapse();

	/**
	 * 16x16 icon
	 * 
	 * @return icon
	 */
	public abstract ImageIcon getIconClear();

	/**
	 * JLabel with 16x16 icon inside
	 * 
	 * @return icon
	 */
	public abstract JLabel getIconLabelDone();

	/**
	 * JLabel with 16x16 icon inside
	 * 
	 * @return icon
	 */
	public abstract JLabel getIconLabelLocked();

	/**
	 * JLabel with 16x16 icon inside
	 * 
	 * @return icon
	 */
	public abstract JLabel getIconLabelUnlocked();
	
	/**
	 * background color for non-treetable components.
	 * 
	 * @return color
	 */
	public abstract Color getBackground();
	
	/**
	 * background color for treetable component.
	 * In X-implementation this color is ignored
	 * 
	 * @return color
	 */
	public abstract Color getBackgroundTreetable();
	
	
	public abstract Color getBackgroundSelection();
	
	public abstract Color getForeround();
	
	/**
	 * mono-space font, e.g CONSOLAS
	 * 
	 * @return mono-space font
	 */
	public abstract Font getFont();
	
}