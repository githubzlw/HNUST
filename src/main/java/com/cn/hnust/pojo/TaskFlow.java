package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TaskFlow implements Serializable {
    private Integer id;

    private Integer conditionType;  //条件

    private Integer triggerId;

    private Date updateTime;
    
    private String conditionName;  //条件名称
    
    private String taskTitle;     //触发任务
    
    private int  roleType;
    
    private String roleName; //任务绑定的角色
    
    private List<TaskFlowDetail> tfDetails;
    
    /** 项目等级*/
    private int plantAnalysis;
    /** 项目材料属性*/
    private int projectMaterialProperties;
    
    
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConditionType() {
        return conditionType;
    }

    public void setConditionType(Integer conditionType) {
        this.conditionType = conditionType;
    }

    public Integer getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(Integer triggerId) {
        this.triggerId = triggerId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public List<TaskFlowDetail> getTfDetails() {
		return tfDetails;
	}

	public void setTfDetails(List<TaskFlowDetail> tfDetails) {
		this.tfDetails = tfDetails;
	}

	public int getPlantAnalysis() {
		return plantAnalysis;
	}

	public void setPlantAnalysis(int plantAnalysis) {
		this.plantAnalysis = plantAnalysis;
	}

	public int getProjectMaterialProperties() {
		return projectMaterialProperties;
	}

	public void setProjectMaterialProperties(int projectMaterialProperties) {
		this.projectMaterialProperties = projectMaterialProperties;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}