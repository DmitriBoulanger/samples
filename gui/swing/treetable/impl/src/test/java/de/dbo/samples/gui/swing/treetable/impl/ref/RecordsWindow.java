package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.Window;
import de.dbo.samples.gui.swing.treetable.api.factory.Factory;
import de.dbo.samples.gui.swing.treetable.api.factory.FactoryMgr;
import de.dbo.samples.gui.swing.treetable.api.gui.Treetable;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordTreeGenerator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public final class RecordsWindow extends Window {
	private static final long serialVersionUID = 4489500964556705612L;
	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setLookAndFeel();
				new RecordsWindow().showup(new Dimension(800,800));
			}
		});
	}
    
	private final Factory factory;
	private Node root;
	private TreetableModel treetableModel;
	private Treetable treetable;
	
	private final JPanel pane = new JPanel();
	private final JButton reloadButton = button(" Reload ");
	private final JButton updateButton = button(" Update ");
	private final JButton expandButton = button(" Exapnd ");
	private final JButton collapseButton = button(" Collapse ");
	private final JButton clearButton = button(" Clear ");
	private final JTextField transactionIdLabel = label("Transaction ID:");
	private final JTextField transactionIdTextField = textfield(30);
	
	RecordsWindow() {
        super("Tree-Table with Records - Reference Implementation");
        
        final long start1 = System.currentTimeMillis();
        factory = FactoryMgr.instance("ReferenceImplementation.xml");
        log.debug("Elapsed " + (System.currentTimeMillis()-start1) + " ms. to create factory" );

//        loadTreetable(root);
        
        // JMenuBar
        final JMenuBar jMenuBar = menubar();
		jMenuBar.add(reloadButton);  
		jMenuBar.add(updateButton);  
		jMenuBar.add(expandButton);  
		jMenuBar.add(collapseButton);  
		jMenuBar.add(clearButton);  
		jMenuBar.add(transactionIdLabel);
		jMenuBar.add(transactionIdTextField);
        setJMenuBar(jMenuBar);
        
        // JFrame
//        setContentBackgroud(BACKGROUND);
        pane.setBackground(BACKGROUND);
        addAs1x1(pane);
        
        // Actions
        reloadButton.addActionListener(this);
        updateButton.addActionListener(this);
        expandButton.addActionListener(this);
        collapseButton.addActionListener(this);
        clearButton.addActionListener(this);
    }
	
	private final void loadTreetable() {
		    final long start = System.currentTimeMillis();
		    final List<Record> records;
		    final String transaction = transactionIdTextField.getText();
		    if (null==transaction || 0==transaction.trim().length()) {
		    	records = Records.list();
		    } else {
		    	records = Records2.list();
		    }
	        root = new RecordTreeGenerator(factory, records).tree();
	        log.debug("Elapsed " + (System.currentTimeMillis()-start) + " ms. to create tree-root" );
		    treetableModel = factory.treeTableModel(root);
	        treetable = new Treetable(treetableModel);
	        treetable.setRootVisible(false);
	        treetable.setBasicUI(BACKGROUND, SELECTION, FOREGROUND, FONT);
	        treetable.setIntercellSpacing(new Dimension(1,1)); 
	        treetable.setColumnWidthMin(0, 190);
	        treetable.setColumnWidthNonresizable(1, 75);

	        final JScrollPane jScrollPane = new JScrollPane();
	        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        jScrollPane.setViewportView(treetable);
	        jScrollPane.getViewport().setBackground(BACKGROUND);
	        addAs1x1(pane, jScrollPane);
	       
	}
	
	
	@Override
	public final void actionPerformed(final ActionEvent e) {
		if (e.getSource()==reloadButton)  {
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					final long start = System.currentTimeMillis();
					pane.removeAll();
					loadTreetable();
					validate();
					log.debug("Elapsed " + (System.currentTimeMillis()-start) + " ms. to reload" );
				}
			});
		} 
		else if  (e.getSource()==updateButton)  {
			System.err.println("update");
		} 
		else if  (e.getSource()==expandButton)  {
			treetable.expandAll();
		} 
		else if  (e.getSource()==collapseButton)  {
			treetable.collapseAll();
		} 
		else if (e.getSource()==clearButton)  {
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					transactionIdTextField.setText("");
					root = null;
					pane.removeAll();
					getContentPane().repaint();
					getContentPane().validate();
					System.gc();
				}
			});
		}
	}
	

	
}