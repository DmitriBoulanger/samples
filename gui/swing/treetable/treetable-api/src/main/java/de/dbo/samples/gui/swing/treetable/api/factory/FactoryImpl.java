package de.dbo.samples.gui.swing.treetable.api.factory;

import java.lang.reflect.*;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableColumns;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;

import org.springframework.beans.factory.annotation.Required;

/**
 * Treetable Factory Implementation.
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
final class FactoryImpl implements Factory  {
	
	protected String rootName;

	protected Class<?> nodeClass;
	
	protected Class<?> treetableModelClass;
	
	protected RecordProvider recordProvider;
	
	protected TreetableColumns treetableColumns;
	
	/**
	 * singleton instance initialized by Spring
	 */
	protected FactoryImpl() {
		
	}
	
	/**
	 * new node-instance.
	 * It is created using reflection (not Spring-bean prototype)
	 */
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
	
	/**
	 * new root-instance.
	 * It is created using reflection (not Spring-bean prototype)
	 */
	@Override
	public Node newRoot() {
		return newNode(getRootName(),null);
	}
	
	/**
	 * new instance of TreetableModel.
	 * It is created using reflection (not Spring-bean prototype)
	 */
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
	
	/**
	 * tree-name of the root
	 */
	@Override
	@Required
    public String getRootName() {
		return rootName;
	}

	@Override
	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	/**
	 * class to be used for tree-nodes
	 */
	@Override
	@Required
    public Class<?> getNodeClass() {
		return nodeClass;
	}

	@Override
	public void setNodeClass(Class<?> nodeClass) {
		this.nodeClass = nodeClass;
	}

	/**
	 * class to be used for data-models of the Treetable
	 */
	@Override
	@Required
	public Class<?> getTreetableModelClass() {
		return treetableModelClass;
	}

	@Override
	public void setTreetableModelClass(Class<?> treetableModelClass) {
		this.treetableModelClass = treetableModelClass;
	}
	
	/**
	 * new instance of record-provider.
	 * Instance is created with the Spring-prototype bean 
	 */
	@Override
	@Required
	public RecordProvider getRecordProvider() {
		return recordProvider;
	}

	@Override
	public void setRecordProvider(RecordProvider recordProvider) {
		this.recordProvider = recordProvider;
	}

	/**
	 * new instance of column manager.
	 * Instance is created with the Spring-prototype bean 
	 */
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
