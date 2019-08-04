package com.kkk26kkk.common.model;

import java.util.List;

public class PageList<E> {
	private List<E> list;
	private int page;
	private int pageSize;
	private int totalCount;
	private boolean hasNext;
	
	public PageList() {}
	
	public PageList(List<E> list, int page, int pageSize, int totalCount, boolean hasNext) {
		this.list = list;
		this.page = page;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.hasNext = hasNext;
	}
	
	public List<E> getList() {
		return list;
	}
	public int getPage() {
		return page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getTotalCount() { // 리스트 수
		return totalCount;
	}
	public int getTotalPage() { // 페이지 수
		return (this.totalCount + this.pageSize - 1) / this.pageSize;
	}
	public boolean hasNext() {
		return hasNext;
	}
}
