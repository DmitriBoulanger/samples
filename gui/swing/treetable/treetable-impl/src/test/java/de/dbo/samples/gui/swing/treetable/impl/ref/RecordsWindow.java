package de.dbo.samples.gui.swing.treetable.impl.ref;

import static de.dbo.samples.gui.swing.treetable.api.Tools.menubar;
import static de.dbo.samples.gui.swing.treetable.api.Tools.addAs1x1;

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
				new RecordsWindow().showup(new Dimension(800,800));
			}
		});
	}
    
	private final Factory factory;
	private Node root;
	private TreetableModel treetableModel;
	private Treetable treetable;
	
	private final JPanel pane = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JButton reloadButton = button(" Reload ");
	private final JButton updateButton = button(" Update ");
	private final JButton expandButton = button(" Exapnd ");
	private final JButton collapseButton = button(" Collapse ");
	private final JButton clearButton = button(" Clear ");
	private final JTextField transactionIdLabel = label("Transaction ID:");
	private final JTextField transactionIdTextField = textfield(30);
	
	RecordsWindow() {
        super("Tree-Table with Records - Reference Implementation");
        
        final long start = System.currentTimeMillis();
        factory = FactoryMgr.instance("ReferenceImplementation.xml");
        elapsed(start, "creating tree-table factory" );
        
         // MenuBar
        final JMenuBar jMenuBar = menubar(3);
		jMenuBar.add(reloadButton);  
		jMenuBar.add(updateButton);  
		jMenuBar.add(expandButton);  
		jMenuBar.add(collapseButton);  
		jMenuBar.add(clearButton);  
		jMenuBar.add(transactionIdLabel);
		jMenuBar.add(transactionIdTextField);
        
        // Pane with scrolling
        pane.setBackground(BACKGROUND);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(BACKGROUND);
        scrollPane.getViewport().setView(null);;
        addAs1x1(pane, scrollPane);
        
        // Actions
        reloadButton.addActionListener(this);
        updateButton.addActionListener(this);
        expandButton.addActionListener(this);
        collapseButton.addActionListener(this);
        clearButton.addActionListener(this);
        
        // Frame
        setJMenuBar(jMenuBar);
        setContentAs1x1(pane);
        
        loadTreetable();
    }
	
	private final void clearTreetable() {
		transactionIdTextField.setText("");
		treetableModel = null;
		treetable = null;
		root = null;
		scrollPane.setViewportView(null);
		scrollPane.getViewport().revalidate();
		System.gc();
	}
	
	private final void loadTreetable() {
		final long start = System.currentTimeMillis();
		final List<Record> records = recordsForTransaction();
		root = new RecordTreeGenerator(factory, records).tree();
		elapsed(start, "creating tree-root");
		
		treetableModel = factory.treeTableModel(root);
		treetable = new Treetable(treetableModel);
		treetable.setRootVisible(false);
		treetable.setBasicUI(BACKGROUND, SELECTION, FOREGROUND, FONT);
		treetable.setIntercellSpacing(new Dimension(1, 1));
		treetable.setColumnWidthMin(0, 190);
		treetable.setColumnWidthNonresizable(1, 75);

		scrollPane.setViewportView(treetable);
		scrollPane.getViewport().revalidate();
		elapsed(start, "loading tree-table");
	}
	
	private final List<Record> recordsForTransaction() {
		final List<Record> records;
		final String transaction = transactionIdTextField.getText();
		if (null == transaction || 0 == transaction.trim().length()) {
			records = null;
		} else {
			final String transactionTrimmed = transaction.trim();
			if ("1".equals(transactionTrimmed)) {
				records = Records.list();
			} else if ("2".equals(transactionTrimmed)) {
				records = Records2.list();
			} else {
				records = null;
			}
		}
		return records;
	}
	
	
	@Override
	public final void actionPerformed(final ActionEvent e) {
		if (e.getSource()==reloadButton)  {
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					loadTreetable();
				}
			});
		} 
		else if  (e.getSource()==updateButton)  {
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
	
	
	
	protected JTextField textfield(int columns) {
		final JTextField jTextField = new JTextField();
		jTextField.setColumns(30);
		jTextField.setMinimumSize(new Dimension(500,20));
		return jTextField;
	}
	
	protected JTextField label(final String text) {
		final JTextField jTextFiled = new JTextField(text);
		jTextFiled.setBorder(new EmptyBorder(0,0,0,0));
		jTextFiled.setEditable(false);
		jTextFiled.setFocusable(false);
		jTextFiled.setOpaque(false);
		return jTextFiled;
	}
	
	protected final JButton button(final String text) {
		final JButton jButton = new JButton(text);
		jButton.setFocusable(false);
		jButton.setSize(120, 20);
		return jButton;
	}
	
	private static void elapsed(final long start, final String comment) {
		log.debug("Elapsed " + (System.currentTimeMillis() - start)+ " ms. " + (null!=comment? comment :""));
	}
	
	

	
}