package com.kkk26kkk.bbs.comment.model;

import com.kkk26kkk.common.model.BaseParam;

public class CommentParam extends BaseParam {
	private final String articleId;
	private final String userId;
	
	public static class Builder extends BaseParam.Builder<Builder> {
		private String articleId = "";
		private String userId = "";
		
		public Builder(int pageSize, String articleId) {
			super(pageSize);
			this.articleId = articleId;
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
		public CommentParam build() {
			super.build();
			return new CommentParam(this);
		}
	}
	
	private CommentParam(Builder builder) {
		super(builder);
		this.articleId = builder.articleId;
		this.userId = builder.userId;
	}

	public String getArticleId() {
		return articleId;
	}
	
	public String getUserId() {
		return userId;
	}
}
