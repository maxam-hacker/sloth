package com.maxamhacker.sloth.http;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpRequestURI {
	
	// https://www.ietf.org/rfc/rfc1808.txt
	
	private String raw;
	private URI uri;
	
	public HttpRequestURI(String uri) {
		this.raw = uri;
		
		try {
			this.uri = new URI(uri);
		} catch (URISyntaxException e) {
			this.uri = null;
		}
	}
	
	public URI getValue() {
		return uri;
	}
	
	public String toString() {
		return this.raw;
	}

}
