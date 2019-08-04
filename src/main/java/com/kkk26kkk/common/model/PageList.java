package com.kkk26kkk.common.model;

import java.util.List;

public class PageList<E> {
	private List<E> list;
	private int page;
	private int pageSize;
	private int totalCount;
	private boolean hasNext;
	
	public PageList() {}
	
	public PageList(List<E> list, int page, int pageSize) {
		this.list = list;
		this.page = page;
		this.pageSize = pageSize;
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
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() { // 페이지 수
//		return totalPage;
		return (this.totalCount + this.pageSize - 1) / this.pageSize;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
}
