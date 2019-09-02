package com.maxamhacker.sloth.http;

import java.util.HashMap;

public abstract class HttpRequestProcessor {
	
	public void handleMessage(HttpRequest request, HttpResponse response) {
		
		if (request == null || request.isNotValid() || response == null)
			return;
		
		String path = request.getUri().getPath();
		if (path == null)
			return;
		
		switch (request.getMethod()) {
		
			case GET:
				doGet(request, response);
				break;
				
			case POST:
				doPost(request, response);
				break;
				
			case PUT:
				doPut(request, response);
				break;
				
			case DELETE:
				doDelete(request, response);
				break;
		}
		
	}
	
	public void doGet(HttpRequest request, HttpResponse response) {
		
	}

	public void doPost(HttpRequest request, HttpResponse response) {
		
	}
	
	public void doPut(HttpRequest request, HttpResponse response) {
		
	}
	
	public void doDelete(HttpRequest request, HttpResponse response) {
		
	}
}
