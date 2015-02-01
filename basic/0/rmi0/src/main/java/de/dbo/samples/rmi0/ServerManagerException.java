package de.dbo.samples.rmi0;

public final class ServerManagerException extends RuntimeException {
    private static final long serialVersionUID = -3934029042961816869L;
    
    public ServerManagerException(String message) {
	super(message);
    }
    
    public ServerManagerException(String message, Throwable cause) {
	super(message,cause);
    }

}
