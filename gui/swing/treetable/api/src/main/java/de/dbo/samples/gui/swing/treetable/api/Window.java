package de.dbo.samples.gui.swing.treetable.api;
 
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Window extends JFrame {
	private static final long serialVersionUID = 8046982486171537192L;
	protected static final Logger log = LoggerFactory.getLogger(Window.class);
	
	protected final long start0 = System.currentTimeMillis();
	protected Window(final String title) {
        super(title);
        setLayout(gbl1x1());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setAlwaysOnTop(true);
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
	
	// MONOSPACE FONTS
	public static final Font CONSOLAS12 = new Font("Consolas",Font.PLAIN, 12);
	public static final Font CONSOLAS13 = new Font("Consolas",Font.PLAIN, 12);
	public static final Font CONSOLAS14 = new Font("Consolas",Font.PLAIN, 14);
}









