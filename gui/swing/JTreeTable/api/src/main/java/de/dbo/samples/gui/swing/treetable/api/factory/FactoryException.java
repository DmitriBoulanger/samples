package de.dbo.samples.gui.swing.treetable.api.factory;

public final class FactoryException extends RuntimeException {
	private static final long serialVersionUID = -110866147035969359L;
	
	public FactoryException(final String message) {
		super(message);
	}
	public FactoryException(final String message, final Throwable e) {
		super(message,e);
	}
}
