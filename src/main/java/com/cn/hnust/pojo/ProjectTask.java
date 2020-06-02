package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProjectTask extends PageHelper implements Cloneable,Serializable {
    private Integer id;

    private String projectNo;

    private String initiator;  //发起人

    private String accepter;  //接收人

    private Date startTime;   //开始时间

    private String description;  //描述

    private String urgentReason;  //紧急理由（小于3天的任务必须给紧急理由）

    private String taskStatus; // 0:未完成,1:已完成 2.已暂停 3.已取消

    private String operator;   //操作人

    private Date operatorTime;  //操作时间
    
    private Date finishTime;   //完成时间
     
    private String finish;//项目是否完成: 1.完成,未完成
    
    private String isPause;//项目是否暂停  1:暂停项目

    private Date createTime;
    
    private String finishRatio; //完成率
    
    private int onTimeFinish;// 按时完成数量
    
    private int allFinish;//所以完成数量
    
    private int meetingTaskNum; //会议任务数量
    
    private String userName;
    
    private String meetingNo;//会议编号
    
    private String taskType; //0:技术部任务,1:会议任务2检验任务3:大货延期任务 4:质量、技术分析上传任务 5：图纸变更任务 6：项目完结任务   7：电子出货单签名任务 8:项目评审会  9:检验有问题任务  10:重要项目进度评审会 11:失联客户邮件提醒  
    
    private Date expectFinishTime;//预计完成时间
    
    private String taskFlag;
    
    private float averageHours;
    
    private int triggerId;       
    
    private int userId;
    
    private int sendOrReceive;
    
    private int taskId;
    
    private int indexNum;
    
    private int qualityId;            
    
    private String qualityReportUrl;  //质量报告链接
    
    private String operateExplain;    //操作说明

    private String projectNoId;       //项目表id
    
    private String returnUrl;         //跳转地址
    
    private String productFileName;   //质检问题需要上传的产品视频图片
    
    
    private InspectionReservation inspectionReservation;
    
    private String taskUrl;
    
    private Integer isVideo;        //是否需要提供视频和交期（0：否 1：是）
    private Integer isVideoUpload;  //视频是否已上传（0：未上传 1：已上传）
    
    //add by polo  2018.7.26
    private String delayList;       //延期表id list
    private String dingTalkId;      //钉钉userid
    private String progress;        //进度内容
    private Date progressDate;      //进度更新时间
    
    //虚拟字段，是否需要更新进度
    private String progressStatus;  //进度状态
    private Integer isPurchase;     //是否是采购任务
    private List<TaskReport> taskReportList;
    private String sellName; //销售名
    private String purchaseName; //采购名
    private Integer roleNo;      //角色
    private Integer overCount;   //超期5天未完成任务数
    private String checkType;    //检测类型
    private String checkPlace;   //检验地址
    private String openRate;    //开箱比例
    private Date newDate;    //工厂实际完成时间
    
    private String start;//开始时间
    private String end;//结束时间
    private String projectAmount;//合同金额
    private String projectName;//项目名
    private String plantAnalysis;//项目等级
    private int complaintId;//投诉单Id
    private int verification;//是否验证
    private String verifier;//验证人
    private String verificationTime;//验证时间
    private String drawingId;//图片Id
    
    
    public String getDrawingId() {
		return drawingId;
	}
	public void setDrawingId(String drawingId) {
		this.drawingId = drawingId;
	}
	public int getVerification() {
		return verification;
	}
	public void setVerification(int verification) {
		this.verification = verification;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	public String getVerificationTime() {
		return verificationTime;
	}
	public void setVerificationTime(String verificationTime) {
		this.verificationTime = verificationTime;
	}
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	public String getPlantAnalysis() {
		return plantAnalysis;
	}
	public void setPlantAnalysis(String plantAnalysis) {
		this.plantAnalysis = plantAnalysis;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectAmount() {
		return projectAmount;
	}
	public void setProjectAmount(String projectAmount) {
		this.projectAmount = projectAmount;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public Date getNewDate() {
		return newDate;
	}
   public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}





	public String getOpenRate() {
		return openRate;
	}





	public void setOpenRate(String openRate) {
		this.openRate = openRate;
	}





	@Override
	public String toString() {
		return "ProjectTask [id=" + id + ", projectNo=" + projectNo
				+ ", initiator=" + initiator + ", accepter=" + accepter
				+ ", startTime=" + startTime + ", description=" + description
				+ ", urgentReason=" + urgentReason + ", taskStatus="
				+ taskStatus + ", operator=" + operator + ", operatorTime="
				+ operatorTime + ", finishTime=" + finishTime + ", finish="
				+ finish + ", isPause=" + isPause + ", createTime="
				+ createTime + ", finishRatio=" + finishRatio
				+ ", onTimeFinish=" + onTimeFinish + ", allFinish=" + allFinish
				+ ", meetingTaskNum=" + meetingTaskNum + ", userName="
				+ userName + ", meetingNo=" + meetingNo + ", taskType="
				+ taskType + ", expectFinishTime=" + expectFinishTime
				+ ", taskFlag=" + taskFlag + ", averageHours=" + averageHours
				+ ", triggerId=" + triggerId + ", userId=" + userId
				+ ", sendOrReceive=" + sendOrReceive + ", taskId=" + taskId
				+ ", indexNum=" + indexNum + ", qualityId=" + qualityId
				+ ", qualityReportUrl=" + qualityReportUrl
				+ ", operateExplain=" + operateExplain + ", projectNoId="
				+ projectNoId + ", returnUrl=" + returnUrl
				+ ", productFileName=" + productFileName
				+ ", inspectionReservation=" + inspectionReservation
				+ ", taskUrl=" + taskUrl + ", isVideo=" + isVideo
				+ ", isVideoUpload=" + isVideoUpload + ", delayList="
				+ delayList + ", dingTalkId=" + dingTalkId + ", progress="
				+ progress + ", progressDate=" + progressDate
				+ ", progressStatus=" + progressStatus + ", isPurchase="
				+ isPurchase + ", taskReportList=" + taskReportList
				+ ", sellName=" + sellName + ", purchaseName=" + purchaseName
				+ ", roleNo=" + roleNo + ", overCount=" + overCount
				+ ", checkType=" + checkType + ", checkPlace=" + checkPlace
				+ ", openRate=" + openRate + ", newDate=" + newDate
				+ ", start=" + start + ", end=" + end + ", projectAmount="
				+ projectAmount + ", projectName=" + projectName
				+ ", plantAnalysis=" + plantAnalysis + ", complaintId="
				+ complaintId + ", verification=" + verification
				+ ", verifier=" + verifier + ", verificationTime="
				+ verificationTime + ", drawingId=" + drawingId
				+ ", operatorTimeView=" + operatorTimeView + ", searchName="
				+ searchName + ", noFinishCount=" + noFinishCount
				+ ", finishCount=" + finishCount + ", job=" + job + ", sign="
				+ sign + "]";
	}





	public String getProductFileName() {
		return productFileName;
	}





	public void setProductFileName(String productFileName) {
		this.productFileName = productFileName;
	}





	public String getCheckPlace() {
		return checkPlace;
	}





	public void setCheckPlace(String checkPlace) {
		this.checkPlace = checkPlace;
	}





	public String getReturnUrl() {
		return returnUrl;
	}





	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}





	public String getCheckType() {
		return checkType;
	}





	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}





	public Integer getOverCount() {
		return overCount;
	}

	public void setOverCount(Integer overCount) {
		this.overCount = overCount;
	}

	public Integer getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(Integer roleNo) {
		this.roleNo = roleNo;
	}

	public Integer getIsVideoUpload() {
		return isVideoUpload;
	}

	public void setIsVideoUpload(Integer isVideoUpload) {
		this.isVideoUpload = isVideoUpload;
	}

	public Integer getIsVideo() {
		return isVideo;
	}

	public void setIsVideo(Integer isVideo) {
		this.isVideo = isVideo;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public List<TaskReport> getTaskReportList() {
		return taskReportList;
	}

	public void setTaskReportList(List<TaskReport> taskReportList) {
		this.taskReportList = taskReportList;
	}

	public Integer getIsPurchase() {
		return isPurchase;
	}

	public void setIsPurchase(Integer isPurchase) {
		this.isPurchase = isPurchase;
	}

	public String getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}

	//虚拟字段
    private String operatorTimeView;  
    
    private String searchName;
    
    private int noFinishCount; //未完成数量
    
    private int finishCount; //完成数量
    
    private String job;  //用户表的job
    
    private String sign;  //标记是否已处理
    
	private static final long serialVersionUID = 1L;
	
	
	


	public String getSign() {
		return sign;
	}





	public void setSign(String sign) {
		this.sign = sign;
	}





	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public Date getProgressDate() {
		return progressDate;
	}

	public void setProgressDate(Date progressDate) {
		this.progressDate = progressDate;
	}

	public String getDingTalkId() {
		return dingTalkId;
	}

	public void setDingTalkId(String dingTalkId) {
		this.dingTalkId = dingTalkId;
	}

	public String getDelayList() {
		return delayList;
	}

	public void setDelayList(String delayList) {
		this.delayList = delayList;
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
		this.projectNo = projectNo;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getUrgentReason() {
		return urgentReason;
	}

	public void setUrgentReason(String urgentReason) {
		this.urgentReason = urgentReason;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getIsPause() {
		return isPause;
	}

	public void setIsPause(String isPause) {
		this.isPause = isPause;
	}

	public int getNoFinishCount() {
		return noFinishCount;
	}

	public void setNoFinishCount(int noFinishCount) {
		this.noFinishCount = noFinishCount;
	}

	public int getFinishCount() {
		return finishCount;
	}

	public void setFinishCount(int finishCount) {
		this.finishCount = finishCount;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getFinishRatio() {
		return finishRatio;
	}

	public void setFinishRatio(String finishRatio) {
		this.finishRatio = finishRatio;
	}

	public int getOnTimeFinish() {
		return onTimeFinish;
	}

	public void setOnTimeFinish(int onTimeFinish) {
		this.onTimeFinish = onTimeFinish;
	}

	public int getAllFinish() {
		return allFinish;
	}

	public void setAllFinish(int allFinish) {
		this.allFinish = allFinish;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public int getMeetingTaskNum() {
		return meetingTaskNum;
	}

	public void setMeetingTaskNum(int meetingTaskNum) {
		this.meetingTaskNum = meetingTaskNum;
	}

	public Date getExpectFinishTime() {
		return expectFinishTime;
	}

	public void setExpectFinishTime(Date expectFinishTime) {
		this.expectFinishTime = expectFinishTime;
	}

	public String getTaskFlag() {
		return taskFlag;
	}

	public void setTaskFlag(String taskFlag) {
		this.taskFlag = taskFlag;
	}

	public float getAverageHours() {
		return averageHours;
	}

	public void setAverageHours(float averageHours) {
		this.averageHours = averageHours;
	}

	public int getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(int triggerId) {
		this.triggerId = triggerId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSendOrReceive() {
		return sendOrReceive;
	}

	public void setSendOrReceive(int sendOrReceive) {
		this.sendOrReceive = sendOrReceive;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(int indexNum) {
		this.indexNum = indexNum;
	}

	public String getOperatorTimeView() {
		return operatorTimeView;
	}

	public void setOperatorTimeView(String operatorTimeView) {
		this.operatorTimeView = operatorTimeView;
	}

	public int getQualityId() {
		return qualityId;
	}

	public void setQualityId(int qualityId) {
		this.qualityId = qualityId;
	}

	public String getQualityReportUrl() {
		return qualityReportUrl;
	}

	public void setQualityReportUrl(String qualityReportUrl) {
		this.qualityReportUrl = qualityReportUrl;
	}

	public String getOperateExplain() {
		return operateExplain;
	}

	public void setOperateExplain(String operateExplain) {
		this.operateExplain = operateExplain;
	}

	public String getProjectNoId() {
		return projectNoId;
	}

	public void setProjectNoId(String projectNoId) {
		this.projectNoId = projectNoId;
	}

	public InspectionReservation getInspectionReservation() {
		return inspectionReservation;
	}

	public void setInspectionReservation(InspectionReservation inspectionReservation) {
		this.inspectionReservation = inspectionReservation;
	}

	public String getTaskUrl() {
		return taskUrl;
	}

	public void setTaskUrl(String taskUrl) {
		this.taskUrl = taskUrl;
	}
	
	
	@Override
	public Object clone() {
		ProjectTask stu = null;
		try{
			stu = (ProjectTask)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return stu;
	}

}