package de.dbo.samples.gui.swing.treetable;
 
import de.dbo.samples.gui.swing.treetable.api.TreeTable;
import de.dbo.samples.gui.swing.treetable.api.TreeTableModel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


import java.awt.Menu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class Window extends JFrame {
	private static final long serialVersionUID = 4489500964556705612L;
	private static final Logger log = LoggerFactory.getLogger(Window.class);
	
	private final Dimension size = new Dimension(1000, 800);
	
	private final DataNode structureRoot;
	private final TreeTableModel treeTableModel;
	private final TreeTable treeTable;
	private final JScrollPane jScrollPane;
	
	 
	public Window() {
        super("Tree Table Sample");
             
        structureRoot = DataStructure.instance();
        treeTableModel = new DataStructureTreeTableModel(structureRoot);
        treeTable = new TreeTable(treeTableModel);
        jScrollPane = new JScrollPane(treeTable);
        
        treeTable.setFont(new Font("Consolas",Font.PLAIN, 14));
        
        final Container contentPane = getContentPane();
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
        this.setVisible(true);
        
        log.debug("created");
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
                new Window().setVisible(true);
            }
        };
        
        SwingUtilities.invokeLater(gui);
    }
}