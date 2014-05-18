package de.dbo.samples.gui.swing.treetable.impl;

import de.dbo.samples.gui.swing.treetable.api.TreetablePane;
import de.dbo.samples.gui.swing.treetable.api.factory.FactoryManager;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

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
	 * @param title string to appear in window-frame
	 * @param createMenuBar if true, then allocated controls in the Menu-bar
	 * 			of this JFrame
	 */
	protected Window(final String context, final String title
			, final boolean createMenuBar) {
        this(title + " CTX=" + context);
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
    }
	
	public void showup() {
		showup(null);
	}
	
	/**
	 * pack everything, 
	 * locate in the screen-center on the top and make itself visible
	 */
	public void showup(final Double sizeFactor) {
	    setAlwaysOnTop(true);
	    if (null==sizeFactor) {
	    	pack();
	    } else {
	    	setSize(size(sizeFactor));
	    }
		setLocationRelativeTo(null);
		setVisible(true);
		log.debug("elapsed " + (System.currentTimeMillis()-start0) + " ms." );
	}
	
	private static final Dimension size(final double factor) {
		final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		final int width  = (int) ((double )gd.getDisplayMode().getWidth() * factor );
		final int height = (int) ((double )gd.getDisplayMode().getHeight() * factor );
		return new Dimension(width,height);
	}
}