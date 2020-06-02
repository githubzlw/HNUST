package com.cn.hnust.pojo;

import java.util.List;

//新报价表
public class QuotationNewQuotes {
	/** id*/
	private int id;
	/** 项目号 */
	private String projectId;
	/** 客户id*/
	private int cid;
	/** 客户名*/
	private String customername;
	/** 项目中文名*/
	private String projectdescc;
	/** 项目英文名*/
	private String projectdesce;
	/** 创建时间*/
	private String createtime;
	/** 创建时间*/
	private String createtime1;
	/** 销售*/
	private String salename;
	/** 报价员*/
	private String quoter;
	/** 项目类型*/
	private int projecttypes;
	/** 项目类型*/
	private String projecttypes1;
	/** 项目大小*/
	private Double itemsize;
	/** 项目状态 (增加待老板审批状态[当金额大于10万]、待姜工审批)*/
	private String quotationstatus;
	/** 项目内容*/
	private String content;
	/** 项目内容*/
	private String content1;
	/** 审批结果*/
	private String status;
	/** 原始附件*/
	private String type;
	/** 报价结果  0报价中   1报价有结果*/
	private int quotationresult;
	/** 客户类型*/
	private String customertype;
	/** 备注*/
	private String notes;
	/** 开始*/
	private int start;
	/** 结束*/
	private int end;
	/** 状态*/
	private int state;
	/** 审批内容*/
	private String status1;
	/** 对内报价时间*/
	private String quotetime;
	/** 审批时间*/
	private String approvaltime;
	/** 资料齐全*/
	private String completeData;
	/** 对外报价时间*/
	private String quotationtime;
	/** 客户反馈时间*/
	private String feedbacktime;
	/** 客户发出时间*/
	private String bidTime;
	/** 客户类型1*/
	private String clienttype;
	/** 客户a,b c*/
	private String projectimportance;
	/** 客户a,b c*/
	private String projectimportance1;
	/** 客户a,b c*/
	private String projectimportance2;
	/** 客户a,b c*/
	private String nonProjectState;
	/** 项目定义 0.项目正常,1.销售3天未报价,2.报价员3天未报价 ,3.销售和报价员3天都未报价*/
	private int withoutquotation;
	/** 催报价时间*/
	private String promptquotation;
	/** 0表示是成品，1表示是定制品*/
	private int permissionsetting;
	/** 英文关键词*/
	private String englishkeywords;
	/** 中文关键词1*/
	private String chinesekeywords1;
	/** 中文关键词1*/
	private String chinesekeywords2;
	/** 中文关键词1*/
	private String chinesekeywords3;
	/** 销售是否查看*/
	private int salesview;
	/** 报价员是否查看*/
	private int pricecheckerview;
	/** rfqid*/
	private int rfqid;
	/** 管理员是否查看*/
	private int adminview;
	/** 模具价格上涨比例*/
	private double moldPriceRise;
	/** 材料价格上涨比例*/
	private double proportionMaterialPriceIncrease;
	/** 美元汇率/人民币*/
	private double dollarExchangeRate;
	/** 美元汇率/欧元汇率*/
	private double euroExchangeRate;
	/** 美元汇率/英镑汇率*/
	private double sterlingExchangeRate;
	/** 几位小数*/
	private int severalDecimalPlaces;
	/** 附件名*/
	private String attachmentname;
	/** 工艺分析*/
	private String technologicalAnalysis;
	/** 主图*/
	private String mainFigure;
	/** 全部图纸*/
	private String allDrawings;
	/** 是否传值给快制造*/
	private int RapidManufacturing;
	/** 国家*/
	private String country;
	/** 工厂名*/
	private String factoryName1;
	/** 工厂名2*/
	private String factoryName2;
	/** 工厂名3*/
	private String factoryName3;
	/** 工厂名4*/
	private String factoryName4;
	/** 工厂名5*/
	private String factoryName5;
	
	/** 时间1*/
	private String createTime1;
	/** 时间2*/
	private String createTime2;
	/** 时间3*/
	private String createTime3;
	/** 时间4*/
	private String createTime4;
	/** 时间5*/
	private String createTime5;
	/** 工厂名*/
	private String factoryName6;
	/** 工厂名2*/
	private String factoryName7;
	/** 工厂名3*/
	private String factoryName8;
	/** 工厂名4*/
	private String factoryName9;
	/** 工厂名5*/
	private String factoryName10;
	
	/** 时间1*/
	private String createTime6;
	/** 时间2*/
	private String createTime7;
	/** 时间3*/
	private String createTime8;
	/** 时间4*/
	private String createTime9;
	/** 时间5*/
	private String createTime10;
	/** 修改crm报价链接*/
	private String url;
	/** 客户留言 3有留言 1.已查看*/
	private int note;
	/** 客户留言 0.代表未有回复邮件 1.有回复邮件*/
	private int customerReply;
	//报价助理
	private String quotationAssistant;
	//报价员是否将报价提交给快制造
	private int quotationSubmission;
	//报价文员选择时间
	private String quotationClerkSelection;
	//文件传到快制造时间
    private String fastManufacturingTime;
    //Add by Polo 2018/03/29
    //需要的技术审批人
    private String processApproval;   
    //技术是否审批通过( 1：已给建议 2：需要支持 3：不需要支持)
    private int processApprovalStatus;
    //技术人意见
    private String processApprovalDetail;    
    //给建议时间
    private String processApprovalTime;
    //价格审批是否通过(0：不通过  1：通过 )
    private int priceApprovalStatus;
    //价格审批意见
    private String priceApprovalDetail;    
    //价格审批时间
    private String priceApprovalTime;
    //老板审批时间
    private String bossApprovalTime;
    //老板审批建议
    private String bossApprovalDetail;
  
    //项目跟进状态项目跟进状态(1：正常 2：深入沟通 3：生产 4：报价太高或错误 5：暂停 6：完成 7：项目死亡(价格过高) 8:项目死亡(其他原因) 9.项目搁置(客户超过一个月没有反应) )
    private int quoteFollowStatus;
    //项目更新时间
    private String followTime;
    //项目类型(1、新客户新报价 2、老客户新报价 3、老客户价格更新)
    private int quoteType;
    //询盘简介
    private String quoteRemark;
    //报价员是否36小时及时报价（0:default  1:及时报价 2：不及时报价）
    private int flag1;
    //销售是否72小时及时报价 （0:default  1:及时报价 2：不及时报价）
    private int flag2;
    //报价文员是否36小时找工厂报价（0:default  1:及时报价 2：不及时报价）
    private int flag3;
    //报价文员是否168小时找三家工厂报价（0:default  1:及时报价 2：不及时报价）
    private int flag4;
  //下单状态 0 还未下单  1，表示ERP转跟单
    private Integer orderState;
    //下单时间
    private String orderStateTime;
    //下单是否更新到快制造数据
    private Integer operation;
    //0无一家工厂报价 1.已有最少1家工厂报价
    private Integer factoryQuotation;
    //多少家工厂报价
    private Integer factoryNum;
    
  //0无3家工厂报价 1.已有最少3家工厂报价
    private Integer threeFactoryQuotations;
     //1家工厂报价剩余时间
    private String oneRemainingTime;
    //1家工厂报价超时时间
    private String oneTimeoutTime;
  //3家工厂报价剩余时间
    private String threeRemainingTime;
    //3家工厂报价超时时间
    private String threeTimeoutTime;
    //要求协助内容
    private String askForAssistance;
    //要求协助内容人员
    private String askForAssistancePerson;
    //要求协助内容时间
    private String askForAssistanceTime;
    /** 距今日期*/
	private int beOverdue;
	 /** 销售报价剩余时间*/
		private String salesRemainingTime;
		 /** 销售报价超时时间*/
		private String salesOvertime;
		 /** 报价员上传报价单剩余时间*/
		private String quoterRemainingTime;
		 /** 报价员上传报价单超时时间*/
		private String quoterOvertime;
		/** 是否属于竞价项目*/
		private int businessBiddingProject;
		/** 新消息*/
		private String newInformation;
		/** 新报价*/
		private String newQuotation;
		/** 采购*/
		private String purchase;
		/** kuaizhizaoId*/
		private String fastManufacturingId;
		/** 是否适合我们做 0适合，1不适合*/
		private int suitableForDoing;
		/** kuaizhizaoId*/
		private String email;
		/** kuaizhizaoId*/
		private String telephone;
		private Integer externalQuotation;//0,未对外报价 1.对外报价
		 
		 
		 private String quotationIssuingTime;//报价发出时间
		 private String quotationSuccessTime;//报价成功时间
		 private String allProcess;//全部工艺多选
		 private String marketPriceOfProducts;//产品市场价
		 /*0,暂未定义 1.小项目（0-6万），2.中等（6-10），3.中大（12-20），4.大（20-50），5.超大（50万以上） */
		 private String projectDefinitionSize;//项目定义大小
		 /** 客户定义 1.最终用户  2.表示中间商*/
			private 	String clientDefining;
			/** 客户定义 -1，表示全部  1，表示半年  2表示3个月  3表示1个月*/
			private int queryDate;
			/*** 需找多少家新低报价 */
			private int number;
		 
		 
	   
		public int getNumber() {
				return number;
			}
			public void setNumber(int number) {
				this.number = number;
			}
		public int getQueryDate() {
				return queryDate;
			}
			public void setQueryDate(int queryDate) {
				this.queryDate = queryDate;
			}
		public String getClientDefining() {
			return clientDefining;
		}
		public void setClientDefining(String clientDefining) {
			this.clientDefining = clientDefining;
		}
		public String getProjectDefinitionSize() {
			return projectDefinitionSize;
		}
		public void setProjectDefinitionSize(String projectDefinitionSize) {
			this.projectDefinitionSize = projectDefinitionSize;
		}
		public String getMarketPriceOfProducts() {
			return marketPriceOfProducts;
		}
		public void setMarketPriceOfProducts(String marketPriceOfProducts) {
			this.marketPriceOfProducts = marketPriceOfProducts;
		}
		public String getAllProcess() {
			return allProcess;
		}
		public void setAllProcess(String allProcess) {
			this.allProcess = allProcess;
		}
		public String getQuotationSuccessTime() {
			return quotationSuccessTime;
		}
		public void setQuotationSuccessTime(String quotationSuccessTime) {
			this.quotationSuccessTime = quotationSuccessTime;
		}
	   public String getQuotationIssuingTime() {
			return quotationIssuingTime;
		}
		public void setQuotationIssuingTime(String quotationIssuingTime) {
			this.quotationIssuingTime = quotationIssuingTime;
		}
	public Integer getExternalQuotation() {
			return externalQuotation;
		}
		public void setExternalQuotation(Integer externalQuotation) {
			this.externalQuotation = externalQuotation;
		}

	public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
	public int getSuitableForDoing() {
			return suitableForDoing;
		}
		public void setSuitableForDoing(int suitableForDoing) {
			this.suitableForDoing = suitableForDoing;
		}
	public String getFastManufacturingId() {
			return fastManufacturingId;
		}
		public void setFastManufacturingId(String fastManufacturingId) {
			this.fastManufacturingId = fastManufacturingId;
		}
	public String getPurchase() {
			return purchase;
		}
		public void setPurchase(String purchase) {
			this.purchase = purchase;
		}
	public String getNewInformation() {
			return newInformation;
		}
		public void setNewInformation(String newInformation) {
			this.newInformation = newInformation;
		}
		public String getNewQuotation() {
			return newQuotation;
		}
		public void setNewQuotation(String newQuotation) {
			this.newQuotation = newQuotation;
		}
	public int getBusinessBiddingProject() {
			return businessBiddingProject;
		}
		public void setBusinessBiddingProject(int businessBiddingProject) {
			this.businessBiddingProject = businessBiddingProject;
		}
	public String getQuoterRemainingTime() {
			return quoterRemainingTime;
		}
		public void setQuoterRemainingTime(String quoterRemainingTime) {
			this.quoterRemainingTime = quoterRemainingTime;
		}
		public String getQuoterOvertime() {
			return quoterOvertime;
		}
		public void setQuoterOvertime(String quoterOvertime) {
			this.quoterOvertime = quoterOvertime;
		}
	public String getSalesRemainingTime() {
			return salesRemainingTime;
		}
		public void setSalesRemainingTime(String salesRemainingTime) {
			this.salesRemainingTime = salesRemainingTime;
		}
		public String getSalesOvertime() {
			return salesOvertime;
		}
		public void setSalesOvertime(String salesOvertime) {
			this.salesOvertime = salesOvertime;
		}
	public int getBeOverdue() {
		return beOverdue;
	}
	public void setBeOverdue(int beOverdue) {
		this.beOverdue = beOverdue;
	}
	public Integer getFactoryNum() {
		return factoryNum;
	}
	public void setFactoryNum(Integer factoryNum) {
		this.factoryNum = factoryNum;
	}
	public Integer getFactoryQuotation() {
		return factoryQuotation;
	}
	public void setFactoryQuotation(Integer factoryQuotation) {
		this.factoryQuotation = factoryQuotation;
	}
	public Integer getThreeFactoryQuotations() {
		return threeFactoryQuotations;
	}
	public void setThreeFactoryQuotations(Integer threeFactoryQuotations) {
		this.threeFactoryQuotations = threeFactoryQuotations;
	}
	public String getOneRemainingTime() {
		return oneRemainingTime;
	}
	public void setOneRemainingTime(String oneRemainingTime) {
		this.oneRemainingTime = oneRemainingTime;
	}
	public String getOneTimeoutTime() {
		return oneTimeoutTime;
	}
	public void setOneTimeoutTime(String oneTimeoutTime) {
		this.oneTimeoutTime = oneTimeoutTime;
	}
	public String getThreeRemainingTime() {
		return threeRemainingTime;
	}
	public void setThreeRemainingTime(String threeRemainingTime) {
		this.threeRemainingTime = threeRemainingTime;
	}
	public String getThreeTimeoutTime() {
		return threeTimeoutTime;
	}
	public void setThreeTimeoutTime(String threeTimeoutTime) {
		this.threeTimeoutTime = threeTimeoutTime;
	}
	public String getNonProjectState() {
		return nonProjectState;
	}
	public void setNonProjectState(String nonProjectState) {
		this.nonProjectState = nonProjectState;
	}
	public String getProjectimportance2() {
		return projectimportance2;
	}
	public void setProjectimportance2(String projectimportance2) {
		this.projectimportance2 = projectimportance2;
	}
	public String getProjectimportance1() {
		return projectimportance1;
	}
	public void setProjectimportance1(String projectimportance1) {
		this.projectimportance1 = projectimportance1;
	}
	public String getCreatetime1() {
		return createtime1;
	}
	public void setCreatetime1(String createtime1) {
		this.createtime1 = createtime1;
	}
	public Integer getOperation() {
		return operation;
	}
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	public int getFlag1() {
		return flag1;
	}
	public void setFlag1(int flag1) {
		this.flag1 = flag1;
	}
	public int getFlag2() {
		return flag2;
	}
	public void setFlag2(int flag2) {
		this.flag2 = flag2;
	}
	public int getFlag3() {
		return flag3;
	}
	public void setFlag3(int flag3) {
		this.flag3 = flag3;
	}
	public int getFlag4() {
		return flag4;
	}
	public void setFlag4(int flag4) {
		this.flag4 = flag4;
	}
	public String getQuoteRemark() {
		return quoteRemark;
	}
	public void setQuoteRemark(String quoteRemark) {
		this.quoteRemark = quoteRemark;
	}
	public int getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(int quoteType) {
		this.quoteType = quoteType;
	}
	public int getQuoteFollowStatus() {
		return quoteFollowStatus;
	}
	public void setQuoteFollowStatus(int quoteFollowStatus) {
		this.quoteFollowStatus = quoteFollowStatus;
	}
	public String getFollowTime() {
		return followTime;
	}
	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}
	public String getBossApprovalDetail() {
		return bossApprovalDetail;
	}
	public void setBossApprovalDetail(String bossApprovalDetail) {
		this.bossApprovalDetail = bossApprovalDetail;
	}
	public String getBossApprovalTime() {
		return bossApprovalTime;
	}
	public void setBossApprovalTime(String bossApprovalTime) {
		this.bossApprovalTime = bossApprovalTime;
	}
	public String getProcessApprovalTime() {
		return processApprovalTime;
	}
	public void setProcessApprovalTime(String processApprovalTime) {
		this.processApprovalTime = processApprovalTime;
	}
	public String getPriceApprovalTime() {
		return priceApprovalTime;
	}
	public void setPriceApprovalTime(String priceApprovalTime) {
		this.priceApprovalTime = priceApprovalTime;
	}
	public String getProcessApproval() {
		return processApproval;
	}
	public void setProcessApproval(String processApproval) {
		this.processApproval = processApproval;
	}
	public int getProcessApprovalStatus() {
		return processApprovalStatus;
	}
	public void setProcessApprovalStatus(int processApprovalStatus) {
		this.processApprovalStatus = processApprovalStatus;
	}
	public String getProcessApprovalDetail() {
		return processApprovalDetail;
	}
	public void setProcessApprovalDetail(String processApprovalDetail) {
		this.processApprovalDetail = processApprovalDetail;
	}
	public int getPriceApprovalStatus() {
		return priceApprovalStatus;
	}
	public void setPriceApprovalStatus(int priceApprovalStatus) {
		this.priceApprovalStatus = priceApprovalStatus;
	}
	public String getPriceApprovalDetail() {
		return priceApprovalDetail;
	}
	public void setPriceApprovalDetail(String priceApprovalDetail) {
		this.priceApprovalDetail = priceApprovalDetail;
	}
	public String getQuotationClerkSelection() {
		return quotationClerkSelection;
	}
	public void setQuotationClerkSelection(String quotationClerkSelection) {
		this.quotationClerkSelection = quotationClerkSelection;
	}
	public String getFastManufacturingTime() {
		return fastManufacturingTime;
	}
	public void setFastManufacturingTime(String fastManufacturingTime) {
		this.fastManufacturingTime = fastManufacturingTime;
	}
	public String getFactoryName4() {
		return factoryName4;
	}
	public void setFactoryName4(String factoryName4) {
		this.factoryName4 = factoryName4;
	}
	public String getFactoryName5() {
		return factoryName5;
	}
	public void setFactoryName5(String factoryName5) {
		this.factoryName5 = factoryName5;
	}
	public String getCreateTime4() {
		return createTime4;
	}
	public void setCreateTime4(String createTime4) {
		this.createTime4 = createTime4;
	}
	public String getCreateTime5() {
		return createTime5;
	}
	public void setCreateTime5(String createTime5) {
		this.createTime5 = createTime5;
	}
	public int getQuotationSubmission() {
		return quotationSubmission;
	}
	public void setQuotationSubmission(int quotationSubmission) {
		this.quotationSubmission = quotationSubmission;
	}
	public String getQuotationAssistant() {
		return quotationAssistant;
	}
	public void setQuotationAssistant(String quotationAssistant) {
		this.quotationAssistant = quotationAssistant;
	}
	public String getBidTime() {
		return bidTime;
	}
	public void setBidTime(String bidTime) {
		this.bidTime = bidTime;
	}
	public int getCustomerReply() {
		return customerReply;
	}
	public void setCustomerReply(int customerReply) {
		this.customerReply = customerReply;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreateTime1() {
		return createTime1;
	}
	public void setCreateTime1(String createTime1) {
		this.createTime1 = createTime1;
	}
	public String getCreateTime2() {
		return createTime2;
	}
	public void setCreateTime2(String createTime2) {
		this.createTime2 = createTime2;
	}
	public String getCreateTime3() {
		return createTime3;
	}
	public void setCreateTime3(String createTime3) {
		this.createTime3 = createTime3;
	}
	public String getFactoryName1() {
		return factoryName1;
	}
	public void setFactoryName1(String factoryName1) {
		this.factoryName1 = factoryName1;
	}
	public String getFactoryName2() {
		return factoryName2;
	}
	public void setFactoryName2(String factoryName2) {
		this.factoryName2 = factoryName2;
	}
	public String getFactoryName3() {
		return factoryName3;
	}
	public void setFactoryName3(String factoryName3) {
		this.factoryName3 = factoryName3;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProjecttypes1() {
		return projecttypes1;
	}
	public void setProjecttypes1(String projecttypes1) {
		this.projecttypes1 = projecttypes1;
	}
	public int getRapidManufacturing() {
		return RapidManufacturing;
	}
	public void setRapidManufacturing(int rapidManufacturing) {
		RapidManufacturing = rapidManufacturing;
	}
	public String getMainFigure() {
		return mainFigure;
	}
	public void setMainFigure(String mainFigure) {
		this.mainFigure = mainFigure;
	}
	public String getAllDrawings() {
		return allDrawings;
	}
	public void setAllDrawings(String allDrawings) {
		this.allDrawings = allDrawings;
	}
	public String getTechnologicalAnalysis() {
		return technologicalAnalysis;
	}
	public void setTechnologicalAnalysis(String technologicalAnalysis) {
		this.technologicalAnalysis = technologicalAnalysis;
	}
	public String getCompleteData() {
		return completeData;
	}
	public void setCompleteData(String completeData) {
		this.completeData = completeData;
	}
	public String getAttachmentname() {
		return attachmentname;
	}
	public void setAttachmentname(String attachmentname) {
		this.attachmentname = attachmentname;
	}
	public int getSeveralDecimalPlaces() {
		return severalDecimalPlaces;
	}
	public void setSeveralDecimalPlaces(int severalDecimalPlaces) {
		this.severalDecimalPlaces = severalDecimalPlaces;
	}
	public double getMoldPriceRise() {
		return moldPriceRise;
	}
	public void setMoldPriceRise(double moldPriceRise) {
		this.moldPriceRise = moldPriceRise;
	}
	public double getProportionMaterialPriceIncrease() {
		return proportionMaterialPriceIncrease;
	}
	public void setProportionMaterialPriceIncrease(
			double proportionMaterialPriceIncrease) {
		this.proportionMaterialPriceIncrease = proportionMaterialPriceIncrease;
	}
	public double getDollarExchangeRate() {
		return dollarExchangeRate;
	}
	public void setDollarExchangeRate(double dollarExchangeRate) {
		this.dollarExchangeRate = dollarExchangeRate;
	}
	public double getEuroExchangeRate() {
		return euroExchangeRate;
	}
	public void setEuroExchangeRate(double euroExchangeRate) {
		this.euroExchangeRate = euroExchangeRate;
	}
	public double getSterlingExchangeRate() {
		return sterlingExchangeRate;
	}
	public void setSterlingExchangeRate(double sterlingExchangeRate) {
		this.sterlingExchangeRate = sterlingExchangeRate;
	}
	public int getAdminview() {
		return adminview;
	}
	public void setAdminview(int adminview) {
		this.adminview = adminview;
	}
	public int getRfqid() {
		return rfqid;
	}
	public void setRfqid(int rfqid) {
		this.rfqid = rfqid;
	}
	public int getSalesview() {
		return salesview;
	}
	public void setSalesview(int salesview) {
		this.salesview = salesview;
	}
	public int getPricecheckerview() {
		return pricecheckerview;
	}
	public void setPricecheckerview(int pricecheckerview) {
		this.pricecheckerview = pricecheckerview;
	}
	public String getContent1() {
		return content1;
	}
	public void setContent1(String content1) {
		this.content1 = content1;
	}
	public int getPermissionsetting() {
		return permissionsetting;
	}
	public void setPermissionsetting(int permissionsetting) {
		this.permissionsetting = permissionsetting;
	}
	public String getPromptquotation() {
		return promptquotation;
	}
	public void setPromptquotation(String promptquotation) {
		this.promptquotation = promptquotation;
	}
	public String getProjectimportance() {
		return projectimportance;
	}
	public void setProjectimportance(String projectimportance) {
		this.projectimportance = projectimportance;
	}
	public int getWithoutquotation() {
		return withoutquotation;
	}
	public void setWithoutquotation(int withoutquotation) {
		this.withoutquotation = withoutquotation;
	}
	
	public String getClienttype() {
		return clienttype;
	}
	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}
	public String getQuotetime() {
		return quotetime;
	}
	public void setQuotetime(String quotetime) {
		this.quotetime = quotetime;
	}
	public String getApprovaltime() {
		return approvaltime;
	}
	public void setApprovaltime(String approvaltime) {
		this.approvaltime = approvaltime;
	}
	public String getQuotationtime() {
		return quotationtime;
	}
	public void setQuotationtime(String quotationtime) {
		this.quotationtime = quotationtime;
	}
	public String getFeedbacktime() {
		return feedbacktime;
	}
	public void setFeedbacktime(String feedbacktime) {
		this.feedbacktime = feedbacktime;
	}
	public String getStatus1() {
		return status1;
	}
	public void setStatus1(String status1) {
		this.status1 = status1;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getCustomertype() {
		return customertype;
	}
	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getProjectdescc() {
		return projectdescc;
	}
	public void setProjectdescc(String projectdescc) {
		this.projectdescc = projectdescc;
	}
	
	public String getProjectdesce() {
		return projectdesce;
	}
	public void setProjectdesce(String projectdesce) {
		this.projectdesce = projectdesce;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getSalename() {
		return salename;
	}
	public void setSalename(String salename) {
		this.salename = salename;
	}
	public String getQuoter() {
		return quoter;
	}
	public void setQuoter(String quoter) {
		this.quoter = quoter;
	}
	public int getProjecttypes() {
		return projecttypes;
	}
	public void setProjecttypes(int projecttypes) {
		this.projecttypes = projecttypes;
	}
	public Double getItemsize() {
		return itemsize;
	}
	public void setItemsize(Double itemsize) {
		this.itemsize = itemsize;
	}
	public String getQuotationstatus() {
		return quotationstatus;
	}
	public void setQuotationstatus(String quotationstatus) {
		this.quotationstatus = quotationstatus;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuotationresult() {
		return quotationresult;
	}
	public void setQuotationresult(int quotationresult) {
		this.quotationresult = quotationresult;
	}
	
	
	public String getEnglishkeywords() {
		return englishkeywords;
	}
	public void setEnglishkeywords(String englishkeywords) {
		this.englishkeywords = englishkeywords;
	}
	public String getChinesekeywords1() {
		return chinesekeywords1;
	}
	public void setChinesekeywords1(String chinesekeywords1) {
		this.chinesekeywords1 = chinesekeywords1;
	}
	public String getChinesekeywords2() {
		return chinesekeywords2;
	}
	public void setChinesekeywords2(String chinesekeywords2) {
		this.chinesekeywords2 = chinesekeywords2;
	}
	public String getChinesekeywords3() {
		return chinesekeywords3;
	}
	public void setChinesekeywords3(String chinesekeywords3) {
		this.chinesekeywords3 = chinesekeywords3;
	}
	
	
	public String getOrderStateTime() {
		return orderStateTime;
	}
	public void setOrderStateTime(String orderStateTime) {
		this.orderStateTime = orderStateTime;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	
	public String getAskForAssistance() {
		return askForAssistance;
	}
	public void setAskForAssistance(String askForAssistance) {
		this.askForAssistance = askForAssistance;
	}
	
	public String getAskForAssistancePerson() {
		return askForAssistancePerson;
	}
	public void setAskForAssistancePerson(String askForAssistancePerson) {
		this.askForAssistancePerson = askForAssistancePerson;
	}
	public String getAskForAssistanceTime() {
		return askForAssistanceTime;
	}
	public void setAskForAssistanceTime(String askForAssistanceTime) {
		this.askForAssistanceTime = askForAssistanceTime;
	}
	
	
	public String getFactoryName6() {
		return factoryName6;
	}
	public void setFactoryName6(String factoryName6) {
		this.factoryName6 = factoryName6;
	}
	public String getFactoryName7() {
		return factoryName7;
	}
	public void setFactoryName7(String factoryName7) {
		this.factoryName7 = factoryName7;
	}
	public String getFactoryName8() {
		return factoryName8;
	}
	public void setFactoryName8(String factoryName8) {
		this.factoryName8 = factoryName8;
	}
	public String getFactoryName9() {
		return factoryName9;
	}
	public void setFactoryName9(String factoryName9) {
		this.factoryName9 = factoryName9;
	}
	public String getFactoryName10() {
		return factoryName10;
	}
	public void setFactoryName10(String factoryName10) {
		this.factoryName10 = factoryName10;
	}
	public String getCreateTime6() {
		return createTime6;
	}
	public void setCreateTime6(String createTime6) {
		this.createTime6 = createTime6;
	}
	public String getCreateTime7() {
		return createTime7;
	}
	public void setCreateTime7(String createTime7) {
		this.createTime7 = createTime7;
	}
	public String getCreateTime8() {
		return createTime8;
	}
	public void setCreateTime8(String createTime8) {
		this.createTime8 = createTime8;
	}
	public String getCreateTime9() {
		return createTime9;
	}
	public void setCreateTime9(String createTime9) {
		this.createTime9 = createTime9;
	}
	public String getCreateTime10() {
		return createTime10;
	}
	public void setCreateTime10(String createTime10) {
		this.createTime10 = createTime10;
	}
	@Override
	public String toString() {
		return "QuotationNewQuotes [id=" + id + ", projectId=" + projectId
				+ ", cid=" + cid + ", customername=" + customername
				+ ", projectdescc=" + projectdescc + ", projectdesce="
				+ projectdesce + ", createtime=" + createtime
				+ ", createtime1=" + createtime1 + ", salename=" + salename
				+ ", quoter=" + quoter + ", projecttypes=" + projecttypes
				+ ", projecttypes1=" + projecttypes1 + ", itemsize=" + itemsize
				+ ", quotationstatus=" + quotationstatus + ", content="
				+ content + ", content1=" + content1 + ", status=" + status
				+ ", type=" + type + ", quotationresult=" + quotationresult
				+ ", customertype=" + customertype + ", notes=" + notes
				+ ", start=" + start + ", end=" + end + ", state=" + state
				+ ", status1=" + status1 + ", quotetime=" + quotetime
				+ ", approvaltime=" + approvaltime + ", completeData="
				+ completeData + ", quotationtime=" + quotationtime
				+ ", feedbacktime=" + feedbacktime + ", bidTime=" + bidTime
				+ ", clienttype=" + clienttype + ", projectimportance="
				+ projectimportance + ", projectimportance1="
				+ projectimportance1 + ", projectimportance2="
				+ projectimportance2 + ", nonProjectState=" + nonProjectState
				+ ", withoutquotation=" + withoutquotation
				+ ", promptquotation=" + promptquotation
				+ ", permissionsetting=" + permissionsetting
				+ ", englishkeywords=" + englishkeywords
				+ ", chinesekeywords1=" + chinesekeywords1
				+ ", chinesekeywords2=" + chinesekeywords2
				+ ", chinesekeywords3=" + chinesekeywords3 + ", salesview="
				+ salesview + ", pricecheckerview=" + pricecheckerview
				+ ", rfqid=" + rfqid + ", adminview=" + adminview
				+ ", moldPriceRise=" + moldPriceRise
				+ ", proportionMaterialPriceIncrease="
				+ proportionMaterialPriceIncrease + ", dollarExchangeRate="
				+ dollarExchangeRate + ", euroExchangeRate=" + euroExchangeRate
				+ ", sterlingExchangeRate=" + sterlingExchangeRate
				+ ", severalDecimalPlaces=" + severalDecimalPlaces
				+ ", attachmentname=" + attachmentname
				+ ", technologicalAnalysis=" + technologicalAnalysis
				+ ", mainFigure=" + mainFigure + ", allDrawings=" + allDrawings
				+ ", RapidManufacturing=" + RapidManufacturing + ", country="
				+ country + ", factoryName1=" + factoryName1
				+ ", factoryName2=" + factoryName2 + ", factoryName3="
				+ factoryName3 + ", factoryName4=" + factoryName4
				+ ", factoryName5=" + factoryName5 + ", createTime1="
				+ createTime1 + ", createTime2=" + createTime2
				+ ", createTime3=" + createTime3 + ", createTime4="
				+ createTime4 + ", createTime5=" + createTime5
				+ ", factoryName6=" + factoryName6 + ", factoryName7="
				+ factoryName7 + ", factoryName8=" + factoryName8
				+ ", factoryName9=" + factoryName9 + ", factoryName10="
				+ factoryName10 + ", createTime6=" + createTime6
				+ ", createTime7=" + createTime7 + ", createTime8="
				+ createTime8 + ", createTime9=" + createTime9
				+ ", createTime10=" + createTime10 + ", url=" + url + ", note="
				+ note + ", customerReply=" + customerReply
				+ ", quotationAssistant=" + quotationAssistant
				+ ", quotationSubmission=" + quotationSubmission
				+ ", quotationClerkSelection=" + quotationClerkSelection
				+ ", fastManufacturingTime=" + fastManufacturingTime
				+ ", processApproval=" + processApproval
				+ ", processApprovalStatus=" + processApprovalStatus
				+ ", processApprovalDetail=" + processApprovalDetail
				+ ", processApprovalTime=" + processApprovalTime
				+ ", priceApprovalStatus=" + priceApprovalStatus
				+ ", priceApprovalDetail=" + priceApprovalDetail
				+ ", priceApprovalTime=" + priceApprovalTime
				+ ", bossApprovalTime=" + bossApprovalTime
				+ ", bossApprovalDetail=" + bossApprovalDetail
				+ ", quoteFollowStatus=" + quoteFollowStatus + ", followTime="
				+ followTime + ", quoteType=" + quoteType + ", quoteRemark="
				+ quoteRemark + ", flag1=" + flag1 + ", flag2=" + flag2
				+ ", flag3=" + flag3 + ", flag4=" + flag4 + ", orderState="
				+ orderState + ", orderStateTime=" + orderStateTime
				+ ", operation=" + operation + ", factoryQuotation="
				+ factoryQuotation + ", factoryNum=" + factoryNum
				+ ", threeFactoryQuotations=" + threeFactoryQuotations
				+ ", oneRemainingTime=" + oneRemainingTime
				+ ", oneTimeoutTime=" + oneTimeoutTime
				+ ", threeRemainingTime=" + threeRemainingTime
				+ ", threeTimeoutTime=" + threeTimeoutTime
				+ ", askForAssistance=" + askForAssistance
				+ ", askForAssistancePerson=" + askForAssistancePerson
				+ ", askForAssistanceTime=" + askForAssistanceTime
				+ ", beOverdue=" + beOverdue + ", salesRemainingTime="
				+ salesRemainingTime + ", salesOvertime=" + salesOvertime
				+ ", quoterRemainingTime=" + quoterRemainingTime
				+ ", quoterOvertime=" + quoterOvertime
				+ ", businessBiddingProject=" + businessBiddingProject
				+ ", newInformation=" + newInformation + ", newQuotation="
				+ newQuotation + ", purchase=" + purchase
				+ ", fastManufacturingId=" + fastManufacturingId
				+ ", suitableForDoing=" + suitableForDoing + ", email=" + email
				+ ", telephone=" + telephone + ", externalQuotation="
				+ externalQuotation + ", quotationIssuingTime="
				+ quotationIssuingTime + ", quotationSuccessTime="
				+ quotationSuccessTime + ", allProcess=" + allProcess
				+ ", marketPriceOfProducts=" + marketPriceOfProducts
				+ ", projectDefinitionSize=" + projectDefinitionSize
				+ ", clientDefining=" + clientDefining + ", queryDate="
				+ queryDate + ", number=" + number + "]";
	}
	
}
