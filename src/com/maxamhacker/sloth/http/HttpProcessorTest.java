package com.maxamhacker.sloth.http;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HttpProcessorTest {
	
	@Test
	public void NullTest0() {
		HttpRequest request = HttpProcessor.doRequest(null, null, null);
		assertEquals(request, null);
	}
	
	@Test
	public void NullTest1() {
		HttpRequest request = HttpProcessor.doRequest("", "", "");
		assertEquals(request, null);
	}
	
	@Test
	public void requestParseURI() {
		HttpRequest request = HttpProcessor.doRequest("GET http://localhost:7070/transfer?user_id=70&value=1000 HTTP/1.1", "", "");
		assertEquals(HttpMethod.GET, request.getMethod());
		assertEquals("HTTP/1.1", request.getHttpVersion());
		assertEquals("/transfer", request.getUri().getPath());
	}

}
