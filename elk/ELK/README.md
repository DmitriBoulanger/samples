
Customized ELK-installation
===========================

The ELK-installation includes

	ElasticSearch server
	Logstash server
	Kibana3 (ES plug-in)
	Marvel (ES plug-in)

Kibana3 and Maven are parts of the ES-server. They are installed as plug-ins. 

Kibana3 and Marvel are extended with customized dash-boards (JSON-files).
Logstash customization includes customized configuration file and the corresponding
start-up file. ES-customization needs configuration file.

The customization can be done as synchonization of the directories 

	elasticsearch-1.0.1
	logstash-1.3.3
	
with actual ELK-installation