package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class InspectionPlan implements Serializable {
    private Integer id;

    private String projectNo;  //项目号

    private String productComponent;  //产品图号

    private String productStandards;  //产品要求

    private Integer isWork;          //是否做完(0:未做 1：已做)
    
    private String type;           //类型 
    
    private Date createDate;      //创建时间
    
    private String content;       //说明文字
    
    private String inspectionPic;  //说明图片
    
    //虚拟字段
    private int completeCount;    //完成数量
    private int noCompleteCount;  //未完成数量
    private int totalCount;       //图号总数量

    private static final long serialVersionUID = 1L;

    
    
    
    @Override
	public String toString() {
		return "InspectionPlan [id=" + id + ", projectNo=" + projectNo
				+ ", productComponent=" + productComponent
				+ ", productStandards=" + productStandards + ", isWork="
				+ isWork + ", type=" + type + ", createDate=" + createDate
				+ ", content=" + content + ", inspectionPic=" + inspectionPic
				+ ", completeCount=" + completeCount + ", noCompleteCount="
				+ noCompleteCount + ", totalCount=" + totalCount + "]";
	}

    
    
	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getInspectionPic() {
		return inspectionPic;
	}



	public void setInspectionPic(String inspectionPic) {
		this.inspectionPic = inspectionPic;
	}



	public int getTotalCount() {
		return totalCount;
	}



	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}



	public int getCompleteCount() {
		return completeCount;
	}



	public void setCompleteCount(int completeCount) {
		this.completeCount = completeCount;
	}



	public int getNoCompleteCount() {
		return noCompleteCount;
	}



	public void setNoCompleteCount(int noCompleteCount) {
		this.noCompleteCount = noCompleteCount;
	}



	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

    public String getProductComponent() {
        return productComponent;
    }

    public void setProductComponent(String productComponent) {
        this.productComponent = productComponent == null ? null : productComponent.trim();
    }

    public String getProductStandards() {
        return productStandards;
    }

    public void setProductStandards(String productStandards) {
        this.productStandards = productStandards == null ? null : productStandards.trim();
    }

    public Integer getIsWork() {
        return isWork;
    }

    public void setIsWork(Integer isWork) {
        this.isWork = isWork;
    }
}