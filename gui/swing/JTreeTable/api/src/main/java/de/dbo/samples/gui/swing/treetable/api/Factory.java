package de.dbo.samples.gui.swing.treetable.api;


import de.dbo.samples.gui.swing.treetable.api.gui.TreeTableModel;
import de.dbo.samples.gui.swing.treetable.api.records.Node;
import de.dbo.samples.gui.swing.treetable.api.records.Record;

import org.springframework.context.ApplicationContext;

public final class Factory  {
	
	final ApplicationContext ctx; 
	
	public Factory(final ApplicationContext ctx) {
		this.ctx = ctx;
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
