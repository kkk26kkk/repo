package com.kkk26kkk.bbs.model;

import java.util.Date;

public class ArticleVo {
	private String articleId;
	private String boardId;
	private String userId;
	private String userName;
	private String title;
	private String contents;
	private String parentId;
	private int readCount;
	private Date regDtm;
	private String grade;
	
	protected String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	protected String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
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
	protected String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
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
	protected String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

}
