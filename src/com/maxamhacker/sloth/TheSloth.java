package com.maxamhacker.sloth;

import com.maxamhacker.sloth.server.BasicTcpServer;
import com.maxamhacker.theseniorprogrammer.TheSeniorEndpoints;

public class TheSloth {

	public static void main(String[] args) {
		
		new BasicTcpServer()
			.withProcessor(new TheSeniorEndpoints())
			.start("localhost", 17070);
	}
	
}
