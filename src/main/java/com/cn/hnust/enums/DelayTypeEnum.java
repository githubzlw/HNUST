package com.cn.hnust.enums;
/**
 * 延期原因类型
 * @author Administrator
 *
 */
public enum DelayTypeEnum {
	/**
	 *  默认(客户原因)
	 */
	DEFAULT(0,"客户原因"),
	
	/**
	 * 采购原因
	 */
	PURCHASE(1,"采购原因");
	
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	DelayTypeEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static DelayTypeEnum getEnum(int code) {
		for(DelayTypeEnum sourceEnum:  DelayTypeEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
