package de.dbo.samples.gui.swing.treetable.api.factory;

import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;

import org.springframework.beans.factory.annotation.Required;

final class FactoryImpl implements Factory  {

	protected Class<?> nodeClass;
	
	protected Class<?> treetableModelClass;
	
	protected FactoryImpl() {
		
	}
	
	@Override
	public Node newNode(final String treename, final Record record) {
		try {
			final Node node = (Node) nodeClass.newInstance();
			node.init(treename,record);
			return node;
		} catch (Exception e) {
			throw new FactoryException(
					"Can't create node-instance."
					+ " Treename=" + treename, e);
		} 
	}
	
	@Override
	public TreetableModel treeTableModel(final Node root) {
		try {
			final TreetableModel treeTableModel = (TreetableModel) treetableModelClass.newInstance();
			treeTableModel.setRoot(root);
			return treeTableModel;
		} catch (Exception e) {
			throw new FactoryException(
					"Can't create model-instance." 
					 + " Root=" + root.getTreename(), e);
		} 
	}

	@Override
	@Required
    public Class<?> getNodeClass() {
		return nodeClass;
	}

	@Override
	public void setNodeClass(Class<?> nodeClass) {
		this.nodeClass = nodeClass;
	}

	@Override
	@Required
	public Class<?> getTreetableModelClass() {
		return treetableModelClass;
	}

	@Override
	public void setTreetableModelClass(Class<?> treetableModelClass) {
		this.treetableModelClass = treetableModelClass;
	}
}
