package de.dbo.samples.gui.swing.treetable.impl.ref;

import static de.dbo.samples.gui.swing.treetable.api.WindowTools.addAs1x1;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.elapsed;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.gbc1xManyLeft;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.gbc1xManyRight;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.gbl1xMany;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.DONE;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.ICON_CLEAR;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.ICON_COLLAPSE;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.ICON_DONE;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.ICON_EXPAND;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.ICON_LOCKED;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.ICON_REFRESH;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.ICON_UNLOCKED;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.ICON_UPDATE;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.LOCKED;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.UNLOCKED;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.button;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.factory;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.label;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.space;
import static de.dbo.samples.gui.swing.treetable.impl.ref.RecordsWindowResources.textfield;

import de.dbo.samples.gui.swing.treetable.api.Window;
import de.dbo.samples.gui.swing.treetable.api.factory.Factory;
import de.dbo.samples.gui.swing.treetable.api.gui.Treetable;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableColumns;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;
import de.dbo.samples.gui.swing.treetable.api.records.RecordTreeGenerator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

abstract class RecordsWindowAbstraction extends Window {
	protected static final long serialVersionUID = 4489500964556705612L;
	
	/* treetable factory and record provider */
	private final Factory factory;
	private final RecordProvider recordProvider;
	
	/* final menu-bar components */
	private final JButton reloadButton = button(ICON_REFRESH
			,"Reload all records for the specified transaction");
	private final JButton updateButton = button(ICON_UPDATE
			,"Update already available records for the specified transaction");
	private final JButton expadCollapseButton = button(ICON_EXPAND
			,"Exapnd/collapse all tree-node");
	private final JButton clearButton = button(ICON_CLEAR
			,"Clean-up record-provider and UI");
	private final JTextField transactionIdLabel = label(" Transaction ID:");
	private final JTextField transactionIdTextField = textfield(30
			,"Transaction ID (should be available)");
	private final JTextField recordCounterLabel = label(" Record counter:");
	private final JTextField recordCounterTextField = space(4);
	
	/* final basic pane with internal scrolling */
	private final JPanel pane = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	
	/* dynamic data */
	private List<Record> records = null;
	
	/* dynamic treetable objects and components */
	private TreetableModel treetableModel = null;
	private Treetable treetable = null;
	private final TreetableColumns treetableColumns;
	
	// initial UI state
	private boolean expanded = false;
	
	/**
	 * GUI with childless treetable-root.
	 * Initial status is UNLOCKED, records = null
	 */
	protected RecordsWindowAbstraction(final String ctx, final String title) {
        super(title + " - " + ctx);
        factory = factory(ctx);
        recordProvider = factory.getRecordProvider();
        
        // menu-bar
        final JPanel controlsPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlsPane.setOpaque(false);
        controlsPane.add(reloadButton);  
        controlsPane.add(updateButton);  
        controlsPane.add(expadCollapseButton);   
        controlsPane.add(clearButton); 
        controlsPane.add(transactionIdLabel);
        controlsPane.add(transactionIdTextField);
        final JPanel statusPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        statusPane.setOpaque(false);
        statusPane.add(recordCounterLabel);
        statusPane.add(recordCounterTextField);
        
        final JMenuBar jMenuBar =  new JMenuBar();
        jMenuBar.setLayout(gbl1xMany());
        int x = 0;
        jMenuBar.add(controlsPane,gbc1xManyLeft(x++,0));
        jMenuBar.add(statusPane,gbc1xManyRight(x++,0));

        // pane with internal scrolling
        pane.setBackground(BACKGROUND);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(BACKGROUND);
        scrollPane.getViewport().setView(null);
        addAs1x1(pane, scrollPane);
         
        // actions
        reloadButton.addActionListener(this);
        updateButton.addActionListener(this);
        expadCollapseButton.addActionListener(this);
        clearButton.addActionListener(this);
        transactionIdTextField.addActionListener(this);
        
        // frame
        setJMenuBar(jMenuBar);
        setContentAs1x1(pane);
        
        // initialize treetable and UI
        treetableColumns = factory.getTreetableColumns();
        loadTreetable();
        setStatus(UNLOCKED);
    }
	
	@Override
	public final void actionPerformed(final ActionEvent event) {
		recordProvider.setTransaction(transactionIdTextField.getText());
		if (null==recordProvider.getTransaction()) {
			return;
		}
		
		if  (null==event || null==event.getSource())  {
			log.error("SYSTEM ERROR: event is null or it has no source " + event);
		}
		
		// quick actions, no status update
		
		else if (event.getSource()==expadCollapseButton)  {
			if (null!=treetable) {
				if (expanded) {
					treetable.collapseAll();
					expanded = false;
					expadCollapseButton.setIcon(ICON_EXPAND);
				}
				else {
					treetable.expandAll();
					expanded = true;
					expadCollapseButton.setIcon(ICON_COLLAPSE);
				}
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
			setStatus(LOCKED);
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					treetableColumns.setColumnWidths(treetable);
					clearTreetable();
					loadTreetable();
					expanded = false;
					expadCollapseButton.setIcon(ICON_EXPAND);
					setStatus(UNLOCKED);
				}
			});
		} 
		
	    else if (event.getSource()==reloadButton)  {
	    	setStatus(LOCKED);
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					records = recordProvider.transactionRecords();
					treetableColumns.setColumnWidths(treetable);
					loadTreetable();
					if (expanded) {
						treetable.expandAll();
					}
					setStatus(DONE);
				}
			});
		} 
		else if  (event.getSource()==updateButton)  {
			setStatus(LOCKED);
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					records = recordProvider.transactionRecordsUpdate();
					treetableColumns.setColumnWidths(treetable);
					loadTreetable();
					if (expanded) {
						treetable.expandAll();
					}
					setStatus(DONE);
				}
			});
		} 

		// Oops!
		else {
			log.error("SYSTEM ERROR: unexpected event-source " + event.getSource());
		}
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
	 * loads records as a tree-table into the view of the scroll-pane.
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
		treetableModel = factory.newTreeTableModel(root);
		treetable = new Treetable(treetableModel);
		treetable.setRootVisible(false);
		treetable.setBasicUI(BACKGROUND, SELECTION, FOREGROUND, FONT);
		treetable.setIntercellSpacing(new Dimension(1, 1));
		treetableColumns.arrangeColumnWidths(treetable);

		// scrolling
		scrollPane.setViewportView(treetable);
		scrollPane.getViewport().revalidate();
		elapsed(start, "loading tree-table");
	}
	
	private final void setStatus(final int status) {
		final Component icon;
		switch(status) {
		case 0: 
			icon = ICON_UNLOCKED;
			pane.setEnabled(true);
			break;
		case 1: 
			icon= ICON_LOCKED;
			pane.setEnabled(false);
			break;
		case 2: 
			icon= null!=recordProvider.recordCounter()? ICON_DONE: null;
			pane.setEnabled(true);
			break;
			
		default:
			icon =  ICON_UNLOCKED;
			pane.setEnabled(true);
		}
		
		scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, icon);
		final Integer recordCounter = recordProvider.recordCounter();
		recordCounterTextField.setText("" + recordCounter);
	}
}