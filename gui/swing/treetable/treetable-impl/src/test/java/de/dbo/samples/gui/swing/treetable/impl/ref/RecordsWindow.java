package de.dbo.samples.gui.swing.treetable.impl.ref;

import static de.dbo.samples.gui.swing.treetable.api.WindowTools.menubar;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.addAs1x1;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.elapsed;

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
import javax.swing.border.EmptyBorder;

public final class RecordsWindow extends Window {
	private static final long serialVersionUID = 4489500964556705612L;
	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setLookAndFeel();
				new RecordsWindow().showup(new Dimension(800,500));
			}
		});
	}
    
	/* final basic pane and treetable-components */
	private final JPanel pane = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JButton reloadButton = button(" Reload ");
	private final JButton updateButton = button(" Update ");
	private final JButton expandButton = button(" Exapnd ");
	private final JButton collapseButton = button(" Collapse ");
	private final JButton clearButton = button(" Clear ");
	private final JTextField transactionIdLabel = label("Transaction ID:");
	private final JTextField transactionIdTextField = textfield(30);
	
	/* treetable factory */
	private final Factory factory;
	
	/* dynamic data */
	private List<Record> records;
	
	/* dynamic treetable objects and components */
	private TreetableModel treetableModel;
	private Treetable treetable;
	
	/**
	 * GUI with childless treetable-root
	 */
	RecordsWindow() {
        super("Tree-Table with Records - Reference Implementation");
        
        final long start = System.currentTimeMillis();
        factory = FactoryMgr.instance("ReferenceImplementation.xml");
        elapsed(start, "creating tree-table factory" );
        
         // menu-bar
        final JMenuBar jMenuBar = menubar(3);
		jMenuBar.add(reloadButton);  
		jMenuBar.add(updateButton);  
		jMenuBar.add(expandButton);  
		jMenuBar.add(collapseButton);  
		jMenuBar.add(clearButton);  
		jMenuBar.add(transactionIdLabel);
		jMenuBar.add(transactionIdTextField);
        
        // pane with scrolling
        pane.setBackground(BACKGROUND);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(BACKGROUND);
        scrollPane.getViewport().setView(null);;
        addAs1x1(pane, scrollPane);
        
        // actions
        reloadButton.addActionListener(this);
        updateButton.addActionListener(this);
        expandButton.addActionListener(this);
        collapseButton.addActionListener(this);
        clearButton.addActionListener(this);
        
        // frame
        setJMenuBar(jMenuBar);
        setContentAs1x1(pane);
        
        // treetable
        loadTreetable();
    }
	
	private final void clearTreetable() {
		transactionIdTextField.setText("");
		treetableModel = null;
		treetable = null;
		records = null;
		scrollPane.setViewportView(null);
		scrollPane.getViewport().revalidate();
		System.gc();
	}
	
	/**
	 * loads records as tree-table into the scroll-pane.
	 * 
	 * 
	 * @see #records
	 * @see #scrollPane
	 */
	private final void loadTreetable() {
		// root
		final long start = System.currentTimeMillis();
		final Node root = new RecordTreeGenerator(factory, records).tree();
		elapsed(start, "creating tree-root");
		
		// treetable
		treetableModel = factory.treeTableModel(root);
		treetable = new Treetable(treetableModel);
		treetable.setRootVisible(false);
		treetable.setBasicUI(BACKGROUND, SELECTION, FOREGROUND, FONT);
		treetable.setIntercellSpacing(new Dimension(1, 1));
		treetable.setColumnWidthMin(0, 190);
		treetable.setColumnWidthNonresizable(1, 75);

		// scrolling
		scrollPane.setViewportView(treetable);
		scrollPane.getViewport().revalidate();
		elapsed(start, "loading tree-table");
	}
	 
	private final List<Record> recordsForTransaction() {
		final String transaction = transactionIdTextField.getText();
		if (null == transaction || 0 == transaction.trim().length()) {
			return null;
		}

		final String transactionTrimmed = transaction.trim();
		if ("1".equals(transactionTrimmed)) {
			return Records.list();
		} else if ("2".equals(transactionTrimmed)) {
			return Records2.list();
		} else {
			return null;
		}
	}
	
	private final List<Record> recordsForTransactionUpdate() {
		final String transaction = transactionIdTextField.getText();
		if (null == transaction || 0 == transaction.trim().length()) {
			return null;
		}

		final List<Record> recordsUpdate = recordsForTransaction();
		if (null==records) {
			return recordsUpdate;
		} 
		else if (null!=recordsUpdate) {
			records.addAll(recordsUpdate);
			return records;
		} else {
			return records;
		}

	}
	
	@Override
	public final void actionPerformed(final ActionEvent e) {
		if (e.getSource()==reloadButton)  {
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					records = recordsForTransaction();
					loadTreetable();
				}
			});
		} 
		else if  (e.getSource()==updateButton)  {
			records = recordsForTransactionUpdate();
			loadTreetable();
			System.err.println("update");
		} 
		else if  (e.getSource()==expandButton)  {
			if (null!=treetable) {
				treetable.expandAll();
			}
		} 
		else if  (e.getSource()==collapseButton)  {
			if (null!=treetable) {
				treetable.collapseAll();
			}
		} 
		else if (e.getSource()==clearButton)  {
			clearTreetable();
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					loadTreetable();
				}
			});
		}
	}
	
	// HELPERS
	
	protected static JTextField textfield(int columns) {
		final JTextField jTextField = new JTextField();
		jTextField.setColumns(30);
		jTextField.setMinimumSize(new Dimension(500,20));
		return jTextField;
	}
	
	protected static JTextField label(final String text) {
		final JTextField jTextFiled = new JTextField(text);
		jTextFiled.setBorder(new EmptyBorder(0,0,0,0));
		jTextFiled.setEditable(false);
		jTextFiled.setFocusable(false);
		jTextFiled.setOpaque(false);
		return jTextFiled;
	}
	
	protected static final JButton button(final String text) {
		final JButton jButton = new JButton(text);
		jButton.setFocusable(false);
		jButton.setSize(new Dimension(120, 20));
		return jButton;
	}
	
}