package com.maxamhacker.sloth.http;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HttpProcessorTest {
	
	@Test
	public void requestParseURI() {
		HttpRequest response = HttpProcessor.doRequest("GET http://localhost:7070/transfer?user_id=70&value=1000 HTTP 1.1", "", "");
		assertEquals(HttpMethod.GET, response.getMethod());
	}

}
