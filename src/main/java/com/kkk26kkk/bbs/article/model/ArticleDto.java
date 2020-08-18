package com.kkk26kkk.bbs.article.model;

import java.util.List;

import com.kkk26kkk.bbs.comment.model.CommentDto;

public class ArticleDto {
	private String articleId;
	private String parentId;
	private String userId;
	private String userName;
	private String title;
	private String contents;
	private String readCount; // int? String?
	private String regDtm;
	private String grade;
	private String link;
	private boolean isNotice;
	
	private List<CommentDto> comments;
	private Integer cPage;
	private Integer cTotalPage;
	private Boolean cHasNext;
	
	private ArticleDto rootArticle;

	public ArticleDto() {}
	
	public ArticleDto(String articleId, String userName, String title) {
		this.articleId = articleId;
		this.userName = userName;
		this.title = title;
	}
	
	public ArticleDto(String articleId, String userName, String title, String contents) {
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
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
	public List<CommentDto> getComments() {
		return comments;
	}
	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
	public int getcPage() {
		return cPage;
	}
	public void setcPage(int cPage) {
		this.cPage = cPage;
	}
	public int getcTotalPage() {
		return cTotalPage;
	}
	public void setcTotalPage(int cTotalPage) {
		this.cTotalPage = cTotalPage;
	}
	public boolean iscHasNext() {
		return cHasNext;
	}
	public void setcHasNext(boolean cHasNext) {
		this.cHasNext = cHasNext;
	}
	public ArticleDto getRootArticle() {
		return rootArticle;
	}
	public void setRootArticle(ArticleDto rootArticle) {
		this.rootArticle = rootArticle;
	}
	
}
