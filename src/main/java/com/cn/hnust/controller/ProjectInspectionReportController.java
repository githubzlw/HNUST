package com.cn.hnust.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cn.hnust.pojo.DrawingSheet;
import com.cn.hnust.pojo.InspectionPlanComments;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectDrawing;
import com.cn.hnust.pojo.ProjectInspectionReport;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.service.ErpReportService;
import com.cn.hnust.service.IDrawingSheetService;
import com.cn.hnust.service.IInspectionPlanCommentsService;
import com.cn.hnust.service.IProjectDrawingService;
import com.cn.hnust.service.IProjectInspectionReportService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.IdGen;

@Controller
@RequestMapping("/inspectionReport")
public class ProjectInspectionReportController {
   
	@Autowired
	private IProjectInspectionReportService projectInspectionReportService;
	@Autowired
	private IProjectDrawingService projectDrawingService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IDrawingSheetService drawingSheetService;
	@Autowired
	private IInspectionPlanCommentsService inspectionPlanCommentsService;
	@Autowired
	private ErpReportService erpReportService;
	@Scheduled(cron="0 0 1 * * ?") //每天凌晨30分执行
	@RequestMapping("/synchProjectDrawing")
	public void synchProjectDrawing()throws Exception {
		
		List<Project>projectList=projectService.getNoDrawing();
		   List<ProjectDrawing> projectDrawingList=new ArrayList<ProjectDrawing>();
		   for(int j=0;j<projectList.size();j++){
			  List<DrawingSheet>sheetList=drawingSheetService.getAll(projectList.get(j).getProjectNo());
				for(int i=0;i<sheetList.size();i++){
					DrawingSheet drawing=sheetList.get(i);	
				ProjectDrawing projectDrawing=new ProjectDrawing();
				projectDrawing.setId(IdGen.uuid());
				projectDrawing.setProjectNo(drawing.getProjectNo());
				projectDrawing.setDrawingName(drawing.getDrawingName());
				projectDrawing.setRemark(drawing.getRemark());
				projectDrawing.setCreateDate(drawing.getCreateDate());
				projectDrawing.setCategory(String.valueOf(4));
				projectDrawing.setName(drawing.getName());
				projectDrawing.setUploadTime(drawing.getCreateDate());
				projectDrawingService.addProjectDrawing(projectDrawing);
				}
				
		   }
		
		
		
		 ProjectInspectionReport inspectionReport=projectInspectionReportService.getLast();
		 QualityReport report=new QualityReport();
		 if(inspectionReport!=null){
		 report.setId(inspectionReport.getEid());
		 }else{
			 report.setId(0);	 
		 }
		 List<QualityReport>ErpReportList=erpReportService.getAllReport(report);
		 List<ProjectInspectionReport> inspectionReportList=new ArrayList<ProjectInspectionReport>();
		 for(int i=0;i<ErpReportList.size();i++){
		 ProjectInspectionReport inspectionReport1=new ProjectInspectionReport();
		 inspectionReport1.setId(IdGen.uuid());
		 inspectionReport1.setProjectNo(ErpReportList.get(i).getProjectNo());
	     inspectionReport1.setReportName(ErpReportList.get(i).getPicUrl());
		 inspectionReport1.setCreateDate(ErpReportList.get(i).getCreatetime());
		 if("JIANYANBAOGAO".equalsIgnoreCase(ErpReportList.get(i).getErpType())){
			 inspectionReport1.setCategory(1);	 
		 }else if("JIANYANJIHUAZJ".equalsIgnoreCase(ErpReportList.get(i).getErpType())){
			 inspectionReport1.setCategory(2); 
		 }
		 inspectionReport1.setEid(ErpReportList.get(i).getId());
		 inspectionReportList.add(inspectionReport1);
		
		// projectInspectionReportService.insertSelective(inspectionReport1);
		 }
		 if(inspectionReportList.size()>0){
		 projectInspectionReportService.batchAddInspectionReport(inspectionReportList);
		 }
		   QualityReport qureport=erpReportService.getAllReportDingDing();//获取最后一条钉钉拉取数据
		   ProjectInspectionReport insreport =new ProjectInspectionReport();
		   if(qureport!=null){
		   insreport.setProjectNo(qureport.getProjectNo());
		   insreport.setCreateDate(qureport.getCreatetime());
		   }
		   List<ProjectInspectionReport> insReportList=projectInspectionReportService.getall(insreport);//获取还未添加到ERP上检验计划
		   List<QualityReport>QualityReport=new ArrayList<QualityReport>();
		   for(int i=0;i<insReportList.size();i++){
			   QualityReport qureport1=new QualityReport();
			   qureport1.setProjectNo(insReportList.get(i).getProjectNo());
			   String content=insReportList.get(i).getEmailContent();
			   
			   content=content.replaceAll("(\\\\r\\\\n|\\\\r|\\\\n|\\\\n\\\\r)", "<br />");
			   List<InspectionPlanComments>comments=inspectionPlanCommentsService.getAll(insReportList.get(i).getProcessInstanceId());
			   String content1="";
			   for(int j=0;j<comments.size();j++){
				   InspectionPlanComments plan= comments.get(j);
				   content1+="<br/>"+ plan.getName()+"评论:"+plan.getOpinion();  
			   }
			   content=content+content1;
			   qureport1.setConcreteContent(content);
			   qureport1.setCreatetime(insReportList.get(i).getCreateDate());
			   qureport1.setPicUrl(insReportList.get(i).getReportName());
			   qureport1.setErpType("JIANYANJIHUAZJ");
			   qureport1.setUser("ninazhao");
			   QualityReport.add(qureport1); 
			   
		   }
		   if(QualityReport.size()>0){
		   erpReportService.batchAddInspectionReport(QualityReport);//ERP添加数据
		   }
		   
		   
		   
		   
		   
		   
		   
		}
	   
	
	
		 
	}

