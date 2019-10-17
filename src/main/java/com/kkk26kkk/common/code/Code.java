package com.kkk26kkk.common.code;

import org.apache.commons.lang3.StringUtils;

public enum Code {
	COMMENT_SECRET_TYPE_PUBLIC("10"),
	COMMENT_SECRET_TYPE_PRIVATE("20"),
	COMMENT_SECRET_TYPE_REPORTED("30");
	
	private String code;
	
	Code(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public boolean compare(String realCode) {
		if(StringUtils.equals(code, realCode)) {
			return true;
		}
		
		return false;
	}
	
}
