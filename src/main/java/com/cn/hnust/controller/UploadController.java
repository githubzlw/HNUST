package com.cn.hnust.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cn.hnust.util.PropertisUtil;

@Controller
@RequestMapping("/file")
public class UploadController {
	
    @RequestMapping(value = "/upload")  
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) throws IllegalStateException, IOException {  
        System.out.println("开始"); 
        //原始名称  
        String originalFilename = file.getOriginalFilename();  
        //上传图片  
        if(file!=null && originalFilename!=null && originalFilename.length()>0){          
        //存储图片的物理路径  
        // String pic_path = "D:\\picture\\";  
        //获取相对路径
        //String path = this.getClass().getResource("/").getPath()+"picture/";
        PropertisUtil prop = new PropertisUtil("pic.properties");
        // String path = request.getServletContext().getRealPath("")+"/picture/";
        String path = prop.get("path");
        File dirFile = new File(path);  
        if(!dirFile.exists()){
        	dirFile.mkdirs();
        }
        //新的图片名称  
       String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));  
        //新图片  
        File newFile = new File(path+newFileName);  
        //将内存中的数据写入磁盘  
        file.transferTo(newFile);    
        //将新图片名称写到itemsCustom中    
        request.setAttribute("newFileName", path+newFileName);
        }
        return "result"; 
    }
}
