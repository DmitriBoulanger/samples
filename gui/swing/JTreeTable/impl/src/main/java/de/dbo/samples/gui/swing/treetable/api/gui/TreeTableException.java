package de.dbo.samples.gui.swing.treetable.api.gui;

public class TreeTableException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public TreeTableException(final String message) {
		super(message);
	}
	public TreeTableException(final String message,Throwable e) {
		super(message,e);
	}
}
