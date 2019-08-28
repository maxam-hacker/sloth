package com.maxamhacker.sloth.http;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HttpProcessorTest {
	
	@Test
	public void NullTest0() {
		HttpRequest response = HttpProcessor.doRequest(null, null, null);
		assertEquals(response, null);
	}
	
	@Test
	public void NullTest1() {
		HttpRequest response = HttpProcessor.doRequest("", "", "");
		assertEquals(response, null);
	}
	
	@Test
	public void requestParseURI() {
		HttpRequest response = HttpProcessor.doRequest("GET http://localhost:7070/transfer?user_id=70&value=1000 HTTP/1.1", "", "");
		assertEquals(HttpMethod.GET, response.getMethod());
		assertEquals("HTTP/1.1", response.getHttpVersion());
		assertEquals("/transfer", response.getUri().getPath());
	}

}
