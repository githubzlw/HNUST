package com.cn.hnust.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.cn.hnust.component.RpcSendNoticeToKuai;
import com.cn.hnust.dao.DelayMapper;
import com.cn.hnust.dao.FactoryScoreMapper;
import com.cn.hnust.dao.IUserDao;
import com.cn.hnust.dao.InspectionPlanInfoMapper;
import com.cn.hnust.dao.InspectionReservationMapper;
import com.cn.hnust.dao.ProjectERPMapper;
import com.cn.hnust.dao.ProjectMapper;
import com.cn.hnust.dao.ProjectScheduleMapper;
import com.cn.hnust.dao.ProjectTaskMapper;
import com.cn.hnust.dao.RoleBindDetailMapper;
import com.cn.hnust.pojo.Delay;
import com.cn.hnust.pojo.FactoryScore;
import com.cn.hnust.pojo.InspectionPlanInfo;
import com.cn.hnust.pojo.InspectionReservation;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.ProjectSchedule;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.RoleBindDetail;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.thread.HttpThread;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;

@Service
public class ProjectTaskServiceImpl implements IProjectTaskService {

	@Autowired
	private ProjectTaskMapper projectTaskMapper;

	@Autowired
	private RoleBindDetailMapper roleBindDetailMapper;

	@Autowired
	private ProjectERPMapper projectERPMapper;
	
	@Autowired
	private InspectionPlanInfoMapper inspectionPlanInfoMapper;
	
	@Autowired
	private InspectionReservationMapper InspectionReservationMapper;
	
	@Autowired
	private ProjectScheduleMapper projectScheduleMapper;
	
	@Autowired
	private DelayMapper delayMapper;
	
	@Autowired
	private ProjectMapper projectMapper;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private FactoryScoreMapper factoryScoreMapper;

	
	/**
	 * 添加任务同时，会通过kuaizhizao.cn服务器中转发送任务提醒
	 */
	@Override
	public void addProjectTask(ProjectTask projectTask) {						
		projectTaskMapper.insertSelective(projectTask);
		
		User user = userDao.findUserByName(projectTask.getAccepter());
		if("质检部".equalsIgnoreCase(projectTask.getAccepter())){}else{
		projectTask.setDingTalkId(user.getDingTalkId());
		RpcSendNoticeToKuai.sendRequest(projectTask);
		}
	}

	@Override
	public List<ProjectTask> selectProjectTask(ProjectTask projectTask) {
		return projectTaskMapper.selectProjectTask(projectTask);
	}

	@Override
	public List<ProjectTask> selectProjectTaskCount(ProjectTask projectTask) {
		return projectTaskMapper.selectProjectTaskCount(projectTask);
	}

	@Override
	public ProjectTask selectProjectTaskById(Integer id) {
		return projectTaskMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateProjectTask(ProjectTask projectTask) {
		projectTaskMapper.updateByPrimaryKeySelective(projectTask);
	}

	/**
	 * 统计每个人未完成的数量
	 */
	@Override
	public List<ProjectTask> statisticsProjectTaskNoFinish(
			ProjectTask projectTask) {
		return projectTaskMapper.statisticsProjectTaskNoFinish(projectTask);
	}

	@Override
	public List<ProjectTask> statisticsProjectTaskFinish(ProjectTask projectTask) {
		return projectTaskMapper.statisticsProjectTaskFinish(projectTask);
	}

	@Override
	public List<ProjectTask> statisticsProjectTaskOnTime(String acceptor,String sign) {
		return projectTaskMapper.statisticsProjectTaskOnTime(acceptor,sign);
	}

	@Override
	public List<ProjectTask> statisticsProjectTaskAllFinish(String acceptor,String sign) {
		return projectTaskMapper.statisticsProjectTaskAllFinish(acceptor,sign);
	}

	@Override
	public List<ProjectTask> selectMeetingRecordTask(String meetingNo) {
		return projectTaskMapper.selectMeetingRecordTask(meetingNo);
	}

	// 统计会议任务未完成的数量
	@Override
	public List<ProjectTask> selectMeetingRecordTaskNoFinish(String acceptor) {
		return projectTaskMapper.selectMeetingRecordTaskNoFinish(acceptor);
	}

	@Override
	public List<ProjectTask> selectProjectTaskIfExist(ProjectTask projectTask) {
		return projectTaskMapper.selectProjectTaskIfExist(projectTask);
	}

	@Override
	public ProjectTask selectProjectTaskMaxDate(String userName) {
		return projectTaskMapper.selectProjectTaskMaxDate(userName);
	}

	@Override
	public int hasAlreadyTask(int triggerId, int userId, String projectNo) {
		return projectTaskMapper.hasAlreadyTask(triggerId, userId, projectNo);
	}

	@Override
	public int insertSelective(ProjectTask pro) {

		return projectTaskMapper.insertSelective(pro);
	}

	@Transactional
	@Override
	public void checkNextTask(ProjectTask pro) {
		RoleBindDetail rbd =  roleBindDetailMapper.checkNextTask(pro.getIndexNum()+1,pro.getId());
		ProjectERP projectErp = new ProjectERP();
		if(rbd!=null){
			int roleType = rbd.getRoleType();
			projectErp =  projectERPMapper.selectProjectErpByNo(pro.getProjectNo());
			if(roleType==4||roleType==5||roleType==6||roleType==7){

				
				if(projectErp!=null){
					
					List<String> accepters = new ArrayList<String>();
					
					switch (roleType) {

					case 4:

						if (StringUtils.isNotBlank(projectErp.getZhijian1())) {
							accepters.add(projectErp.getZhijian1());

						}
						if (StringUtils.isNotBlank(projectErp.getZhijian2())) {
							accepters.add(projectErp.getZhijian2());

						}
						saveDetail(accepters, rbd);
						break;
					case 5:

						if (StringUtils.isNotBlank(projectErp.getMerchandManager2())) {
							accepters.add(projectErp.getMerchandManager2());
						}
						saveDetail(accepters, rbd);
						break;
					case 6:
						if (StringUtils.isNotBlank(projectErp.getCustomerManager())) {
							accepters.add(projectErp.getCustomerManager());
						}
						saveDetail(accepters, rbd);
						break;
					case 7:
						if (StringUtils.isNotBlank(projectErp.getMerchandManager1())) {
							accepters.add(projectErp.getMerchandManager1());
						}
						saveDetail(accepters, rbd);
					
					
				}
				}
			}else{
				List<RoleBindDetail> ls =  roleBindDetailMapper.selectListByRoleType(roleType);
				saveData(ls,rbd);
				
			}
			
		}
		//如果状态taskStatus=4,将放弃的任务更新为新任务
		if(pro.getTaskStatus().equals("4")){
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DATE, 2);
			int day = ca.get(Calendar.DAY_OF_WEEK);
			if (day == 7 || day == 1) {
				ca.add(Calendar.DATE, 2);
			}
			Date finishTime = ca.getTime();
			pro.setDescription(pro.getOperateExplain());
			pro.setOperateExplain(pro.getOperateExplain());
			pro.setFinishTime(finishTime);
			pro.setOperator("");
			pro.setTaskStatus("0");
			pro.setStartTime(new Date());
			pro.setCreateTime(new Date());
			projectTaskMapper.updateByPrimaryKeySelective(pro);
		}else{
			boolean planFlag=false;
			boolean wFlag=false;
			boolean qFlag=false;
			//1.查询检验计划关联的任务信息
			List<InspectionPlanInfo> planInfoList=inspectionPlanInfoMapper.selectInspectionPlanTask(pro.getId());
			if(planInfoList!=null && planInfoList.size()>0){//代表是计划检验任务
				for(int i=0;i<planInfoList.size();i++){
					InspectionPlanInfo inspectionPlanInfo=planInfoList.get(i);
					if(pro.getTaskStatus().equals("1") && inspectionPlanInfo.getAddPlan().equals("0")){//质检完成了,就给王工发一个任务,审核该检验计划
						ProjectTask projectTask=new ProjectTask();
						projectTask.setProjectNo(inspectionPlanInfo.getProjectNo());
						projectTask.setProjectNoId(inspectionPlanInfo.getProjectNoId());
					    projectTask.setInitiator("system");
					    projectTask.setTaskStatus("0");
					    projectTask.setTaskType("1");
					    projectTask.setAccepter("wangweiping");
					    projectTask.setDescription("审核检验计划 步骤:编制检验计划-王工审核-质检补充-阳工审核");
					    projectTask.setCreateTime(new Date());
					    projectTask.setStartTime(new Date());
					    projectTask.setFinishTime(getDateAfter(new Date(),5));
					    projectTask.setMeetingNo(inspectionPlanInfo.getMeetingNo());
					    projectTaskMapper.insertSelective(projectTask);
					    
					    //更新是否操作标志
					    inspectionPlanInfo.setAddPlan("1");
					    inspectionPlanInfoMapper.updateByPrimaryKeySelective(inspectionPlanInfo);
					    
					    planFlag=true;
					}
					
					//判断王工更新任务,
					if(pro.getTaskStatus().equals("1") && inspectionPlanInfo.getwAudit().equals("0")){
                       if(inspectionPlanInfo.getAddPlan().equals("1") && !planFlag){
                    	  //1.判断验货预约是否有记录
                    	   InspectionReservation inspectionReservation=InspectionReservationMapper.selectInspectionReservationByNo(inspectionPlanInfo.getProjectNo());
                    	  if(inspectionReservation!=null){//验货预约不为空
                			 ProjectTask projectTask=new ProjectTask();
       						 projectTask.setProjectNo(inspectionPlanInfo.getProjectNo());
       						 projectTask.setProjectNoId(inspectionPlanInfo.getProjectNoId());
       					     projectTask.setInitiator("system");
       					     projectTask.setTaskStatus("0");
       					     projectTask.setTaskType("1");
       					     projectTask.setAccepter(inspectionReservation.getAccepter());
       					     projectTask.setDescription("审核检验计划 步骤:编制检验计划-王工审核-质检补充-阳工审核");
       					     projectTask.setCreateTime(new Date());
       					     projectTask.setStartTime(new Date());
       					     projectTask.setFinishTime(getDateAfter(new Date(),5));
       					     projectTask.setMeetingNo(inspectionPlanInfo.getMeetingNo());
       					     projectTaskMapper.insertSelective(projectTask);  
                    		 //更新是否操作标志
          					 inspectionPlanInfo.setwAudit("1");
          					 inspectionPlanInfoMapper.updateByPrimaryKeySelective(inspectionPlanInfo); 
          					 wFlag=true;
                    	  }else{
                    	    projectErp=projectERPMapper.selectProjectErpByNo(inspectionPlanInfo.getProjectNo());
  	                    	if(projectErp!=null){
  	                    		if(StringUtils.isNotBlank(projectErp.getZhijian1()) && StringUtils.isBlank(projectErp.getZhijian2())){
  	                    			ProjectTask projectTask=new ProjectTask();
  		       						projectTask.setProjectNo(inspectionPlanInfo.getProjectNo());
  		       						projectTask.setProjectNoId(inspectionPlanInfo.getProjectNoId());
  		       					    projectTask.setInitiator("system");
  		       					    projectTask.setTaskStatus("0");
  		       					    projectTask.setTaskType("1");
  		       					    projectTask.setAccepter(projectErp.getZhijian1());
  		       					    projectTask.setDescription("审核检验计划 步骤:编制检验计划-王工审核-质检补充-阳工审核");
  		       					    projectTask.setCreateTime(new Date());
  		       					    projectTask.setStartTime(new Date());
  		       					    projectTask.setFinishTime(getDateAfter(new Date(),5));
  		       					    projectTask.setMeetingNo(inspectionPlanInfo.getMeetingNo());
  		       					    projectTaskMapper.insertSelective(projectTask);
  	                    		}
  	                    		
  	                    		if(StringUtils.isBlank(projectErp.getZhijian1()) && StringUtils.isNotBlank(projectErp.getZhijian2())){
  	                    			ProjectTask projectTask=new ProjectTask();
  		       						projectTask.setProjectNo(inspectionPlanInfo.getProjectNo());
  		       						projectTask.setProjectNoId(inspectionPlanInfo.getProjectNoId());
  		       					    projectTask.setInitiator("system");
  		       					    projectTask.setTaskStatus("0");
  		       					    projectTask.setTaskType("1");
  		       					    projectTask.setAccepter(projectErp.getZhijian2());
  		       					    projectTask.setDescription("审核检验计划 步骤:编制检验计划-王工审核-质检补充-阳工审核");
  		       					    projectTask.setCreateTime(new Date());
  		       					    projectTask.setStartTime(new Date());
  		       					    projectTask.setFinishTime(getDateAfter(new Date(),5));
  		       					    projectTask.setMeetingNo(inspectionPlanInfo.getMeetingNo());
  		       					    projectTaskMapper.insertSelective(projectTask);
  	                    		}
  	                    		
  	                    		if(StringUtils.isNotBlank(projectErp.getZhijian1()) && StringUtils.isNotBlank(projectErp.getZhijian2())){
  	                    			ProjectTask projectTask=new ProjectTask();
  		       						projectTask.setProjectNo(inspectionPlanInfo.getProjectNo());
  		       						projectTask.setProjectNoId(inspectionPlanInfo.getProjectNoId());
  		       					    projectTask.setInitiator("system");
  		       					    projectTask.setTaskStatus("0");
  		       					    projectTask.setTaskType("1");
  		       					    projectTask.setAccepter(projectErp.getZhijian1());
  		       					    projectTask.setDescription("审核检验计划 步骤:编制检验计划-王工审核-质检补充-阳工审核");
  		       					    projectTask.setCreateTime(new Date());
  		       					    projectTask.setStartTime(new Date());
  		       					    projectTask.setFinishTime(getDateAfter(new Date(),5));
  		       					    projectTask.setMeetingNo(inspectionPlanInfo.getMeetingNo());
  		       					    projectTaskMapper.insertSelective(projectTask); 
  	                    		}   		
  	                    	}
  	                    	 //更新是否操作标志
         					 inspectionPlanInfo.setwAudit("1");
         					 inspectionPlanInfoMapper.updateByPrimaryKeySelective(inspectionPlanInfo); 
         					 wFlag=true; 
                    	  }
                       }
				    }
					//质检操作完成了,yangggong审核
					if(pro.getTaskStatus().equals("1") && inspectionPlanInfo.getIsQuality().equals("0")){
                       if(inspectionPlanInfo.getAddPlan().equals("1") && inspectionPlanInfo.getwAudit().equals("1") && !wFlag){
                    	ProjectTask projectTask=new ProjectTask();
   						projectTask.setProjectNo(inspectionPlanInfo.getProjectNo());
   						projectTask.setProjectNoId(inspectionPlanInfo.getProjectNoId());
   					    projectTask.setInitiator("system");
   					    projectTask.setTaskStatus("0");
   					    projectTask.setTaskType("1");
   					    projectTask.setAccepter("yanggong");
   					    projectTask.setDescription("审核检验计划 步骤:编制检验计划-王工审核-质检补充-阳工审核");
   					    projectTask.setCreateTime(new Date());
   					    projectTask.setStartTime(new Date());
   					    projectTask.setFinishTime(getDateAfter(new Date(),5));
   					    projectTask.setMeetingNo(inspectionPlanInfo.getMeetingNo());
   					    projectTaskMapper.insertSelective(projectTask);
   					    
   					    //更新是否操作标志
   					    inspectionPlanInfo.setIsQuality("1");
   					    inspectionPlanInfoMapper.updateByPrimaryKeySelective(inspectionPlanInfo); 
   					    qFlag=true;
                       }
					}

                    if(pro.getTaskStatus().equals("1") &&inspectionPlanInfo.getyAudit().equals("0") ){
                    	 if(inspectionPlanInfo.getAddPlan().equals("1") && inspectionPlanInfo.getwAudit().equals("1")
                    			 && inspectionPlanInfo.getIsQuality().equals("1") && !qFlag){
                    		    //更新是否操作标志
        					  inspectionPlanInfo.setyAudit("1");
        					  inspectionPlanInfoMapper.updateByPrimaryKeySelective(inspectionPlanInfo); 
                    	 }
                    }
				}
				projectTaskMapper.updateByPrimaryKeySelective(pro);
			}else{
				
				//如果是大货延期任务
				//当同意时将更新大货交期时间
				if("3".equals(pro.getTaskType())){
					if("1".equals(pro.getTaskStatus())){
						String delayIds = pro.getDelayList();
						List<String> arryList = JSONArray.parseArray(delayIds, String.class);
						List<Delay> delayList = delayMapper.selectByIds(arryList);
						Date deliveryDate = null;   //新交期
						//查询大货分批交期
						List<ProjectSchedule> projectSchedules = projectScheduleMapper.selectByProjectNo(pro.getProjectNo());
						for (Delay delay : delayList) {
							delay.setIsAgree(1);
							delay.setAgreePerson(pro.getOperator());
							delay.setAgreeTime(new Date());
							delay.setReson(pro.getOperateExplain());
							//跟单同意延期
							//大货交期修改
							//匹配批次，将延期时间更新到大货交期时间
							for (ProjectSchedule projectSchedule : projectSchedules) {
								if(projectSchedule.getNum() == delay.getNum()){
									projectSchedule.setPredictDate(delay.getDelayDate());
									projectSchedule.setUpdateTime(new Date());
									deliveryDate = delay.getDelayDate();
								}
							}
						}
						
						projectScheduleMapper.updateBatch(projectSchedules);
						delayMapper.updateBatch(delayList);
						
						Project project =  projectMapper.selectProjectByProjectNo(pro.getProjectNo());
						if(deliveryDate != null && project != null){
							project.setDeliveryDate(deliveryDate);
							if(projectErp != null){
								projectErp.setCompletionTime(deliveryDate);
								projectERPMapper.updateByPrimaryKeySelective(projectErp);
							}
							projectMapper.updateByPrimaryKeySelective(project);
						}
                       
						
						
					}else if("3".equals(pro.getTaskStatus())){
						
						//不同意交期修改
						String delayIds = pro.getDelayList();
						List<String> arryList = JSONArray.parseArray(delayIds, String.class);
						List<Delay> delayList = delayMapper.selectByIds(arryList);
						for (Delay delay : delayList) {
							delay.setIsAgree(0);
							delay.setAgreePerson(pro.getOperator());
							delay.setAgreeTime(new Date());		
							delay.setReson(pro.getOperateExplain());
						}
						delayMapper.updateBatch(delayList);
					}
				}
				
				projectTaskMapper.updateByPrimaryKeySelective(pro);
			}
				
		}
	}

	public void saveData(List<RoleBindDetail> list, RoleBindDetail rbd) {

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				RoleBindDetail rd = list.get(i);
				ProjectTask pt = new ProjectTask();
				pt.setInitiator("system");
				pt.setAccepter(rd.getUserName());
				pt.setStartTime(new Date());
				pt.setCreateTime(new Date());
				pt.setDescription(rbd.getTaskTitle());
				pt.setTaskStatus("0");
				pt.setProjectNo(rbd.getProjectNo());
				pt.setTaskId(rbd.getTaskId());
				pt.setIndexNum(rbd.getIndexNum());

				Calendar ca = Calendar.getInstance();
				ca.add(Calendar.DATE, 2);
				int day = ca.get(Calendar.DAY_OF_WEEK);
				if (day == 7 || day == 1) {
					ca.add(Calendar.DATE, 2);
				}
				Date finishTime = ca.getTime();
				pt.setFinishTime(finishTime);
				projectTaskMapper.insertSelective(pt);

				JSONObject object = JSONObject.fromObject(pt);
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put("projectTask", object.toString());
				Thread td = new HttpThread(parameters);
				td.start();
			}

		}

	}
	
	
	public void saveDetail(List<String> accepters, RoleBindDetail rbd) {

		if (accepters.size() > 0) {
			for (int i = 0; i < accepters.size(); i++) {

				ProjectTask pt = new ProjectTask();
				pt.setInitiator("system");
				pt.setAccepter(accepters.get(i));
				pt.setStartTime(new Date());
				pt.setCreateTime(new Date());
				pt.setDescription(rbd.getTaskTitle());
				pt.setTaskStatus("0");
				pt.setProjectNo(rbd.getProjectNo());
				pt.setTaskId(rbd.getTaskId());
				pt.setIndexNum(rbd.getIndexNum());
				
				Calendar ca = Calendar.getInstance();
				
				ca.add(Calendar.DATE, 2);
				
				int day = ca.get(Calendar.DAY_OF_WEEK);
				
				if(day==7||day==1){
					ca.add(Calendar.DATE, 2);
				}
				Date finishTime = ca.getTime();
			
				pt.setFinishTime(finishTime);
				projectTaskMapper.insertSelective(pt);
				
				JSONObject object = JSONObject.fromObject(pt);
				Map<String, String> parameters=new HashMap<String, String>();
				parameters.put("projectTask", object.toString());
				//url and json
				Thread td = new HttpThread(parameters);
				td.start();
			}

		}

	}

	@Override
	public List<ProjectTask> selectByQualityId(Integer qualityId) {

		return projectTaskMapper.selectByQualityId(qualityId);
	}

	@Override
	public int deleteByQualityId(Integer qualityId) {
		return projectTaskMapper.deleteByQualityId(qualityId);
	}
	
	public static Date getDateAfter(Date d,int day){  
	   Calendar now =Calendar.getInstance();  
	   now.setTime(d);  
	   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
	   return now.getTime();  
	}

	@Transactional
	@Override
	public int insertBatch(List<ProjectTask> items) throws ParseException {
		int count = projectTaskMapper.insertBatch(items);
		for (ProjectTask projectTask : items) {
			User user = userDao.findUserByName(projectTask.getAccepter());
			if(user!=null){
				projectTask.setDingTalkId(user.getDingTalkId());
				RpcSendNoticeToKuai.sendRequest(projectTask);
			}
			
			//如果是图纸变更任务，需要任务提醒
			if("5".equals(projectTask.getTaskType())){
				//发送人需要发邮件通知详情给整个项目组
				ProjectTask task = new ProjectTask();
				task.setProjectNo(projectTask.getProjectNo());
				task.setInitiator("system");
				task.setAccepter(projectTask.getInitiator());
				task.setDescription(projectTask.getProjectNo()+"图纸变更任务，需要发邮件通知详情给整个项目组");
				task.setFinishTime(DateFormat.addDays(new Date(), 3));
				task.setTaskStatus("0");
				task.setTaskType("0");
				task.setTaskUrl(null);
            	task.setStartTime(new Date());
            	task.setCreateTime(new Date());
            	projectTaskMapper.insertSelective(task);
            	User sendUser = userDao.findUserByName(projectTask.getInitiator());
            	//钉钉任务提醒
            	if(sendUser!=null){
    				projectTask.setDingTalkId(sendUser.getDingTalkId());
    				RpcSendNoticeToKuai.sendRequest(task);
    			}
            	
            	//接收人需要发送变更图纸给整个项目组
            	ProjectTask task1 = new ProjectTask();
            	task1.setProjectNo(projectTask.getProjectNo());
            	task1.setInitiator("system");
            	task1.setAccepter(projectTask.getAccepter());
            	task1.setDescription(projectTask.getProjectNo()+"图纸变更任务，完成后请发送变更后的图纸给整个项目组");
            	task1.setFinishTime(DateFormat.addDays(new Date(), 3));
            	task1.setTaskStatus("0");
            	task1.setTaskType("0");
            	task1.setTaskUrl(null);
            	task1.setStartTime(new Date());
            	task1.setCreateTime(new Date());
            	projectTaskMapper.insertSelective(task1);
            	//钉钉任务提醒
            	if(user!=null){
    				projectTask.setDingTalkId(user.getDingTalkId());
    				RpcSendNoticeToKuai.sendRequest(task1);
    			}
			}
		}
		return count;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return projectTaskMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Map<String, Long> selectCountByStatus(String projectNo) {
		return projectTaskMapper.selectCountByStatus(projectNo);
	}

	@Override
	public List<ProjectTask> selectByProjectNo(String projectNo) {
		return projectTaskMapper.selectByProjectNo(projectNo);
	}

	@Transactional
	@Override
	public void updateProjectTask(ProjectTask projectTask,List<FactoryScore> scoreList) {
		projectTaskMapper.updateByPrimaryKeySelective(projectTask);
		factoryScoreMapper.insertBatch(scoreList);
	}

	@Override
	public ProjectTask getDrawingAlteration(String projectNo) {
		
		return projectTaskMapper.getDrawingAlteration(projectNo);
	}

	@Override
	public List<ProjectTask> statisticsProjectTaskFinish1(ProjectTask searchTask) {
		
		return projectTaskMapper.statisticsProjectTaskFinish1(searchTask);
	}

	@Override
	public List<ProjectTask> statisticsProjectTaskOnTime1(String userName,
			String sign) {
		
		return projectTaskMapper.statisticsProjectTaskOnTime1(userName,sign);
	}

	@Override
	public List<ProjectTask> statisticsProjectTaskAllFinish1(String userName,
			String sign) {
		
		return projectTaskMapper.statisticsProjectTaskAllFinish1(userName,sign);
	}

	@Override
	public List<ProjectTask> selectMeetingRecordTaskNoFinish1(String userName) {
		
		return projectTaskMapper.selectMeetingRecordTaskNoFinish1(userName);
	}

	@Override
	public List<ProjectTask> statisticsProjectTaskNoFinish1(
			ProjectTask searchTask) {
		
		return projectTaskMapper.statisticsProjectTaskNoFinish1(searchTask);
	}

	@Override
	public void updateByPrimaryKeySelective(ProjectTask projectTask) {
		projectTaskMapper.updateByPrimaryKeySelective(projectTask);
		
	}

	@Override
	public void updateStatus(ProjectTask task) {
		
		projectTaskMapper.updateStatus(task);
	}

	@Override
	public Integer getProjectTaskCount() {
		
		return projectTaskMapper.getProjectTaskCount();
	}

	@Override
	public int getInspectionTaskNotCompleted(ProjectTask task) {
		
		return projectTaskMapper.getInspectionTaskNotCompleted(task);
	}

	@Override
	public List<ProjectTask> statisticsProjectTaskNoFinish2(
			ProjectTask searchTask) {
		
		return projectTaskMapper.statisticsProjectTaskNoFinish2(searchTask);
	}

	@Override
	public List<ProjectTask> statisticsProjectTaskFinish2(ProjectTask searchTask) {
		
		return projectTaskMapper.statisticsProjectTaskFinish2(searchTask);
	}

	@Override
	public JSONArray statisticsProjectTaskOnTime2(ProjectTask projectTask) {
		
		return projectTaskMapper.statisticsProjectTaskOnTime2(projectTask);
	}

	@Override
	public JSONArray statisticsProjectTaskAllFinish2(ProjectTask projectTask) {
		
		return projectTaskMapper.statisticsProjectTaskAllFinish2(projectTask);
	}

	@Override
	public List<ProjectTask> statisticsProjectTaskOnTime3(
			ProjectTask projectTask) {
		
		return projectTaskMapper.statisticsProjectTaskOnTime3(projectTask);
	}

	@Override
	public JSONArray selectMeetingRecordTaskNoFinish2(ProjectTask existTask) {
		
		return projectTaskMapper.selectMeetingRecordTaskNoFinish2(existTask);
	}

	@Override
	public ProjectTask getByProjectNo(String projectNo) {
		
		return projectTaskMapper.getByProjectNo(projectNo);
	}

	
}
