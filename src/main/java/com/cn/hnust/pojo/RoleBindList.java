package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleBindList implements Serializable {
    private Integer id;

    private String projectNo;

    private Integer createId;

    private String createName;

    private Date createTime;

    private Date updateTime;

    private List<RoleBindDetail>  roleBindDetails =  new ArrayList<RoleBindDetail>();
    
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public List<RoleBindDetail> getRoleBindDetails() {
		return roleBindDetails;
	}

	public void setRoleBindDetails(List<RoleBindDetail> roleBindDetails) {
		this.roleBindDetails = roleBindDetails;
	}
    
}