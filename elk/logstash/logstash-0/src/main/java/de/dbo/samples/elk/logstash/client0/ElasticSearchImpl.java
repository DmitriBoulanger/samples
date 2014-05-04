package de.dbo.samples.elk.logstash.client0;

import de.dbo.samples.elk.es.ElasticSearch;



/**
 * ES-Server attributes
 *
 * @author Dmitri Boulanger
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */
public final class ElasticSearchImpl implements ElasticSearch  {

    private String  host = "localhost";
    private Integer port = 9300;
    private String  cluster = "elasticsearch";
    
    private String  timestampFormat;
    private String  timezone;

    private int     maxReturn;
    
    /**
     * creates basic parameters of the remote ES-server
     * @param cluster cluster-name used in ES-server
     * @param host host of the ES-server
     * @param port port used in ES-server (see transport.tcp.port in <ES-home>\config\elasticsearch.yml)
     */
    public ElasticSearchImpl(final String cluster, final String host, final Integer port) {
        if (null!=cluster) {
			this.cluster = cluster;
		}
		this.host = host;
        this.port = port;
    }
    
    /**
     * creates basic parameters of the remote ES-server with the default cluster-name
     * @param host host of the ES-server
     * @param port port used in ES-server (see transport.tcp.port in <ES-home>\config\elasticsearch.yml)
     */
    public ElasticSearchImpl(final String host, final Integer port) {
        this(null,host,port);
    }

    public StringBuilder print() {
        final StringBuilder ret = new StringBuilder("ES-Server:");
        ret.append("\n\t- cluster: " + cluster);
        ret.append("\n\t- host: " + host);
        ret.append("\n\t- port: " + port);
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
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    @Override
    public void setMaxReturn(int maxReturn) {
        this.maxReturn = maxReturn;
    }

    @Override
    public void setTimestampFormat(final String timestampFormat) {
        this.timestampFormat = timestampFormat;
    }

    @Override
    public void setTimezone(final String timezone) {
        this.timezone = timezone;
    }

}
