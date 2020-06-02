package com.cn.hnust.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.component.RpcEfficiencyReportHelper;
import com.cn.hnust.component.RpcReportHelper;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.QualityAndEfficiencyReport;
import com.cn.hnust.service.IQualityAndEfficiencyReportService;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.OperationFileUtil;
import com.cn.hnust.util.UploadAndDownloadPathUtil;



@Controller
@RequestMapping("/qualityAndEfficiencyReport")
public class QualityAndEfficiencyReportController {
	@Autowired
	private IQualityAndEfficiencyReportService qualityAndEfficiencyReportService;
	/**
	 * 
	 * @Title:QualityAndEfficiencyReportController
	 * @Description:上传文件
	   @author wangyang
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	
	@RequestMapping(value="/uploadProductFile",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addFactoryFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 String AnalysisReportpic = request.getParameter("allDocuments");
		 String projectNo=request.getParameter("itemNumber");
		 String category=request.getParameter("category");
		 String userName=request.getParameter("userName");
		 String path = UploadAndDownloadPathUtil.getProductImg();
		 File file = new File(path);
		 if  (!file.exists()  && !file .isDirectory())       {         
				file .mkdir();     
		 }  	
		 //调用保存文件的帮助类进行保存文件(文件上传，form表单提交)
		try {
			Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_file(request, path+File.separator,projectNo);
			String fileName = multiFileUpload.get(AnalysisReportpic);
			QualityAndEfficiencyReport report=new QualityAndEfficiencyReport();
			report.setFileName(fileName);
			report.setProjectNo(projectNo);
			report.setUploader(userName);
			report.setUploadTime(new Date());
			report.setCategory(Integer.parseInt(category));
			int num=qualityAndEfficiencyReportService.insertSelective(report);
			RpcEfficiencyReportHelper.sendRequest("", report);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
}
