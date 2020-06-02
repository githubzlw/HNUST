package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class EmailCustomer extends PageHelper implements Serializable {
    private Integer cid;

    private String firstName;

    private String firstJob;

    private String gsFullName;

    private String otherName1;

    private String otherName2;

    private String mostRecognize;

    private String recognize;

    private String email1;

    private String email2;

    private String email3;

    private String email4;

    private String email5;

    private String address;

    private String country;

    private String state;

    private String city;

    private String postCode;

    private String siteUrl;

    private String tel;

    private String fax;

    private String info;

    private String ybtitle;

    private String qbtitle;

    private String recoder;

    private Date recodDate;

    private Integer saleId;

    private Date saleDate;

    private Integer isTranslate;

    private Integer translaterId;

    private Date translaterDate;

    private Integer isAudit;

    private Date auditDate;

    private Date transDate;

    private Date dateLine;

    private Integer auditFlag;

    private String saleName;

    private String transName;

    private String saleName1;

    private Integer projectstatus;

    private Integer saleId1;

    private Integer getProject;

    private Integer transmit;

    private Integer createProject;

    private Date sendDate;

    private Date sendDate1;

    private Integer blacklist;

    private Integer time;

    private String title;

    private String projectDescc;

    private String projectDesce;

    private Integer flowState;

    private String cidName;

    private Integer confirm;

    private Integer ccid;

    private String ccname;

    private String status;

    private String customerGrade;

    private Integer download;

    private Integer type;

    private String originalMail;

    private String companypicture;

    private Integer logo;

    private Integer mailstatus;

    private String proposer;

    private Integer newOld;

    private Integer oldCid;

    private String tel1;

    private String tel2;

    private String tel3;

    private String address1;

    private String address2;

    private Integer priority;

    private Integer customerType;

    private Integer pullcustomers;

    private Integer newandoldcustomers;

    private Integer customerStatus;

    private Integer twoDaysFutureMail;

    private Integer incomingMail;

    private String companNature;

    private Integer officeInChina;

    private String annualsales;

    private String companyNumber;

    private String mainProducts;

    private Integer companyDefinition;

    private Integer customerAttributes;

    private Date approvalTime;

    private Date abandonCustomerTime;

    private Integer examineApprove;

    private String examineApproveTime;

    private Integer blacklist1;
    
    private String ybcontent;

    private String qbcontent;

    private String content;

    private String companyprofile;

    private String getContent;
    private String projectId;
    private String emailfrom;
    
    private Integer publicCustomerPool;
    private Integer start;
    private Integer end;
    private Integer projectStatus;
    private Integer day;
    private String createTime;
    private Integer outlookCustomers;
    
    public Integer getOutlookCustomers() {
		return outlookCustomers;
	}

	public void setOutlookCustomers(Integer outlookCustomers) {
		this.outlookCustomers = outlookCustomers;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	private static final long serialVersionUID = 1L;
    
    public String getEmailfrom() {
		return emailfrom;
	}

	public void setEmailfrom(String emailfrom) {
		this.emailfrom = emailfrom;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Integer getPublicCustomerPool() {
		return publicCustomerPool;
	}

	public void setPublicCustomerPool(Integer publicCustomerPool) {
		this.publicCustomerPool = publicCustomerPool;
	}

	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getFirstJob() {
        return firstJob;
    }

    public void setFirstJob(String firstJob) {
        this.firstJob = firstJob == null ? null : firstJob.trim();
    }

    public String getGsFullName() {
        return gsFullName;
    }

    public void setGsFullName(String gsFullName) {
        this.gsFullName = gsFullName == null ? null : gsFullName.trim();
    }

    public String getOtherName1() {
        return otherName1;
    }

    public void setOtherName1(String otherName1) {
        this.otherName1 = otherName1 == null ? null : otherName1.trim();
    }

    public String getOtherName2() {
        return otherName2;
    }

    public void setOtherName2(String otherName2) {
        this.otherName2 = otherName2 == null ? null : otherName2.trim();
    }

    public String getMostRecognize() {
        return mostRecognize;
    }

    public void setMostRecognize(String mostRecognize) {
        this.mostRecognize = mostRecognize == null ? null : mostRecognize.trim();
    }

    public String getRecognize() {
        return recognize;
    }

    public void setRecognize(String recognize) {
        this.recognize = recognize == null ? null : recognize.trim();
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1 == null ? null : email1.trim();
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2 == null ? null : email2.trim();
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3 == null ? null : email3.trim();
    }

    public String getEmail4() {
        return email4;
    }

    public void setEmail4(String email4) {
        this.email4 = email4 == null ? null : email4.trim();
    }

    public String getEmail5() {
        return email5;
    }

    public void setEmail5(String email5) {
        this.email5 = email5 == null ? null : email5.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getYbtitle() {
        return ybtitle;
    }

    public void setYbtitle(String ybtitle) {
        this.ybtitle = ybtitle == null ? null : ybtitle.trim();
    }

    public String getQbtitle() {
        return qbtitle;
    }

    public void setQbtitle(String qbtitle) {
        this.qbtitle = qbtitle == null ? null : qbtitle.trim();
    }

    public String getRecoder() {
        return recoder;
    }

    public void setRecoder(String recoder) {
        this.recoder = recoder == null ? null : recoder.trim();
    }

    public Date getRecodDate() {
        return recodDate;
    }

    public void setRecodDate(Date recodDate) {
        this.recodDate = recodDate;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getIsTranslate() {
        return isTranslate;
    }

    public void setIsTranslate(Integer isTranslate) {
        this.isTranslate = isTranslate;
    }

    public Integer getTranslaterId() {
        return translaterId;
    }

    public void setTranslaterId(Integer translaterId) {
        this.translaterId = translaterId;
    }

    public Date getTranslaterDate() {
        return translaterDate;
    }

    public void setTranslaterDate(Date translaterDate) {
        this.translaterDate = translaterDate;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Date getDateLine() {
        return dateLine;
    }

    public void setDateLine(Date dateLine) {
        this.dateLine = dateLine;
    }

    public Integer getAuditFlag() {
        return auditFlag;
    }

    public void setAuditFlag(Integer auditFlag) {
        this.auditFlag = auditFlag;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName == null ? null : saleName.trim();
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName == null ? null : transName.trim();
    }

    public String getSaleName1() {
        return saleName1;
    }

    public void setSaleName1(String saleName1) {
        this.saleName1 = saleName1 == null ? null : saleName1.trim();
    }

    public Integer getProjectstatus() {
        return projectstatus;
    }

    public void setProjectstatus(Integer projectstatus) {
        this.projectstatus = projectstatus;
    }

    public Integer getSaleId1() {
        return saleId1;
    }

    public void setSaleId1(Integer saleId1) {
        this.saleId1 = saleId1;
    }

    public Integer getGetProject() {
        return getProject;
    }

    public void setGetProject(Integer getProject) {
        this.getProject = getProject;
    }

    public Integer getTransmit() {
        return transmit;
    }

    public void setTransmit(Integer transmit) {
        this.transmit = transmit;
    }

    public Integer getCreateProject() {
        return createProject;
    }

    public void setCreateProject(Integer createProject) {
        this.createProject = createProject;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getSendDate1() {
        return sendDate1;
    }

    public void setSendDate1(Date sendDate1) {
        this.sendDate1 = sendDate1;
    }

    public Integer getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Integer blacklist) {
        this.blacklist = blacklist;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getProjectDescc() {
        return projectDescc;
    }

    public void setProjectDescc(String projectDescc) {
        this.projectDescc = projectDescc == null ? null : projectDescc.trim();
    }

    public String getProjectDesce() {
        return projectDesce;
    }

    public void setProjectDesce(String projectDesce) {
        this.projectDesce = projectDesce == null ? null : projectDesce.trim();
    }

    public Integer getFlowState() {
        return flowState;
    }

    public void setFlowState(Integer flowState) {
        this.flowState = flowState;
    }

    public String getCidName() {
        return cidName;
    }

    public void setCidName(String cidName) {
        this.cidName = cidName == null ? null : cidName.trim();
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Integer getCcid() {
        return ccid;
    }

    public void setCcid(Integer ccid) {
        this.ccid = ccid;
    }

    public String getCcname() {
        return ccname;
    }

    public void setCcname(String ccname) {
        this.ccname = ccname == null ? null : ccname.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCustomerGrade() {
        return customerGrade;
    }

    public void setCustomerGrade(String customerGrade) {
        this.customerGrade = customerGrade == null ? null : customerGrade.trim();
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOriginalMail() {
        return originalMail;
    }

    public void setOriginalMail(String originalMail) {
        this.originalMail = originalMail == null ? null : originalMail.trim();
    }

    public String getCompanypicture() {
        return companypicture;
    }

    public void setCompanypicture(String companypicture) {
        this.companypicture = companypicture == null ? null : companypicture.trim();
    }

    public Integer getLogo() {
        return logo;
    }

    public void setLogo(Integer logo) {
        this.logo = logo;
    }

    public Integer getMailstatus() {
        return mailstatus;
    }

    public void setMailstatus(Integer mailstatus) {
        this.mailstatus = mailstatus;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer == null ? null : proposer.trim();
    }

    public Integer getNewOld() {
        return newOld;
    }

    public void setNewOld(Integer newOld) {
        this.newOld = newOld;
    }

    public Integer getOldCid() {
        return oldCid;
    }

    public void setOldCid(Integer oldCid) {
        this.oldCid = oldCid;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1 == null ? null : tel1.trim();
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2 == null ? null : tel2.trim();
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3 == null ? null : tel3.trim();
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1 == null ? null : address1.trim();
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2 == null ? null : address2.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Integer getPullcustomers() {
        return pullcustomers;
    }

    public void setPullcustomers(Integer pullcustomers) {
        this.pullcustomers = pullcustomers;
    }

    public Integer getNewandoldcustomers() {
        return newandoldcustomers;
    }

    public void setNewandoldcustomers(Integer newandoldcustomers) {
        this.newandoldcustomers = newandoldcustomers;
    }

    public Integer getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Integer customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Integer getTwoDaysFutureMail() {
        return twoDaysFutureMail;
    }

    public void setTwoDaysFutureMail(Integer twoDaysFutureMail) {
        this.twoDaysFutureMail = twoDaysFutureMail;
    }

    public Integer getIncomingMail() {
        return incomingMail;
    }

    public void setIncomingMail(Integer incomingMail) {
        this.incomingMail = incomingMail;
    }

    public String getCompanNature() {
        return companNature;
    }

    public void setCompanNature(String companNature) {
        this.companNature = companNature == null ? null : companNature.trim();
    }

    public Integer getOfficeInChina() {
        return officeInChina;
    }

    public void setOfficeInChina(Integer officeInChina) {
        this.officeInChina = officeInChina;
    }

    public String getAnnualsales() {
        return annualsales;
    }

    public void setAnnualsales(String annualsales) {
        this.annualsales = annualsales == null ? null : annualsales.trim();
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber == null ? null : companyNumber.trim();
    }

    public String getMainProducts() {
        return mainProducts;
    }

    public void setMainProducts(String mainProducts) {
        this.mainProducts = mainProducts == null ? null : mainProducts.trim();
    }

    public Integer getCompanyDefinition() {
        return companyDefinition;
    }

    public void setCompanyDefinition(Integer companyDefinition) {
        this.companyDefinition = companyDefinition;
    }

    public Integer getCustomerAttributes() {
        return customerAttributes;
    }

    public void setCustomerAttributes(Integer customerAttributes) {
        this.customerAttributes = customerAttributes;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public Date getAbandonCustomerTime() {
        return abandonCustomerTime;
    }

    public void setAbandonCustomerTime(Date abandonCustomerTime) {
        this.abandonCustomerTime = abandonCustomerTime;
    }

    public Integer getExamineApprove() {
        return examineApprove;
    }

    public void setExamineApprove(Integer examineApprove) {
        this.examineApprove = examineApprove;
    }

    public String getExamineApproveTime() {
        return examineApproveTime;
    }

    public void setExamineApproveTime(String examineApproveTime) {
        this.examineApproveTime = examineApproveTime == null ? null : examineApproveTime.trim();
    }

    public Integer getBlacklist1() {
        return blacklist1;
    }

    public void setBlacklist1(Integer blacklist1) {
        this.blacklist1 = blacklist1;
    }
    public String getYbcontent() {
        return ybcontent;
    }

    public void setYbcontent(String ybcontent) {
        this.ybcontent = ybcontent == null ? null : ybcontent.trim();
    }

    public String getQbcontent() {
        return qbcontent;
    }

    public void setQbcontent(String qbcontent) {
        this.qbcontent = qbcontent == null ? null : qbcontent.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCompanyprofile() {
        return companyprofile;
    }

    public void setCompanyprofile(String companyprofile) {
        this.companyprofile = companyprofile == null ? null : companyprofile.trim();
    }

    public String getGetContent() {
        return getContent;
    }

    public void setGetContent(String getContent) {
        this.getContent = getContent == null ? null : getContent.trim();
    }
}