package de.dbo.samples.gui.swing.treetable.impl.ref;

import static de.dbo.samples.gui.swing.treetable.api.WindowTools.createIcon;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.createIconLabel;

import de.dbo.samples.gui.swing.treetable.api.gui.TreetableUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TreetablePaneUIImpl implements TreetableUI {
	
	// button icons
	private static final ImageIcon iconRefresh = createIcon(TreetablePaneUIImpl.class,"icons/refresh.png");
	private static final ImageIcon iconUpdate = createIcon(TreetablePaneUIImpl.class,"icons/add.png");
	private static final ImageIcon iconExpand = createIcon(TreetablePaneUIImpl.class,"icons/expand.png");
	private static final ImageIcon iconCollapse = createIcon(TreetablePaneUIImpl.class,"icons/collapse.png");
	private static final ImageIcon iconClear = createIcon(TreetablePaneUIImpl.class,"icons/cancel.png");
	
	// status label-icons
	private static final JLabel iconLabelDone = createIconLabel(TreetablePaneUIImpl.class,"icons/done.png");
	private static final JLabel iconLabelLocked = createIconLabel(TreetablePaneUIImpl.class,"icons/lock.png");
	private static final JLabel iconLabelUnlocked = createIconLabel(TreetablePaneUIImpl.class,"icons/unlock.png");
	
	// colors and font
	private static final Font FONT = new Font("Consolas",Font.PLAIN, 13);
	private static final Color BACKGROUND = new Color(239,241,248);
	private static final Color SELECTION = new Color(168,208,245);
	private static final Color FOREGROUND = Color.BLACK;
	
	@Override
	public ImageIcon getIconRefresh() {
		return iconRefresh;
	}
	
	@Override
	public ImageIcon getIconUpdate() {
		return iconUpdate;
	}
	
	@Override
	public ImageIcon getIconExpand() {
		return iconExpand;
	}
	
	@Override
	public ImageIcon getIconCollapse() {
		return iconCollapse;
	}
	
	@Override
	public ImageIcon getIconClear() {
		return iconClear;
	}
	
	@Override
	public JLabel getIconLabelDone() {
		return iconLabelDone;
	}
	
	@Override
	public JLabel getIconLabelLocked() {
		return iconLabelLocked;
	}
	
	@Override
	public JLabel getIconLabelUnlocked() {
		return iconLabelUnlocked;
	}

	@Override
	public Color getBackground() {
		return BACKGROUND;
	}

	@Override
	public Color getForeround() {
		return FOREGROUND;
	}

	@Override
	public Color getSelection() {
		return SELECTION;
	}

	@Override
	public Font getFont() {
		return FONT;
	}
}
