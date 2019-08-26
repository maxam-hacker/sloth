package com.maxamhacker.sloth.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.maxamhacker.sloth.http.HttpProcessor;
import com.maxamhacker.sloth.http.HttpRequest;
import com.maxamhacker.sloth.http.HttpRequestProcessor;
import com.maxamhacker.sloth.http.HttpResponse;

public class BasicTcpServer {
	
	private Class<? extends HttpRequestProcessor> processor;
	
	private class Worker extends Thread {
		
		private Socket socket;
		
		public Worker(Socket socket) {
			this.socket = socket;
		}
		
		public void run() {
			
			try {
	            InputStream  in  = socket.getInputStream();
	            OutputStream out = socket.getOutputStream();
	            
	            // https://www.w3.org/Protocols/rfc2616/rfc2616-sec5.html
	            String requestLine = null;
	            StringBuilder requestHeaders = new StringBuilder();
	            StringBuilder requestBody = new StringBuilder();
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	            
	            if (reader.ready())
	            	requestLine = reader.readLine();
	            else
	            	return;
	            

	            while (reader.ready()) {
	            	String header = reader.readLine();
	            	requestHeaders.append(header);
	                if (header.length() == 0)
	                	break;
	            }
	            
	            while (reader.ready()) {
	            	String body = reader.readLine();
	            	requestBody.append(body);
	                if (body.length() == 0)
	                	break;
	            }
	            
	            HttpRequest request = HttpProcessor.doRequest(
	            		requestLine,
	            		requestHeaders.toString(),
	            		requestBody.toString());
	            
	            HttpResponse response = new HttpResponse();
	                
	            //out.write(response.getBytes());
	            //out.flush();
	                
	            socket.close();

	        } catch(Exception e) {
	            System.out.println("Exception : " + e);
	        }
		}
		
	}
	
	public BasicTcpServer withProcessor(Class<? extends HttpRequestProcessor> processor) {
		this.processor = processor;
		return this;
	}
	
	public void start(String host, int port) {
		
		ServerSocket server = null;
        try {
            try {
               
                InetAddress addr = InetAddress.getByName(host);
                server = new ServerSocket(port, 0, addr);

                System.out.println("Server started\n\n");

                while(true) {

                    Socket socket = server.accept();
                    System.err.println("Client accepted");

                    new Worker(socket).start();
                }
            } catch(Exception e) {
                System.out.println("Exception : " + e);
            }
            
        } finally {
        	
            try {
                if (server != null)
                    server.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
	}

}
