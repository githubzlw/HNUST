package com.cn.hnust.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.DeductionForm;

import com.cn.hnust.pojo.SalesAndMerchandisingScore;
import com.cn.hnust.service.IDeductionFormService;
import com.cn.hnust.service.ISalesAndMerchandisingScoreService;
import com.cn.hnust.util.JsonResult;

@Controller
@RequestMapping("/deduction")
public class DeductionFormController {
	@Autowired
	private IDeductionFormService deductionFormService;
	@Resource
	private ISalesAndMerchandisingScoreService salesAndMerchandisingScoreService;
	@SuppressWarnings("finally")
	@RequestMapping("/updateDeDuction")
	@ResponseBody
	public JsonResult updateDeDuction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JsonResult jsonResult = new JsonResult();
		try{
		String saleName = request.getParameter("saleName");
		String date = request.getParameter("date");
		String num = request.getParameter("num");
		String number = request.getParameter("number");
		DeductionForm deductionForm=new DeductionForm();
		deductionForm.setDate(date);
		deductionForm.setName(saleName);
		int deductionFrequency=Integer.parseInt(number)+Integer.parseInt(num);
		deductionForm.setDeductionFrequency(deductionFrequency);
		DeductionForm from=deductionFormService.getOne(saleName,date);//获取当月扣分次数
			
		if(from!=null){
			int id=deductionFormService.update(deductionForm);	
		}else{
		int id=deductionFormService.insertSelective(deductionForm);
		}
		SalesAndMerchandisingScore score=new SalesAndMerchandisingScore();
		int number1=5;
		DeductionForm from1=deductionFormService.getOne(saleName,date);//获取当月扣分次数
		
		if(from1!=null){
			number1=number1-2*from1.getDeductionFrequency();
		}
		if(number1<0){
			number1=0;
		}
		score.setDate(date);
		score.setSaleName(saleName);
        score.setTechnologicalProcess((double)number1);
        int total=salesAndMerchandisingScoreService.updateOneProcess(score);
        salesAndMerchandisingScoreService.updateAllScoreDocumentary(date);//修改跟单总分
		jsonResult.setOk(true);
		jsonResult.setMessage("设置成功");
		return jsonResult;
		} catch (Exception e) {
		e.printStackTrace();
		jsonResult.setOk(false);
		jsonResult.setMessage(e.getMessage());
		} finally {
			return jsonResult;
		}
	}

}
