package com.whiteroom.ani2gether.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private static int idSeed = 1;
	
	private String name;
	private int id;
	private String ip;

	public void setRID() {
		id = idSeed;
		idSeed++;
	}

}
