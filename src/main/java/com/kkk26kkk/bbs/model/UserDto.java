package com.kkk26kkk.bbs.model;

public class UserDto {
	private String userId;
	private String userName;
	
	UserDto() {}
	
	UserDto(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
