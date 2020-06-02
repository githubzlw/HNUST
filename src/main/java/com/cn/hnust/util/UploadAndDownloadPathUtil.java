package com.cn.hnust.util;

import java.io.IOException;
import java.util.Properties;

public class UploadAndDownloadPathUtil {  
    private static Properties p = new Properties();  
  
    /** 
     * 读取properties配置文件信息 
     */  
    static{  
        try {  
            p.load(UploadAndDownloadPathUtil.class.getClassLoader().getResourceAsStream("upLoadAndDownLoadPath.properties"));  
        } catch (IOException e) {  
            e.printStackTrace();   
        }  
    }  
   
    
    public static String getProjectImg(){
    	return p.getProperty("project_img");  	
    }
    
    public static String getProjectTaskImg(){
    	return p.getProperty("project_task_img");  	
    }
    
    public static String getProductImg(){
    	return p.getProperty("product_img");  	
    }
    
    public static String getProjectStatic(){
    	return p.getProperty("project_static");  	
    }
    
    public static String getStaticUrl(){
    	return p.getProperty("static_url");  	
    }
    public static String getFilePath(){
    	return p.getProperty("file_path");  	
    }
    public static String getComplaintPath(){
    	return p.getProperty("project_complaint");  	
    }
    public static String getExcelHtmlPath(){
    	return p.getProperty("excel_html_path");  	
    }
    
   
}  
	
	



