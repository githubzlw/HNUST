package com.cn.hnust.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cn.hnust.component.SynConvertVideo;
import com.cn.hnust.pojo.FactoryQualityInspectionVideo;
import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.service.FactoryQualityInspectionVideoService;
import com.cn.hnust.service.ProjectFactoryService;
import com.cn.hnust.util.JsonResult;
import com.cn.hnust.util.OperationFileUtil;
import com.cn.hnust.util.UploadAndDownloadPathUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;





@Controller
@RequestMapping("/uploadVideo")
public class UploadVideoController {
	
	@Autowired
	private FactoryQualityInspectionVideoService factoryQualityInspectionVideoService;
	@Autowired
	private ProjectFactoryService projectFactoryService;
	
	
	@RequestMapping("/upload")
	@ResponseBody
	public JsonResult upload(HttpServletRequest request,HttpServletResponse response){  
			JsonResult jsonResult = new JsonResult();
			 try {
		 		 String projectNo = request.getParameter("projectNo");
		 		 String drawingName = request.getParameter("fileName");
		 		 String path1 = UploadAndDownloadPathUtil.getProjectImg()
		 				 + File.separator + projectNo + File.separator ; 
		 		 String path = UploadAndDownloadPathUtil.getProjectImg()
						+ File.separator + projectNo + File.separator + "video"+ File.separator ; 
		 		 String path2 = UploadAndDownloadPathUtil.getProjectImg()
		 				 + File.separator + projectNo + File.separator + "video"+ File.separator + "convert" + File.separator; 
		 		 File file1 = new File(path1);
				 File file = new File(path);
				 File file2 = new File(path2);
				 if  (!file1.exists()  && !file1.isDirectory())      
				 {         
					 file1.mkdir();     
				 }  	    
				 if  (!file.exists()  && !file.isDirectory())      
				 {         
					 file.mkdir();     
				 }  	    
				 if  (!file2.exists()  && !file2.isDirectory())      
				 {         
					 file2.mkdir();     
				 }  	    
			    //根据文件名获取上传文件的位置  数据库保存原始文件名称
		 		Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
		 		String fileName = "";
		 		String newNMame = "";
//		 		boolean convertFlag = false;
		 		if(!(multiFileUpload == null || multiFileUpload.size() == 0)){
		 			 fileName = multiFileUpload.get(drawingName);
		 			 newNMame = FilenameUtils.removeExtension(fileName)+".mp4";
		 			 if(!("mp4").equals(FilenameUtils.getExtension(fileName))){
		 				SynConvertVideo.sendRequest(path+fileName,path2,newNMame);
//		 				SynConvertVideo.sendRequest(path+fileName,path2,newNMame);
		 			 }		 			 
		 		} 				
			 	jsonResult.setOk(true);
			 	jsonResult.setData(newNMame);
		 		return jsonResult;
			 	} catch (IllegalStateException e) {
			 		e.printStackTrace();
			 		jsonResult.setOk(false);
			 		jsonResult.setMessage("上传失败");
			 		return jsonResult;
			 	} catch (IOException e) {
			 		e.printStackTrace();
			 		jsonResult.setOk(false);
			 		jsonResult.setMessage("上传失败");
			 		return jsonResult;
			 	} 			
	 }
	
	/**
	 * 保存视频
	 * @Title saveVideo 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */
	@RequestMapping("/saveVideo")
	@ResponseBody
	public JsonResult saveVideo(HttpServletRequest request,HttpServletResponse response){  
		  JsonResult jsonResult = new JsonResult();
			try {				
				String videoList = request.getParameter("videoList");				
				List<FactoryQualityInspectionVideo> videos= null;
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, FactoryQualityInspectionVideo.class);
				videos = objectMapper.readValue(videoList,javaType);
				for (FactoryQualityInspectionVideo factoryQualityInspectionVideo : videos) {
					factoryQualityInspectionVideo.setUploadTime(new Date());
				}
				if(videos!=null && videos.size()>0){
					factoryQualityInspectionVideoService.insertBatch(videos);
				}
				jsonResult.setOk(true);
			} catch (JsonParseException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
		 		jsonResult.setMessage("保存失败");
			} catch (JsonMappingException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
		 		jsonResult.setMessage("保存失败");
			} catch (IOException e) {
				e.printStackTrace();
				jsonResult.setOk(false);
		 		jsonResult.setMessage("保存失败");
			}
			return jsonResult;	
	}
	
	
	
	
	/**
	 * 跳转视频上传页
	 * @Title toUploadVideo 
	 * @Description 
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult
	 */
	@RequestMapping("/toUploadVideo")
	public String toUploadVideo(HttpServletRequest request,HttpServletResponse response){  
		String projectNo = request.getParameter("projectNo");
		//下单工厂列表
		List<ProjectFactory> factoryList = projectFactoryService.selectByProjectNo(projectNo);	
	    request.setAttribute("factoryList",factoryList);
	    
		return "detail_video_upload";
	}
	
	
	
	@RequestMapping("/deleteVideo")
	@ResponseBody
	public JsonResult deleteVideo(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		try {
            
			    String idStr = request.getParameter("id");
				int id = Integer.parseInt(idStr);
				factoryQualityInspectionVideoService.deleteByPrimaryKey(id);
				jsonResult.setOk(true);
				jsonResult.setMessage("删除成功");

			return jsonResult;
		} catch (Exception e) {
			jsonResult.setOk(false);
			jsonResult.setMessage("删除失败");
			e.printStackTrace();
			return jsonResult;
		}

	}
}
