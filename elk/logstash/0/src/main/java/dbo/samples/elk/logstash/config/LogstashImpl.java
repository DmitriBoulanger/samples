package dbo.samples.elk.logstash.config;

import org.springframework.beans.factory.annotation.Required;

import de.ityx.insight.logstash.client.Logstash;

public final class LogstashImpl implements Logstash {

    private String logType;
    private String indexNameExrension;
    private String indexSufffix;

    @Override
    public String getLogType() {
        return logType;
    }

    @Override
    public String getIndexNameExrension() {
        return indexNameExrension;
    }

    @Override
    public String getIndexSufffix() {
        return indexSufffix;
    }

    @Override
    @Required
    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Override
    @Required
    public void setIndexNameExrension(String indexNameExrension) {
        this.indexNameExrension = indexNameExrension;
    }

    @Override
    @Required
    public void setIndexSufffix(String indexSufffix) {
        this.indexSufffix = indexSufffix;
    }

}
