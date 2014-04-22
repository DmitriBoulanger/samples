package de.dbo.samples.gui.swing.treetable.api;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowTools {
	protected static final Logger log = LoggerFactory.getLogger(Window.class);

	// HELPERS

	/**
	 * adds component to the parent-component. The component takes available
	 * space and supposed to be the only one
	 * 
	 */
	public static final void addAs1x1(final JComponent parent, final JComponent componet) {
		parent.setLayout(gbl1x1());
		parent.add(componet, gbc1x1());
	}

	public static final GridBagConstraints gbc1x1() {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		return gbc;
	}

	public static final GridBagLayout gbl1x1() {
		final GridBagLayout gbl = new GridBagLayout();
		return gbl;
	}

	public static final JMenuBar menubar(final int horizontalGap) {
		final JMenuBar jMenuBar = new JMenuBar();
		final FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		flowLayout.setHgap(horizontalGap);
		jMenuBar.setLayout(flowLayout);
		return jMenuBar;
	}

	public static void elapsed(final long start, final String comment) {
		log.debug("elapsed " + (System.currentTimeMillis() - start) + " ms. " 
				+ (null != comment ? comment : ""));
	}
	
	/** 
	 * @return an ImageIcon, or null if the path was invalid. 
	 */
	public static final ImageIcon createIcon(Object anchor, String path) {
	    return createIcon(anchor.getClass(), path);
	}
	
	public static final ImageIcon createIcon(final Class<?> anchor, final String path) {
		ImageIcon ret;
		try {
			ret = new ImageIcon(anchor.getResource(path));
		} catch (Exception e) {
			log.error("Couldn't find icon-file: path" + path + " ancor=" +anchor.getName());
			return null;
		}
	    
	    log.debug(path+": "  + (null==ret? "NULL" 
	    		: ret.getIconWidth() +"x"+ ret.getIconHeight()));
	    return ret;
	   
	}
	
	public static final JLabel createIconLabel(final Object anchor, final String path
			, ImageObserver imageObserver) {
		return createIconLabel(anchor.getClass(), path, imageObserver);
	}
	
	public static final JLabel createIconLabel(final Class<?> anchor, final String path
			, ImageObserver imageObserver) {
	    final ImageIcon icon = createIcon(anchor,path);
	    if (null==icon) {
	    	return null;
	    }
	    final JLabel iconLabel = new JLabel(icon, JLabel.CENTER);
	    icon.setImageObserver(imageObserver);
	    return iconLabel;
	}
	

}
