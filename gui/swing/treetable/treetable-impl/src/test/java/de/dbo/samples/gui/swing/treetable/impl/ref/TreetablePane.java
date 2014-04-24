package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.factory.Factory;
import de.dbo.samples.gui.swing.treetable.api.gui.Treetable;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableColumns;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableUI;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;
import de.dbo.samples.gui.swing.treetable.api.records.RecordTreeGenerator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TreetablePane extends JPanel implements ActionListener {
	private static final long serialVersionUID = 3272112506751761942L;
	private static final Logger log = LoggerFactory.getLogger(TreetablePane.class);

	/* treetable factory and record provider */
	private final Factory factory;
	private final RecordProvider recordProvider;
	private final TreetableColumns treetableColumns;
	private final TreetableUI treetablePaneUI;
	
	/* final menu-bar components */
	private final JButton reloadButton;
	private final JButton updateButton ;
	private final JButton expadCollapseButton;
	private final JButton clearButton;
	private final JTextField transactionIdLabel = label(" Transaction ID:");
	private final JTextField transactionIdTextField = textfield(30
			,"Transaction ID (should be available)");
	private final JTextField recordCounterLabel = label(" Record counter:");
	private final JTextField recordCounterTextField = space(4);
	
	/* final basic pane with internal scrolling */
    private final JPanel controlsPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel statusPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
	private final JScrollPane scrollPane = new JScrollPane();
	
	/* dynamic data */
	private List<Record> records = null;
	
	/* dynamic treetable objects and components */
	private TreetableModel treetableModel = null;
	private Treetable treetable = null;
	
	// status indices
	private static final int UNLOCKED = 0;
	private static final int LOCKED = 1;
	private static final int DONE = 2;
	


	// initial UI state
	private boolean expanded = false;
	
	/**
	 * GUI with childless treetable-root.
	 * Initial status is UNLOCKED, records = null
	 */
	protected TreetablePane(Factory factory) {
        this.factory = factory;
        this.recordProvider = factory.getRecordProvider();
        this.treetablePaneUI = new TreetablePaneUIImpl(); //TODO from factory
        
        reloadButton = button(treetablePaneUI.getIconRefresh()
    			,"Reload all records for the specified transaction");
    	updateButton = button(treetablePaneUI.getIconUpdate()
    			,"Update already available records for the specified transaction");
    	expadCollapseButton = button(treetablePaneUI.getIconExpand()
    			,"Exapnd/collapse all tree-node");
    	clearButton = button(treetablePaneUI.getIconClear()
    			,"Clean-up record-provider and UI");
        
        // menu-bar
        controlsPane.setOpaque(false);
        controlsPane.add(reloadButton);  
        controlsPane.add(updateButton);  
        controlsPane.add(expadCollapseButton);   
        controlsPane.add(clearButton); 
        controlsPane.add(transactionIdLabel);
        controlsPane.add(transactionIdTextField);
      
        statusPane.setOpaque(false);
        statusPane.add(recordCounterLabel);
        statusPane.add(recordCounterTextField);
        
        
        // pane with internal scrolling
        setBackground(treetablePaneUI.getBackground());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(treetablePaneUI.getBackground());
        scrollPane.getViewport().setView(null);
        addAs1x1(this, scrollPane);
         
        // actions
        reloadButton.addActionListener(this);
        updateButton.addActionListener(this);
        expadCollapseButton.addActionListener(this);
        clearButton.addActionListener(this);
        transactionIdTextField.addActionListener(this);
        
       
        // initialize treetable and UI
        treetableColumns = factory.getTreetableColumns();
        loadTreetable();
        setStatus(UNLOCKED);
    }
	
	public JPanel getControlsPane() {
		return controlsPane;
	}
	
	public JPanel getStatusPane() {
		return statusPane;
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
					expadCollapseButton.setIcon(treetablePaneUI.getIconExpand());
				}
				else {
					treetable.expandAll();
					expanded = true;
					expadCollapseButton.setIcon(treetablePaneUI.getIconCollapse());
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
					expadCollapseButton.setIcon(treetablePaneUI.getIconExpand());
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
		log.trace("elapsed " + (System.currentTimeMillis() - start) + " ms. creating tree-root"); 
		
		// treetable
		treetableModel = factory.newTreeTableModel(root);
		treetable = new Treetable(treetableModel);
		treetable.setRootVisible(false);
		treetable.setBasicUI(treetablePaneUI);
		treetable.setIntercellSpacing(new Dimension(1, 1));
		treetableColumns.arrangeColumnWidths(treetable);

		// scrolling
		scrollPane.setViewportView(treetable);
		scrollPane.getViewport().revalidate();
		log.trace("elapsed " + (System.currentTimeMillis() - start) + " ms. loading tree-table"); 
	}
	
	private final void setStatus(final int status) {
		final Component icon;
		switch(status) {
		case 0: 
			icon = treetablePaneUI.getIconLabelUnlocked();
			setEnabled(true);
			break;
		case 1: 
			icon = treetablePaneUI.getIconLabelLocked();
			setEnabled(false);
			break;
		case 2: 
			icon= null!=recordProvider.recordCounter()? treetablePaneUI.getIconLabelDone()
					: null;
			setEnabled(true);
			break;
			
		default:
			icon =  treetablePaneUI.getIconLabelUnlocked();
			setEnabled(true);
		}
		
		scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, icon);
		final Integer recordCounter = recordProvider.recordCounter();
		recordCounterTextField.setText("" + recordCounter);
	}
	
	
	// HELPERS
	
	protected static JTextField textfield(final int columns, final String tooltip) {
		final JTextField jTextField = new JTextField();
		jTextField.setColumns(columns);
		jTextField.setToolTipText(tooltip);
		return jTextField;
	}
	
	protected static JTextField space(final int columns) {
		final JTextField jTextField = new JTextField();
		jTextField.setColumns(columns);
		jTextField.setBorder(new EmptyBorder(0,0,0,0));
		jTextField.setEditable(false);
		jTextField.setFocusable(false);
		jTextField.setOpaque(false);
		return jTextField;
	}
	
	protected static JTextField info(Dimension size) {
		final JTextField jTextField = new JTextField();
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
	
	protected static final JButton button(final ImageIcon icon, final String tooltip) {
		final JButton jButton = new JButton(icon);
		jButton.setToolTipText(tooltip);
		jButton.setFocusable(false);
		return jButton;
	}
	
	protected static final void addAs1x1(final JComponent parent, final JComponent componet) {
		parent.setLayout(gbl1x1());
		parent.add(componet, gbc1x1());
	}

	protected static final GridBagConstraints gbc1x1() {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		return gbc;
	}
	
	protected static final GridBagLayout gbl1x1() {
		final GridBagLayout gbl = new GridBagLayout();
		return gbl;
	}
}