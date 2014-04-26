package de.dbo.samples.gui.swing.treetable.api;

public class TreetableException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public TreetableException(final String message) {
		super(message);
	}
	public TreetableException(final String message,Throwable e) {
		super(message,e);
	}
}
