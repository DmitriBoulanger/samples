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
             
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        setLayout(new GridLayout(0, 1));
 
        TreeTableModelAbstraction treeTableModel = new TreeTableModelImpl(createDataStructure());
 
        TreeTable treeTable = new TreeTable(treeTableModel);
 
        Container cPane = getContentPane();
         
        cPane.add(new JScrollPane(treeTable));
     
        setSize(1000, 800);
        setLocationRelativeTo(null);
         
    }
 
    private static DataNode createDataStructure() {
        List<DataNode> children1 = new ArrayList<DataNode>();
        children1.add(new DataNode("N12", "C12", new Date(), Integer.valueOf(50), null));
        children1.add(new DataNode("N13", "C13", new Date(), Integer.valueOf(60), null));
        children1.add(new DataNode("N14", "C14", new Date(), Integer.valueOf(70), null));
        children1.add(new DataNode("N15", "C15", new Date(), Integer.valueOf(80), null));
 
        List<DataNode> children2 = new ArrayList<DataNode>();
        children2.add(new DataNode("N12", "C12", new Date(), Integer.valueOf(10), null));
        children2.add(new DataNode("N13", "C13", new Date(), Integer.valueOf(20), children1));
        children2.add(new DataNode("N14", "C14", new Date(), Integer.valueOf(30), null));
        children2.add(new DataNode("N15", "C15", new Date(), Integer.valueOf(40), null));
         
        List<DataNode> rootNodes = new ArrayList<DataNode>();
        rootNodes.add(new DataNode("N1", "C1", new Date(), Integer.valueOf(10), children2));
        rootNodes.add(new DataNode("N2", "C2", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N3", "C3", new Date(), Integer.valueOf(10), children2));
        rootNodes.add(new DataNode("N4", "C4", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N5", "C5", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N6", "C6", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N7", "C7", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N8", "C8", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N9", "C9", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N10", "C10", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N11", "C11", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N12", "C7", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N13", "C8", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N14", "C9", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N15", "C10", new Date(), Integer.valueOf(10), children1));
        rootNodes.add(new DataNode("N16", "C11", new Date(), Integer.valueOf(10), children1));
        
        final DataNode root = new DataNode("R1", "R1", new Date(), Integer.valueOf(10), rootNodes);
        return root;
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