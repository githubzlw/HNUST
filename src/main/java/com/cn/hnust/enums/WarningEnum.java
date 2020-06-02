package com.cn.hnust.enums;
/***
 * 警告信息常量类
 * @author Administrator
 *
 */
public enum WarningEnum {
	/**
	 *  默认无
	 */
	DEFAULT(0,"无"),
	
	/**
	 * 质量投诉
	 */
	COMPLAIN(1,"质量投诉"),
	
	/**
	 * 交期预警
	 */
	DELIVERYWARNING(2,"交期预警");
	
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	WarningEnum(int code, String value){ 
		this.code = code;
		this.value = value;
	}
	
	public static WarningEnum getEnum(int code) {
		for(WarningEnum sourceEnum:  WarningEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
