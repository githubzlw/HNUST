package com.cn.hnust.component;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.hnust.util.Client;
import com.cn.hnust.util.PropertiesUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;


public class RpcGetFactoryUpdate {

	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	public String sendRequest(String factoryId,String projectNo) {		
		String json = null;
		try {
			//当项目号为空时，直接跳出
			if(StringUtils.isBlank(factoryId) || StringUtils.isBlank(projectNo)){
               return json;
			}
			String url = reader.getProperty("kuaizhizao_path")+"/sendPort/getProjectInfo";
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("factoryId", factoryId);
			parameters.put("projectNo", projectNo);
			json = new SendHttp(url, parameters).call();			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("获取工厂进度更新失败"+factoryId , e);
		}
         return json;
	}

	//获取工厂进度更新详情，返回值为json对象
	class SendHttp implements Callable<String> {
		
		private String url;
		
		private Map<String, String> parameters ;
		
		SendHttp(String url,Map<String, String> parameters){
			this.url = url;
			this.parameters = parameters;
		}

		@Override
		public String call() {
			String json = Client.sendPost(url, parameters);
			return json;
		}	
	}
}