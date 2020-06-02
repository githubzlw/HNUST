package com.cn.hnust.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.component.RpcUserHelper;
import com.cn.hnust.component.SynBonusSystemUser;
import com.cn.hnust.component.SynCrmUser;
import com.cn.hnust.component.SynErpUser;
import com.cn.hnust.component.SynNBEmailUser;
import com.cn.hnust.component.SynNewQuotationUser;
import com.cn.hnust.component.SynShippingUser;
import com.cn.hnust.component.SynTaskUser;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.PropertiesUtils;
/***
 * 操作用户增删改查、并且提供各系统同步接口
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/syncUser")
public class SyncAllUserController {
	@Resource
	private IUserService userService;
	
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	/**
	 * 查询以后是否存在
	 * @param request
	 * @param model
	 * @return 
	 * @return
	 */
	@RequestMapping("/showAllUser")
	public String showAllUser(HttpServletRequest request,HttpServletResponse response){
		
		String userName = request.getParameter("userName");
		List<User> users =userService.queryAllUser(userName);
		request.setAttribute("userList", users);
		return "../user/userList";
	}
	
	
	
	

	
	/**
	 * 验证用户是否存在
	 * @Title showAllUser 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/checkUser")
	public JsonResult checkUser(HttpServletRequest request,HttpServletResponse response){
        JsonResult jsonResult = new JsonResult();
        try {
			String userName = request.getParameter("userName");
			User user = userService.selectUserByName(userName);
			if(user != null){
				jsonResult.setOk(false);
				jsonResult.setMessage("用户已存在");
			}else{
				jsonResult.setOk(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage("查询出错");
		}
        return jsonResult;
	}
	
	
	/**
	 * 新增用户
	 * @Title addUser 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/addUser")
	public String addUser(HttpServletRequest request,HttpServletResponse response){
		try {
			String jsonString = request.getParameter("jsonStr");
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(jsonString, User.class);
			user.setRoleNo(user.getTaskRole());
			user.setRoleName(user.getTrueName());
			user.setRegisterDate(new Date());
			user.setType(0);
			user.setJobNumber(user.getJobNumber());
			userService.insertSelective(user);
			
			//同步到任务系统、crm系统、出运联系单
			SynTaskUser.sendRequest(user);
			SynCrmUser.sendRequest(user);
			SynShippingUser.sendRequest(user);
			SynNewQuotationUser.sendRequest(user);
			SynBonusSystemUser.sendRequest(user);
			SynErpUser.sendRequest(user);
			SynNBEmailUser.sendRequest(user);
			//同步内部报价
			//RpcUserHelper.sendRequest(reader.getProperty("new_quotation_path")+"/EmailUserController/modifyingProjectMembers", user);
			//同步NBmail
			//RpcUserHelper.sendRequest(reader.getProperty("nbmail_path")+"/EmailUser/modifyingProjectMembers", user);
			//同步分红系统
			//RpcUserHelper.sendRequest(reader.getProperty("bonus_path")+"/user/modifyingProjectMembers", user);
			//ERP
			//RpcUserHelper.sendRequest(reader.getProperty("erp_path")+"/helpServlet?action=modifyingProjectMembers&className=ExternalinterfaceServlet", user);
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return  "redirect:/syncUser/showAllUser"; 
	}
	
	

	
	/**
	 * 修改信息
	 * @Title addUser 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			User user = userService.selectById(Integer.parseInt(id));
			request.setAttribute("user", user);
		}
		return "../user/person_info_modify";
	}
	
	
	
	/**
	 * 修改信息
	 * @Title addUser 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping("/updateUser")
	public String updateUser(HttpServletRequest request,HttpServletResponse response){
		try {
			String id = request.getParameter("id");
			String jsonString = request.getParameter("jsonStr");
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(jsonString, User.class);
			user.setId(Integer.parseInt(id));
			if(user.getFlag() == 0){
				user.setLeavingDate(new Date());
			}
			user.setRoleNo(user.getTaskRole());
			user.setRoleName(user.getTrueName());
			user.setType(1);
			user.setJobNumber(user.getJobNumber());
			userService.update(user);
			
			//同步到任务系统、crm系统、出运联系单
			SynTaskUser.sendRequest(user);
			SynCrmUser.sendRequest(user);
			SynShippingUser.sendRequest(user);
			SynNewQuotationUser.sendRequest(user);
			SynErpUser.sendRequest(user);
			SynNBEmailUser.sendRequest(user);
			SynBonusSystemUser.sendRequest(user);
			//同步内部报价
			//RpcUserHelper.sendRequest(reader.getProperty("new_quotation_path")+"/EmailUserController/modifyingProjectMembers", user);
			//同步NBmail
			//RpcUserHelper.sendRequest(reader.getProperty("nbmail_path")+"/EmailUser/modifyingProjectMembers", user);
			//同步分红系统
			//RpcUserHelper.sendRequest(reader.getProperty("bonus_path")+"/user/modifyingProjectMembers", user);
			//ERP
			//RpcUserHelper.sendRequest(reader.getProperty("erp_path")+"/helpServlet?action=modifyingProjectMembers&className=ExternalinterfaceServlet", user);
			
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/syncUser/showAllUser";
	}
	
	
	
	/**
	 * 根据职位查询用户
	 * @Title addUser 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/queryByJob")
	public JsonResult queryByJob(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
		String job = request.getParameter("job");
		if(StringUtils.isNotBlank(job)){
			List<User> users = userService.queryByJob(job);
			User user=new User();
			user.setUserName("质检部");
			users.add(user);
			jsonResult.setOk(true);
			jsonResult.setData(users);
		}
		return jsonResult;
	}
	
	
	
	/**
	 * 查询所有用户
	 * @Title selectAll 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/selectAll")
	public JsonResult selectAll(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
		String userName = request.getParameter("userName");
		List<User> users = userService.queryAllUser(userName);
		jsonResult.setOk(true);
		jsonResult.setData(users);
		return jsonResult;
	}
	
}
