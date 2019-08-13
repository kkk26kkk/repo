package com.kkk26kkk.bbs.model;

import com.kkk26kkk.common.model.BaseParam;

public class CommentParam extends BaseParam {
	private final int articleId;
	
	public static class Builder extends BaseParam.Builder<Builder> {
		private int articleId;
		
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
		
		@Override
		public CommentParam build() {
			super.build();
			return new CommentParam(this);
		}
	}
	
	public CommentParam(Builder builder) {
		super(builder);
		this.articleId = builder.articleId;
	}

	public int getArticleId() {
		return articleId;
	}
}
