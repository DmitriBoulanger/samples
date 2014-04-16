package de.dbo.samples.gui.swing.treetable.records.api;

public final class NodeException extends RuntimeException {
	private static final long serialVersionUID = -110866147035969359L;
	
	public NodeException(final String message) {
		super(message);
	}
	public NodeException(final String message, final Throwable e) {
		super(message,e);
	}
}
