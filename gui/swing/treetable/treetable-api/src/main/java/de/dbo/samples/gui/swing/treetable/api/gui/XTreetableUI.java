package de.dbo.samples.gui.swing.treetable.api.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public interface XTreetableUI {

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
	
	public abstract Color getBackground();
	
	public abstract Color getForeround();
	
	public abstract Color getSelection();
	
	/**
	 * mono-space, e.g CONSOLAS
	 * 
	 * @return icon
	 */
	public abstract Font getFont();
	
}