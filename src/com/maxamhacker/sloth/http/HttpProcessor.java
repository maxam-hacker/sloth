package com.maxamhacker.sloth.http;

public class HttpProcessor {
	
	// https://www.w3.org/Protocols/rfc2616/rfc2616-sec5.html
	
	public static HttpRequest doRequest(String line, String headers, String body) {
		
		HttpRequest request = new HttpRequest();
		request.doLine(line);
		request.doHeaders(headers);
		request.doBody(body);
		
		return request;
	}

}
