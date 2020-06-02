package com.cn.hnust.service.impl.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectReport;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.service.IProjectReportService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DbShippingPoolUtil;
import com.cn.hnust.util.DbTaskPoolUtil;

@Component
public class ProjectDateTask {
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IProjectReportService projectReportService;
	
	@Autowired
	private IProjectTaskService projectTaskService;
	
	
	//@Scheduled(cron = "0 0/10 8,23 * * ?")
	@Scheduled(cron="0 01 07 * * ?")  
	public void work() throws Exception{
       syncProjectDateStatus();
	}
//	@Scheduled(cron = "0 0 0 * * ?")
//	@Scheduled(cron="0/30 * *  * * ? ")
//	public void addTask() throws Exception{
//		addMeetingTask();
//	}
//	@Scheduled(cron = "0 0 11  * * ? ")
//	@Scheduled(cron="0/30 * *  * * ? ")
//	public void addImportantTask() throws Exception{
//		addImportantMeetingTask();
//	}
	@Scheduled(cron = "0 0 1 * * ?")
//	@Scheduled(cron="0 0/60 * *  * * ? ")
	public void syncProjectTask(){
		syncProject();
	}
    //定时任务定时更新数据库排序字段优先级和状态
	@Transactional
	public void syncProjectDate(List<Project> projectList) throws Exception{
			//List<Project> projectList=projectService.selectAllProject();// 
			//添加排序字段,最终根据排序字段进行排序
			int sortField=0;
			Date today = new Date();
				for (Project sortProject : projectList) {
//					int projectStatus=sortProject.getProjectStatus();
					if (sortProject.getFinish() == 0) {//大货没有完结
						if (sortProject.getIsPause() == null|| "0".equals(sortProject.getIsPause())) {
							List<ProjectReport> pr = projectReportService.selectProjectReport(sortProject.getProjectNo());
							 /* and (finish =0 and sample_finish =0 and (is_pause = 0 or is_pause is null ) 
									  and weekReportNum=0 and delivery_date is null and sample_scheduled_date is null) */
							if(sortProject.getSampleFinish()==0 && pr.size()==0 && 
									sortProject.getDeliveryDate() == null && sortProject.getSampleScheduledDate()==null){
								sortField = 1;  
//								projectStatus=0;
							}
							//正在进行的项目判断
							/*<!-- 1. 样品交期，大货交期 (大货为空 样品交期不为为空样品 大于当前时间,样品为空,大货不为空 大货交期大于当前时间 ,样品大货不为空都大于)
							2.样品交期完结(大货交期为空 或者大货交期大于 当前时间) 3.新立项项目更新了周报，在正常项目里面 -->*/
							/* AND ( (finish =0 and sample_finish =0 and (is_pause = 0 or is_pause is null ) and weekReportNum &gt; 0 and delivery_date is null and sample_scheduled_date is null)
								     or(finish =0 and sample_finish =0 and (is_pause = 0 or is_pause is null ) and ((delivery_date is NULL and sample_scheduled_date!= '' and sample_scheduled_date &gt; now())
								     or(sample_scheduled_date is NULL and delivery_date!= '' and delivery_date &gt; now())
								     or(sample_scheduled_date != '' and delivery_date != '' and delivery_date &gt; now()))) 
								     or(sample_finish=1 and finish =0 and ((delivery_date is null)or(delivery_date!='' and delivery_date &gt; now()))
								  ))*/
							if((sortProject.getFinish()==0 && sortProject.getSampleFinish() ==0 &&   pr.size()> 0 && sortProject.getDeliveryDate()==null && sortProject.getSampleScheduledDate()== null)
								     ||(sortProject.getFinish()==0 && sortProject.getSampleFinish() ==0  && ((sortProject.getDeliveryDate()==null &&  sortProject.getSampleScheduledDate()!=null && sortProject.getSampleScheduledDate().getTime()+7*24*60*60*1000>today.getTime())
								     ||(sortProject.getSampleScheduledDate()==null && sortProject.getDeliveryDate()!=null && sortProject.getDeliveryDate().getTime()+7*24*60*60*1000>today.getTime())
								     ||(sortProject.getSampleScheduledDate()!=null && sortProject.getDeliveryDate()!=null && sortProject.getDeliveryDate().getTime()+7*24*60*60*1000>today.getTime()))) 
								     ||(sortProject.getSampleFinish()==1 && sortProject.getFinish()==0 && (/*(sortProject.getDeliveryDate()==null)||*/(sortProject.getDeliveryDate()!=null && sortProject.getDeliveryDate().getTime()+7*24*60*60*1000>today.getTime()))
								  )){
								sortField = 2;
//								projectStatus=1;
							}
							//样品完结项目(点击样品完结,没有大货交期sortField=2 projectStatus=6 )
							if(sortProject.getSampleFinish()==1 && sortProject.getFinish()==0 && sortProject.getDeliveryDate()==null){
								sortField = 2;
								//projectStatus=6;
							}
							
							 /*<!-- 延期项目(样品延期,大货延期) DATE_ADD(delivery_date,INTERVAL 7 DAY) < now()-->
						      and ((finish =0 and (is_pause = 0 or is_pause is null ) and delivery_date!= '' and DATE_ADD(delivery_date,INTERVAL 7 DAY) &lt; now())
						         or(delivery_date is NULL AND sample_finish =0 and (is_pause = 0 or is_pause is null ) and sample_scheduled_date!= '' and DATE_ADD(sample_scheduled_date,INTERVAL 7 DAY) &lt; now())
						         or(sample_finish=1 and finish=0 and (is_pause = 0 or is_pause is null )and delivery_date!= '' and DATE_ADD(delivery_date,INTERVAL 7 DAY) &lt; now()))
						    */
							//延期项目
							if((sortProject.getFinish()==0 &&  sortProject.getDeliveryDate()!=null && sortProject.getDeliveryDate().getTime()+7*24*60*60*1000<today.getTime())
							         || (sortProject.getDeliveryDate()==null && sortProject.getSampleFinish() ==0  &&  sortProject.getSampleScheduledDate()!=null && sortProject.getSampleScheduledDate().getTime()+7*24*60*60*1000< today.getTime())
							         || (sortProject.getSampleFinish()==1 && sortProject.getFinish()==0  &&  sortProject.getDeliveryDate()!=null && sortProject.getDeliveryDate().getTime()+7*24*60*60*1000< today.getTime())){
									if(pr!=null &&pr.size()>0){//延期更新了周报
				                   	 sortField = 4; 
				                    }else{
				                   	 sortField = 3; 
				                    } 
				                  //projectStatus=3;
							}
					 }else if(sortProject.getIsPause()!=null && !sortProject.getIsPause().equals("0")){
						 if((sortProject.getFinish()==0 && sortProject.getIsPause().equals("1")) ||
								 (sortProject.getSampleFinish()==0 && sortProject.getIsPause().equals("1")) ||
								 (sortProject.getSampleFinish()==1 && sortProject.getFinish() ==0 && sortProject.getIsPause().equals("1"))){
							 sortField = 5; 
							//projectStatus=4;
							 
						 }
						 if((sortProject.getFinish()==0 && sortProject.getIsPause().equals("2")) ||
								 (sortProject.getSampleFinish()==0 && sortProject.getIsPause().equals("2")) ||
								 (sortProject.getSampleFinish()==1 && sortProject.getFinish() ==0 && sortProject.getIsPause().equals("2"))){
							 sortField = 6; 
							 //projectStatus=5;
							 
						 }
					  }
					} else if (sortProject.getFinish() == 1) {
						if(sortProject.getFinishTime()!=null){
							Calendar calendar1 = Calendar.getInstance();
							Calendar calendar2 = Calendar.getInstance();
							int dayOfWeek=calendar1.get(Calendar.DAY_OF_WEEK)-1;
							int offset1=1-dayOfWeek;
							int offset2=7-dayOfWeek;
							calendar1.add(Calendar.DATE, offset1+7);
							calendar2.add(Calendar.DATE, offset2-5);
							if(DateFormat.formatToDate(sortProject.getFinishTime()).getTime()>=DateFormat.formatToDate(calendar2.getTime()).getTime() && 
									sortProject.getFinishTime().getTime()<=DateFormat.formatToDate(calendar1.getTime()).getTime()){
								sortField = 7; 
								//projectStatus=7;//上周完成项目
							}else{
								sortField = 7; 
//								projectStatus=2;
							}
						}else{
							sortField = 7; 
//							projectStatus=2;
						}
					}
					sortProject.setSortField(sortField);
//					sortProject.setProjectStatus(projectStatus);
					projectService.updateProjectDataSortField(sortProject);	
				}
		
		} 
	
	
	    //定时任务定时更新数据库排序字段优先级和状态
		@Transactional
		public void syncProjectDateStatus() throws Exception{
				List<Project> projectList=projectService.selectAllProject();// 
				//添加排序字段,最终根据排序字段进行排序
				int sortField=0;
				Date today = new Date();
					for (Project sortProject : projectList) {						
//						int projectStatus=sortProject.getProjectStatus();
						if (sortProject.getFinish() == 0) {//大货没有完结
							if (sortProject.getIsPause() == null|| "0".equals(sortProject.getIsPause())) {
								List<ProjectReport> pr = projectReportService.selectProjectReport(sortProject.getProjectNo());
								if(sortProject.getSampleFinish()==0 && pr.size()==0 && 
										sortProject.getDeliveryDate() == null && sortProject.getSampleScheduledDate()==null){
									sortField = 1;  
//									projectStatus=0;
								}
								if((sortProject.getFinish()==0 && sortProject.getSampleFinish() ==0 &&   pr.size()> 0 && sortProject.getDeliveryDate()==null && sortProject.getSampleScheduledDate()== null)
									     ||(sortProject.getFinish()==0 && sortProject.getSampleFinish() ==0  && ((sortProject.getDeliveryDate()==null &&  sortProject.getSampleScheduledDate()!=null && sortProject.getSampleScheduledDate().getTime()+7*24*60*60*1000>today.getTime())
									     ||(sortProject.getSampleScheduledDate()==null && sortProject.getDeliveryDate()!=null && sortProject.getDeliveryDate().getTime()+7*24*60*60*1000>today.getTime())
									     ||(sortProject.getSampleScheduledDate()!=null && sortProject.getDeliveryDate()!=null && sortProject.getDeliveryDate().getTime()+7*24*60*60*1000>today.getTime()))) 
									     ||(sortProject.getSampleFinish()==1 && sortProject.getFinish()==0 && (/*(sortProject.getDeliveryDate()==null)||*/(sortProject.getDeliveryDate()!=null && sortProject.getDeliveryDate().getTime()+7*24*60*60*1000>today.getTime()))
									  )){
									sortField = 2;
//									projectStatus=1;
								}
								//样品完结项目(点击样品完结,没有大货交期sortField=2 projectStatus=6 )
								if(sortProject.getSampleFinish()==1 && sortProject.getFinish()==0 && sortProject.getDeliveryDate()==null){
									sortField = 2;
									//projectStatus=6;
								}
								//延期项目
								if((sortProject.getFinish()==0 &&  sortProject.getDeliveryDate()!=null && sortProject.getDeliveryDate().getTime()+7*24*60*60*1000<today.getTime())
								         || (sortProject.getDeliveryDate()==null && sortProject.getSampleFinish() ==0  &&  sortProject.getSampleScheduledDate()!=null && sortProject.getSampleScheduledDate().getTime()+7*24*60*60*1000< today.getTime())
								         || (sortProject.getSampleFinish()==1 && sortProject.getFinish()==0  &&  sortProject.getDeliveryDate()!=null && sortProject.getDeliveryDate().getTime()+7*24*60*60*1000< today.getTime())){
										if(pr!=null &&pr.size()>0){//延期更新了周报
					                   	 sortField = 4; 
					                    }else{
					                   	 sortField = 3; 
					                    } 
					                   // projectStatus=3;
								}
						 }else if(sortProject.getIsPause()!=null && !sortProject.getIsPause().equals("0")){
							 if((sortProject.getFinish()==0 && sortProject.getIsPause().equals("1")) ||
									 (sortProject.getSampleFinish()==0 && sortProject.getIsPause().equals("1")) ||
									 (sortProject.getSampleFinish()==1 && sortProject.getFinish() ==0 && sortProject.getIsPause().equals("1"))){
								 sortField = 5; 
								// projectStatus=4;
								 
							 }
							 if((sortProject.getFinish()==0 && sortProject.getIsPause().equals("2")) ||
									 (sortProject.getSampleFinish()==0 && sortProject.getIsPause().equals("2")) ||
									 (sortProject.getSampleFinish()==1 && sortProject.getFinish() ==0 && sortProject.getIsPause().equals("2"))){
								 sortField = 6; 
								// projectStatus=5;
								 
							 }
						  }
						} else if (sortProject.getFinish() == 1) {
							if(sortProject.getFinishTime()!=null){
								Calendar calendar1 = Calendar.getInstance();
								Calendar calendar2 = Calendar.getInstance();
								int dayOfWeek=calendar1.get(Calendar.DAY_OF_WEEK)-1;
								int offset1=1-dayOfWeek;
								int offset2=7-dayOfWeek;
								calendar1.add(Calendar.DATE, offset1+7);
								calendar2.add(Calendar.DATE, offset2-5);
								if(DateFormat.formatToDate(sortProject.getFinishTime()).getTime()>=DateFormat.formatToDate(calendar2.getTime()).getTime() && 
										sortProject.getFinishTime().getTime()<=DateFormat.formatToDate(calendar1.getTime()).getTime()){
									sortField = 7; 
								//	projectStatus=7;//上周完成项目
								}else{
									sortField = 7; 
//									projectStatus=2;
								}
							}else{
								sortField = 7; 
//								projectStatus=2;
							}
						}
						sortProject.setSortField(sortField);
//						sortProject.setProjectStatus(projectStatus);
						projectService.updateProjectDataSortField(sortProject);	
					}
			
			} 
		
		
		/**
		 * 定时发送任务 （AB级项目合同15天后增加项目评审会任务。）
		 * @Title syncProjectDateStatus 
		 * @Description 
		 * @throws Exception
		 * @return void
		 * @throws ParseException 
		 */
		@Transactional
		public void addMeetingTask() throws ParseException{
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
		}
		
		
		
		
		
		/**
		 * 定时同步项目报关时间
		 * @Title syncProject 
		 * @Description
		 * @return void
		 */
		public void syncProject(){
			
			Connection conn = DbShippingPoolUtil.getConnection();
			String sql = "select max(saildate)as saildate,max(purno)as purno,max(c.is_extra_invoice)as is_extra_invoice from products  p LEFT JOIN contract c on p.id = c.proId where  saildate != '' and saildate > DATEADD(DAY,-7,GETDATE())    GROUP BY purno";			
 		
	 		try{
	 			Statement createStatement = conn.createStatement();
	 			ResultSet res = createStatement.executeQuery(sql);
	 			while (res.next()) {
	 				String projectNo = null;
	 				String purno = res.getString("purno");
	 				int isExtraInvoice = res.getInt("is_extra_invoice");
	 				if(StringUtils.isNotBlank(purno)){
						purno = purno.replaceAll("[a-zA-Z]","");
						purno = purno.replace("合", "");
						projectNo = "SHS"+ purno;
					}
	 				String sailDate = res.getString("saildate");
	 				Project project = projectService.selectProjctDetails(projectNo);
	 				if(project!=null && isExtraInvoice!=1){
	 					project.setExportDate(sailDate);
	 					projectService.updateProjectInfo(project);
	 				}
	 				  System.out.println("项目号"+projectNo+"同步中");
	 			}
               System.out.println("syncProject已结束");
	 			
	 		} catch (SQLException e) {
	 			e.printStackTrace();
	 		}	finally {
	 			DbTaskPoolUtil.returnConnection(conn);
	 		} 
			
		}
		
 }
