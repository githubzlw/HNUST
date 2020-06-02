package com.cn.hnust.controller;

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

import com.cn.hnust.enums.RoleTypeEnum;
import com.cn.hnust.pojo.TriggerTask;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.ITriggerTaskService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.JsonResult;

@Controller
@RequestMapping("/trigger")
public class TriggerTaskController {

	@Resource
	private ITriggerTaskService service;

	@Resource
	private IProjectService projectService;

	@Resource
	private IUserService userService;

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

		List<TriggerTask> triggerList = service.selectList(start, pageSize);

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
		request.setAttribute("triggerList", triggerList);

		return "trigger_task_list";
	}

	@RequestMapping("/selectRecordDetails")
	@ResponseBody
	public JsonResult selectMeetingRecordDetails(HttpServletRequest request,
			HttpServletResponse response) {

		JsonResult jsonResult = new JsonResult();
		try {

			String idstr = request.getParameter("triggerId");

			if (idstr == null) {
				jsonResult.setOk(false);
				jsonResult.setMessage("id不能为空");
				return jsonResult;

			}
			int id = Integer.parseInt(idstr);
			TriggerTask triggerTask = service.selectByPrimaryKey(id);

			jsonResult.setOk(true);
			jsonResult.setMessage("查询成功");
			jsonResult.setData(triggerTask);
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
		try {

			String triggerId = request.getParameter("triggerId");
			String roleType = request.getParameter("roleType");
			String taskTitle = request.getParameter("taskTitle");

			TriggerTask triggerTask = new TriggerTask();
			int code = Integer.parseInt(roleType);
			String roleName = RoleTypeEnum.getEnum(code).getValue();

			triggerTask.setRoleType(code);
			triggerTask.setRoleName(roleName);
			triggerTask.setTaskTitle(taskTitle);
			triggerTask.setUpdateTime(new Date());
			
			
			List<TriggerTask> ls =  service.checkHasKey(taskTitle, Integer.parseInt(roleType));
			if(ls!=null&&ls.size()>0){
				jsonResult.setOk(false);
				jsonResult.setMessage("已存在相同的触发动作");
				return jsonResult;
				
			}

			if (triggerId.trim() != "") {
				triggerTask.setId(Integer.parseInt(triggerId));
				service.updateByPrimaryKeySelective(triggerTask);
				jsonResult.setOk(true);
				jsonResult.setMessage("更新成功");

			} else {
				service.insertSelective(triggerTask);
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

			String triggerId = request.getParameter("triggerId");

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
