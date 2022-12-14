package com.cn.hnust.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class DingTalkThread extends  Thread{
private static String sendPost(String url1) {
	String value="";
	HttpURLConnection conn = null;
    InputStream is = null;
    BufferedReader br = null;
    StringBuilder result = new StringBuilder();
    try{
        //创建远程url连接对象
        URL url = new URL(url1);
        //通过远程url连接对象打开一个连接，强转成HTTPURLConnection类
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        //设置连接超时时间和读取超时时间
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(60000);
        //conn.setRequestProperty("Accept", "application/json");
        //发送请求
        conn.connect();
        //通过conn取得输入流，并使用Reader读取
        if (200 == conn.getResponseCode()){
            is = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null){
            	 result.append(line);
            	 value =line;
              //  System.out.println(line);
            }
        }else{
            System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
        }
    }catch (IOException e){
        e.printStackTrace();
    }catch (Exception e){
        e.printStackTrace();
    }finally {
        try{
            if(br != null){
                br.close();
            }
            if(is != null){
                is.close();
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        conn.disconnect();
    }
    return value;
}


public static void sendOut(Integer id, String dingTalkId) {
	String dingTalk="?dingTalkId="+dingTalkId+"&qualityId="+id+"&url="+"&selectButton=1";
	String url = "https://www.kuaizhizao.cn/Ding-Talk/qualityInspectionReport2"+dingTalk;
	String process_instance_id=sendPost(url);
	// 做json解析
	if(StringUtils.isNotEmpty(process_instance_id)){
        JSONObject parse = JSONObject.parseObject(process_instance_id);
        if(parse!= null && parse.containsKey("code") && "200".equals(parse.getString("code"))){
            process_instance_id = parse.getString("data");
            if (StringUtils.isNotEmpty(process_instance_id)) {
                QualityController.updateAllProcess(id, process_instance_id);
            }

        }
    }


	
}
public static void ComplaintOut(Integer id, String dingTalkId) {
	String dingTalk="?dingTalkId="+dingTalkId+"&complaintId="+id;
	String url = "https://www.kuaizhizao.cn/Ding-Talk/qualityComplaint"+dingTalk;
	String process_instance_id=sendPost(url);
	ProjectComplaintController.updateQualityComplaint(id,process_instance_id);
	
}

    public static void ProjectLaunch(String projectNo) {
        String url ="https://www.kuaizhizao.cn/Ding-Talk/projectLaunch?projectNo="+projectNo.trim();
        String process_instance_id=sendPost(url);


    }

    public static void sendMessage(String projectNo) {
        String url ="https://www.kuaizhizao.cn/Ding-Talk/firstInspection?projectNo="+projectNo.trim();
        String process_instance_id=sendPost(url);

    }

    public static void setShippingConfirmation(Integer id) {
        String url ="https://www.kuaizhizao.cn/Ding-Talk/shippingConfirmation?id="+id;
        String process_instance_id=sendPost(url);

    }

    public static String sendDeliveryConfirmation(String id) {
        String url ="https://www.kuaizhizao.cn/Ding-Talk/deliveryConfirmation?id="+id;
        String process_instance_id=sendPost(url);
        return process_instance_id;
    }
}
