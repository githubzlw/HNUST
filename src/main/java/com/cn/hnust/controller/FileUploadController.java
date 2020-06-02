package com.cn.hnust.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.Progress;



@Controller
//@SessionAttributes("status")  
public class FileUploadController {

	
	
	@RequestMapping(value = "/progressStatus", method = RequestMethod.POST )
	@ResponseBody
	public Progress initCreateInfo(HttpSession session) {
				
		Progress status = (Progress) session.getAttribute("status");
		if(status==null){
			return status;
		}
		return status;
	}
}
