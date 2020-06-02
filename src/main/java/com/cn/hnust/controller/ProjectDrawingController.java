package com.cn.hnust.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cn.hnust.pojo.ProjectDrawing;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.service.IProjectDrawingService;
import com.cn.hnust.service.IProjectTaskService;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.IdGen;

@Controller
@RequestMapping("/projectDrawing")
public class ProjectDrawingController {
   
	@Autowired
	private IProjectDrawingService projectDrawingService;
	@Autowired
	private IProjectTaskService projectTaskService;
	private static final Log LOG = LogFactory.getLog(ProjectDrawingController.class);
	
	@RequestMapping(value = "/synchProjectDrawing", method = RequestMethod.POST)
	public void synchProjectDrawing(HttpServletRequest request,@RequestParam Map<String,String> map){
		 try {
			String jsonStr = map.get("projectDrawing");
			 JSONArray json = JSONArray.fromObject(jsonStr); // 首先把字符串转成 JSONArray  对象
			 List<ProjectDrawing> projectDrawingList=new ArrayList<ProjectDrawing>();
			 if(json.size()>0){
				  for(int i=0;i<json.size();i++){
				    JSONObject obj = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
				    String projectNo = (String) obj.get("projectNo");	
				    String drawingName=(String) obj.get("drawingName");
				    Integer category=(Integer) obj.get("category");
				    String remark = (String) obj.get("remark");  //图纸备注
				    String uploadTime = (String) obj.get("uploadTime");
				    String name = (String) obj.get("reportName");
					ProjectDrawing projectDrawing=new ProjectDrawing();
					projectDrawing.setId(IdGen.uuid());
					projectDrawing.setProjectNo(projectNo);
					projectDrawing.setDrawingName(drawingName);
					projectDrawing.setRemark(remark);
					projectDrawing.setCreateDate(new Date());
					projectDrawing.setCategory(String.valueOf(category));
					projectDrawing.setName(name);
					if(StringUtils.isNotBlank(uploadTime)){
						projectDrawing.setUploadTime(DateUtil.StrToDate(uploadTime));
					}
					projectDrawingList.add(projectDrawing);
				 }
			 }
			 //1.查询原有的数据
			 //2.将同步的数据与原有数据进行对比
			 List<ProjectDrawing> projectList=projectDrawingService.selectAllProjectDrawing();
			 for(int i=0;i<projectDrawingList.size();i++){
				ProjectDrawing  projectDrawing= projectDrawingList.get(i);
				ProjectDrawing project = null;
				boolean bool = false;
				for (int j = 0; j < projectList.size(); j++) {
					project = projectList.get(j);
					//项目号匹配成功和名字匹配成功不做任何操作
					if(projectDrawing.getProjectNo().equals(project.getProjectNo())&& projectDrawing.getDrawingName().equals(project.getDrawingName())){
						bool = true;
						break;
					}
				}
				//项目号匹配不成功,将不匹配的项目号添加到项目表里面
				if(!bool){
					ProjectDrawing insertProject = new ProjectDrawing();
					insertProject.setId(IdGen.uuid());
					insertProject.setProjectNo(projectDrawing.getProjectNo());
					insertProject.setDrawingName(projectDrawing.getDrawingName());
					insertProject.setCategory(projectDrawing.getCategory());
					insertProject.setCreateDate(new Date());
					insertProject.setRemark(projectDrawing.getRemark());
					insertProject.setUploadTime(projectDrawing.getUploadTime());
					insertProject.setName(projectDrawing.getName());
					projectDrawingService.addProjectDrawing(insertProject);
					
					
					
					
					  String projectNoId=IdGen.uuid();
					  //1.给检验员创建任务和录入一个检验记录 
					  if("4".equals(projectDrawing.getCategory())){
						  /*ProjectTask projectTask=new ProjectTask();
						  projectTask.setProjectNo(projectDrawing.getProjectNo());
						  projectTask.setProjectNoId(projectNoId);
					      projectTask.setInitiator("system");
					      projectTask.setTaskStatus("0");
					      projectTask.setTaskType("1");
					      projectTask.setAccepter("DeanZhang");
					      projectTask.setDescription("项目受控图纸已上传,请编辑项目检验计划! 步骤:编制检验计划-王工审核-质检补充-阳工审核");
					      projectTask.setCreateTime(new Date());
					      projectTask.setStartTime(new Date());
					      projectTask.setFinishTime(DateFormat.addDays(new Date(), 5));
					      projectTaskService.addProjectTask(projectTask);*/
					  }
				}
			  }
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("同步图纸数据synchProjectDrawing失败",e);
		}
	}
}
