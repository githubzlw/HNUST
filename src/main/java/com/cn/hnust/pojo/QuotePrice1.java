package com.cn.hnust.pojo;

import java.io.Serializable;

public class QuotePrice1 implements Serializable {
   
	/** id*/
	private int id;
	/** 项目号 */
	private String caseNo;
	/** 录入人*/
	private String employeeName;
	/** 内容*/
	private String currentStatus;
	/** 启动时间*/
	private String updateTime;
	
	/** 附件*/
	private String uploadUrl;
	
	/** 编号*/
	private int kuzhizao;
	
	
	private int dingding;
	
	
	public int getDingding() {
		return dingding;
	}
	public void setDingding(int dingding) {
		this.dingding = dingding;
	}
	public String getUploadUrl() {
		return uploadUrl;
	}
	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}
	public int getKuzhizao() {
		return kuzhizao;
	}
	public void setKuzhizao(int kuzhizao) {
		this.kuzhizao = kuzhizao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "QuotePrice1 [id=" + id + ", caseNo=" + caseNo
				+ ", employeeName=" + employeeName + ", currentStatus="
				+ currentStatus + ", updateTime=" + updateTime + ", uploadUrl="
				+ uploadUrl + ", kuzhizao=" + kuzhizao + ", dingding="
				+ dingding + "]";
	}
}