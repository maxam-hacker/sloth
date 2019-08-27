package com.maxamhacker.sloth.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.maxamhacker.sloth.http.HttpProcessor;
import com.maxamhacker.sloth.http.HttpRequest;
import com.maxamhacker.sloth.http.HttpRequestProcessor;
import com.maxamhacker.sloth.http.HttpResponse;

public class BasicTcpServer {
	
	private HttpRequestProcessor processor;
	
	private class Worker implements Runnable {
		
		private Socket socket;
		
		public Worker(Socket socket) {
			this.socket = socket;
		}
		
		public void run() {
			
			HttpRequest request = null;
			HttpResponse response = null;
			String requestLine = null;
            StringBuilder requestHeaders = new StringBuilder();
            StringBuilder requestBody = new StringBuilder();
            InputStream in = null;
            OutputStream out = null;
			try {
	            in  = socket.getInputStream();
	            out = socket.getOutputStream();
	            
	            // https://www.w3.org/Protocols/rfc2616/rfc2616-sec5.html
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
	            
	            request = HttpProcessor.doRequest(
	            		requestLine,
	            		requestHeaders.toString(),
	            		requestBody.toString());
	            
	            response = new HttpResponse(request);
	            
	            processor.handleMessage(request, response);
	            String responseText = response.toString();
	            
	            if (responseText != null) {
	            		out.write(responseText.getBytes());
	            		out.flush();
	            }
	            
	            in.close();
	            out.close();
	            socket.close();

	        } catch(Exception e) {
	            System.out.println("Exception in worker: " + e);
	        }
		}
		
	}
	
	public class Acceptor extends Thread {
		
		private String host;
		private int port;
		ThreadPoolExecutor threadPoolExecutor;
		
		public Acceptor(String host, int port) {
			this.host = host;
			this.port = port;
			this.threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		}
		
		public void run() {
			
			ServerSocket server = null;
	        try {
	            try {
	               
	                InetAddress addr = InetAddress.getByName(this.host);
	                server = new ServerSocket(this.port, 0, addr);

	                System.out.println("Server started\n\n");

	                while(true) {

	                    Socket socket = server.accept();
	                    System.err.println("Client accepted");

	                    //new Worker(socket).start();
	                    this.threadPoolExecutor.execute(new Worker(socket));
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
	
	public BasicTcpServer withProcessor(HttpRequestProcessor processor) {
		this.processor = processor;
		return this;
	}
	
	public BasicTcpServer start(String host, int port) {
		
		new Acceptor(host, port).start();
        return this;
	}
	

}
