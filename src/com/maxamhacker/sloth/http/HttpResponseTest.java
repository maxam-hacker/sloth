package com.maxamhacker.sloth.http;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HttpResponseTest {

	@Test
	public void NullTest1() {
		HttpResponse response = new HttpResponse(null);
		assertEquals(true, response.toString().startsWith("HTTP/1.1 400 Bad Request"));
	}
	
	
	@Test
	public void NullTest2() {
		HttpResponse response = new HttpResponse(new HttpRequest());
		assertEquals(true, response.toString().startsWith("HTTP/1.1 400 Bad Request"));
	}
	
	@Test
	public void NullTest3() {
		HttpRequest request = new HttpRequest();
		request.doLine("GET www.theseniorprogrammer.com HTTP/1.1");
		HttpResponse response = new HttpResponse(request);
		assertEquals(true, response.toString().startsWith("HTTP/1.1 404 Not Found"));
	}
	
}
