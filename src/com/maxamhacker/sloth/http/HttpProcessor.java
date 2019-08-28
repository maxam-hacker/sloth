package com.maxamhacker.sloth.http;

public class HttpProcessor {
	
	// https://www.w3.org/Protocols/rfc2616/rfc2616-sec5.html
	
	public static HttpRequest doRequest(String line, String headers, String body) {
		
		if (line == null || line.isEmpty())
			return null;
		
		HttpRequest request = new HttpRequest();
		request.doLine(line);
		if (headers != null && !headers.isEmpty())
			request.doHeaders(headers);
		if (body != null && !body.isEmpty())
			request.doBody(body);
		
		return request;
	}

}
