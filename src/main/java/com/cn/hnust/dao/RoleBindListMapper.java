package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.RoleBindList;

public interface RoleBindListMapper {
	
    int deleteByPrimaryKey(Integer id);
    
    int insert(RoleBindList record);

    int insertSelective(RoleBindList record);

    RoleBindList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleBindList record);

    int updateByPrimaryKey(RoleBindList record);
    
    List<RoleBindList> selectRoleBindList(@Param("projectNo")String projectNo,@Param("start")int start,@Param("pageSize")int pageSize);
    
    int selectRoleBindListCount(@Param("projectNo")String projectNo);
    
 
    
    
    
    
}