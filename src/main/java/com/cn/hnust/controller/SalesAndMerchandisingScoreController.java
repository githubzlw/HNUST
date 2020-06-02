package com.cn.hnust.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.pojo.DeductionForm;
import com.cn.hnust.pojo.InvoiceInfo;
import com.cn.hnust.pojo.ProfitStatement;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.SalesAndMerchandisingScore;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IDeductionFormService;
import com.cn.hnust.service.IProfitStatementService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IQuotationNewQuotesService;
import com.cn.hnust.service.ISalesAndMerchandisingScoreService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.service.InvoiceInfoService;
import com.cn.hnust.service.ItemCaseERPService;
import com.cn.hnust.service.ProjectComplaintService;
import com.cn.hnust.util.PropertiesUtils;


@Controller
@RequestMapping("/salesAndMerchandisingScoreController")
public class SalesAndMerchandisingScoreController {
	private static final Log LOG = LogFactory.getLog(QualityController.class);
	 private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	private static int MetalDeliveryTime = Integer.parseInt(reader.getProperty("MetalDeliveryTime"));//金属交期最大值
	private static int PlasticDeliveryPeriod = Integer.parseInt(reader.getProperty("PlasticDeliveryPeriod"));//塑料交期最大值
	@Resource
	private IUserService userService;
	@Resource
	private ISalesAndMerchandisingScoreService salesAndMerchandisingScoreService;
	@Autowired
	private ItemCaseERPService itemCaseERPService;
	@Autowired
	private InvoiceInfoService invoiceInfoService;
	@Resource
	private IProfitStatementService profitStatementService;
	@Autowired
	private ProjectComplaintService projectComplaintService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IQuotationNewQuotesService quotationNewQuotesService;
	@Autowired
	private IDeductionFormService deductionFormService;
	@RequestMapping("/salesAndMerchandisingScore")
	public String salesAndMerchandisingScore(HttpServletRequest request,
			HttpServletResponse response) {
		String userName="";
		Cookie[] cookies = request.getCookies();
 	     if(cookies!=null){
 	     for(Cookie c :cookies ){
          if(c.getName().equals("name")){
         	userName=c.getValue();
          }
 	     }
 	    }
 	    User user=userService.selectUserByName(userName);
 	    String name="ninazhaoAndsXuejerrylong";
 	    boolean save=name.toLowerCase().contains(userName.toLowerCase());
 	    if(save!=false){
 	    	request.setAttribute("roleNo", 100);	
 	    }
		String date = request.getParameter("date");
		SalesAndMerchandisingScore score=new SalesAndMerchandisingScore();
		if(date!=null&&!"".equals(date)){
		score.setDate(date);
		request.setAttribute("date", date);
		}else{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");  
			Calendar c = Calendar.getInstance();  
			c.add(Calendar.MONTH, -1);    //得到前一个月    
			String start = format.format(c.getTime());  
			score.setDate(start);
			date=start;
			request.setAttribute("date", start);
		}
		List<SalesAndMerchandisingScore> allScoreList = salesAndMerchandisingScoreService.selectAllScore(score);
		
		request.setAttribute("allScoreList", allScoreList);
		return "sales_and_merchandising_score";
	}
	
	
	@Scheduled(cron="0 30 0 * * ?") //每天凌晨30分执行
	public void show(){ 
		salesAndDocumentationScoring();
	}
	@RequestMapping("/salesAndDocumentationScoring")
	public void salesAndDocumentationScoring() {
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
	   String time=sdf.format(new Date());
	  for(int i=0;i<18;i++){
		try {
			Date date1=sdf.parse(time);
			int num=i+1;
			Calendar theCa = Calendar.getInstance();
	         theCa.setTime(date1);
	         theCa.add(theCa.MONTH, -num);
	        String time1 = sdf.format(theCa.getTime()); 
			processingData(time1);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		   
	   }
		
		
	}
   
   @RequestMapping("/getAll")
	public void getAll(HttpServletRequest request,
			HttpServletResponse response) {
	 
	   String date=request.getParameter("date");
	   processingData(date);
	 
   }
/*	@Scheduled(cron="0 0 1 1 * ?") //每月1号凌晨1点执行一次
	public void show(){ 
		processingData();
	}
*/
	private void processingData(String start) {
		try{
		SalesAndMerchandisingScore score1=new SalesAndMerchandisingScore();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");  
		Calendar c = Calendar.getInstance();  
		c.add(Calendar.MONTH, -1);    //得到前一个月    
		//String start = format.format(c.getTime());
		score1.setDate(start);
		User user=new User();
		List<User>saleList=userService.selectAllUser();
		List<SalesAndMerchandisingScore> ScoreList=new ArrayList<SalesAndMerchandisingScore>();
		List<ProfitStatement> ProfitList=new ArrayList<ProfitStatement>();
		for(int i=0;i<saleList.size();i++){
			SalesAndMerchandisingScore score=new SalesAndMerchandisingScore();
			ProfitStatement profitStatement=new ProfitStatement();
			User user1=saleList.get(i);
			if("销售".equals(user1.getJob())){
				int oldCustomerOff=itemCaseERPService.getOldCustomerOff(user1.getUserName(),start)*5;
				List<ProjectERP> itList=itemCaseERPService.getAllCaseNo1(user1.getUserName(),start);//ERP上项目号
				int newCustomerBigProjectOffer=getBigProject(itList,0);	
				int oldCustomerBigProjectOffer=getBigProject(itList,1);
				String money1=invoiceInfoService.getAllMoney(user1.getUserName(),start);
				double money=0.000;
				if(money1!=null&&!"".equals(money1)){
					money=Double.parseDouble(money1);
				}
				score.setDate(start);
				score.setRoleName("销售");
				if(oldCustomerOff>20){
					oldCustomerOff=20;
				}
				if(newCustomerBigProjectOffer>20){
					newCustomerBigProjectOffer=20;
				}
				if(oldCustomerBigProjectOffer>20){
					oldCustomerBigProjectOffer=20;
				}
				
				score.setOldCustomerOff((double)oldCustomerOff);
				score.setNewCustomerBigProjectOffer((double)newCustomerBigProjectOffer);
				score.setOldCustomerBigProjectOffer((double)oldCustomerBigProjectOffer);
				score.setSaleName(user1.getUserName());
				profitStatement.setDate(start);
				profitStatement.setProfit(money);
				profitStatement.setRoleName("销售");
				profitStatement.setUserName(user1.getUserName());
				ScoreList.add(score);
				ProfitList.add(profitStatement);
			}else if("跟单".equals(user1.getJob())){
				int oldCustomerOff=itemCaseERPService.getOldCustomerOff(user1.getUserName(),start)*5; 
				String money1=invoiceInfoService.getAllMoney(user1.getUserName(),start);
				InvoiceInfo info=invoiceInfoService.getAll(user1.getUserName(), start);
				Double customerFollowUp=0.00;
				if(info.getSubscribers()!=0){
				 customerFollowUp=(double) ((info.getSingleNumber()/info.getSubscribers())*50);
				}
			
				double money=0.000;
				if(money1!=null&&!"".equals(money1)){
					money=Double.parseDouble(money1);
				}
				//double number=0;
				List<ProjectERP> itList=itemCaseERPService.getAllCaseNo(user1.getUserName(),start);//ERP上项目号
			//	number=projectViolation(user1.getUserName(),start);//项目违规数
				int proofingSuccess=proofingSuccess(user1.getUserName(),start,1);//打样成功
				int proofingFailure=proofingSuccess(user1.getUserName(),start,2);//打样失败
				int complaintsNumber=complaintsNumber(user1.getUserName(),start,itList);//质量投诉次数
				double quality=0.00;
				int quality1=proofingSuccess*10-proofingFailure*10-5*complaintsNumber;
				if(quality1>25){
					quality1=25;
				}else if(quality1<0){
					quality1=0;
				}
				
				quality=(double)quality1;
				double deliveryFraction=getDeliveryFraction(user1.getUserName(),start,itList);
                if(deliveryFraction>25){
            	   deliveryFraction=25.0;
				}else if(deliveryFraction<0){
					deliveryFraction=0;	
				}
                int number=5;
				//score.setTechnologicalProcess(-2*number);
                DeductionForm from=deductionFormService.getOne(user1.getUserName(),start);//获取当月扣分次数
				if(from!=null){
					number=number-2*from.getDeductionFrequency();
				}
				if(number<0){
					number=0;
				}
                score.setTechnologicalProcess((double)number);
				score.setQuality(quality);
				score.setDeliveryTime(deliveryFraction);
				score.setDate(start);
				score.setRoleName("跟单");
				score.setOldCustomerOff((double)oldCustomerOff);
				
				if(oldCustomerOff+customerFollowUp>25){
					customerFollowUp=25.0;
				}else if(customerFollowUp<0){
					customerFollowUp=0.0;
				}
				score.setCustomerFollowUp(customerFollowUp);
				score.setNewCustomerBigProjectOffer(0.0);
				score.setOldCustomerBigProjectOffer(0.0);
				score.setSaleName(user1.getUserName());
				profitStatement.setDate(start);
				profitStatement.setProfit(money);
				profitStatement.setRoleName("跟单");
				profitStatement.setUserName(user1.getUserName());
				ProfitList.add(profitStatement);
				ScoreList.add(score);
			}
		}
		List<SalesAndMerchandisingScore> list=salesAndMerchandisingScoreService.selectAllScore(score1); 
		if(list.size()>0){
			int total=salesAndMerchandisingScoreService.updateAll(ScoreList);
			int total1=profitStatementService.updateAll(ProfitList);	
		}else{
		int total=salesAndMerchandisingScoreService.insertAll(ScoreList);
		int total1=profitStatementService.insertAll(ProfitList);
		}
		revisionRanking(start);
		updateAllScore(start);
		}catch(Exception e){
			LOG.warn(e);
			e.printStackTrace();
		}
	}
	//获取大客户
	private int getBigProject(List<ProjectERP> itList, int m) {
		int num=0;
		String allProjectNo="";
		for(int i=0;i<itList.size();i++){
			ProjectERP erp=itList.get(i);
			allProjectNo+=",'"+erp.getProjectNo()+"'";
		}
		allProjectNo=allProjectNo.replaceFirst(",", "");
		if(m==0){
			if(allProjectNo!=null&&!"".equals(allProjectNo)){
			int num1=quotationNewQuotesService.getNew(allProjectNo);
			num=10*num1;
			}
		}else if(m==1){
			if(allProjectNo!=null&&!"".equals(allProjectNo)){
			int num1=quotationNewQuotesService.getOld(allProjectNo);
			num=10*num1;
			}
		}
		return num;
	}


	//获取交期分数
	private double getDeliveryFraction(String userName, String start,
		List<ProjectERP> itList) {
		int score=0;
		String allProjectNo="";
		for(int i=0;i<itList.size();i++){
			ProjectERP erp=itList.get(i);
			allProjectNo+=",'"+erp.getProjectNo()+"'";
		}
		allProjectNo=allProjectNo.replaceFirst(",", "");
		if(allProjectNo!=null&&!"".equals(allProjectNo)){
		int num1=projectService.getFinishTime(allProjectNo,MetalDeliveryTime,0,PlasticDeliveryPeriod,0);
		int num2=projectService.getFinishTime(allProjectNo,MetalDeliveryTime+7,MetalDeliveryTime,PlasticDeliveryPeriod+7,PlasticDeliveryPeriod);
		int num3=projectService.getFinishTime(allProjectNo,MetalDeliveryTime+23,MetalDeliveryTime+7,PlasticDeliveryPeriod+23,PlasticDeliveryPeriod+7);
		//int num4=projectService.getFinishTimeOneMonth(allProjectNo);
		int num5=projectService.getFinishTime(allProjectNo,MetalDeliveryTime+83,MetalDeliveryTime+53,PlasticDeliveryPeriod+83,PlasticDeliveryPeriod+53);
		int num6=projectService.getFinishTime(allProjectNo,MetalDeliveryTime+173,MetalDeliveryTime+83,PlasticDeliveryPeriod+173,PlasticDeliveryPeriod+83);
		int num7=projectService.getFinishTime(allProjectNo,0,MetalDeliveryTime+173,0,PlasticDeliveryPeriod+173);
		score=num1*15+10*num2+5*num3+num5*(-5)+(-10)*num6+(-15)*num7;	
		}
		
		
	return score;
}


	//质量投诉次数
	private int complaintsNumber(String userName, String start, List<ProjectERP> itList) {
		int complaintsNumber=0;
		String allProjectNo="";
		for(int i=0;i<itList.size();i++){
			ProjectERP erp=itList.get(i);
			allProjectNo+=",'"+erp.getProjectNo()+"'";
		}
		allProjectNo=allProjectNo.replaceFirst(",", "");
		if(allProjectNo!=null&&!"".equals(allProjectNo)){
			String complaintsNumber1=projectComplaintService.getComplaintsNumber(allProjectNo);
			if(complaintsNumber1!=null&&!"".equalsIgnoreCase(complaintsNumber1)){
			complaintsNumber=Integer.parseInt(complaintsNumber1);
			}
		}
		return complaintsNumber;
	}


	


//打样数据处理
 private int proofingSuccess(String userName, String start, int i) {
	 int proofing=0;
		if(i==1){
			int num1=itemCaseERPService.proofingSuccess(userName,start);//打样成功数量
			proofing=num1;
				
		}else if(i==2){
			int num1=itemCaseERPService.proofingFailure(userName,start);//打样失败数量
			proofing=num1;
		}
		return proofing;
	}


//查询项目违规
 private double projectViolation(String userName, String start) {
	double num=0.0;
	String num1=itemCaseERPService.getProjectViolation(userName,start);
	if(num1!=null&&!"".equals(num1)){
	num=Integer.parseInt(num1);
	}
	 return num;
 }

//修改当月总分
private void updateAllScore(String start) {
	salesAndMerchandisingScoreService.updateAllScoreSale(start);//修改销售总分
	salesAndMerchandisingScoreService.updateAllScoreDocumentary(start);//修改跟单总分
	
}


private void revisionRanking(String start) {
	List<ProfitStatement> ProfitList1=profitStatementService.getAll(start,"销售");
	List<ProfitStatement> ProfitList2=profitStatementService.getAll(start,"跟单");
	String time1="";
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	 try {
		Date date1=sdf.parse(start);
		 Calendar theCa = Calendar.getInstance();
         theCa.setTime(date1);
         theCa.add(theCa.MONTH, -1);
         time1 = sdf.format(theCa.getTime());
	} catch (ParseException e) {
		e.printStackTrace();
	}
	for(int j=0;j<ProfitList1.size();j++){
		ProfitStatement profit=ProfitList1.get(j);
		SalesAndMerchandisingScore score=new SalesAndMerchandisingScore();
		score.setDate(profit.getDate());
		score.setSaleName(profit.getUserName());
		ProfitStatement profit1=profitStatementService.getOne(profit.getUserName(),time1);
		if(profit1!=null&&!"".equals(profit1)){
		double increase=acquisitionRatio(profit1.getProfit(),profit.getProfit(),"销售");
		score.setIncrease(increase);
		}else{
		score.setIncrease(0.0);	
		}
		if(j==0){
		score.setRanking(20.0);
		salesAndMerchandisingScoreService.updateOne(score);	
		}else if(j==1){
		score.setRanking(16.0);
		salesAndMerchandisingScoreService.updateOne(score);	
		}else if(j>=2 &&j<=7){
		score.setRanking(12.0);
		salesAndMerchandisingScoreService.updateOne(score);	
		}else if(j>7){
		score.setRanking(10.0);
		salesAndMerchandisingScoreService.updateOne(score);		
		}
	}
	for(int j=0;j<ProfitList2.size();j++){
		ProfitStatement profit=ProfitList2.get(j);
		SalesAndMerchandisingScore score=new SalesAndMerchandisingScore();
		score.setDate(profit.getDate());
		score.setSaleName(profit.getUserName());
		ProfitStatement profit1=profitStatementService.getOne(profit.getUserName(),time1);
		if(profit1!=null&&!"".equals(profit1)){
		double increase=acquisitionRatio(profit1.getProfit(),profit.getProfit(),"跟单");
		score.setIncrease(increase);
		}else{
		score.setIncrease(0.0);	
		}
		if(j==0){
		score.setRanking(10.0);
		salesAndMerchandisingScoreService.updateOne(score);	
		}else if(j==1){
		score.setRanking(8.0);
		salesAndMerchandisingScoreService.updateOne(score);	
		}else if(j==2 ){
		score.setRanking(7.0);
		salesAndMerchandisingScoreService.updateOne(score);	
		}else if(j>=3 &&j<=7){
		score.setRanking(6.0);
		salesAndMerchandisingScoreService.updateOne(score);	
		}else if(j>7){
		score.setRanking(4.0);
		salesAndMerchandisingScoreService.updateOne(score);		
		}
	}
	
}


private double acquisitionRatio(Double profit, Double profit2, String roleName) {
	double increase=0.00;
	if("销售".equals(roleName)){
		double proportion=0.00;
		if(profit2!=0){
			proportion=(profit-profit2)/profit2;
		}
		if(proportion>=0.2){
			increase=20;
		}else if(proportion>=0.1&&proportion<0.2){
			increase=12;
		}else if(proportion>=0&&proportion<0.1){
			increase=10;
		}else if(proportion>=-0.1&&proportion<0){
			increase=5;
		}else{
			increase=0;	
		}
		
	}
	if("跟单".equals(roleName)){
		double proportion=0.00;
		if(profit2!=0){
			proportion=(profit-profit2)/profit2;
		}
		if(proportion>=0.2){
			increase=10;
		}else if(proportion>=0.1&&proportion<0.2){
			increase=8;
		}else if(proportion>=0&&proportion<0.1){
			increase=6;
		}else if(proportion>=-0.1&&proportion<0){
			increase=4;
		}else if(proportion>=-0.2&&proportion<-0.1){
			increase=2;	
		}else{
			increase=0;
		}
		
	}
	return increase;
} 
	
}
