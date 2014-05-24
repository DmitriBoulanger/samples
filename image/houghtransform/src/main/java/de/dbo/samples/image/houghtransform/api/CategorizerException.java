package de.dbo.samples.image.houghtransform.api;

import org.springframework.beans.BeansException;

/**
 * Exception from ORM-CategorizerImpl.
 * 
 * @author D.Boulanger ITyX GmbH
 * 
 */
public final class CategorizerException extends Exception {
    private static final long serialVersionUID            = -6050886052379079011L;

    public static final int   BEANS_CTX_INITILIZATION     = 10;
    public static final int   BEANS_CONTENT_INITILIZATION = 11;
    public static final int   CONFIG_INITILIZATION        = 20;
    public static final int   CONFIG_CORRECTNESS          = 21;
    public static final int   SYSTEM                      = 30;
    public static final int   IMANGE_ERROR                = 40;
    public static final int   UNKNOWN                     = 99;

    private final int         type;

    public CategorizerException(final int type, final String message,
            final Throwable e) {
        super(message(type, message, e), exception(type, e));
        this.type = type;
    }

    public CategorizerException(final int type, final String message) {
        super(message);
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    /*
     * suppress exception itself or not. Typically exceptions from
     * spring-framework contain very good messages, and, therefore, exception
     * itself can be dropped
     */
    private static final Throwable exception(int type, Throwable e) {
        if (null == e) {
            return null;
        }
        switch (type) {

            case BEANS_CTX_INITILIZATION:
            case BEANS_CONTENT_INITILIZATION:
                return null;

            default:
                return e;
        }
    }

    /*
     * message extraction. Messages from spring-framework exceptions are
     * extracted as they are and possible prefixed with the message from the
     * HT-Exception.
     */
    private static final String message(int type, final String message,
            final Throwable e) {
        if (null == e) {
            return message;
        }
        else if (e instanceof BeansException) {
            return springMessage(type, message, (BeansException) e);
        }
        return message;
    }

    /*
     * prefix for a message from spring-framework exception
     */
    private static final String springMessage(int type, final String message,
            final BeansException e) {
        e.setStackTrace(new StackTraceElement[]{});
        switch (type) {
            case BEANS_CTX_INITILIZATION:
                return e.getMessage();
            case BEANS_CONTENT_INITILIZATION:
                return message + ": " + e.getMessage();
            default:
                return e.getMessage();
        }
    }

}
