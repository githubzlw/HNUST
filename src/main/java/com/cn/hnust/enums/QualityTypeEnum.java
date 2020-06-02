package com.cn.hnust.enums;

/**
 * 延期原因类型
 * 
 * @author Administrator
 *
 */
public enum QualityTypeEnum {

	SAMPLE(0, "样品检验"), 
	LARGE_CARGO(1, "大货样品"),
	METAPHASE(2, "中期检验"),
	FINAL_TIME (3, "终期检验");
	
	private int code;

	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;

	QualityTypeEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public static QualityTypeEnum getEnum(int code) {
		for (QualityTypeEnum sourceEnum : QualityTypeEnum.values()) {
			if (sourceEnum.getCode() == code)
				return sourceEnum;
		}
		return null;
	}
}
