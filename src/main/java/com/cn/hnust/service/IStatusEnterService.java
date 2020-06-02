package com.cn.hnust.service;

import java.util.List;
import java.util.Set;

import com.cn.hnust.pojo.StatusEnter;

/**
 * 
 * @author chenlun
 *
 */
public interface IStatusEnterService {

	public void addStatusEnter(StatusEnter statusEnter);
	
	public List<StatusEnter> selectProjectStatusEnter(String projectNo);

	public void updateQualityComplaintFlagByProjectNo(Set<String> proSet, String feekBackFlag);
}
