package com.cn.hnust.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.User;

public interface IUserDao {
   
    User selectUser(@Param("userName")String userName,@Param("password")String password);
    
    User selectUserByName(String userName);

	User findUserByName(String userName);

	List<User> selectAllUser();
	
	List<User> selectUserByType(@Param("roleNo")int roleNo);
	
	 int insertSelective(User record);


	    /**
	     * 查询所有人员
	     * @return
	     */
	 List<User> queryAllUser(@Param("userName")String userName);

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
	  List<User> queryByJob(@Param("job")String job);
	  /**
	   * 根据权限查询
	   * @Title queryByJob 
	   * @Description
	   * @param job
	   * @return
	   * @return List<User>
	   */
	List<User> queryByRoleNo(User user);
	
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
     * 
     * @Title:IUserDao
     * @Description:查询全部质检名单
       @author wangyang
     * @return List<User>
     * @throws
     */
	List<User> getAllInspection();
	/**
     * 
     * @Title:IUserService
     * @Description:查询最近30天检验任务数及平均延期时间
       @author wangyang
     * @return List<User>
     * @throws
     */
	List<User> getInspectionTask(User user1);

}