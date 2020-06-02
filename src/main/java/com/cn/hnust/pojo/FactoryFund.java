package com.cn.hnust.pojo;

import java.util.Date;

public class FactoryFund {
   String caseno;//项目号
   Double money;//金额
   int moneytype;//付款类别
   Date moneydate;//付款日期
   int payDepartment;//支付部门 
   String content;//支付说明
   String name;//录入人
   String apNumber;//编号
   String state;//状态
   
   
public String getApNumber() {
	return apNumber;
}
public void setApNumber(String apNumber) {
	this.apNumber = apNumber;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCaseno() {
	return caseno;
}
public void setCaseno(String caseno) {
	this.caseno = caseno;
}
public Double getMoney() {
	return money;
}
public void setMoney(Double money) {
	this.money = money;
}
public int getMoneytype() {
	return moneytype;
}
public void setMoneytype(int moneytype) {
	this.moneytype = moneytype;
}
public Date getMoneydate() {
	return moneydate;
}
public void setMoneydate(Date moneydate) {
	this.moneydate = moneydate;
}
public int getPayDepartment() {
	return payDepartment;
}
public void setPayDepartment(int payDepartment) {
	this.payDepartment = payDepartment;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "FactoryFund [caseno=" + caseno + ", money=" + money
			+ ", moneytype=" + moneytype + ", moneydate=" + moneydate
			+ ", payDepartment=" + payDepartment + ", content=" + content
			+ ", name=" + name + ", apNumber=" + apNumber + ", state=" + state
			+ "]";
}
   
   
}
