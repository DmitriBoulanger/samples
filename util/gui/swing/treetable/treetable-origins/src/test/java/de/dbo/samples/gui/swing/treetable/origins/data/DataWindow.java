package de.dbo.samples.gui.swing.treetable.origins.data;

import de.dbo.samples.gui.swing.treetable.api.gui.TreetableImpl;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.impl.Window;
import de.dbo.samples.gui.swing.treetable.impl.ref.TreetableUIImpl;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 * Using plain Treetable-API (no Spring)
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class DataWindow extends Window {
	private static final long serialVersionUID = 4489500964556705612L;

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(	new Runnable() {
			@Override
			public void run() {
				try {
					javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					log.error("Can't set system Look-and-Feel", e);
				}
				new DataWindow().showup();
			}
		});
	}
	
	DataWindow() {
        super("Treetable sample - System LF  (http://www.hameister.org/JavaSwingTreeTable.html)");
         
        final long start = System.currentTimeMillis();
        final Node root = DataStructure.treeroot();
        log.info("elapsed " +(System.currentTimeMillis()-start) + " ms. creating tree-root" );
        
        final TreetableImpl treetable = new TreetableImpl(new DataTreetableModel(root),null);
        treetable.setRootVisible(false);
        treetable.setIntercellSpacing(new Dimension(0, 0)); 
        treetable.setColumnWidthNonresizable(1, 120);
        treetable.setColumnWidthNonresizable(2, 280);
       
        final JScrollPane jScrollPane = new JScrollPane(treetable);
        jScrollPane.getViewport().setBackground(new TreetableUIImpl().getBackgroundTreetable());
        
        // JFrame
        final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
        add(jScrollPane, gbc);
        setPreferredSize(new Dimension(800,700));
    }
}