package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class InspectionReservation extends PageHelper implements Serializable {
	private int dTalkIds;//对应钉钉审批认领
	private String checkNumber;//校验编号
    private Integer id;

    private String projectNoId;   

    private String projectNo;

    private String initiator;

    private String accepter;

    private String description;

    private String urgentReason;                //紧急原因（3天内的检测任务必须填写）

    private Date createDate;

    private String produceStatus;             //产品状态

    private Date finishTime;                   //完成时间

    private Date expectedDelivery;             //预计交期

    private Date shippingDate;                 //运输时间
    
    private String inputKey;
    
    private List<ProjectDrawing> projectDrawingList;
    
    private List<ProjectInspectionReport> inspectionPlanList;
    
    private Date qualityDate;
    
    private String zhijian1;
    
    private String zhijian2;
    
    private Integer projectTaskId;
    
    private String operator;
    
    private String taskStatus;
    
    private Date operatorTime;
    
    private String qualityName;
    
    private String userName;
    
    private String inspectionAddress;  //检验地点
    
    private String testType;
    
    private String openRate;    //开箱比例
    
    private Integer shippingApproval;  //准许出货确认单是否收到 （0:未收到 1:已收到）
    
    private String[] qualityNames;     //多个质检员，用于筛选
    
    private String projectName;        //项目号
         
    private Date newDate;
    
    private String start;//开始时间
    private String end;//结束时间
    private String projectAmount;//合同金额
    private String plantAnalysis;//项目等级
    private String incomingInspection;//进料检验
    private String inspectionPlan;//检验计划
    private String qualityComplaint;//质量投诉
    
    private static final long serialVersionUID = 1L;

    
    
    
	public String getIncomingInspection() {
		return incomingInspection;
	}

	public void setIncomingInspection(String incomingInspection) {
		this.incomingInspection = incomingInspection;
	}

	public String getInspectionPlan() {
		return inspectionPlan;
	}

	public void setInspectionPlan(String inspectionPlan) {
		this.inspectionPlan = inspectionPlan;
	}

	public String getQualityComplaint() {
		return qualityComplaint;
	}

	public void setQualityComplaint(String qualityComplaint) {
		this.qualityComplaint = qualityComplaint;
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

	public String getProjectAmount() {
		return projectAmount;
	}

	public void setProjectAmount(String projectAmount) {
		this.projectAmount = projectAmount;
	}

	public String getPlantAnalysis() {
		return plantAnalysis;
	}

	public void setPlantAnalysis(String plantAnalysis) {
		this.plantAnalysis = plantAnalysis;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return "InspectionReservation [id=" + id + ", projectNoId="
				+ projectNoId + ", projectNo=" + projectNo + ", initiator="
				+ initiator + ", accepter=" + accepter + ", description="
				+ description + ", urgentReason=" + urgentReason
				+ ", createDate=" + createDate + ", produceStatus="
				+ produceStatus + ", finishTime=" + finishTime
				+ ", expectedDelivery=" + expectedDelivery + ", shippingDate="
				+ shippingDate + ", inputKey=" + inputKey
				+ ", projectDrawingList=" + projectDrawingList
				+ ", inspectionPlanList=" + inspectionPlanList
				+ ", qualityDate=" + qualityDate + ", zhijian1=" + zhijian1
				+ ", zhijian2=" + zhijian2 + ", projectTaskId=" + projectTaskId
				+ ", operator=" + operator + ", taskStatus=" + taskStatus
				+ ", operatorTime=" + operatorTime + ", qualityName="
				+ qualityName + ", userName=" + userName
				+ ", inspectionAddress=" + inspectionAddress + ", testType="
				+ testType + ", openRate=" + openRate + ", shippingApproval="
				+ shippingApproval + ", qualityNames="
				+ Arrays.toString(qualityNames) + ", projectName="
				+ projectName + ", newDate=" + newDate + ", start=" + start
				+ ", end=" + end + ", projectAmount=" + projectAmount
				+ ", plantAnalysis=" + plantAnalysis + "]";
	}

	public String[] getQualityNames() {
		return qualityNames;
	}

	public void setQualityNames(String[] qualityNames) {
		this.qualityNames = qualityNames;
	}

	public Integer getShippingApproval() {
		return shippingApproval;
	}

	public void setShippingApproval(Integer shippingApproval) {
		this.shippingApproval = shippingApproval;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectNoId() {
        return projectNoId;
    }

    public void setProjectNoId(String projectNoId) {
        this.projectNoId = projectNoId == null ? null : projectNoId.trim();
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator == null ? null : initiator.trim();
    }

    public String getAccepter() {
        return accepter;
    }

    public void setAccepter(String accepter) {
        this.accepter = accepter == null ? null : accepter.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrgentReason() {
        return urgentReason;
    }

    public void setUrgentReason(String urgentReason) {
        this.urgentReason = urgentReason == null ? null : urgentReason.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getProduceStatus() {
        return produceStatus;
    }

    public void setProduceStatus(String produceStatus) {
        this.produceStatus = produceStatus == null ? null : produceStatus.trim();
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(Date expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

	public String getInputKey() {
		return inputKey;
	}

	public void setInputKey(String inputKey) {
		this.inputKey = inputKey;
	}

	public List<ProjectDrawing> getProjectDrawingList() {
		return projectDrawingList;
	}

	public void setProjectDrawingList(List<ProjectDrawing> projectDrawingList) {
		this.projectDrawingList = projectDrawingList;
	}

	public List<ProjectInspectionReport> getInspectionPlanList() {
		return inspectionPlanList;
	}

	public void setInspectionPlanList(List<ProjectInspectionReport> inspectionPlanList) {
		this.inspectionPlanList = inspectionPlanList;
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

	public Integer getProjectTaskId() {
		return projectTaskId;
	}

	public void setProjectTaskId(Integer projectTaskId) {
		this.projectTaskId = projectTaskId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	public String getQualityName() {
		return qualityName;
	}

	public void setQualityName(String qualityName) {
		this.qualityName = qualityName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getQualityDate() {
		return qualityDate;
	}

	public void setQualityDate(Date qualityDate) {
		this.qualityDate = qualityDate;
	}

	public String getInspectionAddress() {
		return inspectionAddress;
	}

	public void setInspectionAddress(String inspectionAddress) {
		this.inspectionAddress = inspectionAddress;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public int getdTalkIds() {
		return dTalkIds;
	}

	public void setdTalkIds(int dTalkIds) {
		this.dTalkIds = dTalkIds;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	
	
}