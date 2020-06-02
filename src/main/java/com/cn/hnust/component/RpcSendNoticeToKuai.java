package com.cn.hnust.component;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cn.hnust.pojo.ProjectTask;
import com.cn.hnust.util.Client;
import com.cn.hnust.util.PropertiesUtils;

	  /**
	   * 同步任务系统消息到钉钉（通过快制造服务器中转）
	   * @param request
	   * @param response
	   */ 

public class RpcSendNoticeToKuai {

	private final static Logger LOG = Logger.getLogger(RpcHelper.class);
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	public static void sendRequest(ProjectTask projectTask) {

		try {
			//Test地址
//			 String url = "http://1j97509t42.51mypc.cn/Ding-Talk/sendTalkNotice";	
			 //正式地址
			 String url = reader.getProperty("kuaizhizao_path")+"/Ding-Talk/sendTalkNotice";				

			 Map<String,String> map = new HashMap<String, String>();
			 map.put("dingTalkId", projectTask.getDingTalkId());
             map.put("projectNo", projectTask.getProjectNo());
             map.put("description", projectTask.getDescription());
             map.put("sendUser", projectTask.getInitiator());
             map.put("id", projectTask.getId().toString());
             map.put("type", "task");
			
			new Thread(new RpcSendNoticeToKuai().new SendHttp(url,map)).start();;			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("同步任务系统消息失败=="+ projectTask.getId() + e.getMessage());
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