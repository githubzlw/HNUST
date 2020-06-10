package com.cn.hnust.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectERP;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.ProjectSchedule;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IProjectERPService;
import com.cn.hnust.service.IProjectScheduleService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.service.ItemCaseERPService;
import com.cn.hnust.service.ProjectFactoryService;
import com.cn.hnust.service.impl.task.ProjectDateTask;
import com.cn.hnust.util.Client;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.HttpClient;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.SerializeUtil;

/**
 * CRM项目Controller
 * 
 * @author Administrator
 *
 */
@Controller
@Api(value = "api接口", description="同步erp数据接口")
@RequestMapping("/project")
public class ProjectERPController {

	@Autowired
	private IProjectERPService projectERPService;
	
	@Autowired 
	private IProjectService projectService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ProjectDateTask projectDateTask;
	
	@Autowired
	private IProjectScheduleService projectScheduleService;
	
	@Autowired
	private ItemCaseERPService itemCaseERPService;
	
	@Autowired
	private ProjectFactoryService projectFactoryService;
	
	@Autowired
	private IProjectTaskService projectTaskService;
	
	
	private static final Log LOG = LogFactory.getLog(ProjectERPController.class);
	
	/**
	 * 废弃 2018.12.20
	 * 同步ERP数据(项目信息)
	 * 
	 * @param request
	 * @param response
	 */
//	@RequestMapping(value = "/synchERPProject", method = RequestMethod.POST)
	@ResponseBody
	public String updateCRMProject(HttpServletRequest request,HttpServletRequest response,@RequestParam Map<String,String> map)
			throws Exception {
		 String jsonStr = map.get("EmailClient");
		  
//		    BufferedReader reader = null;
//		    String jsonStr = "";
//			reader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\1.text"));
//			String tempString = "";
//
//			int line = 1;
//		
//			while ((tempString = reader.readLine()) != null) {
//				jsonStr+=tempString;
//				line++;
//			}
		
		 JSONArray json = JSONArray.fromObject(jsonStr);
		 // 首先把字符串转成 JSONArray  对象
		 if(json.size()>0){
		  for(int i=0;i<json.size();i++){
		    JSONObject obj = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		    String projectNo = (String) obj.get("caseNo");
		    String projectNameC=""; 
		    
		    if(obj.get("projectDescc")!="" && obj.get("projectDescc")!=null){
		    	projectNameC = obj.get("projectDescc").toString();
		    }
		    
		    String projectNameE="";
		    if(obj.get("projectDesce")!="" && obj.get("projectDesce")!=null){
		    	projectNameE =obj.get("projectDesce").toString();
		    }
		    
		    String zhijian1="";
		    if(obj.get("zhijian1")!="" && obj.get("zhijian1")!=null){
		    	zhijian1 = obj.get("zhijian1").toString();
		    }
            
		    String zhijian2="";
		    if(obj.get("zhijian2")!="" && obj.get("zhijian2")!=null){
		    	zhijian2 = obj.get("zhijian2").toString();
		    }
	        
		    String customerManager="";
		    if(obj.get("customerManager")!="" && obj.get("customerManager")!=null){
		    	customerManager =obj.get("customerManager").toString();
		    }
		    
		    String merchandManager1="";
		    if(obj.get("merchandManager1")!="" && obj.get("merchandManager1")!=null){
		    	merchandManager1 = obj.get("merchandManager1").toString();
		    }
		    
		    String merchandManager2="";
		    if(obj.get("merchandManager2")!="" && obj.get("merchandManager2")!=null){
		    	merchandManager2 =obj.get("merchandManager2").toString();
		    }
            
		    String engineer1="";
		    if(obj.get("engineer1")!="" && obj.get("engineer1")!=null){
		    	engineer1 = obj.get("engineer1").toString();
		    }
		    
		    String engineer2="";
		    if(obj.get("engineer2")!="" && obj.get("engineer2")!=null){
		    	engineer2 =obj.get("engineer2").toString();
		    }
		    
		    String engineer3="";
		    if(obj.get("engineer1")!="" && obj.get("engineer3")!=null){
		    	engineer3 = obj.get("engineer3").toString();
		    } 
		    
		    String companyName="";
		    if(obj.get("factoryName")!="" && obj.get("factoryName")!=null){
		    	companyName = obj.get("factoryName").toString();
		    } 
		    
		    String plantAnalysis="";
		    if(obj.get("plantAnalysis")!="" && obj.get("plantAnalysis")!=null){
		    	plantAnalysis = obj.get("plantAnalysis").toString();
		    } 
		    
		    String projectMaterialProperties="";
		    if(obj.get("projectMaterialProperties")!="" && obj.get("projectMaterialProperties")!=null){
		    	projectMaterialProperties = obj.get("projectMaterialProperties").toString();
		    } 
		    
		    String dateSample="";
		    if(obj.get("dateSample")!="" && obj.get("dateSample")!=null){
		    	dateSample = obj.get("dateSample").toString();
		    } 
		    	    
		    String completionTime="";
		    if(obj.get("completionTime")!="" && obj.get("completionTime")!=null){
		    	completionTime = obj.get("completionTime").toString();
		    } 
		    
		    String dateSampleUploading="";
		    if(obj.get("dateSampleUploading")!="" && obj.get("dateSampleUploading")!=null){
		    	dateSampleUploading = obj.get("dateSampleUploading").toString();
		    } 
		    
		    String moneyDate="";
    		if(obj.get("firstPaymentDate")!="" && obj.get("firstPaymentDate")!=null){
    			moneyDate = obj.get("firstPaymentDate").toString();
		    }
			ProjectERP project = new ProjectERP();
			project.setId(IdGen.uuid());
			project.setProjectNo(projectNo);
			project.setProjectNameC(projectNameC);
	        project.setProjectNameE(projectNameE);
	        project.setZhijian1(zhijian1);
	        project.setZhijian2(zhijian2);
	        project.setCustomerManager(customerManager);
	        project.setMerchandManager1(merchandManager1);
	        project.setMerchandManager2(merchandManager2);
	        project.setEngineer1(engineer1);
	        project.setEngineer2(engineer2);
			project.setEngineer3(engineer3);
			project.setCompanyName(companyName);
			project.setPlantAnalysis(Integer.parseInt(plantAnalysis));
			project.setProjectMaterialProperties(Integer.parseInt(projectMaterialProperties));
			if(StringUtils.isNotBlank(dateSample)){
				Calendar c = Calendar.getInstance();
				c.setTime(DateUtil.StrToDate(dateSample));
				int year=c.get(Calendar.YEAR);
				if(year>2000){
					project.setDateSample(DateUtil.StrToDate(dateSample));
				}
				
			}
			if(StringUtils.isNotBlank(completionTime)){
				Calendar c = Calendar.getInstance();
				c.setTime(DateUtil.StrToDate(completionTime));
				int year=c.get(Calendar.YEAR);
				if(year>2000){
					project.setCompletionTime(DateUtil.StrToDate(completionTime));
				}
			}
			if(StringUtils.isNotBlank(dateSampleUploading)){
				Calendar c = Calendar.getInstance();
				c.setTime(DateUtil.StrToDate(dateSampleUploading));
				int year=c.get(Calendar.YEAR);
				if(year>2000){
//					project.setDateSampleUploading(DateUtil.StrToDate(dateSampleUploading));
				}
			}
			if(StringUtils.isNotBlank(moneyDate)){
				project.setMoneyDate(DateUtil.StrToDate(moneyDate));
			}
			project.setCreateDate(new Date());
			
		    ProjectERP projectErp=projectERPService.selectProjectErpByNo(project.getProjectNo());
			if(projectErp == null){
				projectERPService.addProjectERP(project);
			}else{
				projectERPService.updateProjectErp(project);
			}
			
			//查询插入的项目是否存在
			Project projectInfo=projectService.selectProjctDetails(project.getProjectNo());
			if(projectInfo!=null){//存在就更新
				//Project updateProject=new Project();
				User user=new User();
				User purchaseUser =new User();
				if(StringUtils.isNotBlank(project.getMerchandManager1())){
					user=userService.selectUserByName(project.getMerchandManager1());
					if(user!=null){
						if(projectInfo.getEmailUserId()==0){
							projectInfo.setEmailUserId(user.getId());
						}
			    	}
				}
				if(StringUtils.isNoneBlank(project.getMerchandManager2())){
					purchaseUser=userService.selectUserByName(project.getMerchandManager2());
					if(purchaseUser!=null){
						if(projectInfo.getEmailUserId()==0){
							projectInfo.setPurchaseId(purchaseUser.getId());
						}
			    	}	
				}
				projectInfo.setProjectNo(project.getProjectNo());
				projectInfo.setCompanyName(project.getCompanyName());
				projectInfo.setPlantAnalysis(project.getPlantAnalysis());
				projectInfo.setProjectMaterialProperties(project.getProjectMaterialProperties());
				
				if(projectInfo.getDeliveryDate()==null){//大货交期
					projectInfo.setDeliveryDate(completionTime == null ?  null :DateUtil.StrToDate(completionTime));
				}
				if(projectInfo.getSampleScheduledDate()==null){//样品交期
					projectInfo.setSampleScheduledDate(dateSample == null ? null : DateUtil.StrToDate(dateSample));
				}
			
				if(projectInfo.getDateSampleUploading()==null){//合同上传日期
					projectInfo.setActualStartDate(project.getDateSampleUploading());
				}
				if(projectInfo.getMoneyDate()==null){
					projectInfo.setMoneyDate(DateUtil.StrToDate(moneyDate));
				}
				projectService.updateProjectInfo(projectInfo);
			}else{//不存在就插入操作
				Project insertProject=new Project();
				User user=new User();
				User purchaseUser =new User();
				//1.MerchandManager1 跟单销售,MerchandManager2 采购
				if(StringUtils.isNotBlank(project.getMerchandManager1())){
					user=userService.selectUserByName(project.getMerchandManager1());
					if(user!=null){
						insertProject.setEmailUserId(user.getId());
			    	}
				}
				if(StringUtils.isNotBlank(project.getMerchandManager2())){
					purchaseUser=userService.selectUserByName(project.getMerchandManager2());
					if(purchaseUser!=null){
						insertProject.setPurchaseId(purchaseUser.getId());
			    	}	
				}
				insertProject.setId(IdGen.uuid());
			    insertProject.setProjectNo(project.getProjectNo());
			    insertProject.setProjectName(project.getProjectNameC());
			    insertProject.setDeliveryDate(completionTime == null ?  null :DateUtil.StrToDate(completionTime)); //交期
			    insertProject.setDeliveryStatus(0);
			    insertProject.setWarningStatus(0);
			    insertProject.setImportance(0);
			    insertProject.setFinish(0);
			    insertProject.setStage(0);
			    insertProject.setPoDate(null);  //PO日期
			    insertProject.setActualStartDate(project.getDateSampleUploading());  //开工日期
			    insertProject.setScheduledDate(null);
			    insertProject.setSampleScheduledDate(dateSample == null ? null : DateUtil.StrToDate(dateSample));
			    insertProject.setCompanyName(project.getCompanyName());
			    insertProject.setCreateDate(new Date());
			    insertProject.setPlantAnalysis(project.getPlantAnalysis());
			    insertProject.setZhijian1(zhijian1);
			    insertProject.setZhijian2(zhijian2);
			    
			    insertProject.setProjectMaterialProperties(project.getProjectMaterialProperties());
			    if(StringUtils.isNotBlank(dateSample)){
			    	insertProject.setDateSample(DateUtil.StrToDate(dateSample));
				}
				if(StringUtils.isNotBlank(completionTime)){
					insertProject.setCompletionTime(DateUtil.StrToDate(completionTime));
				}
//				if(StringUtils.isNotBlank(dateSampleUploading)){
//					insertProject.setDateSampleUploading(DateUtil.StrToDate(dateSampleUploading));
//				}
				if(StringUtils.isNotBlank(moneyDate)){
					insertProject.setMoneyDate(DateUtil.StrToDate(moneyDate));
				}
				insertProject.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());  //初始导入正常进行项目
				
				
				//如果交期时间不为空
				//录入首次大货日期，如果存在第一次大货时间，则更新
				if(completionTime != null){

			     }
			
				
			    projectService.addProject(insertProject);
			}
		  }
		 }
		 return "YES";
	}
	
	/**
	 * 同步时间和项目等级
	 * @param request
	 * @param
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value = "/synchUpdateProjectDate")
	@ResponseBody
	public JsonResult updateCRMProject(HttpServletRequest request)
			throws Exception {
		String pro = request.getParameter("projectNo");
		JsonResult jsonResult=new JsonResult();
		Map<String, String> parameters=new HashMap<String, String>();

		//List<ProjectERP> projectERPList=projectERPService.selectAllProjectERP();
		StringBuffer stringBuffer=new StringBuffer();
		/*for (int i = 0; i < projectERPList.size(); i++) {
			 ProjectERP projectERP=projectERPList.get(i);
			if(StringUtils.isNotBlank(projectERP.getProjectNo())){
				stringBuffer.append(projectERP.getProjectNo()).append(",");
	 		}
		 }*/
		 stringBuffer.append(pro);
		 
		 parameters.put("projectIdList", stringBuffer.toString());
		 String result=HttpClient.sendPost("http://117.144.21.74:33169/ERP-NBEmail/helpServlet?action=inspectionReport&className=ExternalinterfaceServlet", parameters);
		 //String result=HttpClient.sendPost("http://192.168.1.91:8080/ERP-NBEmail/helpServlet?action=inspectionReport&className=ExternalinterfaceServlet", parameters);
		 JSONArray json = JSONArray.fromObject(result); // 首先把字符串转成 JSONArray  对象
		 if(json.size()>0){
			  for(int i=0;i<json.size();i++){
			    JSONObject obj = json.getJSONObject(i);  // 閬嶅巻 jsonarray 鏁扮粍锛屾妸姣忎竴涓璞¤浆鎴� json 瀵硅薄
			    if(obj== null && obj.isNullObject()){
               	  continue;
                }
			    String projectNo = (String) obj.get("caseNo");
			    String projectNameC=""; 
			    
			    if(obj.get("projectDescc")!="" && obj.get("projectDescc")!=null){
			    	projectNameC = obj.get("projectDescc").toString();
			    }
			    
			    String projectNameE="";
			    if(obj.get("projectDesce")!="" && obj.get("projectDesce")!=null){
			    	projectNameE =obj.get("projectDesce").toString();
			    }
			    
			    String zhijian1="";
			    if(obj.get("zhijian1")!="" && obj.get("zhijian1")!=null){
			    	zhijian1 = obj.get("zhijian1").toString();
			    }
	            
			    String zhijian2="";
			    if(obj.get("zhijian2")!="" && obj.get("zhijian2")!=null){
			    	zhijian2 = obj.get("zhijian2").toString();
			    }
		        
			    String customerManager="";
			    if(obj.get("customerManager")!="" && obj.get("customerManager")!=null){
			    	customerManager =obj.get("customerManager").toString();
			    }
			    
			    String merchandManager1="";
			    if(obj.get("merchandManager1")!="" && obj.get("merchandManager1")!=null){
			    	merchandManager1 = obj.get("merchandManager1").toString();
			    }
			    if(obj.get("merchandising")!="" && obj.get("merchandising")!=null){
			    	merchandManager1 = obj.get("merchandising").toString();
			    }
			    
			    String merchandManager2="";
			    if(obj.get("merchandManager2")!="" && obj.get("merchandManager2")!=null){
			    	merchandManager2 =obj.get("merchandManager2").toString();
			    }
	            
			    if(obj.get("maturePurchase")!="" && obj.get("maturePurchase")!=null){
			    	merchandManager2 =obj.get("maturePurchase").toString();
			    }
			    
			    String engineer1="";
			    if(obj.get("engineer1")!="" && obj.get("engineer1")!=null){
			    	engineer1 = obj.get("engineer1").toString();
			    }
			    
			    String engineer2="";
			    if(obj.get("engineer2")!="" && obj.get("engineer2")!=null){
			    	engineer2 =obj.get("engineer2").toString();
			    }
			    
			    String engineer3="";
			    if(obj.get("engineer1")!="" && obj.get("engineer3")!=null){
			    	engineer3 = obj.get("engineer3").toString();
			    } 
			    
			    String companyName="";
			    if(obj.get("factoryName")!="" && obj.get("factoryName")!=null){
			    	companyName = obj.get("factoryName").toString();
			    } 
			    
			    String plantAnalysis="";
			    if(obj.get("plantAnalysis")!="" && obj.get("plantAnalysis")!=null){
			    	plantAnalysis = obj.get("plantAnalysis").toString();
			    } 
			    
			    String projectMaterialProperties="";
			    if(obj.get("projectMaterialProperties")!="" && obj.get("projectMaterialProperties")!=null){
			    	projectMaterialProperties = obj.get("projectMaterialProperties").toString();
			    } 
			    
			    String dateSample="";
			    if(obj.get("dateSample")!="" && obj.get("dateSample")!=null){
			    	dateSample = obj.get("dateSample").toString();
			    } 
			    	    
			    String completionTime="";
			    if(obj.get("completionTime")!="" && obj.get("completionTime")!=null){
			    	completionTime = obj.get("completionTime").toString();
			    } 
			    
			    String dateSampleUploading="";
			    if(obj.get("dateSampleUploading")!="" && obj.get("dateSampleUploading")!=null){
			    	dateSampleUploading = obj.get("dateSampleUploading").toString();
			    } 
			    String moneyDate="";
	    		if(obj.get("firstPaymentDate")!="" && obj.get("firstPaymentDate")!=null){
	    			moneyDate = obj.get("firstPaymentDate").toString();
			    }
	    		String poDate="";
	    		if(obj.get("poDate")!="" && obj.get("poDate")!=null){
	    			poDate = obj.get("poDate").toString();
	    		}
			    
				ProjectERP project = new ProjectERP();
				project.setId(IdGen.uuid());
				project.setProjectNo(projectNo);
				project.setProjectNameC(projectNameC);
		        project.setProjectNameE(projectNameE);
		        project.setZhijian1(zhijian1);
		        project.setZhijian2(zhijian2);
		        project.setCustomerManager(customerManager);
		        project.setMerchandManager1(merchandManager1);
		        project.setMerchandManager2(merchandManager2);
		        project.setEngineer1(engineer1);
		        project.setEngineer2(engineer2);
				project.setEngineer3(engineer3);
				project.setCompanyName(companyName);
				project.setPlantAnalysis(Integer.parseInt(plantAnalysis));
				project.setProjectMaterialProperties(Integer.parseInt(projectMaterialProperties));

				
				if(StringUtils.isNotBlank(moneyDate)){
					project.setMoneyDate(DateUtil.StrToDate(moneyDate));
				}
				if(StringUtils.isNotBlank(dateSample)){
					Calendar c = Calendar.getInstance();
					c.setTime(DateUtil.StrToDate(dateSample));
					int year=c.get(Calendar.YEAR);
					if(year>2000){
						project.setDateSample(DateUtil.StrToDate(dateSample));
					}
					
				}
				if(StringUtils.isNotBlank(completionTime)){
					Calendar c = Calendar.getInstance();
					c.setTime(DateUtil.StrToDate(completionTime));
					int year=c.get(Calendar.YEAR);
					if(year>2000){
						project.setCompletionTime(DateUtil.StrToDate(completionTime));
					}
				}

				if(StringUtils.isNotBlank(dateSampleUploading)){
					Calendar c = Calendar.getInstance();
					c.setTime(DateUtil.StrToDate(dateSampleUploading));
					int year=c.get(Calendar.YEAR);
					if(year>2000){
						project.setDateSampleUploading(DateUtil.StrToDate(dateSampleUploading));
					}
				}
				
			    ProjectERP projectErp=projectERPService.selectProjectErpByNo(project.getProjectNo());
				if(projectErp == null){
					project.setCreateDate(new Date());
					projectERPService.addProjectERP(project);
				}else{
					projectERPService.updateProjectErp(project);
				}
				
				Project projectInfo=projectService.selectProjctDetails(project.getProjectNo());
				if(projectInfo!=null){
					projectInfo.setProjectNameEn(projectNameE);
					projectInfo.setProjectNo(project.getProjectNo());
					projectInfo.setCompanyName(project.getCompanyName());
					projectInfo.setPlantAnalysis(project.getPlantAnalysis());
					projectInfo.setProjectMaterialProperties(project.getProjectMaterialProperties());
					projectInfo.setZhijian1(zhijian1);
					projectInfo.setZhijian2(zhijian2);					
					
					User user=new User();
					User purchaseUser =new User();
					if(StringUtils.isNotBlank(project.getMerchandManager1())){
						user=userService.selectUserByName(project.getMerchandManager1());
						if(user!=null){
							if(projectInfo.getEmailUserId()==0){
								projectInfo.setEmailUserId(user.getId());
							}
				    	}
					}
					if(StringUtils.isNotBlank(project.getMerchandManager2())){
						purchaseUser=userService.selectUserByName(project.getMerchandManager2());
						if(purchaseUser!=null){
							if(projectInfo.getEmailUserId()==0){
								projectInfo.setPurchaseId(purchaseUser.getId());
							}
				    	}	
					}
					//大货交期
					if(completionTime !=null){
						projectInfo.setDeliveryDate(java.sql.Date.valueOf(completionTime));
						projectInfo.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
					}
					//样品交期
					if(project.getDateSample()!=null){
						projectInfo.setSampleScheduledDate(project.getDateSample());
					}
					
					if(project.getDateSampleUploading()!=null){
						if(projectInfo.getDateSampleUploading()==null){//合同上传日期,开工日期
							projectInfo.setActualStartDate(project.getDateSampleUploading());
						}
					}
					if(StringUtils.isNotBlank(poDate)){
						projectInfo.setDateSampleUploading(DateUtil.StrToDate(poDate.toString()));
					}
					
					
					
					if(project.getMoneyDate()!=null){
						projectInfo.setMoneyDate(project.getMoneyDate());
					}
					projectService.updateProjectInfo(projectInfo);
					List<Project> projectList=new ArrayList<Project>();
					projectList.add(projectInfo);
					projectDateTask.syncProjectDate(projectList);
				}else{
					Project insertProject=new Project();
					User user=new User();
					User purchaseUser =new User();
					//1.MerchandManager1 跟单销售,MerchandManager2 采购
					if(StringUtils.isNotBlank(project.getMerchandManager1())){
						user=userService.selectUserByName(project.getMerchandManager1());
						if(user!=null){
							insertProject.setEmailUserId(user.getId());
				    	}
					}
					if(StringUtils.isNotBlank(project.getMerchandManager2())){
						purchaseUser=userService.selectUserByName(project.getMerchandManager2());
						if(purchaseUser!=null){
							insertProject.setPurchaseId(purchaseUser.getId());
				    	}	
					}
					insertProject.setId(IdGen.uuid());
				    insertProject.setProjectNo(project.getProjectNo());
				    insertProject.setProjectName(project.getProjectNameC());
				    insertProject.setProjectNameEn(project.getProjectNameE());
				    insertProject.setDeliveryDate(project.getCompletionTime()); //交期
				    insertProject.setDeliveryStatus(0);
				    insertProject.setWarningStatus(0);
				    insertProject.setImportance(0);
				    insertProject.setFinish(0);
				    insertProject.setStage(0);
				    insertProject.setPoDate(null);  //PO日期
				    insertProject.setActualStartDate(project.getDateSampleUploading());  //开工日期
				    insertProject.setScheduledDate(null);
				    insertProject.setSampleScheduledDate(project.getDateSample());
				    insertProject.setCompanyName(project.getCompanyName());
				    insertProject.setCreateDate(new Date());
				    insertProject.setPlantAnalysis(project.getPlantAnalysis());
				    insertProject.setZhijian1(zhijian1);
				    insertProject.setZhijian2(zhijian2);
				    
				    insertProject.setProjectMaterialProperties(project.getProjectMaterialProperties());
				    if(StringUtils.isNotBlank(dateSample)){
				    	insertProject.setDateSample(DateUtil.StrToDate(dateSample));
					}
					if(StringUtils.isNotBlank(completionTime)){
						insertProject.setCompletionTime(DateUtil.StrToDate(completionTime));
					}
					if(StringUtils.isNotBlank(dateSampleUploading)){
						insertProject.setDateSampleUploading(DateUtil.StrToDate(dateSampleUploading));
					}
					if(project.getMoneyDate()!=null){
						insertProject.setMoneyDate(project.getMoneyDate());
					}
					if(StringUtils.isNotBlank(poDate)){
						insertProject.setDateSampleUploading(DateUtil.StrToDate(poDate.toString()));
					}
										
					insertProject.setProjectStatus(0);				
						
						   //如果交期时间不为空
						   //录入首次大货日期，如果存在第一次大货时间，则更新
						  if(completionTime != null){
							  
							  insertProject.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());  //初始导入正常进行项目
							  //如果存在大货交期，计算生成周期时间
//							  insertProject.setUrgentDeliveryDate(DateUtil.StrToDate(completionTime));
							  int scheduledDays = 0;
							  //如果存在po日期，生产天数为 交期-po日期
							  //如果不存在po日期，生产天数为交期-合同日期+7天
							  if(StringUtils.isNotBlank(poDate)){
								  scheduledDays = DateFormat.calDays(completionTime, poDate);
							  }else{
								  scheduledDays = DateFormat.calDays(completionTime, dateSampleUploading);
								  scheduledDays = scheduledDays + 7;
							  }
							  insertProject.setScheduledDays(scheduledDays);							
								
						 }else{
							  if(StringUtils.isNotBlank(dateSample)){
								  //紧急交货期
//								  insertProject.setUrgentDeliveryDate(DateUtil.StrToDate(completionTime));
								  int scheduledDays = 0;
								  //如果存在po日期，生产天数为 交期-po日期
								  //如果不存在po日期，生产天数为交期-合同日期+7天
								  if(StringUtils.isNotBlank(poDate)){
									  scheduledDays = DateFormat.calDays(dateSample, poDate);
								  }else{
									  scheduledDays = DateFormat.calDays(dateSample, dateSampleUploading);
									  scheduledDays = scheduledDays + 7;
								  }
								  insertProject.setScheduledDays(scheduledDays);
							  }
						  
						 }
							
						  
						    projectService.addProject(insertProject);
						    List<Project> projectList=new ArrayList<Project>();
							projectList.add(insertProject);
							projectDateTask.syncProjectDate(projectList);
				}

			  }
			 }
		 return jsonResult;
	}
	/**
	 * 同步开工日期,样品交期,大货交期,合同创建项目
	 * @param request
	 * @param
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="获取项目信息",notes="获取项目合同创建时的信息，同时保存合同信息")
	@RequestMapping(value = "/synchProjectTime")
	@ResponseBody
	public JsonResult synchProjectTime(HttpServletRequest request) throws Exception {
	    JsonResult jsonResult=new JsonResult();

			String merchandManager1=request.getParameter("merchandManager1"); //原始跟单
			String merchandManager2=request.getParameter("merchandManager2");//原始采购
			String productDescC=request.getParameter("productDescC");
			String productDescE=request.getParameter("productDescE");
			String zhijian1=request.getParameter("zhijian1");
			String zhijian2=request.getParameter("zhijian2");
			String merchandising=request.getParameter("merchandising");//成员跟单
			String maturePurchase=request.getParameter("maturePurchase");//成熟采购
			String factoryName=request.getParameter("factoryName");
			String projectId=request.getParameter("projectId");
			String poDate=request.getParameter("poDate");

			if(StringUtils.isNotBlank(poDate)){
				poDate = poDate.toString();
			}
			String dateSampleUploading=request.getParameter("projectStartDate");//开工日期
			String dateSample=request.getParameter("dateSampleCompletion");//样品交期
			String completionTime =request.getParameter("completionDateBigGoods");//大货交期
			String ProjectLevel=request.getParameter("ProjectLevel");
			String ProjectMaterial=request.getParameter("ProjectMaterial");
			String projectStartDate=request.getParameter("projectStartDate");//第一笔到款时间
		    try {	
			ProjectERP project = new ProjectERP();
			project.setId(IdGen.uuid());
			project.setProjectNameC(productDescC);
			project.setProjectNameE(productDescE);
			project.setZhijian1(zhijian1);
			project.setZhijian2(zhijian2);
			project.setCompanyName(factoryName);
			project.setProjectNo(projectId);
			project.setMerchandManager1(merchandManager1);// 跟单
			project.setMerchandManager2(merchandManager2);//采购
			if(StringUtils.isNotBlank(merchandising)){//跟单
				project.setMerchandManager1(merchandising);
			}else{
				project.setMerchandManager1(merchandManager1);
			}
			
			if(StringUtils.isNotBlank(maturePurchase)){//采购
				project.setMerchandManager2(maturePurchase);
			}else{
				project.setMerchandManager2(merchandManager2);
			}
     
			if(StringUtils.isNotBlank(dateSample)){//样品日期
				project.setDateSample(DateUtil.StrToDate(dateSample));
			}
			
			if(StringUtils.isNotBlank(completionTime)){//大货交期
				project.setCompletionTime(DateUtil.StrToDate(completionTime));
			}
			if(StringUtils.isNotBlank(dateSampleUploading)){//开工日期
				project.setDateSampleUploading(DateUtil.StrToDate(dateSampleUploading));
			}
			if(StringUtils.isNotBlank(projectStartDate)){//第一次付款时间
				project.setMoneyDate(DateUtil.StrToDate(projectStartDate));
			}
			ProjectERP projectErp=projectERPService.selectProjectErpByNo(project.getProjectNo());
			if(StringUtils.isNotBlank(ProjectLevel)){
				project.setPlantAnalysis(Integer.parseInt(ProjectLevel));
			}
            if(StringUtils.isNotBlank(ProjectMaterial)){
    			project.setProjectMaterialProperties(Integer.parseInt(ProjectMaterial));
            }
			
			if(projectErp!=null){
				projectERPService.updateProjectErp(project);
			}else{
				projectERPService.addProjectERP(project);
			}
		    sysnByProjectErp(project.getProjectNo());			

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("同步项目数据失败"+projectId,e);
		}
	  return jsonResult;
	}
	
	
	@ApiOperation(value="获取项目信息",notes="跟新合同日期")
	@RequestMapping(value = "/updateDateSample")
	@ResponseBody
	public JsonResult updateDateSample(HttpServletRequest request) throws Exception {
	    JsonResult jsonResult=new JsonResult();
		String projectNo=request.getParameter("projectNo");
	    try{
	    	ProjectFactory projectFactory = new ProjectFactory(); 
			String complateDate=request.getParameter("complateDate");//成熟采购
			String dateSimple=request.getParameter("dateSimple");
			String bargainNo=request.getParameter("bargainNo");
			ProjectERP projectErp=itemCaseERPService.selectByCaseNo(projectNo);
			Project project = projectService.selectProjctDetails(projectNo);
			ProjectFactory factory1 =new ProjectFactory ();
			factory1.setContractNo(bargainNo);
			factory1.setProjectNo(projectNo);
			ProjectFactory factory=projectFactoryService.getProjctDetails(factory1);
			if(project!=null&&factory!=null){
				projectFactory.setProjectNo(projectNo);
				projectFactory.setContractNo(bargainNo);
				 projectFactory.setSampleDate(projectErp.getDateSample());
				 projectFactory.setContractDate(projectErp.getDateSampleUploading());
				if(projectErp.getCompletionTime() != null){
					 
					 //如果新合同交期小于原交期，则不更新，反之则更新交期
					if(project.getDeliveryDate() != null && projectErp.getCompletionTime().getTime() < project.getDeliveryDate().getTime()){
						
					}else{			
						project.setDeliveryDate(projectErp.getCompletionTime());
						project.setOriginalDeliveryDate(projectErp.getCompletionTime());						
					}
					
					//工厂项目表保存交期和合同日期
					projectFactory.setDeliveryDate(projectErp.getCompletionTime());
				}
				project.setSampleScheduledDate(projectErp.getDateSample());
				projectService.updateProjectInfo(project);
				projectFactoryService.updateByPrimaryKey(projectFactory);
			}else if(project!=null){
				projectFactory.setSampleDate(projectErp.getDateSample());
				projectFactory.setContractDate(projectErp.getDateSampleUploading());
				projectFactory.setProjectNo(projectNo);
				projectFactory.setContractNo(bargainNo);
				 //当开工时间为空时不进行插入 
				 if(projectErp.getDateSampleUploading() != null){
					project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());  //初始导入正常进行项目
				 }
				 //大货交期
				 if(projectErp.getCompletionTime() != null){
					 
					 //如果新合同交期小于原交期，则不更新，反之则更新交期
					if(project.getDeliveryDate() != null && projectErp.getCompletionTime().getTime() < project.getDeliveryDate().getTime()){
						
					}else{			
						project.setDeliveryDate(projectErp.getCompletionTime());
						project.setOriginalDeliveryDate(projectErp.getCompletionTime());						
					}
					
					//工厂项目表保存交期和合同日期
					projectFactory.setDeliveryDate(projectErp.getCompletionTime());
				}	
				 projectFactory.setFactoryId(projectErp.getFactoryId());
					projectFactory.setFactoryName(projectErp.getCompanyName());
					projectFactory.setProjectNo(projectNo);	
					projectFactory.setCity(projectErp.getCity());
					projectFactory.setContractNo(projectErp.getContractNo());
					if(projectErp.getSupplementaryContract() != null){
						projectFactory.setOrderNature(projectErp.getSupplementaryContract() == 1 ? 2 : 1); //补货或者正常
					}else{
						projectFactory.setOrderNature(1);
					}
					project.setSampleScheduledDate(projectErp.getDateSample());
					projectFactoryService.insertSelective1(projectFactory);
					projectService.updateProjectInfo(project);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("同步项目数据失败"+projectNo,e);
		}
	  return jsonResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 同步项目成员  2018.8.23
	 * @param request
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/synchProjectMember")
	@ResponseBody
	public JsonResult synchProjectMember(HttpServletRequest request){
		JsonResult jsonResult=new JsonResult();
		try {
			String projectNo = request.getParameter("projectNo");
			if(StringUtils.isNotBlank(projectNo)){
				projectService.updateProjectByErp(projectNo);
				jsonResult.setMessage("同步成功");
				jsonResult.setOk(true);
				return jsonResult;
			}
			jsonResult.setMessage("同步成功");
			jsonResult.setOk(true);
			return jsonResult;
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMessage("同步失败");
			jsonResult.setOk(false);
			return jsonResult;
		}
		
	}
	
	
	
	/**
	 * 同步ERP项目
	 * @param request
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sysnProjectByErp")
	@ResponseBody
	public JsonResult sysnProjectByErp(HttpServletRequest request) throws Exception{
		JsonResult jsonResult = new JsonResult();
		ProjectFactory projectFactory = new ProjectFactory();              //项目下单工厂
	    String projectNo = request.getParameter("projectNo");
	    ProjectERP projectErp=itemCaseERPService.selectByCaseNo(projectNo);
	    Project project = projectService.selectProjctDetails(projectNo);   
	    if(projectErp == null || project != null){
//	    	projectErp=itemCaseERPService.selectByCaseNo(projectNo);
	    	LOG.info(projectErp);
	    	//项目不为空时，更新项目
	    	if(project != null){
	    		project.setProjectName(projectErp.getProjectNameC());
	    		project.setDeliveryDate(projectErp.getCompletionTime());	
			    project.setActualStartDate(projectErp.getDateSampleUploading());  //开工日期
			    project.setSampleScheduledDate(projectErp.getDateSample());
			    project.setOriginalSampleScheduledDate(projectErp.getDateSample());
			    project.setCompanyName(projectErp.getCompanyName());
			    project.setZhijian1(projectErp.getZhijian1());
			    project.setZhijian2(projectErp.getZhijian2());
			    project.setZhijian3(projectErp.getZhijian3());
			    project.setPlantAnalysis(projectErp.getPlantAnalysis());
			    project.setProjectMaterialProperties(projectErp.getProjectMaterialProperties());
			    project.setMoneyDate(projectErp.getMoneyDate());
			    project.setCustomerName(projectErp.getCustomerName());
			    project.setCustomerGrade(projectErp.getCustomerGrade());
			    
			    //默认给样品交期，如果存在大货，保存大货交期
			    //录入合同日期
			    projectFactory.setSampleDate(projectErp.getDateSample());
			    projectFactory.setContractDate(projectErp.getDateSampleUploading());
			    
			    if(projectErp.getDateSampleUploading() != null){
			    	project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
			    }
			  
				//大货交期
				if(projectErp.getCompletionTime() != null){				   
					project.setDeliveryDate(projectErp.getCompletionTime());
					project.setOriginalDeliveryDate(projectErp.getCompletionTime());					
						//工厂项目表保存交期和合同日期
						projectFactory.setDeliveryDate(projectErp.getCompletionTime());	
				}
				   User user=new User();
				   User purchaseUser =new User();
				    //1.MerchandManager1 跟单销售,MerchandManager2 采购			
					//如果存在成熟跟单，保存成熟跟单，不存在，保存原始跟单
				    //Merchandising 成熟跟单
					if(StringUtils.isNotBlank(projectErp.getMerchandising())){
						user=userService.selectUserByName(projectErp.getMerchandising());
						if(user!=null){
							project.setEmailUserId(user.getId());
				    	}
					}else{
						if(StringUtils.isNotBlank(projectErp.getMerchandManager1())){
							user=userService.selectUserByName(projectErp.getMerchandManager1());
							if(user!=null){
								project.setEmailUserId(user.getId());
					    	}
						}
					}			
					if(StringUtils.isNotBlank(projectErp.getCustomerManager())){
							user=userService.selectUserByName(projectErp.getCustomerManager());
							if(user!=null){
								project.setSaleId(user.getId());
					    	}
						}
					
					
					//如果存在成熟采购，保存成熟采购，不存在，保存原始采购
					if(StringUtils.isNotBlank(projectErp.getMaturePurchase())){
						purchaseUser=userService.selectUserByName(projectErp.getMaturePurchase());
						if(purchaseUser!=null){
							project.setPurchaseId(purchaseUser.getId());
				    	}
					}else{
						if(StringUtils.isNotBlank(projectErp.getMerchandManager2())){
							purchaseUser=userService.selectUserByName(projectErp.getMerchandManager2());
							if(purchaseUser!=null){
								project.setPurchaseId(purchaseUser.getId());
					    	}
						}
					}
					
					//保存下单工厂
					projectFactory.setFactoryId(projectErp.getFactoryId());
					projectFactory.setFactoryName(projectErp.getCompanyName());
					projectFactory.setProjectNo(projectNo);
					projectFactory.setCity(projectErp.getCity());
					projectFactory.setContractNo(projectErp.getContractNo());
					if(projectErp.getSupplementaryContract() != null){
						projectFactory.setOrderNature(projectErp.getSupplementaryContract() == 1 ? 2 : 1); //补货或者正常
					}else{
						projectFactory.setOrderNature(1);
					}
					projectFactoryService.insertSelective(projectFactory);
					projectService.updateProjectInfo(project);
	    	}	    	
	    	
	    	return jsonResult;
	    }else{
	    	project = new Project();
	    	User user=new User();
			User purchaseUser =new User();
			//1.MerchandManager1 跟单销售,MerchandManager2 采购
			if(StringUtils.isNotBlank(projectErp.getMerchandManager1())){
				user=userService.selectUserByName(projectErp.getMerchandManager1());
				if(user!=null){
					project.setEmailUserId(user.getId());
		    	}
			}
			if(StringUtils.isNotBlank(projectErp.getMerchandManager2())){
				purchaseUser=userService.selectUserByName(projectErp.getMerchandManager2());
				if(purchaseUser!=null){
					project.setPurchaseId(purchaseUser.getId());
		    	}	
			}
			project.setId(IdGen.uuid());
		    project.setProjectNo(projectNo);
		    project.setProjectName(projectErp.getProjectNameC());
		    project.setDeliveryDate(projectErp.getCompletionTime()); //交期
		    project.setOriginalDeliveryDate(projectErp.getCompletionTime());
		    project.setDeliveryStatus(0);
		    project.setWarningStatus(0);
		    project.setImportance(0);
		    project.setFinish(0);
		    project.setSampleFinish(0);
		    project.setStage(0);
		    project.setPoDate(projectErp.getPoDate());  //PO日期
		    project.setActualStartDate(projectErp.getDateSampleUploading());  //开工日期
		    project.setScheduledDate(null);
		    project.setSampleScheduledDate(projectErp.getDateSample());
		    project.setOriginalSampleScheduledDate(projectErp.getDateSample());
		    project.setCompanyName(projectErp.getCompanyName());
		    project.setCreateDate(new Date());
		    project.setPlantAnalysis(projectErp.getPlantAnalysis());
		    project.setDetailStatus(0);
		    project.setSampleFinishTime(null);
		    project.setZhijian1(projectErp.getZhijian1());
		    project.setZhijian2(projectErp.getZhijian2());
		    project.setZhijian3(projectErp.getZhijian3());
		    project.setCustomerName(projectErp.getCustomerName());
		    project.setDateSampleUploading(projectErp.getDateSampleUploading());
		    project.setCustomerGrade(projectErp.getCustomerGrade());
		    
		    //默认给样品交期，如果存在大货，保存大货交期
		    //录入合同日期
		    projectFactory.setSampleDate(projectErp.getDateSample());
		    projectFactory.setContractDate(projectErp.getDateSampleUploading());
		    
		    project.setProjectMaterialProperties(projectErp.getProjectMaterialProperties());
		    if(projectErp.getDateSample() != null){
		    	project.setDateSample(projectErp.getDateSample());
			}
			if(projectErp.getCompletionTime()!= null){
				project.setCompletionTime(projectErp.getCompletionTime());
			}
			if(projectErp.getMoneyDate()!=null){
				project.setMoneyDate(projectErp.getMoneyDate());
			}
			   //项目启动日期
			Date poDate = projectErp.getPoDate();
			
			//Edit by polo   2018.7.10
			//当开工时间为空时不进行插入 
			if(projectErp.getDateSampleUploading() != null){
				project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());  //初始导入正常进行项目
				
				   //如果存在大货交期，生产周期根据大货交期去计算，如果不存在大货，生产周期根据样品交期计算
				   //如果交期时间不为空
				   //录入首次大货日期，如果存在第一次大货时间，则更新
				  if(projectErp.getCompletionTime() != null){						  
					  //如果存在大货交期，计算生成周期时间
					  int scheduledDays = 0;
					  //如果存在po日期，生产天数为 交期-po日期
					  //如果不存在po日期，生产天数为交期-合同日期+7天
					  if(poDate != null){
						  scheduledDays = DateFormat.calDays(projectErp.getCompletionTime(), poDate);
					  }else{
						  scheduledDays = DateFormat.calDays(projectErp.getCompletionTime(), projectErp.getDateSampleUploading());
						  scheduledDays = scheduledDays + 7;
					  }
					  project.setScheduledDays(scheduledDays);
					
						//工厂项目表保存交期和合同日期
						projectFactory.setDeliveryDate(projectErp.getCompletionTime());
						
				  }else{
					  if(projectErp.getDateSample() != null){
						  //紧急交货期
						  int scheduledDays = 0;
						  //如果存在po日期，生产天数为 交期-po日期
						  //如果不存在po日期，生产天数为交期-合同日期+7天
						  if(poDate != null){
							  scheduledDays = DateFormat.calDays(projectErp.getDateSample(), poDate);
						  }else{
							  scheduledDays = DateFormat.calDays(projectErp.getDateSample(), projectErp.getDateSampleUploading());
							  scheduledDays = scheduledDays + 7;
						  }
						  project.setScheduledDays(scheduledDays);
					  }
				  
				  }
					//保存下单工厂
					projectFactory.setFactoryId(projectErp.getFactoryId());
					projectFactory.setFactoryName(projectErp.getCompanyName());
					projectFactory.setProjectNo(projectNo);
					projectFactory.setCity(projectErp.getCity());
					projectFactory.setContractNo(projectErp.getContractNo());
					if(projectErp.getSupplementaryContract()!=null){
						projectFactory.setOrderNature(projectErp.getSupplementaryContract() == 1 ? 2 : 1); //补货或者正常
					}else{
						projectFactory.setOrderNature(1);
					}
					
					projectFactoryService.insertSelective(projectFactory);
				  
				  
				    projectService.addProject(project);
					List<Project> projectList=new ArrayList<Project>();
					projectList.add(project);
					projectDateTask.syncProjectDate(projectList);
	       }
	    }
			
			
		return jsonResult;
	}
	
	
	
	
	
	
	/**
	 * 同步ERP项目
	 * @param
	 * @param
	 * @return
	 * @throws Exception
     */

	public JsonResult sysnByProjectErp(String projectNo) throws Exception{
		JsonResult jsonResult = new JsonResult();
		ProjectFactory projectFactory = new ProjectFactory(); 
	    ProjectERP projectErp=itemCaseERPService.selectByCaseNo(projectNo);
	    Project project = projectService.selectProjctDetails(projectNo);   
	    if(projectErp != null && project != null){
	    	//项目不为空时，更新项目
	    	if(project != null){
	    		project.setProjectName(projectErp.getProjectNameC());	    			    		
			    project.setActualStartDate(projectErp.getDateSampleUploading());  //开工日期
			    project.setSampleScheduledDate(projectErp.getDateSample());
			    project.setOriginalSampleScheduledDate(projectErp.getDateSample());
			    project.setCompanyName(projectErp.getCompanyName());
			    project.setZhijian1(projectErp.getZhijian1());
			    project.setZhijian2(projectErp.getZhijian2());
			    project.setZhijian3(projectErp.getZhijian3());
			    project.setPlantAnalysis(projectErp.getPlantAnalysis());
			    project.setProjectMaterialProperties(projectErp.getProjectMaterialProperties());
			    project.setMoneyDate(projectErp.getMoneyDate());
			    project.setCustomerName(projectErp.getCustomerName());
			    project.setProjectNameEn(projectErp.getProjectNameE());
			    project.setCustomerGrade(projectErp.getCustomerGrade());
			    
			    //默认给样品交期，如果存在大货，保存大货交期
			    //录入合同日期
			    projectFactory.setSampleDate(projectErp.getDateSample());
			    projectFactory.setContractDate(projectErp.getDateSampleUploading());
			    
				 //当开工时间为空时不进行插入 
				 if(projectErp.getDateSampleUploading() != null){
					project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());  //初始导入正常进行项目
				 }
				 //大货交期
				 if(projectErp.getCompletionTime() != null){
					 
					 //如果新合同交期小于原交期，则不更新，反之则更新交期
					if(project.getDeliveryDate() != null && projectErp.getCompletionTime().getTime() < project.getDeliveryDate().getTime()){
						
					}else{			
						project.setDeliveryDate(projectErp.getCompletionTime());
						project.setOriginalDeliveryDate(projectErp.getCompletionTime());						
					}
					
					//工厂项目表保存交期和合同日期
					projectFactory.setDeliveryDate(projectErp.getCompletionTime());
				}
				   User user=new User();
				   User purchaseUser =new User();
				    //1.MerchandManager1 跟单销售,MerchandManager2 采购			
					//如果存在成熟跟单，保存成熟跟单，不存在，保存原始跟单
				    //Merchandising 成熟跟单
					if(StringUtils.isNotBlank(projectErp.getMerchandising())){
						user=userService.selectUserByName(projectErp.getMerchandising());
						if(user!=null){
							project.setEmailUserId(user.getId());
				    	}
					}else{
						if(StringUtils.isNotBlank(projectErp.getMerchandManager1())){
							user=userService.selectUserByName(projectErp.getMerchandManager1());
							if(user!=null){
								project.setEmailUserId(user.getId());
					    	}
						}
					}			
					
						if(StringUtils.isNotBlank(projectErp.getCustomerManager())){
							user=userService.selectUserByName(projectErp.getCustomerManager());
							if(user!=null){
								project.setSaleId(user.getId());
					    	}
						}
					

					
					//如果存在成熟采购，保存成熟采购，不存在，保存原始采购
					if(StringUtils.isNotBlank(projectErp.getMaturePurchase())){
						purchaseUser=userService.selectUserByName(projectErp.getMaturePurchase());
						if(purchaseUser!=null){
							project.setPurchaseId(purchaseUser.getId());
				    	}
					}else{
						if(StringUtils.isNotBlank(projectErp.getMerchandManager2())){
							purchaseUser=userService.selectUserByName(projectErp.getMerchandManager2());
							if(purchaseUser!=null){
								project.setPurchaseId(purchaseUser.getId());
					    	}
						}
					}
					
					
					//保存下单工厂
					projectFactory.setFactoryId(projectErp.getFactoryId());
					projectFactory.setFactoryName(projectErp.getCompanyName());
					projectFactory.setProjectNo(projectNo);	
					projectFactory.setCity(projectErp.getCity());
					projectFactory.setContractNo(projectErp.getContractNo());
					if(projectErp.getSupplementaryContract() != null){
						projectFactory.setOrderNature(projectErp.getSupplementaryContract() == 1 ? 2 : 1); //补货或者正常
					}else{
						projectFactory.setOrderNature(1);
					}
					
					projectFactoryService.insertSelective(projectFactory);
				
					projectService.updateProjectInfo(project);
	    	}	    	
	    	
	    	return jsonResult;
	    }else{
	    	project = new Project();
	    	User user=new User();
			User purchaseUser =new User();
			//1.MerchandManager1 跟单销售,MerchandManager2 采购
			if(StringUtils.isNotBlank(projectErp.getMerchandManager1())){
				user=userService.selectUserByName(projectErp.getMerchandManager1());
				if(user!=null){
					project.setEmailUserId(user.getId());
		    	}
			}
			if(StringUtils.isNotBlank(projectErp.getMerchandManager2())){
				purchaseUser=userService.selectUserByName(projectErp.getMerchandManager2());
				if(purchaseUser!=null){
					project.setPurchaseId(purchaseUser.getId());
		    	}	
			}
			project.setId(IdGen.uuid());
		    project.setProjectNo(projectNo);
		    project.setProjectName(projectErp.getProjectNameC());
		    project.setProjectNameEn(projectErp.getProjectNameE());
		    project.setDeliveryDate(projectErp.getCompletionTime()); //交期
		    project.setOriginalDeliveryDate(projectErp.getCompletionTime());   //交期
		    project.setDeliveryStatus(0);
		    project.setWarningStatus(0);
		    project.setImportance(0);
		    project.setFinish(0);
		    project.setSampleFinish(0);
		    project.setStage(0);
		    project.setPoDate(projectErp.getPoDate());  //PO日期
		    project.setActualStartDate(projectErp.getDateSampleUploading());  //开工日期
		    project.setScheduledDate(null);
		    project.setSampleScheduledDate(projectErp.getDateSample());
		    project.setOriginalSampleScheduledDate(projectErp.getDateSample());
		    project.setCompanyName(projectErp.getCompanyName());
		    project.setCreateDate(new Date());
		    project.setPlantAnalysis(projectErp.getPlantAnalysis());
		    project.setDetailStatus(0);
		    project.setSampleFinishTime(null);
		    project.setZhijian1(projectErp.getZhijian1());
		    project.setZhijian2(projectErp.getZhijian2());
		    project.setZhijian3(projectErp.getZhijian3());
		    project.setCustomerName(projectErp.getCustomerName());
		    project.setDateSampleUploading(projectErp.getDateSampleUploading());
		    project.setCustomerGrade(projectErp.getCustomerGrade());
		    
		    //默认给样品交期，如果存在大货，保存大货交期
		    //录入合同日期
		    projectFactory.setSampleDate(projectErp.getDateSample());
		    projectFactory.setContractDate(projectErp.getDateSampleUploading());
		    
		    
		    project.setProjectMaterialProperties(projectErp.getProjectMaterialProperties());
		    if(projectErp.getDateSample() != null){
		    	project.setDateSample(projectErp.getDateSample());
			}
			if(projectErp.getCompletionTime()!= null){
				project.setCompletionTime(projectErp.getCompletionTime());
			}
			if(projectErp.getMoneyDate()!=null){
				project.setMoneyDate(projectErp.getMoneyDate());
			}
			//项目启动日期
			Date poDate = projectErp.getPoDate();
			
			//Edit by polo   2018.7.10
			//当开工时间为空时不进行插入 
			if(projectErp.getDateSampleUploading() != null){
				project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());  //初始导入正常进行项目				
				   //如果存在大货交期，生产周期根据大货交期去计算，如果不存在大货，生产周期根据样品交期计算
				   //如果交期时间不为空
				   //录入首次大货日期，如果存在第一次大货时间，则更新
				  if(projectErp.getCompletionTime() != null){						  
					  //如果存在大货交期，计算生成周期时间
					  int scheduledDays = 0;
					  //如果存在po日期，生产天数为 交期-po日期
					  //如果不存在po日期，生产天数为交期-合同日期+7天
					  if(poDate != null){
						  scheduledDays = DateFormat.calDays(projectErp.getCompletionTime(), poDate);
					  }else{
						  scheduledDays = DateFormat.calDays(projectErp.getCompletionTime(), projectErp.getDateSampleUploading());
						  scheduledDays = scheduledDays + 7;
					  }
					   project.setScheduledDays(scheduledDays);					  					  						
						//工厂项目表保存交期
						projectFactory.setDeliveryDate(projectErp.getCompletionTime());
				  }else{
					  if(projectErp.getDateSample() != null){
						  //紧急交货期
						  int scheduledDays = 0;
						  //如果存在po日期，生产天数为 交期-po日期
						  //如果不存在po日期，生产天数为交期-合同日期+7天
						  if(poDate != null){
							  scheduledDays = DateFormat.calDays(projectErp.getDateSample(), poDate);
						  }else{
							  scheduledDays = DateFormat.calDays(projectErp.getDateSample(), projectErp.getDateSampleUploading());
							  scheduledDays = scheduledDays + 7;
						  }
						  project.setScheduledDays(scheduledDays);
					  }
				  
				  }
				  
				    //保存下单工厂
					projectFactory.setFactoryId(projectErp.getFactoryId());
					projectFactory.setFactoryName(projectErp.getCompanyName());
					projectFactory.setProjectNo(projectNo);
					projectFactory.setCity(projectErp.getCity());
					projectFactory.setContractNo(projectErp.getContractNo());
					if(projectErp.getSupplementaryContract()!=null){
						projectFactory.setOrderNature(projectErp.getSupplementaryContract() == 1 ? 2 : 1); //补货或者正常
					}else{
						projectFactory.setOrderNature(1);
					}					
					projectFactoryService.insertSelective(projectFactory);				  
					
				    projectService.addProject(project);
					List<Project> projectList=new ArrayList<Project>();
					projectList.add(project);
					projectDateTask.syncProjectDate(projectList);
	       }
	    }
			
			
		return jsonResult;
	}


	/**
	 * 同步ERP项目
	 * @param request
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sysnAllProjectErp")
	@ResponseBody
	public JsonResult sysnAllProjectErp(HttpServletRequest request) throws Exception{
		JsonResult jsonResult = new JsonResult();
		String projectNo=request.getParameter("projectNo");
		ProjectFactory projectFactory = new ProjectFactory();
		ProjectERP projectErp=itemCaseERPService.selectByCaseNo(projectNo);
		Project project = projectService.selectProjctDetails(projectNo);
		if(projectErp != null && project != null){
			//项目不为空时，更新项目
			if(project != null){
				project.setProjectName(projectErp.getProjectNameC());
				project.setActualStartDate(projectErp.getDateSampleUploading());  //开工日期
				project.setSampleScheduledDate(projectErp.getDateSample());
				project.setOriginalSampleScheduledDate(projectErp.getDateSample());
				project.setCompanyName(projectErp.getCompanyName());
				project.setZhijian1(projectErp.getZhijian1());
				project.setZhijian2(projectErp.getZhijian2());
				project.setZhijian3(projectErp.getZhijian3());
				project.setPlantAnalysis(projectErp.getPlantAnalysis());
				project.setProjectMaterialProperties(projectErp.getProjectMaterialProperties());
				project.setMoneyDate(projectErp.getMoneyDate());
				project.setCustomerName(projectErp.getCustomerName());
				project.setProjectNameEn(projectErp.getProjectNameE());
				project.setCustomerGrade(projectErp.getCustomerGrade());

				//默认给样品交期，如果存在大货，保存大货交期
				//录入合同日期
				projectFactory.setSampleDate(projectErp.getDateSample());
				projectFactory.setContractDate(projectErp.getDateSampleUploading());

				//当开工时间为空时不进行插入
				if(projectErp.getDateSampleUploading() != null){
					project.setProjectStatus(OrderStatusEnum.NORMAL_ORDER.getCode());  //初始导入正常进行项目
				}
				//大货交期
				if(projectErp.getCompletionTime() != null){

					//如果新合同交期小于原交期，则不更新，反之则更新交期
					if(project.getDeliveryDate() != null && projectErp.getCompletionTime().getTime() < project.getDeliveryDate().getTime()){

					}else{
						project.setDeliveryDate(projectErp.getCompletionTime());
						project.setOriginalDeliveryDate(projectErp.getCompletionTime());
					}

					//工厂项目表保存交期和合同日期
					projectFactory.setDeliveryDate(projectErp.getCompletionTime());
				}
				User user=new User();
				User purchaseUser =new User();
				//1.MerchandManager1 跟单销售,MerchandManager2 采购
				//如果存在成熟跟单，保存成熟跟单，不存在，保存原始跟单
				//Merchandising 成熟跟单
				if(StringUtils.isNotBlank(projectErp.getMerchandising())){
					user=userService.selectUserByName(projectErp.getMerchandising());
					if(user!=null){
						project.setEmailUserId(user.getId());
					}
				}else{
					if(StringUtils.isNotBlank(projectErp.getMerchandManager1())){
						user=userService.selectUserByName(projectErp.getMerchandManager1());
						if(user!=null){
							project.setEmailUserId(user.getId());
						}
					}
				}

				if(StringUtils.isNotBlank(projectErp.getCustomerManager())){
					user=userService.selectUserByName(projectErp.getCustomerManager());
					if(user!=null){
						project.setSaleId(user.getId());
					}
				}



				//如果存在成熟采购，保存成熟采购，不存在，保存原始采购
				if(StringUtils.isNotBlank(projectErp.getMaturePurchase())){
					purchaseUser=userService.selectUserByName(projectErp.getMaturePurchase());
					if(purchaseUser!=null){
						project.setPurchaseId(purchaseUser.getId());
					}
				}else{
					if(StringUtils.isNotBlank(projectErp.getMerchandManager2())){
						purchaseUser=userService.selectUserByName(projectErp.getMerchandManager2());
						if(purchaseUser!=null){
							project.setPurchaseId(purchaseUser.getId());
						}
					}
				}


				//保存下单工厂
				projectFactory.setFactoryId(projectErp.getFactoryId());
				projectFactory.setFactoryName(projectErp.getCompanyName());
				projectFactory.setProjectNo(projectNo);
				projectFactory.setCity(projectErp.getCity());
				projectFactory.setContractNo(projectErp.getContractNo());
				if(projectErp.getSupplementaryContract() != null){
					projectFactory.setOrderNature(projectErp.getSupplementaryContract() == 1 ? 2 : 1); //补货或者正常
				}else{
					projectFactory.setOrderNature(1);
				}

				//projectFactoryService.insertSelective(projectFactory);

				projectService.updateProjectInfo(project);
			}

			return jsonResult;
		}else{
			project = new Project();
			User user=new User();
			User purchaseUser =new User();
			//1.MerchandManager1 跟单销售,MerchandManager2 采购
			if(StringUtils.isNotBlank(projectErp.getMerchandManager1())){
				user=userService.selectUserByName(projectErp.getMerchandManager1());
				if(user!=null){
					project.setEmailUserId(user.getId());
				}
			}
			if(StringUtils.isNotBlank(projectErp.getMerchandManager2())){
				purchaseUser=userService.selectUserByName(projectErp.getMerchandManager2());
				if(purchaseUser!=null){
					project.setPurchaseId(purchaseUser.getId());
				}
			}
			project.setId(IdGen.uuid());
			project.setProjectNo(projectNo);
			project.setProjectName(projectErp.getProjectNameC());
			project.setProjectNameEn(projectErp.getProjectNameE());
			project.setDeliveryDate(projectErp.getCompletionTime()); //交期
			project.setOriginalDeliveryDate(projectErp.getCompletionTime());   //交期
			project.setDeliveryStatus(0);
			project.setWarningStatus(0);
			project.setImportance(0);
			project.setFinish(0);
			project.setSampleFinish(0);
			project.setStage(0);
			project.setPoDate(projectErp.getPoDate());  //PO日期
			project.setActualStartDate(projectErp.getDateSampleUploading());  //开工日期
			project.setScheduledDate(null);
			project.setSampleScheduledDate(projectErp.getDateSample());
			project.setOriginalSampleScheduledDate(projectErp.getDateSample());
			project.setCompanyName(projectErp.getCompanyName());
			project.setCreateDate(new Date());
			project.setPlantAnalysis(projectErp.getPlantAnalysis());
			project.setDetailStatus(0);
			project.setSampleFinishTime(null);
			project.setZhijian1(projectErp.getZhijian1());
			project.setZhijian2(projectErp.getZhijian2());
			project.setZhijian3(projectErp.getZhijian3());
			project.setCustomerName(projectErp.getCustomerName());
			project.setDateSampleUploading(projectErp.getDateSampleUploading());
			project.setCustomerGrade(projectErp.getCustomerGrade());

			//默认给样品交期，如果存在大货，保存大货交期
			//录入合同日期
			projectFactory.setSampleDate(projectErp.getDateSample());
			projectFactory.setContractDate(projectErp.getDateSampleUploading());


			project.setProjectMaterialProperties(projectErp.getProjectMaterialProperties());
			if(projectErp.getDateSample() != null){
				project.setDateSample(projectErp.getDateSample());
			}
			if(projectErp.getCompletionTime()!= null){
				project.setCompletionTime(projectErp.getCompletionTime());
			}
			if(projectErp.getMoneyDate()!=null){
				project.setMoneyDate(projectErp.getMoneyDate());
			}
			//项目启动日期
			Date poDate = projectErp.getPoDate();



				//保存下单工厂
				projectFactory.setFactoryId(projectErp.getFactoryId());
				projectFactory.setFactoryName(projectErp.getCompanyName());
				projectFactory.setProjectNo(projectNo);
				projectFactory.setCity(projectErp.getCity());
				projectFactory.setContractNo(projectErp.getContractNo());
				if(projectErp.getSupplementaryContract()!=null){
					projectFactory.setOrderNature(projectErp.getSupplementaryContract() == 1 ? 2 : 1); //补货或者正常
				}else{
					projectFactory.setOrderNature(1);
				}
				//projectFactoryService.insertSelective(projectFactory);

				projectService.addProject(project);
				List<Project> projectList=new ArrayList<Project>();
				projectList.add(project);
				projectDateTask.syncProjectDate(projectList);
			}



		return jsonResult;
	}
	/**
	 * 同步项目等级  2018.10.22
	 * @param request
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/synchProjectPlantAnalysis")
	@ResponseBody
	public JsonResult synchProjectPlantAnalysis(HttpServletRequest request){
		JsonResult jsonResult=new JsonResult();
		try {
			Project project = new Project();
//			project.setPlantAnalysis(0);
			project.setPageNumber(0);
			project.setPageSize(100000);
			List<Project> projectList = projectService.findProjectList(project);
			for (Project project2 : projectList) {
				if(StringUtils.isNotBlank(project2.getProjectNo())){
					projectService.updateProjectByErp(project2.getProjectNo());
				}
			}			
			jsonResult.setMessage("同步成功");
			jsonResult.setOk(true);
			return jsonResult;
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMessage("同步失败");
			jsonResult.setOk(false);
			return jsonResult;
		}
		
	}
	
	
	/**
	 * 发送任务（失联邮件提醒）
	 */
	@RequestMapping(value = "/sendMailTask")
	@ResponseBody
	public JsonResult sendMailTask(@RequestParam Map<String,String> map){
		JsonResult jsonResult=new JsonResult();
		try {			
//			String jsonStr = map.get("task");
//			Map<Object, Object> taskMap = SerializeUtil.JsonToMap(jsonStr);
			String description = map.get("description").toString();
			String url = map.get("url").toString();
			String sellName = map.get("sellName").toString();
			
			ProjectTask task = new ProjectTask();
			task.setProjectNo("");
			task.setInitiator("system");			
			task.setDescription(description);
			task.setFinishTime(DateFormat.addDays(new Date(), 7));
			task.setTaskStatus("0");
			task.setTaskType("11");
			task.setReturnUrl(url);
			task.setStartTime(new Date());
			task.setCreateTime(new Date());
			task.setAccepter(sellName);			
			projectTaskService.addProjectTask(task);
			
			jsonResult.setMessage("同步成功");
			jsonResult.setOk(true);
			return jsonResult;
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMessage("同步失败");
			jsonResult.setOk(false);
			return jsonResult;
		}
		
	}
	
}
