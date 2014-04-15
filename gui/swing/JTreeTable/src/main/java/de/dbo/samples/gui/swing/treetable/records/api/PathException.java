package de.dbo.samples.gui.swing.treetable.records.api;

public class PathException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PathException(final String message) {
		super(message);
	}
	public PathException(final String message,Throwable e) {
		super(message,e);
	}
}
