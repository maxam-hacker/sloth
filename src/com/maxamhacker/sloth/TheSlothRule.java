package com.maxamhacker.sloth;

import org.junit.rules.ExternalResource;

import com.maxamhacker.sloth.server.BasicTcpServer;

public class TheSlothRule extends ExternalResource {
	
	@Override
	protected void before() throws Throwable {
		new BasicTcpServer()
			.withProcessor(new EndpointsHandler())
			.start("localhost", 7070);
	}
	
	@Override
	protected void after() {

	}

}
