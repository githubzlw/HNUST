package com.cn.hnust.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.IUserDao;
import com.cn.hnust.dao.ProjectComplaintMapper;
import com.cn.hnust.pojo.ProjectComplaint;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	@Autowired
	private ProjectComplaintMapper projectComplaintMapper;

	@Override
	public User selectUser(String userName, String password) {
		return userDao.selectUser(userName, password);
	}

	@Override
	public User selectUserByName(String userName) {
		return userDao.selectUserByName(userName);
	}

	@Override
	public User findUserByName(String userName) {
		return userDao.findUserByName(userName);
	}

	@Override
	public List<User> selectAllUser() {
		return userDao.selectAllUser();
	}

	@Override
	public List<User> selectUserByType(int roleNo) {
		return userDao.selectUserByType(roleNo);
	}

	@Override
	public int insertSelective(User record) {
		return userDao.insertSelective(record);
	}

	@Override
	public List<User> queryAllUser(String userName) {
		return userDao.queryAllUser(userName);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public User selectById(Integer id) {
		return userDao.selectById(id);
	}

	@Override
	public List<User> queryByJob(String job) {
		return userDao.queryByJob(job);
	}

	public List<User> queryByRoleNo(User user) {
		
		return userDao.queryByRoleNo(user);
	}

	@Override
	public User selectByDingTalkId(String dingTalkId) {
		return userDao.selectByDingTalkId(dingTalkId);
	}

	@Override
	public List<User> selectComplaint(Date m, Date n) {
		List<User>users=userDao.getAllInspection();
		List<User>users1=new ArrayList<User>();
		for(User user:users){
			ProjectComplaint complaint=new ProjectComplaint();
			complaint.setZhijian1(user.getUserName());
			complaint.setStartingTime(m);
			complaint.setEndTime(n);
			List<ProjectComplaint> projectComplaint=projectComplaintMapper.getComplaintsList(complaint);
			for( ProjectComplaint projectComplaint1 :projectComplaint ){
			User user1=new User();
			user1.setProjectNo(projectComplaint1.getProjectNo());
			user1.setAmount(projectComplaint1.getProjectAmount());
			user1.setInspectionTimelinessNumber(projectComplaint1.getInspectionTimelinessNumber());
			user1.setComplaintsNumber(projectComplaint1.getComplaintsNumber());
			user1.setId(projectComplaint1.getId());
			user1.setUserName(user.getUserName());
			user1.setZhijian1(projectComplaint1.getZhijian1());
			user1.setZhijian2(projectComplaint1.getZhijian2());
			user1.setZhijian3(projectComplaint1.getZhijian3());
			users1.add(user1);
			}
		}
		return users1;
	}

	@Override
	public List<User> getInspectionTask(User user1) {
		
		return userDao.getInspectionTask(user1);
	}

	
	

}
