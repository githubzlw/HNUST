package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.dao.RoleBindDetailMapper;
import com.cn.hnust.dao.RoleBindListMapper;
import com.cn.hnust.pojo.RoleBindDetail;
import com.cn.hnust.pojo.RoleBindList;
import com.cn.hnust.service.IRoleBindListService;

@Service
public class IRoleBindListServiceImpl implements IRoleBindListService {

	@Resource
	private RoleBindListMapper mapper;

	@Resource
	private RoleBindDetailMapper detailMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {

		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RoleBindList record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(RoleBindList record) {
		return mapper.insertSelective(record);
	}

	@Override
	public RoleBindList selectByPrimaryKey(Integer id) {

		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RoleBindList record) {

		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RoleBindList record) {

		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<RoleBindList> selectRoleBindList(String projectNo, int start,
			int pageSize) {

		return mapper.selectRoleBindList(projectNo, start, pageSize);
	}

	@Override
	public int selectRoleBindListCount(String projectNo) {
		return mapper.selectRoleBindListCount(projectNo);
	}

	@Override
	public int insertAll(RoleBindList record) {

		mapper.insertSelective(record);

		if (record.getId() != null) {
			Integer id = record.getId();

			List<RoleBindDetail> list = record.getRoleBindDetails();

			if (list != null && list.size() > 0) {
				for (RoleBindDetail roleBindDetail : list) {
					roleBindDetail.setParentId(id);
				}
				detailMapper.insertList(list);
			}
		}
		return record.getId();
	}

	@Override
	public List<RoleBindDetail> selectByParentId(Integer parentId) {
		return detailMapper.selectByParentId(parentId);
	}

	@Transactional
	@Override
	public int updateAll(RoleBindList record) {

		Integer id = record.getId();
		if (id == null) {
			throw new RuntimeException("更新数据，id不能为空");
		}

		List<RoleBindDetail> list = record.getRoleBindDetails();

		detailMapper.deleteByParentId(id);

		mapper.updateByPrimaryKeySelective(record);

		if (list != null && list.size() > 0) {
			for (RoleBindDetail rd : list) {
				rd.setParentId(id);
			}
			detailMapper.insertList(list);
		}
		return id;
	}

	@Override
	public int deleteAll(Integer id) {

		if (id == null) {
			throw new RuntimeException("更新数据，id不能为空");
		}

		mapper.deleteByPrimaryKey(id);
		detailMapper.deleteByParentId(id);

		return id;
	}

	@Override
	public List<RoleBindDetail> selectCondition(boolean hasEmailUserId,
			boolean hasPurchaseId) {
		return detailMapper.selectCondition(hasEmailUserId, hasPurchaseId);
	}

	@Override
	public List<RoleBindDetail> selectMeetingStatus(int conditionType,
			String meetingName) {

		return detailMapper.selectMeetingStatus(conditionType, meetingName);

	}

	@Override
	public List<RoleBindDetail> selectAllDetail() {
		return detailMapper.selectAllDetail();
	}

	@Transactional
	@Override
	public int updateAllDetail(List<RoleBindDetail> list) {

		if (list.size() == 0) {
			return 0;
		}
		detailMapper.deleteAllDetail();

		return detailMapper.insertList(list);

	}

	@Override
	public List<RoleBindDetail> selectConditionFirst() {
		return detailMapper.selectConditionFirst();
	}

}
