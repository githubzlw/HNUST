package com.cn.hnust.pojo;

public class Bargain {
    private int id;

    private String caseNo;

    private String bargainNo;

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

    public String getBargainNo() {
        return bargainNo;
    }

    public void setBargainNo(String bargainNo) {
        this.bargainNo = bargainNo;
    }

    @Override
    public String toString() {
        return "Bargain{" +
                "id=" + id +
                ", caseNo='" + caseNo + '\'' +
                ", bargainNo='" + bargainNo + '\'' +
                '}';
    }
}
