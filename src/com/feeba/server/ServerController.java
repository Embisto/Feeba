package com.feeba.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;



public class ServerController extends Thread {
	
	private static Server server = new Server(8080);

	public static void startServer() {
	
		
		ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[] { "/WebApp/index.html" });
        resource_handler.setResourceBase(".");
        HandlerList handlers = new HandlerList();
      
        handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
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
}
