package com.cn.hnust.enums;
/**
 * 任务节点常量类
 * @author Administrator
 *
 */
public enum TaskNodeEnum {
	/**
	 *  默认无
	 */
	DEFAULT(0,"无"),
	/**
	 *  样品交货
	 */
    SAMPLE(1,"样品交货"),
	/**
	 * 大货交货
	 */
	LARGECARGO(2,"大货交货");
	
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	TaskNodeEnum(int code, String value){ 
		this.code = code;
		this.value = value;
	}
	
	public static TaskNodeEnum getEnum(int code) {
		for(TaskNodeEnum sourceEnum:  TaskNodeEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
