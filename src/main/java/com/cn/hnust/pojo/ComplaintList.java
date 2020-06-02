package com.cn.hnust.pojo;



public class ComplaintList {
	  private Integer id;

	    private String caseNo;         //项目号

	    private String complaintState; //投诉原因      

	    private Integer complaintId;      //投诉单编号

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getCaseNo() {
			return caseNo;
		}

		public void setCaseNo(String caseNo) {
			this.caseNo = caseNo;
		}

		public String getComplaintState() {
			return complaintState;
		}

		public void setComplaintState(String complaintState) {
			this.complaintState = complaintState;
		}

		public Integer getComplaintId() {
			return complaintId;
		}

		public void setComplaintId(Integer complaintId) {
			this.complaintId = complaintId;
		}

		@Override
		public String toString() {
			return "ComplaintList [id=" + id + ", caseNo=" + caseNo
					+ ", complaintState=" + complaintState + ", complaintId="
					+ complaintId + "]";
		}
	    
	    
	    
}
