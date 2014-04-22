package de.dbo.samples.gui.swing.treetable.impl.ref;

import static de.dbo.samples.gui.swing.treetable.api.WindowTools.*;

import de.dbo.samples.gui.swing.treetable.api.Window;
import de.dbo.samples.gui.swing.treetable.api.factory.Factory;
import de.dbo.samples.gui.swing.treetable.api.factory.FactoryMgr;
import de.dbo.samples.gui.swing.treetable.api.gui.Treetable;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;
import de.dbo.samples.gui.swing.treetable.api.records.RecordTreeGenerator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.springframework.expression.spel.ast.Ternary;

public final class RecordsWindow extends Window {
	private static final long serialVersionUID = 4489500964556705612L;
	
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setLookAndFeel();
				new RecordsWindow().showup(new Dimension(860,500));
			}
		});
	}
	
	public static final Factory factory() {
		final long start = System.currentTimeMillis();
//        final Factory factory = FactoryMgr.instance("ReferenceImplementation.properties");
        final Factory factory = FactoryMgr.instance("ReferenceImplementation.xml");
        elapsed(start, "creating tree-table factory" );
        return factory;
	}
	
	/* final basic pane with scrolling and menu-bar components */
	private final JPanel pane = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JButton reloadButton = button(" Reload ");
	private final JButton updateButton = button(" Update ");
	private final JButton expandButton = button(" Exapnd ");
	private final JButton collapseButton = button(" Collapse ");
	private final JButton clearButton = button(" Clear ");
	private final JTextField transactionIdLabel = label("   Transaction ID: ");
	private final JTextField transactionIdTextField = textfield(30,new Dimension(500,20));
	private final JTextField recordCounterLabel = label("   Record counter: ");
	private final JTextField recordCounterTextField = info(6,new Dimension(40,20));
	
	// status icons
	private final JLabel iconDone = createIconLabel(this,"icons/done.png");
	private final JLabel iconRunning = createIconLabel(this,"icons/running.png");
	
	/* final treetable factory and record provider */
	private final Factory factory = factory();
	private final RecordProvider recordProvider = factory.getRecordProvider();
	
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
 
         // menu-bar
        final JMenuBar jMenuBar = menubar(3);
		jMenuBar.add(reloadButton);  
		jMenuBar.add(updateButton);  
		jMenuBar.add(expandButton);  
		jMenuBar.add(collapseButton);  
		jMenuBar.add(clearButton);  
		jMenuBar.add(transactionIdLabel);
		jMenuBar.add(transactionIdTextField);
		jMenuBar.add(recordCounterLabel);
		jMenuBar.add(recordCounterTextField);
        
        // pane with scrolling
        pane.setBackground(BACKGROUND);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(BACKGROUND);
        scrollPane.getViewport().setView(null);
        addAs1x1(pane, scrollPane);
         
        // actions
        reloadButton.addActionListener(this);
        updateButton.addActionListener(this);
        expandButton.addActionListener(this);
        collapseButton.addActionListener(this);
        clearButton.addActionListener(this);
        transactionIdTextField.addActionListener(this);
        
        // frame
        setJMenuBar(jMenuBar);
        setContentAs1x1(pane);
        
        // treetable and UI
        loadTreetable();
        refreshRecordCounter();
        setStatus(EMPTY);
    }
	
	@Override
	public final void actionPerformed(final ActionEvent event) {
		recordProvider.setTransaction(transactionIdTextField.getText());
		
		if  (null==event || null==event.getSource())  {
			
			log.error("SYSTEM ERROR: event is null or it has no source " + event);
		}
		
		// quick actions, no status update
		
		else if  (event.getSource()==expandButton)  {
			if (null!=treetable) {
				treetable.expandAll();
			}
		} 
		else if  (event.getSource()==collapseButton)  {
			if (null!=treetable) {
				treetable.collapseAll();
			}
		} 
		else if (event.getSource()==transactionIdTextField)  {
			recordProvider.setTransaction(transactionIdTextField.getText());
		} 
		
		// hard actions
		
		else if (event.getSource()==clearButton)  {
			if (null==records) {
				return;
			}
			setStatus(RUNNING);
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					clearTreetable();
					loadTreetable();
					refreshRecordCounter();
					setStatus(EMPTY);
				}
			});
		} 
		
	    else if (event.getSource()==reloadButton)  {
	    	setStatus(RUNNING);
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					records = recordProvider.transactionRecords();
					refreshRecordCounter();
					loadTreetable();
					setStatus(DONE);
				}
			});
		} 
		else if  (event.getSource()==updateButton)  {
			setStatus(RUNNING);
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					records = recordProvider.transactionRecordsUpdate();
					refreshRecordCounter();
					loadTreetable();
					setStatus(DONE);
				}
			});
		} 

		// Oops!
		else {
			log.error("SYSTEM ERROR: unexpected event-source " + event.getSource());
		}
	}
	
	private final void refreshRecordCounter() {
		final Integer recordCounter = recordProvider.recordCounter();
		recordCounterTextField.setText("" + recordCounter);
	}
	
	private final void clearTreetable() {
		treetableModel = null;
		treetable = null;
		records = null;
		transactionIdTextField.setText("");
		scrollPane.setViewportView(null);
		scrollPane.getViewport().revalidate();
		recordProvider.clear();
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
	
	private static final int EMPTY = 0;
	private static final int RUNNING = 1;
	private static final int DONE = 2;
	
	private final void setStatus(final int status) {
		final Component icon;
		switch(status) {
		case 0: 
			icon = null;
			pane.setEnabled(true);
			break;
		case 1: 
			icon= iconRunning;
			pane.setEnabled(false);
			break;
		case 2: 
			icon= null!=recordProvider.recordCounter()? 
					iconDone: null;
			pane.setEnabled(true);
			break;
		default:
			icon =  null;
		}
		
		if (null!=icon) {
			scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, icon);
		} else {
			scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, icon);
		}
	}
	 
	// HELPERS
	
	protected static JTextField textfield(int columns, final Dimension size) {
		final JTextField jTextField = new JTextField();
		jTextField.setColumns(columns);
		jTextField.setPreferredSize(size);
		return jTextField;
	}
	
	protected static JTextField info(int columns, final Dimension size) {
		final JTextField jTextField = new JTextField();
		jTextField.setColumns(columns);
		jTextField.setPreferredSize(size);
		jTextField.setBorder(new EmptyBorder(0,0,0,0));
		jTextField.setEditable(false);
		jTextField.setFocusable(false);
		jTextField.setOpaque(false);
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