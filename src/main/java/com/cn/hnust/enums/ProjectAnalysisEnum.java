package com.cn.hnust.enums;
/**
 * 项目阶段常量类
 * @author Administrator
 *
 */
public enum ProjectAnalysisEnum {
	/**
	 *  默认无
	 */
	DEFAULT(0,"无"),
	
	
	/**
	 * 
	 * 
	 */
	A(1,"A"),
	
	
	B(2,"B"),
	
	C(3,"C");
	
	
	
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	ProjectAnalysisEnum(int code, String value){ 
		this.code = code;
		this.value = value;
	}
	
	public static ProjectAnalysisEnum getEnum(int code) {
		for(ProjectAnalysisEnum sourceEnum:  ProjectAnalysisEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
