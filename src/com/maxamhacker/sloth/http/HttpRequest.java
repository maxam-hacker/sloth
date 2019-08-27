package com.maxamhacker.sloth.http;

public class HttpRequest {
	
	private HttpMethod method;
	private HttpRequestURI uri;
	private String httpVersion;
	
	public HttpRequest() {
		
	}
	
	public void doLine(String line) {
		String[] args = line.split(" ");
		
		switch (args[0]) {
		
			case "GET":
				method = HttpMethod.GET;
				break;
				
			case "POST":
				method = HttpMethod.POST;
				break;
				
			case "PUT":
				method = HttpMethod.PUT;
				break;
				
			case "DELETE":
				method = HttpMethod.DELETE;
				break;
		}
		
		uri = new HttpRequestURI(args[1]);
		
		httpVersion = args[2];
	}
	
	public void doHeaders(String headers) {
		
	}
	
	public void doBody(String body) {
		
	}
	
	public HttpRequestURI getUri() {
		return uri;
	}
	
	public String getHttpVersion() {
		return httpVersion;
	}
	
	public HttpMethod getMethod() {
		return method;
	}
}
