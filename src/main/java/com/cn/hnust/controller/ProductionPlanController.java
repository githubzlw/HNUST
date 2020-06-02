package com.cn.hnust.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.ProductionPlan;
import com.cn.hnust.service.IProductionPlanService;
import com.cn.hnust.util.IdGen;
import com.cn.hnust.util.JsonResult;

/**
 * 添加项目生产计划 Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/plan")
public class ProductionPlanController {

	@Autowired
	private IProductionPlanService productionPlanService;
	
	@RequestMapping("/addPlan")
	@ResponseBody
	public JsonResult addPlan(HttpServletRequest request,HttpServletResponse response){
		List<ProductionPlan> planList=new ArrayList<ProductionPlan>();
		ProductionPlan productionPlan1;
		ProductionPlan productionPlan2;
		ProductionPlan productionPlan3;
		ProductionPlan productionPlan4;
		ProductionPlan productionPlan5;
		String projectNo=request.getParameter("projectNo");
		String po1=request.getParameter("po1");
		String podate1=request.getParameter("podate1");
		String po2=request.getParameter("po2");
		String podate2=request.getParameter("podate2");
		String po3=request.getParameter("po3");
		String podate3=request.getParameter("podate3");
		String po4=request.getParameter("po4");
		String podate4=request.getParameter("podate4");
		String po5=request.getParameter("po5");
		String podate5=request.getParameter("podate5");
		if(StringUtils.isNotBlank(po1) && StringUtils.isNotBlank(podate1)){
			productionPlan1=new ProductionPlan();
			productionPlan1.setId(IdGen.uuid());
			productionPlan1.setPlanNode(po1);
			productionPlan1.setPlanDate(java.sql.Date.valueOf(podate1));
			productionPlan1.setProjectNo(projectNo);
			productionPlan1.setCreateDate(new Date());
			planList.add(productionPlan1);
		}
		if(StringUtils.isNotBlank(po2) && StringUtils.isNotBlank(podate2)){
			productionPlan2=new ProductionPlan();
			productionPlan2.setId(IdGen.uuid());
			productionPlan2.setPlanNode(po2);
			productionPlan2.setPlanDate(java.sql.Date.valueOf(podate2));
			productionPlan2.setProjectNo(projectNo);
			productionPlan2.setCreateDate(new Date());
			planList.add(productionPlan2);
		}
		if(StringUtils.isNotBlank(po3) && StringUtils.isNotBlank(podate3)){
			productionPlan3=new ProductionPlan();
			productionPlan3.setId(IdGen.uuid());
			productionPlan3.setPlanNode(po3);
			productionPlan3.setPlanDate(java.sql.Date.valueOf(podate3));
			productionPlan3.setProjectNo(projectNo);
			productionPlan3.setCreateDate(new Date());	
			planList.add(productionPlan3);
		}
		if(StringUtils.isNotBlank(po4) && StringUtils.isNotBlank(podate4)){
			productionPlan4=new ProductionPlan();
			productionPlan4.setId(IdGen.uuid());
			productionPlan4.setPlanNode(po4);
			productionPlan4.setPlanDate(java.sql.Date.valueOf(podate4));
			productionPlan4.setProjectNo(projectNo);
			productionPlan4.setCreateDate(new Date());	
			planList.add(productionPlan4);
		}       
		if(StringUtils.isNotBlank(po5) && StringUtils.isNotBlank(podate5)){
			productionPlan5=new ProductionPlan();
			productionPlan5.setId(IdGen.uuid());
			productionPlan5.setPlanNode(po4);
			productionPlan5.setPlanDate(java.sql.Date.valueOf(podate5));
			productionPlan5.setProjectNo(projectNo);
			productionPlan5.setCreateDate(new Date());	
			planList.add(productionPlan5);
		}		
		JsonResult json =new JsonResult();
		try {
			ProductionPlan productionPlan=new ProductionPlan();
			for (int i = 0; i < planList.size(); i++) {
				productionPlan=planList.get(i);
				productionPlanService.addProductionPlan(productionPlan);
			}
			json.setOk(true);
			json.setMessage("添加成功");
		} catch (Exception e) {
			json.setOk(false);
			json.setMessage("添加失败");
			e.printStackTrace();
		}
		return json;
	}
}
