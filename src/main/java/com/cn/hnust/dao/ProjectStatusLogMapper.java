package com.cn.hnust.dao;

import com.cn.hnust.pojo.ProjectStatusLog;

public interface ProjectStatusLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectStatusLog record);

    int insertSelective(ProjectStatusLog record);

    ProjectStatusLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectStatusLog record);

    int updateByPrimaryKey(ProjectStatusLog record);
}