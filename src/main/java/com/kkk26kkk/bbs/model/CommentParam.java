package com.kkk26kkk.bbs.model;

import com.kkk26kkk.common.model.PageListParam;

public class ArticleParam extends PageListParam {
	private int articleId;
	
	public static class Builder extends PageListParam.Builder {
		private final int articleId;
		
		public Builder(int page, int pageSize, int articleId) {
			super(page, pageSize);
			this.articleId = articleId;
		}
		
		@Override
		public ArticleParam build() {
			super.build();
			return new ArticleParam(this);
		}
	}
	
	public ArticleParam(Builder builder) {
		super(builder);
		this.articleId = builder.articleId;
	}

	public int getArticleId() {
		return articleId;
	}
}
