package com.cn.hnust.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cn.hnust.pojo.FactoryQualityInspectionVideo;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.ProjectReport;
import com.cn.hnust.service.IProjectReportService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.ProjectFactoryService;
import com.cn.hnust.service.impl.task.ProjectDateTask;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.OperationFileUtil;
import com.cn.hnust.util.PropertisUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
@RequestMapping("/report")
public class ProjectReportController {

	@Autowired 
	private IProjectReportService projectReportService;
	@Autowired
	private IProjectService projectServcie;
	@Autowired
	private ProjectDateTask projectDateTask;
	@Autowired
	private ProjectFactoryService projectFactoryService;
	/**
	 * 添加项目汇报
	 * @param file
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/addReport")
	public void upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response,ModelMap model) throws IOException {  
		//图片传
		String reportName=request.getParameter("reportName");
		String report=request.getParameter("report");
		String projectNo=request.getParameter("projectNo");
		String roleNo=request.getParameter("roleNo");
        PropertisUtil prop = new PropertisUtil("config.properties");
        String userId=request.getParameter("userId");
	    String path = prop.get("picPath");
	    String picUrl = prop.get("picUrl");
	    String projectStageStr = request.getParameter("projectStage"); 
	    String newFileName="";
        try {
        	if(file!=null){
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
    			}else{
    				picUrl="";
    			} 
        	}else{
        		picUrl="";
        	}
			   
			
			ProjectReport projectReport=new ProjectReport();
			projectReport.setId(IdGen.uuid());
			projectReport.setProjectNo(projectNo);
			projectReport.setPicUrl(newFileName);
			projectReport.setReport(report);	
			projectReport.setReportName(reportName);
			projectReport.setRoleNo(roleNo);
			projectReport.setStatus(0);
			projectReport.setCreateDate(new Date());
			if(StringUtils.isNotBlank(projectStageStr)){
				projectReport.setProjectStage(Integer.parseInt(projectStageStr));
			}
			projectReportService.addProjectReport(projectReport);
			//更新状态字段
			/*	Project project=projectServcie.selectProjctDetails(projectNo);
			//ProjectDateTask projectDateTask=new ProjectDateTask();
			List<Project> projectList=new ArrayList<Project>();
			projectList.add(project);
			projectDateTask.syncProjectDate(projectList);*/
			String toUrl=request.getContextPath()+"/project/showDetails?projectNo="+projectNo+"&userName="+reportName+"&userId="+userId+"&roleNo="+roleNo;
			response.sendRedirect(toUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}   
    } 
	
	/**
	 * 添加周报图
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadReportFile",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addFactoryFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 String reportImg = request.getParameter("reportImg");
		 PropertisUtil prop = new PropertisUtil("config.properties");
		 String path = prop.get("picPath");
		 File file = new File(path);
		 if  (!file.exists()  && !file .isDirectory())       {         
				file .mkdir();     
		 }  	
		 //调用保存文件的帮助类进行保存文件(文件上传，form表单提交)
		try {
			Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload(request, path+File.separator);
			String fileName = multiFileUpload.get(reportImg);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping("/addReportPC")
	@ResponseBody
	public JsonResult addReportPC(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String reportName=request.getParameter("reportName");
		String report=request.getParameter("report");
		String projectNo=request.getParameter("projectNo");
		String roleNo=request.getParameter("roleNo");
        String userId=request.getParameter("userId");
	    String projectStageStr = request.getParameter("projectStage"); 
	    String addReportImg=request.getParameter("addReportImg");
		ProjectReport projectReport=new ProjectReport();
		projectReport.setId(IdGen.uuid());
		projectReport.setProjectNo(projectNo);
		projectReport.setPicUrl(addReportImg);
		projectReport.setReport(report);	
		projectReport.setReportName(reportName);
		projectReport.setRoleNo(roleNo);
		projectReport.setStatus(0);
		projectReport.setCreateDate(new Date());
		if(StringUtils.isNotBlank(projectStageStr)){
			projectReport.setProjectStage(Integer.parseInt(projectStageStr));
		}
		projectReportService.addProjectReport(projectReport);
		
		//批量更新工厂生产状态
		String factoryList = request.getParameter("factoryList");
		if(StringUtils.isNotBlank(factoryList)){
			List<ProjectFactory> factorys = null;
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ProjectFactory.class);
			factorys = objectMapper.readValue(factoryList,javaType);
			for (ProjectFactory projectFactory : factorys) {
				projectFactory.setUpdateTime(new Date());
			}
			projectFactoryService.updateBatch(factorys);
		}

		
		//更新状态字段
		/*Project project=projectServcie.selectProjctDetails(projectNo);
		//ProjectDateTask projectDateTask=new ProjectDateTask();
		List<Project> projectList=new ArrayList<Project>();
		projectList.add(project);
		projectDateTask.syncProjectDate(projectList);*/
		JsonResult jsonResult=new JsonResult();
		jsonResult.setOk(true);
		jsonResult.setMessage("操作成功");
		return jsonResult;
	}
}
