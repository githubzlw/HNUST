package com.cn.hnust.enums;
/**
 * 订单详情状态
 * @author Administrator
 *
 */
public enum DetailStatusEnum {
	
	
	//1：一切正常    2:重新打样 3：打样失败 4：反复沟通 5：样品没确认 6：样品已确认 7：其他问题(老的状态)
	
	//新状态 1：正常生产中 2:返工重做中 4：悬而未决中
	/**
	 * 一切正常
	 */
	NORMAL(1,"正常生产中"),
	/**
	 * 返工重做中
	 */
	RE_SAMPLE(2,"返工重做中"),
	/**
	 * 重新打样
	 */
	SAMPLE_FAILED(3,"打样失败"),
	/**
	 * 反复沟通
	 */
	COMMUNICATION(4,"悬而未决中"),
	
	NO_CONFIRM_SAMPLE(5,"样品没确认"),
	
	CONFIRM_SAMPLE(6,"样品已确认"),
	
	OTHER(7,"其他问题");


	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	DetailStatusEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static DetailStatusEnum getEnum(int code) {
		for(DetailStatusEnum sourceEnum:  DetailStatusEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
