package de.dbo.samples.gui.swing.treetable.api.factory;

import java.lang.reflect.*;

import de.dbo.samples.gui.swing.treetable.api.Treetable;
import de.dbo.samples.gui.swing.treetable.api.TreetableColumns;
import de.dbo.samples.gui.swing.treetable.api.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.TreetableUI;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;

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
	
	protected Class<?> treetableClass;
	
	protected TreetableUI treetableUI;
	
	private ApplicationContext ctx;
	
	/**
	 * singleton instance initialized by Spring
	 */
	protected FactoryImpl() {
		
	}
	
	protected final void setApplicationContext(final ApplicationContext ctx) {
		this.ctx = ctx;
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
	public TreetableModel newTreeTableModel(final Node root, final TreetableColumns treetableColumns) {
		if (null==treetableColumns) {
			throw new FactoryException("Can't make TreetableModel-instace if treetableColumns (NULL)");
		}
		try {
			final Constructor<?> constructor = treetableModelClass.getConstructor(TreetableColumns.class);
			final TreetableModel treeTableModel = (TreetableModel) constructor.newInstance(treetableColumns);
			treeTableModel.setRoot(root);
			return treeTableModel;
		} 
		catch (Exception e) {
			throw new FactoryException("Can't create model-instance."  + " Root=" + root.getTreename(), e);
		} 
	}
	
	/**
	 * new instance of Treetable.
	 * It is created using reflection (not Spring-bean prototype)
	 */
	@Override
	public Treetable newTreetable(final TreetableModel treetableModel) {
		if (null == treetableModel) {
			throw new FactoryException(
					"Can't make Treetable-instace if treetableModel (NULL)");
		}

		final TreetableUI treetableUI = getTreetableUI();
		try {
			final Constructor<?> constructor = treetableClass.getConstructor(
					TreetableModel.class, TreetableUI.class);
			final Treetable treetable = (Treetable) constructor
					.newInstance(treetableModel,treetableUI);
			return treetable;
		} catch (Exception e) {
			throw new FactoryException("Can't create model-instance."
					+ " Model=" + treetableModel, e);
		}
	}
	
	/**
	 * new instance of record-provider.
	 * Instance is created with the Spring-prototype bean 
	 */
	@Override
	public RecordProvider newRecordProvider() {
		return  (RecordProvider) ctx.getBean("recordProvider");
	}

	/**
	 * new instance of column manager.
	 * Instance is created with the Spring-prototype bean 
	 */
	@Override
	public TreetableColumns newTreetableColumns() {
		return (TreetableColumns) ctx.getBean("treetableColumns"); 
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
	 * class to be used for data-models of the Treetable
	 */
	@Override
	@Required
	public Class<?> getTreetableClass() {
		return treetableClass;
	}

	@Override
	public void setTreetableClass(Class<?> treetableClass) {
		this.treetableClass = treetableClass;
	}

	/**
	 * UI constants.
	 */
	@Override
	@Required
	public TreetableUI getTreetableUI() {
		return treetableUI;
	}

	@Override
	public void setTreetableUI(final TreetableUI treetableUI) {
		this.treetableUI = treetableUI;
	}
	
}
