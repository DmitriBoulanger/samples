package de.dbo.samples.gui.swing.treetable.records.api;

public final class RecordException extends RuntimeException {
	private static final long serialVersionUID = -1007313509791399051L;
	
	public RecordException(final String message) {
		super(message);
	}
	public RecordException(final String message,Throwable e) {
		super(message,e);
	}
}
