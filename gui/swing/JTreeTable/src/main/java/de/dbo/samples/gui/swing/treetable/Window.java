package de.dbo.samples.gui.swing.treetable;
 
import de.dbo.samples.gui.swing.treetable.api.TreeTable;
import de.dbo.samples.gui.swing.treetable.api.TreeTableModel;
import de.dbo.samples.gui.swing.treetable.data.DataWindow;

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
 
public class Window extends JFrame {
	private static final long serialVersionUID = 4489500964556705612L;
	private static final Logger log = LoggerFactory.getLogger(Window.class);
	
	protected final Dimension size = new Dimension(1000, 400);
	protected final Font font = new Font("Consolas",Font.PLAIN, 14);
	protected final Container contentPane = getContentPane();
	
	protected final TreeTableModel treeTableModel;
	protected final TreeTable treeTable;
	protected final JScrollPane jScrollPane;
	
	protected final Color background = new Color(239,241,248);
	protected final Color selection = new Color(168,208,245);
	
	protected static Window INSTANCE = null;
	
	public Window(final TreeTableModel treeTableModel, final String title) {
        super(title);
        final long start = System.currentTimeMillis();
        
        this.treeTableModel = treeTableModel;
        treeTable = new TreeTable(treeTableModel);
        treeTable.setBasicUI(background, selection, Color.BLACK, font);
        
        jScrollPane = new JScrollPane(treeTable);
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
        
        log.info("window done. Elapsed " + (System.currentTimeMillis()-start) + " ms." );
    }
	
	protected static Runnable runnable(final Window window) {
		return new Runnable() {
        	@Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                window.setVisible(true);
            }
        };
	}
	
}