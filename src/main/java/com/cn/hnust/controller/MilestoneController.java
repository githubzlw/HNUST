package com.cn.hnust.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.cn.hnust.pojo.Delay;
import com.cn.hnust.pojo.DingTalkMileStone;
import com.cn.hnust.pojo.InspectionPlan;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectSchedule;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.service.DingTalkMileStoneService;
import com.cn.hnust.service.IDelayService;
import com.cn.hnust.service.IProjectScheduleService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.JsonResult;

/**
 * 里程碑 Controller 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/milestone")
public class MilestoneController {

	@Autowired
	private DingTalkMileStoneService dingTalkMileStoneService;

	@RequestMapping("/selectByProjectNo")
	@ResponseBody
	public JsonResult selectByProjectNo(HttpServletRequest request,HttpServletResponse response){
		JsonResult json =new JsonResult();
		String projectNo = request.getParameter("projectNo");
		List<DingTalkMileStone> milestones = dingTalkMileStoneService.selectByProjectNo(projectNo);
	    json.setOk(true);
	    json.setData(milestones);
	    return json;
	}
	
	
	/**
	 * 插入、修改里程碑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public JsonResult addMilestone(HttpServletRequest request,HttpServletResponse response){
		JsonResult json =new JsonResult();
		try{ 		
		 
			//里程碑列表
			String milestoneList = request.getParameter("milestoneList");
			if(StringUtils.isNotBlank(milestoneList)){
				List<DingTalkMileStone> milestones = null;
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, DingTalkMileStone.class);
				milestones = objectMapper.readValue(milestoneList,javaType);
				//用于判断是否属于同一批次里程碑
				String uid = IdGen.uuid();
				if(milestones!=null && milestones.size()>0){
					milestones.forEach(m->{
						m.setCreateDate(new Date());
						m.setUid(uid);
					});
					dingTalkMileStoneService.insertBatch(milestones);
				}
			}
			 json.setOk(true);
			 json.setMessage("里程碑添加成功");
		} catch (Exception e) {
			json.setOk(false);
			json.setMessage("里程碑添加错误");
			e.printStackTrace();
		}
		return json;
	}
	
	
}
