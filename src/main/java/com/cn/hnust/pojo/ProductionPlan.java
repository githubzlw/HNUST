package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
/***
 * 生产计划对象
 * @author Administrator
 *
 */
public class ProductionPlan implements Serializable {
	private String id;

    private String projectNo;   //项目号

    private String planNode;    //图纸名

    private Date planDate;     //上传时间
    
    private String planDate2String;

    private Date createDate;
    
    private String uploader;  //上传者

    private static final long serialVersionUID = 1L;
    
    
    

    public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
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

    public String getPlanNode() {
        return planNode;
    }

    public void setPlanNode(String planNode) {
        this.planNode = planNode == null ? null : planNode.trim();
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public String getPlanDate2String() {
		return planDate2String;
	}

	public void setPlanDate2String(String planDate2String) {
		this.planDate2String = planDate2String;
	}

	@Override
	public String toString() {
		return "ProductionPlan [id=" + id + ", projectNo=" + projectNo
				+ ", planNode=" + planNode + ", planDate=" + planDate
				+ ", planDate2String=" + planDate2String + ", createDate="
				+ createDate + ", uploader=" + uploader + "]";
	}
    
    
}