package de.dbo.samples.elk.logstash.es.client;



/**
 * ES-Server attributes
 *
 * @author Dmitri Boulanger
 *
 * Programs are meant to be read by humans and only incidentally for computers to execute (D. Knuth)
 *
 */
public final class ElasticSearch  {


    private String  host;
    private Integer port;
    private String  cluster;
    private int     maxReturn;
    private String  timestampFormat;
    private String  timezone;

    public ElasticSearch(final String cluster, final String host, final Integer port) {
        this.cluster = cluster;
        this.host = host;
        this.port = port;
    }

    
    public StringBuilder print() {
        final StringBuilder ret = new StringBuilder("ES-Server:");
        ret.append("\n\t- cluster: " + cluster);
        ret.append("\n\t- host: " + host);
        ret.append("\n\t- port: " + port);
        return ret;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getCluster() {
        return cluster;
    }

    public int getMaxReturn() {
        return maxReturn;
    }

    
    public String getTimestampFormat() {
        return timestampFormat;
    }

    
    public String getTimezone() {
        return timezone;
    }

    
    
    public void setHost(String host) {
        this.host = host;
    }

    
    
    public void setPort(Integer port) {
        this.port = port;
    }

    
    
    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    
    
    public void setMaxReturn(int maxReturn) {
        this.maxReturn = maxReturn;
    }

    
    
    public void setTimestampFormat(final String timestampFormat) {
        this.timestampFormat = timestampFormat;
    }

    
    
    public void setTimezone(final String timezone) {
        this.timezone = timezone;
    }

}
