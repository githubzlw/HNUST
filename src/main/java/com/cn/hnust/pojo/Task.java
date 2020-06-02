package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/***
 * 发布任务对象
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class Task implements Serializable {
    private String id;

    private String projectNo;

    private String taskDemand;

    private Integer node;

    private Date endDate;

    private Integer status;//是否完成

    private Date createDate;
    
    /**采购的名字*/
    private String roleName;
    
    /**关键字查询*/
    private String inputKey;
    
    private String purchaseNameId;//通过采购名字
    
    private List<TaskReport> taskReportList;
    
    private int emailUserId;
    
    private int purchaseId;
    
    private String purchaseName;
    
    private String sellName;
    
    private String operator;//任务发布人
    
    private Integer type;//未读已读
    
    private String searchType;// 查询类型(查询列表还是,未读任务的数量)
    
    private String userName;
    
    private int roleNo;
    
    public int getEmailUserId() {
		return emailUserId;
	}

	public void setEmailUserId(int emailUserId) {
		this.emailUserId = emailUserId;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getTaskDemand() {
        return taskDemand;
    }

    public void setTaskDemand(String taskDemand) {
        this.taskDemand = taskDemand == null ? null : taskDemand.trim();
    }

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getInputKey() {
		return inputKey;
	}

	public void setInputKey(String inputKey) {
		this.inputKey = inputKey;
	}

	public List<TaskReport> getTaskReportList() {
		return taskReportList;
	}

	public void setTaskReportList(List<TaskReport> taskReportList) {
		this.taskReportList = taskReportList;
	}

	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getPurchaseNameId() {
		return purchaseNameId;
	}

	public void setPurchaseNameId(String purchaseNameId) {
		this.purchaseNameId = purchaseNameId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(int roleNo) {
		this.roleNo = roleNo;
	}
	
	
	
}