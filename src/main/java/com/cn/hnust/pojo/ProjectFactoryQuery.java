package com.cn.hnust.pojo;

import java.io.Serializable;

public class ProjectFactoryQuery extends ProjectFactory implements Serializable{
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
    private String purchaseName;      //采购名
    private String qualityName;       //质检名
    private String userId;            //用户id
    private String userName;          //登录用户
    private Integer queryDate;        //1:半年 2:3个月  3:1个月
    private Integer isFactoryOnly;    //1：单一工厂项目
    private Integer orderStatus;      //项目性质（0:样品 1：大货 2：补货/返修）
    private Integer sortNum;          //排序规则（0：默认按照在做数量  1：交货率  2：质量投诉率  3：交货率（单工厂）  4：质量投诉率（单工厂 5：评分 6：质检次数 7：采购次数）
    private Integer upOrDown;         //向上还是向下排序（0向下 1：向上）
    private Integer otherSelect;      //其他筛选（1：上传合同10天后，无开工视频 ）
    
    
    
    
	public Integer getOtherSelect() {
		return otherSelect;
	}
	public void setOtherSelect(Integer otherSelect) {
		this.otherSelect = otherSelect;
	}
	public Integer getUpOrDown() {
		return upOrDown;
	}
	public void setUpOrDown(Integer upOrDown) {
		this.upOrDown = upOrDown;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getIsFactoryOnly() {
		return isFactoryOnly;
	}
	public void setIsFactoryOnly(Integer isFactoryOnly) {
		this.isFactoryOnly = isFactoryOnly;
	}
	public Integer getQueryDate() {
		return queryDate;
	}
	public void setQueryDate(Integer queryDate) {
		this.queryDate = queryDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	@Override
	public String toString() {
		return "ProjectFactoryQuery [pageNumber=" + pageNumber + ", pageSize="
				+ pageSize + ", inputKey=" + inputKey + ", roleNo=" + roleNo
				+ ", purchaseName=" + purchaseName + ", qualityName="
				+ qualityName + ", userId=" + userId + ", userName=" + userName
				+ ", queryDate=" + queryDate + ", isFactoryOnly="
				+ isFactoryOnly + ", orderStatus=" + orderStatus + ", sortNum="
				+ sortNum + ", upOrDown=" + upOrDown + ", otherSelect="
				+ otherSelect + "]";
	}

    
    
    
}