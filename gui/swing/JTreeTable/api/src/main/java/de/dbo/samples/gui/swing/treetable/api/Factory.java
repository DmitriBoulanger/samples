package de.dbo.samples.gui.swing.treetable.api;

import de.dbo.samples.gui.swing.treetable.api.gui.TreeTableModel;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Factory  {
	private static final Logger log = LoggerFactory.getLogger(Factory.class);
	
	private final ApplicationContext ctx; 
	
	private static final Map<String,Factory> FACTORIES = new HashMap<String,Factory>();
	
	public static final Factory instance(final String config) {
		if (FACTORIES.containsKey(config)) {
			return FACTORIES.get(config);
		} else {
			final Factory factory = new Factory(config);
			FACTORIES.put(config,factory);
			return factory;
		}
	}
	
	private Factory(final String config) {
		this.ctx = new ClassPathXmlApplicationContext(config);
		log.info("created for config=" + config);
	}
	
	public Node newNode(final String treename, final Record record) {
		final Node node = (Node) ctx.getBean("node");
		node.init(treename,record);
		return node;
	}
	
	public TreeTableModel treeTableModel(final Node root) {
		final TreeTableModel treeTableModel = (TreeTableModel) ctx.getBean("treeTableModel");
		treeTableModel.setRoot(root);
		return treeTableModel;
	}

}
