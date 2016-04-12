package net.breakidea.app;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * Starts an embedded Jetty server running our application.
 */
public class Main {

    private static final String CONTEXT_PATH = "/";

    private static final int HTTP_PORT = 8080;

    /**
     * @param args
     */
    public static void main( String[] args ) {
        Server server = new Server();

        SocketConnector connector = new SocketConnector();
        connector.setMaxIdleTime(1000 * 60 * 60);
        connector.setSoLingerTime(-1);
        connector.setPort(HTTP_PORT);

        server.setConnectors(new Connector[] { connector });
        server.setSendServerVersion(true);

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
            System.exit(100);
        }
    }
}
