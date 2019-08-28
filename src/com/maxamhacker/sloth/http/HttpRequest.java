package com.maxamhacker.sloth.http;

public class HttpRequest {
	
	private HttpMethod method;
	private HttpRequestURI uri;
	private String httpVersion;
	
	public HttpRequest() {
	}
	
	public void doLine(String line) {
		
		this.method = null;
		this.uri = null;
		this.httpVersion = null;
		
		String[] args = line.split(" ");
		
		if (args.length != 3)
			return;
		
		switch (args[0]) {
		
			case "GET":
				this.method = HttpMethod.GET;
				break;
				
			case "POST":
				this.method = HttpMethod.POST;
				break;
				
			case "PUT":
				this.method = HttpMethod.PUT;
				break;
				
			case "DELETE":
				this.method = HttpMethod.DELETE;
				break;
		}
		
		try {
			this.uri = new HttpRequestURI(args[1]);
		} catch(Exception e) {
			this.uri = null;
		}
		
		this.httpVersion = args[2];
	}
	
	public void doHeaders(String headers) {
		
	}
	
	public void doBody(String body) {
		
	}
	
	public HttpRequestURI getUri() {
		return this.uri;
	}
	
	public String getHttpVersion() {
		return this.httpVersion;
	}
	
	public HttpMethod getMethod() {
		return this.method;
	}
}
