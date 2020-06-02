package com.cn.hnust.pojo;

public class ProjectComplaintQuery extends ProjectComplaint {

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
    private String userId;        //当前用户id
	private Integer isSolve;         //是否解决 
    
	
	public Integer getIsSolve() {
		return isSolve;
	}
	public void setIsSolve(Integer isSolve) {
		this.isSolve = isSolve;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(Integer roleNo) {
		this.roleNo = roleNo;
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
	@Override
	public String toString() {
		return "ProjectComplaintQuery [pageNumber=" + pageNumber
				+ ", pageSize=" + pageSize + ", inputKey=" + inputKey
				+ ", roleNo=" + roleNo + ", userId=" + userId + ", isSolve="
				+ isSolve + "]";
	}
	
	
}
