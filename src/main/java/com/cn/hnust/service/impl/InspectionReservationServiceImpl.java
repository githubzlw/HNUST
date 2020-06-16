package com.cn.hnust.service.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.InspectionPlanMapper;
import com.cn.hnust.dao.InspectionReservationMapper;
import com.cn.hnust.dao.ProjectInspectionReportMapper;
import com.cn.hnust.excel.ReadInspectionExcel;
import com.cn.hnust.pojo.InspectionPlan;
import com.cn.hnust.pojo.InspectionReservation;
import com.cn.hnust.pojo.ProjectInspectionReport;
import com.cn.hnust.service.IInspectionReservationService;

@Service
public class InspectionReservationServiceImpl implements IInspectionReservationService {

	@Autowired
	private InspectionReservationMapper inspectionReservationMapper;
    @Autowired
    private ProjectInspectionReportMapper projectInspectionReportMapper;
    @Autowired
    private InspectionPlanMapper inspectionPlanMapper;

	@Transactional
	@Override
	public void addInspectionReservation(InspectionReservation inspectionReservation) {
		inspectionReservationMapper.insertSelective(inspectionReservation);
		//查询是否已经有解析的检验计划，如果已经有了，不重复解析
		List<InspectionPlan> plans = inspectionPlanMapper.selectByProjectNo(inspectionReservation.getProjectNo(), null);
		if(plans==null){
			List<ProjectInspectionReport> reports = projectInspectionReportMapper.selectInspectionReportByProjectNo(inspectionReservation.getProjectNo());
			if(reports!=null && reports.size()>0){
				String reportName = reports.get(0).getReportName();
				if(StringUtils.isNotBlank(reportName)){
					try {
						reportName = URLEncoder.encode(reportName, "utf-8");
						ReadInspectionExcel read = new ReadInspectionExcel("http://117.144.21.74:33168/upload/po/upload/JIANYANJIHUAZJ/"+reportName);
						List<InspectionPlan> excelContents = read.readExcelContent();
						excelContents.forEach(plan->{
							plan.setProjectNo(inspectionReservation.getProjectNo());
							plan.setCreateDate(reports.get(0).getCreateDate());
						});
						if(excelContents!=null&&excelContents.size()>0){
							inspectionPlanMapper.insertBatch(excelContents);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	@Override
	public List<InspectionReservation> selectInspectionReservation(InspectionReservation inspectionReservation) {
		return inspectionReservationMapper.selectInspectionReservation(inspectionReservation);
	}
	@Override
	public int selectInspectionReservationCount(InspectionReservation inspectionReservation) {
		return inspectionReservationMapper.selectInspectionReservationCount(inspectionReservation);
	}
	@Override
	public InspectionReservation selectInspectionReservationById(String projectNoId) {
		return inspectionReservationMapper.selectInspectionReservationById(projectNoId);
	}
	@Override
	public InspectionReservation selectInspectionReservationByNo(String projectNo) {
		return inspectionReservationMapper.selectInspectionReservationByNo(projectNo);
	}
	@Override
	public void updateInspectionReservation(InspectionReservation inspectionReservation) {
		inspectionReservationMapper.updateByPrimaryKeySelective(inspectionReservation);
	}
	@Override
	public List<InspectionReservation> selectInspectionList(String projectNo) {
		return inspectionReservationMapper.selectInspectionList(projectNo);
	}
	@Override
	public List<InspectionReservation> getQualityInspectionMap(
			InspectionReservation task) {

		return inspectionReservationMapper.getQualityInspectionMap(task);
	}
	@Override
	public List<InspectionReservation> getQualityInspectionTime(
			InspectionReservation task) {

		return inspectionReservationMapper.getQualityInspectionTime(task);
	}
	@Override
	public InspectionReservation getOne(
			InspectionReservation inspectionReservation) {

		return inspectionReservationMapper.getOne(inspectionReservation);
	}
	@Override
	public List<InspectionReservation> getAll(InspectionReservation inspection,String num) {
		List<InspectionReservation> list=new ArrayList<InspectionReservation>();
		if(!"3".equalsIgnoreCase(num)){
			list=inspectionReservationMapper.getAll(inspection);
		}else{

			list=inspectionReservationMapper.getAllArrangements(inspection);
		}
		return list;
	}
	@Override
	public List<InspectionReservation> getAllInspection(String projectNo) {

		return inspectionReservationMapper.getAllInspection(projectNo);
	}


	@Override
    public List<InspectionReservation> getAllQueryStatistics(InspectionReservation inspection,int i,String num) {
		if("3".equalsIgnoreCase(num)){
			inspection.setTaskStatus(num);
		}
		List<InspectionReservation> list=null;
		if(i==1){
			list=inspectionReservationMapper.getAllQueryStatistics(inspection);
		}else if(i==2){
			list=inspectionReservationMapper.getAllQueryStatistics1(inspection);
		}


		return list;
    }

    @Override
	public int selectInspectionReservationCountByProjectNo(String projectNo) {
		return inspectionReservationMapper.selectInspectionReservationCountByProjectNo(projectNo);
	}

}
