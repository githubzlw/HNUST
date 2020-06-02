package com.cn.hnust.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


@ApiModel(value="采购行踪对象模型")
public class TrackQuery extends Track{
     
	
	/**
	 * @fieldName serialVersionUID
	 * @fieldType long
	 * @Description 
	 */
	private static final long serialVersionUID = 1L;
	private Integer roleNo;       //用户权限
	@ApiModelProperty(value="用户名" ,required=true)
    private String userName;      //当前用户名
	@ApiModelProperty(value="开始时间" ,required=true)
    private String startTime;     //开始时间
	@ApiModelProperty(value="结束时间" ,required=true)
    private String endTime;         //结束时间
    private String trackPlaces;      //地址
    private Integer pageNumber;       //页数
	private Integer pageSize;         //每页显示个数
    
    
	public TrackQuery(){
		
	}
	
    
	public TrackQuery(Integer roleNo, String userName, String startTime,
			String endTime, String trackPlaces, Integer pageNumber,
			Integer pageSize) {
		super();
		this.roleNo = roleNo;
		this.userName = userName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.trackPlaces = trackPlaces;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getTrackPlaces() {
		return trackPlaces;
	}
	public void setList(String trackPlaces) {
		this.trackPlaces = trackPlaces;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	


	public void setTrackPlaces(String trackPlaces) {
		this.trackPlaces = trackPlaces;
	}


	@Override
	public String toString() {
		return "TrackQuery [roleNo=" + roleNo + ", userName=" + userName
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", trackPlaces=" + trackPlaces + ", pageNumber=" + pageNumber
				+ ", pageSize=" + pageSize + "]";
	}
    
    
    
    
}