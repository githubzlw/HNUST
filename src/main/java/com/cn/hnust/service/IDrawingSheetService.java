package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.DrawingSheet;

public interface IDrawingSheetService {
/**
 * 
 * @Title:IDrawingSheetService
 * @Description:查询项目全部图纸
   @author wangyang
 * @param projectNo
 * @return List<DrawingSheet>
 * @throws
 */
	List<DrawingSheet> getAll(String projectNo);

}
