package com.cn.hnust.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




import org.springframework.web.bind.annotation.ResponseBody;





import com.cn.hnust.pojo.EmailCustomer;
import com.cn.hnust.pojo.ProjectSchedule;
import com.cn.hnust.pojo.QuotationNewQuotes;
import com.cn.hnust.pojo.QuotationUser;
import com.cn.hnust.service.IEmailCustomerService;
import com.cn.hnust.service.IQuotationNewQuotesService;
import com.cn.hnust.service.IQuotationUserService;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.SplitPage;



@RequestMapping("/quotation")
@Controller
public class QuotationController {
	private static final int PAGE_SIZE1 = 100;//每页显示的最大数量
	@Autowired
	private IQuotationUserService uservice;
	@Autowired
	private IEmailCustomerService emailCustomerService;
	@RequestMapping("/baojStatistics")
	public String selectAllByDate(HttpServletRequest request,HttpServletResponse response){
	String page1="";
	String num = request.getParameter("num");
	String timeCreening = request.getParameter("timeCreening");
	if(num==null||"".equalsIgnoreCase(num)){
		num="1";
	}
	try{
		if("1".equalsIgnoreCase(num)){
		List<QuotationUser>	userList=uservice.getQuantityQuoted();
		 request.setAttribute("userList", userList);
		 request.setAttribute("fyfz", 1);
		page1="userlist";
		}
		if("2".equals(num)){
			String title="2个月内未及时回复信息的客户列表";
			request.setAttribute("title", title);
			request.setAttribute("fyfz", 2);
		}else if("3".equals(num)){
			String title="2个月内放弃的A/B级客户列表";
			request.setAttribute("title", title);
			request.setAttribute("fyfz", 3);
		}else if("4".equals(num)){
			String title="2个月内立项未回复客户列表";
			request.setAttribute("title", title);
			request.setAttribute("fyfz", 4);
		}else if("5".equals(num)){
			String title="2翻译最近100个项目及平均翻译时间";
			request.setAttribute("title", title);
			int transtime=emailCustomerService.getTransTime(1);
			int transtime1=emailCustomerService.getTransTime(2);
			request.setAttribute("transtime", transtime/100);
			request.setAttribute("transtime1", transtime1/100);
			request.setAttribute("fyfz", 5);
		}else if("6".equals(num)){
			String title="2个月内立项至今无报价员";
			request.setAttribute("title", title);
			request.setAttribute("fyfz", 6);
		}else if("7".equals(num)){
			String title="2个月内立项,有报价员，5天内无报价客户";
			request.setAttribute("title", title);
			request.setAttribute("fyfz", 7);
		}else if("8".equals(num)){
			String title="每个月的 AB级客户";
			request.setAttribute("title", title);
			request.setAttribute("num", num);
			request.setAttribute("fyfz", 8);
		}
		if("9".equals(num)){
			String title="销售工作量统计";
			int timeCreening1=0;
			if(timeCreening!=null&&!"".equalsIgnoreCase(timeCreening)){
				if("1".equals(timeCreening)){
					timeCreening1=14;
				}else if("2".equals(timeCreening)){
					timeCreening1=30;
				}else if("3".equals(timeCreening)){
					timeCreening1=90;
				}else if("4".equals(timeCreening)){
					timeCreening1=180;
				}
				
			}else{
				timeCreening1=14;
			}
			List<QuotationUser>	userList=uservice.getSalesWorkload(timeCreening1);
			request.setAttribute("timeCreening", timeCreening1);
			request.setAttribute("title", title);
			request.setAttribute("userList", userList);
			 request.setAttribute("fyfz", 9);
			page1="userlist";
		}
		
		 if(!"1".equalsIgnoreCase(num)&&!"9".equalsIgnoreCase(num)){
	    String saleName = request.getParameter("saleName");
		String st = request.getParameter("page");
		int page = 1;//默认是第一页
		if(st != null && !"".equals(st)) {
			page = Integer.parseInt(st);
		}
		int start = (page-1) * PAGE_SIZE1;
		EmailCustomer customer=new EmailCustomer();
		customer.setStart(start);
		customer.setEnd(PAGE_SIZE1);
		if(saleName!=null&&!"".equalsIgnoreCase(saleName)){
		customer.setSaleName(saleName);
		}
		if("8".equals(num)){
			 String createTime = request.getParameter("createTime");
			 if(createTime!=null&&!"".equalsIgnoreCase(createTime)){
			customer.setCreateTime(createTime+"-01");
			request.setAttribute("createTime", createTime);
			 }else{
				 Calendar cal = Calendar.getInstance();
	   			 SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
		         Date date1=cal.getTime();
		         String time1=dft.format(date1); 
		         customer.setCreateTime(time1+"-01");
		         request.setAttribute("createTime", time1);
			 }
		}
		
		List<EmailCustomer>emailCustomer=emailCustomerService.getStatisticsPage(customer,num);//最近2个月没得到及时回复	
		for(int i=0;i<emailCustomer.size();i++){
			EmailCustomer cus=emailCustomer.get(i);
			
			String contenta=cus.getYbcontent();
			 contenta = contenta.replaceAll("\\<head>[\\s\\S]*?</head>(?i)", "");//去掉head  
	           contenta = contenta.replaceAll("\\<!--[\\s\\S]*?-->", "");//去掉注释  
	           contenta = contenta.replaceAll("\\<![\\s\\S]*?>", "");  
	           contenta = contenta.replaceAll("\\<style[^>]*>[\\s\\S]*?</style>(?i)", "");//去掉样式  
	           contenta = contenta.replaceAll("\\<script[^>]*>[\\s\\S]*?</script>(?i)", "");//去掉js  
	           contenta = contenta.replaceAll("\\<w:[^>]+>[\\s\\S]*?</w:[^>]+>(?i)", "");//去掉word标签  
	           contenta = contenta.replaceAll("\\<xml>[\\s\\S]*?</xml>(?i)", "");  
	           contenta = contenta.replaceAll("\\<html[^>]*>|<body[^>]*>|</html>|</body>(?i)", "");  
	           contenta = contenta.replaceAll("\\\r\n|\n|\r", "");//去掉换行  
	           contenta = contenta.replaceAll("\\<br[^>]*>(?i)", "");  
	           contenta = contenta.replaceAll("\\</p>(?i)", "");  
	           contenta = contenta.replaceAll("\\<[^>]+>", "");  
	           contenta = contenta.replaceAll("\\ ", " ");  
	           contenta = contenta.replaceAll("&nbsp;", "");
	           if(contenta.length()>100){
	           contenta=contenta.substring(0,100);
	           }
	           cus.setYbcontent(contenta);
	           String content=cus.getContent();
	           String contentb=getContent(content);
			   contentb = contentb.replaceAll("\\<head>[\\s\\S]*?</head>(?i)", "");//去掉head  
	           contentb = contentb.replaceAll("\\<!--[\\s\\S]*?-->", "");//去掉注释  
	           contentb = contentb.replaceAll("\\<![\\s\\S]*?>", "");  
	           contentb = contentb.replaceAll("\\<style[^>]*>[\\s\\S]*?</style>(?i)", "");//去掉样式  
	           contentb = contentb.replaceAll("\\<script[^>]*>[\\s\\S]*?</script>(?i)", "");//去掉js  
	           contentb = contentb.replaceAll("\\<w:[^>]+>[\\s\\S]*?</w:[^>]+>(?i)", "");//去掉word标签  
	           contentb = contentb.replaceAll("\\<xml>[\\s\\S]*?</xml>(?i)", "");  
	           contentb = contentb.replaceAll("\\<html[^>]*>|<body[^>]*>|</html>|</body>(?i)", "");  
	           contentb = contentb.replaceAll("\\\r\n|\n|\r", "");//去掉换行  
	           contentb = contentb.replaceAll("\\<br[^>]*>(?i)", "");  
	           contentb = contentb.replaceAll("\\</p>(?i)", "");  
	           contentb = contentb.replaceAll("\\<[^>]+>", "");  
	           contentb = contentb.replaceAll("\\ ", " ");  
	           contentb = contentb.replaceAll("&nbsp;", "");
		           if(contentb.length()>100){
		        	   contentb=contentb.substring(0,100);
		           }
		        cus.setContent(contentb);
	           
	           
	           
		}
		
		
		int total=emailCustomerService.getStatisticsPageTotal(customer,num);
		SplitPage.buildPager(request, total, PAGE_SIZE1, page);
		request.setAttribute("cusList", emailCustomer);
		
		page1="quotelist";
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return page1;
	}
	private String getContent(String contentb) {
		 String content="";
	 		try{
	             String encoding="utf-8";
	             File file=new File(contentb);
	             if(file.isFile() && file.exists()){ //判断文件是否存在
	                 InputStreamReader read = new InputStreamReader(
	                 new FileInputStream(file),encoding);//考虑到编码格式
	                 BufferedReader bufferedReader = new BufferedReader(read);
	                 StringBuffer buffer = new StringBuffer();
	                 String lineTxt = null;
	                 /*
	                 while((lineTxt = bufferedReader.readLine()) != null){*/
	                 while ((lineTxt = bufferedReader.readLine()) != null){
	                	 buffer.append(lineTxt+" ");
	                }
	                     content=buffer.toString();
	                
	     }else{
	         System.out.println("找不到指定的文件");
	     }
	     } catch (Exception e) {
	         System.out.println("读取文件内容出错");
	         e.printStackTrace();
	     }
	 		return content;
	}
	/**
	 * 
	 * @Title:QuotationController
	 * @Description:修改客户是否属于outlook客户
	   @author wangyang
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException JsonResult
	 * @throws
	 */
	@RequestMapping("/updateOutLookCustomers")
	@ResponseBody
	public JsonResult updateOutLookCustomers(HttpServletRequest request,HttpServletResponse response) throws IOException {
		JsonResult jsonResult = new JsonResult();
		try {
			String cid = request.getParameter("cid");
			String num = request.getParameter("num");
			EmailCustomer cus=new EmailCustomer();
			cus.setCid(Integer.parseInt(cid));
			cus.setOutlookCustomers(Integer.parseInt(num));
			emailCustomerService.updateOutlookCustomers(cus);
			jsonResult.setOk(true);
			jsonResult.setMessage("更新成功");
			return jsonResult;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage(e.getMessage());
			
			return jsonResult;
		}
	}
}
