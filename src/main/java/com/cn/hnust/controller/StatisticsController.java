package com.cn.hnust.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.component.RpcHelper;
import com.cn.hnust.component.RpcQuotationStatistic;
import com.cn.hnust.enums.ItemClassificationEnum;
import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.pojo.Comment;
import com.cn.hnust.pojo.EmailUser;
import com.cn.hnust.pojo.InvoiceInfo;
import com.cn.hnust.pojo.PersonData;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.pojo.QuoteWeeklyReport;
import com.cn.hnust.pojo.QuoteWeeklyReportQuery;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IEmailUserService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IQualityReportService;
import com.cn.hnust.service.InvoiceInfoService;
import com.cn.hnust.service.ItemCaseERPService;
import com.cn.hnust.service.ProjectFactoryService;
import com.cn.hnust.service.QuoteWeeklyReportService;
import com.cn.hnust.service.impl.UserServiceImpl;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.SerializeUtil;
import com.cn.hnust.util.WebCookie;
import com.sun.org.apache.bcel.internal.generic.NEW;

@RequestMapping("/statistics")
@Controller
public class StatisticsController {
	@Autowired
	private IQualityReportService qrService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IEmailUserService emailUserService;
	@Autowired
	private InvoiceInfoService invoiceInfoService;
	@Autowired
	private QuoteWeeklyReportService quoteWeeklyReportService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private ProjectFactoryService projectFactoryService;
	@Autowired
	private ItemCaseERPService itemCaseERPService;
	
	
	
	private static final Log LOG = LogFactory.getLog(StatisticsController.class);
	
	@RequestMapping("/selectAllByDate")
	public String selectAllByDate(HttpServletRequest request,HttpServletResponse response){
         try {
	        String dateType = request.getParameter("dateType");
	        Project p = new Project();
	        p.setProjectStatus(OrderStatusEnum.NEW_ORDER.getCode());
	        if(dateType!=null&&!"".equalsIgnoreCase(dateType)){
	        	p.setDateType(Integer.parseInt(dateType));
	        }else{
	        	p.setDateType(1);
	        	dateType="1";
	        }
	        List<Project> projectList = projectService.selectCount(p);
            //???????????????
	        int totalCount = 0;
	        Double totalAmount = 0.00;
	        if(projectList.size()>0){
	        	totalCount = projectList.size();
	        	List<String> projectNoList = new ArrayList<String>();
	        	for (Project project : projectList) {
	        		String projectAmount = project.getProjectAmount();
	        		Double amount = getAmount(projectAmount);
	        		/*if(amount > 1000){
	        			amount = amount/10000;
	        		}*/
	        		totalAmount +=amount;
	        		projectNoList.add(project.getProjectNo());
				}
	        }
          
          //type 1:?????? 2????????? 3?????????
		  String saleName = projectService.maxStartProject(1,Integer.parseInt(dateType));
		  String followName = projectService.maxStartProject(2,Integer.parseInt(dateType));
		  String purcharName = projectService.maxStartProject(3,Integer.parseInt(dateType));
		  //?????????????????????????????????
		  int saleNewNum = 0,saleOldNum = 0;
		  //?????????????????????????????????
		  int followNewNum = 0,followOldNum = 0;
		  //?????????????????????????????????
		  int purchaseNewNum = 0,purchaseOldNum = 0;		  
		  //??????????????????????????????????????????erp????????????
		  List<String> saleProjectNos = new ArrayList<String>();
		  //?????????????????????????????????????????????erp????????????
		  List<String> followProjectNos = new ArrayList<String>();
		  //??????????????????????????????????????????erp????????????
		  List<String> purchaseProjectNos = new ArrayList<String>();
		  
		  List<EmailUser> emailUsers = emailUserService.queryAll();
		  List<EmailUser> saleUsers=new ArrayList<EmailUser>();
		  List<EmailUser> purchaseUsers=new ArrayList<EmailUser>();
		  for (EmailUser emailUser : emailUsers) {			  
			  PersonData personData = new PersonData();
			  Project project = new Project();
			  Project project1 = new Project();
			  project.setDateType(Integer.parseInt(dateType));
			  project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
              if(emailUser.getRoleNo() == 5){
            	  project.setEmailUserId(emailUser.getId());
            	  project1.setEmailUserId(emailUser.getId());
            	  saleUsers.add(emailUser);
              }else if(emailUser.getRoleNo() == 6){
            	  project.setPurchaseId(emailUser.getId());
            	  project1.setPurchaseId(emailUser.getId());
            	  purchaseUsers.add(emailUser);
              }
			  
			  //?????????????????????????????????
			  List<Project> projects1 = projectService.selectCount(project);
			  if(projects1.size()>0){
				  personData.setProcessNum(projects1.size());
			  }
			  
			  //?????????????????????
			  project.setProjectNo("-");
			  //?????????????????????????????????
			  List<Project> projects2 = projectService.selectCount(project);
			  if(projects2.size()>0){
				  personData.setReorderNum(projects2.size());
				  Double reorderAmount = 0.0;
				  for (Project project2 : projects2) {
					  String projectAmount = project2.getProjectAmount();
		        		Double amount = getAmount(projectAmount);
		        		if(amount > 1000){
		        			amount = amount/10000;
		        		}
		        		reorderAmount +=amount;
				  }
				  reorderAmount = new BigDecimal(reorderAmount).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
				  personData.setTotalAmount(reorderAmount);
			  }
			  
			  //????????????
			  project.setProjectStatus(OrderStatusEnum.NEW_ORDER.getCode());
			  project.setProjectNo(null);
			  List<Project> projects3 = projectService.selectCount(project);
			  if(projects3.size()>0){
				  personData.setNewNum(projects3.size());
				  Double newAmount = 0.0;
				  for (Project project3 : projects3) {
					  String projectAmount = project3.getProjectAmount();
		        		Double amount = getAmount(projectAmount);
		        		if(amount > 1000){
		        			amount = amount/10000;
		        		}
		        		newAmount +=amount;
		        		
		        		//??????
		        		if(emailUser.getUserName().equals(saleName)){
		        			saleProjectNos.add(project3.getProjectNo());
		        		}
		        		//??????
		        		if(emailUser.getUserName().equals(followName)){
		        			followProjectNos.add(project3.getProjectNo());
		        		}
		        		//??????
		        		if(emailUser.getUserName().equals(purcharName)){
		        			purchaseProjectNos.add(project3.getProjectNo());
		        		}
				  }
				  personData.setTotalNewAmount(newAmount);
			  }
	  
			  
			  //??????????????????(?????????)
//			  project1.setDateType(Integer.parseInt(dateType));
			  project1.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
			  project1.setMoneyDateType(1);
			  List<Project> project4 = projectService.selectCount(project1);
			  if(project4.size()>0){
				  personData.setOneMonthCount(project4.size());
			  }
			  //??????????????????(?????????)
			  project1.setMoneyDateType(2);
			  List<Project> project5 = projectService.selectCount(project1);
			  if(project5.size()>0){
				  personData.setTwoMonthCount(project5.size());
			  }
			  //??????????????????(?????????)
			  project1.setMoneyDateType(3);
			  List<Project> project6 = projectService.selectCount(project1);
			  if(project6.size()>0){
				  personData.setThreeMonthCount(project6.size());
			  }
			  //??????????????????(???????????????)
			  project1.setMoneyDateType(4);
			  List<Project> project7 = projectService.selectCount(project1);
			  if(project7.size()>0){
				  personData.setUpThreeMounthCount(project7.size());
			  }
			  
			  emailUser.setPersonData(personData);
		  }
		  
		  //??????erp???????????????
		  if(saleProjectNos != null && saleProjectNos.size() > 0){
			  List<InvoiceInfo> invoiceInfos1 = invoiceInfoService.selectFirstDate(saleProjectNos);
			  for (InvoiceInfo invoiceInfo2 : invoiceInfos1) {
				  Date ifdate = invoiceInfo2.getIfdate();
				  if(ifdate != null){
					  Boolean flag = DateFormat.compareDate(ifdate, new Date(), 90);
					  if(flag){
						  saleNewNum +=1;
					  }else{
						  saleOldNum +=1;
					  }
				  }
			  }
		  }  
			  if(followProjectNos != null && followProjectNos.size() > 0){
				  List<InvoiceInfo> invoiceInfos2 = invoiceInfoService.selectFirstDate(followProjectNos);
				  for (InvoiceInfo invoiceInfo2 : invoiceInfos2) {
					  Date ifdate = invoiceInfo2.getIfdate();
					  if(ifdate != null){
						  Boolean flag = DateFormat.compareDate(ifdate, new Date(), 90);
						  if(flag){
							  followNewNum +=1;
						  }else{
							  followOldNum +=1;
						  }
					  }
				  }
			  }
			  
			  
			  if(purchaseProjectNos != null && purchaseProjectNos.size() > 0){
				  List<InvoiceInfo> invoiceInfos3 = invoiceInfoService.selectFirstDate(purchaseProjectNos);
				  for (InvoiceInfo invoiceInfo2 : invoiceInfos3) {
					  Date ifdate = invoiceInfo2.getIfdate();
					  if(ifdate != null){
						  Boolean flag = DateFormat.compareDate(ifdate, new Date(), 90);
						  if(flag){
							  purchaseNewNum +=1;
						  }else{
							  purchaseOldNum +=1;
						  }
					  }
				  }
			  }
          //????????????????????????
		  int num = 0;         //????????????
		  Double total = 0.0;  //???????????????
		  Double time = 0.0;   //???????????????????????? 
		  String quoter = "";  //?????????????????????
		  String sale = "";    //?????????????????????
		  String quotationAssistant = "";  //?????????????????????
		  
		  RpcQuotationStatistic rpc = new RpcQuotationStatistic();
		  String startDate = null;
		  if(Integer.parseInt(dateType) == 1){
			  startDate = DateFormat.addDays(DateFormat.currentDate(), -7);
		  }else if(Integer.parseInt(dateType) == 2){
			  startDate = DateFormat.addDays(DateFormat.currentDate(), -30);
		  }
		  if(StringUtils.isNotBlank(startDate)){
			  String parameter = rpc.sendRequest(null, startDate,DateFormat.currentDate());
			  if(StringUtils.isNotBlank(parameter)){
				  Map<Object, Object> cl = SerializeUtil.JsonToMap(parameter);
				  num = Integer.parseInt(cl.get("num").toString());
				  total = Double.parseDouble(cl.get("allMoney").toString());
				  total = new BigDecimal(total).divide(new BigDecimal(6.87), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
				  time = Double.parseDouble(cl.get("time").toString());
				  time = new BigDecimal(time).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
				  quoter = cl.get("quoter").toString();
				  sale = cl.get("saleName").toString();
				  quotationAssistant = cl.get("quotationAssistant").toString();
			  }
		  }
		 
		  
		  request.setAttribute("totalCount", totalCount);
		  request.setAttribute("totalAmount", totalAmount);
		  request.setAttribute("saleName", saleName);
		  request.setAttribute("followName", followName);
		  request.setAttribute("purcharName", purcharName);
		  request.setAttribute("emailUsers", emailUsers);
		  request.setAttribute("num", num);
		  request.setAttribute("total", total);
		  request.setAttribute("time", time);
		  request.setAttribute("quoter", quoter);
		  request.setAttribute("sale", sale);
		  request.setAttribute("quotationAssistant", quotationAssistant);
		  request.setAttribute("saleNewNum", saleNewNum);
		  request.setAttribute("saleOldNum", saleOldNum);
		  request.setAttribute("followNewNum", followNewNum);
		  request.setAttribute("followOldNum", followOldNum);
		  request.setAttribute("purchaseNewNum", purchaseNewNum);
		  request.setAttribute("purchaseOldNum", purchaseOldNum);
		  request.setAttribute("saleUsers", saleUsers);
		  request.setAttribute("purchaseUsers", purchaseUsers);

		  

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "statistic";
	}
	
	
	
	
	
	public static Double getAmount(String str){
	
	   Double amount = 0.0;
	   if(StringUtils.isBlank(str)){
		   return amount;
	   }

      // ?????????????????????????????????????????????(??????)
        Pattern p = Pattern.compile("(\\d+\\.\\d+)");
        //Matcher?????????????????????????????????,??????????????????,????????????Pattern.matcher(CharSequence input)???????????????????????????. 
        Matcher m = p.matcher(str);
        //m.find??????????????????????????????????????????"(\\d+\\.\\d+)"??????????????????
        if (m.find()) {
            //?????????????????????,??????????????????null??????
            //group()???????????????0???????????????????????????1????????????????????????????????????,2???????????????????????????,????????????????????????,???1???0????????????
            str = m.group(1) == null ? "" : m.group(1);
            amount = Double.parseDouble(str);
        } else {
            //????????????????????????????????????????????????
            p = Pattern.compile("(\\d+)");
            m = p.matcher(str);
            if (m.find()) {
                //????????????????????????
                str = m.group(1) == null ? "" : m.group(1);
                amount = Double.parseDouble(str);
            } else {
                //????????????????????????????????????,???????????????????????????????????????????????????
                str = "";
            }
        }	
		System.out.println( amount);
		return amount;
	}
	/**
	 * ??????????????????
	 * @Title selectFactoryUpload 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/selectFactoryUpload")
	public String selectFactoryUpload(HttpServletRequest request,HttpServletResponse response){
		
		try {
			String pageStr = request.getParameter("pageStr"); 
			String selectStr = request.getParameter("selectStr");  // ?????????
			String pageSizeStr = request.getParameter("pageSize"); 
			String userName = WebCookie.getUserName(request);
			if(StringUtils.isNotBlank(userName)){
			}else{
				return "redirect:/index.jsp";
			}
			//??????
			Integer page = null;
			if(StringUtils.isNotBlank(pageStr)){
				page = Integer.parseInt(pageStr);
			}else{
				page = 1;
			}
			//???????????????
			Integer pageSize = null;
			if(StringUtils.isNotBlank(pageSizeStr)){
				pageSize = Integer.parseInt(pageSizeStr);
			}else{
				pageSize = 20;
			}
			QuoteWeeklyReportQuery quoteWeeklyReportQuery = new QuoteWeeklyReportQuery();
			quoteWeeklyReportQuery.setInputKey(selectStr);
			quoteWeeklyReportQuery.setPageNumber(pageSize * (page - 1));
			quoteWeeklyReportQuery.setPageSize(pageSize);
            //????????????????????????????????????
			List<QuoteWeeklyReport> reportList = quoteWeeklyReportService.selectAll(quoteWeeklyReportQuery);
			for (QuoteWeeklyReport quoteWeeklyReport : reportList) {
				ProjectFactory projectFactory = projectFactoryService.selectByProjectNoAndFactoryId(quoteWeeklyReport.getCsgOrderId(), quoteWeeklyReport.getFactoryId());
				if(projectFactory!=null){
					quoteWeeklyReport.setProjectName(projectFactory.getProjectName());
					quoteWeeklyReport.setContractDate(projectFactory.getContractDate());
				}
			}
			//????????????
			QuoteWeeklyReportQuery quoteWeeklyReport = new QuoteWeeklyReportQuery();
			quoteWeeklyReport.setInputKey(selectStr);
			quoteWeeklyReport.setPageNumber(-1);
			List<QuoteWeeklyReport> allReport = quoteWeeklyReportService.selectAll(quoteWeeklyReport);
			int projectListCount = 0;
			if(allReport!=null){
				projectListCount = allReport.size();
			}
			request.setAttribute("selectStr", selectStr==null?"":selectStr);
			request.setAttribute("reportList", reportList);				
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("page", page);
			request.setAttribute("userName", userName);
			request.setAttribute("count", projectListCount);
			//????????????
			Integer lastNum = new BigDecimal(projectListCount).divide(new BigDecimal(pageSize)).setScale(0,BigDecimal.ROUND_UP).intValue();
			request.setAttribute("lastNum", lastNum);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			LOG.error("StatisticsController?????????selectFactoryUpload????????? error", e);
		}
		return "project_upload_list";
		
	}
	/**
	 * 
	 * @Title:StatisticsController
	 * @Description:??????????????????
	   @author wangyang
	 * @param request
	 * @param response
	 * @return JsonResult
	 * @throws
	 */
	@RequestMapping("/customerStatistics")
	@ResponseBody
	public JsonResult customerStatistics(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
	  try {
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	       String customerStartTime=request.getParameter("customerStartTime");
	       String customerEndTime=request.getParameter("customerEndTime");
	       String num=request.getParameter("num");
	       Map<String,String> map = new HashMap<String, String>();
	       List<EmailUser>userList=emailUserService.queryAll();//??????????????????
		  if("1".equalsIgnoreCase(num)){
			map.put("customerStartTime",customerStartTime);
			map.put("customerEndTime",customerEndTime);
			List<User> userList1=new ArrayList<User>();
            for(EmailUser user:userList){
            User user1=new User();
            InvoiceInfo info=new InvoiceInfo();
            info.setCustomerManager(user.getUserName());
            if(customerStartTime!=null&&!"".equalsIgnoreCase(customerStartTime)){
            info.setStartTime(customerStartTime);
            }
            if(customerEndTime!=null&&!"".equalsIgnoreCase(customerEndTime)){
            info.setEndTime(customerEndTime);
            }
            if(customerStartTime==null&&customerEndTime==null){
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE, - 7);
            Date d = c.getTime();
            String day = format.format(d);
            info.setStartTime(day);	
            } 
            //?????????????????????????????????
            InvoiceInfo info1 = invoiceInfoService.selectCountByStatus(info);
            user1.setUserName(user.getUserName());
            user1.setNewCustomerProofingNumber(info1.getNewCustomerProofingNumber()+"");
            user1.setNewCustomerProofingMoney(info1.getNewCustomerProofingMoney()+"");
            user1.setNewCustomerBulkNumber(info1.getNewCustomerBulkNumber()+"");
            user1.setNewCustomerBulkMoney(info1.getNewCustomerBulkMoney()+"");
            user1.setCustomerProofingNumber(info1.getCustomerProofingNumber()+"");
            user1.setCustomerProofingMoney(info1.getCustomerProofingMoney()+"");
            user1.setCustomerBulkNumber(info1.getCustomerBulkNumber()+"");
            user1.setCustomerBulkMoney(info1.getCustomerBulkMoney()+"");
            user1.setReturnItemNumber(info1.getReturnItemNumber()+"");
            user1.setReturnItemMoney(info1.getReturnItemMoney()+"");
            userList1.add(user1);
            }
            String json= JSONArray.fromObject(userList1).toString();
            map.put("userList",json);
           }else if("2".equalsIgnoreCase(num)){
        	map.put("purchaseUser",customerStartTime);
   			map.put("saleUser",customerEndTime);
   			String purchaseUser=null;
   			if(customerStartTime!=null&&!"????????????".equalsIgnoreCase(customerStartTime)){
   				purchaseUser=customerStartTime;
   			}
   			String saleUser=null;
   			if(customerEndTime!=null&&!"????????????/??????".equalsIgnoreCase(customerEndTime)){
   				saleUser=customerEndTime;
   			}
   			List<Project> newAdditions = projectService.selectSemiAnnualProject(ItemClassificationEnum.NEW_ADDITIONS.getCode(),purchaseUser,saleUser);//???????????????
			List<Project> noChangeProject = projectService.selectSemiAnnualProject(ItemClassificationEnum.NO_CHANGE_PROJECT.getCode(),purchaseUser,saleUser);//???????????????
			List<Project> reductionProjects = projectService.selectSemiAnnualProject(ItemClassificationEnum.REDUCTION_Projects.getCode(),purchaseUser,saleUser);//????????????
			String json1= JSONArray.fromObject(newAdditions).toString();
			String json2= JSONArray.fromObject(noChangeProject).toString();
			String json3= JSONArray.fromObject(reductionProjects).toString();
			map.put("newAdditions",json1);
			map.put("noChangeProject",json2);
			map.put("reductionProjects",json3);
   			}else if("3".equalsIgnoreCase(num)){
   				EmailUser user=new EmailUser();
   				if(customerStartTime!=null&&!"".equalsIgnoreCase(customerStartTime)){
   	   	        	Date date = null;
   	   	        	 try {    
   	     	               date = format.parse(customerStartTime);   
   	     	        } catch (Exception e) {    
   	     	               e.printStackTrace();    
   	     	        }  
   	   	            user.setStartDate(date);
   	   	            }
   	   	            if(customerEndTime!=null&&!"".equalsIgnoreCase(customerEndTime)){
   	   	            	Date date = null;
   	      	        	 try {    
   	        	               date = format.parse(customerEndTime);   
   	        	        } catch (Exception e) {    
   	        	               e.printStackTrace();    
   	        	        }  
   	      	        user.setEndDate(date);
   	   	            }
   	   	         if(customerStartTime==null&&customerEndTime==null){
   	   	            Calendar c = Calendar.getInstance();
   	   	            c.setTime(new Date());
   	   	            c.add(Calendar.MONTH, - 6);
   	   	            Date date = c.getTime();
   	   	          user.setStartDate(date);	
   	   	            }  
   				List<EmailUser> userList1=emailUserService.getAverageCompletionTime(user);
   				
   	         String json= JSONArray.fromObject(userList1).toString();
             map.put("averageCompletionTime",json);
   			}else if("4".equalsIgnoreCase(num)){
   			 EmailUser user=new EmailUser();
   			 Calendar cal = Calendar.getInstance();
   			 SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
	         Date date1=cal.getTime();
	         String time1=dft.format(date1);
	         Calendar cal1 = Calendar.getInstance();
             cal1.add(cal1.MONTH, -1);
             Date date2=cal1.getTime();
             String time2=dft.format(date2);
             Calendar cal2 = Calendar.getInstance();
             cal2.add(cal2.MONTH, -2);
             Date date3=cal2.getTime();
             String time3=dft.format(date3);
             user.setThisMonth(time1);
             user.setLastMonth(time2);
             user.setLastMonthtwo(time3);
             List<EmailUser> userList1=emailUserService.getQualityProblem(user);
   			 String json= JSONArray.fromObject(userList1).toString();
             map.put("QualityProblem",json);
   			}else if("5".equalsIgnoreCase(num)){
   				EmailUser user=new EmailUser();
   				if(customerStartTime!=null&&!"".equalsIgnoreCase(customerStartTime)){
    	        	Date date = null;
    	        	 try {    
      	               date = format.parse(customerStartTime);   
      	           } catch (Exception e) {    
      	               e.printStackTrace();    
      	           } 
    	        	   
    	        	 user.setStartDate(date);
    	            }
    	            if(customerEndTime!=null&&!"".equalsIgnoreCase(customerEndTime)){
    	            	Date date = null;
       	        	 try {    
         	               date = format.parse(customerEndTime);   
         	        } catch (Exception e) {    
         	               e.printStackTrace();    
         	        }  
       	        	user.setEndDate(date);
    	            }
    	      if(customerStartTime==null&&customerEndTime==null){
    	    	Calendar cal = Calendar.getInstance();
    	       cal.add(cal.YEAR, -1);
  	           Date date1=cal.getTime();
  	           Calendar cal1 = Calendar.getInstance();
               cal1.add(cal1.MONTH, -6);
               Date date2=cal1.getTime();
               user.setStartDate(date1);
               user.setEndDate(date2);
    	        }
    	      //??????????????????
   			 List<EmailUser> userList1=emailUserService.getSuccessRateProject(user);
  			 String json= JSONArray.fromObject(userList1).toString();
             map.put("SuccessRateProject",json);
   			}else if("6".equalsIgnoreCase(num)){
   				EmailUser user=new EmailUser();
   			 SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
   			 SimpleDateFormat dft1 = new SimpleDateFormat("yyyy-MM");	
   			 Calendar cal = Calendar.getInstance();
             Date date=cal.getTime();
             String time=dft.format(date);
   			 String time1=dft1.format(date);
             Calendar cal2= Calendar.getInstance();
             cal2.add(cal2.MONTH, -1);
             Date date2=cal2.getTime();
             String time2=dft1.format(date2);
               user.setThisMonth(time);
             user.setLastMonth(time1+"-01");
             user.setLastMonthtwo(time2+"-01");
   	          List<EmailUser> userList1=emailUserService.getAllReport(user); 
   	          for(int i=0;i<userList1.size();i++){
   	        	EmailUser user1=userList1.get(i);
   	        	String contractAmount="";
   	        	String projectNo=projectService.getAll(user1.getUserName());
   	        	if(projectNo!=null&&!"".equalsIgnoreCase(projectNo)){
   	        	projectNo=projectNo.replaceAll(",", "','");
   	        	
   	        	contractAmount=itemCaseERPService.getAllContractAmount(projectNo);
   	        	
   	        	user1.setContractAmount(contractAmount);
   	        	}
   	        	
   	        	int	proofingProject=projectService.getProjectS(user1.getUserName(),1);
   	        	int	massProductionProject=projectService.getProjectS(user1.getUserName(),2);
   	        	user1.setProofingProject(proofingProject);
   	        	user1.setMassProductionProject(massProductionProject);
   	        	
   	          }
   	          
   	          
    	        String json= JSONArray.fromObject(userList1).toString();
    	        map.put("TestReport",json);
   			}
		    jsonResult.setOk(true);
			jsonResult.setMessage("????????????");
			jsonResult.setData(map);
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage("????????????");
			e.printStackTrace();
			LOG.error("error", e);
		}
		return jsonResult;
	}
}
