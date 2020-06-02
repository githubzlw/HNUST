package com.cn.hnust.component;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cn.hnust.pojo.User;
import com.cn.hnust.util.HttpClient;
import com.cn.hnust.util.PropertiesUtils;
import com.cn.hnust.util.SerializeUtil;


public class RpcUserHelper {

	private final static Logger log = Logger.getLogger(RpcUserHelper.class);
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	public static void sendRequest(String url, User user) {
		try {
			if (StringUtils.isBlank(url)) {
//			  url = "http://192.168.1.91:8081/New-Quotation/EmailUserController/modifyingProjectMembers";
				url = reader.getProperty("erp_path")+"/helpServlet?action=qualityReport&className=ExternalinterfaceServlet";
			}

			Map<String, String> parameters = new HashMap<String, String>();
			parameters = SerializeUtil.object2Map(user);
			new Thread(new RpcUserHelper().new SendHttp(url,parameters)).start();;
			
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