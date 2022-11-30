package com.sourabh.Response;

public class UserResponse {
	private int id;
	private String token;

	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserResponse(int id, String token) {
		super();
		this.id = id;
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
