package com.cn.hnust.enums;
/**
 * 客户投诉严重等级
 * @author Administrator
 *
 */
public enum SeriousLevelEnum {
	/**
	 * 客户索赔
	 */
	CLAIMER(4,"客户索赔"),
	
	/**
	 * 退货
	 */
	RETURNED(3,"退货"),
	
	/**
	 * 严重抱怨
	 */
	SERIOUS_COMPLAINT(2,"严重抱怨"),
	
	/**
	 * 一般抱怨
	 */
	NORMAL_COMPLAINT(1,"一般抱怨");
	
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	SeriousLevelEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static SeriousLevelEnum getEnum(int code) {
		for(SeriousLevelEnum sourceEnum:  SeriousLevelEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
