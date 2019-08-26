package com.maxamhacker.sloth.http;

public class HttpRequest {
	
	private HttpMethod method;
	private HttpRequestURI uri;
	
	public void doLine(String line) {
		String[] args = line.split(" ");
		
		switch (args[0]) {
		case "GET":
			method = HttpMethod.GET;
			break;
		case "POST":
			method = HttpMethod.POST;
			break;
		}
		
		uri = new HttpRequestURI(args[1]);
	}

	public void doHeaders(String headers) {
		
	}
	
	public void doBody(String body) {
		
	}
	
	public HttpMethod getMethod() {
		return method;
	}
}
