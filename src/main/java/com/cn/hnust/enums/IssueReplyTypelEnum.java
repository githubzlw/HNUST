package com.cn.hnust.enums;
/**
 * 消息回复类型
 * @author Administrator
 *
 */
public enum IssueReplyTypelEnum {
	/**
	 * 采购回复
	 */
	PURCHASE_REPLY(1,"采购回复"),
	
	/**
	 * 质检回复
	 */
	INSPECTION_REPLY(2,"质检回复"),
	/**
	 * 技术回复
	 */
	TECHNICIAN_REPLY(3,"技术回复"),
	
	/**
	 * 整改回复
	 */
	RECTIFICATION_REPLY(4,"整改回复");
	
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	IssueReplyTypelEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static IssueReplyTypelEnum getEnum(int code) {
		for(IssueReplyTypelEnum sourceEnum:  IssueReplyTypelEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
