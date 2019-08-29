package com.maxamhacker.sloth;

import com.maxamhacker.sloth.http.HttpRequest;
import com.maxamhacker.sloth.http.HttpResponse;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EndpointsHandlerTest {

	@Test
	public void NullTest() {
		EndpointsHandler handler = new EndpointsHandler();
		handler.doGet(null, null);
	}
	
	@Test
	public void Test0() {
		EndpointsHandler handler = new EndpointsHandler();
		HttpRequest request = new HttpRequest();
		HttpResponse response = new HttpResponse();
		handler.doGet(request, response);
		assertEquals(true, response.toString().startsWith("HTTP/1.1 400 Bad Request"));
	}
	
	@Test
	public void Test1() {
		EndpointsHandler handler = new EndpointsHandler();
		HttpRequest request = new HttpRequest();
		request.doLine("GET www.theseniorprogrammer.com HTTP/1.1");
		HttpResponse response = new HttpResponse();
		handler.doGet(request, response);
		assertEquals(true, response.toString().startsWith("HTTP/1.1 400 Bad Request"));
	}
	
	@Test
	public void Test2() {
		EndpointsHandler handler = new EndpointsHandler();
		HttpRequest request = new HttpRequest();
		request.doLine("GET www.theseniorprogrammer.com HTTP/1.1");
		HttpResponse response = new HttpResponse(request);
		handler.doGet(request, response);
		assertEquals(true, response.toString().startsWith("HTTP/1.1 404 Not Found"));
	}
	
	
	@Test
	public void Test3() {
		EndpointsHandler handler = new EndpointsHandler();
		HttpRequest request = new HttpRequest();
		request.doLine("GET www.theseniorprogrammer.com/new HTTP/1.1");
		HttpResponse response = new HttpResponse(request);
		handler.doGet(request, response);
		assertEquals(true, response.toString().startsWith("HTTP/1.1 404 Not Found"));
	}
	
}
