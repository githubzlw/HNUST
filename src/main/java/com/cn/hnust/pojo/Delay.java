package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 项目延期信息 对象
 * @author Administrator
 *
 */
public class Delay implements Serializable {
    private String id;

    private String projectNo;  //项目号

    private String internalCause;   //采购原因

    private String externalCause;   //客户原因

    private Date delayDate;         //延期时间

    private Integer type;           //类型 0：采购1：客户

    private Date createDate;        //创建日期
    
    private Integer isAgree;        //是否同意  0默认 1:同意 2：不同意
    
    private Integer num;            //大货批次  1、2、3 目前3个
    
    private Integer delayCount;     //延期次数
    
    private Date originalDate;      //原始交期
    
    private String agreePerson;     //同意人
    
    private Date agreeTime;         //同意时间
    
    private String applyPerson;     //申请人
    
    private String reson;           //操作理由

    private static final long serialVersionUID = 1L;


    
    
	public String getReson() {
		return reson;
	}

	public void setReson(String reson) {
		this.reson = reson;
	}

	public String getApplyPerson() {
		return applyPerson;
	}

	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}

	public String getAgreePerson() {
		return agreePerson;
	}

	public void setAgreePerson(String agreePerson) {
		this.agreePerson = agreePerson;
	}

	public Date getAgreeTime() {
		return agreeTime;
	}

	public void setAgreeTime(Date agreeTime) {
		this.agreeTime = agreeTime;
	}

	public Date getOriginalDate() {
		return originalDate;
	}

	public void setOriginalDate(Date originalDate) {
		this.originalDate = originalDate;
	}

	public Integer getDelayCount() {
		return delayCount;
	}

	public void setDelayCount(Integer delayCount) {
		this.delayCount = delayCount;
	}

	@Override
	public String toString() {
		return "Delay [id=" + id + ", projectNo=" + projectNo
				+ ", internalCause=" + internalCause + ", externalCause="
				+ externalCause + ", delayDate=" + delayDate + ", type=" + type
				+ ", createDate=" + createDate + ", isAgree=" + isAgree
				+ ", num=" + num + ", delayCount=" + delayCount
				+ ", originalDate=" + originalDate + ", agreePerson="
				+ agreePerson + ", agreeTime=" + agreeTime + ", applyPerson="
				+ applyPerson + ", reson=" + reson + "]";
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getInternalCause() {
        return internalCause;
    }

    public void setInternalCause(String internalCause) {
        this.internalCause = internalCause == null ? null : internalCause.trim();
    }

    public String getExternalCause() {
        return externalCause;
    }

    public void setExternalCause(String externalCause) {
        this.externalCause = externalCause == null ? null : externalCause.trim();
    }

    public Date getDelayDate() {
        return delayDate;
    }

    public void setDelayDate(Date delayDate) {
        this.delayDate = delayDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public Integer getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(Integer isAgree) {
		this.isAgree = isAgree;
	}
    
}