package com.cn.hnust.component;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cn.hnust.util.HttpClient;
import com.cn.hnust.util.PropertiesUtils;


public class RpcReportHelper {

	private final static Logger log = Logger.getLogger(RpcReportHelper.class);
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	public static void sendRequest(String url, Object obj) {
		try {
			if (StringUtils.isBlank(url)) {
			  //url = "http://192.168.1.91:8080/ERP-NBEmail/helpServlet?action=qualityReport&className=ExternalinterfaceServlet";
				url = reader.getProperty("erp_path")+"/helpServlet?action=qualityReport&className=ExternalinterfaceServlet";
			}
			JSONObject object = JSONObject.fromObject(obj);
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("qualityReport", object.toString());	
			new Thread(new RpcReportHelper().new SendHttp(url,parameters)).start();;
			
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