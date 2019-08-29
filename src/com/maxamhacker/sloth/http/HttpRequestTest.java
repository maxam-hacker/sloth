package com.maxamhacker.sloth.http;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HttpRequestTest {
	
	@Test
	public void NullTest() {
		HttpRequest request = new HttpRequest();
		request.doLine(null);
		assertEquals(null, request.getMethod());
		assertEquals(null, request.getHttpVersion());
		assertEquals(null, request.getUri());
	}
	
	
	@Test
	public void EmptyTest1() {
		HttpRequest request = new HttpRequest();
		request.doLine("");
		assertEquals(null, request.getMethod());
		assertEquals(null, request.getHttpVersion());
		assertEquals(null, request.getUri());
	}
	
	
	@Test
	public void EmptyTest2() {
		HttpRequest request = new HttpRequest();
		request.doLine("         ");
		assertEquals(null, request.getMethod());
		assertEquals(null, request.getHttpVersion());
		assertEquals(null, request.getUri());
	}

}
