package com.cn.hnust.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cn.hnust.pojo.StatusEnter;
import com.cn.hnust.service.IStatusEnterService;
import com.cn.hnust.util.HttpClient;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.PropertiesUtils;
import com.cn.hnust.util.PropertisUtil;


/**
 * 销售录入状态
 * @author chenlun
 *
 */
@Controller
@RequestMapping("/statusEnter")
public class StatusEnterController {
	
	@Autowired
	private IStatusEnterService statusEnterService;
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	@RequestMapping("/addStatusEnter")
	public void addStatusEnter(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){  
		String statusType=request.getParameter("statusType");
		String userName=request.getParameter("userName");
		String projectNo=request.getParameter("projectNo");
		String feedback=request.getParameter("feedback");
		String details=request.getParameter("details");	
		String userId=request.getParameter("userId");
		String roleNo=request.getParameter("roleNo");
	    PropertisUtil prop = new PropertisUtil("config.properties");
	    String path = prop.get("picPath");
	    String picUrl = prop.get("picUrl");
	    String newFileName="";
		try{
			String fileName = file.getOriginalFilename();   
			//新的图片名称  
			if(StringUtils.isNotBlank(fileName)){
				//newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));  
				long time = System.currentTimeMillis();
		    	Date date = new Date(time);    	
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    	newFileName=FilenameUtils.removeExtension(fileName)+"&"+sdf.format(date) + "." + FilenameUtils.getExtension(fileName);
				picUrl =picUrl+"/"+newFileName;
				File newFile = new File(path,newFileName); 
				if(!newFile.exists()){  
					newFile.mkdirs();  
				} 
				file.transferTo(newFile);
			} else{
				picUrl="";
			} 
			StatusEnter statusEnter=new StatusEnter();
			statusEnter.setId(IdGen.uuid());
			statusEnter.setProjectNo(projectNo);
			statusEnter.setStatusType(statusType);
			statusEnter.setOperator(userName);
			statusEnter.setFeedback(feedback);
			statusEnter.setFileUrl(newFileName);
			statusEnter.setDetails(details);
			statusEnter.setType("0");
			statusEnter.setCreateDate(new Date());
			statusEnterService.addStatusEnter(statusEnter);
			Map<String, String> parameters=new HashMap<String, String>();
			JSONObject object = JSONObject.fromObject(statusEnter);
			parameters.put("statusEnter", object.toString());
			try {
				HttpClient.sendPost(reader.getProperty("nbmail_path")+"/helpServlet?action=publicComment&className=ExternalinterfaceServlet", parameters);
			} catch (Exception e) {
				
			}finally{
				String toUrl=request.getContextPath()+"/project/showDetails?projectNo="+projectNo+"&userId="+userId+"&roleNo="+roleNo+"&userName="+userName;
				response.sendRedirect(toUrl);  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 同步NBMai的项目状态信息
	 * @param request
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "/synchProjectStatus", method = RequestMethod.POST)
	public void synchProjectStatus(HttpServletRequest request,@RequestParam Map<String,String> map){
		 try {
			String jsonStr = map.get("enterStatus");
			 JSONObject jsonObj = JSONObject.fromObject(jsonStr);
			 String projectNo = jsonObj.getString("projectNo");
			 String userName=jsonObj.getString("operator");
			 String fileName=jsonObj.getString("fileUrl");
			 String details=jsonObj.getString("details");
			 StatusEnter statusEnter=new StatusEnter();
			 statusEnter.setId(IdGen.uuid());
			 statusEnter.setProjectNo(projectNo);
			 statusEnter.setOperator(userName);
			 statusEnter.setFileUrl(fileName);
			 statusEnter.setDetails(details);
			 statusEnter.setType("1");
			 statusEnter.setCreateDate(new Date());
			 statusEnterService.addStatusEnter(statusEnter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
