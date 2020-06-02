package com.cn.hnust.pojo;
//报价系统列表
public class QuotationUser {
	//主键
	private Integer id;
    //7天内完成报价数量
    private Integer quoteNumber;
    //A级项目
    private Integer aLevelProject;
  //b级项目
    private Integer bLevelProject;
  //c级项目
    private Integer cLevelProject;
  //d级项目
    private Integer dLevelProject;
    //等待报价A/B
    private Integer waitingQuotation;
    //等待报价总数
    private Integer allWaitingQuotation;
    //下单A/B
    private Integer placeOrder;
    //下单总数
    private Integer allPlaceOrder;
   //客户抱怨贵了，正在挽救a/b
    private Integer complainingExpensive;
    //客户抱怨贵了，正在挽救总数
    private Integer allComplainingExpensive;
     //客户没反馈a/b
    private Integer noFeedback;
    //客户没反馈总数
    private Integer allNoFeedback;
    //和客户积极沟通中a/b
    private Integer activeCommunication;
    //和客户积极沟通中总数
    private Integer allActiveCommunication;
    //放弃a/b
    private Integer giveUp;
    //放弃总数
    private Integer allGiveUp;
    
    private String name;     //用户名
    
    
    
	public Integer getWaitingQuotation() {
		return waitingQuotation;
	}

	public void setWaitingQuotation(Integer waitingQuotation) {
		this.waitingQuotation = waitingQuotation;
	}

	public Integer getAllWaitingQuotation() {
		return allWaitingQuotation;
	}

	public void setAllWaitingQuotation(Integer allWaitingQuotation) {
		this.allWaitingQuotation = allWaitingQuotation;
	}

	public Integer getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(Integer placeOrder) {
		this.placeOrder = placeOrder;
	}

	public Integer getAllPlaceOrder() {
		return allPlaceOrder;
	}

	public void setAllPlaceOrder(Integer allPlaceOrder) {
		this.allPlaceOrder = allPlaceOrder;
	}

	public Integer getComplainingExpensive() {
		return complainingExpensive;
	}

	public void setComplainingExpensive(Integer complainingExpensive) {
		this.complainingExpensive = complainingExpensive;
	}

	public Integer getAllComplainingExpensive() {
		return allComplainingExpensive;
	}

	public void setAllComplainingExpensive(Integer allComplainingExpensive) {
		this.allComplainingExpensive = allComplainingExpensive;
	}

	public Integer getNoFeedback() {
		return noFeedback;
	}

	public void setNoFeedback(Integer noFeedback) {
		this.noFeedback = noFeedback;
	}

	public Integer getAllNoFeedback() {
		return allNoFeedback;
	}

	public void setAllNoFeedback(Integer allNoFeedback) {
		this.allNoFeedback = allNoFeedback;
	}

	public Integer getActiveCommunication() {
		return activeCommunication;
	}

	public void setActiveCommunication(Integer activeCommunication) {
		this.activeCommunication = activeCommunication;
	}

	public Integer getAllActiveCommunication() {
		return allActiveCommunication;
	}

	public void setAllActiveCommunication(Integer allActiveCommunication) {
		this.allActiveCommunication = allActiveCommunication;
	}

	public Integer getGiveUp() {
		return giveUp;
	}

	public void setGiveUp(Integer giveUp) {
		this.giveUp = giveUp;
	}

	public Integer getAllGiveUp() {
		return allGiveUp;
	}

	public void setAllGiveUp(Integer allGiveUp) {
		this.allGiveUp = allGiveUp;
	}

	public Integer getaLevelProject() {
		return aLevelProject;
	}

	public void setaLevelProject(Integer aLevelProject) {
		this.aLevelProject = aLevelProject;
	}

	public Integer getbLevelProject() {
		return bLevelProject;
	}

	public void setbLevelProject(Integer bLevelProject) {
		this.bLevelProject = bLevelProject;
	}

	public Integer getcLevelProject() {
		return cLevelProject;
	}

	public void setcLevelProject(Integer cLevelProject) {
		this.cLevelProject = cLevelProject;
	}

	public Integer getdLevelProject() {
		return dLevelProject;
	}

	public void setdLevelProject(Integer dLevelProject) {
		this.dLevelProject = dLevelProject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuoteNumber() {
		return quoteNumber;
	}

	public void setQuoteNumber(Integer quoteNumber) {
		this.quoteNumber = quoteNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
