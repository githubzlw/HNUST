package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class PersonData implements Serializable {
	 	
   /**
	 * @fieldName serialVersionUID
	 * @fieldType long
	 * @Description 
	 */
	private static final long serialVersionUID = 1L;
   private int processNum;          //生产中数量
   private int reorderNum;          //返单项目数
   private Double totalAmount;     //返单总金额
   private int newNum;              //新项目总数
   private Double totalNewAmount;  //新项目金额
   private int oneMonthCount;       //到账一个月的数量
   private int twoMonthCount;       //到账两个月的数量
   private int threeMonthCount;     //到账三个月的数量
   private int upThreeMounthCount;   //到账大于三个月数量
		public int getProcessNum() {
			return processNum;
		}
		public void setProcessNum(int processNum) {
			this.processNum = processNum;
		}
		public int getReorderNum() {
			return reorderNum;
		}
		public void setReorderNum(int reorderNum) {
			this.reorderNum = reorderNum;
		}
		public Double getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(Double totalAmount) {
			this.totalAmount = totalAmount;
		}
		public int getNewNum() {
			return newNum;
		}
		public void setNewNum(int newNum) {
			this.newNum = newNum;
		}
		public Double getTotalNewAmount() {
			return totalNewAmount;
		}
		public void setTotalNewAmount(Double totalNewAmount) {
			this.totalNewAmount = totalNewAmount;
		}
		public int getOneMonthCount() {
			return oneMonthCount;
		}
		public void setOneMonthCount(int oneMonthCount) {
			this.oneMonthCount = oneMonthCount;
		}
		public int getTwoMonthCount() {
			return twoMonthCount;
		}
		public void setTwoMonthCount(int twoMonthCount) {
			this.twoMonthCount = twoMonthCount;
		}
		public int getThreeMonthCount() {
			return threeMonthCount;
		}
		public void setThreeMonthCount(int threeMonthCount) {
			this.threeMonthCount = threeMonthCount;
		}
		public int getUpThreeMounthCount() {
			return upThreeMounthCount;
		}
		public void setUpThreeMounthCount(int upThreeMounthCount) {
			this.upThreeMounthCount = upThreeMounthCount;
		}
		@Override
		public String toString() {
			return "PersonData [processNum=" + processNum + ", reorderNum="
					+ reorderNum + ", totalAmount=" + totalAmount + ", newNum="
					+ newNum + ", totalNewAmount=" + totalNewAmount
					+ ", oneMonthCount=" + oneMonthCount + ", twoMonthCount="
					+ twoMonthCount + ", threeMonthCount=" + threeMonthCount
					+ ", upThreeMounthCount=" + upThreeMounthCount + "]";
}
   
   
   
   
}