package de.dbo.samples.gui.swing.treetable.impl.ref.x;

import de.dbo.samples.gui.swing.treetable.api.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.TreetableUI;
import de.dbo.samples.gui.swing.treetable.api.factory.Factory;
import de.dbo.samples.gui.swing.treetable.api.factory.FactoryManager;
import de.dbo.samples.gui.swing.treetable.api.guix.TreetableImpl;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.impl.Window;
import de.dbo.samples.gui.swing.treetable.impl.ref.RecordsTest;
import de.dbo.samples.gui.swing.treetable.impl.ref.TreetableUIImpl;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 
public class RecordsTestWindow extends Window {
	private static final long serialVersionUID = -5484087161360300717L;
	
	public static void main(final String[] args) {
		final Runnable gui = new Runnable() {
			@Override
			public final void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					log.error("Can't set the system Look-and-Feel", e);
				}
				new RecordsTestWindow().showup(0.6D);
			}
		};
		SwingUtilities.invokeLater(gui);
	}
	
	public RecordsTestWindow() {
        super("XTreetable with Records - JUnit Test Data - System LookAndFeel");
        
        final long start2 = System.currentTimeMillis();
        final Node root = new RecordsTest().getTreeroot();
        log.info("Elapsed " + (System.currentTimeMillis()-start2) + " ms. to create tree-root" );
        
        // Model
        final Factory factory = FactoryManager.getFactory("XReferenceImplementation.xml");
        final TreetableModel model = 
        		factory.newTreeTableModel(root, factory.newTreetableColumns());
        
        // Treetable
        final TreetableImpl treetable = new TreetableImpl(model,factory.getTreetableUI());
        final TreetableUI treetableUI = new TreetableUIImpl();
        treetable.setRootVisible(true);
        treetable.setIntercellSpacing(new Dimension(0,0)); 
        treetable.setColumnWidthNonresizable(1, 75);
       
        final JScrollPane jScrollPane = new JScrollPane(treetable);
        jScrollPane.getViewport().setBackground(treetableUI.getBackgroundTreetable());
        
        // JFrame
        this.add(jScrollPane, gbc1x1());
    }
	
	private static final GridBagConstraints gbc1x1() {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		return gbc;
	}
}