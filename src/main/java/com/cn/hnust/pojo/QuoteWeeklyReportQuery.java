package com.cn.hnust.pojo;

import java.io.Serializable;

public class QuoteWeeklyReportQuery extends QuoteWeeklyReport implements Serializable {
	
	/**
	 * @fieldName serialVersionUID
	 * @fieldType long
	 * @Description
	 */
	private static final long serialVersionUID = 1L;
	private int pageNumber;       //页数
	private int pageSize;         //每页显示个数
	private String inputKey;      //搜索内容
	private Integer roleNo;       //用户权限
    private String purchaseName;     //采购名
    private String qualityName;      //质检名
    private String userId;        //用户id
    private String userName;      //登录用户
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
	public String getPurchaseName() {
		return purchaseName;
	}
	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}
	public String getQualityName() {
		return qualityName;
	}
	public void setQualityName(String qualityName) {
		this.qualityName = qualityName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "QuoteWeeklyReportQuery [pageNumber=" + pageNumber
				+ ", pageSize=" + pageSize + ", inputKey=" + inputKey
				+ ", roleNo=" + roleNo + ", purchaseName=" + purchaseName
				+ ", qualityName=" + qualityName + ", userId=" + userId
				+ ", userName=" + userName + "]";
	}
    
    
    
    
}