
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

###Maven features
This Maven-project a leaf. It inherits most of all its resources from the parent.
Special resources are:

- logstash-api
- es-api 
