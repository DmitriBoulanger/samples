package de.dbo.samples.gui.swing.treetable.api.factory;

import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;

public interface Factory  {
	 
	public Node newNode(final String treename, final Record record);
	
	public TreetableModel treeTableModel(final Node root);

    public Class<?> getNodeClass();

	public void setNodeClass(Class<?> nodeClass);

	public Class<?> getTreetableModelClass();

	public void setTreetableModelClass(Class<?> treetableModelClass);
	
}
