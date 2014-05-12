package de.dbo.samples.elk.es;

import java.text.ParseException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Default standalone ElasticSearch at the localhost
 *
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and
 *           only incidentally for computers to execute
 *
 */
public class ElasticSearchDefault implements ElasticSearch {

	protected String host = "localhost";
	protected String cluster = "elasticsearch";
	protected String node = null;
	protected Integer port = 9300;
    protected String  timestampFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    protected String  timezone        = "UTC";
	protected int maxReturn = 1000;

	public ElasticSearchDefault() {

	}

	@Override
	public final Client elasticsearchClient() {
        final Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", getCluster())
                .put("network.host", getHost())
                .build();
        final TransportClient ret = new TransportClient(settings);
        ret.addTransportAddress(new InetSocketTransportAddress(getHost(), getPort()));
        return ret;
    }

	@Override
	 public StringBuilder print() {
        final StringBuilder ret = new StringBuilder();
        ret.append(" cluster: " + cluster);
        ret.append(" host: " + host + ":" + port);
        return ret;
    }

	@Override
    public final long timestamp(final String timestampES) throws ParseException {
        final DateTimeZone esDateTimeZone = DateTimeZone.forID(getTimezone());
        final String estTmestampPattern = getTimestampFormat();
        final DateTimeFormatter formatter = DateTimeFormat.forPattern(estTmestampPattern).withZone(esDateTimeZone);
        final long ret = formatter.parseDateTime(timestampES).getMillis();
        return ret;
    }

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public String getNode() {
		return node;
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
	public void setNode(String node) {
		this.node = node;
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
	public void setTimestampFormat(String timestampFormat) {
		this.timestampFormat = timestampFormat;

	}

	@Override
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
}
