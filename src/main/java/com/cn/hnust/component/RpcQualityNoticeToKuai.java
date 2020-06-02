package com.cn.hnust.component;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cn.hnust.pojo.Comment;
import com.cn.hnust.pojo.DingTalkModel;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.util.Client;
import com.cn.hnust.util.PropertiesUtils;

	  /**
	   * 同步任务系统消息到钉钉（通过快制造服务器中转）
	   * @param request
	   * @param response
	   */ 

public class RpcQualityNoticeToKuai {

	private final static Logger LOG = Logger.getLogger(RpcHelper.class);
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	public static void sendRequest(DingTalkModel model) {

		try {
			//Test地址
//			 String url = "http://1j97509t42.51mypc.cn/Ding-Talk/sendTalkNotice";	
			 //正式地址
			 String url = reader.getProperty("kuaizhizao_path")+"/Ding-Talk/sendTalkNotice";				

			 Map<String,String> map = new HashMap<String, String>();
			 map.put("dingTalkId", model.getDingTalkId());
             map.put("projectNo", model.getProjectNo());
             map.put("description", model.getDescription());
             map.put("sendUser", model.getReceiver());
             map.put("id", model.getId());
             map.put("type", model.getType());
             LOG.info("=======发送质检报告回复消息开始========="+ map);
			new Thread(new RpcQualityNoticeToKuai().new SendHttp(url,map)).start();;			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("发送质检报告回复消息提醒失败=="+ model.getId() + e.getMessage());
		}

	}
	
	
	
	class SendHttp implements Runnable{
		
		private String url;
		
		private Map<String, String> parameters ;
		
		SendHttp(String url,Map<String, String> parameters){
			this.url = url;
			this.parameters = parameters;
		}

		@Override
		public void run() {			
			 Client.sendPost(url, parameters);			
		}		
	}
}