package com.cn.hnust.component;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cn.hnust.util.HttpClient;
import com.cn.hnust.util.PropertiesUtils;


public class RpcNBEmailHelper {

	private final static Logger log = Logger.getLogger(RpcNBEmailHelper.class);
	private static PropertiesUtils reader = new PropertiesUtils("config.properties");
	public synchronized String sendRequest(String url, String projectNo) {
		String str = "";
		try {			
			if (StringUtils.isBlank(url)) {
//		     url = "http://192.168.1.91:8081/New-Quotation/quote/getData";
				url = reader.getProperty("nbmail_path")+"/CustomerInfo/uncontacted";
			}
			String date="&projectNo="+projectNo;
			
			str = HttpClient.sendMessage(url, date);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发送失败", e);
		}
		return str;
	}

}