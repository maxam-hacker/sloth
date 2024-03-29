package com.maxamhacker.sloth.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class HttpRequestURI {
	
	// https://www.ietf.org/rfc/rfc1808.txt
	
	private String raw;
	private URI uri;
	private String path;
	private String query;
	private HashMap<String, String> params = new HashMap<String, String>();
	
	public HttpRequestURI(String uri) {
		this.raw = uri;
		
		try {
			this.uri = new URI(uri);
			this.path = this.uri.getPath();
			this.query = this.uri.getQuery();
			if (query != null) {
				String[] qargs = this.query.split("#");
				if (qargs.length > 0) {
					String[] pargs = qargs[0].split("&");
					for (int idx = 0; idx < pargs.length; idx ++) {
						String[] keyAndValue = pargs[idx].split("=");
						if (keyAndValue.length == 1)
							params.put(keyAndValue[0], null);
						else if (keyAndValue.length >= 2)
							params.put(keyAndValue[0], keyAndValue[1]);
					}
				}
			}
		} catch (URISyntaxException e) {
			this.uri = null;
		}
	}
	
	public URI getValue() {
		return uri;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public HashMap<String, String> getParams() {
		return this.params;
	}
	
	public String toString() {
		return this.raw;
	}

}
