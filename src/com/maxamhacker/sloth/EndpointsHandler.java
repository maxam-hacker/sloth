package com.maxamhacker.sloth;

import java.util.HashMap;

import com.maxamhacker.sloth.http.HttpResponse;
import com.maxamhacker.sloth.http.HttpResponseStatus;
import com.maxamhacker.sloth.http.HttpRequest;
import com.maxamhacker.sloth.http.HttpRequestProcessor;

public class EndpointsHandler extends HttpRequestProcessor {
	
	private Storage theStorage = new Storage();

	public void doGet(HttpRequest request, HttpResponse response) {
		
		if (request == null || request.isNotValid() || response == null)
			return;
		
		String path = request.getUri().getPath();
		HashMap<String, String> params = request.getUri().getParams();
		if (path == null || params == null)
			return;
		
		switch (path) {
		
			case "/new":
				String name = params.get("name");
				String value = params.get("value");
				
				if (name == null || name.isEmpty() || value == null || value.isEmpty()) {
					response.setStatus(HttpResponseStatus.MethodNotAllowed);
					return;
				}
				
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
				if (id == null || id.isEmpty()) {
					response.setStatus(HttpResponseStatus.MethodNotAllowed);
					return;
				}
				result = theStorage.getUserValue(id);
				if (result.status == Storage.Status.OK) {
					response.setStatus(HttpResponseStatus.OK);
					response.setBody(String.format("<html><body><text>User: %s, value: %d</text></body></html>", id, result.data));
				} else if (result.status == Storage.Status.UserNotFound) {
					response.setStatus(HttpResponseStatus.OK);
					response.setBody(String.format("<html><body><text>User: %s, NotFound</text></body></html>", id));
				}
				break;
				
			case "/transfer":
				String from = params.get("from");
				String to = params.get("to");
				String delta = params.get("delta");
				
				if (from == null || from.isEmpty() || 
					to == null || to.isEmpty() || 
					delta == null || delta.isEmpty()) {
					response.setStatus(HttpResponseStatus.MethodNotAllowed);
					return;
				}
				
				Storage.Result resultFrom = theStorage.getUserValue(from);
				Storage.Result resultTo = theStorage.getUserValue(to);
				
				if (resultFrom.status == Storage.Status.OK && resultTo.status == Storage.Status.OK) {
					
					long valueFrom = (long)resultFrom.data;
					long valueTo = (long)resultTo.data;
					
					if (valueFrom < Long.parseLong(delta)) {
						response.setStatus(HttpResponseStatus.OK);
						response.setBody(String.format(
								"<html><body><text>Not transferred (Limit). From user: %s to user: %s, value: %s</text></body></html>",
								from, to, delta));
					} else {
						theStorage.startTransaction();
						if (theStorage.userDec(from, delta).status != Storage.Status.OK) {
							theStorage.dropTransaction();
							response.setStatus(HttpResponseStatus.InternalServerError);
							return;
						}
						if (theStorage.userInc(to, delta).status != Storage.Status.OK) {
							theStorage.dropTransaction();
							response.setStatus(HttpResponseStatus.InternalServerError);
							return;
						}
						theStorage.closeTransaction();
						response.setStatus(HttpResponseStatus.OK);
						response.setBody(String.format(
								"<html><body><text>Tansferred. From user: %s to user: %s, value: %s</text></body></html>",
								from, to, delta));
					}
				}
				break;
				
		}
		
	}

}