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
	
	public void setList(List<E> list) {
		this.list = list;
		this.totalCount = list.size();
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
	public int getTotalCount() {
		return totalCount;
	}
	public int getTotalPage() {
		if(0 == pageSize) {
			return 0;
		}
		return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
	}
	public boolean hasNext() {
		return hasNext;
	}
}
