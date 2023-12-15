package com.livec.auth.dto;

// objectif : ne pas exposer la vraie classe User dans le controlleur
public class UserDto {
	
	private String name;
	private String password;
	
	public UserDto() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
