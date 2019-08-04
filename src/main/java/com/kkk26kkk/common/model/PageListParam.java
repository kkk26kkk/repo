package com.kkk26kkk.common.model;

public class PageListParam {
	private final int page;
	private final int pageSize;
	private final int startNum;
	private final int endNum;
	private final boolean useTotal;
	private final boolean useMore;
	
	public static class Builder {
		private final int page;
		private final int pageSize;
		private int startNum;
		private int endNum;
		private boolean useTotal;
		private boolean useMore;
		
		public Builder(int page, int pageSize) {
			this.page = page;
			this.pageSize = pageSize;
		}
		
		public Builder startNumAndEndNum(int startNum, int endNum) {
			this.startNum = startNum;
			this.endNum = endNum;
			return this;
		}
		
		public Builder useTotal(boolean useTotal) {
			this.useTotal = useTotal;
			return this;
		}
		
		public Builder useMore(boolean useMore) {
			this.useMore = useMore;
			return this;
		}
		
		public PageListParam build() {
			if(0 != this.startNum && 0 != this.endNum) {
				this.startNum = (page - 1) * pageSize + 1;
				this.endNum = page * pageSize;
			}
			
			if(this.useMore) {
				this.endNum = this.endNum + 1;
			}
			
			return new PageListParam(this);
		}
	}
	
	private PageListParam(Builder builder) {
		this.page = builder.page;
		this.pageSize = builder.pageSize;
		this.startNum = builder.startNum;
		this.endNum = builder.endNum;
		this.useTotal = builder.useTotal;
		this.useMore = builder.useMore;
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

	public boolean useTotal() {
		return useTotal;
	}

	public boolean useMore() {
		return useMore;
	}
	
}
