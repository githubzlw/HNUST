package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class PayOthers implements Serializable {
    private Integer id;

    private String caseno;

    private Double applicationAmount;

    private Integer imoneytype;

    private Date mailingDate;

    private Date entryDate;

    private String paymentType;

    private Integer paymentDepartment;

    private String paymentInstructions;

    private Integer passErp;

    private String processInstanceId;

    private String applicant;

    private Integer reminder;

    private String processInstanceResult;

    private String status;

    private Date finishTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaseno() {
        return caseno;
    }

    public void setCaseno(String caseno) {
        this.caseno = caseno == null ? null : caseno.trim();
    }

    public Double getApplicationAmount() {
        return applicationAmount;
    }

    public void setApplicationAmount(Double applicationAmount) {
        this.applicationAmount = applicationAmount;
    }

    public Integer getImoneytype() {
        return imoneytype;
    }

    public void setImoneytype(Integer imoneytype) {
        this.imoneytype = imoneytype;
    }

    public Date getMailingDate() {
        return mailingDate;
    }

    public void setMailingDate(Date mailingDate) {
        this.mailingDate = mailingDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public Integer getPaymentDepartment() {
        return paymentDepartment;
    }

    public void setPaymentDepartment(Integer paymentDepartment) {
        this.paymentDepartment = paymentDepartment;
    }

    public String getPaymentInstructions() {
        return paymentInstructions;
    }

    public void setPaymentInstructions(String paymentInstructions) {
        this.paymentInstructions = paymentInstructions == null ? null : paymentInstructions.trim();
    }

    public Integer getPassErp() {
        return passErp;
    }

    public void setPassErp(Integer passErp) {
        this.passErp = passErp;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId == null ? null : processInstanceId.trim();
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
    }

    public Integer getReminder() {
        return reminder;
    }

    public void setReminder(Integer reminder) {
        this.reminder = reminder;
    }

    public String getProcessInstanceResult() {
        return processInstanceResult;
    }

    public void setProcessInstanceResult(String processInstanceResult) {
        this.processInstanceResult = processInstanceResult == null ? null : processInstanceResult.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}