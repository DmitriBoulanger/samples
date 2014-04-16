package de.dbo.samples.gui.swing.treetable.records.api;

public class RecordException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public RecordException(final String message) {
		super(message);
	}
	public RecordException(final String message,Throwable e) {
		super(message,e);
	}
}
