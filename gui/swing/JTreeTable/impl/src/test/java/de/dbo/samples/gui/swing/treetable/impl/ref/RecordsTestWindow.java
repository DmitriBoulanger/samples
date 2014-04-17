package de.dbo.samples.gui.swing.treetable.impl.ref;
 
import de.dbo.samples.gui.swing.treetable.api.gui.TreeTable;
import de.dbo.samples.gui.swing.treetable.api.gui.TreeTableModel;
import de.dbo.samples.gui.swing.treetable.impl.ref.TreeTableModelImpl;

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
 
public class RecordsTestWindow extends JFrame {
	private static final long serialVersionUID = 4489500964556705612L;
	private static final Logger log = LoggerFactory.getLogger(RecordsTestWindow.class);
	
	private final Dimension size = new Dimension(1000, 400);
	private final Font font = new Font("Consolas",Font.PLAIN, 14);
	private final Container contentPane = getContentPane();
	private final Color background = new Color(239,241,248);
	private final Color selection = new Color(168,208,245);
	private final Color foreground = Color.BLACK;
	
	private final TreeTableModel treeTableModel;
	private final TreeTable treeTable;
	private final JScrollPane jScrollPane;
	
	public RecordsTestWindow() {
        super("Record Tree-Table");
        final long start = System.currentTimeMillis();
        
        treeTableModel = new TreeTableModelImpl();
        treeTableModel.setRoot(new RecordsTest().getTreeroot());
        treeTable = new TreeTable(treeTableModel);
        jScrollPane = new JScrollPane(treeTable);
        
        treeTable.setRootVisible(true);
        treeTable.setBasicUI(background, selection, foreground, font);
        treeTable.setIntercellSpacing(new Dimension(1,1)); 
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
        
        log.info("window done. Elapsed " +(System.currentTimeMillis()-start) + " ms." );
    }

    public static void main(final String[] args) {
    	log.info("openning ..." );
        final Runnable gui = new Runnable() {
 
        	@Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RecordsTestWindow().setVisible(true);
            }
        };
        
        SwingUtilities.invokeLater(gui);
    }
}