package com.cn.hnust.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.cn.hnust.component.RpcHelper;
import com.cn.hnust.pojo.FactoryQualityInspectionVideo;
import com.cn.hnust.pojo.ProjectComplaintQuery;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.pojo.ShippingConfirmation;
import com.cn.hnust.pojo.ShippingConfirmationQuery;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.FactoryQualityInspectionVideoService;
import com.cn.hnust.service.IQualityReportService;
import com.cn.hnust.service.ProjectComplaintService;
import com.cn.hnust.service.ShippingConfirmationService;


public class ShippingConfirmationThread extends Thread{
	 ShippingConfirmationService shippingConfirmationService;
	
	 ProjectComplaintService projectComplaintService;
	
	 IQualityReportService qualityReportService;
	
	  FactoryQualityInspectionVideoService factoryQualityInspectionVideoService;
	 
	private static final int SAMPLE = 0;           //样品
    private static final int PRODUCT = 1;          //大货
    private static final int LAST = 3;             //终期检验
    private static final int PRODUCT_VIDEO=4;  //产品360视频
   
   
	User user;
	public ShippingConfirmationThread(User user){
		this.user =user ;
		
	};
	
	public ShippingConfirmationThread(
			User user,
			ShippingConfirmationService shippingConfirmationService,
			ProjectComplaintService projectComplaintService,
			IQualityReportService qualityReportService,
			FactoryQualityInspectionVideoService factoryQualityInspectionVideoService) {
		this.user =user ;
		this.shippingConfirmationService =shippingConfirmationService ;
		this.projectComplaintService =projectComplaintService ;
		this.qualityReportService =qualityReportService ;
		this.factoryQualityInspectionVideoService =factoryQualityInspectionVideoService ;
	}

	@Override
	public void run() {
		ShippingConfirmationQuery shippingConfirmationQuery = new ShippingConfirmationQuery();
		shippingConfirmationQuery.setPageSize(0);
		shippingConfirmationQuery.setPageNumber(100);
		shippingConfirmationQuery.setRoleNo(user.getRoleNo());
		shippingConfirmationQuery.setUserName(user.getUserName());
		shippingConfirmationQuery.setInputKey("");
		//判断样品还是大货
		int sampleOrProduct = -1;
		//判断是否完成
		int isComplete = 0;
		
		shippingConfirmationQuery.setSampleOrProduct(sampleOrProduct);
		shippingConfirmationQuery.setIsComplete(isComplete);
        List<ShippingConfirmation> shippings = shippingConfirmationService.selectByProjectNo(shippingConfirmationQuery);
		for (ShippingConfirmation shippingConfirmation : shippings) {
			//查询投诉(该项目历史投诉)
			 String projectNo = shippingConfirmation.getProjectNo();
			 if(projectNo.contains("-")){
				 projectNo = projectNo.split("-")[0];
			 }
			 //根据投诉历史是否完成，判断是否能够出货
			 //查询该项目所有历史投诉
			 ProjectComplaintQuery projectComplaintQuery = new ProjectComplaintQuery();
			 projectComplaintQuery.setInputKey(projectNo);
			 projectComplaintQuery.setRoleNo(100);
			 projectComplaintQuery.setPageNumber(-1);
			 //List<ProjectComplaint> complaintList = projectComplaintService.queryComplaintList(projectComplaintQuery);
			 Boolean isComplaintComplete = true;
			 /*for (ProjectComplaint projectComplaint2 : complaintList) {
				 Date completeTime = projectComplaint2.getCompleteTime();
				 if(completeTime == null){
					 isComplaintComplete = false;
				 }
			 }*/
			 int complaintCount=projectComplaintService.getQueryComplaintCount(projectComplaintQuery);
			 if(complaintCount>0){
				 isComplaintComplete = false;
			 }
			 
			 //查询是否终检没问题
			 Boolean isSampleNoProblem = false;
			 Boolean isProductNoProblem = false;
			 List<QualityReport> reports = qualityReportService.selectByProjectNo(shippingConfirmation.getProjectNo());
			 for (QualityReport qualityReport : reports) {
				 if(qualityReport.getType() == SAMPLE){	 
					 //如果是样品出货，查询出货证明
					 if(shippingConfirmation.getSampleOrProduct() != null && shippingConfirmation.getSampleOrProduct() ==  SAMPLE && (qualityReport.getStatus() == 0 || qualityReport.getStatus() == 1)){
						 isSampleNoProblem = true;
                   }
				 }
				//如果是终期检验
				 if(qualityReport.getType() == LAST && shippingConfirmation.getSampleOrProduct() != null && shippingConfirmation.getSampleOrProduct() ==  PRODUCT && (qualityReport.getStatus() == 0 || qualityReport.getStatus() == 1)){
					 isProductNoProblem = true;
				 }
			 }
            //查询产品360度视频
			 List<FactoryQualityInspectionVideo> videoList = factoryQualityInspectionVideoService.selectByProjectNoAndType(projectNo,PRODUCT_VIDEO);
			//判断是否允许签名
			 Boolean isSign = false;
			 if(shippingConfirmation.getFirstPerson() != null && shippingConfirmation.getSecondPerson() != null 
					 && shippingConfirmation.getThirdPerson() != null && shippingConfirmation.getFourthPerson() != null 
					 && isComplaintComplete == true 						
					 ){	
					 if(shippingConfirmation.getSampleOrProduct() == 1 && (isProductNoProblem == true || (isProductNoProblem == false && StringUtils.isNotBlank(shippingConfirmation.getShipmentAgreement())))){
						 isSign = true;
					 }
					 if((shippingConfirmation.getSampleOrProduct() == 0 && videoList!=null && videoList.size() > 0)){							
						 if((shippingConfirmation.getSampleFileName() != null) || isSampleNoProblem == true){
							 isSign = true;
						 }
					 }
			   }
			 //针对之前的出货单，不进行拦截（id 239）
			 if(shippingConfirmation.getId()<=239){
				 isSign = true;
			 }
			 shippingConfirmation.setIsSign(isSign);
		}
		int totalCount = shippingConfirmationService.count(shippingConfirmationQuery);
		super.run();	
	}
}
