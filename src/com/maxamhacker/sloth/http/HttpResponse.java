package com.maxamhacker.sloth.http;

public class HttpResponse {
	
	private String method;
	private String status;
	private String message;
	private String httpVersion;

	public HttpResponse(HttpRequest request) {
		
		method = request.getMethod().toString();
		status = "400";
		message = "Bad request";
		httpVersion = request.getHttpVersion();
	}
	
	public String toString() {
		return 
			method + " " +
			status + " " +
			message;
	}
	
}
