package de.dbo.samples.gui.swing.treetable.api.records;

public final class RecordProviderException extends RuntimeException {
	private static final long serialVersionUID = -1007313509791399051L;
	
	public RecordProviderException(final String message) {
		super(message);
	}
	public RecordProviderException(final String message,Throwable e) {
		super(message,e);
	}
}
