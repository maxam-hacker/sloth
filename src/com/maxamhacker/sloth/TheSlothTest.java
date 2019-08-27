package com.maxamhacker.sloth;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TheSlothTest {
	
	@Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	
	
	@Test
	public void Test1addMe() {
		
		URL urlCommand = null;
		HttpURLConnection response = null;
		String responleLine = null;
		StringBuilder body = new StringBuilder();
		try {
			urlCommand = new URL("http://localhost:7070/new?name=andrey&value=10000000000000");
			response = (HttpURLConnection) urlCommand.openConnection();
			responleLine = response.getResponseMessage();

			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getInputStream()));
			reader.lines().forEach(line -> body.append(line));
			reader.close();
			System.out.println(body);

		} catch (IOException e) {
		
		}

		assertEquals("OK", responleLine);
		assertEquals("<html><body><text>New User: andrey, id: 2, value: 10000000000000</text></body></html>", body.toString());
	}
	
	
	@Test
	public void Test2addMeAgain() {
		
		URL urlCommand = null;
		HttpURLConnection response = null;
		String responleLine = null;
		StringBuilder body = new StringBuilder();
		try {
			urlCommand = new URL("http://localhost:7070/new?name=andrey&value=10000000000000");
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

}
