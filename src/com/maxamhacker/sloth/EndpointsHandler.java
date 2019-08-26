package com.maxamhacker.sloth;

import com.maxamhacker.sloth.http.HttpRequest;
import com.maxamhacker.sloth.http.HttpRequestProcessor;
import com.maxamhacker.sloth.http.HttpResponse;

public class EndpointsHandler extends HttpRequestProcessor {

	public EndpointsHandler(HttpRequest request, HttpResponse response) {
		super(request, response);
	}

}
