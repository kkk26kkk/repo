package com.kkk26kkk.bbs.model;

import com.kkk26kkk.common.model.PageList;

public class ArticleDto {
	private String articleId;
	private String parentId;
	private String userId;
	private String userName;
	private String title;
	private String contents;
	private String readCount;
	private String regDtm;
	private String link;
	private boolean isNotice;
	private PageList<CommentDto> commentList;

	public ArticleDto() {}
	
	ArticleDto(String articleId, String userName, String title) {
		this.articleId = articleId;
		this.userName = userName;
		this.title = title;
	}
	
	ArticleDto(String articleId, String userName, String title, String contents) {
		this.articleId = articleId;
		this.userName = userName;
		this.title = title;
		this.contents = contents;
	}
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	public boolean isNotice() {
		return isNotice;
	}
	public void setNotice(boolean isNotice) {
		this.isNotice = isNotice;
	}
	public PageList<CommentDto> getCommentList() {
		return commentList;
	}
	public void setCommentList(PageList<CommentDto> commentList) {
		this.commentList = commentList;
	}
	
}
