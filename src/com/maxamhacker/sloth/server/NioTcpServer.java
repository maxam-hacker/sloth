package com.maxamhacker.sloth.server;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

import com.maxamhacker.sloth.http.HttpRequestProcessor;
import com.maxamhacker.sloth.server.BasicTcpServer.Acceptor;

public class NioTcpServer {
	
	private HttpRequestProcessor processor;
	private ThreadPoolExecutor threadPoolExecutor;
	private Acceptor acceptor;
	private String host; 
	private int port;
	private int workerNumbers = 10;
	
	
	public NioTcpServer(String host, int port) {
		
		this.host = host;
		this.port = port;
	}
	
	
	public NioTcpServer withProcessor(HttpRequestProcessor processor) {
		
		this.processor = processor;
		
		return this;
	}
	
	
	public NioTcpServer start() {
		
		this.threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(workerNumbers);
		this.acceptor = new Acceptor(this.host, this.port);
		this.acceptor.start();
		
		return this;
	}
	
	
	public NioTcpServer stop() {
		
		this.threadPoolExecutor.shutdownNow();
		this.acceptor.interrupt();
		
		return this;
	}
	
	
	public class Worker implements Runnable {
		
		private SynchronousQueue<SocketChannel> inQueue;
		
		public Worker(SynchronousQueue<SocketChannel> queue) {
			this.inQueue = queue;
		}
		
		public void run() {
			SocketChannel channel = inQueue.poll();
		}
		
	}
	
	
	public class Acceptor extends Thread {
		
		private String host;
		private int port;
		private ArrayList<SynchronousQueue<SocketChannel>> queues;
	    private ServerSocketChannel serverSocket;
		
		public Acceptor(String host, int port) {
			
			this.host = host;
			this.port = port;
			this.queues = new ArrayList<SynchronousQueue<SocketChannel>>();
			for (int idx = 0; idx < workerNumbers; idx ++) {
				SynchronousQueue<SocketChannel> queue = new SynchronousQueue<SocketChannel>();
				Worker worker = new Worker(queue);
				threadPoolExecutor.submit(worker);
			}
		}
		
		
		public void run() {
			
			try {
	            this.serverSocket = ServerSocketChannel.open();
	            this.serverSocket.bind(new InetSocketAddress(this.host, this.port));
	            
	        } catch(Exception e){
	            return;
	        }

			int idx = 0;
		    while (true) {
		        	
		    		try{
		    			SocketChannel socketChannel = this.serverSocket.accept();
		            this.queues.get(idx++).add(socketChannel);
		            if (idx >= workerNumbers) 
		            		idx = 0;
		            
		        } catch(Exception e) {
		        }

	        }
		}
	}
	
}
