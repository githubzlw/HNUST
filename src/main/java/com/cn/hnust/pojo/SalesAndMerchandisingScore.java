package com.cn.hnust.pojo;

import java.io.Serializable;

public class SalesAndMerchandisingScore implements Serializable {
    private Integer id;
     //销售或跟单
    private String saleName;
     //总分
    private Double totalScore;
    //日期
    private String date;
    //老客户报价成功率
    private Double oldCustomerOff;
     //新客户大项目报价成功率
    private Double newCustomerBigProjectOffer;
    //老客户大项目报价成功率
    private Double oldCustomerBigProjectOffer;
    //排名
    private Double ranking;
    //增长
    private Double increase;
    //角色
    private String roleName;
  //流程打分
    private Double technologicalProcess;
  //质量打分
    private Double quality;
  //交期打分
    private Double deliveryTime;
  //返单率
    private Double customerFollowUp;
  //角色
    private Double profit;
  //扣分次数
    private int number;
    private static final long serialVersionUID = 1L;

    

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName == null ? null : saleName.trim();
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Double getOldCustomerOff() {
        return oldCustomerOff;
    }

    public void setOldCustomerOff(Double oldCustomerOff) {
        this.oldCustomerOff = oldCustomerOff;
    }

    public Double getNewCustomerBigProjectOffer() {
        return newCustomerBigProjectOffer;
    }

    public void setNewCustomerBigProjectOffer(Double newCustomerBigProjectOffer) {
        this.newCustomerBigProjectOffer = newCustomerBigProjectOffer;
    }

    public Double getOldCustomerBigProjectOffer() {
        return oldCustomerBigProjectOffer;
    }

    public void setOldCustomerBigProjectOffer(Double oldCustomerBigProjectOffer) {
        this.oldCustomerBigProjectOffer = oldCustomerBigProjectOffer;
    }

    public Double getRanking() {
        return ranking;
    }

    public void setRanking(Double ranking) {
        this.ranking = ranking;
    }

    public Double getIncrease() {
        return increase;
    }

    public void setIncrease(Double increase) {
        this.increase = increase;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Double getTechnologicalProcess() {
        return technologicalProcess;
    }

    public void setTechnologicalProcess(Double technologicalProcess) {
        this.technologicalProcess = technologicalProcess;
    }

    public Double getQuality() {
        return quality;
    }

    public void setQuality(Double quality) {
        this.quality = quality;
    }

    public Double getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Double deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Double getCustomerFollowUp() {
        return customerFollowUp;
    }

    public void setCustomerFollowUp(Double customerFollowUp) {
        this.customerFollowUp = customerFollowUp;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}