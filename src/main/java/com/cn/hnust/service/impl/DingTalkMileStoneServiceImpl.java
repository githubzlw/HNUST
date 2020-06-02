package com.cn.hnust.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.dao.DingTalkMileStoneMapper;
import com.cn.hnust.pojo.DingTalkMileStone;
import com.cn.hnust.service.DingTalkMileStoneService;

@Service
public class DingTalkMileStoneServiceImpl implements DingTalkMileStoneService {

	@Autowired
	private DingTalkMileStoneMapper dingTalkMileStoneMapper;
	
	
	@Override
	public int insertSelective(DingTalkMileStone record) {
		return dingTalkMileStoneMapper.insertSelective(record);
	}

	@Override
	public DingTalkMileStone selectByPrimaryKey(Integer id) {
		return dingTalkMileStoneMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DingTalkMileStone record) {
		return dingTalkMileStoneMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<DingTalkMileStone> selectByProjectNo(String projectNo) {
		return dingTalkMileStoneMapper.selectByProjectNo(projectNo);
	}

	@Override
	public int deleteByUid(String uid) {
		return dingTalkMileStoneMapper.deleteByUid(uid);
	}

	
	@Transactional
	@Override
	public int insertBatch(List<DingTalkMileStone> list) {
	     
		//判断uid,processInstanceId是否存在
		String uid = null;
		String processInstanceId = null;
		if(list != null && list.size()>0){
			uid = list.get(0).getUid();
			processInstanceId = list.get(0).getProcessInstanceId();
		}
		if(StringUtils.isNotBlank(uid)){
			dingTalkMileStoneMapper.deleteByUid(uid);
		}		
		if(StringUtils.isNotBlank(processInstanceId)){
			dingTalkMileStoneMapper.deleteByProcessInstanceId(processInstanceId);
		}		
		return dingTalkMileStoneMapper.insertBatch(list);
	}

	@Override
	public int updateBatch(List<DingTalkMileStone> list) {
		return dingTalkMileStoneMapper.updateBatch(list);
	}

}
