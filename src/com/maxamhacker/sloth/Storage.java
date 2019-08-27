package com.maxamhacker.sloth;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
	
	AtomicLong IDs = new AtomicLong(1);
	
	private class User {
		
		private String id;
		private String name;
		private long value;
		private Lock valueLock = new ReentrantLock();
		
		public User(String id, String name, long value) {
			this.id = id;
			this.name = name;
			setValue(value);
		}
		
		public String getName() {
			return this.name;
		}
		
		public long getValue() {
			return this.value;
		}
		
		public void setValue(long value) {
			if (valueLock.tryLock()) {
				try {
					this.value = value;
				} finally {
					valueLock.unlock();
				}
			}
		}
	}
	
	private ConcurrentHashMap<String, User> registry = new ConcurrentHashMap<String, User>();
	private ConcurrentHashMap<String, User> mirrorForNames = new ConcurrentHashMap<String, User>();
	
	public int addUser(String name, long value) {
		
		if (mirrorForNames.contains(name))
			return -1;
		
		long id = IDs.incrementAndGet();
		User newUser = new User(String.valueOf(id), name, value);
		registry.put(String.valueOf(id), newUser);
		mirrorForNames.put(name, newUser);
		
		return 0;
	}
	
	public long getUserValue(String id) {
		
		long value = -1;
		
		if (registry.contains(id))
			value = registry.get(id).getValue();
		
		return value;
	}
	
	public int userInc(String id, long delta) {
		
		if (registry.contains(id)) {
			User user = registry.get(id);
			long value = user.getValue() + delta;
			user.setValue(value);
			return 0;
		}
		
		return -1;
	}
}
