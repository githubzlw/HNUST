package com.cn.hnust.enums;
/**
 * 项目状态常量类
 * @author Administrator
 *
 */
public enum ProjectStatusEnum {
	/**
	 *  默认(未完成)
	 */
	DEFAULT(0,"未完成"),
	
	/**
	 * 完成
	 */
	FINISH(1,"完成");
	
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	ProjectStatusEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static ProjectStatusEnum getEnum(int code) {
		for(ProjectStatusEnum sourceEnum:  ProjectStatusEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
