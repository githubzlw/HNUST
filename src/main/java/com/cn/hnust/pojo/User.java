package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息对象
 * @author Administrator
 *
 */
public class User implements Serializable{
    
    /**
	 * @fieldName serialVersionUID
	 * @fieldType long
	 * @Description
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer roleNo;

    private String roleName;     //角色名称

    private String trueName;     //员工的真实姓名

    private String job;          //员工职位

    private String userName;     //用户名

    private String password;          //密码

    private String emailAddress; //邮箱地址

    private String emailPwd;     //邮箱密码
    
    private String phone;

    private Integer flag;        //是否在职（0:离职1：在职）

    private Date registerDate;   //注册日期

    private Date birthday;      //生日

    private Date leavingDate;   //离职日期

    private Integer erpRole;    //ERP角色编号(1-翻译 2-数据录入 3-老外销售 4-跟单销售 5-其他销售 6-采购 7-物流 8-质检 9-助理)

    private Integer taskRole;   //任务系统ERP角色编号(1-翻译 2-数据录入 3-老外销售 4-跟单销售 5-其他销售 6-采购 7-物流 8-质检 9-助理)

    private Integer crmRole;    //CRM角色

    private Integer quotationRole;  //内部报价角色

    private Integer nbRole;    //NBmail角色

    private Integer kuaiRole;  //快制造角色
    
    private Integer bonusRole;  //分红权限
    
    private String jobNumber;  //工号

    //操作类型
    private Integer type;   //0：插入 1：更新
    private String dingTalkId;  //钉钉id
    private String newCustomerProofingNumber;//新客户打样项目数
    private String newCustomerProofingMoney;//新客户打样金额
    private String newCustomerBulkNumber;//新客户大货单数
    private String newCustomerBulkMoney;//新客户大货单金额
    private String customerProofingNumber;//客户打样项目数
    private String customerProofingMoney;//客户打样金额
    private String customerBulkNumber;//客户大货单数
    private String customerBulkMoney;//客户大货单金额
    private String returnItemNumber;//返单项目数
    private String returnItemMoney;//返单项目金额
    private String scheduledDate;//平均完成时间
    private Integer complaintVolume;   //上个月投诉项目数
    private Integer monthlyComplaints;   //本月投诉项目数
    private Integer projectsUnderWay;   //正在进行项目数
    
    private Integer inspectionTimelinessNumber; //检验不及时次数
    private Integer complaintsNumber;          //投诉次数
    private double amount;          //投诉次数
    private String projectNo;          //投诉次数
    private String zhijian1;          //投诉次数
    private String zhijian2;          //投诉次数
    private String zhijian3;          //投诉次数
    private Integer testReport;        //质检报告
    private Integer inspectionItems;   //最近30天检验项目数量
    private double averageDelayDays;  //平均延期天数
    private String startTime;         //起始时间
    private String endTime;           //结束时间
    private double onTimeCompletionRate;           //结束时间
    
    
	public double getOnTimeCompletionRate() {
		return onTimeCompletionRate;
	}


	public void setOnTimeCompletionRate(double onTimeCompletionRate) {
		this.onTimeCompletionRate = onTimeCompletionRate;
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


	public Integer getInspectionItems() {
		return inspectionItems;
	}


	public void setInspectionItems(Integer inspectionItems) {
		this.inspectionItems = inspectionItems;
	}


	public double getAverageDelayDays() {
		return averageDelayDays;
	}


	public void setAverageDelayDays(double averageDelayDays) {
		this.averageDelayDays = averageDelayDays;
	}


	public Integer getTestReport() {
		return testReport;
	}


	public void setTestReport(Integer testReport) {
		this.testReport = testReport;
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


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getProjectNo() {
		return projectNo;
	}


	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
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


	public String getScheduledDate() {
		return scheduledDate;
	}


	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}


	public String getNewCustomerProofingNumber() {
		return newCustomerProofingNumber;
	}


	public void setNewCustomerProofingNumber(String newCustomerProofingNumber) {
		this.newCustomerProofingNumber = newCustomerProofingNumber;
	}


	public String getNewCustomerProofingMoney() {
		return newCustomerProofingMoney;
	}


	public void setNewCustomerProofingMoney(String newCustomerProofingMoney) {
		this.newCustomerProofingMoney = newCustomerProofingMoney;
	}


	public String getNewCustomerBulkNumber() {
		return newCustomerBulkNumber;
	}


	public void setNewCustomerBulkNumber(String newCustomerBulkNumber) {
		this.newCustomerBulkNumber = newCustomerBulkNumber;
	}


	public String getNewCustomerBulkMoney() {
		return newCustomerBulkMoney;
	}


	public void setNewCustomerBulkMoney(String newCustomerBulkMoney) {
		this.newCustomerBulkMoney = newCustomerBulkMoney;
	}


	public String getCustomerProofingNumber() {
		return customerProofingNumber;
	}


	public void setCustomerProofingNumber(String customerProofingNumber) {
		this.customerProofingNumber = customerProofingNumber;
	}


	public String getCustomerProofingMoney() {
		return customerProofingMoney;
	}


	public void setCustomerProofingMoney(String customerProofingMoney) {
		this.customerProofingMoney = customerProofingMoney;
	}


	public String getCustomerBulkNumber() {
		return customerBulkNumber;
	}


	public void setCustomerBulkNumber(String customerBulkNumber) {
		this.customerBulkNumber = customerBulkNumber;
	}


	public String getCustomerBulkMoney() {
		return customerBulkMoney;
	}


	public void setCustomerBulkMoney(String customerBulkMoney) {
		this.customerBulkMoney = customerBulkMoney;
	}


	public String getReturnItemNumber() {
		return returnItemNumber;
	}


	public void setReturnItemNumber(String returnItemNumber) {
		this.returnItemNumber = returnItemNumber;
	}


	public String getReturnItemMoney() {
		return returnItemMoney;
	}


	public void setReturnItemMoney(String returnItemMoney) {
		this.returnItemMoney = returnItemMoney;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", roleNo=" + roleNo + ", roleName="
				+ roleName + ", trueName=" + trueName + ", job=" + job
				+ ", userName=" + userName + ", password=" + password
				+ ", emailAddress=" + emailAddress + ", emailPwd=" + emailPwd
				+ ", phone=" + phone + ", flag=" + flag + ", registerDate="
				+ registerDate + ", birthday=" + birthday + ", leavingDate="
				+ leavingDate + ", erpRole=" + erpRole + ", taskRole="
				+ taskRole + ", crmRole=" + crmRole + ", quotationRole="
				+ quotationRole + ", nbRole=" + nbRole + ", kuaiRole="
				+ kuaiRole + ", bonusRole=" + bonusRole + ", jobNumber="
				+ jobNumber + ", type=" + type + ", dingTalkId=" + dingTalkId
				+ ", newCustomerProofingNumber=" + newCustomerProofingNumber
				+ ", newCustomerProofingMoney=" + newCustomerProofingMoney
				+ ", newCustomerBulkNumber=" + newCustomerBulkNumber
				+ ", newCustomerBulkMoney=" + newCustomerBulkMoney
				+ ", customerProofingNumber=" + customerProofingNumber
				+ ", customerProofingMoney=" + customerProofingMoney
				+ ", customerBulkNumber=" + customerBulkNumber
				+ ", customerBulkMoney=" + customerBulkMoney
				+ ", returnItemNumber=" + returnItemNumber
				+ ", returnItemMoney=" + returnItemMoney + ", scheduledDate="
				+ scheduledDate + ", complaintVolume=" + complaintVolume
				+ ", monthlyComplaints=" + monthlyComplaints
				+ ", projectsUnderWay=" + projectsUnderWay
				+ ", inspectionTimelinessNumber=" + inspectionTimelinessNumber
				+ ", complaintsNumber=" + complaintsNumber + ", amount="
				+ amount + ", projectNo=" + projectNo + ", zhijian1="
				+ zhijian1 + ", zhijian2=" + zhijian2 + ", zhijian3="
				+ zhijian3 + ", testReport=" + testReport
				+ ", inspectionItems=" + inspectionItems
				+ ", averageDelayDays=" + averageDelayDays + ", startTime="
				+ startTime + ", endTime=" + endTime
				+ ", onTimeCompletionRate=" + onTimeCompletionRate + "]";
	}

	
	public String getDingTalkId() {
		return dingTalkId;
	}


	public void setDingTalkId(String dingTalkId) {
		this.dingTalkId = dingTalkId;
	}


	public Integer getBonusRole() {
		return bonusRole;
	}


	public void setBonusRole(Integer bonusRole) {
		this.bonusRole = bonusRole;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(Integer roleNo) {
		this.roleNo = roleNo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailPwd() {
		return emailPwd;
	}

	public void setEmailPwd(String emailPwd) {
		this.emailPwd = emailPwd;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}

	public Integer getErpRole() {
		return erpRole;
	}

	public void setErpRole(Integer erpRole) {
		this.erpRole = erpRole;
	}

	public Integer getTaskRole() {
		return taskRole;
	}

	public void setTaskRole(Integer taskRole) {
		this.taskRole = taskRole;
	}

	public Integer getCrmRole() {
		return crmRole;
	}

	public void setCrmRole(Integer crmRole) {
		this.crmRole = crmRole;
	}

	public Integer getQuotationRole() {
		return quotationRole;
	}

	public void setQuotationRole(Integer quotationRole) {
		this.quotationRole = quotationRole;
	}

	public Integer getNbRole() {
		return nbRole;
	}

	public void setNbRole(Integer nbRole) {
		this.nbRole = nbRole;
	}

	public Integer getKuaiRole() {
		return kuaiRole;
	}

	public void setKuaiRole(Integer kuaiRole) {
		this.kuaiRole = kuaiRole;
	}


	public String getJobNumber() {
		return jobNumber;
	}


	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
    
    

    
}