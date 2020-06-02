package com.cn.hnust.pojo;

import java.io.Serializable;


public class QualityReportQuery implements Serializable {
	/**
	 * @fieldName serialVersionUID
	 * @fieldType long
	 * @Description TODO
	 */
	private static final long serialVersionUID = 1L;
	private int pageNumber;       //页数
	private int pageSize;         //每页显示个数
	private String inputKey;      //搜索内容
	private Integer roleNo;       //用户权限
    private String userName;      //当前用户名
    private Integer isComment;    //是否评论
    
    
    
	public Integer getIsComment() {
		return isComment;
	}
	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getInputKey() {
		return inputKey;
	}
	public void setInputKey(String inputKey) {
		this.inputKey = inputKey;
	}
	public Integer getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(Integer roleNo) {
		this.roleNo = roleNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "QualityReportQuery [pageNumber=" + pageNumber + ", pageSize="
				+ pageSize + ", inputKey=" + inputKey + ", roleNo=" + roleNo
				+ ", userName=" + userName + ", isComment=" + isComment + "]";
	}


    
    
    
    
}