package com.alibaba.rigel;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class Main {

	public static void main(String[] args) {
		Server server = new Server();
		Connector connector = new SelectChannelConnector();
		connector.setPort(8080);

		WebAppContext handler = new WebAppContext("src/main/webapp/", "/");

		server.setConnectors(new Connector[] { connector });
		server.setHandler(handler);

		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
