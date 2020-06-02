package com.cn.hnust.excel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cn.hnust.pojo.InspectionPlan;

public class ReadInspectionExcel {
	private Workbook wb;
	private Sheet sheet;
	private Row row;
	private String gen;

	public ReadInspectionExcel(String filepath) {
	 try {
		if (filepath == null) {
			return;
		}	   
	   InputStream is = null;
       HttpURLConnection urlConnection = null;		
	   String ext = filepath.substring(filepath.lastIndexOf("."));
			
			 URL url = new URL(filepath);
			 urlConnection = (HttpURLConnection) url.openConnection();
			 urlConnection.connect();
			 //文件流，用于存储
			 is = urlConnection.getInputStream();
			if (".xls".equals(ext)) {
				wb = new HSSFWorkbook(is);
				gen = "xls";
			} else if (".xlsx".equals(ext)) {
				wb = new XSSFWorkbook(is);
				gen = "xlsx";
			} else {
				wb = null;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 读取Excel数据内容 
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 * @author polo
	 */
	public List<InspectionPlan> readExcelContent()throws Exception {
		if (wb == null) {			
			return null;
		}
		List<InspectionPlan> content = new ArrayList<InspectionPlan>();

		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			sheet = wb.getSheetAt(sheetIndex);
			// 得到总行数
			int rowNum = sheet.getLastRowNum();
			row = sheet.getRow(0);	
			//产品图号
			String productComponent = "";
			//项目号
			String projectNo = "";
			//标准行数
            int col_standard = 4;
            //抽样行数
            int col_sample = 100;
            //材料行数
            int col_material = 0;
            //包装行数
            int col_package = 0;
            //其他要求
            int col_other = 0;
            
            //类型
            String type = "";
			for (int i = 0; i < rowNum; i++) {
				String productStandards = "";
				
				InspectionPlan excelVO = new InspectionPlan();			
				row = sheet.getRow(i+1);
		        Object obj = getCellFormatValue(row.getCell(0));
		        if(obj!=null&&!"".equals(obj)){
		        	
		        	if(obj.toString().contains("项目号")){
		        		projectNo = getCellFormatValue(row.getCell(1)).toString();
		        	}		        	
		        	if(obj.toString().contains("零件号")){
		        		productComponent = getCellFormatValue(row.getCell(1)).toString();
		        	}
		        	if(obj.toString().contains("抽样")){
		        		col_sample = i;
		        		type = obj.toString();	        		
		        	}
		        	if(obj.toString().contains("材料")){
		        		col_material = i;
		        		type = obj.toString();		        		
		        	}
		        	if(obj.toString().contains("包装")){
		        		col_package = i;
		        		type = obj.toString();		        		
		        	}		        	
		        	if(obj.toString().contains("其他要求")){
		        		col_other = i;
		        		type = obj.toString();		        		
		        	}
		        	if(obj.toString().contains("总部检验要求")){
		        		break;	        		
		        	}
		        	
		        	//获取要求内容
		        	Object obj2 = getCellFormatValue(row.getCell(1));
		        	if(obj2!=null&&!"".equals(obj2) && i>col_sample){
		        		productStandards = obj2.toString();
		        	}
		        	
		        	
		        }	
		        Object obj1 = getCellFormatValue(row.getCell(2));
		        if(obj1!=null&&!"".equals(obj1)){
		        	if(obj1.toString().contains("标准")){
		        		col_standard = i;
		        	}
		        	if(i>col_standard && col_sample==100){
		        		productStandards = getCellFormatValue(row.getCell(2)).toString();
		        		if(StringUtils.isNotBlank(productStandards)){		        			
			        		if(row.getCell(1)!=null && StringUtils.isNotBlank(row.getCell(1).toString())){
			        			type = getCellFormatValue(row.getCell(1)).toString();
			        		}
		        		}		        		
		        	}
		        }
		        if(StringUtils.isNotBlank(productStandards)){
			        excelVO.setProductComponent(productComponent);
			        excelVO.setProductStandards(productStandards);
			        excelVO.setProjectNo(projectNo);
			        excelVO.setType(type);	     
			        content.add(excelVO);
		        }
			}
		}
	  
		//去除type中：特殊字符
		content.stream().filter(bean -> {
			if(bean.getType().contains(":"))
				bean.setType(bean.getType().replace(":", ""));
			    bean.setIsWork(0);
			    bean.setCreateDate(new Date());
			return true;
		  }).collect(Collectors.toList());
        System.out.println(content.toString());
		return content;		
	}

	/**
	 * 
	 * 根据Cell类型设置数据
	 * 
	 * @param cell
	 * @return
	 * @author polo
	 */
	private Object getCellFormatValue(Cell cell) {
		Object cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
			case Cell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (DateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式
					// data格式是带时分秒的：2013-7-10 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();
					// data格式是不带带时分秒的：2013-7-10
					Date date = cell.getDateCellValue();
					cellvalue = date;
				} else {// 如果是纯数字

					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:// 默认的Cell值
				cellvalue = "";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	/**  
	 * 判断字符串是否为数字  
	 *   
	 * @param str  
	 * @return  
	 */  
	public static boolean isNumeric(String str) {  
	    Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");  
	    Matcher isNum = pattern.matcher(str);  
	    if (!isNum.matches()) {  
	        return false;  
	    }  
	    return true;  
	}  

	
	/** 
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty 
     *  
     * @param obj 
     * @return 
     */  
    public static boolean isNullOrEmpty(Object obj) {  
        if (obj == null)  
            return true;  
  
        if (obj instanceof CharSequence)  
            return ((CharSequence) obj).length() == 0;  
  
        if (obj instanceof Collection)  
            return ((Collection) obj).isEmpty();  
  
        if (obj instanceof Map)  
            return ((Map) obj).isEmpty();  
  
        if (obj instanceof Object[]) {  
            Object[] object = (Object[]) obj;  
            if (object.length == 0) {  
                return true;  
            }  
            boolean empty = true;  
            for (int i = 0; i < object.length; i++) {  
                if (!isNullOrEmpty(object[i])) {  
                    empty = false;  
                    break;  
                }  
            }  
            return empty;  
        }  
        return false;  
    } 
	
    /**
     * 判断单元格是否为合并单元格，是的话则将单元格的值返回
     * @param listCombineCell 存放合并单元格的list
     * @param cell 需要判断的单元格
     * @param sheet sheet
     * @return
     */
    public  String isCombineCell(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet)
            throws Exception{
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        String cellValue = null;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)
            {
                if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)
                {
                    Row fRow = sheet.getRow(firstR);
                    Cell fCell = fRow.getCell(firstC);
                    cellValue = getCellValue(fCell);
                    break;
                }
            }
            else
            {
                cellValue = "";
            }
        }
        return cellValue;
    }
    
    
    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    public  String getCellValue(Cell cell){
        if(cell == null) return "";
        return cell.getStringCellValue();
    }
    
    
    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private  boolean isMergedRegion(Sheet sheet,int row ,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    public static void main(String[] args) throws Exception {
    	ReadInspectionExcel read = new ReadInspectionExcel(null);
    	read.readExcelContent();
	}
    
}
