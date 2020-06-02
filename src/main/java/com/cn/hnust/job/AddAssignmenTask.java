package com.cn.hnust.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.cookie.RFC2109DomainHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.component.RpcHelper;
import com.cn.hnust.pojo.InspectionPlanInfo;
import com.cn.hnust.pojo.InspectionReservation;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.RoleBindDetail;
import com.cn.hnust.pojo.TaskFlow;
import com.cn.hnust.service.IInspectionPlanInfoService;
import com.cn.hnust.service.IInspectionReservationService;
import com.cn.hnust.service.IMeetingRecordService;
import com.cn.hnust.service.IProjectERPService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.IRoleBindListService;
import com.cn.hnust.service.IStatusEnterService;
import com.cn.hnust.service.ITaskFlowService;
import com.cn.hnust.thread.HttpThread;

/*
 * 绘图、技术分析、包装分析(推送的是ERP项目上的人员,跟单采购质检销售)
 */
public class AddAssignmenTask {

	private static boolean add_assignmen_running = false;

	private final static Logger log = Logger.getLogger(AddAssignmenTask.class);

	@Resource
	private ITaskFlowService taskFlowService;

	@Resource
	private IProjectService projectService;

	@Resource
	private IProjectERPService projectErpService;

	@Resource
	private IProjectTaskService projectTaskService;

	@Resource
	private IMeetingRecordService meetingService;
	
	@Resource
	private IStatusEnterService statusEnterService;
	
	@Resource
	private IInspectionPlanInfoService inspectionPlanInfoService;

	@Resource
	private IProjectERPService projectERPService;
	
	@Autowired
	private IInspectionReservationService inspectionReservationService;
	
	public static synchronized boolean isRunning() {
		if (add_assignmen_running) {
			return true;
		} else {
			add_assignmen_running = false;
		}
		return add_assignmen_running;
	}

	public void execute() {
		if (isRunning()) {
			return;
		}
		log.info("--->addassignmenTask go...");
		startJob();
	}

	public void startJob() {
		try {
			add_assignmen_running = true;
			
			checkDocumentaryFirst();
			/*
			 * 跟单
			 */
			checkDocumentary();
			/*
			 * 启动会
			 */
			checkStartMeeting();
			/*
			 * 样品检验会
			 */
			checkQualityMeeting();
			/**
			 * 跟单销售对项目提出的质量投诉
			 */
			projectQualityComplaint();
			/**
			 * 交期延期一个星期
			 */
			projectDeliveryDateDelay();
			
			/**
			 * 
			 * 王工审核的质检报告,但是没有质检的项目,定时任务去遍历查询是否由质检
			 */
			selectInspectionPlanTask();
		} catch (Exception e) {
			log.error("--->addassignmenTask error...");
			e.printStackTrace();
		} finally {

			log.error("--->addassignmenTask end...");
			add_assignmen_running = false;
		}
	}

	/*
	 * 项目创建(项目刚创建的时候)
	 */
	public void checkDocumentaryFirst() throws Exception {
		Set<String> proSet = null;
		try {
			List<ProjectERP> list = projectErpService.selectConditionFirst();

			proSet = saveData(list);

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (proSet != null && !proSet.isEmpty()) {
				projectService.updateaddFlogByProjectNoFirst(proSet);
			}

		}

	}
	
	
	/*
	 * 进入跟单(项目进入更多状态)
	 */
	public void checkDocumentary() throws Exception {
		Set<String> proSet = null;
		try {
			List<ProjectERP> list = projectErpService.selectCondition();

			proSet = saveData(list);

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (proSet != null && !proSet.isEmpty()) {
				projectService.updateaddFlogByProjectNo(proSet);
			}

		}

	}

	/**
	 * 项目进行了启动会
	 * @throws Exception
	 */
	public void checkStartMeeting() throws Exception {
		Set<String> proSet = null;
		String meetingName = "项目启动会";
		try {
			List<ProjectERP> list = projectErpService.selectMeetingStatus(3, meetingName);
			proSet = saveData(list);
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (!proSet.isEmpty()) {
				meetingService.updateaddFlagByProjectNo(proSet, meetingName);
			}

		}

	}
    /**
     * (样品验货会)样品质量会后，自动布置 给质检 做  大货质量检验计划
     * @throws Exception
     */
	public void checkQualityMeeting() throws Exception {
		Set<String> proSet = null;
		String meetingName = "样品验货会";
		try {
			List<ProjectERP> list = projectErpService.selectMeetingStatus(2, meetingName);
			proSet = saveData(list);

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (!proSet.isEmpty()) {
				meetingService.updateaddFlagByProjectNo(proSet, meetingName);
			}
		}
	}
	
	 /**
     * 跟单销售发出质量投诉时，触发 给采购 和 质检的解释问题和解决问题的任务
     * @throws Exception
     */
	public void projectQualityComplaint() throws Exception {
		Set<String> proSet = null;
		String feekBackFlag="1";
		try {
			List<ProjectERP> list = projectErpService.selectProjectQualityComplaint();
			proSet = saveData(list);
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (!proSet.isEmpty()) {
				statusEnterService.updateQualityComplaintFlagByProjectNo(proSet,feekBackFlag);
			}
		}
	}
	
	/**
	 * 项目原定交期 超期一周后 ，给采购布置一个解释情况的任务
	 */
	public void projectDeliveryDateDelay() throws Exception {
		Set<String> proSet = null;
		try {
			List<ProjectERP> list = projectErpService.projectDeliveryDateDelay();
			proSet = saveData(list);

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (!proSet.isEmpty()) {
//				projectService.updateProjectDeliveryDateDelay(proSet);
			}
		}
	}
	
	/**
	 * 王工审核的质检报告,但是没有质检的项目,定时任务去遍历查询是否由质检
	 */
	public void selectInspectionPlanTask() throws Exception {
		try {
			List<InspectionPlanInfo> list = inspectionPlanInfoService.selectInspectionPlanTaskNoQuality();
            if(list!=null && list.size()>0){
            	ProjectTask projectTask=null;
            	InspectionReservation inspectionReservation=inspectionReservationService.selectInspectionReservationByNo(list.get(0).getProjectNo());
            	if(inspectionReservation!=null){
            		Calendar ca = Calendar.getInstance();
        			ca.add(Calendar.DATE, 2);
        			int day = ca.get(Calendar.DAY_OF_WEEK);
        			if(day==7||day==1){
        				ca.add(Calendar.DATE, 2);
        			}	
        			Date finishTime = ca.getTime();
        			projectTask=new ProjectTask();
					projectTask.setProjectNo(list.get(0).getProjectNo());
					projectTask.setProjectNoId(list.get(0).getProjectNoId());
				    projectTask.setInitiator("system");
				    projectTask.setTaskStatus("0");
				    projectTask.setTaskType("1");
				    projectTask.setAccepter(inspectionReservation.getAccepter());
				    projectTask.setDescription("审核检验计划 步骤:编制检验计划-王工审核-质检补充-阳工审核");
				    projectTask.setCreateTime(new Date());
				    projectTask.setStartTime(new Date());
				    projectTask.setFinishTime(finishTime);
				    projectTask.setMeetingNo(list.get(0).getMeetingNo());
				    projectTaskService.insertSelective(projectTask);	
            	}else{
            		ProjectERP projectErp=projectERPService.selectProjectErpByNo(list.get(0).getProjectNo());
            		if(projectErp!=null){
                		Calendar ca = Calendar.getInstance();
            			ca.add(Calendar.DATE, 2);
            			int day = ca.get(Calendar.DAY_OF_WEEK);
            			if(day==7||day==1){
            				ca.add(Calendar.DATE, 2);
            			}	
            			Date finishTime = ca.getTime();
                		if(StringUtils.isNotBlank(projectErp.getZhijian1()) && StringUtils.isBlank(projectErp.getZhijian2())){
                			projectTask=new ProjectTask();
       						projectTask.setProjectNo(list.get(0).getProjectNo());
       						projectTask.setProjectNoId(list.get(0).getProjectNoId());
       					    projectTask.setInitiator("system");
       					    projectTask.setTaskStatus("0");
       					    projectTask.setTaskType("1");
       					    projectTask.setAccepter(projectErp.getZhijian1());
       					    projectTask.setDescription("审核检验计划 步骤:编制检验计划-王工审核-质检补充-阳工审核");
       					    projectTask.setCreateTime(new Date());
       					    projectTask.setStartTime(new Date());
       					    projectTask.setFinishTime(finishTime);
       					    projectTask.setMeetingNo(list.get(0).getMeetingNo());
       					    projectTaskService.insertSelective(projectTask);
                		}

                		if(StringUtils.isBlank(projectErp.getZhijian1()) && StringUtils.isNotBlank(projectErp.getZhijian2())){
                			projectTask=new ProjectTask();
       						projectTask.setProjectNo(list.get(0).getProjectNo());
       						projectTask.setProjectNoId(list.get(0).getProjectNoId());
       					    projectTask.setInitiator("system");
       					    projectTask.setTaskStatus("0");
       					    projectTask.setTaskType("1");
       					    projectTask.setAccepter(projectErp.getZhijian2());
       					    projectTask.setDescription("审核检验计划 步骤:编制检验计划-王工审核-质检补充-阳工审核");
       					    projectTask.setCreateTime(new Date());
       					    projectTask.setStartTime(new Date());
       					    projectTask.setFinishTime(finishTime);
       					    projectTask.setMeetingNo(list.get(0).getMeetingNo());
       					    projectTaskService.insertSelective(projectTask);
                		}
                		
                		if(StringUtils.isNotBlank(projectErp.getZhijian1()) && StringUtils.isNotBlank(projectErp.getZhijian2())){
                			projectTask=new ProjectTask();
       						projectTask.setProjectNo(list.get(0).getProjectNo());
       						projectTask.setProjectNoId(list.get(0).getProjectNoId());
       					    projectTask.setInitiator("system");
       					    projectTask.setTaskStatus("0");
       					    projectTask.setTaskType("1");
       					    projectTask.setAccepter(projectErp.getZhijian1());
       					    projectTask.setDescription("审核检验计划 步骤:编制检验计划-王工审核-质检补充-阳工审核");
       					    projectTask.setCreateTime(new Date());
       					    projectTask.setStartTime(new Date());
       					    projectTask.setFinishTime(finishTime);
       					    projectTask.setMeetingNo(list.get(0).getMeetingNo());
       					    projectTaskService.insertSelective(projectTask);
                		}   		
                	}
            	}
            	
            	
    			if(projectTask!=null){
    				InspectionPlanInfo planInfo=new InspectionPlanInfo();
    				planInfo.setId(list.get(0).getId());
    				planInfo.setSendType("1");
					inspectionPlanInfoService.updateInspectionPlanInfo(planInfo);
    				JSONObject object = JSONObject.fromObject(projectTask);
        			Map<String, String> parameters=new HashMap<String, String>();
        			parameters.put("projectTask", object.toString());
        			//url and json
        			Thread td = new HttpThread(parameters);
        			td.start();
    			}
            }
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			
		}
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public Set<String> saveData(List<ProjectERP> list) {
		Set<String> proSet = new HashSet<String>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ProjectERP rd = list.get(i);
				proSet.add(rd.getProjectNo());
				List<String> accepters = new ArrayList<String>();
				switch (rd.getRoleType()) {
				case 4:
                    //质检
					if (StringUtils.isNotBlank(rd.getZhijian1())) {
						accepters.add(rd.getZhijian1());

					}
					if (StringUtils.isNotBlank(rd.getZhijian2())) {
						accepters.add(rd.getZhijian2());

					}
					saveDetail(accepters, rd);
					break;
				case 5:
					//采购
					if (StringUtils.isNotBlank(rd.getMerchandManager2())) {
						accepters.add(rd.getMerchandManager2());
					}
					saveDetail(accepters, rd);
					break;
				case 6:
					//销售
					if (StringUtils.isNotBlank(rd.getCustomerManager())) {
						accepters.add(rd.getCustomerManager());
					}
					saveDetail(accepters, rd);
					break;
				case 7:
					//跟单
					if (StringUtils.isNotBlank(rd.getMerchandManager1())) {
						accepters.add(rd.getMerchandManager1());
					}
					saveDetail(accepters, rd);
					break;
				default:
					break;
				}
			}
		}
		return proSet;

	}

	public void saveDetail(List<String> accepters, ProjectERP rd) {

		if (accepters.size() > 0) {
			for (int i = 0; i < accepters.size(); i++) {

				ProjectTask pt = new ProjectTask();
				pt.setInitiator("system");
				pt.setAccepter(accepters.get(i));
				pt.setStartTime(new Date());
				pt.setCreateTime(new Date());
				pt.setDescription(rd.getTaskTitle());
				pt.setTaskStatus("0");
				pt.setProjectNo(rd.getProjectNo());
				pt.setTaskId(rd.getTaskId());
				pt.setIndexNum(1);
				
				Calendar ca = Calendar.getInstance();
				
				ca.add(Calendar.DATE, 2);
				
				int day = ca.get(Calendar.DAY_OF_WEEK);
				
				if(day==7||day==1){
					ca.add(Calendar.DATE, 2);
				}
				Date finishTime = ca.getTime();
			
				pt.setFinishTime(finishTime);
				projectTaskService.insertSelective(pt);
				
				JSONObject object = JSONObject.fromObject(pt);
				Map<String, String> parameters=new HashMap<String, String>();
				parameters.put("projectTask", object.toString());
				//url and json
				Thread td = new HttpThread(parameters);
				td.start();
			}

		}

	}
	
	public static void main(String[] args) {
		
		Calendar ca = Calendar.getInstance();
		
		ca.add(Calendar.DATE, 2);
		
		int day = ca.get(Calendar.DAY_OF_WEEK);
		
		if(day==7||day==1){
			ca.add(Calendar.DATE, 2);
		}
		Date finishTime = ca.getTime();
			
	}
}
