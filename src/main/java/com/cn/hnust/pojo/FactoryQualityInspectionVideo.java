package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class FactoryQualityInspectionVideo implements Serializable {
    private Integer id;

    private String factoryName;  //工厂名

    private String factoryId;   //工厂id

    private String projectNo;    //项目号

    private String qualityVideoName;  //视频名

    private String videoFile;     //视频路径
    
    private String uploader;      //上传者
    
    private String remark;        //备注
    
    private Date uploadTime;      //上传日期
    
    private Integer taskId;       //任务号
    
    private Integer videoType;    //视频类型 1：验厂/拍工厂环境和展品的  2：对应质检报告的 3：任务视频
    

    private static final long serialVersionUID = 1L;

    
    
    public Integer getVideoType() {
		return videoType;
	}

	public void setVideoType(Integer videoType) {
		this.videoType = videoType;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getQualityVideoName() {
        return qualityVideoName;
    }

    public void setQualityVideoName(String qualityVideoName) {
        this.qualityVideoName = qualityVideoName == null ? null : qualityVideoName.trim();
    }

    public String getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile == null ? null : videoFile.trim();
    }

	@Override
	public String toString() {
		return "FactoryQualityInspectionVideo [id=" + id + ", factoryName="
				+ factoryName + ", factoryId=" + factoryId + ", projectNo="
				+ projectNo + ", qualityVideoName=" + qualityVideoName
				+ ", videoFile=" + videoFile + ", uploader=" + uploader
				+ ", remark=" + remark + ", uploadTime=" + uploadTime
				+ ", taskId=" + taskId + ", videoType=" + videoType + "]";
	}
    
    
    
}