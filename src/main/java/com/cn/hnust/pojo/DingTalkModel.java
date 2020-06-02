package com.cn.hnust.pojo;

public class DingTalkModel {
     
	private String id;
	private String receiver;     //接收人
	private String dingTalkId;   //接收人钉钉id
	private String description;  //任务描述
	private String projectNo;    //项目号
	private String type;        //消息类型
	
	
	
	
	public DingTalkModel(String id, String receiver, String dingTalkId,
			String description, String projectNo, String type) {
		super();
		this.id = id;
		this.receiver = receiver;
		this.dingTalkId = dingTalkId;
		this.description = description;
		this.projectNo = projectNo;
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getDingTalkId() {
		return dingTalkId;
	}
	public void setDingTalkId(String dingTalkId) {
		this.dingTalkId = dingTalkId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "DingTalkModel [id=" + id + ", receiver=" + receiver
				+ ", dingTalkId=" + dingTalkId + ", description=" + description
				+ ", projectNo=" + projectNo + ", type=" + type + "]";
	}
	
	
	
	
	
}
