	package com.cn.hnust.job;

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
import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.component.RpcHelper;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.RoleBindDetail;
import com.cn.hnust.pojo.TaskFlow;
import com.cn.hnust.service.IMeetingRecordService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.IRoleBindListService;
import com.cn.hnust.service.ITaskFlowService;
import com.cn.hnust.thread.HttpThread;

/*
 * 绘图、技术分析、包装分析(推送的是绑定角色的)
 */
public class AssignmenTask {

	private static boolean assignmen_running = false;

	private final static Logger log = Logger.getLogger(AssignmenTask.class);

	@Resource
	private ITaskFlowService taskFlowService;

	@Resource
	private IProjectService projectService;

	@Resource
	private IRoleBindListService roleBindListService;

	@Resource
	private IProjectTaskService projectTaskService;

	@Resource
	private IMeetingRecordService meetingService;


	public synchronized boolean isRunning() {
		if (assignmen_running) {
			return true;
		} else {
			assignmen_running = false;
		}
		return assignmen_running;
	}

	public void execute() {
		if (isRunning()) {
			return;
		}
		log.info("--->assignmenTask go...");
		startJob();
	}

	public void startJob() {
		try {
			assignmen_running = true;
			
			/*
			 * 项目开始
			 */
			checkDocumentaryFirst();
			/*
			 * 跟单项目
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
			 * 1.销售发出质量投诉时，触发 给采购 和 质检的解释问题和解决问题的任务
			 */

		} catch (Exception e) {
			log.error("--->assignmenTask error...");
			e.printStackTrace();
		} finally {

			log.error("--->assignmenTask end...");
			assignmen_running = false;
		}
	}

	
	/*
	 * 创建新项目
	 */
	public void checkDocumentaryFirst() throws Exception {
		Set<String> proSet = null;
		try {

			List<RoleBindDetail> list = roleBindListService.selectConditionFirst();
			proSet = saveData(list);

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (proSet != null && !proSet.isEmpty()) {
				projectService.updateFlogByProjectNoFirst(proSet);
			}

		}

	}

	
	
	
	/*
	 * 跟单
	 */
	public void checkDocumentary() throws Exception {
		Set<String> proSet = null;
		try {

			List<RoleBindDetail> list = roleBindListService.selectCondition(
					true, true);
			proSet = saveData(list);

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (proSet != null && !proSet.isEmpty()) {
				projectService.updateFlogByProjectNo(proSet);
			}

		}

	}

	public void checkStartMeeting() throws Exception {
		Set<String> proSet = null;
		String meetingName = "项目启动会";
		try {

			List<RoleBindDetail> list = roleBindListService
					.selectMeetingStatus(3, meetingName);

			proSet = saveData(list);

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (!proSet.isEmpty()) {
				meetingService.updateFlagByProjectNo(proSet, meetingName);
			}

		}

	}

	public void checkQualityMeeting() throws Exception {
		Set<String> proSet = null;
		String meetingName = "样品验货会";
		try {
			List<RoleBindDetail> list = roleBindListService
					.selectMeetingStatus(2, meetingName);
			proSet = saveData(list);

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (!proSet.isEmpty()) {
				meetingService.updateFlagByProjectNo(proSet, meetingName);
			}

		}

	}

	public Set<String> saveData(List<RoleBindDetail> list) {
		Set<String> proSet = new HashSet<String>();

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				RoleBindDetail rd = list.get(i);
				proSet.add(rd.getProjectNo());
				ProjectTask pt = new ProjectTask();
				pt.setInitiator("system");
				pt.setAccepter(rd.getUserName());
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

				if (day == 7 || day == 1) {
					ca.add(Calendar.DATE, 2);
				}

				Date finishTime = ca.getTime();
				pt.setFinishTime(finishTime);
				projectTaskService.insertSelective(pt);

				JSONObject object = JSONObject.fromObject(pt);
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put("projectTask", object.toString());

				Thread td = new HttpThread(parameters);
				td.start();

			}

		}

		return proSet;

	}

}
