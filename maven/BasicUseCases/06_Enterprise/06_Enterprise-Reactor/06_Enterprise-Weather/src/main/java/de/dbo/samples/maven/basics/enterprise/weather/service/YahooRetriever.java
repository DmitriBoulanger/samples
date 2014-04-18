package de.dbo.samples.maven.basic.enterprise.weather.service;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YahooRetriever {

	private static Logger log = LoggerFactory.getLogger(WeatherFormatter.class);

	public InputStream retrieve(String zipcode) throws Exception {
		log.info( "Retrieving Weather Data" );
		String url = "http://weather.yahooapis.com/forecastrss?p=" + zipcode;
        // Add this if you need to connect via a corporate proxy
//      String proxyHost = "[proxy server]";
//      int proxyPort = [proxy server port];
//      SocketAddress addr = new InetSocketAddress(proxyHost, proxyPort);
//      Proxy httpProxy = new Proxy(Proxy.Type.HTTP, addr);
//      URLConnection conn = new URL(url).openConnection(httpProxy);
		URLConnection conn = new URL(url).openConnection();
		return conn.getInputStream();
	}

}
