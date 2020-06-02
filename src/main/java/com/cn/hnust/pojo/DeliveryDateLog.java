package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class DeliveryDateLog implements Serializable {
    private Integer id;

    private String projectNo;             //项目号   

    private Date newDeliveryDate;         //新交期时间

    private Date createTime;              //创建时间

    private Integer sampleProduction;     //样品还是大货（0样品  1大货）

    private Date originalDate;            //原交期

    private String createPerson;          //录入人
    
    private Integer scheduledDays;        //合同天数

    private static final long serialVersionUID = 1L;

    
    
    
    public Integer getScheduledDays() {
		return scheduledDays;
	}

	public void setScheduledDays(Integer scheduledDays) {
		this.scheduledDays = scheduledDays;
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

    public Date getNewDeliveryDate() {
        return newDeliveryDate;
    }

    public void setNewDeliveryDate(Date newDeliveryDate) {
        this.newDeliveryDate = newDeliveryDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSampleProduction() {
        return sampleProduction;
    }

    public void setSampleProduction(Integer sampleProduction) {
        this.sampleProduction = sampleProduction;
    }

    public Date getOriginalDate() {
        return originalDate;
    }

    public void setOriginalDate(Date originalDate) {
        this.originalDate = originalDate;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

	@Override
	public String toString() {
		return "DeliveryDateLog [id=" + id + ", projectNo=" + projectNo
				+ ", newDeliveryDate=" + newDeliveryDate + ", createTime="
				+ createTime + ", sampleProduction=" + sampleProduction
				+ ", originalDate=" + originalDate + ", createPerson="
				+ createPerson + ", scheduledDays=" + scheduledDays + "]";
	}
    
    
}