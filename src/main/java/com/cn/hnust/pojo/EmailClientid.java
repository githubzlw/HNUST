package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author Administrator
 *
 */
public class EmailClientid implements Serializable {
    private Integer id;

    private Integer erpClientId;

    private Integer emailClientId;

    private Integer projectstatus;

    private Integer saleId;

    private Integer saleId1;

    private String projectId;

    private Integer saleId2;

    private Integer saleId3;

    private Integer saleId4;

    private Integer saleId5;

    private Integer time;

    private String projectDescc;

    private String projectDesce;

    private Date createTime;

    private Integer blacklist;

    private String cusquotetime;

    private String valueorder;

    private String annualorder;

    private Integer quoter;

    private Integer quoter1;

    private Integer quoter2;

    private Integer quoter3;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getErpClientId() {
        return erpClientId;
    }

    public void setErpClientId(Integer erpClientId) {
        this.erpClientId = erpClientId;
    }

    public Integer getEmailClientId() {
        return emailClientId;
    }

    public void setEmailClientId(Integer emailClientId) {
        this.emailClientId = emailClientId;
    }

    public Integer getProjectstatus() {
        return projectstatus;
    }

    public void setProjectstatus(Integer projectstatus) {
        this.projectstatus = projectstatus;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getSaleId1() {
        return saleId1;
    }

    public void setSaleId1(Integer saleId1) {
        this.saleId1 = saleId1;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public Integer getSaleId2() {
        return saleId2;
    }

    public void setSaleId2(Integer saleId2) {
        this.saleId2 = saleId2;
    }

    public Integer getSaleId3() {
        return saleId3;
    }

    public void setSaleId3(Integer saleId3) {
        this.saleId3 = saleId3;
    }

    public Integer getSaleId4() {
        return saleId4;
    }

    public void setSaleId4(Integer saleId4) {
        this.saleId4 = saleId4;
    }

    public Integer getSaleId5() {
        return saleId5;
    }

    public void setSaleId5(Integer saleId5) {
        this.saleId5 = saleId5;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Integer blacklist) {
        this.blacklist = blacklist;
    }

    public String getCusquotetime() {
        return cusquotetime;
    }

    public void setCusquotetime(String cusquotetime) {
        this.cusquotetime = cusquotetime == null ? null : cusquotetime.trim();
    }

    public String getValueorder() {
        return valueorder;
    }

    public void setValueorder(String valueorder) {
        this.valueorder = valueorder == null ? null : valueorder.trim();
    }

    public String getAnnualorder() {
        return annualorder;
    }

    public void setAnnualorder(String annualorder) {
        this.annualorder = annualorder == null ? null : annualorder.trim();
    }

    public Integer getQuoter() {
        return quoter;
    }

    public void setQuoter(Integer quoter) {
        this.quoter = quoter;
    }

    public Integer getQuoter1() {
        return quoter1;
    }

    public void setQuoter1(Integer quoter1) {
        this.quoter1 = quoter1;
    }

    public Integer getQuoter2() {
        return quoter2;
    }

    public void setQuoter2(Integer quoter2) {
        this.quoter2 = quoter2;
    }

    public Integer getQuoter3() {
        return quoter3;
    }

    public void setQuoter3(Integer quoter3) {
        this.quoter3 = quoter3;
    }
}