package de.dbo.samples.gui.swing.treetable.records.api;


public interface Factory {

	/**
	 * node having no children
	 * It can have a record
	 * 
	 * @param name tree-name (display name)
	 * @param record data-record
	 * 
	 * @return new Node-instance
	 */
	public abstract Node newNode(final String name, final Record record);

}