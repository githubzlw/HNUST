package com.cn.hnust.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.component.RpcSendNoticeToKuai;
import com.cn.hnust.dao.IUserDao;
import com.cn.hnust.dao.ProjectMapper;
import com.cn.hnust.dao.ProjectTaskMapper;
import com.cn.hnust.dao.QualityAnalysisMapper;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.QualityAnalysis;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.QualityAnalysisService;
import com.cn.hnust.util.DateUtil;

@Service
public class QualityAnalysisServiceImpl implements QualityAnalysisService {

	@Autowired
	private QualityAnalysisMapper qualityAnalysisMapper;
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private ProjectTaskMapper projectTaskMapper;
	@Autowired
	private IUserDao userDao;
	private static final int QUALITY = 1;
	private static final int TECHNICIAN = 2;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {		
		return qualityAnalysisMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(QualityAnalysis record) {
		return qualityAnalysisMapper.insert(record);
	}

	@Override
	public int insertSelective(QualityAnalysis record) {
		return qualityAnalysisMapper.insertSelective(record);
	}

	@Override
	public QualityAnalysis selectByPrimaryKey(Integer id) {
		return qualityAnalysisMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(QualityAnalysis record) {
		return qualityAnalysisMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(QualityAnalysis record) {
		return qualityAnalysisMapper.updateByPrimaryKey(record);
	}

	@Override
	public QualityAnalysis selectByProjectNo(String projectNo) {
		return qualityAnalysisMapper.selectByProjectNo(projectNo);
	}

	@Override
	public List<String> selectByIssue(String issue) {
		return qualityAnalysisMapper.selectByIssue(issue);
	}

	@Override
	public List<Map<String, Object>> selectMapByIssue(String issue,String inputKey,Integer type,Integer pageNumber,Integer pageSize) {
		return qualityAnalysisMapper.selectMapByIssue(issue,inputKey,type,pageNumber,pageSize);
	}

	
    /**
     * 根据类型判断当前是质量分析表、还是技术分析表
     * 1：质量分析表  2：技术分析表
     * @Title insertSelective 
     * @Description 
     * @param record
     * @param type
     * @return
     * @return int
     */
	@Transactional
	@Override
	public int insertSelective(QualityAnalysis record, String sender,int type) {
		Project project = projectMapper.selectProjctDetails(record.getProjectNo());		
		if(type == QUALITY){				
			if(project.getPurchaseName()!=null){
				ProjectTask projectTask = new ProjectTask();
				projectTask.setAccepter(project.getPurchaseName());
				projectTask.setProjectNo(record.getProjectNo());
            	projectTask.setInitiator(sender);
            	projectTask.setDescription("该项目已经上传质量分析表，请点击链接，根据技术部的问题标签进行问题点分析");
            	projectTask.setTaskStatus("0");
            	projectTask.setTaskType("4");
            	projectTask.setTaskUrl(null);
            	projectTask.setStartTime(new Date());
            	projectTask.setCreateTime(new Date());
            	projectTask.setReturnUrl("http://117.144.21.74:10010/project/showDetails?projectNo="+record.getProjectNo());
            	projectTaskMapper.insertSelective(projectTask);
            	
            	//钉钉任务提醒
	      		User user = userDao.findUserByName(projectTask.getAccepter());
	    		projectTask.setDingTalkId(user.getDingTalkId());
	    		RpcSendNoticeToKuai.sendRequest(projectTask);
			}
			
			ProjectTask projectTask = new ProjectTask();
			projectTask.setAccepter("wangweiping");
			projectTask.setProjectNo(record.getProjectNo());
        	projectTask.setInitiator(sender);
        	projectTask.setDescription("该项目已经上传质量分析表，请点击链接，进行审核");
        	projectTask.setTaskStatus("0");
        	projectTask.setTaskType("4");
        	projectTask.setTaskUrl(null);
        	projectTask.setStartTime(new Date());
        	projectTask.setCreateTime(new Date());
        	projectTask.setReturnUrl("http://117.144.21.74:10010/project/showDetails?projectNo="+record.getProjectNo());
        	projectTaskMapper.insertSelective(projectTask);		
        	
        	//钉钉任务提醒
      		User user = userDao.findUserByName(projectTask.getAccepter());
    		projectTask.setDingTalkId(user.getDingTalkId());
    		RpcSendNoticeToKuai.sendRequest(projectTask);
        	
		}
		return qualityAnalysisMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(QualityAnalysis record,String sender, int type) {
		Project project = projectMapper.selectProjctDetails(record.getProjectNo());		
        if(type == QUALITY){				
			if(project.getPurchaseName()!=null){
				ProjectTask projectTask = new ProjectTask();
				projectTask.setAccepter(project.getPurchaseName());
				projectTask.setProjectNo(record.getProjectNo());
            	projectTask.setInitiator(sender);
            	projectTask.setDescription("该项目已经上传质量分析表，请点击链接，根据技术部的问题标签进行问题点分析");
            	projectTask.setTaskStatus("0");
            	projectTask.setTaskType("4");
            	projectTask.setTaskUrl(null);
            	projectTask.setStartTime(new Date());
            	projectTask.setCreateTime(new Date());
            	projectTask.setReturnUrl("http://117.144.21.74:10010/project/showDetails?projectNo="+record.getProjectNo());
            	projectTaskMapper.insertSelective(projectTask);
            	
            	//钉钉任务提醒
	      		User user = userDao.findUserByName(projectTask.getAccepter());
	    		projectTask.setDingTalkId(user.getDingTalkId());
	    		RpcSendNoticeToKuai.sendRequest(projectTask);
			}
			
			ProjectTask projectTask = new ProjectTask();
			projectTask.setAccepter("wangweiping");
			projectTask.setProjectNo(record.getProjectNo());
        	projectTask.setInitiator(sender);
        	projectTask.setDescription("该项目已经上传质量分析表，请点击链接，进行审核");
        	projectTask.setTaskStatus("0");
        	projectTask.setTaskType("4");
        	projectTask.setTaskUrl(null);
        	projectTask.setStartTime(new Date());
        	projectTask.setCreateTime(new Date());
        	projectTask.setReturnUrl("http://117.144.21.74:10010/project/showDetails?projectNo="+record.getProjectNo());
        	projectTaskMapper.insertSelective(projectTask);		
        	
        	//钉钉任务提醒
      		User user = userDao.findUserByName(projectTask.getAccepter());
    		projectTask.setDingTalkId(user.getDingTalkId());
    		RpcSendNoticeToKuai.sendRequest(projectTask);
		}
		return qualityAnalysisMapper.updateByPrimaryKeySelective(record);
	}

}
