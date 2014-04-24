package de.dbo.samples.gui.swing.treetable.impl.data;

import static de.dbo.samples.gui.swing.treetable.impl.ref.TreetableUIImpl.BACKGROUND;
import static de.dbo.samples.gui.swing.treetable.impl.ref.TreetableUIImpl.FONT;
import static de.dbo.samples.gui.swing.treetable.impl.ref.TreetableUIImpl.FOREGROUND;
import static de.dbo.samples.gui.swing.treetable.impl.ref.TreetableUIImpl.SELECTION;

import de.dbo.samples.gui.swing.treetable.api.Window;
import de.dbo.samples.gui.swing.treetable.api.gui.Treetable;
import de.dbo.samples.gui.swing.treetable.api.records.Node;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class DataWindow extends Window {
	private static final long serialVersionUID = 4489500964556705612L;

	public static void main(final String[] args) {
		final Runnable gui = new Runnable() {
			@Override
			public void run() {
				setLookAndFeel();
				new DataWindow().showup();
			}
		};
		SwingUtilities.invokeLater(gui);
	}
	
	DataWindow() {
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
        add(jScrollPane, gbc1x1());
        setPreferredSize(new Dimension(800,700));
    }
	
	private static final GridBagConstraints gbc1x1() {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		return gbc;
	}
	
}