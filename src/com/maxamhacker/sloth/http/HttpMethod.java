package com.maxamhacker.sloth.http;

public enum HttpMethod {
	
	GET("GET"),
	POST("POST"),
	PUT("PUT"),
	DELETE("DELETE");
	
	private String name;
	
	HttpMethod(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "HTTP Method: " + name;
	}

}
