package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.Window;
import de.dbo.samples.gui.swing.treetable.api.gui.Treetable;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.records.Node;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 
public class RecordsTestWindow extends Window {
	private static final long serialVersionUID = 4489500964556705612L;
	
	public static void main(final String[] args) {
		final Runnable gui = new Runnable() {
			@Override
			public final void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					log.error("Can't set the system Look-and-Feel",e);
				}
				new RecordsTestWindow().setVisible(true);
			}
		};
		SwingUtilities.invokeLater(gui);
	}
	
	private final Dimension size = new Dimension(1000, 400);
	private final Font font = CONSOLAS12;
	private final Color background = new Color(239,241,248);
	private final Color selection = new Color(168,208,245);
	private final Color foreground = Color.BLACK;
	
	public RecordsTestWindow() {
        super("Tree-Table with Records - JUnit Test");
        
        final long start2 = System.currentTimeMillis();
        final Node root = new RecordsTest().getTreeroot();
        log.info("Elapsed " + (System.currentTimeMillis()-start2) + " ms. to create tree-root" );
        
        // Model
        final TreetableModel model = new TreetableModelImpl();
        model.setRoot(root);
        
        // Treetable
        final Treetable treetable = new Treetable(model);
        treetable.setRootVisible(true);
        treetable.setBasicUI(background, selection, foreground, font);
        treetable.setIntercellSpacing(new Dimension(0,0)); 
        treetable.setColumnWidthNonresizable(1, 75);
       
        final JScrollPane jScrollPane = new JScrollPane(treetable);
        jScrollPane.getViewport().setBackground(background);
        
       // JFrame
       addAs1x1(jScrollPane);
       setSize(size);
       setLocationRelativeTo(null);
       
       log.info("Elapsed " + (System.currentTimeMillis()-start0) + " ms." );
    }
	
}