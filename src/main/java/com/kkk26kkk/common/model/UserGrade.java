package com.kkk26kkk.common.model;

public enum UserGrade {
	ADMINISTRATOR("30"),
	COMMON_USER("10");
	
	private String userGrade;
	
	UserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
	
	public String getUserGrade() {
		return this.userGrade;
	}
}
