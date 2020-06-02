package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProjectFactory implements Serializable {
    private Integer id;

    private String factoryId;   //下单工厂id

    private String factoryName;  //下单工厂名

    private String projectNo;    //项目号

    private Integer isUpload;    //是否上传
    
    private Date deliveryDate; //项目交期   
    
    private Date sampleDate;  //样品交期   
    
    private String purchaseName; //采购名
    
    private String inspectionName; //质检名
    
    private Date contractDate;  //合同日期
    
    private String city;   //城市
    
    private String contractNo;  //合同号
    
    private Integer orderNature; //性质 1:正常合同  2：返修补货合同
    
    private Integer orderState;  //合同状态 1在做模具  2在打样或小批量 3在大批量生产4因为工厂原因还未做5因为我司或者客户原因暂停6已经部分交货，正在继续生产7已经部分交货，目前暂停
    
    private String productFinishTime; //大货完成时间
    
    private String sampleFinishTime;  //样品完成时间
    
    private String repairReplenishmentFinishTime; //返修补货完成时间
    
    private Date updateTime;        //更新时间
    
    private Integer projectStage;   //合同阶段0:样品 1:大货 2：返修补货
    
    
    private String projectName; //项目名
    //虚拟字段
    private Integer reportCount;      //质检报告数量
    private Integer complaintCount;       //投诉数量(全部项目)
    private Integer complaintCountOnly;   //投诉数量(单一工厂项目)
    private Integer projectCount;     //进行中项目数量
    private Double  delayRate;        //延期比例(全部项目)
    private Double complaintRate;     //投诉比例(全部项目)
    private String processUpdateDate; //生产进度更新
    private Boolean isUploadVideo;    //是否上传视频
    private Boolean isUploadMaterial; //是否上传材质报告
    private Double avgScore;          //工厂平均分
    private Integer trackPurchaseCount;   //采购去工厂次数
    private Integer trackQualityCount;   //质检去工厂次数
    private Integer scoreCount;          //工厂评分数量
    private Double delayRateOnly;   //延期比例(单一工厂项目)
    private Double complaintRateOnly;   //投诉比例(单一工厂项目)
    private String projectAmount;  //合同金额
    private Boolean isUploadPic;  //是否上传照片
    private Boolean isUploadQualityReport; //是否上传质检报告
    private Double delayCount;    //延期数量
    private Double delayCountOnly;    //延期数量(单一工厂项目)
    private Double onTimeRate;       //按时交货率(全部项目)
    private Double onTimeRateOnly;       //按时交货率(单一工厂项目)
    private int rework;       //返工
    private int finish;       //完工
    private String modificationRecord;       //修改记录
    /** 项目等级*/
    private Integer plantAnalysis;   //0：无等级  1：A 2：B 3：C
    /** 检验计划*/
    private List<ProjectInspectionReport> inspectionPlanList; 
    
    private List<ProjectComplaint> complaintList;      //客户投诉列表
    
    private List<QualityReport> qualityReportList;//项目质检报告
    
    
    
    
    public Integer getPlantAnalysis() {
		return plantAnalysis;
	}

	public void setPlantAnalysis(Integer plantAnalysis) {
		this.plantAnalysis = plantAnalysis;
	}

	

	public List<ProjectInspectionReport> getInspectionPlanList() {
		return inspectionPlanList;
	}

	public void setInspectionPlanList(
			List<ProjectInspectionReport> inspectionPlanList) {
		this.inspectionPlanList = inspectionPlanList;
	}

	public List<ProjectComplaint> getComplaintList() {
		return complaintList;
	}

	public void setComplaintList(List<ProjectComplaint> complaintList) {
		this.complaintList = complaintList;
	}

	public List<QualityReport> getQualityReportList() {
		return qualityReportList;
	}

	public void setQualityReportList(List<QualityReport> qualityReportList) {
		this.qualityReportList = qualityReportList;
	}

	public String getModificationRecord() {
		return modificationRecord;
	}

	public void setModificationRecord(String modificationRecord) {
		this.modificationRecord = modificationRecord;
	}

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}

	public int getRework() {
		return rework;
	}

	public void setRework(int rework) {
		this.rework = rework;
	}

	public Double getOnTimeRate() {
		return onTimeRate;
	}

	public void setOnTimeRate(Double onTimeRate) {
		this.onTimeRate = onTimeRate;
	}

	public Double getOnTimeRateOnly() {
		return onTimeRateOnly;
	}

	public void setOnTimeRateOnly(Double onTimeRateOnly) {
		this.onTimeRateOnly = onTimeRateOnly;
	}

	public Double getDelayCount() {
		return delayCount;
	}

	public void setDelayCount(Double delayCount) {
		this.delayCount = delayCount;
	}

	public Double getDelayCountOnly() {
		return delayCountOnly;
	}

	public void setDelayCountOnly(Double delayCountOnly) {
		this.delayCountOnly = delayCountOnly;
	}

	public Boolean getIsUploadPic() {
		return isUploadPic;
	}

	public void setIsUploadPic(Boolean isUploadPic) {
		this.isUploadPic = isUploadPic;
	}

	public Boolean getIsUploadQualityReport() {
		return isUploadQualityReport;
	}

	public void setIsUploadQualityReport(Boolean isUploadQualityReport) {
		this.isUploadQualityReport = isUploadQualityReport;
	}

	public String getProjectAmount() {
		return projectAmount;
	}

	public void setProjectAmount(String projectAmount) {
		this.projectAmount = projectAmount;
	}

	public Date getSampleDate() {
		return sampleDate;
	}

	public void setSampleDate(Date sampleDate) {
		this.sampleDate = sampleDate;
	}

	public Integer getProjectStage() {
		return projectStage;
	}

	public void setProjectStage(Integer projectStage) {
		this.projectStage = projectStage;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public String getProductFinishTime() {
		return productFinishTime;
	}

	public void setProductFinishTime(String productFinishTime) {
		this.productFinishTime = productFinishTime;
	}

	public String getSampleFinishTime() {
		return sampleFinishTime;
	}

	public void setSampleFinishTime(String sampleFinishTime) {
		this.sampleFinishTime = sampleFinishTime;
	}

	public String getRepairReplenishmentFinishTime() {
		return repairReplenishmentFinishTime;
	}

	public void setRepairReplenishmentFinishTime(String repairReplenishmentFinishTime) {
		this.repairReplenishmentFinishTime = repairReplenishmentFinishTime;
	}



	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Integer getOrderNature() {
		return orderNature;
	}

	public void setOrderNature(Integer orderNature) {
		this.orderNature = orderNature;
	}

	public Integer getComplaintCountOnly() {
		return complaintCountOnly;
	}

	public void setComplaintCountOnly(Integer complaintCountOnly) {
		this.complaintCountOnly = complaintCountOnly;
	}

	public Double getDelayRateOnly() {
		return delayRateOnly;
	}

	public void setDelayRateOnly(Double delayRateOnly) {
		this.delayRateOnly = delayRateOnly;
	}

	public Double getComplaintRateOnly() {
		return complaintRateOnly;
	}

	public void setComplaintRateOnly(Double complaintRateOnly) {
		this.complaintRateOnly = complaintRateOnly;
	}

	public Integer getScoreCount() {
		return scoreCount;
	}

	public void setScoreCount(Integer scoreCount) {
		this.scoreCount = scoreCount;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private static final long serialVersionUID = 1L;

    
    
    
    




	public Integer getTrackPurchaseCount() {
		return trackPurchaseCount;
	}

	public void setTrackPurchaseCount(Integer trackPurchaseCount) {
		this.trackPurchaseCount = trackPurchaseCount;
	}

	public Integer getTrackQualityCount() {
		return trackQualityCount;
	}

	public void setTrackQualityCount(Integer trackQualityCount) {
		this.trackQualityCount = trackQualityCount;
	}

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public Boolean getIsUploadVideo() {
		return isUploadVideo;
	}

	public void setIsUploadVideo(Boolean isUploadVideo) {
		this.isUploadVideo = isUploadVideo;
	}

	public Boolean getIsUploadMaterial() {
		return isUploadMaterial;
	}

	public void setIsUploadMaterial(Boolean isUploadMaterial) {
		this.isUploadMaterial = isUploadMaterial;
	}

	public String getProcessUpdateDate() {
		return processUpdateDate;
	}

	public void setProcessUpdateDate(String processUpdateDate) {
		this.processUpdateDate = processUpdateDate;
	}

	public Double getComplaintRate() {
		return complaintRate;
	}

	public void setComplaintRate(Double complaintRate) {
		this.complaintRate = complaintRate;
	}

	public Double getDelayRate() {
		return delayRate;
	}

	public void setDelayRate(Double delayRate) {
		this.delayRate = delayRate;
	}

	public Integer getProjectCount() {
		return projectCount;
	}

	public void setProjectCount(Integer projectCount) {
		this.projectCount = projectCount;
	}

	public Integer getComplaintCount() {
		return complaintCount;
	}

	public void setComplaintCount(Integer complaintCount) {
		this.complaintCount = complaintCount;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public String getInspectionName() {
		return inspectionName;
	}

	public void setInspectionName(String inspectionName) {
		this.inspectionName = inspectionName;
	}

	public Integer getReportCount() {
		return reportCount;
	}

	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public Integer getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(Integer isUpload) {
        this.isUpload = isUpload;
    }

	@Override
	public String toString() {
		return "ProjectFactory [id=" + id + ", factoryId=" + factoryId
				+ ", factoryName=" + factoryName + ", projectNo=" + projectNo
				+ ", isUpload=" + isUpload + ", deliveryDate=" + deliveryDate
				+ ", sampleDate=" + sampleDate + ", purchaseName="
				+ purchaseName + ", inspectionName=" + inspectionName
				+ ", contractDate=" + contractDate + ", city=" + city
				+ ", contractNo=" + contractNo + ", orderNature=" + orderNature
				+ ", orderState=" + orderState + ", productFinishTime="
				+ productFinishTime + ", sampleFinishTime=" + sampleFinishTime
				+ ", repairReplenishmentFinishTime="
				+ repairReplenishmentFinishTime + ", updateTime=" + updateTime
				+ ", projectStage=" + projectStage + ", projectName="
				+ projectName + ", reportCount=" + reportCount
				+ ", complaintCount=" + complaintCount
				+ ", complaintCountOnly=" + complaintCountOnly
				+ ", projectCount=" + projectCount + ", delayRate=" + delayRate
				+ ", complaintRate=" + complaintRate + ", processUpdateDate="
				+ processUpdateDate + ", isUploadVideo=" + isUploadVideo
				+ ", isUploadMaterial=" + isUploadMaterial + ", avgScore="
				+ avgScore + ", trackPurchaseCount=" + trackPurchaseCount
				+ ", trackQualityCount=" + trackQualityCount + ", scoreCount="
				+ scoreCount + ", delayRateOnly=" + delayRateOnly
				+ ", complaintRateOnly=" + complaintRateOnly
				+ ", projectAmount=" + projectAmount + ", isUploadPic="
				+ isUploadPic + ", isUploadQualityReport="
				+ isUploadQualityReport + ", delayCount=" + delayCount
				+ ", delayCountOnly=" + delayCountOnly + ", onTimeRate="
				+ onTimeRate + ", onTimeRateOnly=" + onTimeRateOnly
				+ ", rework=" + rework + ", finish=" + finish
				+ ", modificationRecord=" + modificationRecord
				+ ", plantAnalysis=" + plantAnalysis + ", inspectionPlanList="
				+ inspectionPlanList + ", complaintList=" + complaintList
				+ ", qualityReportList=" + qualityReportList + "]";
	}
    
    
    
}