package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 项目实体对象
 * @author Administrator
 *
 */
public class Project extends PageHelper implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private String id;

    private String projectNo;

    private String projectName;
    
    private String projectNameEn;

    private Date deliveryDate;
    
    private String roleName;
    
    private String trueName;

    private int deliveryStatus;

    private int warningStatus;

    private int importance;

    private int stage;

	private int emailUserId; //跟单销售Id
    
    private int purchaseId; //采购Id
    
    private int saleId; //销售Id

    private Date poDate;

    private Date scheduledDate;

    private Date actualStartDate;

    private Date createDate;
    
    private int finish;
  
    private String isPause; //项目是否暂停
    
    private String pauseReason; //项目取消、启动原因
    
    private String  inputKey; //关键字查询
    
    private List<Delay> delayList; //项目延期信息
    
    private List<ProductionPlan> planList; //生产计划
    
    private List<ProjectReport> reportList; //项目汇报
    
    private List<ProjectDrawing> projectDrawingList;
    
    private List<ProjectInspectionReport> inspectionReportList;
    
    private List<InspectionReservation> inspectionList;    //验货预约list
    
    private List<ProjectSchedule> scheduleList;        //大货交期表
    
    private List<ProjectComplaint> complaintList;      //客户投诉列表
    
    private List<QualityReport> erpReports;          //ERP质检报告
    
    private List<ProjectPause> projectPauses;        //项目暂停列表
    
    private List<ProjectFactory> factoryList;        //下单工厂列表
    
    private List<DeliveryDateLog> deliveryList;      //交期修改表
    
    private List<DingTalkMileStone> milestones;      //里程碑
    
    
    //add by polo 2018.1.2
    private List<AnalysisIssue> analysisIssueList;   //质量问题、工艺问题    
    private QualityAnalysis qualityAnalysis;         //质量分析数据       
    //end
    
    
    private Integer require;                        //是否更新客户需求0：否 1：是     
    private Integer updateInspect;                  //是否更新检验计划0：否 1：是
    private Integer updateDrawing;                  //是否更新图纸0：否 1：是
    private Integer difficultProject;               //是否疑难项目0：否 1：是
    private Integer dateBefore;                     //付款距今日期
    private Integer importantTaskTotal;             //重要项目15天REVIEW会总任务数
    private Integer importantTaskFinish;            //重要项目15天REVIEW会完成任务数
    private Integer metalDeliveryTime;            //金属交期天数最大天数
    private Integer metalDeliveryTime1;            //金属交期天数最小天数
    private Integer plasticDeliveryPeriod;            //塑料交期天数最大天数
    private Integer plasticDeliveryPeriod1;            //塑料交期天数最大天数
    private Date dateDelivery;                       //客户要求交货期
    private Integer delayDay;                       //客户要求交货期
    private String explain;                            //解释理由
    private String interpretationDocument;            //解释文件
    private String factoryName;                      //工厂名称
    private Integer incompleteInspectionTasks;       //未完成检验任务
    private Date lateDeliveryDate;                   //公司同意最终延期
    private String historyInspection;                //历史质检
    private String startTime;                //起始时间
    private String endTime;                //结束时间
    private String sampleDeliveryDate;           //起始时间
    private String deliveryTime;                //结束时间
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

	public String getSampleDeliveryDate() {
		return sampleDeliveryDate;
	}

	public void setSampleDeliveryDate(String sampleDeliveryDate) {
		this.sampleDeliveryDate = sampleDeliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getHistoryInspection() {
		return historyInspection;
	}

	public void setHistoryInspection(String historyInspection) {
		this.historyInspection = historyInspection;
	}

	public Date getLateDeliveryDate() {
		return lateDeliveryDate;
	}

	public void setLateDeliveryDate(Date lateDeliveryDate) {
		this.lateDeliveryDate = lateDeliveryDate;
	}

	private List<QualityReport> qrList;
    
    private Date startDate; //开始时间
    
    private Date endDate; //结束时间
    
    private List<Task> taskList; //项目的关联任务
     
    private String reportName;
    
    private String report;
    
    private String picUrl;
    
    private Date projectReportDate;
    
    private List<Comment> commentList; //销售输入的状态信息
    
    private List<StatusEnter> statusEnterList;//销售输入的状态信息
    
    private Date sampleScheduledDate;//样品交期 
    
    private int flag;
    
    private String companyName;  //工厂名称
    
    private String purchaseNameId; //采购名字查询
    
    private String delayType; //延期类型

    private List<ProjectReport> projectReportList;//查询周报的更新时间
    
    private RoleBindList roleBindList;

    private String createTime; //质量分析表时间
    
    // JASON SANG
    private Integer projectType; //根据项目状态查询
    
    private Integer projectStage; //根据项目阶段
    
    private String purchaseName; //采购
    
    private String sellName;//跟单
    
    private String saleName;//销售
    
    private String qualityName;//质检
    
    private String userName;//登录用户
    
    private int roleNo;
    
    private String status;
    
    private String zhijian1;
    
    private String zhijian2;
    
    private String zhijian3;
    
    private String projectAmount;
    
    private String weekPicture;
    
    private String weekInfo;
    
    private int sortField; //排序字段优先级
    
    private Date finishTime; //完成时间
    
    private Date sampleFinishTime;  //样品完成时间
    
    //ERP过来的三个字段
    /** 样品交期*/
    private Date dateSample; //样品交期
    /** 合同交期*/
    private Date completionTime; //大货交期
    /** 上传日期*/
    private Date dateSampleUploading; //合同签订日期(也用于记录日期录入时间)
    
    private String productImg;//产品图;
    
    private int sampleFinish; //样品完结;
    
    private Delay delay;//项目申请延期
    
    private List<QualityReport> qualityReportList;//项目质检报告
    
    private QualityReport qualityReport;
    
    private String operatorType;//代表操作
    
    private String screenType; //多条件组合筛选
    
    private List<Integer> plantAnalysisS;//多等级
    
    private List<Integer> projectStageS;//多阶段
    
    private List<Integer> projectStatusS;//多状态(定时任务去更新状态)
    
    private List<Integer> detailStatusS;//详情状态数组
    
    private List<Integer> delayStatusS;  //延期筛选
    
    private Feedback feedback;          //项目最新反馈
    
    
    private Integer projectStatus;//项目状态  0:新立项项目 1：正常进行项目 2：大货完结 4:项目暂停 5：取消项目 6：样品完结 
    
    private int finishTask;  //完成任务
    
    private int allTask;     //所有任务 （去除取消任务）
    
    private int puaseTask;   //暂停任务数
    
    private int qualityTask;   //质量任务
    
    private int qualityFinishTask;//完成的质量任务
    
    private Date moneyDate;
    
    private String qualityReportSelect;//质检报告查询
    
    private String expectedShipmentSelect;//预计7天出货
    
    private ProjectReport projectReport;
    
    private Integer delayTriggerFlag;  //是否已触发任务流
    
    private String technician;    //技术员
    
    private String customerName;  //客户名

    private Integer customerGrade;  //客户等级 0：无 1：A 2：B 3：C 
    
    private String exportDate;     //出口日期

	//项目详情状态*(跟单进行更新)
    private Integer detailStatus;   //1：一切正常    2:重新打样 3：打样失败 4：反复沟通 5：样品没确认 6：样品已确认 7：其他问题
    private Integer createPersonId;  //录入人id
    private Integer scheduledDays;   //预计耗时
    private Date newPredictDate;     //最新交货日
    private Date urgentDeliveryDate; //紧急交货日
    
    //add by polo (2019.05.10)
    private Date prevSampleDate;      //上次送样日期
    private String customerAttitude;  //客户态度

    //虚拟字段
    private Boolean isProcess;     //是否含有过程检
    private Boolean isSample;      //是否样品检
    private Boolean isProduct;     //是否大货检
    private Boolean isNoteProcess; //是否需要提醒过程检
    private Boolean isPurchaseWeekReport; //采购是否发周报
    private Boolean isContract;           //是否一周内制作合同
    private String factoryId;             //工厂id
    private Boolean isNewProject;         //判断是否是新项目
    
    
    private String importantSelect;    //是否选择A、B级项目两周未更新周报
    private int shippingApproval;      //准许出货单是否上传 0：未上传 1：已上传 2：部分未传 3:未有检测报告
    private Boolean isStart;           //是否开项目启动会
    private String detailStr;          //详情状态
    private Integer detailUnUpdate;   //详情状态未更新
    private Integer stageUnUpdate;    //阶段未更新
    private String createName;        //录入人名字   
    private Integer dateType;         //时间查询  1：一周时间  2：一个月
    private Integer moneyDateType;    //到账查询  1：一个月  2：二个月 3:三个月 4：超过3个月
    private Integer contractDays;     //合同日期
    private Integer delayDays;        //延期天数
    private Integer unFinshedTask;    //投诉任务未完成的数量
    private String technicianStr;     //技术筛选
    private Date originalDeliveryDate;     //原始大货交期
    private Date originalSampleScheduledDate; //原始样品交期
    
    private String sellDingTalkId;       //销售钉钉id
    private String purchaseDingTalkId;   //采购钉钉id
    private Integer complaintVolume;   //上个月投诉项目数
    private Integer monthlyComplaints;   //本月投诉项目数
    private Integer projectsUnderWay;   //正在进行项目数
    private Integer sampleContracts;   //样品无大货项目
    private Integer allContracts;   //全部项目数
    private Date delayedDeliveryDate;   //样品延期发货日
    private Date cargoDelayedDeliveryDate;   //大货延期发货日
    private Integer deliveryReminder;   //交期提醒0，无1，表示交期过松
    /** 项目等级*/
    private Integer plantAnalysis;   //0：无等级  1：A 2：B 3：C
    
    private String plantAnalysisView;
    
    /** 项目材料属性*/
    private int projectMaterialProperties;

    private String technology;
    
    private List<ProjectInspectionReport> inspectionPlanList;
    
    private List<ProjectTask> projectTaskList;
    
    private List<ProjectDrawing> projectDemandReportList;

    private List<Feedback> feedbackList;
    
    private int InspectionReservationNum;
   
    private String projectMembers;   //全部项目成员
    
    private Integer recentInspectionReport;       //最近出检验报告
    
    
    private String sailingDate;       //船期
    
 
    
    private Integer contractNumber;       //最近报告数

	private Integer firstInspectionReport;//处理

	public Integer getFirstInspectionReport() {
		return firstInspectionReport;
	}

	public void setFirstInspectionReport(Integer firstInspectionReport) {
		this.firstInspectionReport = firstInspectionReport;
	}

	public Integer getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(Integer contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getSailingDate() {
		return sailingDate;
	}

	public void setSailingDate(String sailingDate) {
		this.sailingDate = sailingDate;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public Integer getRecentInspectionReport() {
		return recentInspectionReport;
	}

	public void setRecentInspectionReport(Integer recentInspectionReport) {
		this.recentInspectionReport = recentInspectionReport;
	}

	public String getProjectMembers() {
		return projectMembers;
	}

	public void setProjectMembers(String projectMembers) {
		this.projectMembers = projectMembers;
	}

	public Integer getIncompleteInspectionTasks() {
		return incompleteInspectionTasks;
	}

	public void setIncompleteInspectionTasks(Integer incompleteInspectionTasks) {
		this.incompleteInspectionTasks = incompleteInspectionTasks;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public Integer getDelayDay() {
		return delayDay;
	}

	public void setDelayDay(Integer delayDay) {
		this.delayDay = delayDay;
	}

	public Date getDateDelivery() {
		return dateDelivery;
	}

	public void setDateDelivery(Date dateDelivery) {
		this.dateDelivery = dateDelivery;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getInterpretationDocument() {
		return interpretationDocument;
	}

	public void setInterpretationDocument(String interpretationDocument) {
		this.interpretationDocument = interpretationDocument;
	}

	public Integer getMetalDeliveryTime() {
		return metalDeliveryTime;
	}

	public void setMetalDeliveryTime(Integer metalDeliveryTime) {
		this.metalDeliveryTime = metalDeliveryTime;
	}

	public Integer getMetalDeliveryTime1() {
		return metalDeliveryTime1;
	}

	public void setMetalDeliveryTime1(Integer metalDeliveryTime1) {
		this.metalDeliveryTime1 = metalDeliveryTime1;
	}

	public Integer getPlasticDeliveryPeriod() {
		return plasticDeliveryPeriod;
	}

	public void setPlasticDeliveryPeriod(Integer plasticDeliveryPeriod) {
		this.plasticDeliveryPeriod = plasticDeliveryPeriod;
	}

	public Integer getPlasticDeliveryPeriod1() {
		return plasticDeliveryPeriod1;
	}

	public void setPlasticDeliveryPeriod1(Integer plasticDeliveryPeriod1) {
		this.plasticDeliveryPeriod1 = plasticDeliveryPeriod1;
	}

	public List<DingTalkMileStone> getMilestones() {
		return milestones;
	}

	public void setMilestones(List<DingTalkMileStone> milestones) {
		this.milestones = milestones;
	}

	public Integer getImportantTaskTotal() {
		return importantTaskTotal;
	}

	public void setImportantTaskTotal(Integer importantTaskTotal) {
		this.importantTaskTotal = importantTaskTotal;
	}

	public Integer getImportantTaskFinish() {
		return importantTaskFinish;
	}

	public void setImportantTaskFinish(Integer importantTaskFinish) {
		this.importantTaskFinish = importantTaskFinish;
	}

	public List<AnalysisIssue> getAnalysisIssueList() {
		return analysisIssueList;
	}

	public void setAnalysisIssueList(List<AnalysisIssue> analysisIssueList) {
		this.analysisIssueList = analysisIssueList;
	}

	public QualityAnalysis getQualityAnalysis() {
		return qualityAnalysis;
	}

	public void setQualityAnalysis(QualityAnalysis qualityAnalysis) {
		this.qualityAnalysis = qualityAnalysis;
	}

	public List<DeliveryDateLog> getDeliveryList() {
		return deliveryList;
	}

	public void setDeliveryList(List<DeliveryDateLog> deliveryList) {
		this.deliveryList = deliveryList;
	}

	public String getProjectNameEn() {
		return projectNameEn;
	}

	public void setProjectNameEn(String projectNameEn) {
		this.projectNameEn = projectNameEn;
	}

    public Integer getDateBefore() {
		return dateBefore;
	}

	public void setDateBefore(Integer dateBefore) {
		this.dateBefore = dateBefore;
	}

	public Integer getDifficultProject() {
		return difficultProject;
	}

	public void setDifficultProject(Integer difficultProject) {
		this.difficultProject = difficultProject;
	}

	public List<ProjectFactory> getFactoryList() {
		return factoryList;
	}

	public void setFactoryList(List<ProjectFactory> factoryList) {
		this.factoryList = factoryList;
	}

	public List<ProjectPause> getProjectPauses() {
		return projectPauses;
	}

	public void setProjectPauses(List<ProjectPause> projectPauses) {
		this.projectPauses = projectPauses;
	}

	public List<QualityReport> getErpReports() {
		return erpReports;
	}

	public void setErpReports(List<QualityReport> erpReports) {
		this.erpReports = erpReports;
	}

	public Integer getRequire() {
		return require;
	}

	public void setRequire(Integer require) {
		this.require = require;
	}

	public Integer getUpdateInspect() {
		return updateInspect;
	}

	public void setUpdateInspect(Integer updateInspect) {
		this.updateInspect = updateInspect;
	}

	public Integer getUpdateDrawing() {
		return updateDrawing;
	}

	public void setUpdateDrawing(Integer updateDrawing) {
		this.updateDrawing = updateDrawing;
	}

	public List<ProjectComplaint> getComplaintList() {
		return complaintList;
	}

	public void setComplaintList(List<ProjectComplaint> complaintList) {
		this.complaintList = complaintList;
	}

	public List<ProjectSchedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<ProjectSchedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public List<InspectionReservation> getInspectionList() {
		return inspectionList;
	}

	public void setInspectionList(List<InspectionReservation> inspectionList) {
		this.inspectionList = inspectionList;
	}
    
    public Integer getDeliveryReminder() {
		return deliveryReminder;
	}

	public void setDeliveryReminder(Integer deliveryReminder) {
		this.deliveryReminder = deliveryReminder;
	}

	public Date getCargoDelayedDeliveryDate() {
		return cargoDelayedDeliveryDate;
	}

	public void setCargoDelayedDeliveryDate(Date cargoDelayedDeliveryDate) {
		this.cargoDelayedDeliveryDate = cargoDelayedDeliveryDate;
	}

	public Date getDelayedDeliveryDate() {
		return delayedDeliveryDate;
	}

	public void setDelayedDeliveryDate(Date delayedDeliveryDate) {
		this.delayedDeliveryDate = delayedDeliveryDate;
	}

	public Integer getSampleContracts() {
		return sampleContracts;
	}

	public void setSampleContracts(Integer sampleContracts) {
		this.sampleContracts = sampleContracts;
	}

	public Integer getAllContracts() {
		return allContracts;
	}

	public void setAllContracts(Integer allContracts) {
		this.allContracts = allContracts;
	}

	public Integer getComplaintVolume() {
		return complaintVolume;
	}

	public void setComplaintVolume(Integer complaintVolume) {
		this.complaintVolume = complaintVolume;
	}

	public Integer getMonthlyComplaints() {
		return monthlyComplaints;
	}

	public void setMonthlyComplaints(Integer monthlyComplaints) {
		this.monthlyComplaints = monthlyComplaints;
	}

	public Integer getProjectsUnderWay() {
		return projectsUnderWay;
	}

	public void setProjectsUnderWay(Integer projectsUnderWay) {
		this.projectsUnderWay = projectsUnderWay;
	}

	public int getQualityFinishTask() {
		return qualityFinishTask;
	}

	public void setQualityFinishTask(int qualityFinishTask) {
		this.qualityFinishTask = qualityFinishTask;
	}

	

	public int getQualityTask() {
		return qualityTask;
	}

	public void setQualityTask(int qualityTask) {
		this.qualityTask = qualityTask;
	}

	public Date getPrevSampleDate() {
		return prevSampleDate;
	}

	public void setPrevSampleDate(Date prevSampleDate) {
		this.prevSampleDate = prevSampleDate;
	}

	public String getCustomerAttitude() {
		return customerAttitude;
	}

	public void setCustomerAttitude(String customerAttitude) {
		this.customerAttitude = customerAttitude;
	}

	public String getSellDingTalkId() {
		return sellDingTalkId;
	}

	public void setSellDingTalkId(String sellDingTalkId) {
		this.sellDingTalkId = sellDingTalkId;
	}

	public String getPurchaseDingTalkId() {
		return purchaseDingTalkId;
	}

	public void setPurchaseDingTalkId(String purchaseDingTalkId) {
		this.purchaseDingTalkId = purchaseDingTalkId;
	}

	public Integer getCustomerGrade() {
		return customerGrade;
	}

	public void setCustomerGrade(Integer customerGrade) {
		this.customerGrade = customerGrade;
	}

	public String getExportDate() {
		return exportDate;
	}

	public void setExportDate(String exportDate) {
		this.exportDate = exportDate;
	}

	public Date getOriginalSampleScheduledDate() {
		return originalSampleScheduledDate;
	}

	public void setOriginalSampleScheduledDate(Date originalSampleScheduledDate) {
		this.originalSampleScheduledDate = originalSampleScheduledDate;
	}

	public Date getOriginalDeliveryDate() {
		return originalDeliveryDate;
	}

	public void setOriginalDeliveryDate(Date originalDeliveryDate) {
		this.originalDeliveryDate = originalDeliveryDate;
	}

	public Boolean getIsNewProject() {
		return isNewProject;
	}

	public void setIsNewProject(Boolean isNewProject) {
		this.isNewProject = isNewProject;
	}

	public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTechnicianStr() {
		return technicianStr;
	}

	public void setTechnicianStr(String technicianStr) {
		this.technicianStr = technicianStr;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	public Integer getUnFinshedTask() {
		return unFinshedTask;
	}

	public void setUnFinshedTask(Integer unFinshedTask) {
		this.unFinshedTask = unFinshedTask;
	}

	public String getZhijian3() {
		return zhijian3;
	}

	public void setZhijian3(String zhijian3) {
		this.zhijian3 = zhijian3;
	}

	public Integer getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(Integer delayDays) {
		this.delayDays = delayDays;
	}

	public Integer getContractDays() {
		return contractDays;
	}

	public void setContractDays(Integer contractDays) {
		this.contractDays = contractDays;
	}

	public Date getNewPredictDate() {
		return newPredictDate;
	}

	public void setNewPredictDate(Date newPredictDate) {
		this.newPredictDate = newPredictDate;
	}

	public Date getUrgentDeliveryDate() {
		return urgentDeliveryDate;
	}

	public void setUrgentDeliveryDate(Date urgentDeliveryDate) {
		this.urgentDeliveryDate = urgentDeliveryDate;
	}

	public Integer getScheduledDays() {
		return scheduledDays;
	}

	public void setScheduledDays(Integer scheduledDays) {
		this.scheduledDays = scheduledDays;
	}

	public Integer getMoneyDateType() {
		return moneyDateType;
	}

	public void setMoneyDateType(Integer moneyDateType) {
		this.moneyDateType = moneyDateType;
	}

	public Integer getDateType() {
		return dateType;
	}

	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public Integer getStageUnUpdate() {
		return stageUnUpdate;
	}

	public void setStageUnUpdate(Integer stageUnUpdate) {
		this.stageUnUpdate = stageUnUpdate;
	}

	public Integer getDetailUnUpdate() {
		return detailUnUpdate;
	}

	public void setDetailUnUpdate(Integer detailUnUpdate) {
		this.detailUnUpdate = detailUnUpdate;
	}

	public int getPuaseTask() {
		return puaseTask;
	}

	public void setPuaseTask(int puaseTask) {
		this.puaseTask = puaseTask;
	}
    
	public Boolean getIsContract() {
		return isContract;
	}

	public void setIsContract(Boolean isContract) {
		this.isContract = isContract;
	}

	public String getDetailStr() {
		return detailStr;
	}

	public void setDetailStr(String detailStr) {
		this.detailStr = detailStr;
	}

	public List<Integer> getDelayStatusS() {
		return delayStatusS;
	}

	public void setDelayStatusS(List<Integer> delayStatusS) {
		this.delayStatusS = delayStatusS;
	}

	public Boolean getIsStart() {
		return isStart;
	}

	public void setIsStart(Boolean isStart) {
		this.isStart = isStart;
	}

	public Integer getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(Integer createPersonId) {
		this.createPersonId = createPersonId;
	}

	public int getShippingApproval() {
		return shippingApproval;
	}

	public void setShippingApproval(int shippingApproval) {
		this.shippingApproval = shippingApproval;
	}

	public String getImportantSelect() {
		return importantSelect;
	}

	public void setImportantSelect(String importantSelect) {
		this.importantSelect = importantSelect;
	}

	public Boolean getIsPurchaseWeekReport() {
		return isPurchaseWeekReport;
	}

	public void setIsPurchaseWeekReport(Boolean isPurchaseWeekReport) {
		this.isPurchaseWeekReport = isPurchaseWeekReport;
	}

	public Boolean getIsNoteProcess() {
		return isNoteProcess;
	}

	public void setIsNoteProcess(Boolean isNoteProcess) {
		this.isNoteProcess = isNoteProcess;
	}

	public Boolean getIsSample() {
		return isSample;
	}

	public void setIsSample(Boolean isSample) {
		this.isSample = isSample;
	}

	public Boolean getIsProduct() {
		return isProduct;
	}

	public void setIsProduct(Boolean isProduct) {
		this.isProduct = isProduct;
	}

	public Boolean getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(Boolean isProcess) {
		this.isProcess = isProcess;
	}

	public Date getSampleFinishTime() {
		return sampleFinishTime;
	}

	public void setSampleFinishTime(Date sampleFinishTime) {
		this.sampleFinishTime = sampleFinishTime;
	}

	public List<Integer> getDetailStatusS() {
		return detailStatusS;
	}

	public void setDetailStatusS(List<Integer> detailStatusS) {
		this.detailStatusS = detailStatusS;
	}

	public Integer getDetailStatus() {
		return detailStatus;
	}

	public void setDetailStatus(Integer detailStatus) {
		this.detailStatus = detailStatus;
	}

	public String getExpectedShipmentSelect() {
		return expectedShipmentSelect;
	}

	public void setExpectedShipmentSelect(String expectedShipmentSelect) {
		this.expectedShipmentSelect = expectedShipmentSelect;
	}
    
   

	public Integer getDelayTriggerFlag() {
		return delayTriggerFlag;
	}

	public void setDelayTriggerFlag(Integer delayTriggerFlag) {
		this.delayTriggerFlag = delayTriggerFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
    
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
		this.projectNo = projectNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public int getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public int getWarningStatus() {
		return warningStatus;
	}

	public void setWarningStatus(int warningStatus) {
		this.warningStatus = warningStatus;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public int getEmailUserId() {
		return emailUserId;
	}

	public void setEmailUserId(int emailUserId) {
		this.emailUserId = emailUserId;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Date getPoDate() {
		return poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}

	public int getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(int roleNo) {
		this.roleNo = roleNo;
	}

	public String getInputKey() {
		return inputKey;
	}

	public void setInputKey(String inputKey) {
		this.inputKey = inputKey;
	}

	public List<Delay> getDelayList() {
		return delayList;
	}

	public void setDelayList(List<Delay> delayList) {
		this.delayList = delayList;
	}

	public List<ProductionPlan> getPlanList() {
		return planList;
	}

	public void setPlanList(List<ProductionPlan> planList) {
		this.planList = planList;
	}

	public List<ProjectReport> getReportList() {
		return reportList;
	}

	public void setReportList(List<ProjectReport> reportList) {
		this.reportList = reportList;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public List<ProjectDrawing> getProjectDrawingList() {
		return projectDrawingList;
	}

	public void setProjectDrawingList(List<ProjectDrawing> projectDrawingList) {
		this.projectDrawingList = projectDrawingList;
	}

	public List<ProjectInspectionReport> getInspectionReportList() {
		return inspectionReportList;
	}

	public void setInspectionReportList(List<ProjectInspectionReport> inspectionReportList) {
		this.inspectionReportList = inspectionReportList;
	}

	

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public Date getProjectReportDate() {
		return projectReportDate;
	}

	public void setProjectReportDate(Date projectReportDate) {
		this.projectReportDate = projectReportDate;
	}

	public Date getSampleScheduledDate() {
		return sampleScheduledDate;
	}

	public void setSampleScheduledDate(Date sampleScheduledDate) {
		this.sampleScheduledDate = sampleScheduledDate;
	}

	public List<StatusEnter> getStatusEnterList() {
		return statusEnterList;
	}

	public void setStatusEnterList(List<StatusEnter> statusEnterList) {
		this.statusEnterList = statusEnterList;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIsPause() {
		return isPause;
	}

	public void setIsPause(String isPause) {
		this.isPause = isPause;
	}

	public String getPauseReason() {
		return pauseReason;
	}

	public void setPauseReason(String pauseReason) {
		this.pauseReason = pauseReason;
	}

	public String getPurchaseNameId() {
		return purchaseNameId;
	}

	public void setPurchaseNameId(String purchaseNameId) {
		this.purchaseNameId = purchaseNameId;
	}

	public String getDelayType() {
		return delayType;
	}

	public void setDelayType(String delayType) {
		this.delayType = delayType;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public List<ProjectReport> getProjectReportList() {
		return projectReportList;
	}

	public void setProjectReportList(List<ProjectReport> projectReportList) {
		this.projectReportList = projectReportList;
	}

	public RoleBindList getRoleBindList() {
		return roleBindList;
	}

	public void setRoleBindList(RoleBindList roleBindList) {
		this.roleBindList = roleBindList;
	}

	public Integer getPlantAnalysis() {
		return plantAnalysis;
	}

	public void setPlantAnalysis(Integer plantAnalysis) {
		this.plantAnalysis = plantAnalysis;
	}

	public int getProjectMaterialProperties() {
		return projectMaterialProperties;
	}

	public void setProjectMaterialProperties(int projectMaterialProperties) {
		this.projectMaterialProperties = projectMaterialProperties;
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

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public Integer getProjectStage() {
		return projectStage;
	}

	public void setProjectStage(Integer projectStage) {
		this.projectStage = projectStage;
	}

	public String getPlantAnalysisView() {
		return plantAnalysisView;
	}

	public void setPlantAnalysisView(String plantAnalysisView) {
		this.plantAnalysisView = plantAnalysisView;
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

	public String getProjectAmount() {
		return projectAmount;
	}

	public void setProjectAmount(String projectAmount) {
		this.projectAmount = projectAmount;
	}

	public String getWeekPicture() {
		return weekPicture;
	}

	public void setWeekPicture(String weekPicture) {
		this.weekPicture = weekPicture;
	}

	public String getWeekInfo() {
		return weekInfo;
	}

	public void setWeekInfo(String weekInfo) {
		this.weekInfo = weekInfo;
	}

	public List<QualityReport> getQrList() {
		return qrList;
	}

	public void setQrList(List<QualityReport> qrList) {
		this.qrList = qrList;
	}

	public int getSortField() {
		return sortField;
	}

	public void setSortField(int sortField) {
		this.sortField = sortField;
	}

	public Date getDateSample() {
		return dateSample;
	}

	public void setDateSample(Date dateSample) {
		this.dateSample = dateSample;
	}

	public Date getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(Date completionTime) {
		this.completionTime = completionTime;
	}

	public Date getDateSampleUploading() {
		return dateSampleUploading;
	}

	public void setDateSampleUploading(Date dateSampleUploading) {
		this.dateSampleUploading = dateSampleUploading;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public int getSampleFinish() {
		return sampleFinish;
	}

	public void setSampleFinish(int sampleFinish) {
		this.sampleFinish = sampleFinish;
	}

	public Delay getDelay() {
		return delay;
	}

	public void setDelay(Delay delay) {
		this.delay = delay;
	}

	public List<QualityReport> getQualityReportList() {
		return qualityReportList;
	}

	public void setQualityReportList(List<QualityReport> qualityReportList) {
		this.qualityReportList = qualityReportList;
	}

	public QualityReport getQualityReport() {
		return qualityReport;
	}

	public void setQualityReport(QualityReport qualityReport) {
		this.qualityReport = qualityReport;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getScreenType() {
		return screenType;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public List<Integer> getPlantAnalysisS() {
		return plantAnalysisS;
	}

	public void setPlantAnalysisS(List<Integer> plantAnalysisS) {
		this.plantAnalysisS = plantAnalysisS;
	}

	public List<Integer> getProjectStageS() {
		return projectStageS;
	}

	public void setProjectStageS(List<Integer> projectStageS) {
		this.projectStageS = projectStageS;
	}

	public List<Integer> getProjectStatusS() {
		return projectStatusS;
	}

	public void setProjectStatusS(List<Integer> projectStatusS) {
		this.projectStatusS = projectStatusS;
	}

	public Integer getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}

	public List<ProjectInspectionReport> getInspectionPlanList() {
		return inspectionPlanList;
	}

	public void setInspectionPlanList(List<ProjectInspectionReport> inspectionPlanList) {
		this.inspectionPlanList = inspectionPlanList;
	}

	public List<ProjectTask> getProjectTaskList() {
		return projectTaskList;
	}

	public void setProjectTaskList(List<ProjectTask> projectTaskList) {
		this.projectTaskList = projectTaskList;
	}

	public List<ProjectDrawing> getProjectDemandReportList() {
		return projectDemandReportList;
	}

	public void setProjectDemandReportList(List<ProjectDrawing> projectDemandReportList) {
		this.projectDemandReportList = projectDemandReportList;
	}

	public int getFinishTask() {
		return finishTask;
	}

	public void setFinishTask(int finishTask) {
		this.finishTask = finishTask;
	}

	public int getAllTask() {
		return allTask;
	}

	public void setAllTask(int allTask) {
		this.allTask = allTask;
	}

	public String getQualityReportSelect() {
		return qualityReportSelect;
	}

	public void setQualityReportSelect(String qualityReportSelect) {
		this.qualityReportSelect = qualityReportSelect;
	}

	public Date getMoneyDate() {
		return moneyDate;
	}

	public void setMoneyDate(Date moneyDate) {
		this.moneyDate = moneyDate;
	}

	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	public ProjectReport getProjectReport() {
		return projectReport;
	}

	public void setProjectReport(ProjectReport projectReport) {
		this.projectReport = projectReport;
	}

	public int getInspectionReservationNum() {
		return InspectionReservationNum;
	}

	public void setInspectionReservationNum(int inspectionReservationNum) {
		InspectionReservationNum = inspectionReservationNum;
	}

	@Override
	public String toString() {
		return "Project{" +
				"id='" + id + '\'' +
				", projectNo='" + projectNo + '\'' +
				", projectName='" + projectName + '\'' +
				", projectNameEn='" + projectNameEn + '\'' +
				", deliveryDate=" + deliveryDate +
				", roleName='" + roleName + '\'' +
				", trueName='" + trueName + '\'' +
				", deliveryStatus=" + deliveryStatus +
				", warningStatus=" + warningStatus +
				", importance=" + importance +
				", stage=" + stage +
				", emailUserId=" + emailUserId +
				", purchaseId=" + purchaseId +
				", saleId=" + saleId +
				", poDate=" + poDate +
				", scheduledDate=" + scheduledDate +
				", actualStartDate=" + actualStartDate +
				", createDate=" + createDate +
				", finish=" + finish +
				", isPause='" + isPause + '\'' +
				", pauseReason='" + pauseReason + '\'' +
				", inputKey='" + inputKey + '\'' +
				", delayList=" + delayList +
				", planList=" + planList +
				", reportList=" + reportList +
				", projectDrawingList=" + projectDrawingList +
				", inspectionReportList=" + inspectionReportList +
				", inspectionList=" + inspectionList +
				", scheduleList=" + scheduleList +
				", complaintList=" + complaintList +
				", erpReports=" + erpReports +
				", projectPauses=" + projectPauses +
				", factoryList=" + factoryList +
				", deliveryList=" + deliveryList +
				", milestones=" + milestones +
				", analysisIssueList=" + analysisIssueList +
				", qualityAnalysis=" + qualityAnalysis +
				", require=" + require +
				", updateInspect=" + updateInspect +
				", updateDrawing=" + updateDrawing +
				", difficultProject=" + difficultProject +
				", dateBefore=" + dateBefore +
				", importantTaskTotal=" + importantTaskTotal +
				", importantTaskFinish=" + importantTaskFinish +
				", metalDeliveryTime=" + metalDeliveryTime +
				", metalDeliveryTime1=" + metalDeliveryTime1 +
				", plasticDeliveryPeriod=" + plasticDeliveryPeriod +
				", plasticDeliveryPeriod1=" + plasticDeliveryPeriod1 +
				", dateDelivery=" + dateDelivery +
				", delayDay=" + delayDay +
				", explain='" + explain + '\'' +
				", interpretationDocument='" + interpretationDocument + '\'' +
				", factoryName='" + factoryName + '\'' +
				", incompleteInspectionTasks=" + incompleteInspectionTasks +
				", lateDeliveryDate=" + lateDeliveryDate +
				", historyInspection='" + historyInspection + '\'' +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", sampleDeliveryDate='" + sampleDeliveryDate + '\'' +
				", deliveryTime='" + deliveryTime + '\'' +
				", masterQualityInspection='" + masterQualityInspection + '\'' +
				", qualityInspector1='" + qualityInspector1 + '\'' +
				", qualityInspector2='" + qualityInspector2 + '\'' +
				", qualityInspector3='" + qualityInspector3 + '\'' +
				", qualityInspector4='" + qualityInspector4 + '\'' +
				", qualityInspector5='" + qualityInspector5 + '\'' +
				", qualityInspector6='" + qualityInspector6 + '\'' +
				", qualityInspector7='" + qualityInspector7 + '\'' +
				", qrList=" + qrList +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", taskList=" + taskList +
				", reportName='" + reportName + '\'' +
				", report='" + report + '\'' +
				", picUrl='" + picUrl + '\'' +
				", projectReportDate=" + projectReportDate +
				", commentList=" + commentList +
				", statusEnterList=" + statusEnterList +
				", sampleScheduledDate=" + sampleScheduledDate +
				", flag=" + flag +
				", companyName='" + companyName + '\'' +
				", purchaseNameId='" + purchaseNameId + '\'' +
				", delayType='" + delayType + '\'' +
				", projectReportList=" + projectReportList +
				", roleBindList=" + roleBindList +
				", createTime='" + createTime + '\'' +
				", projectType=" + projectType +
				", projectStage=" + projectStage +
				", purchaseName='" + purchaseName + '\'' +
				", sellName='" + sellName + '\'' +
				", saleName='" + saleName + '\'' +
				", qualityName='" + qualityName + '\'' +
				", userName='" + userName + '\'' +
				", roleNo=" + roleNo +
				", status='" + status + '\'' +
				", zhijian1='" + zhijian1 + '\'' +
				", zhijian2='" + zhijian2 + '\'' +
				", zhijian3='" + zhijian3 + '\'' +
				", projectAmount='" + projectAmount + '\'' +
				", weekPicture='" + weekPicture + '\'' +
				", weekInfo='" + weekInfo + '\'' +
				", sortField=" + sortField +
				", finishTime=" + finishTime +
				", sampleFinishTime=" + sampleFinishTime +
				", dateSample=" + dateSample +
				", completionTime=" + completionTime +
				", dateSampleUploading=" + dateSampleUploading +
				", productImg='" + productImg + '\'' +
				", sampleFinish=" + sampleFinish +
				", delay=" + delay +
				", qualityReportList=" + qualityReportList +
				", qualityReport=" + qualityReport +
				", operatorType='" + operatorType + '\'' +
				", screenType='" + screenType + '\'' +
				", plantAnalysisS=" + plantAnalysisS +
				", projectStageS=" + projectStageS +
				", projectStatusS=" + projectStatusS +
				", detailStatusS=" + detailStatusS +
				", delayStatusS=" + delayStatusS +
				", feedback=" + feedback +
				", projectStatus=" + projectStatus +
				", finishTask=" + finishTask +
				", allTask=" + allTask +
				", puaseTask=" + puaseTask +
				", qualityTask=" + qualityTask +
				", qualityFinishTask=" + qualityFinishTask +
				", moneyDate=" + moneyDate +
				", qualityReportSelect='" + qualityReportSelect + '\'' +
				", expectedShipmentSelect='" + expectedShipmentSelect + '\'' +
				", projectReport=" + projectReport +
				", delayTriggerFlag=" + delayTriggerFlag +
				", technician='" + technician + '\'' +
				", customerName='" + customerName + '\'' +
				", customerGrade=" + customerGrade +
				", exportDate='" + exportDate + '\'' +
				", detailStatus=" + detailStatus +
				", createPersonId=" + createPersonId +
				", scheduledDays=" + scheduledDays +
				", newPredictDate=" + newPredictDate +
				", urgentDeliveryDate=" + urgentDeliveryDate +
				", prevSampleDate=" + prevSampleDate +
				", customerAttitude='" + customerAttitude + '\'' +
				", isProcess=" + isProcess +
				", isSample=" + isSample +
				", isProduct=" + isProduct +
				", isNoteProcess=" + isNoteProcess +
				", isPurchaseWeekReport=" + isPurchaseWeekReport +
				", isContract=" + isContract +
				", factoryId='" + factoryId + '\'' +
				", isNewProject=" + isNewProject +
				", importantSelect='" + importantSelect + '\'' +
				", shippingApproval=" + shippingApproval +
				", isStart=" + isStart +
				", detailStr='" + detailStr + '\'' +
				", detailUnUpdate=" + detailUnUpdate +
				", stageUnUpdate=" + stageUnUpdate +
				", createName='" + createName + '\'' +
				", dateType=" + dateType +
				", moneyDateType=" + moneyDateType +
				", contractDays=" + contractDays +
				", delayDays=" + delayDays +
				", unFinshedTask=" + unFinshedTask +
				", technicianStr='" + technicianStr + '\'' +
				", originalDeliveryDate=" + originalDeliveryDate +
				", originalSampleScheduledDate=" + originalSampleScheduledDate +
				", sellDingTalkId='" + sellDingTalkId + '\'' +
				", purchaseDingTalkId='" + purchaseDingTalkId + '\'' +
				", complaintVolume=" + complaintVolume +
				", monthlyComplaints=" + monthlyComplaints +
				", projectsUnderWay=" + projectsUnderWay +
				", sampleContracts=" + sampleContracts +
				", allContracts=" + allContracts +
				", delayedDeliveryDate=" + delayedDeliveryDate +
				", cargoDelayedDeliveryDate=" + cargoDelayedDeliveryDate +
				", deliveryReminder=" + deliveryReminder +
				", plantAnalysis=" + plantAnalysis +
				", plantAnalysisView='" + plantAnalysisView + '\'' +
				", projectMaterialProperties=" + projectMaterialProperties +
				", technology='" + technology + '\'' +
				", inspectionPlanList=" + inspectionPlanList +
				", projectTaskList=" + projectTaskList +
				", projectDemandReportList=" + projectDemandReportList +
				", feedbackList=" + feedbackList +
				", InspectionReservationNum=" + InspectionReservationNum +
				", projectMembers='" + projectMembers + '\'' +
				", recentInspectionReport=" + recentInspectionReport +
				", sailingDate='" + sailingDate + '\'' +
				", contractNumber=" + contractNumber +
				", firstInspectionReport=" + firstInspectionReport +
				'}';
	}


}