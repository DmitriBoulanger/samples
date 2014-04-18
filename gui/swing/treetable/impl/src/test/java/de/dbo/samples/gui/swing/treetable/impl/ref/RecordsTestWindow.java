package de.dbo.samples.gui.swing.treetable.impl.ref;
 
import de.dbo.samples.gui.swing.treetable.api.Window;
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
	
	 public static void main(final String[] args) {
	        SwingUtilities.invokeLater(runnable(new RecordsTestWindow()));
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
        final TreetableModel model = new TreeTableModelImpl();
        model.setRoot(root);
        
        // Treetable
        final Treetable treetable = new Treetable(model);
        treetable.setRootVisible(true);
        treetable.setBasicUI(background, selection, foreground, font);
        treetable.setIntercellSpacing(new Dimension(0,0)); 
        treetable.setColumnWidthNonresizable(1, 65);
        treetable.setColumnWidthNonresizable(2, 600);
       
        final JScrollPane jScrollPane = new JScrollPane(treetable);
        jScrollPane.getViewport().setBackground(background);
        
       // JFrame
       addToContent(jScrollPane);
       setSize(size);
       setLocationRelativeTo(null);
    }
	
}