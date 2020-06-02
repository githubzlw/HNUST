package com.cn.hnust.job;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.service.IProjectReportService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.util.DateFormat;

@Component
public class AddMeetingTask {
	
	
	private final static Logger log = Logger.getLogger(AddMeetingTask.class);
	
	@Resource
	private IProjectService projectService;
	
	@Resource
	private IProjectReportService projectReportService;
	
	@Resource
	private IProjectTaskService projectTaskService;

   // @Scheduled(cron = "0 0 9 * * ?")  
	public void execute() {
		log.info("--->addMeetingTask go..."+DateFormat.format());
		addImportantMeetingTask();
		addMeetingTask();
		log.info("--->addMeetingTask end..."+DateFormat.format());
	}	
	
	
		/**
		 * 定时发送任务 （AB级项目每15天需要开重要项目评审会 ）
		 * @Title syncProjectDateStatus 
		 * @Description 
		 * @throws Exception
		 * @return void
		 * @throws ParseException 
		 */
		public void addImportantMeetingTask(){
			try {
				List<Project> projectList = projectService.selectByImportantMeetingTask();	
				//需要项目评审会
				List<ProjectTask> projectTaks = new ArrayList<ProjectTask>();
				for (Project project : projectList) {
					ProjectTask task = new ProjectTask();
					if(StringUtils.isNotBlank(project.getSellName())){
						task.setProjectNo(project.getProjectNo());
						task.setInitiator("system");			
						task.setDescription("A/B级项目"+project.getProjectNo()+"请每15天与领导开重要项目评审会并上传会议记录到系统");
						task.setFinishTime(DateFormat.addDays(new Date(), 7));
						task.setTaskStatus("0");
						task.setTaskType("10");
						task.setTaskUrl(null);
						task.setStartTime(new Date());
						task.setCreateTime(new Date());
						task.setAccepter(project.getSellName());
						projectTaks.add(task);
					}

					if(StringUtils.isNotBlank(project.getPurchaseName()) && !project.getPurchaseName().equals(project.getSellName())){
						ProjectTask task1 = new ProjectTask();
						task1.setProjectNo(project.getProjectNo());
						task1.setInitiator("system");			
						task1.setDescription("A/B级项目"+project.getProjectNo()+"请每15天与领导开重要项目评审会并上传会议记录到系统");
						task1.setFinishTime(DateFormat.addDays(new Date(), 7));
						task1.setTaskStatus("0");
						task1.setTaskType("10");
						task1.setTaskUrl(null);
						task1.setStartTime(new Date());
						task1.setCreateTime(new Date());
						task1.setAccepter(project.getPurchaseName());
						projectTaks.add(task1);
					}
					
				}
				if(projectTaks!=null && projectTaks.size()>0){
					projectTaskService.insertBatch(projectTaks);
				}
			} catch (ParseException e) {
				e.printStackTrace();
				log.error("添加15天重要项目会议失败addImportantMeetingTask", e);
			}			
		}

		
		
		/**
		 * 项目组织一次 【项目评审会】任务
		 * @Title addMeetingTask 
		 * @Description
		 * @throws ParseException
		 * @return void
		 */
		public void addMeetingTask(){
			try {
				List<Project> projectList = projectService.selectByMeetingTask();	
				//需要项目评审会
				List<ProjectTask> projectTaks = new ArrayList<ProjectTask>();
				for (Project project : projectList) {
					ProjectTask task = new ProjectTask();
					task.setProjectNo(project.getProjectNo());
					task.setInitiator("system");			
					task.setDescription("请对"+project.getProjectNo()+"项目组织一次 【项目评审会】所需准备资料：1.项目生产计划 2.提供开工视频 3.粗线条的工艺流程并需要上传会议记录。");
					task.setFinishTime(DateFormat.addDays(new Date(), 7));
					task.setTaskStatus("0");
					task.setTaskType("8");
					task.setTaskUrl(null);
					task.setStartTime(new Date());
					task.setCreateTime(new Date());
					task.setAccepter(project.getSellName());
					projectTaks.add(task);
				}
				projectTaskService.insertBatch(projectTaks);
			} catch (ParseException e) {
				e.printStackTrace();
				log.error("添加项目评审会失败addMeetingTask", e);
			}
		}
 }

