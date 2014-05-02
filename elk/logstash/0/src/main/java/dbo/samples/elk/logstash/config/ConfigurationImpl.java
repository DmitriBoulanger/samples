package dbo.samples.elk.logstash.config;

import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author Dmitri Boulanger
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */

public final class ConfigurationImpl implements Configuration {
    private static final long  serialVersionUID = -8045234178540478263L;


    // ========================================================================
    private String loggerName;

    @Override
    public String getLoggerName() {
        return loggerName;
    }

    @Override
    @Required
    public void setLoggerName(final String loggerName) {
        this.loggerName = loggerName;
    }

    // ========================================================================
    private long lastMilliseconds;

    @Override
    public long getLastMilliseconds() {
        return lastMilliseconds;
    }

    @Override
    @Required
    public void setLastMilliseconds(final long lastMilliseconds) {
        this.lastMilliseconds = lastMilliseconds;
    }

}
