package de.dbo.samples.gui.swing.treetable.impl.data;
 
import de.dbo.samples.gui.swing.treetable.api.Window;
import de.dbo.samples.gui.swing.treetable.api.gui.Treetable;
import de.dbo.samples.gui.swing.treetable.api.records.Node;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class DataWindow extends Window {
	private static final long serialVersionUID = 4489500964556705612L;
	
	public static void main(final String[] args) {
		final Runnable gui = new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				new DataWindow().setVisible(true);
			}
		};
		SwingUtilities.invokeLater(gui);
	}
	
	private final Dimension size = new Dimension(1000, 400);
	private final Font font = CONSOLAS12.deriveFont(13.0f);
	
	private final Color background = new Color(239,241,248);
	private final Color selection = new Color(168,208,245);
	private final Color foreground = Color.BLACK;
	
	public DataWindow() {
        super("Tree-Table Sample (adapted from http://www.hameister.org/JavaSwingTreeTable.html)");
         
        final long start = System.currentTimeMillis();
        final Node root = DataStructure.treeroot();
        log.info("Elapsed " +(System.currentTimeMillis()-start) + " ms. creating tree-root" );
        
        final Treetable treetable = new Treetable(new DataTreetableModel(root));
        treetable.setRootVisible(false);
        treetable.setBasicUI(background, selection, foreground, font);
        treetable.setIntercellSpacing(new Dimension(0, 0)); 
        treetable.setColumnWidthNonresizable(1, 120);
        treetable.setColumnWidthNonresizable(2, 280);
       
        final JScrollPane jScrollPane = new JScrollPane(treetable);
        jScrollPane.getViewport().setBackground(background);
        
        // JFrame
        addAs1x1(jScrollPane);
        setSize(size);
        setLocationRelativeTo(null);  
    }
}