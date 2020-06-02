package com.cn.hnust.print;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.util.IOUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.enums.ProjectAnalysisEnum;
import com.cn.hnust.enums.QualityStatusEnum;
import com.cn.hnust.enums.QualityTypeEnum;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.QualityPicExplain;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.UploadAndDownloadPathUtil;



/**
 * @Description: 导出项目
 * @Author: polo
 * @CreateDate:2019/05/05
 */

public class ProjectPrint {

	private static File tempPath;

	/**
	 * pdf打印,使用excel编辑，生成pdf
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static String printExcel(HttpServletRequest request, List<Project> projectList)throws Exception {
         //进行中项目数量
		 int pro_tl = 0;
		 if(projectList != null && projectList.size() >0){
			 pro_tl = projectList.size();
		 }
		
        //创建workbook  
         HSSFWorkbook wb = new HSSFWorkbook();  
           
         //创建sheet  
         HSSFSheet sheet = wb.createSheet("项目时间管理");  
          
         HSSFFont font = wb.createFont();
         font.setFontName("黑体");
         font.setFontHeightInPoints((short) 16);//设置字体大小
         
         
         HSSFFont font2 = wb.createFont();
         font2.setFontName("黑体");
         font2.setFontHeightInPoints((short) 16);//设置字体大小
         font2.setColor(HSSFColor.RED.index);

         
         //创建行row:添加表头0行  
         HSSFRow row = sheet.createRow(0);  
         HSSFCellStyle  style = wb.createCellStyle();      
         style.setFont(font);
         
         HSSFCellStyle cellStyle = wb.createCellStyle();
         HSSFDataFormat format= wb.createDataFormat();
         cellStyle.setDataFormat(format.getFormat("yyyy/m/d"));
         cellStyle.setAlignment(HorizontalAlignment.CENTER);
         cellStyle.setBorderBottom(BorderStyle.THIN);
         cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
         cellStyle.setBorderLeft(BorderStyle.THIN);
         cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
         cellStyle.setBorderRight(BorderStyle.THIN);
         cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
         cellStyle.setBorderTop(BorderStyle.THIN);
         cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
         
         //创建边框样式
         HSSFCellStyle boderStyle = wb.createCellStyle();
         boderStyle.setAlignment(HorizontalAlignment.CENTER);
         boderStyle.setBorderBottom(BorderStyle.THIN);
         boderStyle.setBottomBorderColor(HSSFColor.BLACK.index);
         boderStyle.setBorderLeft(BorderStyle.THIN);
         boderStyle.setLeftBorderColor(HSSFColor.BLACK.index);
         boderStyle.setBorderRight(BorderStyle.THIN);
         boderStyle.setRightBorderColor(HSSFColor.BLACK.index);
         boderStyle.setBorderTop(BorderStyle.THIN);
         boderStyle.setTopBorderColor(HSSFColor.BLACK.index);
        
         
         //退税金额使用样式
         HSSFCellStyle lastStyle = wb.createCellStyle();
         lastStyle.setAlignment(HorizontalAlignment.CENTER);
         lastStyle.setBorderBottom(BorderStyle.THIN);
         lastStyle.setBottomBorderColor(HSSFColor.BLACK.index);
         lastStyle.setBorderLeft(BorderStyle.THIN);
         lastStyle.setLeftBorderColor(HSSFColor.BLACK.index);
         lastStyle.setBorderRight(BorderStyle.THIN);
         lastStyle.setRightBorderColor(HSSFColor.BLACK.index);
         lastStyle.setBorderTop(BorderStyle.THIN);
         lastStyle.setTopBorderColor(HSSFColor.BLACK.index);
         lastStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// 设置背景色
         lastStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    
         //创建单元格  
         HSSFCell cell = row.createCell(0); //第一个单元格  
         cell.setCellValue("进行中项目");  
         cell.setCellStyle(style);           
         style.setFont(font2);
         
         
         //相同的工厂数
         int factoryNum = 0;
         int startCol = 0;
         String factoryName = "";
         DecimalFormat df=new DecimalFormat("¥###,##0.00");
         
         for (int i=0;i<pro_tl;i++){
        	 if(i == 0){
        		 row = sheet.createRow(1); 
            	 cell = row.createCell(0); //获取单元格 
            	 cell.setCellValue("序号"); 
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(1); //获取单元格 
            	 cell.setCellValue("项目号");  
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(2); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("项目名");  
            	 cell = row.createCell(3); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("项目等级");  
            	 cell = row.createCell(4); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("客户名");  
            	 cell = row.createCell(5); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("工厂名");  
            	 cell = row.createCell(6); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("客户付款日期");  
            	 cell = row.createCell(7); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("上次送样日期");  
            	 cell = row.createCell(8); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("目标样品完成日期");  
            	 cell = row.createCell(9); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("目前客户态度");  
            	 cell = row.createCell(10); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("项目状态");             	 
        	 }
        	 row = sheet.createRow(i+2);    	
        	 cell = row.createCell(0); //获取单元格 
        	 cell.setCellValue(i+1);  
        	 cell.setCellStyle(boderStyle);
           	 cell = row.createCell(1); //获取单元格 
        	 cell.setCellValue(projectList.get(i).getProjectNo());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(2); //获取单元格 
        	 cell.setCellValue(projectList.get(i).getProjectName());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(3); //获取单元格 
        	 cell.setCellValue(ProjectAnalysisEnum.getEnum(projectList.get(i).getPlantAnalysis()).getValue());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(4); //获取单元格 
        	 cell.setCellValue(projectList.get(i).getCustomerName());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(5); //获取单元格 
        	 cell.setCellValue(projectList.get(i).getCompanyName());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(6); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 if(projectList.get(i).getMoneyDate()!=null){
        		 cell.setCellValue(projectList.get(i).getMoneyDate()); 
        	 }
        	 cell = row.createCell(7); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue("");
        	 cell = row.createCell(8); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 if(projectList.get(i).getSampleFinishTime()!=null){
        		 cell.setCellValue(projectList.get(i).getSampleFinishTime()); 
        	 }
        	 cell = row.createCell(9); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue("");
        	 cell = row.createCell(10); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue(projectList.get(i).getReport());
         }

         //自动调整列宽
         int i=0;
         while (i<16) {
        	 sheet.autoSizeColumn((short)i);
        	 i++;
		 }
		
		String paths = UploadAndDownloadPathUtil.getFilePath();

		tempPath = new File(paths);
		// deleteFile(tempPath);
		if (!tempPath.exists() || !tempPath.isDirectory()) {
			tempPath.mkdir(); // 如果不存在，则创建该文件夹
		}
		FileOutputStream fs = new FileOutputStream(paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls", false);
		wb.write(fs);
		fs.close();		

		return paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls";
	}


}
