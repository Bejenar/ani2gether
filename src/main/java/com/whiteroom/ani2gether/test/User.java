package com.whiteroom.ani2gether.test;

public class User {
	private static int idSeed = 1;
	
	private String name;
	private int id;
	private String ip;
	public User() {

	}
	
	public User(String name) {
		this.name = name;
	}
	
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setRID() {
		id = idSeed;
		idSeed++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
	
	
	
}
