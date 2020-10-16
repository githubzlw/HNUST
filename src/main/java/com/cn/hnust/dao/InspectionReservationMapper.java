package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.InspectionReservation;

public interface InspectionReservationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InspectionReservation record);

    int insertSelective(InspectionReservation record);

    InspectionReservation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionReservation record);

    int updateByPrimaryKey(InspectionReservation record);
    
	List<InspectionReservation> selectInspectionReservation(InspectionReservation inspectionReservation);

	int selectInspectionReservationCount(InspectionReservation inspectionReservation);

	InspectionReservation selectInspectionReservationById(@Param("projectNoId")String projectNoId);
	
	InspectionReservation selectInspectionReservationByNo(@Param("projectNo")String projectNo);
	
	int selectInspectionReservationCountByProjectNo(@Param("projectNo")String projectNo);
	
	/**
	 * 根据项目号查询查询
	 * @Title selectInspectionList 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<InspectionReservation>
	 */
	List<InspectionReservation> selectInspectionList(@Param("projectNo")String projectNo);
	/**
	 * 
	 * @Title:IInspectionReservationService
	 * @Description:质检地图
	   @author wangyang
	 * @param task
	 * @return List<InspectionReservation>
	 * @throws
	 */
	List<InspectionReservation> getQualityInspectionMap(
			InspectionReservation task);
	/**
	 * 
	 * @Title:IInspectionReservationService
	 * @Description:获取质检检验时间表
	 *    @author wangyang
	 * @param task
	 * @return List<InspectionReservation>
	 * @throws
	 */
	List<InspectionReservation> getQualityInspectionTime(
			InspectionReservation task);
    /**
     * 
     * @Title:InspectionReservationMapper
     * @Description:查询是否存在去工厂重复
       @author wangyang
     * @param inspectionReservation
     * @return InspectionReservation
     * @throws
     */
	InspectionReservation getOne(InspectionReservation inspectionReservation);
	/**
	 * 
	 * @Title:IInspectionReservationService
	 * @Description:查询
	   @author wangyang
	 * @param monday
	 * @return List<InspectionReservation>
	 * @throws
	 */
	List<InspectionReservation> getAll(InspectionReservation inspection);
    /**
     * 
     * @Title:InspectionReservationMapper
     * @Description:获取最近检验计划
       @author wangyang
     * @param projectNo
     * @return List<InspectionReservation>
     * @throws
     */
	List<InspectionReservation> getAllInspection(@Param("projectNo")String projectNo);
	/**
	 * 
	 * @Title:IInspectionReservationService
	 * @Description:获取全部安排
	   @author wangyang
	 * @param monday
	 * @return List<InspectionReservation>
	 * @throws
	 */
	List<InspectionReservation> getAllArrangements(
			InspectionReservation inspection);
	/**
	 * 查询数据统计
	 * @param inspection
	 * @return
	 */
    List<InspectionReservation> getAllQueryStatistics(InspectionReservation inspection);
	/**
	 * 查询数据统计
	 * @param inspection
	 * @return
	 */
	List<InspectionReservation> getAllQueryStatistics1(InspectionReservation inspection);

	List<InspectionReservation>  getAllQueryStatisticsCompany(InspectionReservation inspection);
}