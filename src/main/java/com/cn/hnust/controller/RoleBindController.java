package com.cn.hnust.controller;

import java.util.ArrayList;
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
import com.cn.hnust.pojo.RoleBindDetail;
import com.cn.hnust.pojo.RoleBindList;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IRoleBindListService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.JsonResult;

@Controller
@RequestMapping("/roleBind")
public class RoleBindController {

	@Resource
	private IRoleBindListService service;

	@Resource
	private IProjectService projectService;

	@Resource
	private IUserService userService;

	@RequestMapping("/queryList")
	public String selectMeetingRecordList(HttpServletRequest request,
			HttpServletResponse response) {
		String projectNo = request.getParameter("projectNo");
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

		List<RoleBindList> roleList = service.selectRoleBindList(projectNo,
				start, pageSize);

		int totalCount = service.selectRoleBindListCount(projectNo);// 查询记录条数
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
		request.setAttribute("roleList", roleList);
		return "role_bind_list";
	}

	@RequestMapping("/selectRecordDetails")
	@ResponseBody
	public JsonResult selectMeetingRecordDetails(HttpServletRequest request,
			HttpServletResponse response) {

		JsonResult jsonResult = new JsonResult();
		try {
			List<RoleBindDetail> roleBindDetails = service.selectAllDetail();
			jsonResult.setOk(true);
			jsonResult.setMessage("查询成功");
			jsonResult.setData(roleBindDetails);
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

			String projectNo = request.getParameter("projectNo");
			String userName = request.getParameter("userName");
			String userId = request.getParameter("userId");
			String roleBindId = request.getParameter("roleBindId");

			String[] roleTypes = request.getParameterValues("roleType");
			String[] staffNames = request.getParameterValues("staffName");

			List<RoleBindDetail> list = new ArrayList<RoleBindDetail>();

			for (int i = 0; i < roleTypes.length; i++) {
				RoleBindDetail rod = new RoleBindDetail();
				int roleType = Integer.parseInt(roleTypes[i]);
				rod.setRoleType(roleType);
				rod.setRoleName(RoleTypeEnum.getEnum(roleType).getValue());
				rod.setUserName(staffNames[i]);
				if (staffNames[i] != null) {
					User user = userService.selectUserByName(staffNames[i]);
					rod.setUserId(user.getId());
				}

				list.add(rod);
			}

			service.updateAllDetail(list);

			jsonResult.setOk(true);
			jsonResult.setMessage("添加成功");

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

			String roleBindId = request.getParameter("roleBindId");

			if (roleBindId == null) {
				jsonResult.setOk(false);
				jsonResult.setMessage("id不能为空");
				return jsonResult;
			}
			int id = Integer.parseInt(roleBindId);
			service.deleteAll(id);

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
