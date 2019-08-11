package com.kkk26kkk.bbs.model;

import java.util.Date;

public class CommentVo {
	private int commentId;
	private int articleId;
	private String userId;
	private String userName;
	private String contents;
	private int parentId;
	private Date regDtm;
	private String code;
	
	protected int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	protected int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
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
	protected Date getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(Date regDtm) {
		this.regDtm = regDtm;
	}
	protected String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
