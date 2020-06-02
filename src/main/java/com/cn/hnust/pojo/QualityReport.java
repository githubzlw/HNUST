package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class QualityReport implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
    
	private String eid;
	
    private Integer status;        //0:没问题；1：有问题 2：有问题需要采购解决

    private Integer type;         //0:样品；1:大货 ;2:中期 3：终期

    private String picUrl;        

    private Date createtime;

    private String user;

    private String projectNo;
    
    private String typeView;
    
    private String statusView;
    
    private String explainCause;
    
    private String detailView;
    
    private String[] picUrls;
    
    private String erpType;   //erp表类型
    
    private String projectNoId; //检验表项目号id
    
    //add by polo 2018.7.27
    private String spendTime;   //花费时间
    private String place;       //检测地点
    private Date checkDate;    //检验日期
    private String orders;       //订单量
    private String boxNumber;   //箱数
    private String perQty;      //每箱数量
    private String inventoryQty;  //清点数量
    private String openQty;       //开箱数量
    private String inspectionForm; //检验表格
    private String inspectionPath; //检验表格地址
    private String picNum;         //图号
    private String conclusion;     //结论
    
    private String companyName;    //公司名
    private int noCheck;           //因为没检测工具无法测量数量
    private int keySizeExceed;     //关键尺寸超差的数量
    
    private String factoryInspection;  //工厂做过的检验    
    private String meetingNo;          //会议id
    private Integer isAllRight;        //是否所有都完成（0：否 1：是）
    private String operateExplain;     //整改结论
    private String productFileName;    //产品图片
    private String openRate;           //开箱比例
    private String inspectionCreateDate; //检验计划创建时间
    
    private List<ProjectTask> taskList; //有问题发给采购的任务
    
    private List<Comment> comment; //评论
    private Date startDate;    //检验日期
    private Date endDate;    //检验日期
    private Integer testReport;          //质检报告
    private String concreteContent;          //质检报告
    private String dingTalkId;          //钉钉
    private String processInstanceId;     //钉钉审批id
    private String dingdingStatus;     //钉钉审批状态
    private String dingdingResult;     //钉钉审批结果
    private int materialSelection; 
    private String specificLicensePlate; 
    private int materialJudgement;
    private int surfaceTreatment ;
    private String surfaceResultEntry; 
    private String filmThickness;
    
    
    public String getFilmThickness() {
		return filmThickness;
	}

	public void setFilmThickness(String filmThickness) {
		this.filmThickness = filmThickness;
	}

	public int getMaterialSelection() {
		return materialSelection;
	}

	public void setMaterialSelection(int materialSelection) {
		this.materialSelection = materialSelection;
	}

	public String getSpecificLicensePlate() {
		return specificLicensePlate;
	}

	public void setSpecificLicensePlate(String specificLicensePlate) {
		this.specificLicensePlate = specificLicensePlate;
	}

	public int getMaterialJudgement() {
		return materialJudgement;
	}

	public void setMaterialJudgement(int materialJudgement) {
		this.materialJudgement = materialJudgement;
	}

	public int getSurfaceTreatment() {
		return surfaceTreatment;
	}

	public void setSurfaceTreatment(int surfaceTreatment) {
		this.surfaceTreatment = surfaceTreatment;
	}

	public String getSurfaceResultEntry() {
		return surfaceResultEntry;
	}

	public void setSurfaceResultEntry(String surfaceResultEntry) {
		this.surfaceResultEntry = surfaceResultEntry;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getDingdingStatus() {
		return dingdingStatus;
	}

	public void setDingdingStatus(String dingdingStatus) {
		this.dingdingStatus = dingdingStatus;
	}

	public String getDingdingResult() {
		return dingdingResult;
	}

	public void setDingdingResult(String dingdingResult) {
		this.dingdingResult = dingdingResult;
	}

	public String getDingTalkId() {
		return dingTalkId;
	}

	public void setDingTalkId(String dingTalkId) {
		this.dingTalkId = dingTalkId;
	}

	public String getConcreteContent() {
		return concreteContent;
	}

	public void setConcreteContent(String concreteContent) {
		this.concreteContent = concreteContent;
	}

	public Integer getTestReport() {
		return testReport;
	}

	public void setTestReport(Integer testReport) {
		this.testReport = testReport;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public String getInspectionCreateDate() {
		return inspectionCreateDate;
	}

	public void setInspectionCreateDate(String inspectionCreateDate) {
		this.inspectionCreateDate = inspectionCreateDate;
	}

	public String getProjectNoId() {
		return projectNoId;
	}

	public void setProjectNoId(String projectNoId) {
		this.projectNoId = projectNoId;
	}

	public String getOpenRate() {
		return openRate;
	}

	public void setOpenRate(String openRate) {
		this.openRate = openRate;
	}

	public String getOperateExplain() {
		return operateExplain;
	}

	public void setOperateExplain(String operateExplain) {
		this.operateExplain = operateExplain;
	}

	public String getProductFileName() {
		return productFileName;
	}

	public void setProductFileName(String productFileName) {
		this.productFileName = productFileName;
	}

	public Integer getIsAllRight() {
		return isAllRight;
	}

	public void setIsAllRight(Integer isAllRight) {
		this.isAllRight = isAllRight;
	}

	public List<ProjectTask> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<ProjectTask> taskList) {
		this.taskList = taskList;
	}

	public String getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}

	public String getFactoryInspection() {
		return factoryInspection;
	}

	public void setFactoryInspection(String factoryInspection) {
		this.factoryInspection = factoryInspection;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getNoCheck() {
		return noCheck;
	}

	public void setNoCheck(int noCheck) {
		this.noCheck = noCheck;
	}

	public int getKeySizeExceed() {
		return keySizeExceed;
	}

	public void setKeySizeExceed(int keySizeExceed) {
		this.keySizeExceed = keySizeExceed;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getPicNum() {
		return picNum;
	}

	public void setPicNum(String picNum) {
		this.picNum = picNum;
	}

	public String getErpType() {
		return erpType;
	}

	public void setErpType(String erpType) {
		this.erpType = erpType;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	private List<ProjectTask> projectTaskList;
    
    private String createtimeView;
    
    private String qualityReportUrl;
    
    private String projectName;
    
    private String grade;
    
    private List<QualityPicExplain> QualityPicExplainList;
    
    private String checkExplain;
    
    private String packageExplain;
    
    //虚拟字段
    private String purchaseName;         //采购名
    private Boolean purchaseReply;       //采购是否回复
    private Boolean yangReply;           //阳工是否回复
    private Comment purchaseReplyComment; //采购回复内容
    private String yangReplyContent;     //阳工回复内容
    private String sellName;             //销售名
    private String sellReplyContent;     //销售回复内容
   

    
    
	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public String getSellReplyContent() {
		return sellReplyContent;
	}

	public void setSellReplyContent(String sellReplyContent) {
		this.sellReplyContent = sellReplyContent;
	}

	public Comment getPurchaseReplyComment() {
		return purchaseReplyComment;
	}

	public void setPurchaseReplyComment(Comment purchaseReplyComment) {
		this.purchaseReplyComment = purchaseReplyComment;
	}

	public String getYangReplyContent() {
		return yangReplyContent;
	}

	public void setYangReplyContent(String yangReplyContent) {
		this.yangReplyContent = yangReplyContent;
	}

	public Boolean getPurchaseReply() {
		return purchaseReply;
	}

	public void setPurchaseReply(Boolean purchaseReply) {
		this.purchaseReply = purchaseReply;
	}

	public Boolean getYangReply() {
		return yangReply;
	}

	public void setYangReply(Boolean yangReply) {
		this.yangReply = yangReply;
	}

	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public String getBoxNumber() {
		return boxNumber;
	}

	public void setBoxNumber(String boxNumber) {
		this.boxNumber = boxNumber;
	}

	public String getPerQty() {
		return perQty;
	}

	public void setPerQty(String perQty) {
		this.perQty = perQty;
	}

	public String getInventoryQty() {
		return inventoryQty;
	}

	public void setInventoryQty(String inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	public String getOpenQty() {
		return openQty;
	}

	public void setOpenQty(String openQty) {
		this.openQty = openQty;
	}

	public String getInspectionForm() {
		return inspectionForm;
	}

	public void setInspectionForm(String inspectionForm) {
		this.inspectionForm = inspectionForm;
	}

	public String getInspectionPath() {
		return inspectionPath;
	}

	public void setInspectionPath(String inspectionPath) {
		this.inspectionPath = inspectionPath;
	}

	@Override
	public String toString() {
		return "QualityReport [id=" + id + ", eid=" + eid + ", status="
				+ status + ", type=" + type + ", picUrl=" + picUrl
				+ ", createtime=" + createtime + ", user=" + user
				+ ", projectNo=" + projectNo + ", typeView=" + typeView
				+ ", statusView=" + statusView + ", explainCause="
				+ explainCause + ", detailView=" + detailView + ", picUrls="
				+ Arrays.toString(picUrls) + ", erpType=" + erpType
				+ ", projectNoId=" + projectNoId + ", spendTime=" + spendTime
				+ ", place=" + place + ", checkDate=" + checkDate + ", orders="
				+ orders + ", boxNumber=" + boxNumber + ", perQty=" + perQty
				+ ", inventoryQty=" + inventoryQty + ", openQty=" + openQty
				+ ", inspectionForm=" + inspectionForm + ", inspectionPath="
				+ inspectionPath + ", picNum=" + picNum + ", conclusion="
				+ conclusion + ", companyName=" + companyName + ", noCheck="
				+ noCheck + ", keySizeExceed=" + keySizeExceed
				+ ", factoryInspection=" + factoryInspection + ", meetingNo="
				+ meetingNo + ", isAllRight=" + isAllRight
				+ ", operateExplain=" + operateExplain + ", productFileName="
				+ productFileName + ", openRate=" + openRate
				+ ", inspectionCreateDate=" + inspectionCreateDate
				+ ", taskList=" + taskList + ", comment=" + comment
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", testReport=" + testReport + ", concreteContent="
				+ concreteContent + ", dingTalkId=" + dingTalkId
				+ ", processInstanceId=" + processInstanceId
				+ ", dingdingStatus=" + dingdingStatus + ", dingdingResult="
				+ dingdingResult + ", materialSelection=" + materialSelection
				+ ", specificLicensePlate=" + specificLicensePlate
				+ ", materialJudgement=" + materialJudgement
				+ ", surfaceTreatment=" + surfaceTreatment
				+ ", surfaceResultEntry=" + surfaceResultEntry
				+ ", filmThickness=" + filmThickness + ", projectTaskList="
				+ projectTaskList + ", createtimeView=" + createtimeView
				+ ", qualityReportUrl=" + qualityReportUrl + ", projectName="
				+ projectName + ", grade=" + grade + ", QualityPicExplainList="
				+ QualityPicExplainList + ", checkExplain=" + checkExplain
				+ ", packageExplain=" + packageExplain + ", purchaseName="
				+ purchaseName + ", purchaseReply=" + purchaseReply
				+ ", yangReply=" + yangReply + ", purchaseReplyComment="
				+ purchaseReplyComment + ", yangReplyContent="
				+ yangReplyContent + ", sellName=" + sellName
				+ ", sellReplyContent=" + sellReplyContent + "]";
	}

	public String getSpendTime() {
		return spendTime;
	}

	public void setSpendTime(String spendTime) {
		this.spendTime = spendTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheck_date(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

	public String getTypeView() {
		return typeView;
	}

	public void setTypeView(String typeView) {
		this.typeView = typeView;
	}

	public String getDetailView() {
		return detailView;
	}

	public void setDetailView(String detailView) {
		this.detailView = detailView;
	}

	public List<ProjectTask> getProjectTaskList() {
		return projectTaskList;
	}

	public void setProjectTaskList(List<ProjectTask> projectTaskList) {
		this.projectTaskList = projectTaskList;
	}

	public String getStatusView() {
		return statusView;
	}

	public void setStatusView(String statusView) {
		this.statusView = statusView;
	}

	public String[] getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String[] picUrls) {
		this.picUrls = picUrls;
	}

	public String getCreatetimeView() {
		return createtimeView;
	}

	public void setCreatetimeView(String createtimeView) {
		this.createtimeView = createtimeView;
	}

	public String getExplainCause() {
		return explainCause;
	}

	public void setExplainCause(String explainCause) {
		this.explainCause = explainCause;
	}

	public String getQualityReportUrl() {
		return qualityReportUrl;
	}

	public void setQualityReportUrl(String qualityReportUrl) {
		this.qualityReportUrl = qualityReportUrl;
	}

	public List<QualityPicExplain> getQualityPicExplainList() {
		return QualityPicExplainList;
	}

	public void setQualityPicExplainList(List<QualityPicExplain> qualityPicExplainList) {
		QualityPicExplainList = qualityPicExplainList;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCheckExplain() {
		return checkExplain;
	}

	public void setCheckExplain(String checkExplain) {
		this.checkExplain = checkExplain;
	}

	public String getPackageExplain() {
		return packageExplain;
	}

	public void setPackageExplain(String packageExplain) {
		this.packageExplain = packageExplain;
	}
	
	
}