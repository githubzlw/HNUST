package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class DingTalkMileStone implements Serializable {
    private Integer id;

    private String dingtalkid;   //钉钉id

    private String milestoneName;  //里程碑名

    private Date milestoneDate;   //里程碑日期

    private String projectNo;     //项目号
    
    private String uid;           //唯一id(用于判断同一批录入）
    
    private String processInstanceId;  //钉钉审批唯一id
    
    private Date createDate;      //创建时间

    private static final long serialVersionUID = 1L;

    
    
    
  

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDingtalkid() {
        return dingtalkid;
    }

    public void setDingtalkid(String dingtalkid) {
        this.dingtalkid = dingtalkid == null ? null : dingtalkid.trim();
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName == null ? null : milestoneName.trim();
    }

    public Date getMilestoneDate() {
        return milestoneDate;
    }

    public void setMilestoneDate(Date milestoneDate) {
        this.milestoneDate = milestoneDate;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

	@Override
	public String toString() {
		return "DingTalkMileStone [id=" + id + ", dingtalkid=" + dingtalkid
				+ ", milestoneName=" + milestoneName + ", milestoneDate="
				+ milestoneDate + ", projectNo=" + projectNo + ", uid=" + uid
				+ ", processInstanceId=" + processInstanceId + ", createDate="
				+ createDate + "]";
	}
    
    
}