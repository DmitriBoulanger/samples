package de.dbo.samples.gui.swing.treetable.records.api;

public class NodeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NodeException(final String message) {
		super(message);
	}
	public NodeException(final String message,Throwable e) {
		super(message,e);
	}
}
