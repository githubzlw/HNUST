package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Track implements Serializable {
    private Integer id;

    private String userName;   //用户

    private Date outDate;      //外出时间

    private String place;      //地点

    private String factoryList; //工厂列表

    private Date createTime;    //创建时间

    private Integer reportId;   //质检报告id
    
    private String remark;      //备注信息
    
	private List<TrackPlace> placeList; //地点列表
    
    private static final long serialVersionUID = 1L;

    
    
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<TrackPlace> getPlaceList() {
		return placeList;
	}

	public void setPlaceList(List<TrackPlace> placeList) {
		this.placeList = placeList;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(String factoryList) {
        this.factoryList = factoryList == null ? null : factoryList.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "Track [id=" + id + ", userName=" + userName + ", outDate="
				+ outDate + ", place=" + place + ", factoryList=" + factoryList
				+ ", createTime=" + createTime + ", reportId=" + reportId
				+ ", remark=" + remark + ", placeList=" + placeList + "]";
	}
    
    
}