package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.TreetableUI;
import de.dbo.samples.gui.swing.treetable.api.records.Node;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TreetableUIImpl implements TreetableUI {
	protected static final Logger log = LoggerFactory.getLogger(TreetableUI.class);
	
	/**
	 * resource-directory with icons
	 */
	private static final String ICONS = "icons/";
	
	// node-icons
	private static final ImageIcon iconError = createIcon(TreetableUIImpl.class,ICONS+"red-flag.png");
	private static final ImageIcon iconWarning = createIcon(TreetableUIImpl.class,ICONS+"warning.png");

    // button icons
	private static final ImageIcon iconRefresh = createIcon(TreetableUIImpl.class,ICONS+"refresh.png");
	private static final ImageIcon iconUpdate = createIcon(TreetableUIImpl.class,ICONS+"add.png");
	private static final ImageIcon iconExpand = createIcon(TreetableUIImpl.class,ICONS+"expand.png");
	private static final ImageIcon iconCollapse = createIcon(TreetableUIImpl.class,ICONS+"collapse.png");
	private static final ImageIcon iconClear = createIcon(TreetableUIImpl.class,ICONS+"cancel.png");
	
	// status label-icons
	private static final JLabel iconLabelDone = createIconLabel(TreetableUIImpl.class,ICONS+"done.png");
	private static final JLabel iconLabelLocked = createIconLabel(TreetableUIImpl.class,ICONS+"lock.png");
	private static final JLabel iconLabelUnlocked = createIconLabel(TreetableUIImpl.class,ICONS+"unlock.png");
	
	// colors and font
	private static final Font FONT = new Font("Consolas",Font.PLAIN, 13);
	private static final Color BACKGROUND = new Color(239,241,248);
	
	@SuppressWarnings("unused")
	private static final Color SELECTION = new Color(168,208,245);
	
	@SuppressWarnings("unused")
	private static final Color FOREGROUND = Color.BLACK;
	
	@Override
    public Icon getIcon(final Node node) {
		if (null==node) {
			 return null;
		 }
		 if ("Error".equals(node.getTreename())) {
			 return iconError;
		 } 
		 else if ("Warning".equals(node.getTreename())) {
			 return iconWarning;
		 } 
		 else {
			 return null;
		 }
    }
	
	public TreetableUIImpl(){
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
	public Color getBackgroundTreetable() {
		return Color.WHITE;
	}

	@Override
	public Color getForeround() {
		return null; //FOREGROUND;
	}

	@Override
	public Color getBackgroundSelection() {
		return null; //SELECTION;
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
