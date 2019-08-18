package com.kkk26kkk.bbs.model;

import java.util.Date;

public class CommentVo {
	private String commentId;
	private String articleId;
	private String userId;
	private String userName;
	private String contents;
	private String parentId;
	private Date regDtm;
	private String code;
	
	protected String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	protected String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
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
	protected String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
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
