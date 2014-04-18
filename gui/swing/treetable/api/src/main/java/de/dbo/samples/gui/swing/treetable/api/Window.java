package de.dbo.samples.gui.swing.treetable.api;
 
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Window extends JFrame {
	private static final long serialVersionUID = 8046982486171537192L;
	private static final Logger log = LoggerFactory.getLogger(Window.class);
	
	private final long start0 = System.currentTimeMillis();
	
	protected Window(final String title) {
        super(title);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setAlwaysOnTop(true);
    }
	
	/**
	 * adds application-contents to the content of this jFrame
	 */
	protected final void addToContent(final Component componet) {
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        setLayout(gridBagLayout);
        add(componet, gbc);
	}
	
	protected static final Runnable runnable(final Window window) {
		 log.info("Elapsed " +(System.currentTimeMillis()-window.start0) + " ms. before opening ..." );
		 return new Runnable() {
	        	@Override
	            public void run() {
	                try {
	                	final String lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
	                	log.debug("LookAndFeel=" +lookAndFeelClassName);
	                    UIManager.setLookAndFeel(lookAndFeelClassName);
	                } catch (Exception e) {
	                   log.error("Can't set system Look-and-Feel: ", e);
	                }
	                window.setVisible(true);
	            }
	        }; 
	}
}