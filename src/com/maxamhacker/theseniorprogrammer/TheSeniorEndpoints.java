package com.maxamhacker.theseniorprogrammer;

import java.util.HashMap;

import com.maxamhacker.sloth.http.HttpRequest;
import com.maxamhacker.sloth.http.HttpRequestProcessor;
import com.maxamhacker.sloth.http.HttpResponse;
import com.maxamhacker.sloth.http.HttpResponseStatus;

public class TheSeniorEndpoints extends HttpRequestProcessor {
	
	public void doGet(HttpRequest request, HttpResponse response) {
		
		if (request == null || request.isNotValid() || response == null) 
			return;
		
		String path = request.getUri().getPath();
		HashMap<String, String> params = request.getUri().getParams();
		if (path == null)
			return;
		
		/* Get all available topics */
		if (path.startsWith("/groups")) {
			String groups = "spring,spring-boot,tomcat,jetty,Java VM,Chrome,V8,Hazelcast,PostgeSQL,Tarantool,ReactJS";
			response.setStatus(HttpResponseStatus.OK);
			response.setBody(groups);
				
		/* Get content of the topic */
		} else if (path.startsWith("/topic")) {
			if (params == null) 
				return;
			System.out.println(params.get("topic"));
			response.setStatus(HttpResponseStatus.OK);
			response.setBody("spring-framework, spring-boot, spring-data-commons");
			
		/* Get CodeHacker for the topic */
		} else if (path.startsWith("/codehacker")) {
			if (params == null) 
				return;
			System.out.println(params.get("topic"));
			response.setStatus(HttpResponseStatus.OK);
			response.setBody("spring-framework, spring-boot, spring-data-commons");
		}
	}

	public void doPost(HttpRequest request, HttpResponse response) {
		
	}
	
	public void doPut(HttpRequest request, HttpResponse response) {
		
	}
	
	public void doDelete(HttpRequest request, HttpResponse response) {
		
	}

}
