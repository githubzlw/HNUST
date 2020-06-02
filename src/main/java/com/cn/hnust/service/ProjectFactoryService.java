package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.ProjectFactoryQuery;

public interface ProjectFactoryService {
    /**
     * 根据项目号和工厂id查询
     * @Title selectByProjectNoAndFactoryId 
     * @Description
     * @param projectNo
     * @param factoryId
     * @return
     * @return ProjectFactory
     */
    ProjectFactory selectByProjectNoAndFactoryId(String projectNo,String factoryId);
    
    
    /**
     * 根据项目号查询
     * @Title selectByProjectNoAndFactoryId 
     * @Description
     * @param projectNo
     * @param factoryId
     * @return
     * @return ProjectFactory
     */
    List<ProjectFactory> selectByProjectNo(String projectNo);  
    
    
    int insertSelective(ProjectFactory record);
    
    
    
    /**
     * 根据项目号查询
     * @Title selectByFactoryId 
     * @Description
     * @param projectNo
     * @param factoryId
     * @return
     * @return ProjectFactory
     */
    List<ProjectFactory> selectByFactoryId(String factoryId,Integer queryDate); 
    
    
    /**
     * 查询工厂项目统计列表
     * @Title selectFactoryList 
     * @Description 
     * @return
     * @return int
     */
    List<ProjectFactory> selectFactoryList(ProjectFactoryQuery projectFactoryQuery);
    
    /**
     * 查询工厂项目统计数量
     * @Title selectFactoryList 
     * @Description 
     * @return
     * @return int
     */
    int selectFactoryListCount(ProjectFactoryQuery projectFactoryQuery);
    
    
    /**
     * 
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return int
     */
    int selectCountByFactoryName(String factoryName);
    
    
    
    /**
     * 根据工厂查询单一工厂项目个数
     * @Title selectCountByFactoryNameOnly 
     * @Description
     * @param factoryName
     * @return
     * @return int
     */
    int selectCountByFactoryNameOnly(String factoryName);
    
    
    
    /**
     * 查询工厂项目列表
     * @Title selectFactoryList 
     * @Description 
     * @return
     * @return int
     */
    List<ProjectFactory> selectProjectList(ProjectFactoryQuery projectFactoryQuery);
    /**
     * 查询工厂项目数量
     * @Title selectFactoryList 
     * @Description 
     * @return
     * @return int
     */
    int selectProjectListCount(ProjectFactoryQuery projectFactoryQuery);
    
    
    /**
     * 查询工厂
     * @Title selectAllFactory 
     * @Description
     * @return
     * @return List<ProjectFactory>
     */
    List<String> selectAllFactory(@Param("inputKey")String inputKey);
    
    
    
    /**
     * 批量更新工厂状态
     * @Title updateBatch 
     * @Description
     * @param factoryList
     * @return
     * @return int
     */
    int updateBatch(List<ProjectFactory> factoryList);
    
    
    
    /**
     * 根据项目号查询
     * @Title selectByProjectNoAndFactoryId 
     * @Description
     * @param projectNo
     * @param factoryId
     * @return
     * @return ProjectFactory
     */
    List<ProjectFactory> selectByProjectNoGroupByFactoryId(String projectNo);
      /**
       * 
       * @Title:ProjectFactoryService
       * @Description:修改合同实际完成时间
         @author wangyang
       * @param factory
       * @return int
       * @throws
       */

	int updateStatus(ProjectFactory factory);
    /**
     * 
     * @Title:ProjectFactoryService
     * @Description:获取项目是否有未完成合同
       @author wangyang
     * @return int
     * @throws
     */

	int getCompletedCount(Project project);
    /**
     * 
     * @Title:ProjectFactoryService
     * @Description:根据主键查询数据
       @author wangyang
     * @param parseInt
     * @return ProjectFactory
     * @throws
     */

	ProjectFactory selectByPrimaryKey(int parseInt);

    /**
     * 
     * @Title:ProjectFactoryService
     * @Description:查询数据是否存在
       @author wangyang
     * @param factory1
     * @return ProjectFactory
     * @throws
     */
	ProjectFactory getProjctDetails(ProjectFactory factory1);
    /**
     * 
     * @Title:ProjectFactoryService
     * @Description:修改数据
       @author wangyang
     * @param projectFactory void
     * @throws
     */

	

	void updateByPrimaryKey(ProjectFactory projectFactory);
     /**
      * 
      * @Title:ProjectFactoryService
      * @Description:添加数据到数据库
        @author wangyang
      * @param projectFactory void
      * @throws
      */

	void insertSelective1(ProjectFactory projectFactory);
/**
 * 
 * @Title:ProjectFactoryService
 * @Description:最近报告数
   @author wangyang
 * @param factory
 * @return int
 * @throws
 */

	int getFactory(ProjectFactory factory);   
}
