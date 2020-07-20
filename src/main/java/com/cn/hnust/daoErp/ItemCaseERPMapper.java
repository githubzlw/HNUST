package com.cn.hnust.daoErp;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.FactoryFund;
import com.cn.hnust.pojo.ProjectERP;

public interface ItemCaseERPMapper {

	List<String> getProjects(@Param("start")int startRow,@Param("end")int endRow);
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
     * @param user1 
     * 
     * @Title:ItemCaseERPService
     * @Description:老客户报价数
       @author wangyang
     * @return double
     * @throws
     */
	int getOldCustomerOff(@Param("CustomerManager")String CustomerManager, @Param("projectNo")String projectNo);
	/**
	 * @param user1 
     * 
     * @Title:ItemCaseERPService
     * @Description:新客户大项目报价数
       @author wangyang
     * @return double
     * @throws
     */
	int getNewCustomerBigProjectOffer(@Param("CustomerManager")String CustomerManager, @Param("projectNo")String projectNo);
	/**
	 * @param user1 
     * 
     * @Title:ItemCaseERPService
     * @Description:老客户大项目报价数
       @author wangyang
     * @return double
     * @throws
     */
	int getOldCustomerBigProjectOffer(@Param("CustomerManager")String CustomerManager, @Param("projectNo")String projectNo);
    /**
     * 
     * @Title:ItemCaseERPMapper
     * @Description:获取违规次数
       @author wangyang
     * @param userName
     * @param start
     * @return int
     * @throws
     */
	String getProjectViolation(@Param("CustomerManager")String userName,@Param("projectNo") String start);
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
	int proofingSuccess(@Param("CustomerManager")String userName,@Param("projectNo") String start);
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
	int proofingFailure(@Param("CustomerManager")String userName,@Param("projectNo") String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:获取项目
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return String
	 * @throws
	 */
	List<ProjectERP> getAllCaseNo(@Param("CustomerManager")String userName, @Param("projectNo")String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:获取项目
	   @author wangyang
	 * @param userName
	 * @param start
	 * @return String
	 * @throws
	 */
	List<ProjectERP> getAllCaseNo1(@Param("CustomerManager")String userName,@Param("projectNo") String start);
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:获取合同金额
	   @author wangyang
	 * @param project
	 * @return String
	 * @throws
	 */
	String getContractAmount1(@Param("projectNo")String projectNo);
	/**
	 * 
	 * @Title:ErpReportMapper
	 * @Description:修改项目不允许上传合同
	   @author wangyang
	 * @param projectNo
	 * @return int
	 * @throws
	 */
	int update(@Param("projectNo")String projectNo);
	/**
	 * 
	 * @Title:ErpReportMapper
	 * @Description:清理不允许上传合同逻辑
	   @author wangyang
	 * @param projectNo
	 * @return int
	 * @throws
	 */
	int clean(String projectNo);
	/**
	 * 
	 * @Title:ItemCaseERPMapper
	 * @Description:查看项目组是否存在
	   @author wangyang
	 * @param fac
	 * @return int
	 * @throws
	 */
	int findName(@Param("projectNo")String caseno, @Param("customerManager")String applicant ,@Param("id")int num);
	/**
	 * 
	 * @Title:ItemCaseERPMapper
	 * @Description:查询apnumber
	   @author wangyang
	 * @return String
	 * @throws
	 */
	String getApNumber();
	/**
	 * 
	 * @Title:ItemCaseERPService
	 * @Description:添加apnumber
	   @author wangyang
	 * @param apNumber void
	 * @throws
	 */
	void insert(@Param("ApNumber")String apNumber);
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
	 * @Title:ItemCaseERPMapper
	 * @Description:修改主质检
	   @author wangyang
	 * @param projectERP void
	 * @throws
	 */
	void updateQuality(ProjectERP projectERP);

	/**
	 * 查看付工厂款列表信息
	 * @param projectERP
	 * @return
	 */
	List<ProjectERP> getProjectExportProgress(ProjectERP projectERP);
}
