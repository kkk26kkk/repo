package com.kkk26kkk.bbs.model;

import com.kkk26kkk.common.model.BaseParam;

public class ArticleParam extends BaseParam {
	private final String sort;
	
	public static class Builder extends BaseParam.Builder<Builder> {
		private String sort;
		
		public Builder(int pageSize) {
			super(pageSize);
		}
		
		public Builder sort(String sort) {
			this.sort = sort;
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
	}
	
	public String getSort() {
		return sort;
	}
}
