package com.cn.hnust.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.QualityPicExplain;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.service.IQualityPicExplainService;
import com.cn.hnust.service.IQualityReportService;
import com.cn.hnust.util.JsonResult;

@Controller
@RequestMapping("/qualityPic")
public class QualityPicExplainController {
    
	@Autowired
	private IQualityPicExplainService qualityPicExplainService;
	
	@Autowired
	private  IQualityReportService qualityReportService;
	
	@RequestMapping("/addQualityPicExplain")
	@ResponseBody
	public JsonResult addQualityPicExplain(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
	    String picName=request.getParameter("picName");
	    String qualityReportId=request.getParameter("qualityReportId");
	    String projectNo=request.getParameter("picProjectNo");
	    String picExplain=request.getParameter("picExplain");
	    
	    QualityPicExplain qualityPicExplain=new QualityPicExplain();
	    qualityPicExplain.setPicExplain(picExplain);
	    qualityPicExplain.setPicName(picName);
	    qualityPicExplain.setProjectNo(projectNo);
	    qualityPicExplain.setQualityReportId(Integer.parseInt(qualityReportId));
	    qualityPicExplain.setCreateDate(new Date());
	    try {
	    	QualityPicExplain selectPicExplain=qualityPicExplainService.selectQualityPicExplainById(picName);
	    	if(selectPicExplain!=null){
	    		selectPicExplain.setPicExplain(picExplain);
	    		qualityPicExplainService.updateQualityPicExplain(selectPicExplain);
	    	}else{
	    		qualityPicExplainService.addQualityPicExplain(qualityPicExplain);	
	    	}
			jsonResult.setOk(true);
			jsonResult.setMessage("操作成功");
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage("操作失败");
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	@RequestMapping("/selectQualityPicExplain")
	@ResponseBody
	public JsonResult selectQualityPicExplain(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
	    String picName=request.getParameter("picName");
	    try {
	    	QualityPicExplain qualityPicExplain=qualityPicExplainService.selectQualityPicExplainById(picName);
            jsonResult.setData(qualityPicExplain);
			jsonResult.setOk(true);
		} catch (Exception e) {
			jsonResult.setOk(false);
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	@RequestMapping("/addAllQualityPicExplain")
	@ResponseBody
	public JsonResult addAllQualityPicExplain(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
		try {
	    	/*QualityPicExplain selectPicExplain=qualityPicExplainService.selectQualityPicExplainById(picName);
	    	if(selectPicExplain!=null){
	    		selectPicExplain.setPicExplain(picExplain);
	    		qualityPicExplainService.updateQualityPicExplain(selectPicExplain);
	    	}else{
	    		qualityPicExplainService.addQualityPicExplain(qualityPicExplain);	
	    	}*/
			//将质检报告表图片路径拆分
			List<QualityReport> qualityReportList=qualityReportService.selectByProjectNo(null);
			for (int i = 0; i < qualityReportList.size(); i++) {
				QualityReport qualityReport=qualityReportList.get(i);
				String picUrls[]=qualityReport.getPicUrl().split(";");
				for (int j = 0; j < picUrls.length; j++) {
				    QualityPicExplain qualityPicExplain=new QualityPicExplain();
				    qualityPicExplain.setPicExplain("");
				    qualityPicExplain.setPicName(picUrls[j]);
				    qualityPicExplain.setProjectNo(qualityReport.getProjectNo());
				    qualityPicExplain.setQualityReportId(qualityReport.getId());
				    qualityPicExplain.setCreateDate(new Date());
				    qualityPicExplainService.addQualityPicExplain(qualityPicExplain);	
				}
			}
			
			jsonResult.setOk(true);
			jsonResult.setMessage("操作成功");
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage("操作失败");
			e.printStackTrace();
		}
		return null;
	}
	
	
}
