package com.cn.hnust.pojo;

import java.io.Serializable;

public class MaterialList implements Serializable {
    private Integer id;

    private Integer qualityId;

    private Integer materialSelection;

    private String specificLicensePlate;

    private Integer materialJudgement;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQualityId() {
        return qualityId;
    }

    public void setQualityId(Integer qualityId) {
        this.qualityId = qualityId;
    }

    public Integer getMaterialSelection() {
        return materialSelection;
    }

    public void setMaterialSelection(Integer materialSelection) {
        this.materialSelection = materialSelection;
    }

    public String getSpecificLicensePlate() {
        return specificLicensePlate;
    }

    public void setSpecificLicensePlate(String specificLicensePlate) {
        this.specificLicensePlate = specificLicensePlate == null ? null : specificLicensePlate.trim();
    }

    public Integer getMaterialJudgement() {
        return materialJudgement;
    }

    public void setMaterialJudgement(Integer materialJudgement) {
        this.materialJudgement = materialJudgement;
    }

	@Override
	public String toString() {
		return "MaterialList [id=" + id + ", qualityId=" + qualityId
				+ ", materialSelection=" + materialSelection
				+ ", specificLicensePlate=" + specificLicensePlate
				+ ", materialJudgement=" + materialJudgement + "]";
	}
    
}