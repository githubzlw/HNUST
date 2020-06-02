package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.ProjectDrawing;

public interface IProjectDrawingService {
  /**
   * 批量添加图纸信息
   * @param projectDrawingList
   */
  public void batchAddProjectDrawing(List<ProjectDrawing> projectDrawingList);
  
  public List<ProjectDrawing> selectAllProjectDrawing();

  public List<ProjectDrawing> selectProjectDrawingByProjectNo(java.lang.String projectNo);

  public void addProjectDrawing(ProjectDrawing projectDrawing);
 
  public List<ProjectDrawing> selectProjectDemandReportByNo(java.lang.String projectNo);
/**
 * 
 * @Title:IProjectDrawingService
 * @Description:查询要求图纸跟新后图纸上传情况
   @author wangyang
 * @param drawing
 * @return List<ProjectDrawing>
 * @throws
 */
public List<ProjectDrawing> selectByProjectNo(ProjectDrawing drawing);
}
