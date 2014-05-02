package dbo.samples.elk.logstash.config;

/**
 *
 * @author Dmitri Boulanger
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */

public interface Configuration extends java.io.Serializable {

    public String getLoggerName();

    public void setLoggerName(final String loggerName);

    // LOGSTASH Properties

    public long getLastMilliseconds();

    public void setLastMilliseconds(final long lastMilliseconds);


}