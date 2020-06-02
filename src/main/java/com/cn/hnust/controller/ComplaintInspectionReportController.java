package com.cn.hnust.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.ComplaintInspectionReport;
import com.cn.hnust.pojo.ProjectComplaint;
import com.cn.hnust.service.ComplaintInspectionReportService;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.WebCookie;
@Controller
@RequestMapping("/complaintInspectionReport")
public class ComplaintInspectionReportController {
	@Autowired
	ComplaintInspectionReportService complaintInspectionReportService;
	/**
	 * 删除产品检验报告
	 * @Title delete 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
		try {
			String id = request.getParameter("id");   //项目号
			//查询cookie中用户
			String userName = WebCookie.getUserName(request);
			if(StringUtils.isBlank(userName)){
				jsonResult.setOk(false);
				jsonResult.setMessage("请先登录");
			}				
			if(StringUtils.isNotBlank(id)){
				ComplaintInspectionReport projectComplaint = complaintInspectionReportService.selectByPrimaryKey(Integer.parseInt(id));
				if(userName.equals(projectComplaint.getReviewer()) || userName.equals("ninazhao")){
					complaintInspectionReportService.deleteByPrimaryKey(Integer.parseInt(id));
					jsonResult.setOk(true);
					jsonResult.setMessage("已删除");
				}else{
					jsonResult.setOk(false);
					jsonResult.setMessage("你没有权限删除");
				}
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage("删除失败");
			
	
		}	 
		return jsonResult;
	}
}
