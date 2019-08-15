package com.kkk26kkk.bbs.model;

import com.kkk26kkk.common.model.BaseParam;

public class CommentParam extends BaseParam {
	private final int articleId;
	private final String userId;
	
	public static class Builder extends BaseParam.Builder<Builder> {
		private int articleId;
		private String userId;
		
		public Builder(int pageSize, int articleId) {
			super(pageSize);
			this.articleId = articleId;
		}
		
		public Builder(int startNum, int endNum, int articleId) {
			super(startNum, endNum);
			this.articleId = articleId;
		}
		
		public Builder articleId(int articleId) {
			this.articleId = articleId;
			return this;
		}
		
		public Builder userId(String userId) {
			this.userId = userId;
			return this;
		}
		
		@Override
		public CommentParam build() {
			super.build();
			return new CommentParam(this);
		}
	}
	
	public CommentParam(Builder builder) {
		super(builder);
		this.articleId = builder.articleId;
		this.userId = builder.userId;
	}

	public int getArticleId() {
		return articleId;
	}
	
	public String getUserId() {
		return userId;
	}
}
