package com.cn.hnust.pojo;

import java.util.Date;

/***
 * 出运单主表数据
 * @author Administrator
 *
 */
public class Products {
	private String id;

    private String projectNo;   //项目号

    private String sailingDate;   //船运时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getSailingDate() {
		return sailingDate;
	}

	public void setSailingDate(String sailingDate) {
		this.sailingDate = sailingDate;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", projectNo=" + projectNo
				+ ", sailingDate=" + sailingDate + "]";
	}


}
