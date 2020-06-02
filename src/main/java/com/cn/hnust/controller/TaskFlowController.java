package com.cn.hnust.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.TaskFlow;
import com.cn.hnust.pojo.TaskFlowDetail;
import com.cn.hnust.pojo.TriggerTask;
import com.cn.hnust.service.ITaskFlowService;
import com.cn.hnust.service.ITriggerTaskService;
import com.cn.hnust.util.JsonResult;

@Controller
@RequestMapping("/taskflow")
public class TaskFlowController {

	@Resource
	private ITaskFlowService service;

	@Resource
	private ITriggerTaskService triggerService;
	

	@RequestMapping("/queryList")
	public String selectMeetingRecordList(HttpServletRequest request,
			HttpServletResponse response) {
		
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		String roleNo = request.getParameter("roleNo");

		int pageNumber = 0;
		int start = 0;

		if (StringUtils.isNotBlank(request.getParameter("pageNumber"))) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));// 第几页,1,2
		} else {
			pageNumber = 1;
		}

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("name")) {
					userName = c.getValue();
				}
			}
		}

		Integer pageSize = 10;

		start = pageSize * (pageNumber - 1);

		List<TaskFlow> taskFlowList = service.selectList(start, pageSize);

		int totalCount = service.selectListCount();// 查询记录条数
		int countPage = 0;

		if (totalCount != 0) {
			countPage = (totalCount - 1) / 10 + 1;
		}
		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userName", userName);
		request.setAttribute("userId", userId);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("countPage", countPage);
		request.setAttribute("taskFlowList", taskFlowList);

		return "task_flow_list";
	}

	@RequestMapping("/selectRecordDetails")
	@ResponseBody
	public JsonResult selectRecordDetails(HttpServletRequest request,
			HttpServletResponse response) {

		JsonResult jsonResult = new JsonResult();
		try {

			String idstr = request.getParameter("taskflowId");

			if (idstr == null) {
				jsonResult.setOk(false);
				jsonResult.setMessage("id不能为空");
				return jsonResult;

			}
			int id = Integer.parseInt(idstr);
			TaskFlow taskFlow = service.selectAllDetail(id);
	
			

			jsonResult.setOk(true);
			jsonResult.setMessage("查询成功");
			jsonResult.setData(taskFlow);
			return jsonResult;

		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage("查询方法出现异常");
			return jsonResult;

		}

	}

	@RequestMapping("/getTaskList")
	@ResponseBody
	public JsonResult getTaskList(HttpServletRequest request,
			HttpServletResponse response) {

		JsonResult jsonResult = new JsonResult();
		try {

			List<TriggerTask> triggerList = triggerService.selectAllList();
			jsonResult.setOk(true);
			jsonResult.setMessage("查询成功");
			jsonResult.setData(triggerList);
			return jsonResult;

		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage("查询方法出现异常");
			return jsonResult;

		}

	}

	@RequestMapping("/addRecord")
	@ResponseBody
	public JsonResult addRecord(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		List<TaskFlowDetail> list = null;
		try {

			String conditionName = request.getParameter("conditionName");
			String conditionType = request.getParameter("conditionType");
			String[] triggerIdS = request.getParameterValues("triggerId");
			String idstr = request.getParameter("taskflowId");
			String plantAnalysis = request.getParameter("plantAnalysis");
		    String projectMaterialProperties = request.getParameter("projectMaterialProperties");

			TaskFlow taskFlow = new TaskFlow();
			taskFlow.setConditionType(Integer.parseInt(conditionType));
			taskFlow.setTriggerId(Integer.parseInt(triggerIdS[0]));
			taskFlow.setConditionName(conditionName);
			taskFlow.setUpdateTime(new Date());
			taskFlow.setPlantAnalysis(Integer.parseInt(plantAnalysis));
			taskFlow.setProjectMaterialProperties(Integer.parseInt(projectMaterialProperties));
			
			list = new ArrayList<TaskFlowDetail>();
			
			for(int i=0;i<triggerIdS.length;i++){
				
				Integer triggerId = Integer.parseInt(triggerIdS[i]);
				if(triggerId!=null&&triggerId!=0){
					TaskFlowDetail td = new TaskFlowDetail();
					td.setTriggerId(triggerId);
					td.setIndexNum(i+1);
					list.add(td);
				}
				
			}
			taskFlow.setTfDetails(list);
//			service.insertAll(taskFlow);
			
			
			if (idstr.trim() != "") {
				taskFlow.setId(Integer.parseInt(idstr));
				service.updateAll(taskFlow);
				jsonResult.setOk(true);
				jsonResult.setMessage("更新成功");

			} else {
				service.insertAll(taskFlow);
				jsonResult.setOk(true);
				jsonResult.setMessage("添加成功");
			}

			return jsonResult;

		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage("添加失败");
			e.printStackTrace();
			return jsonResult;

		}

	}

	@RequestMapping("/deleteRecordDetails")
	@ResponseBody
	public JsonResult deleteRecordDetails(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		try {

			String triggerId = request.getParameter("taskflowId");

			if (triggerId == null) {
				jsonResult.setOk(false);
				jsonResult.setMessage("id不能为空");
				return jsonResult;
			}
			int id = Integer.parseInt(triggerId);
			service.deleteByPrimaryKey(id);

			jsonResult.setOk(true);
			jsonResult.setMessage("删除成功");

			return jsonResult;

		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage("删除失败");
			e.printStackTrace();
			return jsonResult;

		}

	}

}
