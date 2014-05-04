
###Contents
The most simple usage of the Log4j socket-appender with Logstash as a remote server.

###Test description
The test in the *src/test/java/de.dbo.samples.elk.logstash.client0* consists of:

- message generator **ApplicationMessages**
- test itself **ElasticSearchClientTest**

To run the test, do the following:

- check parameters of the ElasticSerach-server in ESLClientTest.init() - method
- start stand-alone ElasticSerach-server
- start Logstash
- run the ApplicationMessages a little bit and then stop it
- run the test itself

###Optional Kibana-based Monitor

Copy the file **Log4JMonitor-test.json** from  *src/main/scripts/* into 
the Kibana directory <HOME>src/app/dashboards/ and then use the following link to monitor test-events

[http://localhost:9200/_plugin/kibana3/src/index.html#/dashboard/file/Log4jMonitor-test.json](http://localhost:9200/_plugin/kibana3/src/index.html#/dashboard/file/Log4jMonitor-test.json)

Another way is to install the Log4JMonitor from the GitHub-Gist

[https://gist.github.com/DmitriBoulanger/2803b71ae2a5d6bcd15f](https://gist.github.com/DmitriBoulanger/2803b71ae2a5d6bcd15f)

To do that, start default Kibana and use *Load/Advanced/Gist* menu to install the above

**Note:** The browser should support HTML5

###Maven features
This Maven-project a leaf. It inherits most of all its resources from the parent.
Special resources are:

- logstash-api
- es-api 
