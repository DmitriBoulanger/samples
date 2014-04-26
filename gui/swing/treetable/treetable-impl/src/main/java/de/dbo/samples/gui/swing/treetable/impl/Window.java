package de.dbo.samples.gui.swing.treetable.impl;

import de.dbo.samples.gui.swing.treetable.api.TreetablePane;
import de.dbo.samples.gui.swing.treetable.api.factory.FactoryManager;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default window for treetable-components.
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public abstract class Window extends JFrame {
	protected static final long serialVersionUID = 4489500964556705612L;
	protected static final Logger log = LoggerFactory.getLogger(Window.class);
	
	private final long start0 = System.currentTimeMillis();
	
	/**
	 * GUI with childless treetable-root.
	 * Initial status is UNLOCKED, records = null
	 * 
	 * @param context  Spring-context to initialize Treetable-Factory
	 */
	protected Window(final String context, final String title
			, final boolean createMenuBar) {
        super(title + " - " + context);
        setLayout(new GridBagLayout());
        new TreetablePane(FactoryManager.getFactory(context)).selfDeployTo(this, createMenuBar);
    }
	
	protected Window(final String context, final String title) {
        this(context,title,false);
    }
	
	/**
	 * plain window with GridBag-Layout
	 * 
	 * @param title window title
	 */
	protected Window(final String title) {
        super(title);
        setLayout(new GridBagLayout());
    }
	
	/**
	 * pack everything, 
	 * locate in the screen-center on the top and make itself visible
	 */
	public void showup() {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setVisible(true);
		log.debug("elapsed " + (System.currentTimeMillis()-start0) + " ms." );
	}
	
	//
	// HELPERS
	// 
	
	protected static final void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			log.error("Can't set the system Look-and-Feel", e);
		}
	}
}