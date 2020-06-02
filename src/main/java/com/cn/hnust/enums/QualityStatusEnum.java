package com.cn.hnust.enums;

/**
 * 延期原因类型
 * 
 * @author Administrator
 *
 */
public enum QualityStatusEnum {

	NO_PROBLEM(0, "没问题"), 
	RESOLVED(1, "有问题，但已经处理"),
	UNSOLVED(2, "有问题，需要采购解决");
	
	private int code;

	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;

	QualityStatusEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public static QualityStatusEnum getEnum(int code) {
		for (QualityStatusEnum sourceEnum : QualityStatusEnum.values()) {
			if (sourceEnum.getCode() == code)
				return sourceEnum;
		}
		return null;
	}
}
