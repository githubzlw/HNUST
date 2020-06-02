package com.cn.hnust.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;







import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.DingTalkMileStone;
import com.cn.hnust.service.IProjectDrawingService;
import com.cn.hnust.service.IProjectService;
import com.cn.hnust.service.ShippingConfirmationService;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.SerializeUtil;
/**
 * 提供给其他系统的数据
 * @author polo
 * 2019年03月04日
 */
@Controller
@RequestMapping("/port")
public class GetDataPort {
	

        private Logger logger = LoggerFactory.getLogger(this.getClass());	



	 @Autowired
	  private IProjectService projectService;     
	  @Autowired
	  private IProjectDrawingService projectDrawingService;     
	  @Autowired
	  private ShippingConfirmationService shippingConfirmationService;     
	  
	  
  

	  /**
	   * 获取dingtalk里程碑json数据
	   * @author polo
	   * 2019年03月04日
	   *
	   */
	  @ResponseBody
	  @RequestMapping("/getMileStoneJson")	 
	  public String getProjectInfo(String jsonString){
		  try {
			
		       // String	jsonStr = "{\"dingtalk_smartwork_bpms_processinstance_list_response\":{\"result\":{\"ding_open_errcode\":0,\"result\":{\"list\":{\"process_instance_top_vo\":[{\"business_id\":\"201903061457000372107\",\"cc_userid_list\":{\"string\":[\"0541283264532669437\",\"05413816132428110\",\"05413547031185225278\",\"12406613501197197725\"]},\"create_time\":\"2019-03-06 14:57:38\",\"finish_time\":\"2019-03-06 14:59:36\",\"form_component_values\":{\"form_component_value_vo\":[{\"name\":\"1.项目号SHS\",\"value\":\"20858\"},{\"name\":\"2.项目启动邮件\",\"value\":\"11\"},{\"name\":\"5.价格是否有问题？\",\"value\":\"无\"},{\"name\":\"6.初步工厂选择\",\"value\":\"安平哲瀚\"},{\"name\":\"7.预计的生产工艺\",\"value\":\"丝网 - 卷圆 - 焊接 - 清洗 - 酸洗钝化 - 包装\"},{\"name\":\"8.确定要视频的关键步骤\",\"value\":\"焊接 \"},{\"name\":\"9.强制要求\",\"value\":\"提供材质报告及丝网线径及宽度检验报告\"},{\"name\":\"10.里程碑a\",\"value\":\"丝网采购\"},{\"name\":\"里程碑a日期\",\"value\":\"2019-03-10\"},{\"name\":\"里程碑b\",\"value\":\"连接件加工\"},{\"name\":\"里程碑b日期\",\"value\":\"2019-03-12\"},{\"name\":\"里程碑c\",\"value\":\"丝网焊接\"},{\"name\":\"里程碑c日期\",\"value\":\"2019-03-15\"},{\"name\":\"里程碑d\",\"value\":\"两端焊接\"},{\"name\":\"里程碑d日期\",\"value\":\"2019-03-18\"},{\"name\":\"里程碑e\",\"value\":\"清洗包装\"},{\"name\":\"里程碑e日期\",\"value\":\"2019-03-20\"}]},\"originator_dept_id\":\"14019490\",\"originator_userid\":\"06076009391348503023\",\"process_instance_id\":\"8b275c1a-d652-46a2-995d-54838008f4f7\",\"process_instance_result\":\"agree\",\"status\":\"COMPLETED\",\"title\":\"徐平提交的采购.项目生产启动\"}]},\"next_cursor\":2},\"success\":true},\"request_id\":\"4wtsm4zskwb0\"}}";		  
			  
		        String jsonStr = "{\"dingtalk_smartwork_bpms_processinstance_list_response\":{\"result\":{\"ding_open_errcode\":0,\"result\":{\"list\":{\"process_instance_top_vo\":[{\"business_id\":\"201903061659000108745\",\"cc_userid_list\":{\"string\":[\"0541283264532669437\",\"05413816132428110\",\"05413547031185225278\",\"12406613501197197725\"]},\"create_time\":\"2019-03-06 16:59:10\",\"finish_time\":\"2019-03-07 10:09:07\",\"form_component_values\":{\"form_component_value_vo\":[{\"name\":\"1.项目号SHS\",\"value\":\"21012\"},{\"name\":\"2.项目启动邮件\",\"value\":\"11\"},{\"name\":\"3.预计合同签订时间\",\"value\":\"2019-03-08\"},{\"name\":\"4.当前问题\",\"value\":\"和工厂沟通模具结构，采用\"},{\"name\":\"5.价格是否有问题？\",\"value\":\"产品价格偏低，勉强够本。\"},{\"name\":\"6.初步工厂选择\",\"value\":\"轩祥\"},{\"name\":\"7.预计的生产工艺\",\"value\":\"注塑\"},{\"name\":\"8.确定要视频的关键步骤\",\"value\":\"模具制作过程视频，试模视频\"},{\"name\":\"9.强制要求\",\"value\":\"开模报告\"},{\"name\":\"10.里程碑a\",\"value\":\"签订合同\"},{\"name\":\"里程碑a日期\",\"value\":\"2019-03-07\"},{\"name\":\"里程碑b\",\"value\":\"出开模报告\"},{\"name\":\"里程碑b日期\",\"value\":\"2019-03-08\"},{\"name\":\"里程碑c\",\"value\":\"模具开始加工\"},{\"name\":\"里程碑c日期\",\"value\":\"2019-03-15\"},{\"name\":\"里程碑d\",\"value\":\"模具装配\"},{\"name\":\"里程碑d日期\",\"value\":\"2019-04-15\"},{\"name\":\"里程碑e\",\"value\":\"试模送样\"},{\"name\":\"里程碑e日期\",\"value\":\"2019-04-23\"}]},\"originator_dept_id\":\"14019490\",\"originator_userid\":\"05413159511213058\",\"process_instance_id\":\"196bd344-5346-4c65-9ade-50b70dd8540d\",\"process_instance_result\":\"agree\",\"status\":\"COMPLETED\",\"title\":\"陈俊提交的采购.项目生产启动\"}]},\"next_cursor\":2},\"success\":true},\"request_id\":\"9o1h0gd1vau2\"}}";
		        //去除启动邮件内容
	            String json = null;
	            String match = "\\{\"name\":\"2.项目启动邮件\",\"value\":\"";
	            String[] split = jsonStr.split(match);
	            if(split.length>1){
	                String s = split[1];
	                int i = s.indexOf("},");
	                json = split[0]+s.substring(i+2,s.length());
	            }
	            System.out.println(json);
	            
			    List<DingTalkMileStone> list = new ArrayList<DingTalkMileStone>(); 	
			    DingTalkMileStone menu = new DingTalkMileStone();
/*			    if(StringUtils.isNotBlank(json)){
			    	JSONObject jsonObject = JSONObject.fromObject(json);
			    	List<DingTalkMileStone> analysisJson = analysisJson(jsonObject,menu,list);
                    System.out.println(analysisJson);
			    }*/
			    List<DingTalkMileStone> analysisJsonToList = analysisJsonToList(json, list);
		        System.out.println(analysisJsonToList);
		       } catch (Exception e) {		    	   
		         e.printStackTrace();
		         logger.error("获取项目详情接口失败",e.getMessage());
		       }	
		  return "ok";
	  }
	  
	  
	  
       /**
        * 使用递归解决复杂json解析
        * @Title analysisJson 
        * @Description
        * @param objJson
        * @param menu
        * @param list
        * @return
        * @return List<DingTalkMileStone>
        */
		public List<DingTalkMileStone> analysisJson(Object objJson,DingTalkMileStone menu,List<DingTalkMileStone> list) {			
		   
		    //钉钉id
		    String dingTalkId = null;
		    //项目号
		    String projectNo = null;
			// 如果obj为json数组
			if (objJson instanceof JSONArray) {
				//将接收到的对象转换为JSONArray数组
				JSONArray objArray = (JSONArray) objJson;
				//对JSONArray数组进行循环遍历
				for (int i = 0; i < objArray.size(); i++) {
					//新建menu对象
					DingTalkMileStone mile = new DingTalkMileStone();
					//设置menu属性
					Object obj = objArray.get(i);
					if(obj instanceof JSONObject){
								
						//里程碑名
						String mileStoneName = null;
						//里程碑日期
						Date mileStoneDate = null;
						
						if(((JSONObject)objArray.get(i)).get("name")!= null && ((JSONObject)objArray.get(i)).get("name")!= "" && ((JSONObject)objArray.get(i)).get("name").toString().contains("里程碑")){
							if(((JSONObject)objArray.get(i)).get("name").toString().contains("日期") && ((JSONObject)objArray.get(i)).get("value")!=null){
								mileStoneDate = DateUtil.StrToDate(((JSONObject)objArray.get(i)).get("value").toString());
							}else if(!((JSONObject)objArray.get(i)).get("name").toString().contains("日期") && ((JSONObject)objArray.get(i)).get("value")!=null){
								mileStoneName = ((JSONObject)objArray.get(i)).get("value").toString();
							}
						}
						if(((JSONObject)objArray.get(i)).get("originator_userid")!= null){
							dingTalkId = ((JSONObject)objArray.get(i)).get("originator_userid").toString();	
							menu.setDingtalkid(dingTalkId);
						}
						if(((JSONObject)objArray.get(i)).get("name")!= null && ((JSONObject)objArray.get(i)).get("name")!= "" && ((JSONObject)objArray.get(i)).get("name").toString().contains("项目号")){							
							projectNo = "SHS"+((JSONObject)objArray.get(i)).get("value").toString();	
							menu.setProjectNo(projectNo);
						}
						
						//如果list最后一个对象里程碑名存在，里程碑日期不存在
						//当前里程碑日期不为空，则保存在最后一个对象中
						if(mileStoneDate!=null){
							if(list.size()>0){
								list.get(list.size()-1).setMilestoneDate(mileStoneDate);
							}
						}
						//当里程碑名不为空时，执行添加
						if(mileStoneName!=null){
							mile.setDingtalkid(dingTalkId);
							mile.setMilestoneDate(mileStoneDate);
							mile.setMilestoneName(mileStoneName);
							mile.setProjectNo(projectNo);
							//将该级菜单对象存进list集合中
							list.add(mile);
						}		  
					}
					
					//调用回调方法
					analysisJson(objArray.get(i),menu,list);
				}
			// 如果为json对象
			}else if (objJson instanceof JSONObject) {
				//将Object对象转换为JSONObject对象
				JSONObject jsonObject = (JSONObject) objJson;
				//迭代多有的Key值
				Iterator it = jsonObject.keys();
				//遍历每个Key值
				while (it.hasNext()) {
					//将key值转换为字符串
					String key = it.next().toString();
					//根据key获取对象
					Object object = new JSONTokener(jsonObject.get(key).toString()).nextValue();		
//					System.out.println(object.getClass() +"："+ (object instanceof JSONArray));
				    if("next_cursor".equals(key.toString())){
				    	System.out.println(jsonObject.get(key).toString());
//				    	menu.setNextCursor(Long.parseLong(jsonObject.get(key).toString()));
	                }
					// 如果得到的是数组
					if (object instanceof JSONArray) {
						//将Object对象转换为JSONObject对象
						JSONArray objArray = (JSONArray) object;
						//调用回调方法
						analysisJson(objArray,menu,list);
					}
					// 如果key中是一个json对象
					else if (object instanceof JSONObject) {
						//调用回调方法
						analysisJson((JSONObject) object,menu,list);
					}
				}
			}
			for (DingTalkMileStone dingTalkMileStone : list) {
				dingTalkMileStone.setProjectNo(menu.getProjectNo());
				dingTalkMileStone.setDingtalkid(menu.getDingtalkid());
//				dingTalkMileStone.setNextCursor(menu.getNextCursor());
			}			
			return list;
		} 
		
		
		
		public List<DingTalkMileStone> analysisJsonToList(String objJson,List<DingTalkMileStone> list) {			
			
			String projectNo = null;
			String dingTalkId = null;
			Long nextCursor = null;
			String[] split = objJson.split("里程碑");			
            if(split.length>1){               
                for (int j = 0; j < split.length; j++) {
                	DingTalkMileStone mile = new DingTalkMileStone();
                	String s = split[j];
                	if(j>0){                    	
                    	if(s.contains("日期")){
                        	String[] split2 = s.split("\"value\":");
                        	if(split2.length > 1){
                        		String string = split2[1];
                        		int i = string.indexOf("},");
                        		String json = string.substring(0,i);                    		
                        		Date mileStoneDate = DateUtil.StrToDate(json.replace("\"", ""));
                        		//如果list最后一个对象里程碑名存在，里程碑日期不存在
        						//当前里程碑日期不为空，则保存在最后一个对象中
        						if(mile!=null){
        							if(list.size()>0){
        								list.get(list.size()-1).setMilestoneDate(mileStoneDate);
        							}
        						}              		
                        	}
                    	}else{
                    		String[] split2 = s.split("\"value\":");
                        	if(split2.length > 1){
                        		String string = split2[1];
                        		int i = string.indexOf("},");
                        		String json = string.substring(0,i);
                        		//如果list最后一个对象里程碑名存在，里程碑日期不存在
        						//当前里程碑日期不为空，则保存在最后一个对象中
        						if(mile!=null){
        							mile.setMilestoneName(json.replace("\"", ""));
        							list.add(mile);
        						}              		
                        	}
                    	}                   	                    	
                	}
                	//获取项目号
                	if(s.contains("项目号")){
                		String[] split2 = s.split("项目号");
                		if(split2.length>1){
                			String string = split2[1];
                			String[] split3 = string.split("\"value\":");
                			if(split3.length>1){
                        		int i = split3[1].indexOf("},");
                        		String json = split3[1].substring(0,i);
                        		projectNo = json.replace("\"", "");
                			}
                		}
                	}
                	//dingTaklId
                	if(s.contains("originator_userid")){
                		String[] split2 = s.split("originator_userid");
                		if(split2.length>1){
                			String string = split2[1];
                			String[] split3 = string.split(":");
                			if(split3.length>1){
                				int i = split3[1].indexOf(",");
                				String json = split3[1].substring(0,i);
                				dingTalkId = json.replace("\"", "");
                			}
                		}
                	}
                	//dingTaklId
                	if(s.contains("next_cursor")){
                		String[] split2 = s.split("next_cursor");
                		if(split2.length>1){
                			String string = split2[1];
                			String[] split3 = string.split(":");
                			if(split3.length>1){
                				int i = split3[1].indexOf("}");
                				String json = split3[1].substring(0,i);
                				nextCursor = Long.parseLong(json.replace("\"", ""));
                			}
                		}
                	}               	
				}
            }
			
            for (DingTalkMileStone mileStone : list) {
            	mileStone.setProjectNo("SHS"+projectNo);
            	mileStone.setDingtalkid(dingTalkId);
//            	mileStone.setNextCursor(nextCursor);
			}            
			return list;
		}

}
