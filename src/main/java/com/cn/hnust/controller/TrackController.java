package com.cn.hnust.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.hnust.pojo.Track;
import com.cn.hnust.pojo.TrackQuery;
import com.cn.hnust.service.ProjectFactoryService;
import com.cn.hnust.service.TrackService;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.WebCookie;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 采购行踪录入
 * @ClassName TrackController 
 * @Description 
 * @author zj
 * @date 2019年4月30日 
 */
@Api(value = "TrackController | 采购行踪增删改查的接口")
@Controller
@RequestMapping("/track")
public class TrackController {
	
	@Autowired
	private TrackService trackService;
	@Autowired
	private ProjectFactoryService projectFactoryService;
	
	private static final Log LOG = LogFactory.getLog(TrackController.class);
	
	/**
	 * 添加采购行踪
	 * @Title addTrack 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */
	@ResponseBody
	@RequestMapping(value = "/addTrack",method = RequestMethod.POST)
    @ApiOperation(value="保存采购行踪，必须是json对象", notes="")
	public JsonResult addTrack(HttpServletRequest request,HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		try {
			String jsonString = request.getParameter("param");
			List<TrackQuery> trackList= null;
			if(StringUtils.isNotBlank(jsonString)){				
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, TrackQuery.class);
				trackList = objectMapper.readValue(jsonString,javaType);
			}	
			if(trackList!=null){
				trackService.insertBatch(trackList);
			}		
			jsonResult.setOk(true);
			jsonResult.setMessage("录入成功");
		} catch (JsonParseException e) {
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage("录入失败");
			LOG.error("-------addTrack-----failed", e);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage("录入失败");
			LOG.error("-------addTrack-----failed", e);
		} catch (IOException e) {
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage("录入失败");
			LOG.error("-------addTrack-----failed", e);
		}
		return jsonResult;
	}
	
	/**
	 * 跳转录入行踪
	 * @Title toAddTrack 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@RequestMapping(value="/toAddTrack",method = RequestMethod.POST)
	public String toAddTrack(HttpServletRequest request,HttpServletResponse response) {
		String inputKey = request.getParameter("inputKey");
		List<String> factoryNameList = projectFactoryService.selectAllFactory(inputKey);	
		request.setAttribute("factoryNameList", factoryNameList);
		return "track_entry";
	}
	
	
	
	/**
	 * 跳转行踪列表
	 * @Title selectTrack 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="/selectTrack",method = RequestMethod.POST)
    @ApiOperation(value="根据关键字查询行踪", notes="管理员可查询所有")
    @ApiImplicitParam(paramType="TrackQuery", name = "TrackQuery", value = "查询对象json", required = true, dataType = "String")
	public JsonResult selectTrack(HttpServletRequest request,HttpServletResponse response) {
		
		JsonResult jsonResult = new JsonResult();
		String param = request.getParameter("param");
		TrackQuery track = new TrackQuery();
		if(StringUtils.isNotBlank(param)){
			JSONObject jsonObj = (JSONObject) JSON.parse(param);
			track = JSONObject.toJavaObject(jsonObj, TrackQuery.class);
		}
		Integer page = 1;
	    Integer pageSize = 10;
        if(track.getPageNumber()!=null){
            page =track.getPageNumber();
        }
        if(track.getPageSize()!=null){
            pageSize = track.getPageSize();
        }
        String userName = WebCookie.getUserName(request);
        Integer roleNo = WebCookie.getRoleNo(request);
        track.setUserName(userName);
        track.setRoleNo(roleNo);
        PageHelper.startPage(page,pageSize);
		List<TrackQuery> trackList = trackService.selectByDate(track);				
		
		//获取分页信息
        Page<TrackQuery> list = (Page<TrackQuery>)trackList;
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("tracks", list);
		map.put("totalCount", list.getTotal());
		map.put("totalPage", list.getPages());
		jsonResult.setOk(true);
		jsonResult.setData(map);
		
		return jsonResult;
	}
	
	
	/**
	 * 跳转行踪列表
	 * @Title selectTrack 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="/selectTrackById",method = RequestMethod.GET)
    @ApiOperation(value="根据id查询行踪", notes="根据id查询行踪")
	public JsonResult selectTrackById(HttpServletRequest request,HttpServletResponse response) {		
		JsonResult jsonResult = new JsonResult();
		String idStr = request.getParameter("id");
		Track track = null;
		if(StringUtils.isNotBlank(idStr)){
			track = trackService.selectByPrimaryKey(Integer.parseInt(idStr));
		}		
		jsonResult.setOk(true);
		jsonResult.setData(track);		
		return jsonResult;
	}
	
	/**
	 * 添加采购行踪
	 * @Title updateTrack 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTrack",method = RequestMethod.POST)
    @ApiOperation(value="传值必须是json对象", notes="")
	public JsonResult updateTrack(HttpServletRequest request,HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		try {
			String jsonString = request.getParameter("param");
			TrackQuery track = new TrackQuery();
			if(StringUtils.isNotBlank(jsonString)){							
				JSONObject jsonObj = (JSONObject) JSON.parse(jsonString);
				track = JSONObject.toJavaObject(jsonObj, TrackQuery.class);
			}	
			trackService.updateByPrimaryKeySelective(track);	
			jsonResult.setOk(true);
			jsonResult.setMessage("录入成功");
		} catch(Exception e){
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage("录入失败");
			LOG.error("-------updateTrack-----failed {}", e);
		}
		return jsonResult;
	}
	
}
