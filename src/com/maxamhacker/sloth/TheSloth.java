package com.maxamhacker.sloth;

import com.maxamhacker.sloth.server.BasicTcpServer;

public class TheSloth {

	public static void main(String[] args) {
		
		new BasicTcpServer()
			.withProcessor(new EndpointsHandler())
			.start("localhost", 7070);
	}
	
}
