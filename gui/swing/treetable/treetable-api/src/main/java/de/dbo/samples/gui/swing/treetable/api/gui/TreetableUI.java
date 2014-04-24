package de.dbo.samples.gui.swing.treetable.api.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public interface TreetableUI {

	public abstract ImageIcon getIconRefresh();

	public abstract ImageIcon getIconUpdate();

	public abstract ImageIcon getIconExpand();

	public abstract ImageIcon getIconCollapse();

	public abstract ImageIcon getIconClear();

	public abstract JLabel getIconLabelDone();

	public abstract JLabel getIconLabelLocked();

	public abstract JLabel getIconLabelUnlocked();
	
	public abstract Color getBackground();
	
	public abstract Color getForeround();
	
	public abstract Color getSelection();
	
	public abstract Font getFont();
	
}