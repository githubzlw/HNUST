package com.cn.hnust.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.component.RpcSendNoticeToKuai;
import com.cn.hnust.component.SynShippingConfirmation;
import com.cn.hnust.dao.IUserDao;
import com.cn.hnust.dao.ProjectComplaintMapper;
import com.cn.hnust.dao.ProjectMapper;
import com.cn.hnust.dao.ProjectTaskMapper;
import com.cn.hnust.dao.QualityReportMapper;
import com.cn.hnust.dao.ShippingConfirmationMapper;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectComplaint;
import com.cn.hnust.pojo.ProjectComplaintQuery;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.pojo.ShippingConfirmation;
import com.cn.hnust.pojo.ShippingConfirmationQuery;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.ShippingConfirmationService;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.PropertiesUtils;

@Service
public class ShippingConfirmationServiceImpl implements ShippingConfirmationService {
    @Autowired
	private ShippingConfirmationMapper shippingConfirmationMapper;
    @Autowired
    private ProjectComplaintMapper projectComplaintMapper;
    @Autowired
    private QualityReportMapper qualityReportMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectTaskMapper projectTaskMapper;
    @Autowired
    private IUserDao userDao;
	
    private static final int SAMPLE = 0;
    private static final int PRODUCT = 1;
    private static final int COMPLETE = 1;
    private static final int NOTCOMPLETE = 0;
    private static final int TYPE = 0;   //普通出货单
    private static final int BOSS_COMFIRM = 1;      //需要老板确认
    private static final int NO_BOSS_COMFIRM = 0;   //不需要老板确认
    
    private static final int DELETE_TYPE = 2;  //用于删除出运单
    private static final int UPDATE_TYPE = 1;  //用于更新出运单
    
    private static PropertiesUtils reader = new PropertiesUtils("config.properties");
    
	@Override
	public int deleteByPrimaryKey(Integer id) {
		//删除电子出货单的数据同时删除出运单记录
		ShippingConfirmation shippingConfirmation = shippingConfirmationMapper.selectByPrimaryKey(id);
		SynShippingConfirmation.sendRequest(shippingConfirmation, DELETE_TYPE);
		return shippingConfirmationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ShippingConfirmation record) {
		if(record!=null){
			String serialNumber1=shippingConfirmationMapper.getSerialNumber(record.getProjectNo());
            int count=0;
			if(serialNumber1!=null&&!"".equalsIgnoreCase(serialNumber1)) {
                count = Integer.parseInt(serialNumber1.split("QR")[1]);
             }

            count++;
            String serialNumber = record.getProjectNo() + "QR" + count;
            record.setSerialNumber(serialNumber);
            record.setCreateTime(new Date());
			//查询项目是否是A\B级项目返单，判断是否需要姜工审批
			Integer isQualityLeaderConfirm = 0;
			String projectNo = record.getProjectNo();
			Project project = projectMapper.selectProjectByProjectNo(projectNo);
			Integer plantAnalysis = project.getPlantAnalysis();
			if(plantAnalysis != null && (plantAnalysis == 1 || plantAnalysis == 2 || !projectNo.contains("-"))){
				isQualityLeaderConfirm = 1;
			}
			record.setIsQualityLeaderConfirm(isQualityLeaderConfirm);
			
		}		
		return shippingConfirmationMapper.insertSelective(record);
	}

	@Override
	public ShippingConfirmation selectByPrimaryKey(Integer id) {
		return shippingConfirmationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ShippingConfirmation record) throws ParseException {
		
		//判断当前出货次数
		int count = shippingConfirmationMapper.selectCountByProjectNoAndType(record.getProjectNo(),PRODUCT);
		//获取项目金额
		Double amount = 0.00;
		String projectAmount = record.getProjectAmount();
		if(projectAmount!=null){
			//String regEx="[^0-9]";  
			//Pattern p = Pattern.compile(regEx);  
			//Matcher m = p.matcher(projectAmount);
			//projectAmount = m.replaceAll("").trim();
			amount = Double.parseDouble(projectAmount);
		}
		  //判断是否需要老板签字
		 //1、非塑料类样品 出货   2、金额大于 1万美元的第一次 大货出货	
		if(((record.getIsPlastic() == 0 && record.getSampleOrProduct() != null && record.getSampleOrProduct() == SAMPLE) || (record.getSampleOrProduct() != null && record.getSampleOrProduct() == PRODUCT && count == 1 &&!record.getProjectNo().contains("-"))) &&  amount > 1){
	    	 if((record.getIsPlastic() == 0 && record.getSampleOrProduct() == SAMPLE)&&(amount > 1)){
	    		 record.setIsBossConfirm(1);
	    		 
	    		 record.setNonPlastic(1);
				}
	    	 if(((record.getSampleOrProduct() == PRODUCT) && count == 1)&&!record.getProjectNo().contains("-")&&(amount > 1)){
	    		 record.setIsBossConfirm(1);
	    		
	    		 record.setFirstShipment(1);
				}
	    	 if(amount > 1.5){
	    		 record.setIsBossConfirm(1);
	    		 record.setAmountMoney(1);
	    		
				}
	    	 record.setIsBossConfirm(BOSS_COMFIRM);		
		 }else{
			 record.setIsBossConfirm(NO_BOSS_COMFIRM);	
		 }
	     
		
		//需要销售、采购、质检总监、采购总经理确认
		if(record!=null && StringUtils.isNotBlank(record.getSalesConfirm()) 
				&& (StringUtils.isNotBlank(record.getPurchaseConfirm()) 
						|| StringUtils.isBlank(record.getPurchaseName()))
				&& (StringUtils.isNotBlank(record.getPurchaseLeaderConfirm())
						|| record.getIsQualityLeaderConfirm() == 0) 
						&& (StringUtils.isNotBlank(record.getQualityLeaderConfirm())
						&& ((record.getPurchaseLeaderConfirmId()==1) ||(StringUtils.isBlank(record.getPurchaseLeaderConfirm())))
						&&((record.getQualityLeaderConfirmId()==1)||(StringUtils.isBlank(record.getQualityLeaderConfirm()) )))	 	
		){	
			 //判断是否需要老板签字
			 //如果老板已签字，则同意出货
			 if(record.getIsBossConfirm() == BOSS_COMFIRM){
				 if(StringUtils.isNotBlank(record.getBossConfirm()) &&record.getBossConfirmId()==1){
					 record.setIsComplete(COMPLETE);
				 }else{
					 record.setIsComplete(NOTCOMPLETE);
				 }			 
			 }else{
				 record.setIsComplete(COMPLETE);
			 }
			 //更新的同时同步出运联系单
			 SynShippingConfirmation.sendRequest(record, UPDATE_TYPE);
		}
		ProjectTask task2=projectTaskMapper.getDrawingAlteration(record.getProjectNo());
		 if(task2!=null&&(amount > 1)){
			 record.setIsBossConfirm(1);
			 
			 record.setProductChanges(1); 
			 record.setIsBossConfirm(BOSS_COMFIRM);	
		 }
		 Project project1 = projectMapper.selectProjctDetails(record.getProjectNo());
		 if((project1.getCustomerGrade()==1||project1.getCustomerGrade()==2||project1.getPlantAnalysis()==1||project1.getPlantAnalysis()==2)&&(amount > 1)){
			 record.setIsBossConfirm(1);
			 
			 record.setGrade(1);
			 record.setIsBossConfirm(BOSS_COMFIRM);	
		 }
		//如果已经全部录入，需要通知采购、质检签名
		if(record.getIsSendConfirmTask() != null && record.getIsSendConfirmTask() == 0 && StringUtils.isNotBlank(record.getFirstPerson()) 
				&& StringUtils.isNotBlank(record.getSecondPerson()) && StringUtils.isNotBlank(record.getThirdPerson()) 
				&& StringUtils.isNotBlank(record.getFourthPerson())){
			 //根据投诉历史是否完成，判断是否能够出货
			 //查询该项目所有历史投诉
			 ProjectComplaintQuery projectComplaintQuery = new ProjectComplaintQuery();
			 projectComplaintQuery.setInputKey(record.getProjectNo());
			 projectComplaintQuery.setRoleNo(100);
			 projectComplaintQuery.setPageNumber(-1);
			 List<ProjectComplaint> complaintList = projectComplaintMapper.queryComplaintList(projectComplaintQuery);
			 Boolean isComplaintComplete = true;
			 for (ProjectComplaint projectComplaint2 : complaintList) {
				 Date completeTime = projectComplaint2.getCompleteTime();
				 if(completeTime == null){
					 isComplaintComplete = false;
				 }
				
				 record.setIsBossConfirm(1);
				 record.setAmountMoney(1);
				 record.setComplaint(1); 
				 record.setIsBossConfirm(BOSS_COMFIRM);	
				
			 }
			 
			 //查询质检报告是否含有样品检验
			 Boolean isSampleCheck = false;
			 //最后一个上传质检报告的发送任务
			 String inspection = null;
			 List<QualityReport> reports = qualityReportMapper.selectByProjectNo(record.getProjectNo());
			 int i=0;
			 for (QualityReport qualityReport : reports) {
				 if(qualityReport.getType() == SAMPLE){
					 isSampleCheck = true;
				 }
				 //取最后一个质检报告录入人
				 if(i==0){
					 inspection = qualityReport.getUser();
				 }
				 i++;
			 }
			 //如果样品出货无样品检验，未上传样品报告，则不允许签名
			 if((record.getSampleOrProduct() == 0 && StringUtils.isNotBlank(record.getSampleFileName()) && isSampleCheck == false) || isComplaintComplete == false){
				 
			 }else{
					List<ProjectTask> projectTaks = new ArrayList<ProjectTask>();
					Project project = projectMapper.selectProjctDetails(record.getProjectNo());
					ProjectTask task = new ProjectTask();
					task.setProjectNo(record.getProjectNo());
					task.setInitiator("system");			
					task.setDescription("准予出货确认单 "+record.getSerialNumber()+"需要填写，请点我跳转填写");
					task.setFinishTime(DateFormat.addDays(new Date(), 3));
					task.setTaskStatus("0");
					task.setTaskType("7");
					task.setTaskUrl(null);
		        	task.setStartTime(new Date());
		        	task.setCreateTime(new Date());
		        	task.setReturnUrl("/complaint/detail?id="+record.getId());
		        	if(StringUtils.isNotBlank(project.getPurchaseName())){
		        		task.setAccepter(project.getPurchaseName());
		        		projectTaks.add(task);
		        		User sendUser = userDao.selectUserByName(project.getPurchaseName());
		        		//钉钉任务提醒
		            	if(sendUser!=null){
		            		task.setDingTalkId(sendUser.getDingTalkId());
		    				RpcSendNoticeToKuai.sendRequest(task);
		    			}
		        	}
		        	if(StringUtils.isNotBlank(inspection)){       		
		        		ProjectTask task1 = (ProjectTask)task.clone();
		        		task1.setAccepter(inspection);
		        		projectTaks.add(task1);
		        		User sendUser = userDao.selectUserByName(project.getPurchaseName());
		        		//钉钉任务提醒
		            	if(sendUser!=null){
		            		task.setDingTalkId(sendUser.getDingTalkId());
		    				RpcSendNoticeToKuai.sendRequest(task);
		    			}
		        	}
		        	if(projectTaks!=null&&projectTaks.size()>0){
		        		projectTaskMapper.insertBatch(projectTaks);
		        	}        			        	
		        	
			 }
			 
		}
		
		return shippingConfirmationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int selectCountByProjectNo(String projectNo,int type) {
        String serialNumber1=shippingConfirmationMapper.getSerialNumber(projectNo);
        int count=0;
        if(serialNumber1!=null&&!"".equalsIgnoreCase(serialNumber1)) {
            count = Integer.parseInt(serialNumber1.split("QR")[1]);
        }

		return count;
	}

	@Override
	public List<ShippingConfirmation> selectByProjectNo(ShippingConfirmationQuery shippingConfirmationQuery) {
		return shippingConfirmationMapper.selectByProjectNo(shippingConfirmationQuery);
	}

	@Override
	public int count(ShippingConfirmationQuery shippingConfirmationQuery) {
		return shippingConfirmationMapper.count(shippingConfirmationQuery);
	}

	@Override
	public int selectCountByProjectNoAndType(String projectNo,Integer sampleOrProduct) {
		return shippingConfirmationMapper.selectCountByProjectNoAndType(projectNo, sampleOrProduct);
	}

	@Override
	public ShippingConfirmation selectBySerialNumber(String serialNumber) {
		return shippingConfirmationMapper.selectBySerialNumber(serialNumber);
	}

	@Override
	public void updateByPrimaryKeySelective1(ShippingConfirmation projectComplaint) {
		shippingConfirmationMapper.updateByPrimaryKeySelective(projectComplaint);
	}

}
