package com.kkk26kkk.bbs.model;

public class UserDto {
	private String userId;
	private String userPw;
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
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
