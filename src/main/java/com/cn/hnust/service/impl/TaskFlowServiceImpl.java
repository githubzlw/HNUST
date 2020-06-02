package com.cn.hnust.service.impl;



import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;



import com.cn.hnust.dao.TaskFlowDetailMapper;
import com.cn.hnust.dao.TaskFlowMapper;
import com.cn.hnust.pojo.TaskFlow;
import com.cn.hnust.pojo.TaskFlowDetail;
import com.cn.hnust.service.ITaskFlowService;


@Service
public class TaskFlowServiceImpl implements ITaskFlowService {

	@Resource
	private TaskFlowMapper mapper;
	@Resource
	private TaskFlowDetailMapper detailMapper;



	@Override
	public int insert(TaskFlow record) {
		
		return mapper.insert(record);
	}
	
	

	@Override
	public int insertSelective(TaskFlow record) {
		
		return mapper.insertSelective(record);
	}

	@Override
	public TaskFlow selectByPrimaryKey(Integer id) {
	
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TaskFlow record) {
		
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TaskFlow record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TaskFlow> selectList(int start, int pageSize) {
	
		return mapper.selectList(start, pageSize);
	}

	@Override
	public int selectListCount() {

		return mapper.selectListCount();
	}

	@Override
	public List<TaskFlow> checkHasKey(int conditionType, int triggerId) {

		return mapper.checkHasKey(conditionType, triggerId);
	}

	@Override
	public List<TaskFlow> selectAllList() {
		return mapper.selectAllList();
	}

	@Transactional
	@Override
	public void insertAll(TaskFlow record) {
		
		mapper.insertBackId(record);
		
		Integer parentId = record.getId();
		
		if(parentId!=null){
			List<TaskFlowDetail> list = record.getTfDetails();
			if (list!=null&&list.size()>0) {
				for (TaskFlowDetail td : list) {
					td.setParentId(parentId);
				}
			}
			

			detailMapper.insertList(list);
		}
	}
	
	@Transactional
	@Override
	public void updateAll(TaskFlow record) {
		
		Integer parentId = record.getId();
		
		mapper.updateByPrimaryKeySelective(record);
		if(parentId!=null){
			detailMapper.deleteByParentId(parentId);
			
			List<TaskFlowDetail>  list = record.getTfDetails();
			if (list!=null&&list.size()>0) {
				for (TaskFlowDetail td : list) {
					td.setParentId(parentId);
				}
				detailMapper.insertList(list);
			}
		}
	}
	
	@Transactional
	@Override
	public void deleteByPrimaryKey(Integer id) {
		 mapper.deleteByPrimaryKey(id);
		 detailMapper.deleteByParentId(id);
		
		 
	}



	@Override
	public TaskFlow selectAllDetail(Integer id) {
	
		return mapper.selectAllDetail(id);
	}
	
	

	

}
