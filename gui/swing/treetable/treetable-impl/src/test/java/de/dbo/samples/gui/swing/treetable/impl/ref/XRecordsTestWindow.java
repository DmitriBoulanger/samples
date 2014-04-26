package de.dbo.samples.gui.swing.treetable.impl.ref;

import static de.dbo.samples.gui.swing.treetable.impl.ref.TreetableUIImpl.BACKGROUND;
import static de.dbo.samples.gui.swing.treetable.impl.ref.TreetableUIImpl.FONT;
import static de.dbo.samples.gui.swing.treetable.impl.ref.TreetableUIImpl.FOREGROUND;
import static de.dbo.samples.gui.swing.treetable.impl.ref.TreetableUIImpl.SELECTION;

import de.dbo.samples.gui.swing.treetable.api.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.factory.Factory;
import de.dbo.samples.gui.swing.treetable.api.factory.FactoryManager;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.xgui.TreetableImpl;
import de.dbo.samples.gui.swing.treetable.impl.Window;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
 
public class XRecordsTestWindow extends Window {
	private static final long serialVersionUID = -5484087161360300717L;
	
	public static void main(final String[] args) {
		final Runnable gui = new Runnable() {
			@Override
			public final void run() {
				setLookAndFeel();
				new XRecordsTestWindow().showup();
			}
		};
		SwingUtilities.invokeLater(gui);
	}
	
	public XRecordsTestWindow() {
        super("XTreetable with Records - JUnit Test Data");
        
        final long start2 = System.currentTimeMillis();
        final Node root = new RecordsTest().getTreeroot();
        log.info("Elapsed " + (System.currentTimeMillis()-start2) + " ms. to create tree-root" );
        
        // Model
        final Factory factory = FactoryManager.getFactory("XReferenceImplementation.xml");
        final TreetableModel model = 
        		factory.newTreeTableModel(root, factory.newTreetableColumns());
        
        // Treetable
        final TreetableImpl treetable = new TreetableImpl(model);
        treetable.setRootVisible(true);
        treetable.setBasicUI(BACKGROUND, SELECTION, FOREGROUND, FONT);
        treetable.setIntercellSpacing(new Dimension(0,0)); 
        treetable.setColumnWidthNonresizable(1, 75);
       
        final JScrollPane jScrollPane = new JScrollPane(treetable);
        jScrollPane.getViewport().setBackground(BACKGROUND);
        
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