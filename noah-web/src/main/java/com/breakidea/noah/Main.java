package com.breakidea.noah;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class Main {

	public static final String MAIN_WEBAPP = "src/main/webapp/";

	public static final String CONTEXT_PATH = "/";

	public static void main(String[] args) {
		Server server = new Server();

		Connector connector = new SelectChannelConnector();
		connector.setPort(8080);

		WebAppContext handler = new WebAppContext(MAIN_WEBAPP, CONTEXT_PATH);

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
