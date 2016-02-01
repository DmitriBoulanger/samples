package de.dbo.samples.gui.swing.treetable.api.factory;

import de.dbo.samples.gui.swing.treetable.api.Treetable;
import de.dbo.samples.gui.swing.treetable.api.TreetableColumns;
import de.dbo.samples.gui.swing.treetable.api.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.TreetableUI;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.NodeFactory;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;

/**
 * Treetable needs Nodes, Records and Models.
 * An tree-application should provide implementations for all these interfaces.  
 * A factory delivers instances
 *  
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Factory extends NodeFactory {
	
	public TreetableModel newTreeTableModel(final Node root, final TreetableColumns treetableColumns);
	
	public Treetable newTreetable(final TreetableModel treetableModel);
	
    public RecordProvider newRecordProvider();
	
	public TreetableColumns newTreetableColumns();

	// configuration methods
	
    public Class<?> getNodeClass();

	public void setNodeClass(final Class<?> nodeClass);

	public Class<?> getTreetableModelClass();

	public void setTreetableModelClass(final Class<?> treetableModelClass);
	
	public Class<?> getTreetableClass();

	public void setTreetableClass(Class<?> treetableClass);
	
    public void setTreetableUI(final TreetableUI treetableUI);
	
	public TreetableUI getTreetableUI();
	
}
