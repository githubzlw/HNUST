package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.FactoryFund;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.User;

public interface ItemCaseERPService {
	/**
	 * 根据项目号查询
	 * @Title selectByCaseNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return ProjectERP
	 */
    ProjectERP selectByCaseNo(String projectNo);
    /**
     * @param start 
     * @param user1 
     * 
     * @Title:ItemCaseERPService
     * @Description:老客户报价数
       @author wangyang
     * @return double
     * @throws
     */
	int getOldCustomerOff(String userName, String start);
	/**
	 * @param start 
	 * @param user1 
     * 
     * @Title:ItemCaseERPService
     * @Description:新客户大项目报价数
       @author wangyang
     * @return double
     * @throws
     */
	int getNewCustomerBigProjectOffer(String userName, String start);
	/**
	 * @param start 
	 * @param user1 
     * 
     * @Title:ItemCaseERPService
     * @Description:老客户大项目报价数
       @author wangyang
     * @return double
     * @throws
     */
	int getOldCustomerBigProjectOffer(String userName, String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:获取违规次数
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return int
	 * @throws
	 */
	String getProjectViolation(String userName, String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:打样成功
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return String
	 * @throws
	 */
	int proofingSuccess(String userName, String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:打样失败
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return String
	 * @throws
	 */
	int proofingFailure(String userName, String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:获取项目
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return List<ProjectERP>
	 * @throws
	 */
	List<ProjectERP> getAllCaseNo(String userName, String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:获取销售项目
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return List<ProjectERP>
	 * @throws
	 */
	List<ProjectERP> getAllCaseNo1(String userName, String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:获取合同金额
	   @author wangyang
	 * @param project
	 * @return String
	 * @throws
	 */
	String getAllContractAmount(String projectNo);
	/**
	 * 
	 * @Title:ErpReportService
	 * @Description:修改ERP上传合同显示
	   @author wangyang
	 * @param projectNo
	 * @return int
	 * @throws
	 */
	int update(String projectNo);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:查看项目组是否存在
	   @author wangyang
	 * @param fac
	 * @return int
	 * @throws
	 */
	
	int findName(String caseno, String applicant,int num);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:查询主键值
	   @author wangyang
	 * @return java.lang.String
	 * @throws
	 */
	java.lang.String getApNumber();
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:添加apnumber
	   @author wangyang
	 * @param apNumber void
	 * @throws
	 */
	void insert(String apNumber);
    /**
     * 
     * @Title:ItemCaseERPService
     * @Description:查询质检是否在项目中,并处理
       @author wangyang
     * @param project
     * @return ProjectERP
     * @throws
     */
	ProjectERP search(ProjectERP project);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:修改质检
	   @author wangyang
	 * @param projectERP void
	 * @throws
	 */
	void updateQuality(ProjectERP projectERP);

	/**
	 * 查询指定时间段付工厂款信息
	 * @param projectERP
	 * @return
	 */
    List<ProjectERP> getProjectExportProgress(ProjectERP projectERP);
}
