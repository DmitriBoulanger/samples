package de.dbo.samples.gui.swing.treetable.records.impl;
 
import de.dbo.samples.gui.swing.treetable.api.TreeTable;
import de.dbo.samples.gui.swing.treetable.api.TreeTableModel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class RecordWindow extends JFrame {
	private static final long serialVersionUID = 4489500964556705612L;
	private static final Logger log = LoggerFactory.getLogger(RecordWindow.class);
	
	private final Dimension size = new Dimension(1000, 400);
	private final Font font = new Font("Consolas",Font.PLAIN, 14);
	private final Container contentPane = getContentPane();
	private final Color background = new Color(239,241,248);
	private final Color selection = new Color(168,208,245);
	private final Color foreground = Color.BLACK;
	
	private final TreeTableModel treeTableModel;
	private final TreeTable treeTable;
	private final JScrollPane jScrollPane;
	
	public RecordWindow() {
        super("Record Tree-Table");
        final long start = System.currentTimeMillis();

        treeTableModel = new RecordStructureTreeTableModel( RecordStructure.instance());
        treeTable = new TreeTable(treeTableModel);
        jScrollPane = new JScrollPane(treeTable);
        
       
        treeTable.setRootVisible(true);
        treeTable.setBasicUI(background, selection, foreground, font);
        treeTable.setIntercellSpacing(new Dimension(2, 2)); 
        treeTable.setColumnWidthNonresizable(1, 65);
        treeTable.setColumnWidthNonresizable(2, 600);
       
        jScrollPane.getViewport().setBackground(background);
        
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        
        contentPane.setLayout(gridBagLayout);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPane.add(jScrollPane, gbc);

        this.setSize(size);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        
        log.info("window done. Elapsed " +(System.currentTimeMillis()-start) + " ms." );
        
    }

    public static void main(final String[] args) {
    	
        final Runnable gui = new Runnable() {
 
        	@Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RecordWindow().setVisible(true);
            }
        };
        
        SwingUtilities.invokeLater(gui);
        
       
    }
}