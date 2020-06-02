package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 客户投诉表
 * @ClassName ProjectComplaint 
 * @Description
 * @author zj
 * @date 2018年7月24日 下午7:52:09
 */
public class ProjectComplaint implements Serializable {
    private Integer id;

    private String projectNo;         //项目号

    private Date complaintDate;       //投诉日期

    private String costAnalysis;      //成本分析

    private Integer purchaseId;      //采购id

    private Integer sellId;          //跟单id

    private Integer isPurchase;       //采购是否确认(0否1是)默认0

    private Integer isSell;          //跟单是否确认(0否1是)默认0

    private Date createTime;          //创建日期

    private String createPerson;      //创建人

    private String complaintContent;   //投诉内容
    
    private String purchaseContent;    //采购原因分析
    
    private String sellContent;        //跟单原因分析
    
    private Date purchaseConfirmDate;   //采购确认时间
    
    private Date sellConfirmDate;      //跟单确认时间
    
    private String fileName;          //原文件名
    
    private String filePath;          //文件路径

    private String meetingNo;        //会议Id
    
    private Integer seriousLevel;    //严重等级
    
    private Date purchaseReplyTime; //采购回复日期
    
    private Date zhijianReplyTime;  //质检回复日期
    
    private Date technicianReplyTime; //技术回复日期
    
    private Date completeTime;   //整改完成日期
    
    private String inspectionContent; //质检解释和分析
    
    private Date predictCompleteTime;  //采购整改预计日期
    
    private Integer projectStage;     //项目阶段(0:样品2:大货)
    
    private Integer inspectionLeaderConfirm; //质检领导确认（0：未确认 1：已确认）
    
    private Integer purchaseLeaderConfirm; //技术领导确认（0：未确认 1：已确认）
    
    private Date inspectionLeaderConfirmDate; //质检领导确认时间
    
    private Date purchaseLeaderConfirmDate; //技术领导确认时间
    
    private String rectificationZhijianReply;  //整改质检回复
    
    private String rectificationSellPurchaseReply; //整改采购销售回复人
    
    private Date rectificationZhijianTime;        //整改质检回复时间
    
    private Date rectificationSellPurchaseTime;        //整改质检回复时间
    
    private static final long serialVersionUID = 1L;
    
    //虚拟字段
	private String purchaseName;   //采购名
	private String sellName;       //跟单名
    private List<ProjectTask> tasks; //任务列表
    private String projectName;     //项目号
    private String customerName;    //客户名称
    private String zhijian1;         //质检1
    private String zhijian2;         //质检2
    private String zhijian3;         //质检3
    private String process;          //工艺
    private Date startingTime;     //起始时间
    private Date endTime;          //结束时间
    private Integer inspectionTimelinessNumber; //检验不及时次数
    private Integer complaintsNumber;          //投诉次数
    private Double projectAmount;          //投诉次数
    private String processInstanceId;     //钉钉审批id
    private String dingdingStatus;     //钉钉审批状态
    private String dingdingResult;     //钉钉审批结果
    
    private int picUp;     //图纸跟新
    private String taskBrief;     //任务简述
    private String taskAttachment;     //附件上传
    private int verification;     //是否验证
    private String masterQualityInspection;    //主质检
    private String qualityInspector1;    //待质检
    private String qualityInspector2;    //待质检
    private String qualityInspector3;    //待质检
    private String qualityInspector4;    //待质检
    private String qualityInspector5;    //待质检
    private String qualityInspector6;    //待质检
    private String qualityInspector7;    //待质检
    
	
	public String getMasterQualityInspection() {
		return masterQualityInspection;
	}

	public void setMasterQualityInspection(String masterQualityInspection) {
		this.masterQualityInspection = masterQualityInspection;
	}

	public String getQualityInspector1() {
		return qualityInspector1;
	}

	public void setQualityInspector1(String qualityInspector1) {
		this.qualityInspector1 = qualityInspector1;
	}

	public String getQualityInspector2() {
		return qualityInspector2;
	}

	public void setQualityInspector2(String qualityInspector2) {
		this.qualityInspector2 = qualityInspector2;
	}

	public String getQualityInspector3() {
		return qualityInspector3;
	}

	public void setQualityInspector3(String qualityInspector3) {
		this.qualityInspector3 = qualityInspector3;
	}

	public String getQualityInspector4() {
		return qualityInspector4;
	}

	public void setQualityInspector4(String qualityInspector4) {
		this.qualityInspector4 = qualityInspector4;
	}

	public String getQualityInspector5() {
		return qualityInspector5;
	}

	public void setQualityInspector5(String qualityInspector5) {
		this.qualityInspector5 = qualityInspector5;
	}

	public String getQualityInspector6() {
		return qualityInspector6;
	}

	public void setQualityInspector6(String qualityInspector6) {
		this.qualityInspector6 = qualityInspector6;
	}

	public String getQualityInspector7() {
		return qualityInspector7;
	}

	public void setQualityInspector7(String qualityInspector7) {
		this.qualityInspector7 = qualityInspector7;
	}

	public int getVerification() {
		return verification;
	}

	public void setVerification(int verification) {
		this.verification = verification;
	}

	public int getPicUp() {
		return picUp;
	}

	public void setPicUp(int picUp) {
		this.picUp = picUp;
	}

	public String getTaskBrief() {
		return taskBrief;
	}

	public void setTaskBrief(String taskBrief) {
		this.taskBrief = taskBrief;
	}

	public String getTaskAttachment() {
		return taskAttachment;
	}

	public void setTaskAttachment(String taskAttachment) {
		this.taskAttachment = taskAttachment;
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

	public Double getProjectAmount() {
		return projectAmount;
	}

	public void setProjectAmount(Double projectAmount) {
		this.projectAmount = projectAmount;
	}

	public Integer getInspectionTimelinessNumber() {
		return inspectionTimelinessNumber;
	}

	public void setInspectionTimelinessNumber(Integer inspectionTimelinessNumber) {
		this.inspectionTimelinessNumber = inspectionTimelinessNumber;
	}

	public Integer getComplaintsNumber() {
		return complaintsNumber;
	}

	public void setComplaintsNumber(Integer complaintsNumber) {
		this.complaintsNumber = complaintsNumber;
	}

	public Date getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getRectificationZhijianReply() {
		return rectificationZhijianReply;
	}

	public void setRectificationZhijianReply(String rectificationZhijianReply) {
		this.rectificationZhijianReply = rectificationZhijianReply;
	}

	public String getRectificationSellPurchaseReply() {
		return rectificationSellPurchaseReply;
	}

	public void setRectificationSellPurchaseReply(
			String rectificationSellPurchaseReply) {
		this.rectificationSellPurchaseReply = rectificationSellPurchaseReply;
	}

	public Date getRectificationZhijianTime() {
		return rectificationZhijianTime;
	}

	public void setRectificationZhijianTime(Date rectificationZhijianTime) {
		this.rectificationZhijianTime = rectificationZhijianTime;
	}

	public Date getRectificationSellPurchaseTime() {
		return rectificationSellPurchaseTime;
	}

	public void setRectificationSellPurchaseTime(Date rectificationSellPurchaseTime) {
		this.rectificationSellPurchaseTime = rectificationSellPurchaseTime;
	}

	public Integer getInspectionLeaderConfirm() {
		return inspectionLeaderConfirm;
	}

	public void setInspectionLeaderConfirm(Integer inspectionLeaderConfirm) {
		this.inspectionLeaderConfirm = inspectionLeaderConfirm;
	}

	public Integer getPurchaseLeaderConfirm() {
		return purchaseLeaderConfirm;
	}

	public void setPurchaseLeaderConfirm(Integer purchaseLeaderConfirm) {
		this.purchaseLeaderConfirm = purchaseLeaderConfirm;
	}

	public Date getInspectionLeaderConfirmDate() {
		return inspectionLeaderConfirmDate;
	}

	public void setInspectionLeaderConfirmDate(Date inspectionLeaderConfirmDate) {
		this.inspectionLeaderConfirmDate = inspectionLeaderConfirmDate;
	}

	public Date getPurchaseLeaderConfirmDate() {
		return purchaseLeaderConfirmDate;
	}

	public void setPurchaseLeaderConfirmDate(Date purchaseLeaderConfirmDate) {
		this.purchaseLeaderConfirmDate = purchaseLeaderConfirmDate;
	}

	public Integer getProjectStage() {
		return projectStage;
	}

	public void setProjectStage(Integer projectStage) {
		this.projectStage = projectStage;
	}

	public String getZhijian1() {
		return zhijian1;
	}

	public void setZhijian1(String zhijian1) {
		this.zhijian1 = zhijian1;
	}

	public String getZhijian2() {
		return zhijian2;
	}

	public void setZhijian2(String zhijian2) {
		this.zhijian2 = zhijian2;
	}

	public String getZhijian3() {
		return zhijian3;
	}

	public void setZhijian3(String zhijian3) {
		this.zhijian3 = zhijian3;
	}

	public String getInspectionContent() {
		return inspectionContent;
	}

	public void setInspectionContent(String inspectionContent) {
		this.inspectionContent = inspectionContent;
	}

	public Date getPredictCompleteTime() {
		return predictCompleteTime;
	}

	public void setPredictCompleteTime(Date predictCompleteTime) {
		this.predictCompleteTime = predictCompleteTime;
	}

	public Date getPurchaseReplyTime() {
		return purchaseReplyTime;
	}

	public void setPurchaseReplyTime(Date purchaseReplyTime) {
		this.purchaseReplyTime = purchaseReplyTime;
	}

	public Date getZhijianReplyTime() {
		return zhijianReplyTime;
	}

	public void setZhijianReplyTime(Date zhijianReplyTime) {
		this.zhijianReplyTime = zhijianReplyTime;
	}

	public Date getTechnicianReplyTime() {
		return technicianReplyTime;
	}

	public void setTechnicianReplyTime(Date technicianReplyTime) {
		this.technicianReplyTime = technicianReplyTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getSeriousLevel() {
		return seriousLevel;
	}

	public void setSeriousLevel(Integer seriousLevel) {
		this.seriousLevel = seriousLevel;
	}

	public List<ProjectTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<ProjectTask> tasks) {
		this.tasks = tasks;
	}

	public String getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getPurchaseConfirmDate() {
		return purchaseConfirmDate;
	}

	public void setPurchaseConfirmDate(Date purchaseConfirmDate) {
		this.purchaseConfirmDate = purchaseConfirmDate;
	}

	public Date getSellConfirmDate() {
		return sellConfirmDate;
	}

	public void setSellConfirmDate(Date sellConfirmDate) {
		this.sellConfirmDate = sellConfirmDate;
	}

	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public String getPurchaseContent() {
		return purchaseContent;
	}

	public void setPurchaseContent(String purchaseContent) {
		this.purchaseContent = purchaseContent;
	}

	public String getSellContent() {
		return sellContent;
	}

	public void setSellContent(String sellContent) {
		this.sellContent = sellContent;
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

    public Date getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(Date complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getCostAnalysis() {
        return costAnalysis;
    }

    public void setCostAnalysis(String costAnalysis) {
        this.costAnalysis = costAnalysis == null ? null : costAnalysis.trim();
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }

    public Integer getIsPurchase() {
        return isPurchase;
    }

    public void setIsPurchase(Integer isPurchase) {
        this.isPurchase = isPurchase;
    }

    public Integer getIsSell() {
        return isSell;
    }

    public void setIsSell(Integer isSell) {
        this.isSell = isSell;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent == null ? null : complaintContent.trim();
    }

	@Override
	public String toString() {
		return "ProjectComplaint [id=" + id + ", projectNo=" + projectNo
				+ ", complaintDate=" + complaintDate + ", costAnalysis="
				+ costAnalysis + ", purchaseId=" + purchaseId + ", sellId="
				+ sellId + ", isPurchase=" + isPurchase + ", isSell=" + isSell
				+ ", createTime=" + createTime + ", createPerson="
				+ createPerson + ", complaintContent=" + complaintContent
				+ ", purchaseContent=" + purchaseContent + ", sellContent="
				+ sellContent + ", purchaseConfirmDate=" + purchaseConfirmDate
				+ ", sellConfirmDate=" + sellConfirmDate + ", fileName="
				+ fileName + ", filePath=" + filePath + ", meetingNo="
				+ meetingNo + ", seriousLevel=" + seriousLevel
				+ ", purchaseReplyTime=" + purchaseReplyTime
				+ ", zhijianReplyTime=" + zhijianReplyTime
				+ ", technicianReplyTime=" + technicianReplyTime
				+ ", completeTime=" + completeTime + ", inspectionContent="
				+ inspectionContent + ", predictCompleteTime="
				+ predictCompleteTime + ", projectStage=" + projectStage
				+ ", inspectionLeaderConfirm=" + inspectionLeaderConfirm
				+ ", purchaseLeaderConfirm=" + purchaseLeaderConfirm
				+ ", inspectionLeaderConfirmDate="
				+ inspectionLeaderConfirmDate + ", purchaseLeaderConfirmDate="
				+ purchaseLeaderConfirmDate + ", rectificationZhijianReply="
				+ rectificationZhijianReply
				+ ", rectificationSellPurchaseReply="
				+ rectificationSellPurchaseReply
				+ ", rectificationZhijianTime=" + rectificationZhijianTime
				+ ", rectificationSellPurchaseTime="
				+ rectificationSellPurchaseTime + ", purchaseName="
				+ purchaseName + ", sellName=" + sellName + ", tasks=" + tasks
				+ ", projectName=" + projectName + ", customerName="
				+ customerName + ", zhijian1=" + zhijian1 + ", zhijian2="
				+ zhijian2 + ", zhijian3=" + zhijian3 + ", process=" + process
				+ ", startingTime=" + startingTime + ", endTime=" + endTime
				+ ", inspectionTimelinessNumber=" + inspectionTimelinessNumber
				+ ", complaintsNumber=" + complaintsNumber + ", projectAmount="
				+ projectAmount + ", processInstanceId=" + processInstanceId
				+ ", dingdingStatus=" + dingdingStatus + ", dingdingResult="
				+ dingdingResult + ", picUp=" + picUp + ", taskBrief="
				+ taskBrief + ", taskAttachment=" + taskAttachment
				+ ", verification=" + verification
				+ ", masterQualityInspection=" + masterQualityInspection
				+ ", qualityInspector1=" + qualityInspector1
				+ ", qualityInspector2=" + qualityInspector2
				+ ", qualityInspector3=" + qualityInspector3
				+ ", qualityInspector4=" + qualityInspector4
				+ ", qualityInspector5=" + qualityInspector5
				+ ", qualityInspector6=" + qualityInspector6
				+ ", qualityInspector7=" + qualityInspector7 + "]";
	}
}