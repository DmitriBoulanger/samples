package dbo.samples.elk.logstash.config;

import org.springframework.beans.factory.annotation.Required;

import de.ityx.insight.logstash.client.ElasticSearch;

/**
 * ES-Server attributes
 *
 * @author Dmitri Boulanger
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */
public final class ElasticSearchImpl implements ElasticSearch {


    private String  host;
    private Integer port;
    private String  cluster;
    private int     maxReturn;
    private String  timestampFormat;
    private String  timezone;

    /**
     * Default (standard) instance of the ES-Server
     */
    public ElasticSearchImpl() {
        this(CLUSTER, HOST, PORT);
    }

    public ElasticSearchImpl(final String cluster) {
        this(cluster, HOST, PORT);
    }

    public ElasticSearchImpl(final String cluster, final String host, final Integer port) {
        this.cluster = cluster;
        this.host = host;
        this.port = port;
    }

    @Override
    public StringBuilder print() {
        final StringBuilder ret = new StringBuilder("ES-Server:");
        ret.append(" Cluster=" + cluster);
        ret.append(" Host=" + host);
        ret.append(" Port=" + port);
        return ret;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public Integer getPort() {
        return port;
    }

    @Override
    public String getCluster() {
        return cluster;
    }

    @Override
    public int getMaxReturn() {
        return maxReturn;
    }

    @Override
    public String getTimestampFormat() {
        return timestampFormat;
    }

    @Override
    public String getTimezone() {
        return timezone;
    }

    @Override
    @Required
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    @Required
    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    @Required
    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    @Override
    @Required
    public void setMaxReturn(int maxReturn) {
        this.maxReturn = maxReturn;
    }

    @Override
    @Required
    public void setTimestampFormat(final String timestampFormat) {
        this.timestampFormat = timestampFormat;
    }

    @Override
    @Required
    public void setTimezone(final String timezone) {
        this.timezone = timezone;
    }

}
