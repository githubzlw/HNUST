package com.cn.hnust.enums;
/**
 * 任务状态常量类
 * @author Administrator
 *
 */
public enum TaskStatusEnum {
	/**
	 *  默认(未完成)
	 */
	DEFAULT(0,"未完成"),
	
	/**
	 * 完成
	 */
	FINISH(1,"完成"),
	
	/**
	 *  暂停(暂停)
	 */
	PAUSE(2,"暂停"),
	/**
	 *  取消
	 */
	CANCEL(3,"取消");
	
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	TaskStatusEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static TaskStatusEnum getEnum(int code) {
		for(TaskStatusEnum sourceEnum:  TaskStatusEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
