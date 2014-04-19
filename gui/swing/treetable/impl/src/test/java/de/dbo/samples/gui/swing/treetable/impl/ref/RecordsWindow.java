package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.Window;
import de.dbo.samples.gui.swing.treetable.api.factory.Factory;
import de.dbo.samples.gui.swing.treetable.api.factory.FactoryMgr;
import de.dbo.samples.gui.swing.treetable.api.gui.Treetable;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.RecordTreeGenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class RecordsWindow extends Window {
	private static final long serialVersionUID = 4489500964556705612L;
	
	public static void main(final String[] args) {
		final Runnable gui = new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					log.error("Can't use system Look-and-Feel",e);
				}
				new RecordsWindow().setVisible(true);
			}
		};
		SwingUtilities.invokeLater(gui);
	}
    
	private final Dimension size = new Dimension(1000, 400);
	private final Font font = CONSOLAS13;
	private final Color background = new Color(239,241,248);
	private final Color selection = new Color(168,208,245);
	private final Color foreground = Color.BLACK;
	
	public RecordsWindow() {
        super("Record Tree-Table - Reference Implementation");
        
        final long start1 = System.currentTimeMillis();
        final Factory factory = FactoryMgr.instance("ReferenceImplementation.xml");
        log.debug("Elapsed " + (System.currentTimeMillis()-start1) + " ms. to create factory" );

        final long start2 = System.currentTimeMillis();
        final Node root = new RecordTreeGenerator(factory, Records.list()).tree();
        log.debug("Elapsed " + (System.currentTimeMillis()-start2) + " ms. to create tree-root" );
        
        final Treetable treetable = new Treetable(factory.treeTableModel(root));
        treetable.setRootVisible(false);
        treetable.setBasicUI(background, selection, foreground, font);
        treetable.setIntercellSpacing(new Dimension(1,1)); 
        treetable.setColumnWidthNonresizable(1, 75);
        
        final JScrollPane jScrollPane = new JScrollPane(treetable);
        jScrollPane.getViewport().setBackground(treetable.getBackground());
        final JPanel pane = new JPanel();
        addAs1x1(pane, jScrollPane);
        addAs1x1(pane);
        
        // JFrame
        
        setSize(size);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        log.info("Elapsed " + (System.currentTimeMillis()-start0) + " ms." );
    }
	
}