package com.cn.hnust.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.enums.ItemClassificationEnum;
import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.pojo.EmailUser;
import com.cn.hnust.pojo.GoodsEntry;
import com.cn.hnust.pojo.InspectionReservation;
import com.cn.hnust.pojo.InvoiceInfo;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectComplaint;
import com.cn.hnust.pojo.ProjectDrawing;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.ProjectInspectionReport;
import com.cn.hnust.pojo.ProjectReport;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.QuotePrice;
import com.cn.hnust.pojo.User;
import com.cn.hnust.print.InspectionPrint;
import com.cn.hnust.print.ProjectPrint;
import com.cn.hnust.service.GoodsEntryService;
import com.cn.hnust.service.IInspectionReservationService;
import com.cn.hnust.service.IProjectDrawingService;
import com.cn.hnust.service.IProjectInspectionReportService;
import com.cn.hnust.service.IProjectReportService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.service.ItemCaseERPService;
import com.cn.hnust.service.ProjectComplaintService;
import com.cn.hnust.service.ProjectFactoryService;
import com.cn.hnust.service.QuotePriceService;
import com.cn.hnust.service.TQuotePriceService;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.WebCookie;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 验货预约任务
 * @author chenlun
 *
 */
@RequestMapping("/inspection")
@Controller
public class InspectionReservationController {

	@Autowired
	private IInspectionReservationService inspectionReservationService;
	@Autowired
	private IProjectTaskService projectTaskService;
	@Autowired
	private IProjectReportService projectReportService;
	@Autowired
	private IProjectDrawingService projectDrawingService;
	@Autowired
	private IProjectInspectionReportService projectInspectionReportService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ProjectFactoryService projectFactoryService;
	@Autowired
	private TQuotePriceService tquotePriceService;
	@Autowired
	private GoodsEntryService goodsEntryService;
	@Autowired
	private ProjectComplaintService projectComplaintService;
	@Autowired
	private ItemCaseERPService itemCaseERPService;
	@RequestMapping("/addInspection")
	@ResponseBody
	public JsonResult addInspection(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
		InspectionReservation inspection=new InspectionReservation();
    	String projectNo=request.getParameter("projectNo");
    	String initiator=request.getParameter("initiator");
    	String accepter=request.getParameter("accepter");
    	String description=request.getParameter("description");
    	String finishTime=request.getParameter("finishTime");
    	String urgentReason=request.getParameter("urgentReason");
    	String produceStatus=request.getParameter("produceStatus");
    	String expectedDelivery=request.getParameter("expectedDelivery");
    	String shippingDate=request.getParameter("shippingDate");
    	String inspectionAddress=request.getParameter("inspectionAddress");
        String testType=request.getParameter("testType");
        String checkNumber=request.getParameter("checkNumber");
    	inspection.setProjectNo(projectNo);
    	inspection.setProjectNoId(IdGen.uuid());
    	inspection.setInitiator(initiator);
    	inspection.setAccepter(accepter);
    	inspection.setDescription(description);
    	inspection.setUrgentReason(urgentReason);
    	inspection.setFinishTime(DateUtil.StrToDate(finishTime));
		inspection.setProduceStatus(produceStatus);
		inspection.setExpectedDelivery(DateUtil.StrToDate(expectedDelivery));
		inspection.setTestType(testType);
		inspection.setCheckNumber(checkNumber);
		//大货检验默认50%开箱比例
		if("出货".equals(testType)){
			inspection.setOpenRate("50");
		}
		inspection.setCreateDate(new Date());
		if(StringUtils.isNotBlank(shippingDate)){
			inspection.setShippingDate(DateUtil.StrToDate(shippingDate));
		}
		inspection.setInspectionAddress(inspectionAddress);
		//inspection.setQualityDate(new Date());

		ProjectTask projectTask=new ProjectTask();
		projectTask.setProjectNoId(inspection.getProjectNoId());
		projectTask.setProjectNo(projectNo);
    	projectTask.setInitiator(initiator);
    	projectTask.setAccepter(accepter);
    	projectTask.setDescription(description);
    	projectTask.setUrgentReason(urgentReason);
    	projectTask.setFinishTime(DateUtil.StrToDate(finishTime));
    	projectTask.setTaskStatus("0");
    	projectTask.setTaskType("2");
    	projectTask.setStartTime(new Date());
    	projectTask.setCreateTime(new Date());
		ProjectERP project=new ProjectERP();
		project.setZhijian1(accepter);
		project.setProjectNo(projectNo);
		int num1= itemCaseERPService.findName(projectNo,accepter,2);//查看成员是否在ERP成员列表中
		if(num1==0) {
			ProjectERP projectERP = itemCaseERPService.search(project);//查询质检是否存在
			if (projectERP.getMasterQualityInspection() == null || "".equalsIgnoreCase(projectERP.getMasterQualityInspection())) {
				projectERP.setMasterQualityInspection(accepter);
			} else if (projectERP.getZhijian1() == null || "".equalsIgnoreCase(projectERP.getZhijian1())) {
				projectERP.setZhijian1(accepter);
			} else if (projectERP.getZhijian2() == null || "".equalsIgnoreCase(projectERP.getZhijian2())) {
				projectERP.setZhijian2(accepter);
			} else if (projectERP.getZhijian3() == null || "".equalsIgnoreCase(projectERP.getZhijian3())) {
				projectERP.setZhijian3(accepter);
			} else if (projectERP.getQualityInspector1() == null || "".equalsIgnoreCase(projectERP.getQualityInspector1())) {
				projectERP.setQualityInspector1(accepter);
			} else if (projectERP.getQualityInspector2() == null || "".equalsIgnoreCase(projectERP.getQualityInspector2())) {
				projectERP.setQualityInspector2(accepter);
			} else if (projectERP.getQualityInspector3() == null || "".equalsIgnoreCase(projectERP.getQualityInspector3())) {
				projectERP.setQualityInspector3(accepter);
			} else if (projectERP.getQualityInspector4() == null || "".equalsIgnoreCase(projectERP.getQualityInspector4())) {
				projectERP.setQualityInspector4(accepter);
			} else if (projectERP.getQualityInspector5() == null || "".equalsIgnoreCase(projectERP.getQualityInspector5())) {
				projectERP.setQualityInspector5(accepter);
			} else if (projectERP.getQualityInspector6() == null || "".equalsIgnoreCase(projectERP.getQualityInspector6())) {
				projectERP.setQualityInspector6(accepter);
			} else if (projectERP.getQualityInspector7() == null || "".equalsIgnoreCase(projectERP.getQualityInspector7())) {
				projectERP.setQualityInspector7(accepter);
			}
			projectService.updateProjectByErp(projectNo);
			itemCaseERPService.updateQuality(projectERP);//修改质检
		}


		try {
			inspectionReservationService.addInspectionReservation(inspection);
			projectTaskService.addProjectTask(projectTask);

			//批量更新工厂生产状态
			String factoryList = request.getParameter("factoryList");
			if(StringUtils.isNotBlank(factoryList)){
				List<ProjectFactory> factorys = null;
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ProjectFactory.class);
				factorys = objectMapper.readValue(factoryList,javaType);
				if(factorys!=null&&factorys.size()>0){
					projectFactoryService.updateBatch(factorys);
				}
			}

			jsonResult.setOk(true);
			jsonResult.setData("录入成功");
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setData("录入失败");
			e.printStackTrace();
		}
		return jsonResult;
	}

	@RequestMapping("/selectInspection")
	@ResponseBody
	public JsonResult selectInspection(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
		String projectNo=request.getParameter("projectNo");
		String inputKey=request.getParameter("inputKey");
		String userId=request.getParameter("userId");
		String roleNo=request.getParameter("roleNo");
		String userName=request.getParameter("userName");
		String qualityName=request.getParameter("quality_name");
		String taskStatus=request.getParameter("taskStatus");
		String approvalSelect = request.getParameter("approvalSelect");
		String qualityNames = request.getParameter("qualityNames");

		InspectionReservation inspection=new InspectionReservation();
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
		User user=userService.selectUserByName(userName);
		if(user.getRoleNo()==100 || user.getRoleNo()==99 || user.getRoleNo()==98 ||user.getRoleNo()==97 || "AndsXue".equals(userName)){
			inspection.setUserName(null);
		}else{
			inspection.setUserName(userName);
		}

		//根据质检名进行筛选
		String[] strs ={};
		if(StringUtils.isNotBlank(qualityNames)){
			strs = qualityNames.split(",");
			inspection.setQualityNames(strs);
		}


		inspection.setPageSize(pageSize);
		inspection.setPageNumber(pageSize*(pageNumber-1));
		if(projectNo!=null&&!"".equalsIgnoreCase(projectNo)){
		inspection.setProjectNo(projectNo);
		}
		inspection.setInputKey(inputKey);
		inspection.setQualityName(qualityName);
		inspection.setTaskStatus(taskStatus);
		if(StringUtils.isNotBlank(approvalSelect)){
			inspection.setShippingApproval(Integer.parseInt(approvalSelect));
		}
		List<InspectionReservation> inspectionList=inspectionReservationService.selectInspectionReservation(inspection);
		int count=inspectionReservationService.selectInspectionReservationCount(inspection);
		for (InspectionReservation inspectionReservation : inspectionList) {
			//检验计划,图纸,检验员
			//1.查询项目的图纸信息
			List<ProjectDrawing> projectDrawingList = projectDrawingService.selectProjectDrawingByProjectNo(inspectionReservation.getProjectNo());
			//2.查询检验计划
			List<ProjectInspectionReport> inspectionPlanList=projectInspectionReportService.selectInspectionPlanByProjectNo(inspectionReservation.getProjectNo());
			inspectionReservation.setInspectionPlanList(inspectionPlanList);
			inspectionReservation.setProjectDrawingList(projectDrawingList);
		}
		goodsEntry(inspectionList);
		List<User> users = userService.queryByJob("质检");

		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("inspectionList", inspectionList);
		hashMap.put("userId", userId);
		hashMap.put("roleNo", roleNo);
		hashMap.put("userName", userName);
		hashMap.put("pageSize", pageSize);
		hashMap.put("pageNumber", pageNumber);
		hashMap.put("count", count);
		hashMap.put("users", users);
		jsonResult.setData(hashMap);
		jsonResult.setOk(true);
		return jsonResult;
	}
	/**
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toSelectInspection")
	public String toSelectInspection(HttpServletRequest request,HttpServletResponse response){
		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String projectNo = request.getParameter("projectNo");
		request.setAttribute("projectNo", projectNo);

		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		return "task_list";
	}

	//更新检验人和初检时间
	@RequestMapping("/updateInspectionReservation")
	@ResponseBody
	public JsonResult updateInspectionReservation(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult=new JsonResult();
		String projectNoId=request.getParameter("projectNoId");
	    String accepter=request.getParameter("accepter");
	 	String finishTime=request.getParameter("finishTime");
	 	String userName = request.getParameter("userName");
	 	String openRate = request.getParameter("openRate");
	 	String start = request.getParameter("start");
	 	String end = request.getParameter("end");
	 	String acceptor1 = request.getParameter("acceptor1");
	 	InspectionReservation inspectionReservation=new InspectionReservation();
	 	inspectionReservation.setProjectNoId(projectNoId);
	 	inspectionReservation.setAccepter(accepter);
	 	if(StringUtils.isNotBlank(finishTime)){
	 		inspectionReservation.setFinishTime(DateUtil.StrToDate(finishTime));
	 	}
	 	inspectionReservation.setOpenRate(openRate);
	 	User user=userService.findUserByName(userName);
	 	InspectionReservation inspectionReservation2=inspectionReservationService.selectInspectionReservationById(projectNoId);
	 	if(!(user.getRoleNo()==100 || user.getRoleNo()==99 || user.getRoleNo()==98 || user.getRoleNo()==97 || inspectionReservation2.getInitiator().equalsIgnoreCase(userName) )){
	 		jsonResult.setOk(false);
			jsonResult.setMessage("你没有权限操作");
	 	}else{
	 		try {
				inspectionReservationService.updateInspectionReservation(inspectionReservation);
			    //更新出检时间和任务接受人
				if(accepter!=null){//更新检验员和任务接受人
					ProjectTask projectTask=new ProjectTask();
					projectTask.setProjectNoId(projectNoId);
					projectTask.setAccepter(accepter);
					projectTask.setTaskStatus("0");
					projectTaskService.updateProjectTask(projectTask);
					ProjectERP project=new ProjectERP();
					project.setZhijian1(accepter);
					project.setProjectNo(inspectionReservation2.getProjectNo());
					int num1= itemCaseERPService.findName(inspectionReservation2.getProjectNo(),accepter,2);//查看成员是否在ERP成员列表中
					if(num1==0){
					ProjectERP projectERP=itemCaseERPService.search(project);//查询质检是否存在
					if(projectERP.getMasterQualityInspection()==null||"".equalsIgnoreCase(projectERP.getMasterQualityInspection()))
					{
						projectERP.setMasterQualityInspection(accepter);
					}else if(projectERP.getZhijian1()==null||"".equalsIgnoreCase(projectERP.getZhijian1())){
						projectERP.setZhijian1(accepter);
					}else if(projectERP.getZhijian2()==null||"".equalsIgnoreCase(projectERP.getZhijian2())){
						projectERP.setZhijian2(accepter);
					}else if(projectERP.getZhijian3()==null||"".equalsIgnoreCase(projectERP.getZhijian3())){
						projectERP.setZhijian3(accepter);
					}else if(projectERP.getQualityInspector1()==null||"".equalsIgnoreCase(projectERP.getQualityInspector1())){
						projectERP.setQualityInspector1(accepter);
					}else if(projectERP.getQualityInspector2()==null||"".equalsIgnoreCase(projectERP.getQualityInspector2())){
						projectERP.setQualityInspector2(accepter);
					}else if(projectERP.getQualityInspector3()==null||"".equalsIgnoreCase(projectERP.getQualityInspector3())){
						projectERP.setQualityInspector3(accepter);
					}else if(projectERP.getQualityInspector4()==null||"".equalsIgnoreCase(projectERP.getQualityInspector4())){
						projectERP.setQualityInspector4(accepter);
					}else if(projectERP.getQualityInspector5()==null||"".equalsIgnoreCase(projectERP.getQualityInspector5())){
						projectERP.setQualityInspector5(accepter);
					}else if(projectERP.getQualityInspector6()==null||"".equalsIgnoreCase(projectERP.getQualityInspector6())){
						projectERP.setQualityInspector6(accepter);
					}else if(projectERP.getQualityInspector7()==null||"".equalsIgnoreCase(projectERP.getQualityInspector7())){
						projectERP.setQualityInspector7(accepter);
					}
					projectService.updateProjectByErp(projectERP.getProjectNo());
					itemCaseERPService.updateQuality(projectERP);//修改质检

					}



				}
				jsonResult.setOk(true);
				if(start!=null&&!"".equalsIgnoreCase(start)){
				inspectionReservation.setStart(start);
				inspectionReservation.setEnd(end);
				inspectionReservation.setAccepter(acceptor1);
				InspectionReservation inspectionReservation1=inspectionReservationService.getOne(inspectionReservation);
				if(inspectionReservation1!=null){
				String message="项目:"+inspectionReservation1.getProjectNo()+"有质检重复现象请注意";
				jsonResult.setMessage(message);
				}else{


				jsonResult.setMessage("操作成功");
				}
				}else{
				jsonResult.setMessage("操作成功");
				}
			} catch (Exception e) {
				jsonResult.setOk(false);
				jsonResult.setMessage("操作失败");
				e.printStackTrace();
			}
	 	}
		return jsonResult;
	}


	   //更新开箱比例
		@RequestMapping("/updateOpenRate")
		@ResponseBody
		public JsonResult updateOpenRate(HttpServletRequest request,HttpServletResponse response){
			JsonResult jsonResult=new JsonResult();
			String projectNoId=request.getParameter("projectNoId");
		 	String openRate = request.getParameter("openRate");
		 	InspectionReservation inspectionReservation=new InspectionReservation();
		 	inspectionReservation.setProjectNoId(projectNoId);
		 	inspectionReservation.setOpenRate(openRate);
		 		try {
					inspectionReservationService.updateInspectionReservation(inspectionReservation);
					jsonResult.setOk(true);
					jsonResult.setMessage("操作成功");
				} catch (Exception e) {
					jsonResult.setOk(false);
					jsonResult.setMessage("操作失败");
					e.printStackTrace();
				}
			return jsonResult;
		}



	/**
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toInputInspection")
	public String toInputInspection(HttpServletRequest request,HttpServletResponse response){
		String roleNo = request.getParameter("roleNo");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
        String projectNo=request.getParameter("projectNo");
        Project project=new Project();
        String status="";
        Date today=new Date();
        if(StringUtils.isNotBlank(projectNo)){
        	 project=projectService.selectProjctDetails(projectNo);
        	// 判断项目状态
				if (project.getFinish() == 0) {//大货没有完结
					if (project.getIsPause() == null|| "0".equals(project.getIsPause())) {
						List<ProjectReport> pr = projectReportService.selectProjectReport(project.getProjectNo());
						if(project.getSampleFinish()==0 && pr.size()==0 &&
								project.getDeliveryDate() == null && project.getSampleScheduledDate()==null){
							status = "新立项项目";
						}
						//正在进行的项目判断
						if((project.getFinish()==0 && project.getSampleFinish() ==0 &&   pr.size()> 0 && project.getDeliveryDate()==null && project.getSampleScheduledDate()== null)
							     ||(project.getFinish()==0 && project.getSampleFinish() ==0  && ((project.getDeliveryDate()==null &&  project.getSampleScheduledDate()!=null && project.getSampleScheduledDate().getTime()+7*24*60*60*1000>today.getTime())
							     ||(project.getSampleScheduledDate()==null && project.getDeliveryDate()!=null && project.getDeliveryDate().getTime()+7*24*60*60*1000>today.getTime())
							     ||(project.getSampleScheduledDate()!=null && project.getDeliveryDate()!=null && project.getDeliveryDate().getTime()+7*24*60*60*1000>today.getTime())))
							     ||(project.getSampleFinish()==1 && project.getFinish()==0 && (/*(project.getDeliveryDate()==null)||*/(project.getDeliveryDate()!=null && project.getDeliveryDate().getTime()+7*24*60*60*1000 >today.getTime()))
							  )){
							status = "正在进行的项目";
						}
						//样品完结项目(点击样品完结,没有大货交期sortField=2 projectStatus=6 )
						if(project.getSampleFinish()==1 && project.getFinish()==0 && project.getDeliveryDate()==null){
							status = "样品完结项目";
						}
						//样品交期或者大货交期 延期
                     if(project.getDeliveryDate() != null){//大货交期不为空,样品交期不为空
     					if (project.getDeliveryDate() != null && project.getDeliveryDate().getTime()+7*24*60*60*1000< today.getTime() && project.getFinish() == 0
     							&& (project.getIsPause() == null || "0".equals(project.getIsPause()))) {// 交期小于当前时间,算延期
     						status = "延期项目";
     					}
     				}
     				//2.大货交期为空,样品交期不为空,样品没完结，没取消暂停
     				else if(project.getSampleScheduledDate() != null && project.getDelayType()==null){
     					if(project.getSampleScheduledDate() != null && project.getSampleScheduledDate().getTime()+7*24*60*60*1000< today.getTime() && project.getSampleFinish() == 0
     							&& (project.getIsPause() == null || "0".equals(project.getIsPause()))){
     						status = "延期项目";
     					}
     				}
     				//3.样品交期完结了大货交期没完结并且有大货交期
     				else if(project.getSampleFinish()==1 && project.getDeliveryDate() != null){
     					if (project.getDeliveryDate() != null && project.getDeliveryDate().getTime() +7*24*60*60*1000 < today.getTime() && project.getFinish() == 0
     							&& (project.getIsPause() == null || "0".equals(project.getIsPause()))) {// 交期小于当前时间,算延期
     						status = "延期项目";
     					}
     				}
					} else if ("1".contains(project.getIsPause())) {
						status = "暂停项目";

					} else if ("2".contains(project.getIsPause())) {
						status = "取消项目";
					}

				} else if (project.getFinish() == 1) {
					status = "完成项目";
				}
				project.setStatus(status);
        }

         //查询下单工厂
        List<String> factoryList = projectFactoryService.selectAllFactory(null);

        request.setAttribute("project", project);
		request.setAttribute("roleNo", roleNo);
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.setAttribute("factoryNameList", factoryList);
		return "project_task_entry";
	}


	/**
	 * 更新允许出货确认单是否收到
	 * @Title updateApprovalShipping
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/updateApprovalShipping")
	@ResponseBody
	public JsonResult updateApprovalShipping(HttpServletRequest request,HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		String projectNoId = request.getParameter("projectNoId");
		String type=request.getParameter("type");
		// 查询该检测信息(projectNoId)
		try {
			InspectionReservation inspectionReservation = inspectionReservationService.selectInspectionReservationById(projectNoId);
			inspectionReservation.setShippingApproval(Integer.parseInt(type));
			inspectionReservationService.updateInspectionReservation(inspectionReservation);

		} catch (NumberFormatException e) {
			e.printStackTrace();
			jsonResult.setOk(false);
			jsonResult.setMessage(e.getMessage());
		} finally {
			return jsonResult;
		}

	}






    /**
     * 导出预约检验任务
     * @Title exportProject
     * @Description
     * @param request
     * @param response
     * @return void
     */
	@RequestMapping(value="/exportInspection")
	public void exportInspection(HttpServletRequest request,HttpServletResponse response){

        try {
        	String projectNo=request.getParameter("projectNo");
    		String inputKey=request.getParameter("inputKey");
    		String userName=request.getParameter("userName");
    		String qualityName=request.getParameter("quality_name");
    		String taskStatus=request.getParameter("taskStatus");
    		String approvalSelect = request.getParameter("approvalSelect");
    		String qualityNames = request.getParameter("qualityNames");

    		InspectionReservation inspection=new InspectionReservation();
    		User user=userService.selectUserByName(userName);
    		if(user.getRoleNo()==100 || user.getRoleNo()==99 || user.getRoleNo()==98 ||user.getRoleNo()==97 || "AndsXue".equals(userName)){
    			inspection.setUserName(null);
    		}else{
    			inspection.setUserName(userName);
    		}

    		//根据质检名进行筛选
    		String[] strs ={};
    		if(StringUtils.isNotBlank(qualityNames)){
    			strs = qualityNames.split(",");
    			inspection.setQualityNames(strs);
    		}
    		inspection.setProjectNo(projectNo);
    		inspection.setInputKey(inputKey);
    		inspection.setQualityName(qualityName);
    		inspection.setTaskStatus(taskStatus);
    		if(StringUtils.isNotBlank(approvalSelect)){
    			inspection.setShippingApproval(Integer.parseInt(approvalSelect));
    		}
    		List<InspectionReservation> inspectionList=inspectionReservationService.selectInspectionReservation(inspection);
			String excelPath = InspectionPrint.printExcel(request, inspectionList);
			File outFile = new File(excelPath);
			InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			String fileName = "检验任务"+DateFormat.currentDate()+".xls";
			fileName = URLEncoder.encode(fileName, "utf-8");                                  //这里要用URLEncoder转下才能正确显示中文名称
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Content-Length", "" + outFile.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 *
	 * @Title:InspectionReservationController
	 * @Description:随机时间检验表
	   @author wangyang
	 * @param request
	 * @param response void
	 * @throws
	 */
	@RequestMapping(value="/exportInspectionTask")
	public void exportInspectionTask(HttpServletRequest request,HttpServletResponse response){
		try {
			String time=request.getParameter("time");
			String time1=request.getParameter("time1");
			String userName=request.getParameter("userName");
			User user=userService.selectUserByName(userName);
			User user1=new User();
			if(time!=null&&!"".equalsIgnoreCase(time)){
			user1.setStartTime(time);
			}else{
				time=null;
				user1.setStartTime(time);
			}
			if(time1!=null&&!"".equalsIgnoreCase(time1)){
				user1.setEndTime(time1);
				}else{
					time1=null;
					user1.setEndTime(time1);
				}
			List<User>list=userService.getInspectionTask(user1);
			String excelPath = InspectionPrint.printExcel1(request, list,time,time1);
			File outFile = new File(excelPath);
			InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			String fileName ="";
			if(time==null&&time1==null){
				fileName ="最近30天检验项目情况延期表"+DateFormat.currentDate()+".xls";
			}else{
		    fileName =time+"至"+time1+ "检验项目情况延期表"+DateFormat.currentDate()+".xls";
			}


			fileName = URLEncoder.encode(fileName, "utf-8");                                  //这里要用URLEncoder转下才能正确显示中文名称
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Content-Length", "" + outFile.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 质检地图列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
    @RequestMapping("/qualityInspectionMap")
    public String qualityInspectionMap(HttpServletRequest request,HttpServletResponse response) throws ParseException{
    	String userName=request.getParameter("userName");
    	String userId=request.getParameter("userId");
    	String roleNo=request.getParameter("roleNo");
    	Date date = new Date();
    	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		String currSun = dateFm.format(date);
		String today=dateformat.format(date);
		Date date2 = dateformat.parse(today);
		String startTime="";
		String endTime="";
		String lastWeekMonday="";
		String lastWeekSunday="";
		InspectionReservation task=new InspectionReservation();
		  if("星期一".equalsIgnoreCase(currSun)){
        		startTime=getNextSunday(-7);
        		endTime=getNextMonday(14);
        		lastWeekMonday=getNextSunday(-35);
        		lastWeekSunday=	getNextMonday(0);
        	}else if("星期二".equalsIgnoreCase(currSun)){
        		startTime=getNextSunday(-8);
        		endTime=getNextMonday(13);
        		lastWeekMonday=getNextSunday(-36);
        		lastWeekSunday=	getNextMonday(-1);
        	}else if("星期三".equalsIgnoreCase(currSun)){
        		startTime=getNextSunday(-9);
        		endTime=getNextMonday(12);
        		lastWeekMonday=getNextSunday(-37);
        		lastWeekSunday=	getNextMonday(-2);
        	}else if("星期四".equalsIgnoreCase(currSun)){
        		startTime=getNextSunday(-10);
        		endTime=getNextMonday(11);
        		lastWeekMonday=getNextSunday(-38);
        		lastWeekSunday=	getNextMonday(-3);
        	}else if("星期五".equalsIgnoreCase(currSun)){
        		startTime=getNextSunday(-11);
        		endTime=getNextMonday(10);
        		lastWeekMonday=getNextSunday(-39);
        		lastWeekSunday=	getNextMonday(-4);
        	}else if("星期六".equalsIgnoreCase(currSun)){
        		startTime=getNextSunday(-12);
        		endTime=getNextMonday(9);
        		lastWeekMonday=getNextSunday(-40);
        		lastWeekSunday=	getNextMonday(-5);
        	}else if("星期日".equalsIgnoreCase(currSun)){
        		startTime=getNextSunday(-13);
        		endTime=getNextMonday(8);
        		lastWeekMonday=getNextSunday(-41);
        		lastWeekSunday=	getNextMonday(-6);
        	}


		  List<InspectionReservation>projectTasks5=new ArrayList<InspectionReservation>();
		     List<InspectionReservation>projectTasks4=new ArrayList<InspectionReservation>();
		     List<InspectionReservation>projectTasks1=new ArrayList<InspectionReservation>();
             List<InspectionReservation>projectTasks2=new ArrayList<InspectionReservation>();
             List<InspectionReservation>projectTasks3=new ArrayList<InspectionReservation>();
             List<InspectionReservation>projectTasks=new ArrayList<InspectionReservation>();
        	if("9".equalsIgnoreCase(roleNo)){
        	task.setAccepter(userName);
        	task.setStart(startTime);
        	task.setEnd(endTime);
        	task.setTaskStatus("-1");
        	projectTasks=inspectionReservationService.getQualityInspectionMap(task);
        	task.setStart(lastWeekMonday);
        	task.setEnd(lastWeekSunday);
        	task.setTaskStatus("0");
        	projectTasks5=inspectionReservationService.getQualityInspectionMap(task);
        	}else{
        	task.setStart(startTime);
            task.setEnd(endTime);
            task.setTaskStatus("-1");
            projectTasks=inspectionReservationService.getQualityInspectionMap(task);
            task.setStart(lastWeekMonday);
        	task.setEnd(lastWeekSunday);
        	task.setTaskStatus("0");
            projectTasks5=inspectionReservationService.getQualityInspectionMap(task);
           }
        	Project project=new Project();
        	project.setStartTime(startTime);
        	project.setEndTime(endTime);
        	int noInspectionTask=projectService.getNoInspectionTask(project);//查询无质检任务项目数
        	int noInterimInspection=projectService.getNoInterimInspection(project);//查询第一次大货，尚未安排中期检验项目
        	//
        	projectTasks1=getMessage(projectTasks,1);
        	//匹配钉钉货物认领
        	goodsEntry(projectTasks1);



        	//projectTasks2=getMessage(projectTasks,2);
        	projectTasks3=getMessage(projectTasks,3);
        	projectTasks4=getMessage(projectTasks5,1);
        	request.setAttribute("noInspectionTask", noInspectionTask);
        	request.setAttribute("noInterimInspection", noInterimInspection);
        	request.setAttribute("date2", date2);
        	 request.setAttribute("userName", userName);
        	 request.setAttribute("userId", userId);
        	 request.setAttribute("roleNo", roleNo);
             request.setAttribute("projectTasks1", projectTasks1);
             request.setAttribute("projectTasks2", projectTasks2);
             request.setAttribute("projectTasks3", projectTasks3);
             request.setAttribute("projectTasks4", projectTasks4);
             request.setAttribute("lastWeekMonday", lastWeekMonday);
     		 request.setAttribute("lastWeekSunday", lastWeekSunday);
             request.setAttribute("start", startTime);
    		 request.setAttribute("end", endTime);
        return "quality_map";
    }

    /**匹配钉钉货物认领
     * @param projectTaskslistEntry
     */
    private void goodsEntry(List<InspectionReservation> projectTasks) {
    	if(CollectionUtils.isEmpty(projectTasks)) {
    		return ;
    	}
    	List<String> checkNumbers = projectTasks.stream().filter(p->StringUtils.isNotEmpty(p.getCheckNumber()) && "仓库".equals(p.getInspectionAddress()))
    											.map(p->p.getCheckNumber()).collect(Collectors.toList());

		List<GoodsEntry> listEntry = goodsEntryService.listEntryByCheckNumber(checkNumbers);
		if(CollectionUtils.isEmpty(listEntry)) {
			return ;
		}
		Map<String,List<String>> dTalk = Maps.newHashMap();
		listEntry.stream().forEach(l->{
			if("COMPLETED".equals(l.getStatus())) {
				String key = l.getCheckNumber();
				List<String> value = dTalk.get(key);
				value = value == null ? Lists.newArrayList() : value;
				if(!value.contains(l.getBusinessId())) {
					value.add(l.getBusinessId());
				}
				dTalk.put(key, value);
			}
		});

		projectTasks.stream().forEach(p->{
			if(StringUtils.isBlank(p.getCheckNumber())) {
				p.setCheckNumber("");
			}
			if("仓库".equals(p.getInspectionAddress()) && StringUtils.isNotBlank(p.getCheckNumber())) {
				List<String> value = dTalk.get(p.getCheckNumber());
				p.setdTalkIds(value == null ? 0 : value.size());
			}
		});
	}

	private List<InspectionReservation> getMessage(
			List<InspectionReservation> projectTasks,int num) {
    	List<InspectionReservation> projectTasks1=new ArrayList<InspectionReservation>();
    	for(InspectionReservation projectTask :projectTasks){
         	String projectNo=projectTask.getProjectNo();
         	ProjectComplaint complaint=projectComplaintService.getLate(projectNo);
         	if("1".equalsIgnoreCase(projectTask.getPlantAnalysis())||"2".equalsIgnoreCase(projectTask.getPlantAnalysis())){
         		QuotePrice price=tquotePriceService.getOne(projectNo);
             	List<ProjectInspectionReport> inspectionPlanList=projectInspectionReportService.selectInspectionPlanByProjectNo1(projectNo);
         		if(price==null){
         			projectTask.setIncomingInspection("缺");
         		}else{
         			String incoming=price.getUploadurl();
         			if(incoming.contains("http")){
         			projectTask.setIncomingInspection("<a href='"+incoming+"' target='_blank'>进料检验</a>");
         			}else{
         				projectTask.setIncomingInspection("缺");
         			}
         		}
         		if(inspectionPlanList.size()>0){
         			String inspectionPlan="";
         			for(ProjectInspectionReport report :inspectionPlanList){
         				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         				Date date1=report.getCreateDate();
         				String time=dateFormat.format(date1);
         				if(report.getEmailContent()!=""&&report.getEmailContent()!=null){
         					inspectionPlan= report.getReportName();
         					projectTask.setInspectionPlan("<a href='"+inspectionPlan+"' target='_blank'>"+time+"</a>");
         				}else{
         				inspectionPlan= "http://117.144.21.74:33168/upload/po/upload/JIANYANJIHUAZJ/"+report.getReportName();
         				projectTask.setInspectionPlan("<a href='"+inspectionPlan+"' target='_blank'>"+time+"</a>");
         				}
         			}


         		}else{
         			projectTask.setInspectionPlan("缺");
         		}


         	}else{
         		projectTask.setIncomingInspection("不需要");
         		projectTask.setInspectionPlan("不需要");
         	}
          if(complaint!=null){
         	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date1=complaint.getCreateTime();
				String time=dateFormat.format(date1);
         	 String qualityComplaint="https://www.kuaizhizao.cn/complaint/queryComplaint?id="+complaint.getId();
         	 projectTask.setQualityComplaint("<a href='"+qualityComplaint+"' target='_blank'>"+time+"</a>");
          }else{
         	 projectTask.setQualityComplaint("无");
          }

    	 if("1".equalsIgnoreCase(projectTask.getTaskStatus())){
         if(num==3){
       	  projectTasks1.add(projectTask);
         }
         }else{
         if(num==1){
       	  projectTasks1.add(projectTask);
         }
         }
    	}
    	return projectTasks1;
      }


	public String getNextMonday(int count) {
        Calendar strDate = Calendar.getInstance();
        strDate.add(strDate.DATE,count);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.set(strDate.get(Calendar.YEAR), strDate.get(Calendar.MONTH),strDate.get(Calendar.DATE));
        Date monday = currentDate.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }
    public String getNextTuesday(int count) {
        Calendar strDate = Calendar.getInstance();
        strDate.add(strDate.DATE,count);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.set(strDate.get(Calendar.YEAR), strDate.get(Calendar.MONTH),strDate.get(Calendar.DATE));
        currentDate.add(GregorianCalendar.DATE, 1);
        Date monday = currentDate.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }
    public String getNextWednesday(int count) {
        Calendar strDate = Calendar.getInstance();
        strDate.add(strDate.DATE,count);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.set(strDate.get(Calendar.YEAR), strDate.get(Calendar.MONTH),strDate.get(Calendar.DATE));
        currentDate.add(GregorianCalendar.DATE, 2);
        Date monday = currentDate.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }
    public String getNextThursday(int count) {
    	Calendar strDate = Calendar.getInstance();
    	strDate.add(strDate.DATE,count);
    	GregorianCalendar currentDate = new GregorianCalendar();
    	currentDate.set(strDate.get(Calendar.YEAR), strDate.get(Calendar.MONTH),strDate.get(Calendar.DATE));
    	currentDate.add(GregorianCalendar.DATE, 3);
    	Date monday = currentDate.getTime();
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String preMonday = df.format(monday);
    	return preMonday;
    }
    public String getNextFriday(int count) {
    	Calendar strDate = Calendar.getInstance();
    	strDate.add(strDate.DATE,count);
    	GregorianCalendar currentDate = new GregorianCalendar();
    	currentDate.set(strDate.get(Calendar.YEAR), strDate.get(Calendar.MONTH),strDate.get(Calendar.DATE));
    	currentDate.add(GregorianCalendar.DATE, 4);
    	Date monday = currentDate.getTime();
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String preMonday = df.format(monday);
    	return preMonday;
    }
    public String getNextSaturday(int count) {
    	Calendar strDate = Calendar.getInstance();
    	strDate.add(strDate.DATE,count);
    	GregorianCalendar currentDate = new GregorianCalendar();
    	currentDate.set(strDate.get(Calendar.YEAR), strDate.get(Calendar.MONTH),strDate.get(Calendar.DATE));
    	currentDate.add(GregorianCalendar.DATE, 5);
    	Date monday = currentDate.getTime();
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String preMonday = df.format(monday);
    	return preMonday;
    }

    public String getNextSunday(int count)
    {

        GregorianCalendar currentDate = new GregorianCalendar();
        Calendar strDate = Calendar.getInstance();
        strDate.add(strDate.DATE,count);
//        System.out.println("=="+strDate.getTime());
        currentDate.set(strDate.get(Calendar.YEAR), strDate.get(Calendar.MONTH),strDate.get(Calendar.DATE));
        currentDate.add(GregorianCalendar.DATE, 6);
        Date monday = currentDate.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }


    @RequestMapping("/searchAll")
	@ResponseBody
	public JsonResult customerStatistics(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
	  try {
		    String num=request.getParameter("num");
		    String userName=request.getParameter("userName");
	    	String roleNo=request.getParameter("roleNo");
		    Date date = new Date();
	    	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
			String currSun = dateFm.format(date);
			SimpleDateFormat dateFm1 = new SimpleDateFormat("HH");
			int hour=Integer.parseInt(dateFm1.format(date));
			String today=dateformat.format(date);
			Date date2 = dateformat.parse(today);
		    String Monday="";
			String Tuesday="";
			String Wednesday="";
			String Thursday="";
			String Friday="";
			String Saturday="";
			String Sunday="";
			String NextMonday="";
			String NextTuesday="";
			String NextWednesday="";
			String NextThursday="";
			String NextFriday="";
			String NextSaturday="";
			String NextSunday="";
			String lastMonday="";
			String lastTuesday="";
			String lastWednesday="";
			String lastThursday="";
			String lastFriday="";
			String lastSaturday="";
			String lastSunday="";
			InspectionReservation task=new InspectionReservation();

	        	if("星期一".equalsIgnoreCase(currSun)){
	        		Monday=getNextMonday(0);
	        		Tuesday=getNextTuesday(0);
	        		Wednesday=getNextWednesday(0);
	        		Thursday=getNextThursday(0);
	        		Friday=getNextFriday(0);
	        		Saturday=getNextSaturday(0);
	        		Sunday=getNextSunday(0);
	        		NextMonday=getNextMonday(7);
	    			NextTuesday=getNextTuesday(7);
	    			NextWednesday=getNextWednesday(7);
	    			NextThursday=getNextThursday(7);
	    			NextFriday=getNextFriday(7);
	    			NextSaturday=getNextSaturday(7);
	    			NextSunday=getNextSunday(7);
	    			lastMonday=getNextMonday(-7);
	    			lastTuesday=getNextTuesday(-7);
	    			lastWednesday=getNextWednesday(-7);
	    			lastThursday=getNextThursday(-7);
	    			lastFriday=getNextFriday(-7);
	    			lastSaturday=getNextSaturday(-7);
	    			lastSunday=getNextSunday(-7);
	        	}else if("星期二".equalsIgnoreCase(currSun)){
	        		Monday=getNextMonday(-1);
	        		Tuesday=getNextTuesday(-1);
	        		Wednesday=getNextWednesday(-1);
	        		Thursday=getNextThursday(-1);
	        		Friday=getNextFriday(-1);
	        		Saturday=getNextSaturday(-1);
	        		Sunday=getNextSunday(-1);
	        		NextMonday=getNextMonday(6);
	    			NextTuesday=getNextTuesday(6);
	    			NextWednesday=getNextWednesday(6);
	    			NextThursday=getNextThursday(6);
	    			NextFriday=getNextFriday(6);
	    			NextSaturday=getNextSaturday(6);
	    			NextSunday=getNextSunday(6);
	    			lastMonday=getNextMonday(-8);
	    			lastTuesday=getNextTuesday(-8);
	    			lastWednesday=getNextWednesday(-8);
	    			lastThursday=getNextThursday(-8);
	    			lastFriday=getNextFriday(-8);
	    			lastSaturday=getNextSaturday(-8);
	    			lastSunday=getNextSunday(-8);
	        	}else if("星期三".equalsIgnoreCase(currSun)){
	        		Monday=getNextMonday(-2);
	        		Tuesday=getNextTuesday(-2);
	        		Wednesday=getNextWednesday(-2);
	        		Thursday=getNextThursday(-2);
	        		Friday=getNextFriday(-2);
	        		Saturday=getNextSaturday(-2);
	        		Sunday=getNextSunday(-2);
	        		NextMonday=getNextMonday(5);
	    			NextTuesday=getNextTuesday(5);
	    			NextWednesday=getNextWednesday(5);
	    			NextThursday=getNextThursday(5);
	    			NextFriday=getNextFriday(5);
	    			NextSaturday=getNextSaturday(5);
	    			NextSunday=getNextSunday(5);
	    			lastMonday=getNextMonday(-9);
	    			lastTuesday=getNextTuesday(-9);
	    			lastWednesday=getNextWednesday(-9);
	    			lastThursday=getNextThursday(-9);
	    			lastFriday=getNextFriday(-9);
	    			lastSaturday=getNextSaturday(-9);
	    			lastSunday=getNextSunday(-9);
	        	}else if("星期四".equalsIgnoreCase(currSun)){
	        		Monday=getNextMonday(-3);
	        		Tuesday=getNextTuesday(-3);
	        		Wednesday=getNextWednesday(-3);
	        		Thursday=getNextThursday(-3);
	        		Friday=getNextFriday(-3);
	        		Saturday=getNextSaturday(-3);
	        		Sunday=getNextSunday(-3);
	        		NextMonday=getNextMonday(4);
	    			NextTuesday=getNextTuesday(4);
	    			NextWednesday=getNextWednesday(4);
	    			NextThursday=getNextThursday(4);
	    			NextFriday=getNextFriday(4);
	    			NextSaturday=getNextSaturday(4);
	    			NextSunday=getNextSunday(4);
	    			lastMonday=getNextMonday(-10);
	    			lastTuesday=getNextTuesday(-10);
	    			lastWednesday=getNextWednesday(-10);
	    			lastThursday=getNextThursday(-10);
	    			lastFriday=getNextFriday(-10);
	    			lastSaturday=getNextSaturday(-10);
	    			lastSunday=getNextSunday(-10);
	        	}else if("星期五".equalsIgnoreCase(currSun)){
	        		Monday=getNextMonday(-4);
	        		Tuesday=getNextTuesday(-4);
	        		Wednesday=getNextWednesday(-4);
	        		Thursday=getNextThursday(-4);
	        		Friday=getNextFriday(-4);
	        		Saturday=getNextSaturday(-4);
	        		Sunday=getNextSunday(-4);
	        		NextMonday=getNextMonday(3);
	    			NextTuesday=getNextTuesday(3);
	    			NextWednesday=getNextWednesday(3);
	    			NextThursday=getNextThursday(3);
	    			NextFriday=getNextFriday(3);
	    			NextSaturday=getNextSaturday(3);
	    			NextSunday=getNextSunday(3);
	    			lastMonday=getNextMonday(-11);
	    			lastTuesday=getNextTuesday(-11);
	    			lastWednesday=getNextWednesday(-11);
	    			lastThursday=getNextThursday(-11);
	    			lastFriday=getNextFriday(-11);
	    			lastSaturday=getNextSaturday(-11);
	    			lastSunday=getNextSunday(-11);
	        	}else if("星期六".equalsIgnoreCase(currSun)){
	        		Monday=getNextMonday(-5);
	        		Tuesday=getNextTuesday(-5);
	        		Wednesday=getNextWednesday(-5);
	        		Thursday=getNextThursday(-5);
	        		Friday=getNextFriday(-5);
	        		Saturday=getNextSaturday(-5);
	        		Sunday=getNextSunday(-5);
	        		NextMonday=getNextMonday(2);
	    			NextTuesday=getNextTuesday(2);
	    			NextWednesday=getNextWednesday(2);
	    			NextThursday=getNextThursday(2);
	    			NextFriday=getNextFriday(2);
	    			NextSaturday=getNextSaturday(2);
	    			NextSunday=getNextSunday(2);
	    			lastMonday=getNextMonday(-12);
	    			lastTuesday=getNextTuesday(-12);
	    			lastWednesday=getNextWednesday(-12);
	    			lastThursday=getNextThursday(-12);
	    			lastFriday=getNextFriday(-12);
	    			lastSaturday=getNextSaturday(-12);
	    			lastSunday=getNextSunday(-12);
	        	}else if("星期日".equalsIgnoreCase(currSun)){

	        		Monday=getNextMonday(-6);
	        		Tuesday=getNextTuesday(-6);
	        		Wednesday=getNextWednesday(-6);
	        		Thursday=getNextThursday(-6);
	        		Friday=getNextFriday(-6);
	        		Saturday=getNextSaturday(-6);
	        		Sunday=getNextSunday(-6);
	        		NextMonday=getNextMonday(1);
	    			NextTuesday=getNextTuesday(1);
	    			NextWednesday=getNextWednesday(1);
	    			NextThursday=getNextThursday(1);
	    			NextFriday=getNextFriday(1);
	    			NextSaturday=getNextSaturday(1);
	    			NextSunday=getNextSunday(1);
	    			lastMonday=getNextMonday(-13);
	    			lastTuesday=getNextTuesday(-13);
	    			lastWednesday=getNextWednesday(-13);
	    			lastThursday=getNextThursday(-13);
	    			lastFriday=getNextFriday(-13);
	    			lastSaturday=getNextSaturday(-13);
	    			lastSunday=getNextSunday(-13);
	        	}

			List<String>dayList=new ArrayList<String>();
			if("1".equalsIgnoreCase(num)){
			dayList.add(Monday);
			dayList.add(Tuesday);
			dayList.add(Wednesday);
			dayList.add(Thursday);
			dayList.add(Friday);
			dayList.add(Saturday);
			dayList.add(Sunday);
			}else if("2".equalsIgnoreCase(num)){
			dayList.add(NextMonday);
			dayList.add(NextTuesday);
			dayList.add(NextWednesday);
			dayList.add(NextThursday);
			dayList.add(NextFriday);
			dayList.add(NextSaturday);
			dayList.add(NextSunday);
			}else if("3".equalsIgnoreCase(num)){
				dayList.add(lastMonday);
				dayList.add(lastTuesday);
				dayList.add(lastWednesday);
				dayList.add(lastThursday);
				dayList.add(lastFriday);
				dayList.add(lastSaturday);
				dayList.add(lastSunday);
			}
	        Map<String,String> map = new HashMap<String, String>();
	        for(int i=0;i<dayList.size();i++){
	        	InspectionReservation inspection=new InspectionReservation();
	        	inspection.setStart(dayList.get(i));

	        List<InspectionReservation>projectTasks=inspectionReservationService.getAll(inspection,num);
		    String json= JSONArray.fromObject(projectTasks).toString();
            map.put("projectTasks"+i,json);
            }
	        if("1".equalsIgnoreCase(num)){
	        map.put("Monday",Monday);
	        map.put("Tuesday",Tuesday);
	        map.put("Wednesday",Wednesday);
	        map.put("Thursday",Thursday);
	        map.put("Friday",Friday);
	        map.put("Saturday",Saturday);
	        map.put("Sunday",Sunday);
	        }else if("2".equalsIgnoreCase(num)){
	        map.put("Monday",NextMonday);
	        map.put("Tuesday",NextTuesday);
	        map.put("Wednesday",NextWednesday);
	        map.put("Thursday",NextThursday);
	        map.put("Friday",NextFriday);
	        map.put("Saturday",NextSaturday);
	        map.put("Sunday",NextSunday);
	        }else  if("3".equalsIgnoreCase(num)){
	        	map.put("Monday",lastMonday);
		        map.put("Tuesday",lastTuesday);
		        map.put("Wednesday",lastWednesday);
		        map.put("Thursday",lastThursday);
		        map.put("Friday",lastFriday);
		        map.put("Saturday",lastSaturday);
		        map.put("Sunday",lastSunday);
	        }


    	    jsonResult.setOk(true);
			jsonResult.setMessage("留言成功");
			jsonResult.setData(map);
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage("留言失败");
			e.printStackTrace();
		}
		return jsonResult;
	}
    /**
     *
     * @Title:InspectionReservationController
     * @Description:查询两表数据
       @author wangyang
     * @param request
     * @param response
     * @return JsonResult
     * @throws
     */

    @RequestMapping("/searchProductionCompletion")
   	@ResponseBody
   	public JsonResult searchProductionCompletion(HttpServletRequest request,
   			HttpServletResponse response) {
   		JsonResult jsonResult = new JsonResult();
   	  try {
   		    String num=request.getParameter("num");
   		    String userName=request.getParameter("userName");
   	    	String roleNo=request.getParameter("roleNo");
   	    	Date date = new Date();
   	    	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
   			SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
   			String currSun = dateFm.format(date);
   			String today=dateformat.format(date);
   			Date date2 = dateformat.parse(today);
   			String startTime="";
   			String endTime="";
   			String lastWeekMonday="";
   			String lastWeekSunday="";
   			InspectionReservation task=new InspectionReservation();
   			  if("星期一".equalsIgnoreCase(currSun)){
   	        		startTime=getNextSunday(-7);
   	        		endTime=getNextMonday(14);

   	        	}else if("星期二".equalsIgnoreCase(currSun)){
   	        		startTime=getNextSunday(-8);
   	        		endTime=getNextMonday(13);

   	        	}else if("星期三".equalsIgnoreCase(currSun)){
   	        		startTime=getNextSunday(-9);
   	        		endTime=getNextMonday(12);

   	        	}else if("星期四".equalsIgnoreCase(currSun)){
   	        		startTime=getNextSunday(-10);
   	        		endTime=getNextMonday(11);

   	        	}else if("星期五".equalsIgnoreCase(currSun)){
   	        		startTime=getNextSunday(-11);
   	        		endTime=getNextMonday(10);

   	        	}else if("星期六".equalsIgnoreCase(currSun)){
   	        		startTime=getNextSunday(-12);
   	        		endTime=getNextMonday(9);

   	        	}else if("星期日".equalsIgnoreCase(currSun)){
   	        		startTime=getNextSunday(-13);
   	        		endTime=getNextMonday(8);

   	        	}
   			Project project=new Project();
        	project.setStartTime(startTime);
        	project.setEndTime(endTime);
        	List<Project> noInspectionTaskList=projectService.getNoInspectionTaskList(project);//查询无质检任务项目数
        	List<Project> noInterimInspectionList=projectService.getNoInterimInspectionList(project);//查询第一次大货，尚未安排中期检验项目
        	Map<String,String> map = new HashMap<String, String>();
   		    String noInspectionTask= JSONArray.fromObject(noInspectionTaskList).toString();
            map.put("noInspectionTaskList",noInspectionTask);
            String noInterimInspection= JSONArray.fromObject(noInterimInspectionList).toString();
            map.put("noInterimInspectionList",noInterimInspection);
   	        jsonResult.setOk(true);
   			jsonResult.setMessage("留言成功");
   			jsonResult.setData(map);
   		} catch (Exception e) {
   			jsonResult.setOk(false);
   			jsonResult.setMessage("留言失败");
   			e.printStackTrace();
   		}
   		return jsonResult;
   	}
    @RequestMapping("/searchGoodsEntry")
    @ResponseBody
    public JsonResult searchGoodsEntry(HttpServletRequest request,
    		HttpServletResponse response) {
    	JsonResult jsonResult = new JsonResult();
    	try {
    		String startTime = DateUtil.getIntervalDate(-28);
    		List<GoodsEntry> listEntry = goodsEntryService.listEntry(startTime, null);
    		Map<String,Object> map = Maps.newHashMap();
    		String noInterimInspection= JSONArray.fromObject(listEntry).toString();
    		map.put("listEntry",noInterimInspection);
    		jsonResult.setOk(true);
    		jsonResult.setMessage("成功");
    		jsonResult.setData(map);
    	} catch (Exception e) {
    		jsonResult.setOk(false);
    		jsonResult.setMessage("失败");
    		e.printStackTrace();
    	}
    	return jsonResult;
    }

    private Map<String,String> getLastDay(){
    	Map<String,String> result = Maps.newHashMap();
    	String startTime="";
		String endTime="";
		String currSun = new SimpleDateFormat("EEEE").format(new Date());

		switch (currSun) {
		case "星期一":
			startTime=getNextMonday(-8);
			endTime=getNextSunday(-6);
			break;
		case "星期二":
			startTime=getNextMonday(-9);
			endTime=getNextSunday(-7);
			break;
		case "星期三":
			startTime=getNextMonday(-10);
			endTime=getNextSunday(-8);
			break;
		case "星期四":
			startTime=getNextMonday(-11);
			endTime=getNextSunday(-9);
			break;
		case "星期五":
			startTime=getNextMonday(-12);
			endTime=getNextSunday(-10);
			break;
		case "星期六":
			startTime=getNextMonday(-13);
			endTime=getNextSunday(-11);
			break;
		case "星期日":
			startTime=getNextMonday(-14);
			endTime=getNextSunday(-12);
			break;
		default:
			break;
		}
    	result.put("startTime", startTime);
    	result.put("endTime", endTime);
    	return result;
    }

    public static void main(String[] args) {
    	InspectionReservationController t = new InspectionReservationController();
    	System.out.println(t.getNextSunday(-6));
	}
}
