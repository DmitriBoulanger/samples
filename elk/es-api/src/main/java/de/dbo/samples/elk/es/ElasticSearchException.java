package de.dbo.samples.elk.es;

public class ElasticSearchException extends RuntimeException {
	private static final long serialVersionUID = 5435137626587252337L;

	public ElasticSearchException(final String message) {
		super(message);
	}
	
	public ElasticSearchException(final String message, final Throwable throwable) {
		super(message,throwable);
	}
}
