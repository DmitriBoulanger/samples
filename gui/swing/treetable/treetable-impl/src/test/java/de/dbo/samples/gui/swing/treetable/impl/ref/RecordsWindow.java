package de.dbo.samples.gui.swing.treetable.impl.ref;

import static de.dbo.samples.gui.swing.treetable.api.WindowTools.addAs1x1;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.createIcon;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.createIconLabel;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.elapsed;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.gbc1xManyLeft;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.gbc1xManyRight;
import static de.dbo.samples.gui.swing.treetable.api.WindowTools.gbl1xMany;

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
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
				new RecordsWindow().showup();
			}
		});
	}
	
	public static final Factory factory(final String ctx) {
		final long start = System.currentTimeMillis();
        final Factory factory = FactoryMgr.instance(ctx);
        elapsed(start, "creating tree-table factory" );
        return factory;
	}
	
	// button-icons
	private static final ImageIcon ICON_REFRESH = createIcon(RecordsWindow.class,"icons/refresh.png");
	private static final ImageIcon ICON_UPDATE = createIcon(RecordsWindow.class,"icons/update.png");
	private static final ImageIcon ICON_EXPAND = createIcon(RecordsWindow.class,"icons/expand.png");
	private static final ImageIcon ICON_COLLAPSE = createIcon(RecordsWindow.class,"icons/collapse.png");
	private static final ImageIcon ICON_CLEAR = createIcon(RecordsWindow.class,"icons/clear.png");
	
	// status and status-icons
	private static final int UNLOCKED = 0;
	private static final int LOCKED = 1;
	private static final int DONE = 2;
	private static final JLabel ICON_DONE = createIconLabel(RecordsWindow.class,"icons/done.png");
	private static final JLabel ICON_LOCKED = createIconLabel(RecordsWindow.class,"icons/lock.png");
	private static final JLabel ICON_UNLOCKED = createIconLabel(RecordsWindow.class,"icons/unlock.png");

	/* final treetable factory and record provider */
	private final Factory factory = factory("ReferenceImplementation.xml");
	private final RecordProvider recordProvider = factory.getRecordProvider();
	
	/* final basic pane with scrolling and menu-bar components */
	private final JPanel pane = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JButton reloadButton = button(ICON_REFRESH);
	private final JButton updateButton = button(ICON_UPDATE);
	private final JButton expandButton = button(ICON_EXPAND);
	private final JButton collapseButton = button(ICON_COLLAPSE);
	private final JButton clearButton = button(ICON_CLEAR);
	private final JTextField transactionIdLabel = label(" Transaction ID:");
	private final JTextField transactionIdTextField = textfield(30);
	private final JTextField recordCounterLabel = label(" Record counter:");
	private final JTextField recordCounterTextField = space(4);
	
	/* dynamic data */
	private List<Record> records = null;
	
	/* dynamic treetable objects and components */
	private TreetableModel treetableModel = null;
	private Treetable treetable = null;
	
	/**
	 * GUI with childless treetable-root
	 */
	RecordsWindow() {
        super("Tree-Table with Records - Reference Implementation");
 
        // menu-bar
        final JPanel controlsPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlsPane.setOpaque(false);
        controlsPane.add(reloadButton);  
        controlsPane.add(updateButton);  
        controlsPane.add(expandButton);  
        controlsPane.add(collapseButton);  
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
        expandButton.addActionListener(this);
        collapseButton.addActionListener(this);
        clearButton.addActionListener(this);
        transactionIdTextField.addActionListener(this);
        
        // frame
        setJMenuBar(jMenuBar);
        setContentAs1x1(pane);
        
        // initialize treetable and UI
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
		
		else if (event.getSource()==expandButton)  {
			if (null!=treetable) {
				treetable.expandAll();
			}
		} 
		else if (event.getSource()==collapseButton)  {
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
			setStatus(LOCKED);
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					clearTreetable();
					loadTreetable();
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
					loadTreetable();
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
	 
	// HELPERS
	
	protected static JTextField textfield(int columns) {
		final JTextField jTextField = new JTextField();
		jTextField.setColumns(columns);
		return jTextField;
	}
	
	protected static JTextField space(int columns) {
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
	
	protected static final JButton button(final ImageIcon icon) {
		final JButton jButton = new JButton(icon);
		jButton.setBackground(BACKGROUND);
		jButton.setFocusable(false);
		return jButton;
	}
	

}