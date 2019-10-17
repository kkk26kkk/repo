package com.kkk26kkk.common.code;

import org.apache.commons.lang3.StringUtils;

public enum Path {
	Articles("/articles"),
	Write("/write", Path.Articles),
	Modify("/modify", Path.Articles),
	Reply("/reply", Path.Articles),
	Comments("/comments", Path.Articles),
	User("/user"),
	Login("/login", Path.User),
	SignUp("/join", Path.User),
	Logout("/logout", Path.User);
	
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
	
	public boolean containedTo(String realPath) {
		if(StringUtils.contains(realPath, path)) {
			return true;
		}
		
		return false;
	}
	
}