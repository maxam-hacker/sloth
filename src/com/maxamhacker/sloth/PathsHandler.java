package com.maxamhacker.sloth;

import java.util.HashMap;
import java.util.Iterator;

import com.maxamhacker.sloth.http.HttpRequest;
import com.maxamhacker.sloth.http.HttpRequestProcessor;
import com.maxamhacker.sloth.http.HttpResponse;
import com.maxamhacker.sloth.http.HttpResponseStatus;

public class PathsHandler extends HttpRequestProcessor {
	
	public void doGet(HttpRequest request, HttpResponse response) {
			
			if (request == null || request.isNotValid() || response == null)
				return;
			
			String path = request.getUri().getPath();
			HashMap<String, String> params = request.getUri().getParams();
			if (path == null || params == null)
				return;
			
			switch (path) {
			
				case "/paths":
					Iterator<String> projects = params.keySet().iterator();
					while (projects.hasNext()) {
						String project = projects.next();
						if (project.equals("webtorrent")) {
							response.setBody("{ webtorrent }");
							response.setStatus(HttpResponseStatus.OK);
						}
					}
					break;
			}
	}
}
