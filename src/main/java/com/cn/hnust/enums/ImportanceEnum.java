package com.cn.hnust.enums;
/**
 * 項目重要性常量
 * @author Administrator
 *
 */
public enum ImportanceEnum {
	/**
	 *  默认(一般)
	 */
	DEFAULT(0,"一般"),
	
	/**
	 * 重要
	 */
	PAUSE(1,"重要");
	
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	ImportanceEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static ImportanceEnum getEnum(int code) {
		for(ImportanceEnum sourceEnum:  ImportanceEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
