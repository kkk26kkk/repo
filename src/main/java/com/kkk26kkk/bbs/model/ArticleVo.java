package com.kkk26kkk.bbs.model;

import java.util.Date;

public class ArticleVo {
	private int articleId;
	private int boardId;
	private String userId;
	private String userName;
	private String title;
	private String contents;
	private int parentId;
	private int readCount;
	private Date regDtm;
	
	protected int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	protected int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	protected String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	protected String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	protected String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	protected String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	protected int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	protected int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	protected Date getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(Date regDtm) {
		this.regDtm = regDtm;
	}

}
