package com.cn.hnust.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.enums.DetailStatusEnum;
import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.enums.ProjectStatusEnum;
import com.cn.hnust.pojo.Feedback;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.service.IFeedbackService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.JsonResult;

@RequestMapping("/feedback")
@Controller
public class FeedbackController {

	@Autowired
	private IFeedbackService feedbackService;
	@Autowired
	private IProjectService projectService;
	
	@RequestMapping("/addFeedback")
	@ResponseBody
	public JsonResult addFeedback(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
		String projectNo=request.getParameter("projectNo");
		String userName=request.getParameter("userName");
		String feedbackStr=request.getParameter("feedback");
		String detailStatus=request.getParameter("detailStatus");
		Feedback feedback=new Feedback();
		feedback.setProjectNo(projectNo);
		feedback.setFeedback(feedbackStr);
		feedback.setOperator(userName);
		feedback.setCreateDate(new Date());
		
		Project project=new Project();
		project.setProjectNo(projectNo);
		project.setDetailStatus(Integer.parseInt(detailStatus));	
		//如果是返工重做，将项目更新状态为进行中    
		//polo 2019.5.14
		if(DetailStatusEnum.RE_SAMPLE.getValue().equals(feedbackStr)){
			project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
			project.setFinishTime(null);
		}
		try {
			feedbackService.addFeedback(feedback);
			projectService.updateProjectInfo(project);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("updateDate", DateFormat.currentDate());
			map.put("detailStr", DetailStatusEnum.getEnum(Integer.parseInt(detailStatus)).getValue());
			map.put("userName", userName);
			map.put("projectNo", projectNo);
			jsonResult.setOk(true);
			jsonResult.setData(map);
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage("录入失败");
			e.printStackTrace();
		}
		return jsonResult;
	}
}
