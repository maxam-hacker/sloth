package com.maxamhacker.sloth.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.maxamhacker.sloth.http.HttpProcessor;
import com.maxamhacker.sloth.http.HttpRequest;
import com.maxamhacker.sloth.http.HttpRequestProcessor;
import com.maxamhacker.sloth.http.HttpResponse;

public class BasicTcpServer {
	
	private HttpRequestProcessor processor;
	private ThreadPoolExecutor threadPoolExecutor;
	private Acceptor acceptor;
	
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
	            Timestamp start = new Timestamp(System.currentTimeMillis());
	            Timestamp current = new Timestamp(System.currentTimeMillis());
	            while (true) {
		            if (reader.ready()) {
		            		requestLine = reader.readLine();
		            		current = start;
		            } else {
		            		current = new Timestamp(System.currentTimeMillis());
		            		if (current.getTime() - start.getTime() >= 2500) {
		            			break;
		            		} else {
		            			continue;
		            		}
		            }
		            
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
		            
		            System.out.println(requestLine);
		            System.out.println(requestHeaders.toString());
		            System.out.println(requestBody.toString());
		            
		            request = HttpProcessor.doRequest(
		            		requestLine,
		            		requestHeaders.toString(),
		            		requestBody.toString());
		            if (request == null)
		            		continue;
		            
		            response = new HttpResponse(request);
		            
		            processor.handleMessage(request, response);
		            String responseText = response.toString();
		            
		            if (responseText != null) {
			            	out.write(responseText.getBytes());
			            	out.flush();
			            start = new Timestamp(System.currentTimeMillis());
		            }
	            }
	            
	            in.close();
	            out.close();
	            socket.close();
	            System.out.println("Client is closed");

	        } catch(Exception e) {
	            System.out.println("Exception in worker: " + e);
	        }
		}
		
	}
	
	public class Acceptor extends Thread {
		
		private String host;
		private int port;
		
		public Acceptor(String host, int port) {
			this.host = host;
			this.port = port;
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
	                    threadPoolExecutor.execute(new Worker(socket));
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
		
		this.threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		this.acceptor = new Acceptor(host, port);
		this.acceptor.start();
		
        return this;
	}
	
	public BasicTcpServer stop() {
		
		this.acceptor.interrupt();
		this.threadPoolExecutor.shutdownNow();
		
		return this;
	}
	

}
