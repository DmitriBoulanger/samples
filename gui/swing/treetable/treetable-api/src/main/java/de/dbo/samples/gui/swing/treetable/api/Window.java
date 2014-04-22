package de.dbo.samples.gui.swing.treetable.api;
 
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.gbc1x1;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.gbl1x1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;

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
	
	public void showup(final Dimension preferredSize) {
		if (null!=preferredSize) {
			setMinimumSize(preferredSize);
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
	protected final void setContentAs1x1(final JComponent componet) {
        this.add(componet, gbc1x1());
	}


}









