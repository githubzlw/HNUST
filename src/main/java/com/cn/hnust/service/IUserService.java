package com.cn.hnust.service;

import java.util.Date;
import java.util.List;

import com.cn.hnust.pojo.User;
/**
 * 用户Service 接口
 * @author Administrator
 *
 */
public interface IUserService {
	 User selectUser(String userName,String password);
	
	 User selectUserByName(String trueName);
	
	 User findUserByName(String userName);
	
	 List<User> selectAllUser();
	 
	 List<User> selectUserByType(int roleNo);
	 
	 
	 
	 
	 int insertSelective(User record);


	    /**
	     * 查询所有人员
	     * @return
	     */
	 List<User> queryAllUser(String userName);

	    /**
	     * 修改用户
	     * @param user
	     * @return
	     */
	  int update(User user);
	  
	  
	  
	  /**
	   * 根据id查询
	   * @Title selectById 
	   * @Description
	   * @param id
	   * @return
	   * @return User
	   */
	  User selectById(Integer id);
	  
	  
	  
	  /**
	   * 根据职位查询
	   * @Title queryByJob 
	   * @Description
	   * @param job
	   * @return
	   * @return List<User>
	   */
	  List<User> queryByJob(String job);
	  
	  
	  
		/**
		 * 根据钉钉id查询
		 * @Title selectByDingTalkId 
		 * @Description 
		 * @param dingTalkId
		 * @return
		 * @return User
		 */
		User selectByDingTalkId(String dingTalkId);
        /**
         * @param n 
         * @param m 
         * 
         * @Title:IUserService
         * @Description:查询投诉列表
           @author wangyang
         * @return List<User>
         * @throws
         */
		List<User> selectComplaint(Date m, Date n);
        /**
         * 
         * @Title:IUserService
         * @Description:查询最近30天检验任务数及平均延期时间
           @author wangyang
         * @return List<User>
         * @throws
         */
		List<User> getInspectionTask(User user1);
/**
 * 
 * @Title:IUserService
 * @Description:查询采购
   @author wangyang
 * @param user1
 * @return List<User>
 * @throws
 */
		List<User> queryByRoleNo(User user1);



	
}
