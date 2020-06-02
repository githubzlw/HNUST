package com.cn.hnust.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cn.hnust.pojo.TaskReport;
import com.cn.hnust.service.ITaskReportService;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.PropertisUtil;

@Controller
@RequestMapping("/taskReport")
public class TaskReportController {

	@Autowired
	private ITaskReportService taskReportService;
	/**
	 * 任务进展汇报
	 * @param file
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/addReport")
	public void upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response,ModelMap model){  
		String reportName=request.getParameter("reportName");
		String userName=request.getParameter("userName");
		String taskReport=request.getParameter("taskReport");
		String projectNo=request.getParameter("projectNo");
		String taskId=request.getParameter("id");	
		String userId=request.getParameter("userId");
		String roleNo=request.getParameter("roleNo");
	    PropertisUtil prop = new PropertisUtil("config.properties");
	    String path = prop.get("picPath");
	    String picUrl = prop.get("picUrl");
	    String newFileName="";
	try {
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
		TaskReport report=new TaskReport();
		report.setId(IdGen.uuid());
		report.setTaskId(taskId);
		report.setProjectNo(projectNo);
		report.setReportName(reportName);
		report.setTaskReport(taskReport);
		report.setPicUrl(newFileName);
		report.setCreateDate(new Date());
		taskReportService.addTaskReport(report);
		String toUrl=request.getContextPath()+"/task/taskDetails?projectNo="+projectNo+"&id="+taskId+"&userId="+userId+"&roleNo="+roleNo+"&userName="+userName;
		response.sendRedirect(toUrl);  
	} catch (Exception e) {
		e.printStackTrace();
	}
    }  
}
