package com.kkk26kkk.bbs.model;

public class ArticleDto {
	private String userName;
	private String title;
	private String contents;
	private String readCount;
	private String regDtm;
	private String link;
	
	ArticleDto() {}
	
	ArticleDto(String userName, String title, String contents, String readCount) {
		this.userName = userName;
		this.title = title;
		this.contents = contents;
		this.readCount = readCount;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}
	public String getReadCount() {
		return readCount;
	}
	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}
	
}
