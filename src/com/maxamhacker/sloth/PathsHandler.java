package com.maxamhacker.sloth;

import java.util.HashMap;
import java.util.Iterator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.maxamhacker.sloth.http.HttpRequest;
import com.maxamhacker.sloth.http.HttpRequestProcessor;
import com.maxamhacker.sloth.http.HttpResponse;
import com.maxamhacker.sloth.http.HttpResponseStatus;

public class PathsHandler extends HttpRequestProcessor {
	
	private String projectsPrexif = "~/workspace";
	
	public void doGet(HttpRequest request, HttpResponse response) {
			
			if (request == null || request.isNotValid() || response == null)
				return;
			
			String path = request.getUri().getPath();
			HashMap<String, String> params = request.getUri().getParams();
			if (path == null || params == null)
				return;
			
			switch (path) {
			
				case "file":
					Iterator<String> files = params.keySet().iterator();
					while (files.hasNext()) {
						String fullPath = projectsPrexif + files.next();
						try {
							String content = new String(Files.readAllBytes(Paths.get(fullPath)));
							response.setHeader("Content-Type", "text/html");
							response.setBody(content);
							response.setStatus(HttpResponseStatus.OK);
						} catch (IOException e) {
						}
					}
					break;
			
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
