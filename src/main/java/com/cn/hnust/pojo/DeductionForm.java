package com.cn.hnust.pojo;

import java.io.Serializable;
//每月扣分表
public class DeductionForm implements Serializable {
    private Integer id;
     //名字
    private String name;
     //扣分次数
    private Integer deductionFrequency;
    //日期
    private String date;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDeductionFrequency() {
        return deductionFrequency;
    }

    public void setDeductionFrequency(Integer deductionFrequency) {
        this.deductionFrequency = deductionFrequency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
}