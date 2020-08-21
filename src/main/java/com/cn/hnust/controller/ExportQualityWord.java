package com.cn.hnust.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.enums.QualityImgTypeEnum;
import com.cn.hnust.enums.QualityStatusEnum;
import com.cn.hnust.enums.QualityTypeEnum;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.QualityPicExplain;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.print.QualityReportPrint;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.IQualityPicExplainService;
import com.cn.hnust.service.IQualityReportService;
import com.cn.hnust.util.CustomXWPFDocument;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.ExportWordUtil;
import com.cn.hnust.util.UploadAndDownloadPathUtil;

@Controller
public class ExportQualityWord {
	
	@Autowired
	private IQualityReportService iQualityReportService;
	@Autowired
	private IQualityPicExplainService iQualityPicExplainService;	
	@Autowired
	private IProjectService projectService;
	
	private static final Log LOG = LogFactory.getLog(QualityController.class);
	 /** 
     * 质检报告报表下载 
     */  
    @RequestMapping("/exportReport")  
    public void exportReport(HttpServletResponse response, HttpServletRequest request, String reportId) {  
    	try {  
 
    		List<QualityPicExplain> qualityPicExplains = iQualityPicExplainService.queryByReportId(Integer.parseInt(reportId));
    		QualityReport qualityReport =iQualityReportService.selectByPrimaryKey(Integer.parseInt(reportId));
    		if(qualityPicExplains != null){
    			int tl = qualityPicExplains.size();
    			
    			CustomXWPFDocument xdoc = new CustomXWPFDocument();  
    			StringBuffer titleBuffer=new StringBuffer();
    			titleBuffer.append("项目号:").append(qualityReport.getProjectNo()).append("\n").append("上传人:").append(qualityReport.getUser())
     	        .append("\n").append("时间:").append(DateFormat.date2String(qualityReport.getCreatetime()));
    			ExportWordUtil.createTitle(xdoc,titleBuffer.toString());
    			// 报表数据分析  
    	        XWPFTable dataReportTable = xdoc.createTable(0, 0);  
    	        StringBuffer sBuffer=new StringBuffer();
    	        sBuffer.append("报告结论:").append(QualityTypeEnum.getEnum(qualityReport.getType()).getValue())
    	        .append(QualityStatusEnum.getEnum(qualityReport.getStatus()).getValue()).append("\n")
    	        .append("检验数量说明:").append(qualityReport.getCheckExplain()).append("\n")
    	        .append("问题点说明:").append(qualityReport.getExplainCause()).append("\n")
    	        .append("包装说明:").append(qualityReport.getPackageExplain());
    	        
    	        
    	        String bgColor = "111111";  
    	        ExportWordUtil exportWord = new ExportWordUtil();
    	        exportWord.setCellText(xdoc, exportWord.getCellHight(dataReportTable, 0, 0, 1400), sBuffer.toString(), bgColor, 8600, 14, "仿宋", ParagraphAlignment.LEFT,true);
        		for (int i = 0; i < tl; i++) {
        			String url = qualityPicExplains.get(i).getPicName();      
        			url = "http://117.144.21.74:10010"+url; 
        			//url = "http://localhost:8099"+url;   
        			String remark = qualityPicExplains.get(i).getPicExplain();
        			byte[] base64Info1 = new ExportWordUtil().image2byte(url);    			
        			xdoc = new ExportWordUtil().export(base64Info1,i,xdoc,remark);
    			}
    			
 
        		Calendar c = Calendar.getInstance();  
    			String fileName = "Inspection Report" + c.get(Calendar.YEAR) + to2String(String.valueOf((c.get(Calendar.MONTH) + 1)))  
    			        + c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.HOUR_OF_DAY) + c.get(Calendar.MILLISECOND) + ".docx";  
    			//获取存放路径  
    			String classPath = UploadAndDownloadPathUtil.getProjectImg()+ File.separator + qualityReport.getProjectNo() + File.separator;  
    			System.out.println("==============report================" + classPath + fileName);  
    			FileOutputStream fos = new FileOutputStream(classPath + fileName);  
    			xdoc.write(fos);  
    			fos.close();
    			

    			File outFile = new File(classPath + fileName);  
    			InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));  
	            byte[] buffer = new byte[fis.available()];  
	            fis.read(buffer);  
	            fis.close();  
    	            // 清空response  
    	            response.reset();  
    	        // 设置response的Header  
    			fileName = URLEncoder.encode(fileName, "utf-8");                                  //这里要用URLEncoder转下才能正确显示中文名称  
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);  
                response.addHeader("Content-Length", "" + outFile.length());  
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
                response.setContentType("application/octet-stream");  
                toClient.write(buffer);  
                toClient.flush();
    		}
        } catch (Exception e) {  
			e.printStackTrace();
        }  
    }  
  
  
    
    /**
     * 生成excel质检报告
     * @Title exportReportExcel 
     * @Description
     * @param response
     * @param request
     * @param reportId
     * @return void
     */
    @RequestMapping("/exportReportExcel")  
    public void exportReportExcel(HttpServletResponse response, HttpServletRequest request, String reportId){  
    	
		try {
			QualityReport qualityReport =iQualityReportService.selectByPrimaryKey(Integer.parseInt(reportId));
			Project project = projectService.selectProjctDetails(qualityReport.getProjectNo());
			if(StringUtils.isNotEmpty(project.getProjectNo())){
				project.setProjectNo(project.getProjectNo().toUpperCase());
			}

			List<QualityPicExplain> details = iQualityPicExplainService.queryPicByType(Integer.parseInt(reportId), QualityImgTypeEnum.DETAIL_IMG.getCode());
			List<QualityPicExplain> bads = iQualityPicExplainService.queryPicByType(Integer.parseInt(reportId), QualityImgTypeEnum.BAD_IMG.getCode());
			List<QualityPicExplain> materials = iQualityPicExplainService.queryPicByType(Integer.parseInt(reportId), QualityImgTypeEnum.MATERIAL_IMG.getCode());
			List<QualityPicExplain> packages = iQualityPicExplainService.queryPicByType(Integer.parseInt(reportId), QualityImgTypeEnum.PACKAGE_IMG.getCode());
			List<QualityPicExplain> checks = iQualityPicExplainService.queryPicByType(Integer.parseInt(reportId), QualityImgTypeEnum.TABLE_IMG.getCode());
			String excelPath = QualityReportPrint.printExcel(request, request.getSession().getServletContext().getRealPath(File.separator), project, qualityReport, details, bads, materials, packages,checks);
	
			
			
//    		Calendar c = Calendar.getInstance();  
//			String fileName = "Inspection_Report" + c.get(Calendar.YEAR) + to2String(String.valueOf((c.get(Calendar.MONTH) + 1)))  
//			        + c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.HOUR_OF_DAY) + c.get(Calendar.MILLISECOND) + ".xls";
			File outFile = new File(excelPath);  
			InputStream  fis = new BufferedInputStream(new FileInputStream(outFile));  
            byte[] buffer = new byte[fis.available()];  
            fis.read(buffer);  
            fis.close();  
	        // 清空response  
	        response.reset();  
	        // 设置response的Header  
			String fileName = URLEncoder.encode(outFile.getName(), "utf-8");                                  //这里要用URLEncoder转下才能正确显示中文名称  
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);  
            response.addHeader("Content-Length", "" + outFile.length());  
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
            response.setContentType("application/octet-stream");  
            toClient.write(buffer);  
            toClient.flush();
            //删除生成的文档
            QualityReportPrint.deleteFile(outFile);
		
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("生成质检报告失败", e);
		}
    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    /** 
     * 解析base64，返回图片所在路径 
     * 
     * @param base64Info 
     * @return 
     */  
    private byte[] decodeBase64(String base64Info) {  
        if (StringUtils.isEmpty(base64Info)) {  
            return null;  
        }  
//        BASE64Decoder decoder = new BASE64Decoder();  
        Base64 decoder = new Base64();
        if (!base64Info.contains("base64,"))  
            return null;  
        String[] arr = base64Info.split("base64,");  
        return decoder.decode(arr[1]);  
    }  
  
    private String to2String(String str) {  
        if (str.length() > 2) {  
            str = str.substring(0, 2);  
        } else {  
            str = "0" + str;  
        }  
        return str;  
    }  
}
