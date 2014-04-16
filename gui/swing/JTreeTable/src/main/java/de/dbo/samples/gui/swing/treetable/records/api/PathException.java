package de.dbo.samples.gui.swing.treetable.records.api;

public final class PathException extends RuntimeException {
	private static final long serialVersionUID = 5303374770038618098L;
	
	public PathException(final String message) {
		super(message);
	}
	public PathException(final String message, final Throwable e) {
		super(message,e);
	}
}
