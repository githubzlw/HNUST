package com.cn.hnust.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ProjectFactoryMapper;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.ProjectFactoryQuery;
import com.cn.hnust.service.ProjectFactoryService;

@Service
public  class ProjectFactoryServiceImpl implements ProjectFactoryService {

	@Autowired
	private ProjectFactoryMapper projectFactoryMapper;
	
	@Override
	public ProjectFactory selectByProjectNoAndFactoryId(String projectNo,String factoryId) {
		return projectFactoryMapper.selectByProjectNoAndFactoryId(projectNo, factoryId);
	}

	@Override
	public List<ProjectFactory> selectByProjectNo(String projectNo) {
		return projectFactoryMapper.selectByProjectNo(projectNo);
	}

	@Override
	public int insertSelective(ProjectFactory record) {
		int count = 0;
		if(record!=null){
			if(StringUtils.isNotBlank(record.getProjectNo()) && StringUtils.isNotBlank(record.getFactoryId())){
				//根据项目号和工厂名查询
				ProjectFactory projectFactory = projectFactoryMapper.selectByProjectNoAndFactoryName(record.getProjectNo(), record.getFactoryName());
				//根据项目号和工厂id查询
				ProjectFactory projectFactory2 = projectFactoryMapper.selectByProjectNoAndFactoryId(record.getProjectNo(), record.getFactoryId());
				if(projectFactory == null && projectFactory2 == null){
					count = projectFactoryMapper.insertSelective(record);
				}else if(projectFactory == null && projectFactory2 != null){
					projectFactory2.setContractDate(record.getContractDate());					
					projectFactory2.setFactoryName(record.getFactoryName());
					//比较交期时间，保存较长的时间
					Date oldDate = projectFactory2.getDeliveryDate();
					Date newDate = record.getDeliveryDate();
					if(newDate!=null && oldDate!=null && newDate.getTime()>oldDate.getTime()){
						projectFactory2.setDeliveryDate(newDate);
					}else if(newDate!=null && oldDate==null){
						projectFactory2.setDeliveryDate(newDate);
					}
					projectFactoryMapper.updateByPrimaryKeySelective(projectFactory2);
				}else if(projectFactory != null && projectFactory2 == null){
					projectFactory.setContractDate(record.getContractDate());					
					projectFactory.setFactoryId(record.getFactoryId());
					//比较交期时间，保存较长的时间
					Date oldDate = projectFactory.getDeliveryDate();
					Date newDate = record.getDeliveryDate();
					if(newDate!=null && oldDate!=null && newDate.getTime()>oldDate.getTime()){
						projectFactory.setDeliveryDate(newDate);
					}else if(newDate!=null && oldDate==null){
						projectFactory.setDeliveryDate(newDate);
					}

					projectFactoryMapper.updateByPrimaryKeySelective(projectFactory);
				}else{
					projectFactory2.setContractDate(record.getContractDate());					
					//比较交期时间，保存较长的时间
					Date oldDate = projectFactory2.getDeliveryDate();
					Date newDate = record.getDeliveryDate();
					if(newDate!=null && oldDate!=null && newDate.getTime()>oldDate.getTime()){
						projectFactory2.setDeliveryDate(newDate);
					}else if(newDate!=null && oldDate==null){
						projectFactory2.setDeliveryDate(newDate);
					}
					projectFactoryMapper.updateByPrimaryKeySelective(projectFactory2);
				}
			}			
		}		
		return count;
	}

	@Override
	public List<ProjectFactory> selectByFactoryId(String factoryId,Integer queryDate) {
		return projectFactoryMapper.selectByFactoryId(factoryId,queryDate);
	}

	@Override
	public List<ProjectFactory> selectFactoryList(ProjectFactoryQuery projectFactoryQuery) {
		return projectFactoryMapper.selectFactoryList(projectFactoryQuery);
	}

	@Override
	public int selectCountByFactoryName(String factoryName) {
		return projectFactoryMapper.selectCountByFactoryName(factoryName);
	}

	@Override
	public int selectFactoryListCount(ProjectFactoryQuery projectFactoryQuery) {
		return projectFactoryMapper.selectFactoryListCount(projectFactoryQuery);
	}

	@Override
	public List<ProjectFactory> selectProjectList(
			ProjectFactoryQuery projectFactoryQuery) {
		return projectFactoryMapper.selectProjectList(projectFactoryQuery);
	}

	@Override
	public int selectProjectListCount(ProjectFactoryQuery projectFactoryQuery) {
		return projectFactoryMapper.selectProjectListCount(projectFactoryQuery);
	}

	@Override
	public List<String> selectAllFactory(String inputKey) {
		return projectFactoryMapper.selectAllFactory(inputKey);
	}

	@Override
	public int selectCountByFactoryNameOnly(String factoryName) {
		return projectFactoryMapper.selectCountByFactoryNameOnly(factoryName);
	}

	@Override
	public int updateBatch(List<ProjectFactory> factoryList) {
		return projectFactoryMapper.updateBatch(factoryList);
	}

	@Override
	public List<ProjectFactory> selectByProjectNoGroupByFactoryId(String projectNo) {
		return projectFactoryMapper.selectByProjectNoGroupByFactoryId(projectNo);
	}

	@Override
	public int updateStatus(ProjectFactory factory) {
		
		return projectFactoryMapper.updateStatus(factory);
	}

	@Override
	public int getCompletedCount(Project project) {
		
		return projectFactoryMapper.getCompletedCount(project);
	}

	@Override
	public ProjectFactory selectByPrimaryKey(int parseInt) {
		
		return projectFactoryMapper.selectByPrimaryKey(parseInt);
	}

	@Override
	public ProjectFactory getProjctDetails(ProjectFactory factory1) {
		
		return projectFactoryMapper.getProjctDetails(factory1);
	}

	@Override
	public void updateByPrimaryKey(ProjectFactory projectFactory) {
		
		 projectFactoryMapper.updateByPrimaryKey1(projectFactory);
	}

	@Override
	public void insertSelective1(ProjectFactory projectFactory) {
		
		projectFactoryMapper.insertSelective(projectFactory);
	}

	@Override
	public int getFactory(ProjectFactory factory) {
		
		return projectFactoryMapper.getFactory(factory);
	}

	@Override
	public ProjectFactory getAllBargain(ProjectFactory factory1) {
		return projectFactoryMapper.getAllBargain(factory1);
	}

    @Override
    public ProjectFactory selectProjectNo(String projectNo) {

		return projectFactoryMapper.selectProjectNo(projectNo);
    }


}
