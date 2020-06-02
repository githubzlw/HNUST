package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProjectSchedule implements Serializable {
    private Integer id;

    private String projectNo;  //项目号

    private Date predictDate;  //预计完成日期

    private Integer num;       //出货批次

    private Date actualDate;   //实际完成日期

    private Date createTime;   //创建时间
    
    private Date updateTime;   //更新时间
    
    private Integer isFinish;   //大货是否完成（0未完成1已完成）
    
    private Date originalDate;   //原始交期

    private static final long serialVersionUID = 1L;

    
    
    
    public Date getOriginalDate() {
		return originalDate;
	}

	public void setOriginalDate(Date originalDate) {
		this.originalDate = originalDate;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

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

    public Date getPredictDate() {
        return predictDate;
    }

    public void setPredictDate(Date predictDate) {
        this.predictDate = predictDate;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "ProjectSchedule [id=" + id + ", projectNo=" + projectNo
				+ ", predictDate=" + predictDate + ", num=" + num
				+ ", actualDate=" + actualDate + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", isFinish=" + isFinish + "]";
	}
}