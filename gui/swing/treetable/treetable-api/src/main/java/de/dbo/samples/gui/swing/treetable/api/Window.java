package de.dbo.samples.gui.swing.treetable.api;

import de.dbo.samples.gui.swing.treetable.api.factory.Factory;
import de.dbo.samples.gui.swing.treetable.api.factory.FactoryManager;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	protected Window(final String context, final String title) {
        super(title + " - " + context);
        setLayout(new GridBagLayout());
        new TreetablePane(factory(context)).setup(this);
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
	 
	public void showup() {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setVisible(true);
		log.info("elapsed " + (System.currentTimeMillis()-start0) + " ms." );
	}
	
	//
	// HELPERS
	// 
	
	protected static final Factory factory(final String ctx) {
		final long start = System.currentTimeMillis();
        final Factory factory = FactoryManager.instance(ctx);
        log.trace("elapsed " 
        		+ (System.currentTimeMillis() - start) + " ms. creating tree-table factory" );
        return factory;
	}
	
	protected static final void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			log.error("Can't set the system Look-and-Feel", e);
		}
	}
}