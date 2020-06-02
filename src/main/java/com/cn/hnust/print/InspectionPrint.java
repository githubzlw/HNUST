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
import com.cn.hnust.pojo.InspectionReservation;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.QualityPicExplain;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.pojo.User;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.UploadAndDownloadPathUtil;



/**
 * @Description: 导出检验任务
 * @Author: polo
 * @CreateDate:2019/05/22
 */

public class InspectionPrint {

	private static File tempPath;

	/**
	 * 导出excel
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static String printExcel(HttpServletRequest request, List<InspectionReservation> inspectionList)throws Exception {
         //检验任务数量
		 int pro_tl = 0;
		 if(inspectionList != null && inspectionList.size() >0){
			 pro_tl = inspectionList.size();
		 }
		
        //创建workbook  
         HSSFWorkbook wb = new HSSFWorkbook();  
           
         //创建sheet  
         HSSFSheet sheet = wb.createSheet("检验任务");  
          
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
         cell.setCellValue("检验任务");  
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
            	 cell.setCellValue("当前状态");  
            	 cell = row.createCell(4); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("预计交期");  
            	 cell = row.createCell(5); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("船期");  
            	 cell = row.createCell(6); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("初检日期");  
            	 cell = row.createCell(7); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("工厂");  
            	 cell = row.createCell(8); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("类型");  
            	 cell = row.createCell(9); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("发布人");  
            	 cell = row.createCell(10); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("检验员");             	 
            	 cell = row.createCell(11); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("开箱比例");             	 
            	 cell = row.createCell(12); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("详情");             	 
        	 }
        	 row = sheet.createRow(i+2);    	
        	 cell = row.createCell(0); //获取单元格 
        	 cell.setCellValue(i+1);  
        	 cell.setCellStyle(boderStyle);
           	 cell = row.createCell(1); //获取单元格 
        	 cell.setCellValue(inspectionList.get(i).getProjectNo());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(2); //获取单元格 
        	 cell.setCellValue(inspectionList.get(i).getProjectName());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(3); //获取单元格 
        	 cell.setCellValue(inspectionList.get(i).getProduceStatus());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(4); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 if(inspectionList.get(i).getExpectedDelivery()!=null){
        		 cell.setCellValue(inspectionList.get(i).getExpectedDelivery());  
        	 }  
        	 cell = row.createCell(5); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 if(inspectionList.get(i).getShippingDate()!=null){
        		 cell.setCellValue(inspectionList.get(i).getShippingDate());  
        	 }       	
        	 cell = row.createCell(6); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 if(inspectionList.get(i).getFinishTime()!=null){
        		 cell.setCellValue(inspectionList.get(i).getFinishTime()); 
        	 }
        	 cell = row.createCell(7); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue(inspectionList.get(i).getInspectionAddress());
        	 cell = row.createCell(8); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue(inspectionList.get(i).getTestType()); 
        	 cell = row.createCell(9); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue(inspectionList.get(i).getInitiator());
        	 cell = row.createCell(10); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue(inspectionList.get(i).getAccepter());
        	 cell = row.createCell(11); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue(inspectionList.get(i).getOpenRate());
        	 cell = row.createCell(12); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue("0".equals(inspectionList.get(i).getTaskStatus()) ? "未完成" : ("1".equals(inspectionList.get(i).getTaskStatus())?"已完成":("2".equals(inspectionList.get(i).getTaskStatus())?"暂停":("3".equals(inspectionList.get(i).getTaskStatus())?"取消":""))));
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
	/**
	 * 导出excel
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static String printExcel1(HttpServletRequest request, List<User> list,String start,String end)throws Exception {
		
		
		//创建workbook  
		HSSFWorkbook wb = new HSSFWorkbook();  
		
		//创建sheet  
		HSSFSheet sheet = wb.createSheet(start+"至"+end+"检验项目延期情况表");  
		
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
		if(start==null&&end==null){
		cell.setCellValue("最近30天检验项目延期情况");
		}else if(end==null){
			cell.setCellValue(start+"至今检验项目延期情况");	
		}else if(start==null){
			cell.setCellValue("开始至"+end+"检验项目延期情况");
		}else{
			cell.setCellValue(start+"至"+end+"检验项目延期情况");
		}
		cell.setCellStyle(style);           
		style.setFont(font2);
		
		DecimalFormat df=new DecimalFormat("##.##");
		
		for (int i=0;i<list.size();i++){
			User user=list.get(i);
			if(i == 0){
				row = sheet.createRow(1); 
				cell = row.createCell(0); //获取单元格 
				cell.setCellValue("质检员"); 
				cell.setCellStyle(boderStyle);
				cell = row.createCell(1); //获取单元格
				if(start==null&&end==null){
				cell.setCellValue("最近30天检验项目数量"); 
				}else if(start==null){
					cell.setCellValue("开始至"+end+"检验项目数量");	
				}else if(end==null){
					cell.setCellValue(start+"至今检验项目数量");	
				}else{
					cell.setCellValue(start+"至"+end+"检验项目数量");	
				}
				cell.setCellStyle(boderStyle);
				cell = row.createCell(2); //获取单元格 
				cell.setCellValue("平均延期天数");  
				cell.setCellStyle(boderStyle);
				cell = row.createCell(3); //获取单元格 
				cell.setCellValue("按时完成百分率");  
				cell.setCellStyle(boderStyle);
				            	 
			}
			row = sheet.createRow(i+2);    	
			cell = row.createCell(0); //获取单元格 
			cell.setCellValue(user.getUserName());  
			cell.setCellStyle(boderStyle);
			cell = row.createCell(1); //获取单元格 
			cell.setCellValue(df.format(user.getInspectionItems()));  
			cell.setCellStyle(boderStyle);
			cell = row.createCell(2); //获取单元格 
			double num=Math.ceil(user.getAverageDelayDays());
			cell.setCellValue(df.format(num));  
			cell = row.createCell(3); //获取单元格 
			double num1=Math.ceil(user.getOnTimeCompletionRate());
			cell.setCellValue(df.format(num1)+"%");
			
			
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
