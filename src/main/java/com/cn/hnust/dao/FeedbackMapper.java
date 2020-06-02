package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Feedback;
import com.cn.hnust.pojo.Project;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

	List<Feedback> selectFeedbackByProjectNo(@Param("projectNo")String projectNo, @Param("week")int week);
	
	/**
	 * 根据项目号查询最近时间的更新
	 * @Title selectByProjectNo 
	 * @Description 
	 * @param projectNo
	 * @return
	 * @return List<Feedback>
	 */
	Feedback selectLastByProjectNo(String projectNo);
	/**
	 * 
	 * @Title:IProjectService
	 * @Description:1周未跟新跟单项目数
	   @author wangyang
	 * @param project
	 * @return int
	 * @throws
	 */
	int getNumberOfItemsNotUpdated(Project project);
}