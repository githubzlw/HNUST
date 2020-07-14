package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ShippingConfirmation implements Serializable {
    private Integer id;

    private String projectNo;              //项目号

    private String serialNumber;           //出运单号

    private String customerName;           //客户名

    private String productName;            //货物名称

    private String shippingWay;            //运输方式

    private Integer isBossConfirm;         //是否需要ED确认(0：否 1：是）

    private String checkQty;               //抽检数量

    private String orders;                 //总订单量
    
    private String boxNumber;              //箱数

    private String openQty;                //开箱数量

    private String spendTime;              //检验费时

    private String checkConclusion;        //检验结论

    private String noExecuted;             //预定检验没能执行 
    
    private String concessiveAccept;       //让步接受的问题

    private String meetingConclusion;      //会议决议

    private String notPaid;                //客户未付金额(美元)

    private String scale;                  //所占比例
 
    private String customerConfirmWay;     //客户确认方式

    private Integer isQualityReportEn;     //是否提供英文版检验报告   0:没提供 1:已经提供 2：一天内来补3包装得当符合要求

    private String drawbackProduct;        //退税品名（多个逗号隔开）

    private String drawbackRate;           //退税率（多个逗号隔开）

    private String shippingFee;            //运费金额(美元)

    private String salesWorry;             //销售对质量的担忧

    private Date createTime;               //创建时间
    
    private String createPerson;           //创建人

    private String salesConfirm;           //销售确认

    private Date salesConfirmTime;          //销售确认时间

    private String purchaseConfirm;         //采购确认 

    private Date purchaseConfirmTime;       //采购确认时间

    private String qualityLeaderConfirm;    //质检总监确认

    private Date qualityLeaderConfirmTime;  //质检总监确认时间

    private String purchaseLeaderConfirm;   //采购总经理确认

    private Date purchaseLeaderConfirmTime; //采购总经理确认时间

    private String bossConfirm;             //老板确认

    private Date bossConfirmTime;           //老板确认时间
    
    private Integer isComplete;                 //是否完成 （0:未完成  1：已完成）
    
    private int isPlastic;                  //0:其他  1：塑料
    
    private Integer sampleOrProduct;            //0:样品 1:大货
    
    private String firstPerson;             //第一步录入人
    
    private Date firstTime;                 //第一步录入时间
    
    private String secondPerson;             //第二步录入人
    
    private Date secondTime;                 //第二步录入时间
    
    private String thirdPerson;              //第三步录入人
    
    private Date thirdTime;                  //第三步录入时间
    
    private String fourthPerson;             //第四步录入人
    
    private Date fourthTime;                 //第四步录入时间
    
    private Integer complaintId;             //投诉录入时，投诉表id
    
    private String sampleFileName;           //样品检验文件名
    
    private String sampleFileOriginalName;   //样品检验文件名
   
    private Integer isSendConfirmTask;       //是否发送签名任务(0：未发送 1：已发送)
       
    private Integer isQualityLeaderConfirm;   //新项目和A\B级项目需要姜工审批(0：不需要 1：需要）
    
    private String shipmentAgreement;         //出货证明     
    
    
    //虚拟字段
    private String projectAmount;          //项目金额
    private String purchaseName;   //采购名
	private String sellName;       //跟单名
    private String zhijian1;       //质检1
    private String zhijian2;       //质检2
    private String zhijian3;       //质检3 
    private String masterQualityInspection;    //主质检
    private String qualityInspector1;    //待质检
    private String qualityInspector2;    //待质检
    private String qualityInspector3;    //待质检
    private String qualityInspector4;    //待质检
    private String qualityInspector5;    //待质检
    private String qualityInspector6;    //待质检
    private String qualityInspector7;    //待质检
    private String customerNameProject;  //项目内查询的客户名
    private List<Comment> comments;   //评论
    private Boolean isSign;        //是否可以签名
    private Integer amountMoney;   //清关金额15000美元
    private Integer firstShipment; //第一次出货
    private Integer complaint;     //存在投诉
    private Integer productChanges;//产品有改动
    private Integer nonPlastic;    //非塑料类样品
    private Integer grade;    //项目客户等级A/B
    private Integer qualityLeaderConfirmId;  //质检总监审批 (0,未处理,1,代表审批通过，2，代表审批不通过)
    private Integer purchaseLeaderConfirmId; //采购总经理审批(0,未处理,1,代表审批通过，2，代表审批不通过)
    private Integer bossConfirmId; //老板审批(0,未处理,1,代表审批通过，2，代表审批不通过)
    private String sailingDate; //老板审批(0,未处理,1,代表审批通过，2，代表审批不通过)
    private Integer productNumber; //本次实发数量
    
    private String weight;//重量
    
    private String shippingInformation;//小批量是否发送信息
	private int deliveryConfirmation;//是否转钉钉审批
    
    private static final long serialVersionUID = 1L;


	public int getDeliveryConfirmation() {
		return deliveryConfirmation;
	}

	public void setDeliveryConfirmation(int deliveryConfirmation) {
		this.deliveryConfirmation = deliveryConfirmation;
	}

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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getShippingInformation() {
		return shippingInformation;
	}

	public void setShippingInformation(String shippingInformation) {
		this.shippingInformation = shippingInformation;
	}

	public Integer getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}

	public String getSailingDate() {
		return sailingDate;
	}

	public void setSailingDate(String sailingDate) {
		this.sailingDate = sailingDate;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getQualityLeaderConfirmId() {
		return qualityLeaderConfirmId;
	}

	public void setQualityLeaderConfirmId(Integer qualityLeaderConfirmId) {
		this.qualityLeaderConfirmId = qualityLeaderConfirmId;
	}

	public Integer getPurchaseLeaderConfirmId() {
		return purchaseLeaderConfirmId;
	}

	public void setPurchaseLeaderConfirmId(Integer purchaseLeaderConfirmId) {
		this.purchaseLeaderConfirmId = purchaseLeaderConfirmId;
	}

	public Integer getBossConfirmId() {
		return bossConfirmId;
	}

	public void setBossConfirmId(Integer bossConfirmId) {
		this.bossConfirmId = bossConfirmId;
	}

	public Integer getAmountMoney() {
		return amountMoney;
	}

	public void setAmountMoney(Integer amountMoney) {
		this.amountMoney = amountMoney;
	}

	public Integer getFirstShipment() {
		return firstShipment;
	}

	public void setFirstShipment(Integer firstShipment) {
		this.firstShipment = firstShipment;
	}

	public Integer getComplaint() {
		return complaint;
	}

	public void setComplaint(Integer complaint) {
		this.complaint = complaint;
	}

	public Integer getProductChanges() {
		return productChanges;
	}

	public void setProductChanges(Integer productChanges) {
		this.productChanges = productChanges;
	}

	public Integer getNonPlastic() {
		return nonPlastic;
	}

	public void setNonPlastic(Integer nonPlastic) {
		this.nonPlastic = nonPlastic;
	}

	public Boolean getIsSign() {
		return isSign;
	}

	public void setIsSign(Boolean isSign) {
		this.isSign = isSign;
	}

	public Integer getIsQualityLeaderConfirm() {
		return isQualityLeaderConfirm;
	}

	public void setIsQualityLeaderConfirm(Integer isQualityLeaderConfirm) {
		this.isQualityLeaderConfirm = isQualityLeaderConfirm;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getShipmentAgreement() {
		return shipmentAgreement;
	}

	public void setShipmentAgreement(String shipmentAgreement) {
		this.shipmentAgreement = shipmentAgreement;
	}

	public Integer getIsSendConfirmTask() {
		return isSendConfirmTask;
	}

	public void setIsSendConfirmTask(Integer isSendConfirmTask) {
		this.isSendConfirmTask = isSendConfirmTask;
	}

	public String getSampleFileName() {
		return sampleFileName;
	}

	public void setSampleFileName(String sampleFileName) {
		this.sampleFileName = sampleFileName;
	}

	public String getSampleFileOriginalName() {
		return sampleFileOriginalName;
	}

	public void setSampleFileOriginalName(String sampleFileOriginalName) {
		this.sampleFileOriginalName = sampleFileOriginalName;
	}

	public String getConcessiveAccept() {
		return concessiveAccept;
	}

	public void setConcessiveAccept(String concessiveAccept) {
		this.concessiveAccept = concessiveAccept;
	}

	public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}

	public String getFirstPerson() {
		return firstPerson;
	}

	public void setFirstPerson(String firstPerson) {
		this.firstPerson = firstPerson;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	public String getSecondPerson() {
		return secondPerson;
	}

	public void setSecondPerson(String secondPerson) {
		this.secondPerson = secondPerson;
	}

	public Date getSecondTime() {
		return secondTime;
	}

	public void setSecondTime(Date secondTime) {
		this.secondTime = secondTime;
	}

	public String getThirdPerson() {
		return thirdPerson;
	}

	public void setThirdPerson(String thirdPerson) {
		this.thirdPerson = thirdPerson;
	}

	public Date getThirdTime() {
		return thirdTime;
	}

	public void setThirdTime(Date thirdTime) {
		this.thirdTime = thirdTime;
	}

	public String getFourthPerson() {
		return fourthPerson;
	}

	public void setFourthPerson(String fourthPerson) {
		this.fourthPerson = fourthPerson;
	}

	public Date getFourthTime() {
		return fourthTime;
	}

	public void setFourthTime(Date fourthTime) {
		this.fourthTime = fourthTime;
	}

	public String getBoxNumber() {
		return boxNumber;
	}

	public void setBoxNumber(String boxNumber) {
		this.boxNumber = boxNumber;
	}

	public String getCustomerNameProject() {
		return customerNameProject;
	}

	public void setCustomerNameProject(String customerNameProject) {
		this.customerNameProject = customerNameProject;
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

	public Integer getSampleOrProduct() {
		return sampleOrProduct;
	}

	public void setSampleOrProduct(Integer sampleOrProduct) {
		this.sampleOrProduct = sampleOrProduct;
	}

	public String getProjectAmount() {
		return projectAmount;
	}

	public void setProjectAmount(String projectAmount) {
		this.projectAmount = projectAmount;
	}

	public int getIsPlastic() {
		return isPlastic;
	}

	public void setIsPlastic(int isPlastic) {
		this.isPlastic = isPlastic;
	}

	public Integer getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Integer isComplete) {
		this.isComplete = isComplete;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getShippingWay() {
        return shippingWay;
    }

    public void setShippingWay(String shippingWay) {
        this.shippingWay = shippingWay == null ? null : shippingWay.trim();
    }

    public Integer getIsBossConfirm() {
        return isBossConfirm;
    }

    public void setIsBossConfirm(Integer isBossConfirm) {
        this.isBossConfirm = isBossConfirm;
    }

    public String getCheckQty() {
        return checkQty;
    }

    public void setCheckQty(String checkQty) {
        this.checkQty = checkQty == null ? null : checkQty.trim();
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders == null ? null : orders.trim();
    }

    public String getOpenQty() {
        return openQty;
    }

    public void setOpenQty(String openQty) {
        this.openQty = openQty == null ? null : openQty.trim();
    }

    public String getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(String spendTime) {
        this.spendTime = spendTime == null ? null : spendTime.trim();
    }

    public String getCheckConclusion() {
        return checkConclusion;
    }

    public void setCheckConclusion(String checkConclusion) {
        this.checkConclusion = checkConclusion == null ? null : checkConclusion.trim();
    }

    public String getNoExecuted() {
        return noExecuted;
    }

    public void setNoExecuted(String noExecuted) {
        this.noExecuted = noExecuted == null ? null : noExecuted.trim();
    }

    public String getMeetingConclusion() {
        return meetingConclusion;
    }

    public void setMeetingConclusion(String meetingConclusion) {
        this.meetingConclusion = meetingConclusion == null ? null : meetingConclusion.trim();
    }

    public String getNotPaid() {
        return notPaid;
    }

    public void setNotPaid(String notPaid) {
        this.notPaid = notPaid == null ? null : notPaid.trim();
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }

    public String getCustomerConfirmWay() {
        return customerConfirmWay;
    }

    public void setCustomerConfirmWay(String customerConfirmWay) {
        this.customerConfirmWay = customerConfirmWay == null ? null : customerConfirmWay.trim();
    }

    public Integer getIsQualityReportEn() {
        return isQualityReportEn;
    }

    public void setIsQualityReportEn(Integer isQualityReportEn) {
        this.isQualityReportEn = isQualityReportEn;
    }

    public String getDrawbackProduct() {
        return drawbackProduct;
    }

    public void setDrawbackProduct(String drawbackProduct) {
        this.drawbackProduct = drawbackProduct == null ? null : drawbackProduct.trim();
    }

    public String getDrawbackRate() {
        return drawbackRate;
    }

    public void setDrawbackRate(String drawbackRate) {
        this.drawbackRate = drawbackRate == null ? null : drawbackRate.trim();
    }

    public String getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(String shippingFee) {
        this.shippingFee = shippingFee == null ? null : shippingFee.trim();
    }

    public String getSalesWorry() {
        return salesWorry;
    }

    public void setSalesWorry(String salesWorry) {
        this.salesWorry = salesWorry == null ? null : salesWorry.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSalesConfirm() {
        return salesConfirm;
    }

    public void setSalesConfirm(String salesConfirm) {
        this.salesConfirm = salesConfirm == null ? null : salesConfirm.trim();
    }

    public Date getSalesConfirmTime() {
        return salesConfirmTime;
    }

    public void setSalesConfirmTime(Date salesConfirmTime) {
        this.salesConfirmTime = salesConfirmTime;
    }

    public String getPurchaseConfirm() {
        return purchaseConfirm;
    }

    public void setPurchaseConfirm(String purchaseConfirm) {
        this.purchaseConfirm = purchaseConfirm == null ? null : purchaseConfirm.trim();
    }

    public Date getPurchaseConfirmTime() {
        return purchaseConfirmTime;
    }

    public void setPurchaseConfirmTime(Date purchaseConfirmTime) {
        this.purchaseConfirmTime = purchaseConfirmTime;
    }

    public String getQualityLeaderConfirm() {
        return qualityLeaderConfirm;
    }

    public void setQualityLeaderConfirm(String qualityLeaderConfirm) {
        this.qualityLeaderConfirm = qualityLeaderConfirm == null ? null : qualityLeaderConfirm.trim();
    }

    public Date getQualityLeaderConfirmTime() {
        return qualityLeaderConfirmTime;
    }

    public void setQualityLeaderConfirmTime(Date qualityLeaderConfirmTime) {
        this.qualityLeaderConfirmTime = qualityLeaderConfirmTime;
    }

    public String getPurchaseLeaderConfirm() {
        return purchaseLeaderConfirm;
    }

    public void setPurchaseLeaderConfirm(String purchaseLeaderConfirm) {
        this.purchaseLeaderConfirm = purchaseLeaderConfirm == null ? null : purchaseLeaderConfirm.trim();
    }

    public Date getPurchaseLeaderConfirmTime() {
        return purchaseLeaderConfirmTime;
    }

    public void setPurchaseLeaderConfirmTime(Date purchaseLeaderConfirmTime) {
        this.purchaseLeaderConfirmTime = purchaseLeaderConfirmTime;
    }

    public String getBossConfirm() {
        return bossConfirm;
    }

    public void setBossConfirm(String bossConfirm) {
        this.bossConfirm = bossConfirm == null ? null : bossConfirm.trim();
    }

    public Date getBossConfirmTime() {
        return bossConfirmTime;
    }

    public void setBossConfirmTime(Date bossConfirmTime) {
        this.bossConfirmTime = bossConfirmTime;
    }

	@Override
	public String toString() {
		return "ShippingConfirmation{" +
				"id=" + id +
				", projectNo='" + projectNo + '\'' +
				", serialNumber='" + serialNumber + '\'' +
				", customerName='" + customerName + '\'' +
				", productName='" + productName + '\'' +
				", shippingWay='" + shippingWay + '\'' +
				", isBossConfirm=" + isBossConfirm +
				", checkQty='" + checkQty + '\'' +
				", orders='" + orders + '\'' +
				", boxNumber='" + boxNumber + '\'' +
				", openQty='" + openQty + '\'' +
				", spendTime='" + spendTime + '\'' +
				", checkConclusion='" + checkConclusion + '\'' +
				", noExecuted='" + noExecuted + '\'' +
				", concessiveAccept='" + concessiveAccept + '\'' +
				", meetingConclusion='" + meetingConclusion + '\'' +
				", notPaid='" + notPaid + '\'' +
				", scale='" + scale + '\'' +
				", customerConfirmWay='" + customerConfirmWay + '\'' +
				", isQualityReportEn=" + isQualityReportEn +
				", drawbackProduct='" + drawbackProduct + '\'' +
				", drawbackRate='" + drawbackRate + '\'' +
				", shippingFee='" + shippingFee + '\'' +
				", salesWorry='" + salesWorry + '\'' +
				", createTime=" + createTime +
				", createPerson='" + createPerson + '\'' +
				", salesConfirm='" + salesConfirm + '\'' +
				", salesConfirmTime=" + salesConfirmTime +
				", purchaseConfirm='" + purchaseConfirm + '\'' +
				", purchaseConfirmTime=" + purchaseConfirmTime +
				", qualityLeaderConfirm='" + qualityLeaderConfirm + '\'' +
				", qualityLeaderConfirmTime=" + qualityLeaderConfirmTime +
				", purchaseLeaderConfirm='" + purchaseLeaderConfirm + '\'' +
				", purchaseLeaderConfirmTime=" + purchaseLeaderConfirmTime +
				", bossConfirm='" + bossConfirm + '\'' +
				", bossConfirmTime=" + bossConfirmTime +
				", isComplete=" + isComplete +
				", isPlastic=" + isPlastic +
				", sampleOrProduct=" + sampleOrProduct +
				", firstPerson='" + firstPerson + '\'' +
				", firstTime=" + firstTime +
				", secondPerson='" + secondPerson + '\'' +
				", secondTime=" + secondTime +
				", thirdPerson='" + thirdPerson + '\'' +
				", thirdTime=" + thirdTime +
				", fourthPerson='" + fourthPerson + '\'' +
				", fourthTime=" + fourthTime +
				", complaintId=" + complaintId +
				", sampleFileName='" + sampleFileName + '\'' +
				", sampleFileOriginalName='" + sampleFileOriginalName + '\'' +
				", isSendConfirmTask=" + isSendConfirmTask +
				", isQualityLeaderConfirm=" + isQualityLeaderConfirm +
				", shipmentAgreement='" + shipmentAgreement + '\'' +
				", projectAmount='" + projectAmount + '\'' +
				", purchaseName='" + purchaseName + '\'' +
				", sellName='" + sellName + '\'' +
				", zhijian1='" + zhijian1 + '\'' +
				", zhijian2='" + zhijian2 + '\'' +
				", zhijian3='" + zhijian3 + '\'' +
				", masterQualityInspection='" + masterQualityInspection + '\'' +
				", qualityInspector1='" + qualityInspector1 + '\'' +
				", qualityInspector2='" + qualityInspector2 + '\'' +
				", qualityInspector3='" + qualityInspector3 + '\'' +
				", qualityInspector4='" + qualityInspector4 + '\'' +
				", qualityInspector5='" + qualityInspector5 + '\'' +
				", qualityInspector6='" + qualityInspector6 + '\'' +
				", qualityInspector7='" + qualityInspector7 + '\'' +
				", customerNameProject='" + customerNameProject + '\'' +
				", comments=" + comments +
				", isSign=" + isSign +
				", amountMoney=" + amountMoney +
				", firstShipment=" + firstShipment +
				", complaint=" + complaint +
				", productChanges=" + productChanges +
				", nonPlastic=" + nonPlastic +
				", grade=" + grade +
				", qualityLeaderConfirmId=" + qualityLeaderConfirmId +
				", purchaseLeaderConfirmId=" + purchaseLeaderConfirmId +
				", bossConfirmId=" + bossConfirmId +
				", sailingDate='" + sailingDate + '\'' +
				", productNumber=" + productNumber +
				", weight='" + weight + '\'' +
				", shippingInformation='" + shippingInformation + '\'' +
				", deliveryConfirmation='" + deliveryConfirmation + '\'' +
				'}';
	}


}