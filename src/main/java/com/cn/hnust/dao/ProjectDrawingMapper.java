package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.ProjectDrawing;

public interface ProjectDrawingMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProjectDrawing record);

    int insertSelective(ProjectDrawing record);

    ProjectDrawing selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProjectDrawing record);

    int updateByPrimaryKey(ProjectDrawing record);
    /**
     * 批量添加图纸信息
     * @param projectDrawingList
     */
    void batchAddProjectDrawing(List<ProjectDrawing> projectDrawingList);
    /**
     * 查询所有记录信息
     * @return
     */
	List<ProjectDrawing> selectAllProjectDrawing();
  
	List<ProjectDrawing> selectProjectDrawingByProjectNo(String projectNo);
    
	List<ProjectDrawing> selectProjectDemandReportByNo(String projectNo);
	/**
	 * 
	 * @Title:IProjectDrawingService
	 * @Description:查询要求图纸跟新后图纸上传情况
	   @author wangyang
	 * @param drawing
	 * @return List<ProjectDrawing>
	 * @throws
	 */
	List<ProjectDrawing> selectByProjectNo(ProjectDrawing drawing);
}