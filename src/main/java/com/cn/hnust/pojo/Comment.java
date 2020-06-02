package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private String id;

    private String projectNo;

    private String reviewer;

    private String comment;

    private Date createDate;
    
    private Integer qualityReportId;  //质检报告id
    
    private Integer shippingId;    //电子出货单id
    
    private Integer complaintId;   //投诉id（用于技术回复）
    
    private String fileName;       //原上传文件名  路径为 /project_img/SHS18438-2/comment/
    
    private String newFileName;    //新文件名
    
    
   
    
    
    

    private static final long serialVersionUID = 1L;

    

    
    
	public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}

	public Integer getShippingId() {
		return shippingId;
	}

	public void setShippingId(Integer shippingId) {
		this.shippingId = shippingId;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getQualityReportId() {
		return qualityReportId;
	}

	public void setQualityReportId(Integer qualityReportId) {
		this.qualityReportId = qualityReportId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	@Override
	public String toString() {
		return "Comment [id=" + id + ", projectNo=" + projectNo + ", reviewer="
				+ reviewer + ", comment=" + comment + ", createDate="
				+ createDate + ", qualityReportId=" + qualityReportId
				+ ", shippingId=" + shippingId + ", complaintId=" + complaintId
				+ ", fileName=" + fileName + ", newFileName=" + newFileName
				+ "]";
	}
    
    
}