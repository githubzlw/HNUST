package com.cn.hnust.pojo;

import java.io.Serializable;

public class QualityAnalysisTable implements Serializable {
    private Integer id;

    private String projectId;

    private Integer quantityDrawings;

    private Integer bomTable;

    private String maxPrecisionRequiremen;

    private String precisionGrade;

    private String maxRoughnessRequirement;

    private String surfaceTreatment;

    private String materialsAbroad;

    private String correspondingNationalStandard;

    private String listStandards;

    private String standardNotFound;

    private Integer annotation;

    private Integer assemblyRelationship;

    private String heatTreatmentRequirements;

    private String customerRequirements;

    private String designDefects;

    private String recommendedProcess;

    private String suggestCommunicationProblems;

    private String createTime;

    private String userName;
    
    private String process;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    @Override
	public String toString() {
		return "QualityAnalysisTable [id=" + id + ", projectId=" + projectId
				+ ", quantityDrawings=" + quantityDrawings + ", bomTable="
				+ bomTable + ", maxPrecisionRequiremen="
				+ maxPrecisionRequiremen + ", precisionGrade=" + precisionGrade
				+ ", maxRoughnessRequirement=" + maxRoughnessRequirement
				+ ", surfaceTreatment=" + surfaceTreatment
				+ ", materialsAbroad=" + materialsAbroad
				+ ", correspondingNationalStandard="
				+ correspondingNationalStandard + ", listStandards="
				+ listStandards + ", standardNotFound=" + standardNotFound
				+ ", annotation=" + annotation + ", assemblyRelationship="
				+ assemblyRelationship + ", heatTreatmentRequirements="
				+ heatTreatmentRequirements + ", customerRequirements="
				+ customerRequirements + ", designDefects=" + designDefects
				+ ", recommendedProcess=" + recommendedProcess
				+ ", suggestCommunicationProblems="
				+ suggestCommunicationProblems + ", createTime=" + createTime
				+ ", userName=" + userName + ", process=" + process + "]";
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public Integer getQuantityDrawings() {
        return quantityDrawings;
    }

    public void setQuantityDrawings(Integer quantityDrawings) {
        this.quantityDrawings = quantityDrawings;
    }

    public Integer getBomTable() {
        return bomTable;
    }

    public void setBomTable(Integer bomTable) {
        this.bomTable = bomTable;
    }

    public String getMaxPrecisionRequiremen() {
        return maxPrecisionRequiremen;
    }

    public void setMaxPrecisionRequiremen(String maxPrecisionRequiremen) {
        this.maxPrecisionRequiremen = maxPrecisionRequiremen == null ? null : maxPrecisionRequiremen.trim();
    }

    public String getPrecisionGrade() {
        return precisionGrade;
    }

    public void setPrecisionGrade(String precisionGrade) {
        this.precisionGrade = precisionGrade == null ? null : precisionGrade.trim();
    }

    public String getMaxRoughnessRequirement() {
        return maxRoughnessRequirement;
    }

    public void setMaxRoughnessRequirement(String maxRoughnessRequirement) {
        this.maxRoughnessRequirement = maxRoughnessRequirement == null ? null : maxRoughnessRequirement.trim();
    }

    public String getSurfaceTreatment() {
        return surfaceTreatment;
    }

    public void setSurfaceTreatment(String surfaceTreatment) {
        this.surfaceTreatment = surfaceTreatment == null ? null : surfaceTreatment.trim();
    }

    public String getMaterialsAbroad() {
        return materialsAbroad;
    }

    public void setMaterialsAbroad(String materialsAbroad) {
        this.materialsAbroad = materialsAbroad == null ? null : materialsAbroad.trim();
    }

    public String getCorrespondingNationalStandard() {
        return correspondingNationalStandard;
    }

    public void setCorrespondingNationalStandard(String correspondingNationalStandard) {
        this.correspondingNationalStandard = correspondingNationalStandard == null ? null : correspondingNationalStandard.trim();
    }

    public String getListStandards() {
        return listStandards;
    }

    public void setListStandards(String listStandards) {
        this.listStandards = listStandards == null ? null : listStandards.trim();
    }

    public String getStandardNotFound() {
        return standardNotFound;
    }

    public void setStandardNotFound(String standardNotFound) {
        this.standardNotFound = standardNotFound == null ? null : standardNotFound.trim();
    }

    public Integer getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Integer annotation) {
        this.annotation = annotation;
    }

    public Integer getAssemblyRelationship() {
        return assemblyRelationship;
    }

    public void setAssemblyRelationship(Integer assemblyRelationship) {
        this.assemblyRelationship = assemblyRelationship;
    }

    public String getHeatTreatmentRequirements() {
        return heatTreatmentRequirements;
    }

    public void setHeatTreatmentRequirements(String heatTreatmentRequirements) {
        this.heatTreatmentRequirements = heatTreatmentRequirements == null ? null : heatTreatmentRequirements.trim();
    }

    public String getCustomerRequirements() {
        return customerRequirements;
    }

    public void setCustomerRequirements(String customerRequirements) {
        this.customerRequirements = customerRequirements == null ? null : customerRequirements.trim();
    }

    public String getDesignDefects() {
        return designDefects;
    }

    public void setDesignDefects(String designDefects) {
        this.designDefects = designDefects == null ? null : designDefects.trim();
    }

    public String getRecommendedProcess() {
        return recommendedProcess;
    }

    public void setRecommendedProcess(String recommendedProcess) {
        this.recommendedProcess = recommendedProcess == null ? null : recommendedProcess.trim();
    }

    public String getSuggestCommunicationProblems() {
        return suggestCommunicationProblems;
    }

    public void setSuggestCommunicationProblems(String suggestCommunicationProblems) {
        this.suggestCommunicationProblems = suggestCommunicationProblems == null ? null : suggestCommunicationProblems.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}