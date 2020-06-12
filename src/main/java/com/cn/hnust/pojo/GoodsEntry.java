package com.cn.hnust.pojo;

import java.util.List;

import lombok.Data;

/**
 * Description:货物认领表单
 *
 * @author : Administrator
 * @date : 2020-05-27
 */
@Data
public class GoodsEntry {
	private String goodsImg;
	private String textCount;
	private String goodsWeight;
	private String sendUser;
	private String projectNumber;
	private String claimUser;
	private String createTime;
	private String finishTime;
	private String businessId;
	private String processInstanceResult;
	private String status;
	private String processInstanceId;
	private String originatorUserid;
	private String originatorDeptId;
	private String bagCount;
	private String goodsCount;
	private String goodsUnit;
	private String cpprovalUserid;
	private String checkNumber;
	private String recipient;

	private List<String> imgs;

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public String getTextCount() {
		return textCount;
	}

	public void setTextCount(String textCount) {
		this.textCount = textCount;
	}

	public String getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public String getSendUser() {
		return sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getClaimUser() {
		return claimUser;
	}

	public void setClaimUser(String claimUser) {
		this.claimUser = claimUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getProcessInstanceResult() {
		return processInstanceResult;
	}

	public void setProcessInstanceResult(String processInstanceResult) {
		this.processInstanceResult = processInstanceResult;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getOriginatorUserid() {
		return originatorUserid;
	}

	public void setOriginatorUserid(String originatorUserid) {
		this.originatorUserid = originatorUserid;
	}

	public String getOriginatorDeptId() {
		return originatorDeptId;
	}

	public void setOriginatorDeptId(String originatorDeptId) {
		this.originatorDeptId = originatorDeptId;
	}

	public String getBagCount() {
		return bagCount;
	}

	public void setBagCount(String bagCount) {
		this.bagCount = bagCount;
	}

	public String getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getCpprovalUserid() {
		return cpprovalUserid;
	}

	public void setCpprovalUserid(String cpprovalUserid) {
		this.cpprovalUserid = cpprovalUserid;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
}
