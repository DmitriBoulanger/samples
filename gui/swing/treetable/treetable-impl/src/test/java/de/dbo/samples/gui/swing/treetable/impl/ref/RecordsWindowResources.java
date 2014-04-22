package de.dbo.samples.gui.swing.treetable.impl.ref;

import static de.dbo.samples.gui.swing.treetable.api.WindowTools.createIcon;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.createIconLabel;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.elapsed;

import de.dbo.samples.gui.swing.treetable.api.factory.Factory;
import de.dbo.samples.gui.swing.treetable.api.factory.FactoryMgr;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public final class RecordsWindowResources {
	 
	public static final Factory factory(final String ctx) {
		final long start = System.currentTimeMillis();
        final Factory factory = FactoryMgr.instance(ctx);
        elapsed(start, "creating tree-table factory" );
        return factory;
	}
	
	// button-icons
	static final ImageIcon ICON_REFRESH = createIcon(RecordsWindowResources.class,"icons/refresh.png");
	static final ImageIcon ICON_UPDATE = createIcon(RecordsWindowResources.class,"icons/update.png");
	static final ImageIcon ICON_EXPAND = createIcon(RecordsWindowResources.class,"icons/expand.png");
	static final ImageIcon ICON_COLLAPSE = createIcon(RecordsWindowResources.class,"icons/collapse.png");
	static final ImageIcon ICON_CLEAR = createIcon(RecordsWindowResources.class,"icons/clear.png");
	
	// status and status-icons
	static final int UNLOCKED = 0;
	static final int LOCKED = 1;
	static final int DONE = 2;
	static final JLabel ICON_DONE = createIconLabel(RecordsWindowResources.class,"icons/done.png");
	static final JLabel ICON_LOCKED = createIconLabel(RecordsWindowResources.class,"icons/lock.png");
	static final JLabel ICON_UNLOCKED = createIconLabel(RecordsWindowResources.class,"icons/unlock.png");

	// HELPERS
	
	protected static JTextField textfield(final int columns, final String tooltip) {
		final JTextField jTextField = new JTextField();
		jTextField.setColumns(columns);
		jTextField.setToolTipText(tooltip);
		return jTextField;
	}
	
	protected static JTextField space(final int columns) {
		final JTextField jTextField = new JTextField();
		jTextField.setColumns(columns);
		jTextField.setBorder(new EmptyBorder(0,0,0,0));
		jTextField.setEditable(false);
		jTextField.setFocusable(false);
		jTextField.setOpaque(false);
		return jTextField;
	}
	
	protected static JTextField info(Dimension size) {
		final JTextField jTextField = new JTextField();
		jTextField.setPreferredSize(size); 
		jTextField.setBorder(new EmptyBorder(0,0,0,0));
		jTextField.setEditable(false);
		jTextField.setFocusable(false);
		jTextField.setOpaque(false);
		return jTextField;
	}
	
	protected static JTextField label(final String text) {
		final JTextField jTextFiled = new JTextField(text);
		jTextFiled.setBorder(new EmptyBorder(0,0,0,0));
		jTextFiled.setEditable(false);
		jTextFiled.setFocusable(false);
		jTextFiled.setOpaque(false);
		return jTextFiled;
	}
	
	protected static final JButton button(final ImageIcon icon, final String tooltip) {
		final JButton jButton = new JButton(icon);
		jButton.setToolTipText(tooltip);
		jButton.setFocusable(false);
		return jButton;
	}
}