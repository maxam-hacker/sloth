package com.maxamhacker.sloth;

import com.maxamhacker.sloth.http.HttpResponse;
import com.maxamhacker.sloth.http.HttpRequest;
import com.maxamhacker.sloth.http.HttpRequestProcessor;

public class EndpointsHandler extends HttpRequestProcessor {

	public void doGet(HttpRequest request, HttpResponse response) {
		
		String path = request.getUri().getValue().getPath();
		
		switch (path) {
		
			case "/user":
			
				break;
				
			case "/transfer":
				
				break;
		}
		
	}

}