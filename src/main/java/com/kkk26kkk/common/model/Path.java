package com.kkk26kkk.common.model;

import org.apache.commons.lang3.StringUtils;

public enum Path {
	Board("/board"),
	WriteForm("/write", Path.Board),
	UpdateForm("/update", Path.Board),
	ReplyForm("/reply", Path.Board),
	ArticleList("/list", Path.Board),
	Comment("/comment", Path.Board),
	LoginForm("/user/loginForm"),
	SignUpForm("/user/signUpForm"),
	Logout("/user/logout");
	
	private String path;
	private Path parent;
	
	Path(String path) {
		this.path = path;
	}
	
	Path(String path, Path parent) {
		this.path = path;
		this.parent = parent;
	}

	public String getPath() {
		if(null != parent) {
			return parent.getPath() + path;
		}
		return path;
	}

	public String getRestPath(String resource) {
		if(null != parent) {
			return parent.getPath() + "/" + resource + path;
		}
		return path + "/" + resource;
	}

	public String getMethod() {
		return path;
	}
	
	public boolean compare(String realPath) {
		if(StringUtils.equals(realPath, path)) {
			return true;
		}
		
		return false;
	}
	
	// TODO ~~To 단어 찾아서 메소드 이름 수정
	public boolean 포함되다오른쪽거에(String realPath) {
		if(StringUtils.contains(realPath, path)) {
			return true;
		}
		
		return false;
	}
	
}