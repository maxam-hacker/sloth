package com.maxamhacker.sloth;

import java.util.HashMap;

import com.maxamhacker.sloth.http.HttpResponse;
import com.maxamhacker.sloth.http.HttpResponseStatus;
import com.maxamhacker.sloth.http.HttpRequest;
import com.maxamhacker.sloth.http.HttpRequestProcessor;

public class EndpointsHandler extends HttpRequestProcessor {
	
	private Storage theStorage = new Storage();

	public void doGet(HttpRequest request, HttpResponse response) {
		
		String path = request.getUri().getPath();
		HashMap<String, String> params = request.getUri().getParams();
		
		switch (path) {
		
			case "/new":
				String name = params.get("name");
				String value = params.get("value");
				
				if (name == null || name.isEmpty())
					return;
				if (value == null || value.isEmpty())
					return;
				
				Storage.Result result = theStorage.addUser(name, value);

				if (result.status == Storage.Status.OK) {
					response.setStatus(HttpResponseStatus.OK);
					response.setBody(
						String.format("<html><body><text>New User: %s, id: %s, value: %s</text></body></html>", 
						name, result.data, value));
				} else if (result.status == Storage.Status.UserAlreadyExist) {
					response.setStatus(HttpResponseStatus.OK);
					response.setBody(
							String.format("<html><body><text>User: %s, alredy exist</text></body></html>", name));
				}
				break;
		
			case "/user":
				String id = params.get("id");
				if (id == null || id.isEmpty())
					return;
				value = "0";
				response.setStatus(HttpResponseStatus.OK);
				response.setBody(String.format("<html><body><text>User: %s, info: %s</text></body></html>", id, value));
				break;
				
			case "/transfer":
				String from = params.get("from");
				String to = params.get("to");
				String delta = params.get("delta");
				
				if (from == null || from.isEmpty())
					return;
				if (to == null || to.isEmpty())
					return;
				if (delta == null || delta.isEmpty())
					return;
				
				response.setStatus(HttpResponseStatus.OK);
				response.setBody(String.format(
						"<html><body><text>Tansferred. From user: %s to user: %s, Sum: %s</text></body></html>",
						from, to, delta));
				break;
		}
		
	}

}