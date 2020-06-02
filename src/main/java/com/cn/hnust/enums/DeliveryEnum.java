package com.cn.hnust.enums;
/**
 * 添加常量类(交期状态)
 * @author Administrator
 *
 */
public enum DeliveryEnum {
	/**
	 *  准时默认
	 */
	DEFAULT(0,"正常"),
	
	/**
	 * 延期
	 */
	OVERDUE(1,"延期"),
	
	/**
	 * 暂停
	 */
	PAUSE(2,"暂停");
	
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	DeliveryEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static DeliveryEnum getEnum(int code) {
		for(DeliveryEnum sourceEnum:  DeliveryEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
