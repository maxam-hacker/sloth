package com.maxamhacker.sloth.http;

import java.util.LinkedHashMap;

public class HttpResponse {
	
	private String method;
	private HttpResponseStatus status;
	private String httpVersion;
	private LinkedHashMap<String, String> headers = new LinkedHashMap();
	private String body;

	public HttpResponse(HttpRequest request) {
		
		method = request.getMethod().toString();
		status = HttpResponseStatus.BadRequest;
		httpVersion = request.getHttpVersion();
		
		headers.put("Server", 			"TheSloth-0.0");
		headers.put("Content-Type", 		"text/html");
		headers.put("Content-Length", 	"0");
		headers.put("Connection", 		"close");
		
		body = "";
	}
	
	public void setStatus(HttpResponseStatus status) {
		this.status = status;
	}
	
	public void setBody(String body) {
		this.body = body;
		headers.replace("Content-Length", String.valueOf(body.length()));
	}
	
	public String toString() {
		
		StringBuilder response = new StringBuilder();
		
		response.append(httpVersion).append("\u0020");
		response.append(String.valueOf(status.getCode())).append("\u0020");
		response.append(status.getDescription()).append("\r\n");
			
		headers.entrySet().forEach( header -> {
			response.append(header.getKey());
			response.append(":");
			response.append(header.getValue());
			response.append("\r\n");
		});
		response.append("\r\n");
			
		response.append(body);

		return response.toString();
	}
	
}
