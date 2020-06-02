package com.cn.hnust.enums;
/**
 * 订单状态
 * @author Administrator
 *
 */
public enum OrderStatusEnum {
	/**
	 * 新立项项目
	 */
	NEW_ORDER(0,"新立项项目"),
	/**
	 * 正常订单
	 */
	NORMAL_ORDER(1,"正在进行的项目"),
	/**
	 * 大货完结项目
	 */
	COMPLETE_ORDER(2,"大货完结项目"),
	/**
	 * 延期项目
	 */
	DELAY_ORDER(3,"延期项目"),
	/**
	 * 暂停项目
	 */
	PAUSE_ORDER(4,"暂停项目"),
	

	CANCEL_ORDER(5,"取消项目"),
	
	
	SAMPLE_ORDER(6,"样品完结");


	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	OrderStatusEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static OrderStatusEnum getEnum(int code) {
		for(OrderStatusEnum sourceEnum:  OrderStatusEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
