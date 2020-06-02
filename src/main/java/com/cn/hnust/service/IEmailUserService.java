package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.EmailUser;
import com.cn.hnust.pojo.User;

public interface IEmailUserService {
    /**
     * 查询所有的销售、跟单、采购
     * @Title queryAll 
     * @Description
     * @return
     * @return List<EmailUser>
     */
    List<EmailUser> queryAll();
   /**
    * 
    * @Title:IEmailUserService
    * @Description:查询质检
      @author wangyang
    * @return List<EmailUser>
    * @throws
    */
	List<EmailUser> queryQualityTesting();
	/**
	 * 
	 * @Title:IEmailUserService
	 * @Description:查询项目平均完成时间
	   @author wangyang
	 * @param user
	 * @return List<User>
	 * @throws
	 */
List<EmailUser> getAverageCompletionTime(EmailUser user);
/**
 * 
 * @Title:IEmailUserService
 * @Description:查询质量投诉问题
   @author wangyang
 * @param user
 * @return List<User>
 * @throws
 */
	List<EmailUser> getQualityProblem(EmailUser user);
/**
 * 
 * @Title:IEmailUserService
 * @Description:获取打样项目数据
   @author wangyang
 * @param user
 * @return List<EmailUser>
 * @throws
 */
List<EmailUser> getSuccessRateProject(EmailUser user);
/**
 * 
 * @Title:IEmailUserService
 * @Description:质检报告
   @author wangyang
 * @param user
 * @return List<User>
 * @throws
 */
List<EmailUser> getAllReport(EmailUser user);

   
}
