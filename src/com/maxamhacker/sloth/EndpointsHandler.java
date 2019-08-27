package com.maxamhacker.sloth;

import java.util.HashMap;

import com.maxamhacker.sloth.http.HttpResponse;
import com.maxamhacker.sloth.http.HttpResponseStatus;
import com.maxamhacker.sloth.http.HttpRequest;
import com.maxamhacker.sloth.http.HttpRequestProcessor;

public class EndpointsHandler extends HttpRequestProcessor {

	public void doGet(HttpRequest request, HttpResponse response) {
		
		String path = request.getUri().getPath();
		HashMap<String, String> params = request.getUri().getParams();
		
		switch (path) {
		
			case "/user":
				String id = params.get("id");
				if (id == null || id.isEmpty())
					return;
				long value = 0;
				response.setStatus(HttpResponseStatus.OK);
				response.setBody(String.format("<html><body><text>User: %s, info: %d</text></body></html>", id, value));
				break;
				
			case "/transfer":
				String from = params.get("from");
				String to = params.get("to");
				String sum = params.get("sum");
				
				if (from == null || from.isEmpty())
					return;
				if (to == null || to.isEmpty())
					return;
				if (sum == null || sum.isEmpty())
					return;
				
				response.setStatus(HttpResponseStatus.OK);
				response.setBody(String.format(
						"<html><body><text>Tansferred. From user: %s to user: %s, Sum: %s</text></body></html>",
						from, to, sum));
				break;
		}
		
	}

}