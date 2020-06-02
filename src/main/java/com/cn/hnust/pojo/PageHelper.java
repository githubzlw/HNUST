package com.cn.hnust.pojo;

import java.io.Serializable;

public class PageHelper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageSize;// 当前页
	private int PageNumber;// 每页显示记录数
	private int totalCount;//总行数

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return PageNumber;
	}
	public void setPageNumber(int pageNumber) {
		PageNumber = pageNumber;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
