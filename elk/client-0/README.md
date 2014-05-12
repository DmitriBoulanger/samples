
### Contents
The most simple usage of the Log4j socket-appender with Logstash as a remote server.

### Test description
The test in the *src/test/java/de.dbo.samples.elk.client0* consists of:

		message generator Messages

		test ClientTest

To run the test, do the following:

- check parameters of the ElasticSerach-server in ClientTest.init() - method. If the default ELK is in use,
  then only cluster-name should be checked
- start stand-alone ElasticSerach-server
- start Logstash
- run the Messages a little bit and then stop it
- run the test itself

### Optional Kibana-based Log4J Monitor

Copy the file **log4j.json** from *src/main/scripts/* into 
the directory *<Kibana-HOME>src/app/dashboards/* and then use the below link to monitor test-events

[http://localhost:9200/_plugin/kibana3/src/index.html#/dashboard/file/log4j.json](http://localhost:9200/_plugin/kibana3/src/index.html#/dashboard/file/log4j.json)

Another way is to install the Log4J Monitor from the GitHub.Gist

[https://gist.github.com/DmitriBoulanger/2803b71ae2a5d6bcd15f](https://gist.github.com/DmitriBoulanger/2803b71ae2a5d6bcd15f)

Start default Kibana and use *Load/Advanced/Gist* menu to install the above

*Note:* The browser should support HTML5, e,g. FireFox

### Maven features
This Maven-project inherits its basic resources from the parent. Special resources:

		logstash-api
		es-api 
