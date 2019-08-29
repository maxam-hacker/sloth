package com.maxamhacker.sloth;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StorageTest {

	@Test
	public void NullTest() {
		Storage storage = new Storage();
		Storage.Result result = storage.addUser(null, null);
		assertEquals(Storage.Status.InternalError, result.status);
	}
	
	
	@Test
	public void EmptyTest0() {
		Storage storage = new Storage();
		Storage.Result result = storage.addUser("   ", "   ");
		assertEquals(Storage.Status.InternalError, result.status);
	}
	
	
	@Test
	public void EmptyTest1() {
		Storage storage = new Storage();
		Storage.Result result = storage.addUser("nik", "   ");
		assertEquals(Storage.Status.InternalError, result.status);
	}
	
	
	@Test
	public void BadValueTest() {
		Storage storage = new Storage();
		Storage.Result result = storage.addUser("nik", "387456jfhb");
		assertEquals(Storage.Status.InternalError, result.status);
	}
	
	
	@Test
	public void FilterBadNameTest() {
		Storage storage = new Storage();
		Storage.Result result = storage.addUser("nik@", "100000000");
		assertEquals(Storage.Status.OK, result.status);
	}
	
}
