package com.cn.hnust.component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.cn.hnust.pojo.ShippingConfirmation;
import com.cn.hnust.util.DbShippingPoolUtil;
import com.cn.hnust.util.DbTaskPoolUtil;


public class SynShippingConfirmation {

	private final static Logger log = Logger.getLogger(SynShippingConfirmation.class);
	public static void sendRequest(ShippingConfirmation shippingConfirmation,int type) {
		try {		 	
			
			new Thread(new SynShippingConfirmation().new SendHttp(shippingConfirmation,type)).start();
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发送失败", e);
		}
	}
	class SendHttp implements Runnable{
		
		private ShippingConfirmation shippingConfirmation;
		private int type;
		
		SendHttp(ShippingConfirmation shippingConfirmation,int type){
			this.shippingConfirmation = shippingConfirmation;
			this.type = type;
		}
        
		
		@Override
		public void run() {		
			Connection conn = DbShippingPoolUtil.getConnection();
			String sql = "";
			
			//type：1 更新数据     type:2 删除数据
			if(type == 1){
		 		sql = "update shipping_contract "
		 				+ " set is_complete = ?"    
		 				+ " where sid = ?;"; 
			}else if(type == 2){
				sql = "delete from shipping_contract where sid = ?";
			}
			
	 		
	 		try{
	 			PreparedStatement psmt = conn.prepareStatement(sql);	
	 			if(type == 1){
		 			psmt.setInt(1,shippingConfirmation.getIsComplete());
		 			psmt.setInt(2,shippingConfirmation.getId());
	 			}else if(type == 2){
			 		psmt.setInt(1,shippingConfirmation.getId());
	 			}			
	 			psmt.execute();

	 		} catch (SQLException e) {
	 			e.printStackTrace();
	 			log.error("<<<<<<<<<<<<<<<<<<SynShippingConfirmation>>>>>>>>>>>>>>>>>>>同步电子出货单"+e.getMessage());
	 		}	finally {
	 			DbShippingPoolUtil.returnConnection(conn);
	 		} 
		}	
	}
}