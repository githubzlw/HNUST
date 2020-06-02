package com.cn.hnust.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.cn.hnust.pojo.QualityPicExplain;

public class ZipUtils {
    
    private ZipUtils(){
    }
    


    public static void doZip(List<QualityPicExplain> picList, String filePath,String excelPath,String fileName) throws IOException {

    	existsDelete(filePath,fileName);
    	ZipOutputStream  out = new ZipOutputStream(new FileOutputStream(filePath+fileName));
    	    	
    	
    	for(int i=0;i<picList.size();i++){
    		
    		String entryName = i+1+".jpg";
    		ZipEntry entry = new ZipEntry(entryName);
    	    out.putNextEntry(entry);
    	    int len = 0 ;
    	    byte[] buffer = new byte[1024];
    	    
    		URL url = new URL(UploadAndDownloadPathUtil.getStaticUrl()+picList.get(i).getPicName());
			HttpURLConnection  httpURLConnection = (HttpURLConnection) url.openConnection();
			// 设置网络连接超时时间
			httpURLConnection.setConnectTimeout(3000);
			// 设置应用程序要从网络连接读取数据
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestMethod("GET");
			int responseCode = httpURLConnection.getResponseCode();
			InputStream in = httpURLConnection.getInputStream();   
			out.setEncoding("utf-8"); 
    	     while ((len = in.read(buffer)) > 0) {
    	             out.write(buffer, 0, len);
    	             out.flush();
    	         }

	         
//    	     if(i==0){
//    	    	    File file = new File(excelPath);
//    	    	    String name = file.getName();
//    	    	    String path = UploadAndDownloadPathUtil.getStaticUrl()+UploadAndDownloadPathUtil.getProjectStatic()+ "/" + name;
//    	    	    url = new URL(path);
//    				httpURLConnection = (HttpURLConnection) url.openConnection();
//    				// 设置网络连接超时时间
//    				httpURLConnection.setConnectTimeout(3000);
//    				// 设置应用程序要从网络连接读取数据
//    				httpURLConnection.setDoInput(true);
//    				httpURLConnection.setRequestMethod("GET");
//    				in = httpURLConnection.getInputStream();   
//    				out.setEncoding("utf-8"); 
//    	    	     while ((len = in.read(buffer)) > 0) {
//    	    	             out.write(buffer, 0, len);
//    	    	             out.flush();
//    	    	         }
//    	    }
    	     
	         out.closeEntry();
	         in.close();
    	}
    	

    	
    	
    	

        out.close();
        	
    }

    
    public static void existsDelete(String filePath,String fileName) {
		File pathFile = new File(filePath);
		if(!pathFile.exists() || pathFile.isFile()) {
			if(!pathFile.mkdirs()){
				return;
			};
			
		}
		for(File file:pathFile.listFiles()) {
			if(file.isFile() && fileName.equals(file.getName())) {
				file.delete();
				break;
			}
		}
	}
    
    
    

}
