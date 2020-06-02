package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.ProjectPause;

public interface ProjectPauseService {

	
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
    
    
    
    int updateByPrimaryKeySelective(ProjectPause record);
}
