package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.daoErp.DrawingSheetMapper;
import com.cn.hnust.pojo.DrawingSheet;
import com.cn.hnust.service.IDrawingSheetService;

@Service
public class DrawingSheetServiceImpl implements IDrawingSheetService{
	@Autowired
	private DrawingSheetMapper drawingSheetMapper;

	@Override
	public List<DrawingSheet> getAll(String projectNo) {
		
		return drawingSheetMapper.getAll(projectNo);
	}
}
