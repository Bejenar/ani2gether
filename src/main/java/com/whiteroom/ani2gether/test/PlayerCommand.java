package com.whiteroom.ani2gether.test;


public class PlayerCommand {

	private String command;
	private double timestamp;
	
	public PlayerCommand() {
		
	}

	public PlayerCommand(String command, double timestamp) {
		this.command = command;
		this.timestamp = timestamp;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public double getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "PlayerCommand [command=" + command + ", timestamp=" + timestamp + "]";
	}
	
	
	
	
	

	
}
