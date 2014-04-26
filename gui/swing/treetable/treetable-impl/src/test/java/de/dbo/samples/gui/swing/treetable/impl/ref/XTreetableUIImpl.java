package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.TreetableUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XTreetableUIImpl implements TreetableUI {
	protected static final Logger log = LoggerFactory.getLogger(TreetableUI.class);

	// button icons
	private static final ImageIcon iconRefresh = createIcon(XTreetableUIImpl.class,"icons/refresh.png");
	private static final ImageIcon iconUpdate = createIcon(XTreetableUIImpl.class,"icons/add.png");
	private static final ImageIcon iconExpand = createIcon(XTreetableUIImpl.class,"icons/expand.png");
	private static final ImageIcon iconCollapse = createIcon(XTreetableUIImpl.class,"icons/collapse.png");
	private static final ImageIcon iconClear = createIcon(XTreetableUIImpl.class,"icons/cancel.png");
	
	// status label-icons
	private static final JLabel iconLabelDone = createIconLabel(XTreetableUIImpl.class,"icons/done.png");
	private static final JLabel iconLabelLocked = createIconLabel(XTreetableUIImpl.class,"icons/lock.png");
	private static final JLabel iconLabelUnlocked = createIconLabel(XTreetableUIImpl.class,"icons/unlock.png");
	
	// colors and font
	public static final Font FONT = new Font("Consolas",Font.PLAIN, 13);
	public static final Color BACKGROUND = new Color(239,241,248);
	public static final Color SELECTION = new Color(168,208,245);
	public static final Color FOREGROUND = Color.BLACK;
	
	public XTreetableUIImpl(){
		log.trace("created");
	}
	
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
	
	
	
	
	/** 
	 * @return an ImageIcon, or null if the path was invalid. 
	 */
	public static final ImageIcon createIcon(final Object anchor, final String path) {
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
	    
	    log.trace(path+": "  + (null==ret? "NULL" : ret.getIconWidth() +"x"+ ret.getIconHeight()));
	    return ret;
	   
	}
	
	public static final JLabel createIconLabel(final Object anchor, final String path) {
		return createIconLabel(anchor.getClass(), path, null);
	}
	
	public static final JLabel createIconLabel(final Object anchor, final String path
			, final ImageObserver imageObserver) {
		return createIconLabel(anchor.getClass(), path, imageObserver);
	}
	
	public static final JLabel createIconLabel(final Class<?> anchor, final String path) {
		 return createIconLabel(anchor,path,null);
	}
	
	public static final JLabel createIconLabel(final Class<?> anchor, final String path
			, final ImageObserver imageObserver) {
	    final ImageIcon icon = createIcon(anchor,path);
	    if (null==icon) {
	    	return null;
	    }
	    final JLabel iconLabel = new JLabel(icon, JLabel.CENTER);
	    icon.setImageObserver(imageObserver);
	    return iconLabel;
	}
}
