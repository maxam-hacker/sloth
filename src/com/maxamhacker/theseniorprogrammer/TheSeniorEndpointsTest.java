package com.maxamhacker.theseniorprogrammer;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TheSeniorEndpointsTest {
	
	@Test
	public void Test01() {
		
		URL urlCommand = null;
		HttpURLConnection response = null;
		String responleLine = null;
		StringBuilder body = new StringBuilder();
		try {
			urlCommand = new URL("http://localhost:17070/topics");
			System.out.println(urlCommand.toString());
			response = (HttpURLConnection) urlCommand.openConnection();
			responleLine = response.getResponseMessage();

			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getInputStream()));
			reader.lines().forEach(line -> body.append(line));
			reader.close();
			System.out.println(body);

		} catch (IOException e) {
		
		}

		assertEquals("OK", responleLine);
	}
	
	
	@Test
	public void Test02() {
		
		URL urlCommand = null;
		HttpURLConnection response = null;
		String responleLine = null;
		StringBuilder body = new StringBuilder();
		try {
			urlCommand = new URL("http://localhost:17070/topic/spring");
			System.out.println(urlCommand.toString());
			response = (HttpURLConnection) urlCommand.openConnection();
			responleLine = response.getResponseMessage();

			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getInputStream()));
			reader.lines().forEach(line -> body.append(line));
			reader.close();
			System.out.println(body);

		} catch (IOException e) {
		
		}

		assertEquals("OK", responleLine);
	}
	
	/*
	@Test
	public void Test03() {
		
		URL urlCommand = null;
		HttpURLConnection response = null;
		String responleLine = null;
		StringBuilder body = new StringBuilder();
		try {
			urlCommand = new URL("http://localhost:7070/new?name=elena&value=100");
			System.out.println(urlCommand.toString());
			response = (HttpURLConnection) urlCommand.openConnection();
			responleLine = response.getResponseMessage();

			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getInputStream()));
			reader.lines().forEach(line -> body.append(line));
			reader.close();
			System.out.println(body);

		} catch (IOException e) {
		
		}

		assertEquals("OK", responleLine);
		assertEquals("<html><body><text>New User: elena, id: 3, value: 100</text></body></html>", body.toString());
	}
	
	@Test
	public void Test04() {
		
		URL urlCommand = null;
		HttpURLConnection response = null;
		String responleLine = null;
		StringBuilder body = new StringBuilder();
		try {
			urlCommand = new URL("http://localhost:7070/transfer?from=2&to=3&delta=100");
			System.out.println(urlCommand.toString());
			response = (HttpURLConnection) urlCommand.openConnection();
			responleLine = response.getResponseMessage();

			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getInputStream()));
			reader.lines().forEach(line -> body.append(line));
			reader.close();
			System.out.println(body);

		} catch (IOException e) {
		
		}

		assertEquals("OK", responleLine);
		assertEquals("<html><body><text>Tansferred. From user: 2 to user: 3, value: 100</text></body></html>", body.toString());
	}
	
	@Test
	public void Test05() {
		
		URL urlCommand = null;
		HttpURLConnection response = null;
		String responleLine = null;
		StringBuilder body = new StringBuilder();
		try {
			urlCommand = new URL("http://localhost:7070/user?id=2");
			System.out.println(urlCommand.toString());
			response = (HttpURLConnection) urlCommand.openConnection();
			responleLine = response.getResponseMessage();

			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getInputStream()));
			reader.lines().forEach(line -> body.append(line));
			reader.close();
			System.out.println(body);

		} catch (IOException e) {
		
		}

		assertEquals("OK", responleLine);
		assertEquals("<html><body><text>User: 2, value: 0</text></body></html>", body.toString());
	}
	
	@Test
	public void Test06() {
		
		URL urlCommand = null;
		HttpURLConnection response = null;
		String responleLine = null;
		StringBuilder body = new StringBuilder();
		try {
			urlCommand = new URL("http://localhost:7070/user?id=3");
			System.out.println(urlCommand.toString());
			response = (HttpURLConnection) urlCommand.openConnection();
			responleLine = response.getResponseMessage();

			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getInputStream()));
			reader.lines().forEach(line -> body.append(line));
			reader.close();
			System.out.println(body);

		} catch (IOException e) {
		
		}

		assertEquals("OK", responleLine);
		assertEquals("<html><body><text>User: 3, value: 200</text></body></html>", body.toString());
	}
	
	@Test
	public void Test07() {
		
		URL urlCommand = null;
		HttpURLConnection response = null;
		String responleLine = null;
		StringBuilder body = new StringBuilder();
		try {
			urlCommand = new URL("http://localhost:7070/transfer?from=2&to=3&delta=100");
			System.out.println(urlCommand.toString());
			response = (HttpURLConnection) urlCommand.openConnection();
			responleLine = response.getResponseMessage();

			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getInputStream()));
			reader.lines().forEach(line -> body.append(line));
			reader.close();
			System.out.println(body);

		} catch (IOException e) {
		
		}

		assertEquals("OK", responleLine);
		assertEquals("<html><body><text>Not transferred (Limit). From user: 2 to user: 3, value: 100</text></body></html>", body.toString());
	}
	
	@Test
	public void Test08() {
		
		URL urlCommand = null;
		HttpURLConnection response = null;
		String responleLine = null;
		StringBuilder body = new StringBuilder();
		try {
			urlCommand = new URL("http://localhost:7070/transfer?from=2&to=3&delta=100");
			System.out.println(urlCommand.toString());
			response = (HttpURLConnection) urlCommand.openConnection();
			responleLine = response.getResponseMessage();

			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getInputStream()));
			reader.lines().forEach(line -> body.append(line));
			reader.close();
			System.out.println(body);

		} catch (IOException e) {
		
		}

		assertEquals("OK", responleLine);
		assertEquals("<html><body><text>Not transferred (Limit). From user: 2 to user: 3, value: 100</text></body></html>", body.toString());
	}
	
	@Test
	public void Test09() {
		
		CloseableHttpClient client = HttpClients.createMinimal();
		HttpGet request = new HttpGet("http://localhost:7070/transfer?from=2&to=3&delta=100");
		System.out.println(request.getRequestLine());
		String first = "", second = "";
		try {
			HttpResponse response = client.execute(request);
			first = response.getStatusLine().toString();
			System.out.println(first);
			response = client.execute(request);
			second = response.getStatusLine().toString();
			System.out.println(second);
			client.close();
		} catch (IOException e) {

		}
		assertEquals("HTTP/1.1 200 OK", first);
		assertEquals("HTTP/1.1 200 OK", second);
	}
	
	
	@Test
	public void Test10() {
		
		CloseableHttpClient client = HttpClients.createMinimal();
		HttpGet request = new HttpGet("http://localhost:7070/transfer?from=2&to=3&delta=100");
		System.out.println(request.getRequestLine());
		String first = "", second = "";
		try {
			HttpResponse response = client.execute(request);
			first = response.getStatusLine().toString();
			System.out.println(first);
			Thread.sleep(4000);
			response = client.execute(request);
			second = response.getStatusLine().toString();
			System.out.println(second);
			client.close();
		} catch (Exception e) {

		}
		assertEquals("HTTP/1.1 200 OK", first);
		assertEquals("HTTP/1.1 200 OK", second);
	}
	*/

}
