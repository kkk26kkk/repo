package com.kkk26kkk.common.model;

public enum Path {
	Article("/board_view"),
	ArticleList("/board_list");
	
	private String path;
	
	Path(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
}
