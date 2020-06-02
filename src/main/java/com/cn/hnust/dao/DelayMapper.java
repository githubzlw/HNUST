package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.Delay;
import com.cn.hnust.pojo.ProjectSchedule;


public interface DelayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Delay record);

    int insertSelective(Delay record);

    Delay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Delay record);

    int updateByPrimaryKey(Delay record);
    
    /**
     * 查询项目延期信息(全部)
     * @param projectNo
     * @return
     */
    List<Delay> selectAllByProjectNo(String projectNo);
    
    /**
     * 查询项目延期信息(最新)
     * @param projectNo
     * @return
     */
    List<Delay> selectDelayByProjectNo(String projectNo);
    /**
     * 查询项目最大延期时间
     * @param projectNo
     * @return
     */
	Delay selectMaxDelayByProjectNo(@Param("projectNo")String projectNo);

	Delay selectApplyDelayByProjectNo(@Param("projectNo")String projectNo);
	
	
	/**
	 * 批量插入延期数据
	 */
	int insertBatch(List<Delay> item);
	
	/**
	 * 查询延期次数
	 * @Title delayCount 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return int
	 */
	int delayCount(@Param("projectNo")String projectNo,@Param("num")int num);
	
	
    /**
     * 批量延期数据
     * @Title updateBatch 
     * @Description
     * @param records
     * @return
     * @return int
     */
    int updateBatch(List<Delay> item);
    
    
    /**
     * 根据ids查询延期表数据
     * @Title selectByIds 
     * @Description
     * @param item
     * @return
     * @return List<Delay>
     */
    List<Delay> selectByIds(List<?> item);
}