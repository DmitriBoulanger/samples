package de.dbo.samples.gui.swing.treetable.impl.xexample;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXTreeTable;

public class TreeTableTest extends JFrame {
	private static final long serialVersionUID = -1244000424456241709L;

	private JXTreeTable treeTable;

	public TreeTableTest() {
		
		// we use a no root model
		NoRootTreeTableModel noRootTreeTableModel = new NoRootTreeTableModel(new Data());
		treeTable = new JXTreeTable(noRootTreeTableModel);
		treeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		treeTable.setRootVisible(false); // hide the root
		
		// special renderer
		treeTable.getColumnModel().getColumn(3).setCellRenderer(new PhotoRenderer());

		add(new JScrollPane(treeTable));

		setTitle("JXTreeTable Example");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TreeTableTest();
			}
		});
	}
}
