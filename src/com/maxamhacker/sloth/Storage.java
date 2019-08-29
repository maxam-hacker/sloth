package com.maxamhacker.sloth;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
	
	AtomicLong IDs = new AtomicLong(1);
	
	public enum Status {
		OK,
		UserNotFound,
		UserAlreadyExist,
		InternalError
	}
	
	public class Result {
		public Status status;
		public Object data;
		
		public Result(Status status, Object data) {
			this.status = status;
			this.data = data;
		}
	}
	
	private class User {
		
		private String id;
		private String name;
		private long value;
		private Lock valueLock = new ReentrantLock();
		
		public User(String id, String name, String value) {
			this.id = id;
			this.name = name;
			setValue(Long.parseLong(value));
		}
		
		public String getName() {
			return this.name;
		}
		
		public long getValue() {
			long result = -1;
			if (valueLock.tryLock()) {
				try {
					result = value;
				} finally {
					valueLock.unlock();
				}
			}
			return result;
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
	
	public Result addUser(String name, String value) {
		
		if (name == null || name.isEmpty() || value == null || value.isEmpty())
			return new Result(Status.InternalError, null);
		
		name = name.replaceAll("[^a-zA-Z0-9]","");
		if (name == null || name.isEmpty())
			return new Result(Status.InternalError, null);
		
		try {
			Long.parseLong(value);
		} catch(Exception e) {
			return new Result(Status.InternalError, null);
		}
		
		if (mirrorForNames.get(name) != null)
			return new Result(Status.UserAlreadyExist, null);
		
		long id = IDs.incrementAndGet();
		User newUser = new User(String.valueOf(id), name, value);
		registry.put(String.valueOf(id), newUser);
		mirrorForNames.put(name, newUser);
		
		return new Result(Status.OK, String.valueOf(id));
	}
	
	public Result getUserValue(String id) {
		
		if (registry.get(id) != null) {
			long value = registry.get(id).getValue();
			return new Result(Status.OK, value);
		}
		
		return new Result(Status.UserNotFound, null);
		
	}
	
	public Result userInc(String id, String delta) {
		
		try {
			Long.parseLong(delta);
		} catch(Exception e) {
			return new Result(Status.InternalError, null);
		}
		
		if (registry.get(id) != null) {
			User user = registry.get(id);
			long value = user.getValue() + Long.parseLong(delta);
			user.setValue(value);
			return new Result(Status.OK, value);
		}
		
		return new Result(Status.UserNotFound, null);
	}
	
	public Result userDec(String id, String delta) {
		
		try {
			Long.parseLong(delta);
		} catch(Exception e) {
			return new Result(Status.InternalError, null);
		}
		
		if (registry.get(id) != null) {
			User user = registry.get(id);
			long value = user.getValue() - Long.parseLong(delta);
			user.setValue(value);
			return new Result(Status.OK, value);
		}
		
		return new Result(Status.UserNotFound, null);
	}
	
	public void startTransaction() {
		
	}
	
	public void closeTransaction() {
		
	}
	
	public void dropTransaction() {
		
	}
}
