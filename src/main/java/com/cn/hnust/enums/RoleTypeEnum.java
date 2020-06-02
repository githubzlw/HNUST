package com.cn.hnust.enums;

/**
 * 延期原因类型
 * 
 * @author Administrator
 *
 */
public enum RoleTypeEnum {

	DRAFTS(1, "绘图"), 
	TECHNICAL_ANALYSIS(2, "技术分析"),
	PACKAGING_ANALYSIS(3, "包装分析"),
	QUALITY(4,"质检"),
	PURCHASE(5,"采购"),
	SALE(6,"销售"),
	DOCUMENTARY(7,"跟单"),
	SALE_ASSISTANT(8,"销售部助理"),
	CEO_ASSISTANT(9,"总裁助理"),
	PURCHASE_ASSISTANT(10,"采购部助理"),
	QUOTATION_CLERK(11,"报价员"),
	PLASTIC_TECHNICAL_ANALYSIS(12,"技术分析（塑料类）"),
	METAL_QUALITY(13,"质检（金属类）");
	
	
	


	private int code;

	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;

	RoleTypeEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public static RoleTypeEnum getEnum(int code) {
		for (RoleTypeEnum sourceEnum : RoleTypeEnum.values()) {
			if (sourceEnum.getCode() == code)
				return sourceEnum;
		}
		return null;
	}
}
