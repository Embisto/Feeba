package com.feeba.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;




public class ServerController extends Thread {
	
	private static Server server = new Server(8080);

	public static void startServer() {
		
		ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[] { "index.html" });
        resource_handler.setResourceBase(".");
        HandlerList handlers = new HandlerList();
        
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/"); 
        context.addServlet(new ServletHolder(new MainServlet()),"/*");
      
        handlers.setHandlers(new Handler[] { resource_handler, context });
        server.setHandler(handlers);
        
	    try {
			server.start();
		    server.join();
		    
		} catch (Exception e) {
			
			System.err.println("Failed to start server");
			e.printStackTrace();
			
		}
	}
	
	public static void stopServer() {
		
		try {
			server.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isStarted() {

		return server.isStarted();
	}
	
	public static String getIp() {
		
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return "Fehler beim abrufen der Ip-Adresse";
	}

	public static void showGui() {
	
		ServerGUI.main(null);
		
	}
}
