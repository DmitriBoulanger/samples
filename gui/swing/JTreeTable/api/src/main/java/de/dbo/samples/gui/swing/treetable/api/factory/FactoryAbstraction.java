package de.dbo.samples.gui.swing.treetable.api.factory;

import de.dbo.samples.gui.swing.treetable.api.gui.TreeTableModel;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public abstract class FactoryAbstraction implements Factory  {
	protected static final Logger log = LoggerFactory.getLogger(Factory.class);
	 
	protected static final Map<String, Factory> FACTORIES = new HashMap<String,Factory>();
	
	protected Class<?> nodeClass;
	
	protected Class<?> treetableModelClass;
	
	protected FactoryAbstraction() {
		
	}
	
	@Override
	public Node newNode(final String treename, final Record record) {
		try {
			final Node node = (Node) nodeClass.newInstance();
			node.init(treename,record);
			return node;
		} catch (Exception e) {
			throw new FactoryException("Can't create node-instance. Treename=" + treename
					, e);
		} 
	}
	
	@Override
	public TreeTableModel treeTableModel(final Node root) {
		try {
			final TreeTableModel treeTableModel = (TreeTableModel) treetableModelClass.newInstance();
			treeTableModel.setRoot(root);
			return treeTableModel;
		} catch (Exception e) {
			throw new FactoryException("Can't create model-instance. Treename=" + root.getTreename()
					, e);
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
