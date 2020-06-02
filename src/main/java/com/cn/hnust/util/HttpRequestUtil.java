package com.cn.hnust.util;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpRequestUtil {
    
	public  static String  httpGetRequest(){
		// 需要访问的接口路径
	    String url = "http://192.168.1.54:8080/crm/queryShippingInfo.do?userId=10000";
	    // 配置请求信息（请求时间）
	    RequestConfig rc = RequestConfig.custom().setSocketTimeout(5000)
	            .setConnectTimeout(5000).build();
	    // 获取使用DefaultHttpClient对象
	    CloseableHttpClient httpclient = HttpClients.createDefault();
	    // 返回结果
	    String result = null;
	    try {
	        if (url != null) {
	            // 创建HttpGet对象，将URL通过构造方法传入HttpGet对象
	            HttpGet httpget = new HttpGet(url);
	            // 将配置好请求信息附加到http请求中
	            httpget.setConfig(rc);
	            // 执行DefaultHttpClient对象的execute方法发送GET请求，通过CloseableHttpResponse接口的实例，可以获取服务器返回的信息
	            CloseableHttpResponse response = httpclient.execute(httpget);
	            try {
	                // 得到返回对象
	                HttpEntity entity = response.getEntity();
	                if (entity != null) {
	                    // 获取返回结果
	                    result = EntityUtils.toString(entity, "UTF-8");
	                }
	            } finally {
	                // 关闭到客户端的连接
	                response.close();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }  finally {
	        try {
	            httpclient.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return result;
	}
	
	public static void main(String xp[]){
		String str=httpGetRequest();
		System.err.println(str);
	}
	
}

