package com.maxamhacker.sloth.http;

public enum HttpResponseStatus {

	Continue(100, "Continue"),
	OK(200, "OK"),
	PartialContent(206, "Partial Content"),
	BadRequest(400, "Bad Request"),
	NotFound(404, "Not Found"),
	MethodNotAllowed(405, "Method Not Allowed");
	
	private int code;
	private String description;
	
	HttpResponseStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}
}
