package de.dbo.samples.gui.swing.treetable.api;
 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Window extends JFrame implements ActionListener {
	private static final long serialVersionUID = 8046982486171537192L;
	protected static final Logger log = LoggerFactory.getLogger(Window.class);
	
	protected static final Font FONT = new Font("Consolas",Font.PLAIN, 12);;
	protected static final Color BACKGROUND = new Color(239,241,248);
	protected static final Color SELECTION = new Color(168,208,245);
	protected static final Color FOREGROUND = Color.BLACK;
	
	private final long start0 = System.currentTimeMillis();
	
	protected static final void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			log.error("Can't set the system Look-and-Feel", e);
		}
	}
	
	protected Window(final String title) {
        super(title);
        setLayout(gbl1x1());
    }
	
	public final void setContentBackgroud(Color background) {
		getContentPane().setBackground(background);
	}
	
	public final void clearContent() {
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().validate();
	}
	
	public void showup(final Dimension preferredSize) {
		if (null!=preferredSize) {
			setPreferredSize(preferredSize);
		}
		setAlwaysOnTop(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		log.info("Elapsed " + (System.currentTimeMillis()-start0) + " ms." );
	}
	 
	/**
	 * adds application-component to the content of this jFrame.
	 * The component takes available space and supposed to be the only one 
	 * 
	 */
	protected final void addAs1x1(final JComponent componet) {
        this.add(componet, gbc1x1());
	}

	// HELPERS
	
	/**
	 * adds component to the parent-component.
	 * The component takes available space and supposed to be the only one 
	 * 
	 */
	public static final void addAs1x1(final JComponent parent, final JComponent componet) {
        parent.setLayout(gbl1x1());
        parent.add(componet, gbc1x1());
	}
	
	private static final GridBagConstraints gbc1x1() {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        return gbc;
	}
	
	private static final GridBagLayout gbl1x1() {
		final GridBagLayout gbl = new GridBagLayout();
        return gbl;
	}
	
	protected JTextField textfield(int columns) {
		final JTextField jTextField = new JTextField();
		jTextField.setColumns(30);
		jTextField.setMinimumSize(new Dimension(500,20));
		return jTextField;
	}
	
	protected JTextField label(final String text) {
		final JTextField jTextFiled = new JTextField(text);
		jTextFiled.setBorder(new EmptyBorder(0,0,0,0));
		jTextFiled.setEditable(false);
		jTextFiled.setFocusable(false);
		jTextFiled.setOpaque(false);
		return jTextFiled;
	}
	
	protected final JButton button(final String text) {
		final JButton jButton = new JButton(text);
		jButton.setFocusable(false);
		jButton.setSize(120, 20);
		return jButton;
	}
	
	protected final JMenuBar menubar() {
		final JMenuBar jMenuBar = new JMenuBar();
		final FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		flowLayout.setHgap(3);
		jMenuBar.setLayout(flowLayout);
		return jMenuBar;
	}
	

}









