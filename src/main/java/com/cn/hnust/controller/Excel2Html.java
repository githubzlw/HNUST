package com.cn.hnust.controller;

import java.io.BufferedOutputStream;  
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;
import java.io.OutputStreamWriter;  
import java.net.HttpURLConnection;
import java.net.URISyntaxException;  
import java.net.URL;
import java.net.URLConnection;
import java.util.List;  
  

















import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.transform.OutputKeys;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerException;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
  

















import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;  
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;  
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;  
import org.apache.poi.hssf.usermodel.HSSFPictureData;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;  
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.ss.usermodel.WorkbookFactory;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;  

import com.cn.hnust.util.UploadAndDownloadPathUtil;
  
/** 
 * java将excel转换为HTML 
 * @author 
 */  
public class Excel2Html {  
    private static final Logger logger = Logger.getLogger(Excel2Html.class.getName());  
    private static final String DEFAULT_PICTURE_FOLDER = "pictures";  
    private static final String DEFAULT_HTML_TYPE = ".html";// 默认转换的HTML文件后缀  
  
    public static void main(String[] args) {  
        File outputFolder = null;  
        File outputPictureFolder = null;  
        // 转换后HTML文件存放位置  
		outputFolder = new File(UploadAndDownloadPathUtil.getExcelHtmlPath());  
		if (null != outputFolder) {  
		    // 转换后原excel中图片存放位置  
		    String outputPictureFolderPath = outputFolder.getAbsolutePath()  
		            + File.separator + DEFAULT_PICTURE_FOLDER;  
		    outputPictureFolder = new File(outputPictureFolderPath);  
		    outputPictureFolder.mkdir();  
		}  
        try {  
            // 被转换的excel文件  
           File convertedWordFile = new File("C://Users//zj//Desktop//11.xlsx"); 
           convert2Html(convertedWordFile, outputFolder, outputPictureFolder);  
            
            ExcelToHtml("C://Users//zj//Desktop//11.xlsx", "C://Users//zj//Desktop//",11);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    /**
     * 
     * excel转html，兼容03、07版本
     * @Title excel2Html 
     * @Description 
     * convertFile:excel文件
     * outPath：转换后html存储路径
     * @return void
     * @throws Exception 
     */
    public static String excel2Html(String convertFile,String outPath,Integer reportId) throws Exception{
    	
    	 String htmlPath = "";
    	 File outputFolder = null;  
         File outputPictureFolder = null;  
         // 转换后HTML文件存放位置  
         outputFolder = new File(outPath);
         if(!outputFolder.exists()  && !outputFolder.isDirectory())      
		 {         
        	 outputFolder.mkdir();     
		 }  	    
         if(outputFolder!=null){
        	  // 转换后原excel中图片存放位置  
 		    String outputPictureFolderPath = outputFolder.getAbsolutePath() + File.separator + DEFAULT_PICTURE_FOLDER;  
 		    outputPictureFolder = new File(outputPictureFolderPath);  
 		    outputPictureFolder.mkdir();   
         }
         String extension = FilenameUtils.getExtension(convertFile);
         if(StringUtils.isBlank(extension)){
        	 throw new Exception("未获取到正确文件");
         }
         if("xls".equals(extension)){
        	// 被转换的excel文件  
             File convertedWordFile = new File(convertFile); 
             htmlPath = convert2Html(convertedWordFile, outputFolder, outputPictureFolder);  
         }else if("xlsx".equals(extension)){
        	 htmlPath = ExcelToHtml(convertFile, outPath,reportId);
         }    
         return htmlPath;
    }
    
    
    
    
  
    public static void writeFile(String content, String path) {  
        FileOutputStream fos = null;  
        BufferedWriter bw = null;  
        try {  
            File file = new File(path);  
            fos = new FileOutputStream(file);  
            bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));  
            bw.write(content);  
        } catch (FileNotFoundException fnfe) {  
            fnfe.printStackTrace();  
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
        } finally {  
            try {  
                if (bw != null)  
                    bw.close();  
                if (fos != null)  
                    fos.close();  
            } catch (IOException ie) {  
            }  
        }  
    }  
  
    public static Workbook getWorkbook(File file) {  
        Workbook workbook = null;  
        try {  
            if (null != file && file.exists()) {  
                workbook = WorkbookFactory.create(file);  
            }  
        } catch (IOException e) {  
            logger.error("IOException in getWorkbook:", e);  
        } catch (InvalidFormatException e) {  
            logger.error("InvalidFormatException in getWorkbook:", e);  
        }  
        return workbook;  
    }  
  
    /** 
     * @param excelFile 被转换的excel文件 
     * @param outputFolder 转换后HTML文件存放位置 
     * @param outputPictureFolder 转换后原word中图片存放位置 
     * @throws TransformerException 
     * @throws IOException 
     * @throws ParserConfigurationException 
     */  
    public static String convert2Html(File excelFile, File outputFolder,  
            final File outputPictureFolder) throws TransformerException,  
            IOException, ParserConfigurationException {  
        // 创建excel ExcelToHtmlConverter对象  
        ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(  
                DocumentBuilderFactory.newInstance().newDocumentBuilder()  
                        .newDocument());  
        excelToHtmlConverter.setOutputColumnHeaders(false);  
        excelToHtmlConverter.setOutputRowNumbers(false);  
          
        // 创建POI工作薄对象  
        HSSFWorkbook workbook = (HSSFWorkbook) getWorkbook(excelFile);  
        excelToHtmlConverter.processWorkbook(workbook);  
  
        Document htmlDocument = excelToHtmlConverter.getDocument();  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        DOMSource domSource = new DOMSource(htmlDocument);  
        StreamResult streamResult = new StreamResult(out);  
  
        TransformerFactory tf = TransformerFactory.newInstance();  
        Transformer serializer = tf.newTransformer();  
        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");  
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");  
        serializer.setOutputProperty(OutputKeys.METHOD, "html");  
        serializer.transform(domSource, streamResult);  
  
        writePicures(workbook.getAllPictures(), outputPictureFolder.getAbsolutePath()+ File.separator );  
        writeFile(new String(out.toByteArray()), outputFolder.getAbsolutePath()  
                + File.separator + excelFile.getName().split(".")[0] + DEFAULT_HTML_TYPE);  
        out.close();  
        System.out.println("转换成功");
        return outputFolder.getAbsolutePath() + File.separator + excelFile.getName().split(".")[0] + DEFAULT_HTML_TYPE;
    }  
  
    public static void writePicures(List<HSSFPictureData> pics,String picturesFolder)  
            throws IOException {  
        if (pics != null) {  
            int count = 0;  
            for (int i = 0; i < pics.size(); i++) {  
                HSSFPictureData picData = pics.get(i);  
                if (null == picData) {  
                    continue;  
                }  
                byte[] bytes = picData.getData();  
                FileOutputStream output = new FileOutputStream(picturesFolder + count  
                        + "." + picData.suggestFileExtension());  
                BufferedOutputStream writer = new BufferedOutputStream(output);  
                writer.write(bytes);  
                writer.flush();  
                writer.close();  
                output.close();  
                count++;  
            }  
        }  
    }  
    
    
    
    
    
    /*excel07转html
	 * filename:要读取的文件所在文件夹
	 * filepath:文件名
	 * htmlname:生成html名称
	 * path:html存放路径
	 *
     */
		public static String ExcelToHtml (String filePath,String outPath,Integer reportId) throws Exception{
			    String html="";
			    String htmlname = reportId+".html";
			  	Workbook workbook = null;
			    URL url = new URL(filePath);
			    // 打开连接
//			    URLConnection con = url.openConnection();
			    HttpURLConnection con = (HttpURLConnection) url.openConnection();
			    //设置请求超时为120s
			    con.setConnectTimeout(120*1000);
			 // 设置通用的请求属性
//				con.setRequestProperty("accept", "*/*");
//				con.setRequestProperty("connection", "Keep-Alive");
//				con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//				con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
				con.connect();
			    // 输入流
			    InputStream is = con.getInputStream();
//			    OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(), "utf-8");
		       try {		       	
		           workbook =  new XSSFWorkbook(is);
		           for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
		               Sheet sheet = workbook.getSheetAt(numSheet);
		               if (sheet == null) {
		                   continue;
		               }
//		               html+="<!DOCTYPE html><html><head><title></title><meta charset=\"UTF-8\"/><head></head><body>";
		
		               int firstRowIndex = sheet.getFirstRowNum();
		               int lastRowIndex = sheet.getLastRowNum();
		               html+="<table border='1' align='left'  style='border-collapse: collapse;border-spacing: 0;width:300%;'>";
		               Row firstRow = sheet.getRow(firstRowIndex);
		               for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
		                   Cell cell = firstRow.getCell(i);
		                   String cellValue = getCellValue(cell, true);
		                   html+="<th style='width: 300px;'>" + cellValue + "</th>";
		               }
		
		
		               //行
		               for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
		                   Row currentRow = sheet.getRow(rowIndex);
		                   html+="<tr>";
		                   if(currentRow!=null){
		                   	
		                   	int firstColumnIndex = currentRow.getFirstCellNum(); 
		                   	int lastColumnIndex = currentRow.getLastCellNum();
		                   	if(firstColumnIndex<0){
		                   		continue;
		                   	}
		                   	//列
		                   	for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
		                   		Cell currentCell = currentRow.getCell(columnIndex);
		                   		String currentCellValue = getCellValue(currentCell, true);
		                   		if(StringUtils.isNotBlank(currentCellValue)){
		                   			html+="<td>"+currentCellValue + "</td>";
		                   		}else{
		                   			html+="<td>"+currentCellValue + "</td>";
		                   		}		                   		
		                   	}
		                   }else{
		                   	 html+=" ";
		                   }
		                   html+="</tr>";
		               }
		               html+="</table>";
		           
		
		               ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			       	     DOMSource domSource = new DOMSource ();
			       	     StreamResult streamResult = new StreamResult (outStream);
			       	     
			       	     TransformerFactory tf = TransformerFactory.newInstance();
			       	     Transformer serializer = tf.newTransformer();
			       	     serializer.setOutputProperty (OutputKeys.ENCODING, "utf-8");
			       	     serializer.setOutputProperty (OutputKeys.INDENT, "yes");
			       	     serializer.setOutputProperty (OutputKeys.METHOD, "html");
			       	     serializer.transform (domSource, streamResult);
			       	     outStream.close();
			       	     FileUtils.writeStringToFile(new File (outPath, htmlname), html, "utf-8");
		           }
		       } catch (Exception e) {
		           e.printStackTrace();
		       }finally{
		    	   workbook.close();
		    	   is.close();
		    	   con.disconnect(); 
		       }
		  	     return html;
		}
		
		
		
		 /**
	     * 读取单元格
	     * 
	     */
	    private static String getCellValue(Cell cell, boolean treatAsStr) {
	        if (cell == null) {
	            return "";
	        }

	        if (treatAsStr) {
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	        }

	        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(cell.getBooleanCellValue());
	        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	            return String.valueOf(cell.getNumericCellValue());
	        } else {
	            return String.valueOf(cell.getStringCellValue());
	        }
	    }


}  