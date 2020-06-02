package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.ProjectPause;

public interface ProjectPauseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPause record);

    int insertSelective(ProjectPause record);

    ProjectPause selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectPause record);

    int updateByPrimaryKey(ProjectPause record);
    
    
    /**
     * 查询暂停数
     * @Title count 
     * @Description
     * @param projectNo
     * @return
     * @return int
     */
    int count(String projectNo);
    
    
    /**
     * 查询暂停项目
     * @Title selectByProjectNo 
     * @Description
     * @param projectNo
     * @return
     * @return List<ProjectPause>
     */
    List<ProjectPause> selectByProjectNo(String projectNo);
    
    
    /**
     * 查询最近项目暂停
     * @Title selectLastPause 
     * @Description
     * @param projectNo
     * @return
     * @return ProjectPause
     */
    ProjectPause selectLastPause(String projectNo);
}