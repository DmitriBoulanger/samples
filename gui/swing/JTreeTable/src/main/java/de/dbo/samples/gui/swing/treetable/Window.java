package de.dbo.samples.gui.swing.treetable;
 
import de.dbo.samples.gui.swing.treetable.api.TreeTable;
import de.dbo.samples.gui.swing.treetable.api.TreeTableModel;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 
public class Window extends JFrame {
	private static final long serialVersionUID = 4489500964556705612L;

	public Window() {
        super("Tree Table Sample");
             
        final DataNode structureRoot = DataStructure.instance();
        final TreeTableModel treeTableModel = new TreeTableModelImpl(structureRoot);
        final TreeTable treeTable = new TreeTable(treeTableModel);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(0, 1));
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        
        final Container contentPane = getContentPane();
        contentPane.add(new JScrollPane(treeTable));
        this.setVisible(true);
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