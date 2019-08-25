package com.maxamhacker.sloth.http;

public class HttpRequestURI {
	
	// https://www.ietf.org/rfc/rfc1808.txt
	
	private String value;
	
	public HttpRequestURI(String uri) {
		this.value = uri;
	}
	
	public String toString() {
		return this.value;
	}

}
