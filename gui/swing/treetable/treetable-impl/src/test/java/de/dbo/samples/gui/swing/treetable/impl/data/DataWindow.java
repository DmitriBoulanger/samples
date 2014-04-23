package de.dbo.samples.gui.swing.treetable.impl.data;
 
import de.dbo.samples.gui.swing.treetable.api.Window;
import de.dbo.samples.gui.swing.treetable.api.gui.Treetable;
import de.dbo.samples.gui.swing.treetable.api.records.Node;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

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
				new DataWindow().showup();
			}
		};
		SwingUtilities.invokeLater(gui);
	}
	
	public DataWindow() {
        super("Tree-Table Sample (adapted from http://www.hameister.org/JavaSwingTreeTable.html)");
         
        final long start = System.currentTimeMillis();
        final Node root = DataStructure.treeroot();
        log.info("elapsed " +(System.currentTimeMillis()-start) + " ms. creating tree-root" );
        
        final Treetable treetable = new Treetable(new DataTreetableModel(root));
        treetable.setRootVisible(false);
        treetable.setBasicUI(BACKGROUND, SELECTION, FOREGROUND, FONT);
        treetable.setIntercellSpacing(new Dimension(0, 0)); 
        treetable.setColumnWidthNonresizable(1, 120);
        treetable.setColumnWidthNonresizable(2, 280);
       
        final JScrollPane jScrollPane = new JScrollPane(treetable);
        jScrollPane.getViewport().setBackground(BACKGROUND);
        
        // JFrame
        setContentAs1x1(jScrollPane);
        
        setPreferredSize(new Dimension(800,700));
    }
	
	@Override
	public final void actionPerformed(final ActionEvent e) {
		
	}
}