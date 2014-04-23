package de.dbo.samples.gui.swing.treetable.api.factory;

import java.lang.reflect.*;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableColumns;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;

import org.springframework.beans.factory.annotation.Required;

final class FactoryImpl implements Factory  {
	
	protected String rootName;

	protected Class<?> nodeClass;
	
	protected Class<?> treetableModelClass;
	
	protected RecordProvider recordProvider;
	
	protected TreetableColumns treetableColumns;
	
	protected FactoryImpl() {
		
	}
	
	@Override
	public Node newRoot() {
		return newNode(getRootName(),null);
	}
	
	@Override
	public Node newNode(final String treename, final Record record) {
		try {
			final Node node = (Node) nodeClass.newInstance();
			node.init(treename,record);
			return node;
		} catch (Exception e) {
			throw new FactoryException("Can't create node-instance."+ " Treename=" + treename, e);
		} 
	}
	
	@Override
	public TreetableModel newTreeTableModel(final Node root) {
		final TreetableColumns treetableColumns = getTreetableColumns();
		if (null==treetableColumns) {
			throw new FactoryException("Can't get treetableColumns (NULL)");
		}
		try {
			final Constructor<?> constructor = treetableModelClass.getConstructor(TreetableColumns.class);
			final TreetableModel treeTableModel = (TreetableModel) constructor.newInstance(treetableColumns);
			treeTableModel.setRoot(root);
			return treeTableModel;
		} catch (Exception e) {
			throw new FactoryException("Can't create model-instance."  + " Root=" + root.getTreename(), e);
		} 
	}
	
	@Override
	@Required
    public String getRootName() {
		return rootName;
	}

	@Override
	public void setRootName(String rootName) {
		this.rootName = rootName;
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
	
	@Override
	@Required
	public RecordProvider getRecordProvider() {
		return recordProvider;
	}

	@Override
	public void setRecordProvider(RecordProvider recordProvider) {
		this.recordProvider = recordProvider;
	}

	@Override
	@Required
	public TreetableColumns getTreetableColumns() {
		return treetableColumns;
	}

	@Override
	public void setTreetableColumns(final TreetableColumns treetableColumns) {
		this.treetableColumns = treetableColumns;
	}
	
	

}
