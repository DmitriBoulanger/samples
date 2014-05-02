package dbo.samples.elk.logstash.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.ityx.insight.logstash.client.ElasticSearch;
import de.ityx.insight.logstash.client.Logstash;

/**
 * Base class for all configuration factories in the Cartesian-Product.
 * @author Dmitri Boulanger
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */

public class ConfigurationFactory {
    private static Logger        log           = LoggerFactory.getLogger(ConfigurationFactory.class);

    public static final String   CONFIG        = "classpath*:**/insight-logstash*.xml";

    private static ElasticSearch esServer      = null;
    private static Logstash      logstash      = null;

    private ConfigurationFactory() {
        // no instances of this class
    }

    static final void reset() {
        esServer = null;
        logstash = null;
    }

    /**
     * default ES-Server
     * @return
     */
    public synchronized static final ElasticSearch es() {
        if (null == esServer) {
            esServer = (ElasticSearch) ctx(CONFIG).getBean("esServer");
        }
        return esServer;
    }

    /**
     * default logstash
     * @return
     */
    public synchronized static final Logstash logstash() {
        if (null == logstash) {
            logstash = (Logstash) ctx(CONFIG).getBean("logstash");
        }
        return logstash;
    }

    protected static final ApplicationContext ctx(final String name) {
        log.trace("Config from resource: name=" + name + " name=" + name + " ...");
        return new ClassPathXmlApplicationContext(name);
    }
}
