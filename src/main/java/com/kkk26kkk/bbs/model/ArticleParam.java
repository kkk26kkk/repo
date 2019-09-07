package com.kkk26kkk.bbs.model;

import com.kkk26kkk.common.model.BaseParam;

public class ArticleParam extends BaseParam {
	private final String sort;
	private final String userId;
	private final String userGrade;
	private final String articleId;
	
	public static class Builder extends BaseParam.Builder<Builder> {
		private String sort;
		private String userId;
		private String userGrade;
		private String articleId;
		
		public Builder(int pageSize) {
			super(pageSize);
		}
		
		public Builder sort(String sort) {
			this.sort = sort;
			return this;
		}
		
		public Builder userId(String userId) {
			this.userId = userId;
			return this;
		}
		
		public Builder userGrade(String userGrade) {
			this.userGrade = userGrade;
			return this;
		}
		
		public Builder articleId(String articleId) {
			this.articleId = articleId;
			return this;
		}
		
		@Override
		public ArticleParam build() {
			super.build();
			return new ArticleParam(this);
		}
	}
	
	public ArticleParam(Builder builder) {
		super(builder);
		this.sort = builder.sort;
		this.userId = builder.userId;
		this.userGrade = builder.userGrade;
		this.articleId = builder.articleId;
	}
	
	public String getSort() {
		return sort;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String userGrade() {
		return userGrade;
	}
	
	public String getArticleId() {
		return articleId;
	}
	
}
