package com.kkk26kkk.common.model;

public enum Path {
	WriteForm("/board/write"),
	UpdateForm("/board/update"),
	ReplyForm("/board/reply"),
	Write("/board"),
	Update("/board"),
	Article("/board"),
	Delete("/board"),
	Reply("/board"),
	ArticleList("/board/list"),
	Comment("/comment");
	
	private String path;
	
	Path(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
}
