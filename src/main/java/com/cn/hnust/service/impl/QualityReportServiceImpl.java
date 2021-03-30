package com.cn.hnust.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.cn.hnust.pojo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.ProjectFactoryMapper;
import com.cn.hnust.dao.ProjectMapper;
import com.cn.hnust.dao.ProjectTaskMapper;
import com.cn.hnust.dao.QualityReportMapper;
import com.cn.hnust.dao.TrackMapper;
import com.cn.hnust.dao.TrackPlaceMapper;
import com.cn.hnust.service.IQualityReportService;
import com.cn.hnust.util.DateFormat;
@Service
public class QualityReportServiceImpl implements IQualityReportService {

	@Resource
	private QualityReportMapper mapper;
	@Resource
	private ProjectMapper projectMapper;
	@Resource
	private ProjectTaskMapper projectTaskMapper;
	@Resource
	private TrackMapper trackMapper;
	@Resource
	private TrackPlaceMapper trackPlaceMapper;
	@Resource
	private ProjectFactoryMapper projectFactoryMapper;
	
	private static final int SAMPLE_INSPECTION = 0;  //样品检验
	private static final int LAST_INSPECTION = 3;    //终检
	private static final int NO_ISSUE_STATUS = 0;    //没问题
	private static final int ISSUE_DEAL_STATUS = 1;  //有问题已处理
	private static final int ALL_RIGHT = 1;          //项目是否所有都完成
	private static final int FINISH = 2;             //大货完结
	private static final int SAMPLE_FINISH = 6;      //样品完结
	private static final int NO_FINISH = 0;          //未完成
	
	
	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		trackMapper.deleteByReportId(id);
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(QualityReport record) {
		return mapper.insert(record);
	}

	
	//上传质检报告的时候判断是否是终检，并且所有已完成（如果终检无问题自动完结项目，其他问题则不更新状态）	
	@Transactional
	@Override
	public int insertSelective(QualityReport record) throws ParseException {
		
		//查询所有合同是否已完结
		List<ProjectFactory> factoryList = projectFactoryMapper.selectByProjectNo(record.getProjectNo());
		Integer isSampleAllRight = ALL_RIGHT;
		Integer isProductAllRight = ALL_RIGHT;
		for (ProjectFactory projectFactory : factoryList) {
			if(projectFactory.getSampleDate() != null && projectFactory.getSampleFinishTime() == null){
				isSampleAllRight = NO_FINISH;
			}
			if(projectFactory.getDeliveryDate() != null && projectFactory.getProductFinishTime() == null){
				isProductAllRight = NO_FINISH;
			}			
		}
		
		
		//如果是终检
		if(record.getType() == LAST_INSPECTION && (record.getStatus() == NO_ISSUE_STATUS || record.getStatus() == ISSUE_DEAL_STATUS) &&  isProductAllRight == ALL_RIGHT){
			Project project = projectMapper.selectProjctDetails(record.getProjectNo());
			project.setFinishTime(new Date());
			project.setProjectStatus(FINISH);
			projectMapper.updateByPrimaryKeySelective(project);
			
			//当终检没问题的时候需要任务项目组人员给工厂打分
			List<ProjectTask> projectTaks = new ArrayList<ProjectTask>();
			ProjectTask task = new ProjectTask();
			task.setProjectNo(project.getProjectNo());
			task.setInitiator("system");			
			task.setDescription(project.getProjectNo()+"项目已完结，请给工厂打分。");
			task.setFinishTime(DateFormat.addDays(new Date(), 3));
			task.setTaskStatus("0");
			task.setTaskType("6");
			task.setTaskUrl(null);
        	task.setStartTime(new Date());
        	task.setCreateTime(new Date());
        	task.setAccepter(project.getSellName());
        	projectTaks.add(task);
        	if(StringUtils.isNotBlank(project.getPurchaseName())){       		
        		ProjectTask task1 = (ProjectTask)task.clone();
        		task1.setAccepter(project.getPurchaseName());
        		projectTaks.add(task1);
        	}
        	ProjectTask task2 = (ProjectTask)task.clone();
        	task2.setAccepter(record.getUser());
        	projectTaks.add(task2);
        	if(projectTaks!=null&&projectTaks.size()>0){
        		projectTaskMapper.insertBatch(projectTaks);
        	}        	
		}else if(record.getType() == SAMPLE_INSPECTION && (record.getStatus() == NO_ISSUE_STATUS || record.getStatus() == ISSUE_DEAL_STATUS) && isSampleAllRight == ALL_RIGHT){
			Project project = projectMapper.selectProjctDetails(record.getProjectNo());
			project.setSampleFinishTime(new Date());
			project.setProjectStatus(SAMPLE_FINISH);
			projectMapper.updateByPrimaryKeySelective(project);
		}
		
		int count = mapper.insertSelective(record);
		
		//根据检验地点保存到检验行踪
		if(StringUtils.isNotBlank(record.getPlace())){
			String place = record.getPlace();
			String[] split = place.split(",");
			Track track = new Track();
			track.setCreateTime(new Date());
			track.setOutDate(record.getCheckDate());
			track.setReportId(record.getId());
			track.setUserName(record.getUser());
			trackMapper.insertSelective(track);
			if(split.length>0){
				List<TrackPlace> placeList = new ArrayList<TrackPlace>();
				for (int i = 0; i < split.length; i++) {
					TrackPlace trackPlace = new TrackPlace();
					trackPlace.setTrackId(track.getId());
					trackPlace.setPlace(split[i]);
					//如果该地区已录入，则不重复录入
					int placeCount = trackPlaceMapper.selectByDate(record.getUser(), place, DateFormat.date2String(record.getCheckDate()));
					if(placeCount==0){
						placeList.add(trackPlace);
					}					
				}
				if(placeList!=null&&placeList.size()>0){
					trackPlaceMapper.insertBatch(placeList);
				}				
		    }
		}	
		return count;
	}

	@Override
	public QualityReport selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(QualityReport record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(QualityReport record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public QualityReport selectByPrimaryKey(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<QualityReport> selectByProjectNo(String projectNo) {
		return mapper.selectByProjectNo(projectNo);
	}

	@Override
	public QualityReport selectNewestReportByProjectNo(String projectNo) {
		return mapper.selectNewestReportByProjectNo(projectNo);
	}

	@Override
	public List<QualityReport> selectAllReport(QualityReportQuery qualityReportQuery) {

		return mapper.selectAllReport(qualityReportQuery);
	}

	@Override
	public int totalReports(QualityReportQuery qualityReportQuery) {
		return mapper.totalReports(qualityReportQuery);
	}

	@Override
	public List<QualityReport> selectByProjectNoAndType(String projectNo,
			Integer type) {
		return mapper.selectByProjectNoAndType(projectNo, type);
	}

	@Override
	public int inspectionPlanningCondition(String projectNo) {
		
		return mapper.inspectionPlanningCondition(projectNo);
	}

	@Override
	public QualityReport selectProjectNo(String projectNo) {
		
		return mapper.selectProjectNo(projectNo);
	}

	@Override
	public int getReport(QualityReport report) {
		
		return mapper.getReport(report);
	}

	@Override
	public List<QualityReport> selectByProjectNo1(String projectNo,
			String userName) {
		
		return mapper.selectByProjectNo1(projectNo,userName);
	}

    @Override
    public List<ErpQualityReport> queryErpQualityReport(String projectNo) {
        return mapper.queryErpQualityReport(projectNo);
    }

    @Override
    public List<ErpQualityReport> queryAllQualityReport(String projectNo) {
        return mapper.queryAllQualityReport(projectNo);
    }

    @Override
    public int insertErpQualityReport(List<ErpQualityReport> reportList) {
        return mapper.insertErpQualityReport(reportList);
    }


}
