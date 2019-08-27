package com.kkk26kkk.bbs.model;

import com.kkk26kkk.common.model.BaseParam;

public class ArticleParam extends BaseParam {
	private final String sort;
	private final String articleIdList;
	
	public static class Builder extends BaseParam.Builder<Builder> {
		private String sort;
		private String articleIdList;
		
		public Builder(int pageSize) {
			super(pageSize);
		}
		
		public Builder sort(String sort) {
			this.sort = sort;
			return this;
		}
		
		public Builder articleIdList(String articleIdList) {
			this.articleIdList = articleIdList;
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
		this.articleIdList = builder.articleIdList;
	}
	
	public String getSort() {
		return sort;
	}

	public String getArticleIdList() {
		return articleIdList;
	}
	
}
