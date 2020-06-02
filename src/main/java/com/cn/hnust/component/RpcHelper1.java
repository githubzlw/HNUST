package com.cn.hnust.component;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cn.hnust.util.HttpClient;
import com.cn.hnust.util.PropertiesUtils;

@Component
public class RpcHelper1 {

	private final static Logger log = Logger.getLogger(RpcHelper1.class);
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	public void sendRequest(String url, Object obj) {

		try {
			if (StringUtils.isBlank(url)) {
				/*
				 * 线上url
				 */
				// url="http://117.144.21.74:33169/ERP-NBEmail/helpServlet?action=publicComment&className=ExternalinterfaceServlet";
				//url = "http://192.168.1.91:8080/ERP-NBEmail/helpServlet?action=publicComment3&className=ExternalinterfaceServlet";
				url = reader.getProperty("erp_path")+"/helpServlet?action=SendReport&className=ExternalinterfaceServlet";
				
			}

			JSONObject object = JSONObject.fromObject(obj);
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("QualityAnalysisTable", object.toString());
			
			HttpClient.sendPost(url, parameters);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发送失败", e);
		}

	}

}