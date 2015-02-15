package de.dbo.samples.rmi0;

public final class RegistryManagementException extends RuntimeException {
    private static final long serialVersionUID = -3934029042961816869L;
    
    public RegistryManagementException(String message) {
	super(message);
    }
    
    public RegistryManagementException(String message, Throwable cause) {
	super(message,cause);
    }

}
