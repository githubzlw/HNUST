package com.cn.hnust.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectDrawing;
import com.cn.hnust.pojo.ProjectVO;
import com.cn.hnust.pojo.ShippingConfirmation;
import com.cn.hnust.service.IProjectDrawingService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.ShippingConfirmationService;
import com.cn.hnust.util.DateFormat;

import javax.servlet.http.HttpServletRequest;

/**
 * 提供给其他系统的数据
 * @author polo
 * 2018年11月19日
 */
@Controller
@RequestMapping("/port")
public class SendDataPort {
	

      private Logger logger = LoggerFactory.getLogger(this.getClass());	


	  @Autowired
	  private IProjectService projectService;     
	  @Autowired
	  private IProjectDrawingService projectDrawingService;     
	  @Autowired
	  private ShippingConfirmationService shippingConfirmationService;     
	  
	  
  

	  /**
	   * 获取项目详情接口
	   * @author polo
	   * 2018年11月20日
	   *
	   */
	  @ResponseBody
	  @RequestMapping("/getProjectInfo")	 
	  public String getProjectInfo(@RequestParam Map<String,String> map){
		  String json = null;
		  try {	  
		       String projectNo = map.get("projectNo");
		       String factoryId = map.get("factoryId");
		       ProjectVO projectVO = projectService.selectProjectVO(projectNo,factoryId);
		       List<ProjectDrawing> drawings = projectDrawingService.selectProjectDrawingByProjectNo(projectNo);
		       Map<String, Object> sendMap = new HashMap<String, Object>();
		       sendMap.put("projectVO", projectVO);
		       sendMap.put("drawings", drawings);
		       json = JSONObject.toJSON(sendMap).toString();
		       } catch (Exception e) {		    	   
		         e.printStackTrace();
		         logger.error("获取项目详情接口失败",e.getMessage());
		       }	
		  return json;
	  }
	  
	  
	  
	  /**
	   * 获取项目详情接口
	   * @author polo
	   * 2018年11月26日
	   *
	   */
	  @ResponseBody
	  @RequestMapping("/getProjectList")	 
	  public String getProjectList(@RequestParam Map<String,String> map){
		  String json = null;
		  try {	  
		       String factoryId = map.get("factoryId");
		       List<ProjectVO> projectVOList = projectService.selectProjectVOList(factoryId);
//		       Iterator<ProjectVO> it = projectVOList.iterator();
//		       while(it.hasNext()) {
//		    	   ProjectVO projectVO = it.next();
//		    	   if(projectVO.getProjectNo().contains("-")){
//		    		   
//		    	   }else{
//		    		   it.remove();
//		    	   }
//		       }		       
		       Map<String, Object> sendMap = new HashMap<String, Object>();
		       sendMap.put("projectVOList", projectVOList);
		       json = JSONObject.toJSON(sendMap).toString();
		       } catch (Exception e) {		    	   
		         e.printStackTrace();
		         logger.error("获取项目详情接口失败",e.getMessage());
		       }	
		  return json;
	  }
	  
	  
	  
	  /**
	   * 获取项目数量接口
	   * @author polo
	   * 2018年11月27日
	   *
	   */
	  @ResponseBody
	  @RequestMapping("/getProjectCount")	 
	  public String getProjectCount(@RequestParam Map<String,String> map){
		  String json = null;
		  try {	  
		       String factoryId = map.get("factoryId");
		       List<ProjectVO> projectVOList = projectService.selectProjectVOList(factoryId);
//		       Iterator<ProjectVO> it = projectVOList.iterator();
//		       while(it.hasNext()) {
//		    	   ProjectVO projectVO = it.next();
//		    	   if(projectVO.getProjectNo().contains("-")){
//		    		   
//		    	   }else{
//		    		   it.remove();
//		    	   }
//		       }		       
		       json = projectVOList.size()+"";
		       } catch (Exception e) {		    	   
		         e.printStackTrace();
		         logger.error("获取项目详情接口失败",e.getMessage());
		       }	
		  return json;
	  }
	  
	  
	  
	  
	  
	  
	  
	  /**
	   * 获取电子出货单
	   * @author polo
	   * 2018年12月21日
	   *
	   */
	  @ResponseBody
	  @RequestMapping("/getShippingNum")	 
	  public String getShippingNum(HttpServletRequest request){
		  String json = "";
		  Boolean flag = false; 
		  try {	  
			       String serialNumber = request.getParameter("serialNumber");
			       ShippingConfirmation shippingConfirmation = shippingConfirmationService.selectBySerialNumber(serialNumber);
			       if(shippingConfirmation != null){
			    	   flag = true;
			    	   json = flag.toString() + "," + shippingConfirmation.getIsComplete() + "," + shippingConfirmation.getId();
			       }else{
			    	   flag = false;
			    	   json = flag.toString() + ",,";
			       }
		       } catch (Exception e) {		    	   
		         e.printStackTrace();
		         logger.error("获取电子出货单接口失败",e.getMessage());
		       }	
		  return json;
	  }
	  
	  
	  
	  
	  /**
	   * 根据项目号获取项目是否延期
	   * @author polo
	   * 2019年01月17日
	   *
	   */
	  @ResponseBody
	  @RequestMapping("/judgeDelay")	 
	  public String judgeDelay(String projectNo){
		   String delayType = null;	
		   try {	  
			       if(StringUtils.isNotBlank(projectNo)){
			    	   Project project = projectService.selectProjctDetails(projectNo);
			    	   if(project!=null && project.getProjectStatus() == OrderStatusEnum.NORMAL_ORDER.getCode()){
			    		   delayType = "true";
					       //判断大货是否延期
							 Integer isFinish = project.getFinish();
							 Date predictDate = project.getDeliveryDate();
							 Date actualDate = project.getFinishTime();
							 if(isFinish == 1 && actualDate != null && predictDate != null){
								if(actualDate.getTime() > predictDate.getTime() + 7*24*60*60*1000){
									delayType = "false";										
								}
							 }else if(isFinish == 0 && predictDate != null){
								if(new Date().getTime() > predictDate.getTime() + 7*24*60*60*1000){
									delayType = "false";
							    }
							 }

							
							//如果已有大货交期则不管样品是否延期 
							//判断样品是否延期
							Date sampleScheduledDate = project.getSampleScheduledDate();
							if(sampleScheduledDate != null && predictDate == null){
								if(project.getSampleFinish() == 0){
									if(new Date().getTime()  > sampleScheduledDate.getTime() + 7*24*60*60*1000){
										delayType = "false";	
								    }
								}else if(project.getSampleFinish() == 1 && project.getSampleFinishTime() != null){
									if(project.getSampleFinishTime().getTime()  > sampleScheduledDate.getTime() + 7*24*60*60*1000){
										delayType = "false";										
								    }
								}
							}
			    	   }
			       }  
		       } catch (Exception e) {		    	   
		         e.printStackTrace();
		         logger.error("获取项目详情接口失败",e.getMessage());
		       }	
		  return delayType;
	  }
}
