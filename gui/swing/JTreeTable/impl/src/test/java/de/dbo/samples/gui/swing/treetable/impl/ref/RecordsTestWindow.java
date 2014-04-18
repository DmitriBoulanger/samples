package de.dbo.samples.gui.swing.treetable.impl.ref;
 
import de.dbo.samples.gui.swing.treetable.api.Window;
import de.dbo.samples.gui.swing.treetable.api.factory.FactoryMgr;
import de.dbo.samples.gui.swing.treetable.api.gui.Treetable;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.records.Node;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class RecordsTestWindow extends Window {
	private static final long serialVersionUID = 4489500964556705612L;
	private static final Logger log = LoggerFactory.getLogger(RecordsTestWindow.class);
	
	// to avoid time elapsed to create the factory
	static {
		 try {
				FactoryMgr.instance("ReferenceImplementation.xml");
			} catch (Exception e) {
				log.info(e.toString());
			}
	}
	
	private final Dimension size = new Dimension(1000, 400);
	private final Font font = new Font("Consolas",Font.PLAIN, 14);
	private final Color background = new Color(239,241,248);
	private final Color selection = new Color(168,208,245);
	private final Color foreground = Color.BLACK;
	
	public RecordsTestWindow() {
        super("Record Tree-Table Test");
        
        final long start2 = System.currentTimeMillis();
        final Node root = new RecordsTest().getTreeroot();
        log.info("Elapsed " + (System.currentTimeMillis()-start2) + " ms. to create tree-root" );
        
        // Model
        final TreetableModel treetableModel = new TreeTableModelImpl();
        treetableModel.setRoot(root);
        
        // Treetable
        final Treetable treeTable = new Treetable(treetableModel);
        treeTable.setRootVisible(true);
        treeTable.setBasicUI(background, selection, foreground, font);
        treeTable.setIntercellSpacing(new Dimension(0,0)); 
        treeTable.setColumnWidthNonresizable(1, 65);
        treeTable.setColumnWidthNonresizable(2, 600);
       
        final JScrollPane jScrollPane = new JScrollPane(treeTable);
        jScrollPane.getViewport().setBackground(background);
        
       // JFrame
       addToContent(jScrollPane);
       setSize(size);
       setLocationRelativeTo(null);
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(runnable(new RecordsTestWindow()));
    }
}