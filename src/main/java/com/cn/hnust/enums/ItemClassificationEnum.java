package com.cn.hnust.enums;

public enum ItemClassificationEnum {
	/**
	 * 最近半年新增邮件
	 */
	NEW_ADDITIONS(0,"最近半年新增邮件"),
	/**
	 * 没有变化的项目
	 */
	NO_CHANGE_PROJECT(1,"没有变化的项目"),
	/**
	 * 减少的项目
	 */
	REDUCTION_Projects(2,"减少的项目");
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	ItemClassificationEnum(int code, String value){ 
		this.code = code;
		this.value = value;
	}
	
	public static ItemClassificationEnum getEnum(int code) {
		for(ItemClassificationEnum sourceEnum:  ItemClassificationEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}

	
}
