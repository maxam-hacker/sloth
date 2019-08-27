package com.maxamhacker.sloth.http;

public enum HttpResponseStatus {

	OK(200, "OK"),
	BadRequest(400, "Bad Request");
	
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
