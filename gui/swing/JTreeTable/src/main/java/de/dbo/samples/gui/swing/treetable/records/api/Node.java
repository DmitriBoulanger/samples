package de.dbo.samples.gui.swing.treetable.records.api;

import java.util.List;

public interface Node {

	public abstract String getName();

	public abstract Object getObject();

	public abstract void setObject(Object o);

	public abstract List<Node> children();
	
	public abstract StringBuilder print();

	/**
	 * Node-path.
	 */
	public abstract String toString();

}