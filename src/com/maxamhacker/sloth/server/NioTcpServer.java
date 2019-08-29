package com.maxamhacker.sloth.server;

import java.util.concurrent.ThreadPoolExecutor;

import com.maxamhacker.sloth.http.HttpRequestProcessor;
import com.maxamhacker.sloth.server.BasicTcpServer.Acceptor;

public class NioTcpServer {
	
	private HttpRequestProcessor processor;
	private ThreadPoolExecutor threadPoolExecutor;
	private Acceptor acceptor;
	private String host; 
	private int port;
	
	
	public NioTcpServer(String host, int port) {
		
		this.host = host;
		this.port = port;
	}
	
	
	public NioTcpServer withProcessor(HttpRequestProcessor processor) {
		
		this.processor = processor;
		
		return this;
	}
	
	
	public NioTcpServer start() {
		
		this.acceptor = new Acceptor(this.host, this.port);
		this.acceptor.start();
		
		return this;
	}
	
	
	public NioTcpServer stop() {
		
		this.acceptor.interrupt();
		
		return this;
	}
	
	
	public class Worker implements Runnable {
		
		public void run() {
			
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
			
		}
	}
	
}
