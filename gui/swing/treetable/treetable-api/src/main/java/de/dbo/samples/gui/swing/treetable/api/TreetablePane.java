package de.dbo.samples.gui.swing.treetable.api;

import de.dbo.samples.gui.swing.treetable.api.factory.Factory;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableImpl;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;
import de.dbo.samples.gui.swing.treetable.api.records.NodeGenerator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default Treetable-pane.
 * It consists of pane with tree-records (this), controls-pane
 * and status-pane. An instance can performs self-deployment
 * to a parent component.
 * 
 * @see #selfDeployTo(JPanel)
 * @see #selfDeployTo(JFrame, boolean)
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public final class TreetablePane extends JPanel implements ActionListener {
	private static final long serialVersionUID = 3272112506751761942L;
	private static final Logger log = LoggerFactory.getLogger(TreetablePane.class);

	/* treetable factory and record provider */
	private final Factory factory;
	private final RecordProvider recordProvider;
	private final TreetableColumns treetableColumns;
	private final TreetableUI treetableUI;
	
	/* control-pane components */
	private final JButton reloadButton;
	private final JButton updateButton ;
	private final JButton expadCollapseButton;
	private final JButton clearButton;
	private final JTextField transactionIdLabel = label(" Transaction ID:");
	private final JTextField transactionIdTextField = textfield(30
			,"Transaction ID (should be available)");
	
	/* status-pane components */
	private final JTextField recordCounterLabel = label(" Record counter:");
	private final JTextField recordCounterTextField = space(4);
	
	/* left-pane with buttons and other controls */
    private final JPanel controlsPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    /* right-pane with record-status */
    private final JPanel statusPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    /* scrolling for this pane */
	private final JScrollPane scrollPane = new JScrollPane();
	
	/* dynamic data from record-provider */
	private List<Record> records = null;
	
	/* dynamic treetable components */
	private TreetableModel treetableModel = null;
	private TreetableImpl treetable = null;
	
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
	protected TreetablePane(final Factory factory) {
        this.factory = factory;
        this.recordProvider = factory.newRecordProvider();
        this.treetableUI = factory.getTreetableUI();
        
        reloadButton = button(treetableUI.getIconRefresh()
    			,"Reload all records for the specified transaction");
    	updateButton = button(treetableUI.getIconUpdate()
    			,"Update already available records for the specified transaction");
    	expadCollapseButton = button(treetableUI.getIconExpand()
    			,"Exapnd/collapse all tree-node");
    	clearButton = button(treetableUI.getIconClear()
    			,"Clean-up record-provider and UI");
        
        // left control-pane
        controlsPane.setOpaque(false);
        controlsPane.add(reloadButton);  
        controlsPane.add(updateButton);  
        controlsPane.add(expadCollapseButton);   
        controlsPane.add(clearButton); 
        controlsPane.add(transactionIdLabel);
        controlsPane.add(transactionIdTextField);
      
        // right status-pane
        statusPane.setOpaque(false);
        statusPane.add(recordCounterLabel);
        statusPane.add(recordCounterTextField);
        
        // scrolling in this pane
        setBackground(treetableUI.getBackground());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(treetableUI.getBackground());
        scrollPane.getViewport().setView(null);

        // actions
        reloadButton.addActionListener(this);
        updateButton.addActionListener(this);
        expadCollapseButton.addActionListener(this);
        clearButton.addActionListener(this);
        transactionIdTextField.addActionListener(this);
        
        // allocate
        setLayout(new GridBagLayout());
        add(scrollPane, gbc1x1());
        
        // initialize treetable and UI
        treetableColumns = factory.newTreetableColumns();
        loadTreetable();
        setStatus(UNLOCKED);
    }
	
	/**
	 * left-pane with controls
	 * 
	 * @return complete pane connected with this pane
	 */
	public JPanel getControlsPane() {
		return controlsPane;
	}
	
	/**
	 * right-pane with record-status 
	 * 
	 * @return complete pane connected with this pane
	 */
	public JPanel getStatusPane() {
		return statusPane;
	}
	
	/**
	 * setup itself in the JFrame.
	 * Controls and status are allocated in the menu-bar
	 *  
	 * @param jFrame
	 */
	public final void selfDeployTo(final JFrame jFrame, boolean createMenuBar) {
		jFrame.setLayout(new GridBagLayout());
		if (createMenuBar) {
			log.trace("self-deployment to JFrame with JMenuBar-creation ...");
			// menu-bar
			final JMenuBar jMenuBar = new JMenuBar();
			jMenuBar.setLayout(new GridBagLayout());
			int x = 0;
			jMenuBar.add(getControlsPane(), gbc1xManyLeft_X(x++, 0));
			jMenuBar.add(getStatusPane(), gbc1xManyRight_X(x++, 0));

			jFrame.setJMenuBar(jMenuBar);
			jFrame.add(this, gbc1x1());
		} else {
			log.trace("self-deployment to JFrame ...");
			final JPanel pane = new JPanel();
			selfDeployTo(pane);
			jFrame.add(pane,gbc1x1());
		}
	}
	
    public final void selfDeployTo(final JPanel jPanel) {
    	log.trace("self-deployment to JPanel ...");
    	jPanel.setLayout(new GridBagLayout());
        jPanel.setBackground(getBackground());
		
		// menu-bar
    	int x = 0;
		final JPanel menuBar =  new JPanel();
		menuBar.setBackground(getBackground());
        menuBar.setLayout(new GridBagLayout());
        menuBar.add(getControlsPane(),gbc1xManyLeft_X(x++,0));
        menuBar.add(getStatusPane(),gbc1xManyRight_X(x++,0));
        menuBar.setMaximumSize(new Dimension(25,10000));

        // parent
        int y = 0;
        GridBagConstraints gbc = null;
        final GridBagLayout gbl = new GridBagLayout();
        jPanel.setLayout(gbl);
        gbc = gbc1xMany_Y(y++);
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanel.add(menuBar,gbc);
        gbc = gbc1xMany_Y(y++);
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        jPanel.add(this,gbc1xMany_Y(y++));
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
					expadCollapseButton.setIcon(treetableUI.getIconExpand());
				}
				else {
					treetable.expandAll();
					expanded = true;
					expadCollapseButton.setIcon(treetableUI.getIconCollapse());
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
					expadCollapseButton.setIcon(treetableUI.getIconExpand());
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
		final Node root = new NodeGenerator(factory, records).tree();
		log.trace("elapsed " + (System.currentTimeMillis() - start) + " ms. creating tree-root"); 
		
		// treetable
		treetableModel = factory.newTreeTableModel(root,treetableColumns);
		treetable = new TreetableImpl(treetableModel);
		treetable.setRootVisible(false);
		treetable.setBasicUI(treetableUI);
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
			icon = treetableUI.getIconLabelUnlocked();
			setEnabled(true);
			break;
		case 1: 
			icon = treetableUI.getIconLabelLocked();
			setEnabled(false);
			break;
		case 2: 
			icon= null!=recordProvider.recordCounter()? treetableUI.getIconLabelDone()
					: null;
			setEnabled(true);
			break;
			
		default:
			icon =  treetableUI.getIconLabelUnlocked();
			setEnabled(true);
		}
		
		scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, icon);
		final Integer recordCounter = recordProvider.recordCounter();
		recordCounterTextField.setText("" + recordCounter);
	}
	
	// HELPERS (components used in the constructor)
	
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
	
	protected static JTextField info(final Dimension size) {
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
		jButton.setPreferredSize(new Dimension(20, 20));
		return jButton;
	}
	
	// HELPERS (GridBag-constraints used in the constructor)
	
	private static final GridBagConstraints gbc1x1() {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		return gbc;
	}
	
	private static final GridBagConstraints gbc1xMany_Y(int y) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = y;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		return gbc;
	}
	
	private static final GridBagConstraints gbc1xManyLeft_X(final int x, final int inset) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = 0;
		gbc.insets = new Insets(inset,inset,inset,inset);
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.WEST;
		gbc.weightx = 0.9;
		gbc.weighty = 1.0;
		return gbc;
	}
	
	private static final GridBagConstraints gbc1xManyRight_X(final int x, final int inset) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = 0;
		gbc.insets = new Insets(inset,inset,inset,inset);
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.fill = GridBagConstraints.EAST;
		gbc.weightx = 0.1;
		gbc.weighty = 1.0;
		return gbc;
	}
}