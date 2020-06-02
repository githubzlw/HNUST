package com.cn.hnust.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.InspectionPlanInfo;
import com.cn.hnust.service.IInspectionPlanInfoService;
import com.cn.hnust.util.JsonResult;

@Controller
@RequestMapping("/plan")
public class InspectionPlanInfoController {
   
	@Autowired
	private IInspectionPlanInfoService inspectionPlanInfoService;
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toSelectPlan")
	public String toSelectInspection(HttpServletRequest request,HttpServletResponse response){
		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");

		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		return "plan_list";
	}
	
	@RequestMapping("/selectInspectionPlan")
	@ResponseBody
	public JsonResult selectInspectionPlan(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
		String projectNo=request.getParameter("projectNo");
		String userId=request.getParameter("userId");
		String roleNo=request.getParameter("roleNo");
		String userName=request.getParameter("userName");
		InspectionPlanInfo planInfo=new InspectionPlanInfo();
		int pageNumber;
		if(StringUtils.isNotBlank(request.getParameter("pageNumber"))){
			pageNumber=Integer.parseInt(request.getParameter("pageNumber"));//第几页,1,2
		}else{
			pageNumber=1;
		}
		Integer pageSize=10;
		String pageSizeStr = request.getParameter("pageSize");
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		planInfo.setPageSize(pageSize);
		planInfo.setPageNumber(pageSize*(pageNumber-1));
		planInfo.setProjectNo(projectNo);
		List<InspectionPlanInfo> planList=inspectionPlanInfoService.selectInspectionPlan(planInfo);
		int count=inspectionPlanInfoService.selectInspectionPlanCount(planInfo);
		

		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("planList", planList);
		hashMap.put("userId", userId);
		hashMap.put("roleNo", roleNo);
		hashMap.put("userName", userName);
		hashMap.put("pageSize", pageSize);
		hashMap.put("pageNumber", pageNumber);
		hashMap.put("count", count);
		jsonResult.setData(hashMap);
		jsonResult.setOk(true);
		return jsonResult;
	}
}
