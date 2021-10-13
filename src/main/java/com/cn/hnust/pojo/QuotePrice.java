package com.cn.hnust.pojo;

public class QuotePrice {
	 private Integer id;

	    private String projectNo;

	private String employeName;

	private String currentStatus;

	private String updateTime;

	private String uploadurl;

	private String dingding;

	private String redirectUser;//转交人

	/**
	 * 编号
	 */
	private int kuzhizao;

	private static final long serialVersionUID = 1L;

	public int getKuzhizao() {
		return kuzhizao;
	}

		public void setKuzhizao(int kuzhizao) {
			this.kuzhizao = kuzhizao;
		}

		public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getProjectNo() {
	        return projectNo;
	    }

	    public void setProjectNo(String projectNo) {
	        this.projectNo = projectNo == null ? null : projectNo.trim();
	    }

	    public String getEmployeName() {
	        return employeName;
	    }

	    public void setEmployeName(String employeName) {
	        this.employeName = employeName == null ? null : employeName.trim();
	    }

	    public String getCurrentStatus() {
	        return currentStatus;
	    }

	    public void setCurrentStatus(String currentStatus) {
	        this.currentStatus = currentStatus == null ? null : currentStatus.trim();
	    }

	    public String getUpdateTime() {
	        return updateTime;
	    }

	    public void setUpdateTime(String updateTime) {
	        this.updateTime = updateTime == null ? null : updateTime.trim();
	    }

	    public String getUploadurl() {
	        return uploadurl;
	    }

	    public void setUploadurl(String uploadurl) {
	        this.uploadurl = uploadurl == null ? null : uploadurl.trim();
	    }

	public String getDingding() {
		return dingding;
	}

	public void setDingding(String dingding) {
		this.dingding = dingding == null ? null : dingding.trim();
	}

	public String getRedirectUser() {
		return redirectUser;
	}

	public void setRedirectUser(String redirectUser) {
		this.redirectUser = redirectUser;
	}

	@Override
	public String toString() {
		return "QuotePrice [id=" + id + ", projectNo=" + projectNo
				+ ", employeName=" + employeName + ", currentStatus="
				+ currentStatus + ", updateTime=" + updateTime + ", uploadurl="
				+ uploadurl + ", dingding=" + dingding + ", kuzhizao="
				+ kuzhizao + "]";
		}
	
}
