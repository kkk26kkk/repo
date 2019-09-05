package com.kkk26kkk.bbs.model;

import com.kkk26kkk.common.model.BaseParam;

public class ArticleParam extends BaseParam {
	private final String sort;
	private final String userId;
	private final String articleId;
	
	public static class Builder extends BaseParam.Builder<Builder> {
		private String sort;
		private String userId;
		private String articleId;
		
		public Builder(int pageSize) {
			super(pageSize);
		}
		
		public Builder sort(String sort) {
			this.sort = sort;
			return this;
		}
		
		public Builder articleId(String articleId) {
			this.articleId = articleId;
			return this;
		}
		
		public Builder userId(String userId) {
			this.userId = userId;
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
		this.articleId = builder.articleId;
		this.userId = builder.userId;
	}
	
	public String getSort() {
		return sort;
	}

	public String getArticleId() {
		return articleId;
	}
	
	public String getUserId() {
		return userId;
	}
	
}
