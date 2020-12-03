package com.cn.hnust.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.hnust.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.component.RpcNBEmailHelper;
import com.cn.hnust.component.RpcQuotationStatistic;
import com.cn.hnust.dao.QualityReportMapper;
import com.cn.hnust.dingding.Dingtalk;
import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.pojo.Products;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectComplaintQuery;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.QualityReportQuery;
import com.cn.hnust.pojo.ShippingConfirmationQuery;
import com.cn.hnust.pojo.User;
import com.cn.hnust.print.ProjectStatisticsPrint;
import com.cn.hnust.service.FactoryQualityInspectionVideoService;
import com.cn.hnust.service.IFeedbackService;
import com.cn.hnust.service.IProductsService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.IQualityReportService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.service.ProjectComplaintService;
import com.cn.hnust.service.ShippingConfirmationService;


import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.taobao.api.ApiException;
/***
 * 用 户  Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	@Autowired
	private IProjectTaskService projectTaskService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IFeedbackService feedbackService;
	@Autowired
	private ProjectComplaintService projectComplaintService;
	@Autowired
	private ShippingConfirmationService shippingConfirmationService;
	@Autowired
	private IQualityReportService qualityReportService;
	@Autowired
	private IProductsService productsService;
	 @Autowired
		private FactoryQualityInspectionVideoService factoryQualityInspectionVideoService;
	
	private static final int UNSOLVE = 0;  //未完成投诉
	
    private static PropertiesUtils reader = new PropertiesUtils("dingtalk.properties");
    private static String CORP_ID = reader.getProperty("CorpId");
    private static String CORP_SECRET = reader.getProperty("CorpSecret");
    private static long AgentID = Integer.parseInt(reader.getProperty("AgentID"));
    private static final Log LOG = LogFactory.getLog(UserController.class);
	
	/**
	 * 查询以后是否存在
	 * @param request
	 * @param
	 * @return 
	 * @return
	 */
	@RequestMapping("/showUser")
	@ResponseBody
	public JsonResult showUser(HttpServletRequest request,HttpServletResponse response){

		String userName=request.getParameter("userName");
	    String password=request.getParameter("password");
		User user =userService.selectUser(userName, password);
		JsonResult json =new JsonResult();
		if(user!=null){
			request.getSession().setAttribute(request.getSession().getId(), userName);
			//登录保存cookie
		    Cookie userCookie = new Cookie("name",userName);       
		    userCookie.setPath("/");
		    userCookie.setMaxAge(60*60*24*365);
		    response.addCookie(userCookie);
		    
			//登录保存cookie
		    Cookie userRoleCookie = new Cookie("role",user.getRoleNo().toString());       
		    userRoleCookie.setPath("/");
		    userRoleCookie.setMaxAge(60*60*24*365);
		    response.addCookie(userRoleCookie);
			
			json.setOk(true);
			json.setMessage("登录成功！");
			json.setData(user);
			SessionIdUtil.setUserName(request.getSession().getId(), userName);
		}else{
			json.setOk(false);
			json.setMessage("用户名或密码错误！");
		}	
		return json;
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @Title:UserController
	   @author wangyang
	 * @param request
	 * @param response
	 * @return String
	 * @throws
	 */
	@RequestMapping("/ERPshowUser")
	public void ERPshowUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String userInfo = request.getParameter("userInfo");
		String name=Base64Encode.getFromBase64(userInfo);
		String []mad=name.split(",");
		String userName=mad[0];
		String password=mad[1];
		User user =userService.findUserByName(userName);
		Cookie userCookie = new Cookie("name",userName);       
		userCookie.setPath("/");
		userCookie.setMaxAge(60*60*24*365);
		response.addCookie(userCookie);
		
		//登录保存cookie
	    Cookie userRoleCookie = new Cookie("role",user.getRoleNo().toString());       
	    userRoleCookie.setPath("/");
	    userRoleCookie.setMaxAge(60*60*24*365);
	    response.addCookie(userRoleCookie);
		
		String toUrl="http://117.144.21.74:10010/user/toIndex";
		response.sendRedirect(toUrl);
	}
	
	@RequestMapping("/toShowTask")
	public void toShowTask(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String userInfo = request.getParameter("userInfo");
		String name=Base64Encode.getFromBase64(userInfo);
		String []mad=name.split(",");
		String userName=mad[0];
		String password=mad[1];
		User user =userService.findUserByName(userName);
		Cookie userCookie = new Cookie("name",user.getUserName());       
		userCookie.setPath("/");
		userCookie.setMaxAge(60*60*24*365);
		response.addCookie(userCookie);
		
		//登录保存cookie
	    Cookie userRoleCookie = new Cookie("role",user.getRoleNo().toString());       
	    userRoleCookie.setPath("/");
	    userRoleCookie.setMaxAge(60*60*24*365);
	    response.addCookie(userRoleCookie);
		
		String toUrl="http://117.144.21.74:10010/user/toIndex";
		response.sendRedirect(toUrl);
	}
	/**
	 * 跳转个人中心
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String toIndex(HttpServletRequest request,HttpServletResponse response){
		try {
			
			String userId = request.getParameter("userId");
			String purchaseNameId=request.getParameter("purchaseNameId");
//			String userName=request.getParameter("userName");
			String userInfo=Base64Encode.getBase64("ninaZhao,zhao2015con");
			Integer roleNo = null;
			//登录保存cookie
           String name = WebCookie.getUserName(request);
			if(StringUtils.isNotBlank(name)){
				User user = userService.findUserByName(name);
				roleNo = user.getRoleNo();
				userId = user.getId()+"";
				request.setAttribute("user", user);
				String userInfo1 = Base64Encode.getBase64(user.getUserName()
						+ "," + user.getPassword());
				request.setAttribute("userInfo", userInfo);
				if(user.getRoleNo()==5 ||"jerrylong".equalsIgnoreCase(name)){
				RpcQuotationStatistic rpc = new RpcQuotationStatistic();
				int num = rpc.sendRequest1(null, name);
				request.setAttribute("num", num);
				String unquotedItems = rpc.sendRequest2(null, name);
				if(unquotedItems!=null&&!"".equalsIgnoreCase(unquotedItems)){
				String[]unquotedItem=unquotedItems.split(";");
				request.setAttribute("unquotedItem1", unquotedItem[0]);
				request.setAttribute("unquotedItem2", unquotedItem[1]);
				}
				}
			}else{
				return "redirect:/index.jsp";
			}
			request.setAttribute("userId", userId);
			request.setAttribute("roleNo", roleNo);
			request.setAttribute("purchaseNameId", purchaseNameId);
			request.setAttribute("userName", name);
			User user = userService.findUserByName(name);
			Project project=new Project();
			project.setUserName(name);
			project.setEmailUserId(Integer.parseInt(userId));
			List<Integer> projectTypeList=new ArrayList<Integer>();
			projectTypeList.add(OrderStatusEnum.NORMAL_ORDER.getCode());
			project.setProjectStatusS(projectTypeList);
			project.setRoleNo(roleNo);
			//project.setProjectStatusS(OrderStatusEnum.NORMAL_ORDER.getCode());
			if("jerrylong".equalsIgnoreCase(name)){
			request.setAttribute("userName", "jerrylong");
			project.setUserName(null);
			project.setEmailUserId(0);
			}	
			project.setProjectStatus(1);
		if(user.getRoleNo()==5 ||"jerrylong".equalsIgnoreCase(name)){	
		int documentaryItemNumber = projectService.findAllCount(project);// 查询记录条数
		int numberOfItemsNotUpdated1 = feedbackService.getNumberOfItemsNotUpdated(project);// 查询未更新项目数记录条数
		int numberOfItemsNotUpdated=documentaryItemNumber-numberOfItemsNotUpdated1;
		int numberOfDifficultProjects = projectService.getNumberOfDifficultProjects(project);// 查询疑难项目数
		List<Integer> delayStatusList = new ArrayList<Integer>();
        delayStatusList.add(1);
        project.setDelayStatusS(delayStatusList);
        int numberOfDeferredItems = projectService.findProjectListCount(project);// 查询延期项目数
		int numberOfDocumentaryItemsOver3Months = projectService.getNumberOfDocumentaryItemsOver3Months(project);// 查询超过3个月跟单项目数
		List<Project>list=projectService.getNumberOfProjectsCompletedInOneMonth(project);
		int numberOfProjectsCompletedInOneMonth=list.size();
		int noFollowUpItems=0;
		String parameter="0";
		String projectNos="";
		for(int i=0;i<list.size();i++){
			Project project1=list.get(i);
		projectNos +=","+project1.getProjectNo();
		}
		if(projectNos!=null&&!"".equals(projectNos)){
		 RpcNBEmailHelper rpc = new RpcNBEmailHelper();
		parameter = rpc.sendRequest(null, projectNos);
		}
		noFollowUpItems=numberOfProjectsCompletedInOneMonth-Integer.parseInt(parameter);
		request.setAttribute("documentaryItemNumber", documentaryItemNumber);
		request.setAttribute("noFollowUpItems", noFollowUpItems);
		request.setAttribute("numberOfDifficultProjects", numberOfDifficultProjects);
		request.setAttribute("numberOfItemsNotUpdated", numberOfItemsNotUpdated);
		request.setAttribute("numberOfProjectsCompletedInOneMonth", numberOfProjectsCompletedInOneMonth);
		request.setAttribute("notContactingCustomersForOneMonth", parameter);
		request.setAttribute("numberOfDeferredItems", numberOfDeferredItems);
		request.setAttribute("numberOfDocumentaryItemsOver3Months", numberOfDocumentaryItemsOver3Months);
		}
		/*if(roleNo<8) {
			//查询未完成任务数量
			Integer noFinishCount = 0;
			ProjectTask projectTask = new ProjectTask();
			projectTask.setUserName(name);
			projectTask.setTaskStatus("0");
			projectTask.setSendOrReceive(2);
			noFinishCount = projectTaskService.selectProjectTaskCountTotal();
		}*/
		new ShippingConfirmationThread(user,shippingConfirmationService,projectComplaintService,qualityReportService,factoryQualityInspectionVideoService).start();
		//查询未完成投诉数量
		/*ProjectComplaintQuery projectComplaintQuery = new ProjectComplaintQuery();
		projectComplaintQuery.setIsSolve(UNSOLVE);
		projectComplaintQuery.setRoleNo(roleNo);
		projectComplaintQuery.setUserId(userId);
		projectComplaintQuery.setZhijian1(name);
		projectComplaintQuery.setZhijian2(name);
		projectComplaintQuery.setZhijian3(name);*/
		
		//int unFinishComplaintCount = projectComplaintService.queryCount(projectComplaintQuery);
		//查询出货单未填写数量
		/*ShippingConfirmationQuery shippingConfirmationQuery = new ShippingConfirmationQuery();
		shippingConfirmationQuery.setRoleNo(roleNo);
		shippingConfirmationQuery.setIsComplete(0);
		shippingConfirmationQuery.setUserName(name);*/
		//int unFinishShippingCount = shippingConfirmationService.count(shippingConfirmationQuery);
		//查询质检报告数量
		/*QualityReportQuery qualityReportQuery = new QualityReportQuery();
		qualityReportQuery.setRoleNo(roleNo);
		qualityReportQuery.setUserName(name);
		qualityReportQuery.setIsComment(1); //查询未评论数量*/
		//int qualityCount = qualityReportService.totalReports(qualityReportQuery);
		request.setAttribute("userInfo", userInfo);
		//request.setAttribute("noFinishCount", noFinishCount);
		//request.setAttribute("unFinishComplaintCount", unFinishComplaintCount);
		//request.setAttribute("unFinishShippingCount", unFinishShippingCount);
		//request.setAttribute("qualityCount", qualityCount);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "select";
	}
	
	
	/**
	 * 钉钉免密登录
	 * @Title dingtalkLogin 
	 * @Description
	 * @param request
	 * @param response
	 * @throws IOException
	 * @return void
	 */
	@ResponseBody
	@RequestMapping("/dingtalkLogin")
	public void dingtalkLogin(HttpServletRequest request,HttpServletResponse response){
		   try {
			   String code = request.getParameter("code");
			   String accessToken = Dingtalk.getAccessToken(CORP_ID, CORP_SECRET);
			   DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
			   OapiUserGetuserinfoRequest qRequest = new OapiUserGetuserinfoRequest();
			   qRequest.setCode(code);
			   qRequest.setHttpMethod("GET");
			   OapiUserGetuserinfoResponse qresponse = client.execute(qRequest, accessToken);
			   String userId = qresponse.getUserid();
			   User user = userService.selectByDingTalkId(userId);
			   if(user != null){
				    Cookie userCookie = new Cookie("name",user.getUserName());       
					userCookie.setPath("/");
					userCookie.setMaxAge(60*60*24*365);
					response.addCookie(userCookie);
					
					//登录保存cookie
				    Cookie userRoleCookie = new Cookie("role",user.getRoleNo().toString());       
				    userRoleCookie.setPath("/");
				    userRoleCookie.setMaxAge(60*60*24*365);
				    response.addCookie(userRoleCookie);
			   }
			} catch (ApiException e) {
				e.printStackTrace();
			}
	}
	
	
	
	/**
	 * script标签发起的请求是get类型，将实现写入doGet方法中
	 */
	@RequestMapping("/setCookie")
	public void setCookie(HttpServletRequest request,HttpServletResponse response){
		// 将要写入的cookie项，调用者通过参数传递
		String cookieName = request.getParameter("cname");
		if(StringUtils.isNotBlank(cookieName)){
			// 生成cookie 
			Cookie cookie = new Cookie("name", cookieName);
			cookie.setPath("/"); //www.a.com
			response.addCookie(cookie);
		}
	}
	/**
	 * 
	 * @Title:UserController
	 * @Description:导出质检投诉任务列表
	   @author wangyang
	 * @param request
	 * @param response void
	 * @throws
	 */
			@RequestMapping(value="/qualityInspectionComplaints")
			public void qualityInspectionComplaints(HttpServletRequest request,HttpServletResponse response){
				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			        Calendar c = Calendar.getInstance();
			        c.setTime(new Date());
			        c.set(Calendar.DAY_OF_MONTH, 1);
					c.set(Calendar.HOUR_OF_DAY, 0);
					c.set(Calendar.MINUTE, 0);
					c.set(Calendar.SECOND, 0);
			        c.add(Calendar.MONTH, -2);
			        Date m = c.getTime();
			        
			        Calendar c1 = Calendar.getInstance();
			        c1.setTime(new Date());
			        c1.set(Calendar.DAY_OF_MONTH, 1);
					c1.set(Calendar.HOUR_OF_DAY, 0);
					c1.set(Calendar.MINUTE, 0);
					c1.set(Calendar.SECOND, 0);
					c1.add(Calendar.MONTH, -1);
			        Date n= c1.getTime();
			        List<User> sampleFinishes = userService.selectComplaint(m,n);
					String excelPath = ProjectStatisticsPrint.printExcel2(request, sampleFinishes);
					File outFile = new File(excelPath);  
					InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));  
					byte[] buffer = new byte[fis.available()];  
					fis.read(buffer);  
					fis.close();  
					// 清空response  
					response.reset();  
					// 设置response的Header  
					Date startDate = DateFormat.addMonth(new Date(), -2);
					String fileName = "质检投诉列表"+DateFormat.date2String(startDate)+"~"+DateFormat.currentDate()+".xls";
					fileName = URLEncoder.encode(fileName, "utf-8");                                  //这里要用URLEncoder转下才能正确显示中文名称  
					response.addHeader("Content-Disposition", "attachment;filename=" + fileName);  
					response.addHeader("Content-Length", "" + outFile.length());  
					OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
					response.setContentType("application/octet-stream");  
					toClient.write(buffer);  
					toClient.flush();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}	
	
	

}
