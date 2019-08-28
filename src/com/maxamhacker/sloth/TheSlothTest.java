package com.maxamhacker.sloth;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TheSlothTest {
	
	@Test
	public void Test1() {
		
		URL urlCommand = null;
		HttpURLConnection response = null;
		String responleLine = null;
		StringBuilder body = new StringBuilder();
		try {
			urlCommand = new URL("http://localhost:7070/new?name=andrey&value=100");
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
		assertEquals("<html><body><text>New User: andrey, id: 2, value: 100</text></body></html>", body.toString());
	}
	
	
	@Test
	public void Test2() {
		
		URL urlCommand = null;
		HttpURLConnection response = null;
		String responleLine = null;
		StringBuilder body = new StringBuilder();
		try {
			urlCommand = new URL("http://localhost:7070/new?name=andrey&value=100");
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
		assertEquals("<html><body><text>User: andrey, alredy exist</text></body></html>", body.toString());
	}
	
	
	@Test
	public void Test3() {
		
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
	public void Test4() {
		
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
	public void Test5() {
		
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
	public void Test6() {
		
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
	public void Test7() {
		
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
	public void Test8() {
		
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
	public void Test9() {
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://localhost:7070/transfer?from=2&to=3&delta=100");
		System.out.println(request.getRequestLine());
		try {
			HttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine());
			response = client.execute(request);
			System.out.println(response.getStatusLine());
		} catch (IOException e) {

		}
	}

}
