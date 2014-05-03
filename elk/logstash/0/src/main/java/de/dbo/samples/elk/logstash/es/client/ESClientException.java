package de.dbo.samples.elk.logstash.es.client;

public class ESClientException extends RuntimeException {
	private static final long serialVersionUID = 5435137626587252337L;

	public ESClientException(final String message) {
		super(message);
	}
	
	public ESClientException(final String message, final Throwable throwable) {
		super(message,throwable);
	}

}
