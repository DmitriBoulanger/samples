package de.dbo.samples.gui.swing.treetable.api.factory;

import de.dbo.samples.gui.swing.treetable.api.gui.XTreetableColumns;
import de.dbo.samples.gui.swing.treetable.api.gui.TreetableModel;
import de.dbo.samples.gui.swing.treetable.api.gui.XTreetableModel;
import de.dbo.samples.gui.swing.treetable.api.gui.XTreetableUI;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;
import de.dbo.samples.gui.swing.treetable.api.records.RecordProvider;

import java.lang.reflect.Constructor;

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
final class XFactoryImpl implements XFactory  {
	
	protected String rootName;

	protected Class<?> nodeClass;
	
	protected Class<?> treetableModelClass;
	
	protected XTreetableUI treetableUI;
	
	private ApplicationContext ctx;
	
	/**
	 * singleton instance initialized by Spring
	 */
	protected XFactoryImpl() {
		
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
	public XTreetableModel newTreeTableModel(final Node root, final XTreetableColumns treetableColumns) {
		if (null==treetableColumns) {
			throw new FactoryException("Can't get treetableColumns (NULL)");
		}
		try {
			final Constructor<?> constructor = treetableModelClass.getConstructor(XTreetableColumns.class);
			final XTreetableModel treeTableModel = (XTreetableModel) constructor.newInstance(treetableColumns);
			treeTableModel.setRoot(root);
			return treeTableModel;
		} catch (Exception e) {
			throw new FactoryException("Can't create model-instance."  + " Root=" + root.getTreename(), e);
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
	public XTreetableColumns newTreetableColumns() {
		return (XTreetableColumns) ctx.getBean("treetableColumns"); 
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
	 * UI constants.
	 */
	@Override
	@Required
	public XTreetableUI getTreetableUI() {
		return treetableUI;
	}

	@Override
	public void setTreetableUI(final XTreetableUI treetableUI) {
		this.treetableUI = treetableUI;
	}
	
}
