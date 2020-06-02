package com.cn.hnust.component;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cn.hnust.util.HttpClient;
import com.cn.hnust.util.PropertiesUtils;


public class RpcSynPurchase {

	private final static Logger log = Logger.getLogger(RpcSynPurchase.class);
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	public static void sendRequest(String url, String projectNo,String procurement) {
		try {
			if (StringUtils.isBlank(url)) {			
			  url = reader.getProperty("new_quotation_path")+"/quote/acquisitionOfProcurement";
			}
//			JSONObject object = JSONObject.fromObject(obj);
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("projectId", projectNo);	
			parameters.put("procurement", procurement);	
			
			new Thread(new RpcSynPurchase().new SendHttp(url,parameters)).start();;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发送失败", e);
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
			HttpClient.sendPost(url, parameters);
		}	
	}
}