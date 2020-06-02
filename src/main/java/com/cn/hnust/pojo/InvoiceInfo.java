package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class InvoiceInfo implements Serializable {
    private String id;
    private String caseNo;
    private Date ifdate;
    private int  customercode;
    private int singleNumber;//返单个数
    private int subscribers;//客户数
    private String customerManager;//项目成员
    private int newCustomerProofingNumber;//新客户打样项目数
    private double newCustomerProofingMoney;//新客户打样金额
    private int newCustomerBulkNumber;//新客户大货单数
    private double newCustomerBulkMoney;//新客户大货单金额
    private int customerProofingNumber;//客户打样项目数
    private double customerProofingMoney;//客户打样金额
    private int customerBulkNumber;//客户大货单数
    private double customerBulkMoney;//客户大货单金额
    private int returnItemNumber;//返单项目数
    private double returnItemMoney;//返单项目金额
    private String startTime;//起始时间
    private String endTime;//截止时间
    
    
	
	public int getCustomercode() {
		return customercode;
	}
	public void setCustomercode(int customercode) {
		this.customercode = customercode;
	}
	public int getNewCustomerProofingNumber() {
		return newCustomerProofingNumber;
	}
	public void setNewCustomerProofingNumber(int newCustomerProofingNumber) {
		this.newCustomerProofingNumber = newCustomerProofingNumber;
	}
	public double getNewCustomerProofingMoney() {
		return newCustomerProofingMoney;
	}
	public void setNewCustomerProofingMoney(double newCustomerProofingMoney) {
		this.newCustomerProofingMoney = newCustomerProofingMoney;
	}
	public int getNewCustomerBulkNumber() {
		return newCustomerBulkNumber;
	}
	public void setNewCustomerBulkNumber(int newCustomerBulkNumber) {
		this.newCustomerBulkNumber = newCustomerBulkNumber;
	}
	public double getNewCustomerBulkMoney() {
		return newCustomerBulkMoney;
	}
	public void setNewCustomerBulkMoney(double newCustomerBulkMoney) {
		this.newCustomerBulkMoney = newCustomerBulkMoney;
	}
	public int getCustomerProofingNumber() {
		return customerProofingNumber;
	}
	public void setCustomerProofingNumber(int customerProofingNumber) {
		this.customerProofingNumber = customerProofingNumber;
	}
	public double getCustomerProofingMoney() {
		return customerProofingMoney;
	}
	public void setCustomerProofingMoney(double customerProofingMoney) {
		this.customerProofingMoney = customerProofingMoney;
	}
	public int getCustomerBulkNumber() {
		return customerBulkNumber;
	}
	public void setCustomerBulkNumber(int customerBulkNumber) {
		this.customerBulkNumber = customerBulkNumber;
	}
	public double getCustomerBulkMoney() {
		return customerBulkMoney;
	}
	public void setCustomerBulkMoney(double customerBulkMoney) {
		this.customerBulkMoney = customerBulkMoney;
	}
	public int getReturnItemNumber() {
		return returnItemNumber;
	}
	public void setReturnItemNumber(int returnItemNumber) {
		this.returnItemNumber = returnItemNumber;
	}
	public double getReturnItemMoney() {
		return returnItemMoney;
	}
	public void setReturnItemMoney(double returnItemMoney) {
		this.returnItemMoney = returnItemMoney;
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
	
	public String getCustomerManager() {
		return customerManager;
	}
    public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}
    public int getSingleNumber() {
		return singleNumber;
	}
    public void setSingleNumber(int singleNumber) {
		this.singleNumber = singleNumber;
	}

	public int getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(int subscribers) {
		this.subscribers = subscribers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public Date getIfdate() {
		return ifdate;
	}

	public void setIfdate(Date ifdate) {
		this.ifdate = ifdate;
	}

	@Override
	public String toString() {
		return "InvoiceInfo [id=" + id + ", caseNo=" + caseNo + ", ifdate="
				+ ifdate + ", customercode=" + customercode + ", singleNumber="
				+ singleNumber + ", subscribers=" + subscribers
				+ ", customerManager=" + customerManager
				+ ", newCustomerProofingNumber=" + newCustomerProofingNumber
				+ ", newCustomerProofingMoney=" + newCustomerProofingMoney
				+ ", newCustomerBulkNumber=" + newCustomerBulkNumber
				+ ", newCustomerBulkMoney=" + newCustomerBulkMoney
				+ ", customerProofingNumber=" + customerProofingNumber
				+ ", customerProofingMoney=" + customerProofingMoney
				+ ", customerBulkNumber=" + customerBulkNumber
				+ ", customerBulkMoney=" + customerBulkMoney
				+ ", returnItemNumber=" + returnItemNumber
				+ ", returnItemMoney=" + returnItemMoney + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

}
 
   