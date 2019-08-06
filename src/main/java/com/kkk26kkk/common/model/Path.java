package com.kkk26kkk.common.model;

import org.apache.commons.lang3.StringUtils;

public enum Path {
	WriteForm("/board/write"),
	UpdateForm("/board/update"),
	ReplyForm("/board/reply"),
	Article("/board"),
	ArticleList("/board/list"),
	Comment("/comment");
	
	private String path;
	
	Path(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	public boolean compare(String realPath) {
		if(StringUtils.equals(path, realPath)) {
			return true;
		}
		
		return false;
	}
	
}
