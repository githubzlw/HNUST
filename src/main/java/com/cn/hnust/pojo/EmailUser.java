package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class EmailUser implements Serializable {
    private Integer id;

    private Integer roleNo;

    private String roleName;

    private String trueName;

    private String job;

    private String userName;

    private String pwd;

    private String emailAddress;

    private String emailPwd;

    private Integer flag;

    private Date registDate;

    private Integer dimission;

    private String loginIp;

    private Integer wrongNumber;

    private Long wrongTime;

    private String wrongIp;

    private Date birthday;

    private String workTime;

    private String englishlvl;

    private String phono;

    private String liveAddress;

    private Integer workMonth;

    private String skapeEmail;

    private String msn;

    private String companyPhono;

    private String mobile;

    private String loginIp1;

    private String loginIp2;

    private String loginIp3;

    private String loginIp4;

    private String loginIp5;

    private String loginIp6;

    private String loginIp7;

    private String loginIp8;

    private String loginIp9;

    private String loginIp10;

    private String loginIp11;

    private String loginIp12;

    private String loginIp13;

    private String loginIp14;

    private String loginIp15;

    private String loginIp16;

    private String loginIp17;

    private String loginIp18;

    private String loginIp19;

    private String loginIp20;

    private String loginIp21;

    private String loginIp22;

    private String loginIp23;

    private String loginIp24;

    private String loginIp25;

    private String loginIp26;

    private String loginIp27;

    private String loginIp28;

    private String loginIp29;

    private String loginIp30;

    private String emailAddress1;

    private String userName1;

    private Integer customers;

    private Integer emails;

    private Integer thread;

    private Integer oricollnum;

    private Integer succollnum;

    private Integer orihairnum;

    private Integer numofsucc;

    private String holidayNotice;
    
    private PersonData personData;
    private String scheduledDate;//平均完成时间
    private String item;//项目数
    private static final long serialVersionUID = 1L;
    private Date startDate; //开始时间
    private Date endDate; //结束时间
    private String thisMonth; //本月
    private String lastMonth; //上月
    private String lastMonthtwo; //上上月
    private Integer complaintVolume;   //上个月投诉项目数
    private Integer monthlyComplaints;   //本月投诉项目数
    private Integer lastMonthTwoComplaints;   //上上月投诉项目数
    private Integer projectsUnderWay;   //正在进行项目数
    private Integer completedLastMonth;   //上月完成项目数
    private Integer completedAboveMonth;   //上上月完成项目数
    private Double allComplaints;   //全部投诉数
    private Integer sampleContracts;   //样品无大货项目
    private Double allContracts;   //全部项目数
    private Double successRate;   //成功率
    private String allProject;   //成功率
    private Integer testReport;          //质检报告
    
    private String contractAmount;   //合同金额
    private int proofingProject;   //打样项目数
    private int massProductionProject;   //量产项目数
    private Double lastWorkHour;   //上月工时
    private Double workingHours;   //本月工时
    private int reportNumber;   //上月
    private int monthReportNumber;   //本月
    
    
    public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount2) {
		this.contractAmount = contractAmount2;
	}

	public int getProofingProject() {
		return proofingProject;
	}

	public void setProofingProject(int proofingProject) {
		this.proofingProject = proofingProject;
	}

	public int getMassProductionProject() {
		return massProductionProject;
	}

	public void setMassProductionProject(int massProductionProject) {
		this.massProductionProject = massProductionProject;
	}

	public Double getLastWorkHour() {
		return lastWorkHour;
	}

	public void setLastWorkHour(Double lastWorkHour) {
		this.lastWorkHour = lastWorkHour;
	}

	public Double getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(Double workingHours) {
		this.workingHours = workingHours;
	}

	public int getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(int reportNumber) {
		this.reportNumber = reportNumber;
	}

	public int getMonthReportNumber() {
		return monthReportNumber;
	}

	public void setMonthReportNumber(int monthReportNumber) {
		this.monthReportNumber = monthReportNumber;
	}

	public Integer getTestReport() {
		return testReport;
	}

	public void setTestReport(Integer testReport) {
		this.testReport = testReport;
	}
    public String getAllProject() {
		return allProject;
	}


	public void setAllProject(String allProject) {
		this.allProject = allProject;
	}


	public Double getSuccessRate() {
		return successRate;
	}


	public void setSuccessRate(Double successRate) {
		this.successRate = successRate;
	}


	public Integer getSampleContracts() {
		return sampleContracts;
	}


	public void setSampleContracts(Integer sampleContracts) {
		this.sampleContracts = sampleContracts;
	}


	public Double getAllContracts() {
		return allContracts;
	}


	public void setAllContracts(Double allContracts) {
		this.allContracts = allContracts;
	}
    public Double getAllComplaints() {
		return allComplaints;
	}

	public void setAllComplaints(Double allComplaints) {
		this.allComplaints = allComplaints;
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

	public Integer getLastMonthTwoComplaints() {
		return lastMonthTwoComplaints;
	}

	public void setLastMonthTwoComplaints(Integer lastMonthTwoComplaints) {
		this.lastMonthTwoComplaints = lastMonthTwoComplaints;
	}

	public Integer getProjectsUnderWay() {
		return projectsUnderWay;
	}

	public void setProjectsUnderWay(Integer projectsUnderWay) {
		this.projectsUnderWay = projectsUnderWay;
	}

	public Integer getCompletedLastMonth() {
		return completedLastMonth;
	}

	public void setCompletedLastMonth(Integer completedLastMonth) {
		this.completedLastMonth = completedLastMonth;
	}

	public Integer getCompletedAboveMonth() {
		return completedAboveMonth;
	}

	public void setCompletedAboveMonth(Integer completedAboveMonth) {
		this.completedAboveMonth = completedAboveMonth;
	}

	public String getThisMonth() {
		return thisMonth;
	}

	public void setThisMonth(String thisMonth) {
		this.thisMonth = thisMonth;
	}

	public String getLastMonth() {
		return lastMonth;
	}

	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}

	public String getLastMonthtwo() {
		return lastMonthtwo;
	}

	public void setLastMonthtwo(String lastMonthtwo) {
		this.lastMonthtwo = lastMonthtwo;
	}

	public String getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "EmailUser [id=" + id + ", roleNo=" + roleNo + ", roleName="
				+ roleName + ", trueName=" + trueName + ", job=" + job
				+ ", userName=" + userName + ", pwd=" + pwd + ", emailAddress="
				+ emailAddress + ", emailPwd=" + emailPwd + ", flag=" + flag
				+ ", registDate=" + registDate + ", dimission=" + dimission
				+ ", loginIp=" + loginIp + ", wrongNumber=" + wrongNumber
				+ ", wrongTime=" + wrongTime + ", wrongIp=" + wrongIp
				+ ", birthday=" + birthday + ", workTime=" + workTime
				+ ", englishlvl=" + englishlvl + ", phono=" + phono
				+ ", liveAddress=" + liveAddress + ", workMonth=" + workMonth
				+ ", skapeEmail=" + skapeEmail + ", msn=" + msn
				+ ", companyPhono=" + companyPhono + ", mobile=" + mobile
				+ ", loginIp1=" + loginIp1 + ", loginIp2=" + loginIp2
				+ ", loginIp3=" + loginIp3 + ", loginIp4=" + loginIp4
				+ ", loginIp5=" + loginIp5 + ", loginIp6=" + loginIp6
				+ ", loginIp7=" + loginIp7 + ", loginIp8=" + loginIp8
				+ ", loginIp9=" + loginIp9 + ", loginIp10=" + loginIp10
				+ ", loginIp11=" + loginIp11 + ", loginIp12=" + loginIp12
				+ ", loginIp13=" + loginIp13 + ", loginIp14=" + loginIp14
				+ ", loginIp15=" + loginIp15 + ", loginIp16=" + loginIp16
				+ ", loginIp17=" + loginIp17 + ", loginIp18=" + loginIp18
				+ ", loginIp19=" + loginIp19 + ", loginIp20=" + loginIp20
				+ ", loginIp21=" + loginIp21 + ", loginIp22=" + loginIp22
				+ ", loginIp23=" + loginIp23 + ", loginIp24=" + loginIp24
				+ ", loginIp25=" + loginIp25 + ", loginIp26=" + loginIp26
				+ ", loginIp27=" + loginIp27 + ", loginIp28=" + loginIp28
				+ ", loginIp29=" + loginIp29 + ", loginIp30=" + loginIp30
				+ ", emailAddress1=" + emailAddress1 + ", userName1="
				+ userName1 + ", customers=" + customers + ", emails=" + emails
				+ ", thread=" + thread + ", oricollnum=" + oricollnum
				+ ", succollnum=" + succollnum + ", orihairnum=" + orihairnum
				+ ", numofsucc=" + numofsucc + ", holidayNotice="
				+ holidayNotice + ", personData=" + personData
				+ ", scheduledDate=" + scheduledDate + ", item=" + item
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", thisMonth=" + thisMonth + ", lastMonth=" + lastMonth
				+ ", lastMonthtwo=" + lastMonthtwo + ", complaintVolume="
				+ complaintVolume + ", monthlyComplaints=" + monthlyComplaints
				+ ", lastMonthTwoComplaints=" + lastMonthTwoComplaints
				+ ", projectsUnderWay=" + projectsUnderWay
				+ ", completedLastMonth=" + completedLastMonth
				+ ", completedAboveMonth=" + completedAboveMonth
				+ ", allComplaints=" + allComplaints + ", sampleContracts="
				+ sampleContracts + ", allContracts=" + allContracts
				+ ", successRate=" + successRate + ", allProject=" + allProject
				+ ", testReport=" + testReport + ", contractAmount="
				+ contractAmount + ", proofingProject=" + proofingProject
				+ ", massProductionProject=" + massProductionProject
				+ ", lastWorkHour=" + lastWorkHour + ", workingHours="
				+ workingHours + ", reportNumber=" + reportNumber
				+ ", monthReportNumber=" + monthReportNumber + "]";
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
	public PersonData getPersonData() {
		return personData;
	}

	public void setPersonData(PersonData personData) {
		this.personData = personData;
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
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
    }

    public String getEmailPwd() {
        return emailPwd;
    }

    public void setEmailPwd(String emailPwd) {
        this.emailPwd = emailPwd == null ? null : emailPwd.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public Integer getDimission() {
        return dimission;
    }

    public void setDimission(Integer dimission) {
        this.dimission = dimission;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Integer getWrongNumber() {
        return wrongNumber;
    }

    public void setWrongNumber(Integer wrongNumber) {
        this.wrongNumber = wrongNumber;
    }

    public Long getWrongTime() {
        return wrongTime;
    }

    public void setWrongTime(Long wrongTime) {
        this.wrongTime = wrongTime;
    }

    public String getWrongIp() {
        return wrongIp;
    }

    public void setWrongIp(String wrongIp) {
        this.wrongIp = wrongIp == null ? null : wrongIp.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime == null ? null : workTime.trim();
    }

    public String getEnglishlvl() {
        return englishlvl;
    }

    public void setEnglishlvl(String englishlvl) {
        this.englishlvl = englishlvl == null ? null : englishlvl.trim();
    }

    public String getPhono() {
        return phono;
    }

    public void setPhono(String phono) {
        this.phono = phono == null ? null : phono.trim();
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress == null ? null : liveAddress.trim();
    }

    public Integer getWorkMonth() {
        return workMonth;
    }

    public void setWorkMonth(Integer workMonth) {
        this.workMonth = workMonth;
    }

    public String getSkapeEmail() {
        return skapeEmail;
    }

    public void setSkapeEmail(String skapeEmail) {
        this.skapeEmail = skapeEmail == null ? null : skapeEmail.trim();
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn == null ? null : msn.trim();
    }

    public String getCompanyPhono() {
        return companyPhono;
    }

    public void setCompanyPhono(String companyPhono) {
        this.companyPhono = companyPhono == null ? null : companyPhono.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getLoginIp1() {
        return loginIp1;
    }

    public void setLoginIp1(String loginIp1) {
        this.loginIp1 = loginIp1 == null ? null : loginIp1.trim();
    }

    public String getLoginIp2() {
        return loginIp2;
    }

    public void setLoginIp2(String loginIp2) {
        this.loginIp2 = loginIp2 == null ? null : loginIp2.trim();
    }

    public String getLoginIp3() {
        return loginIp3;
    }

    public void setLoginIp3(String loginIp3) {
        this.loginIp3 = loginIp3 == null ? null : loginIp3.trim();
    }

    public String getLoginIp4() {
        return loginIp4;
    }

    public void setLoginIp4(String loginIp4) {
        this.loginIp4 = loginIp4 == null ? null : loginIp4.trim();
    }

    public String getLoginIp5() {
        return loginIp5;
    }

    public void setLoginIp5(String loginIp5) {
        this.loginIp5 = loginIp5 == null ? null : loginIp5.trim();
    }

    public String getLoginIp6() {
        return loginIp6;
    }

    public void setLoginIp6(String loginIp6) {
        this.loginIp6 = loginIp6 == null ? null : loginIp6.trim();
    }

    public String getLoginIp7() {
        return loginIp7;
    }

    public void setLoginIp7(String loginIp7) {
        this.loginIp7 = loginIp7 == null ? null : loginIp7.trim();
    }

    public String getLoginIp8() {
        return loginIp8;
    }

    public void setLoginIp8(String loginIp8) {
        this.loginIp8 = loginIp8 == null ? null : loginIp8.trim();
    }

    public String getLoginIp9() {
        return loginIp9;
    }

    public void setLoginIp9(String loginIp9) {
        this.loginIp9 = loginIp9 == null ? null : loginIp9.trim();
    }

    public String getLoginIp10() {
        return loginIp10;
    }

    public void setLoginIp10(String loginIp10) {
        this.loginIp10 = loginIp10 == null ? null : loginIp10.trim();
    }

    public String getLoginIp11() {
        return loginIp11;
    }

    public void setLoginIp11(String loginIp11) {
        this.loginIp11 = loginIp11 == null ? null : loginIp11.trim();
    }

    public String getLoginIp12() {
        return loginIp12;
    }

    public void setLoginIp12(String loginIp12) {
        this.loginIp12 = loginIp12 == null ? null : loginIp12.trim();
    }

    public String getLoginIp13() {
        return loginIp13;
    }

    public void setLoginIp13(String loginIp13) {
        this.loginIp13 = loginIp13 == null ? null : loginIp13.trim();
    }

    public String getLoginIp14() {
        return loginIp14;
    }

    public void setLoginIp14(String loginIp14) {
        this.loginIp14 = loginIp14 == null ? null : loginIp14.trim();
    }

    public String getLoginIp15() {
        return loginIp15;
    }

    public void setLoginIp15(String loginIp15) {
        this.loginIp15 = loginIp15 == null ? null : loginIp15.trim();
    }

    public String getLoginIp16() {
        return loginIp16;
    }

    public void setLoginIp16(String loginIp16) {
        this.loginIp16 = loginIp16 == null ? null : loginIp16.trim();
    }

    public String getLoginIp17() {
        return loginIp17;
    }

    public void setLoginIp17(String loginIp17) {
        this.loginIp17 = loginIp17 == null ? null : loginIp17.trim();
    }

    public String getLoginIp18() {
        return loginIp18;
    }

    public void setLoginIp18(String loginIp18) {
        this.loginIp18 = loginIp18 == null ? null : loginIp18.trim();
    }

    public String getLoginIp19() {
        return loginIp19;
    }

    public void setLoginIp19(String loginIp19) {
        this.loginIp19 = loginIp19 == null ? null : loginIp19.trim();
    }

    public String getLoginIp20() {
        return loginIp20;
    }

    public void setLoginIp20(String loginIp20) {
        this.loginIp20 = loginIp20 == null ? null : loginIp20.trim();
    }

    public String getLoginIp21() {
        return loginIp21;
    }

    public void setLoginIp21(String loginIp21) {
        this.loginIp21 = loginIp21 == null ? null : loginIp21.trim();
    }

    public String getLoginIp22() {
        return loginIp22;
    }

    public void setLoginIp22(String loginIp22) {
        this.loginIp22 = loginIp22 == null ? null : loginIp22.trim();
    }

    public String getLoginIp23() {
        return loginIp23;
    }

    public void setLoginIp23(String loginIp23) {
        this.loginIp23 = loginIp23 == null ? null : loginIp23.trim();
    }

    public String getLoginIp24() {
        return loginIp24;
    }

    public void setLoginIp24(String loginIp24) {
        this.loginIp24 = loginIp24 == null ? null : loginIp24.trim();
    }

    public String getLoginIp25() {
        return loginIp25;
    }

    public void setLoginIp25(String loginIp25) {
        this.loginIp25 = loginIp25 == null ? null : loginIp25.trim();
    }

    public String getLoginIp26() {
        return loginIp26;
    }

    public void setLoginIp26(String loginIp26) {
        this.loginIp26 = loginIp26 == null ? null : loginIp26.trim();
    }

    public String getLoginIp27() {
        return loginIp27;
    }

    public void setLoginIp27(String loginIp27) {
        this.loginIp27 = loginIp27 == null ? null : loginIp27.trim();
    }

    public String getLoginIp28() {
        return loginIp28;
    }

    public void setLoginIp28(String loginIp28) {
        this.loginIp28 = loginIp28 == null ? null : loginIp28.trim();
    }

    public String getLoginIp29() {
        return loginIp29;
    }

    public void setLoginIp29(String loginIp29) {
        this.loginIp29 = loginIp29 == null ? null : loginIp29.trim();
    }

    public String getLoginIp30() {
        return loginIp30;
    }

    public void setLoginIp30(String loginIp30) {
        this.loginIp30 = loginIp30 == null ? null : loginIp30.trim();
    }

    public String getEmailAddress1() {
        return emailAddress1;
    }

    public void setEmailAddress1(String emailAddress1) {
        this.emailAddress1 = emailAddress1 == null ? null : emailAddress1.trim();
    }

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1 == null ? null : userName1.trim();
    }

    public Integer getCustomers() {
        return customers;
    }

    public void setCustomers(Integer customers) {
        this.customers = customers;
    }

    public Integer getEmails() {
        return emails;
    }

    public void setEmails(Integer emails) {
        this.emails = emails;
    }

    public Integer getThread() {
        return thread;
    }

    public void setThread(Integer thread) {
        this.thread = thread;
    }

    public Integer getOricollnum() {
        return oricollnum;
    }

    public void setOricollnum(Integer oricollnum) {
        this.oricollnum = oricollnum;
    }

    public Integer getSuccollnum() {
        return succollnum;
    }

    public void setSuccollnum(Integer succollnum) {
        this.succollnum = succollnum;
    }

    public Integer getOrihairnum() {
        return orihairnum;
    }

    public void setOrihairnum(Integer orihairnum) {
        this.orihairnum = orihairnum;
    }

    public Integer getNumofsucc() {
        return numofsucc;
    }

    public void setNumofsucc(Integer numofsucc) {
        this.numofsucc = numofsucc;
    }

    public String getHolidayNotice() {
        return holidayNotice;
    }

    public void setHolidayNotice(String holidayNotice) {
        this.holidayNotice = holidayNotice == null ? null : holidayNotice.trim();
    }
}