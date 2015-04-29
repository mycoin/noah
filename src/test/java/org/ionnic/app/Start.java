package org.ionnic.app;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * Starts an embedded Jetty server running our application.
 * 
 * @author ajesler
 * 
 */
public class Start {
	/**
	 * The path to the web app on the server. Relative to root (/)
	 */
	private static final String CONTEXT_PATH = "/";
	/**
	 * The port the server should run on.
	 */
	private static final int PORT = 8080;

	public static void main(String[] args) {
		Server server = new Server();

		SocketConnector connector = new SocketConnector();
		connector.setMaxIdleTime(1000 * 60 * 60);
		connector.setSoLingerTime(-1);
		connector.setPort(PORT);

		server.setConnectors(new Connector[]{connector});

		// makes the web app available at localhost:PORT/CONTEXT_PATH (with only
		// one slash between PORT and CONTEXT_PATH, eg
		// http://localhost:8086/rest)
		WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath(CONTEXT_PATH);
		bb.setWar("src/main/webapp");
		server.addHandler(bb);

		try {
			System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
			server.start();
			// press any key to stop the web server.
			System.in.read();
			System.out.println(">>> STOPPING EMBEDDED JETTY SERVER");

			server.stop();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}

	}
}
