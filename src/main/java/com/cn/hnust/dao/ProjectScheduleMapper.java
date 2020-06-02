package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProjectSchedule;

public interface ProjectScheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectSchedule record);

    int insertSelective(ProjectSchedule record);

    ProjectSchedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectSchedule record);

    int updateByPrimaryKey(ProjectSchedule record);
    
    /**
     * 根据项目号查询大货交期
     * @Title selectByProjectNo 
     * @Description
     * @param projectNo
     * @return
     * @return List<ProjectSchedule>
     */
    List<ProjectSchedule> selectByProjectNo(String projectNo);
    
    /**
     * 批量插入数据
     * @Title insert 
     * @Description
     * @param record
     * @return
     * @return int
     */
    int insertBatch(List<ProjectSchedule> item);
    
    
    /**
     * 批量更新大货时间
     * @Title updateBatch 
     * @Description
     * @param records
     * @return
     * @return int
     */
    int updateBatch(List<ProjectSchedule> item);
    
    /**
     * 根据项目号和批次查询
     * @Title selectByProjectNoAndNum 
     * @Description
     * @param projectNo
     * @param num
     * @return
     * @return ProjectSchedule
     */
    ProjectSchedule selectByProjectNoAndNum(@Param("projectNo")String projectNo,@Param("num")Integer num);
}