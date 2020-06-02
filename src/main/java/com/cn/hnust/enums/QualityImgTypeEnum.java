package com.cn.hnust.enums;
/**
 * 质检图片类型
 * @author polo
 *
 */
public enum QualityImgTypeEnum {
	/**
	 *  细节图片
	 */
	DETAIL_IMG(1,"细节图片"),
	/**
	 *  不良品图片
	 */
	BAD_IMG(2,"不良品图片"),
	/**
	 *  材质图片
	 */
	MATERIAL_IMG(3,"材质图片"),
	
	/**
	 * 包装图片
	 */
	PACKAGE_IMG(4,"包装图片"),
	/**
	 * 检验表格
	 */
	TABLE_IMG(5,"检验表格");
	
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	QualityImgTypeEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static QualityImgTypeEnum getEnum(int code) {
		for(QualityImgTypeEnum sourceEnum:  QualityImgTypeEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
