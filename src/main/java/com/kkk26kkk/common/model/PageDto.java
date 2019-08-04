package com.kkk26kkk.common.model;

public class PageDto {
	private final int page;
	private final int pageSize;
	private final int startNum;
	private final int endNum;
	
	public static class Builder {
		private final int page;
		private final int pageSize;
		private int startNum;
		private int endNum;
		
		public Builder(int page, int pageSize) {
			this.page = page;
			this.pageSize = pageSize;
		}
		
		public Builder startNum(int page, int pageSize) {
			int startNum = (page - 1) * pageSize + 1;
			
			this.startNum = startNum;
			return this;
		}
		
		public Builder endNum(int page, int pageSize) {
			int endNum = page * pageSize;
			
			this.endNum = endNum;
			return this;
		}
		
		public PageDto build() {
			return new PageDto(this);
		}
	}
	
	private PageDto(Builder builder) {
		this.page = builder.page;
		this.pageSize = builder.pageSize;
		this.startNum = builder.startNum;
		this.endNum = builder.endNum;
	}

	public int getPage() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getStartNum() {
		return startNum;
	}

	public int getEndNum() {
		return endNum;
	}
	
}
