package com.kkk26kkk.bbs.model;

public class ArticleDto {
	private int articleId;
	private String userId;
	private String userName;
	private String title;
	private String contents;
	private String readCount;
	private String regDtm;
	private String link;
	private String writeFormLink;
	private String updateFormLink;
	private String replyFormLink;
	private String deleteLink;
	private String commentLink;

	ArticleDto() {}
	
	ArticleDto(String userName, String title, String contents, String readCount) {
		this.userName = userName;
		this.title = title;
		this.contents = contents;
		this.readCount = readCount;
	}
	
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
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
	public String getWriteFormLink() {
		return writeFormLink;
	}
	public void setWriteFormLink(String writeFormLink) {
		this.writeFormLink = writeFormLink;
	}
	public String getUpdateFormLink() {
		return updateFormLink;
	}
	public void setUpdateFormLink(String updateFormLink) {
		this.updateFormLink = updateFormLink;
	}
	public String getReplyFormLink() {
		return replyFormLink;
	}
	public void setReplyFormLink(String replyFormLink) {
		this.replyFormLink = replyFormLink;
	}
	public String getDeleteLink() {
		return deleteLink;
	}
	public void setDeleteLink(String deleteLink) {
		this.deleteLink = deleteLink;
	}
	public String getCommentLink() {
		return commentLink;
	}
	public void setCommentLink(String commentLink) {
		this.commentLink = commentLink;
	}
	
}
