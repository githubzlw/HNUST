package com.cn.hnust.thread;

import java.util.Map;

import com.cn.hnust.component.RpcHelper;

public class HttpThread extends Thread {
	
	private Map<String, String> parameters;
	
	public HttpThread(Map<String, String> parameters){
		this.parameters =parameters ;
		
	};
	
	@Override
	public void run() {
		RpcHelper.sendRequest("",parameters);
	}

}
