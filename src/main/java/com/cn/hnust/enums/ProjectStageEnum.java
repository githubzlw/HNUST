package com.cn.hnust.enums;
/**
 * 项目阶段常量类
 * @author Administrator
 *
 */
public enum ProjectStageEnum {
	/**
	 *  默认无
	 */
	DEFAULT(0,"样品"),
	
	
	/**
	 * 
	 * 
	 */
	MOULD(1,"模具"),
	
	/**
	 * 大货
	 */
	LARGECARGO(2,"大货"),
	
	/**
	 * 成熟
	 */
	MATURE(3,"成熟");
	
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	ProjectStageEnum(int code, String value){ 
		this.code = code;
		this.value = value;
	}
	
	public static ProjectStageEnum getEnum(int code) {
		for(ProjectStageEnum sourceEnum:  ProjectStageEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
