package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class FactoryScore implements Serializable {
    private Integer id;          

    private String projectNo;       //项目号

    private String factoryName;     //工厂名

    private Integer score;          //评分数（1-5分）

    private String scorer;          //评分人

    private Date scoreTime;         //评分时间

    private static final long serialVersionUID = 1L;

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

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getScorer() {
        return scorer;
    }

    public void setScorer(String scorer) {
        this.scorer = scorer == null ? null : scorer.trim();
    }

    public Date getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(Date scoreTime) {
        this.scoreTime = scoreTime;
    }

	@Override
	public String toString() {
		return "FactoryScore [id=" + id + ", projectNo=" + projectNo
				+ ", factoryName=" + factoryName + ", score=" + score
				+ ", scorer=" + scorer + ", scoreTime=" + scoreTime + "]";
	}
    
    
    
    
}