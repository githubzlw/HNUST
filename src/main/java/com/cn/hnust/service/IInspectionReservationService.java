package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.InspectionReservation;

public interface IInspectionReservationService {

	public void addInspectionReservation(InspectionReservation inspectionReservation);
	
	public List<InspectionReservation> selectInspectionReservation(InspectionReservation inspectionReservation);
	
	public int selectInspectionReservationCount(InspectionReservation inspectionReservation);

	public InspectionReservation selectInspectionReservationById(String projectNoId);
	
	public InspectionReservation selectInspectionReservationByNo(String projectNo);
	int selectInspectionReservationCountByProjectNo(String projectNo);

	public void updateInspectionReservation(InspectionReservation inspectionReservation);
	
	
	/**
	 * 根据项目号查询查询
	 * @Title selectInspectionList 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<InspectionReservation>
	 */
	List<InspectionReservation> selectInspectionList(String projectNo);
/**
 * 
 * @Title:IInspectionReservationService
 * @Description:质检地图
   @author wangyang
 * @param task
 * @return List<InspectionReservation>
 * @throws
 */
	public List<InspectionReservation> getQualityInspectionMap(
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
public List<InspectionReservation> getQualityInspectionTime(
		InspectionReservation task);
/**
 * 
 * @Title:IInspectionReservationService
 * @Description:查询是否存在其他工厂检验
   @author wangyang
 * @param inspectionReservation
 * @return InspectionReservation
 * @throws
 */
public InspectionReservation getOne(InspectionReservation inspectionReservation);
/**
 * 
 * @Title:IInspectionReservationService
 * @Description:查询
   @author wangyang
 * @param monday
 * @return List<InspectionReservation>
 * @throws
 */
public List<InspectionReservation> getAll(InspectionReservation inspection,
		String num);
/**
 * 
 * @Title:IInspectionReservationService
 * @Description:获取最近1星期的全部预检验
   @author wangyang
 * @param projectNo
 * @return List<InspectionReservation>
 * @throws
 */
public List<InspectionReservation> getAllInspection(String projectNo);

	/**
	 * 查询数据统计
	 * @param inspection
	 * @return
	 */

    List<InspectionReservation> getAllQueryStatistics(InspectionReservation inspection,int i,String num);
}
