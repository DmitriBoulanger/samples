package de.dbo.samples.gui.swing.treetable;
 
import de.dbo.samples.gui.swing.treetable.api.TreeTable;
import de.dbo.samples.gui.swing.treetable.api.TreeTableModelAbstraction;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 
public class Window extends JFrame {
	private static final long serialVersionUID = 4489500964556705612L;

	public Window() {
        super("Tree Table Sample");
             
        final DataNode structureRoot = DataStructure.instance();
        final TreeTableModelAbstraction treeTableModel = new TreeTableModelImpl(structureRoot);
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
        Runnable gui = new Runnable() {
 
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