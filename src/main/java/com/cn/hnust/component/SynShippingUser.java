package com.cn.hnust.component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.cn.hnust.pojo.User;
import com.cn.hnust.util.DbShippingPoolUtil;
import com.cn.hnust.util.DbTaskPoolUtil;


public class SynShippingUser {

	private final static Logger log = Logger.getLogger(SynShippingUser.class);
	public static void sendRequest(User user) {
		try {		 	
			
			new Thread(new SynShippingUser().new SendHttp(user)).start();
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发送失败", e);
		}
	}
	class SendHttp implements Runnable{
		
		private User user;
		
		SendHttp(User user){
			this.user = user;
		}

		@Override
		public void run() {		
			Connection conn = DbShippingPoolUtil.getConnection();
			String sql = "";
			
			//type：1 更新数据     type:0 插入数据
			if(user.getType() == 1){
		 		sql = "update admin "
		 				+ " set pass=?,job_number=?"    
		 				+ " where name = ?;"; 
			}else if(user.getType() == 0){
				sql = "insert into admin (name,pass,auth,job_number) values (?,?,?,?);";
			}
			
	 		
	 		try{
	 			PreparedStatement psmt = conn.prepareStatement(sql);	
	 			if(user.getType() == 0){
		 			psmt.setString(1,user.getUserName());
		 			psmt.setString(2,user.getPassword());
		 			psmt.setInt(3, 0);    //出运单初始给普通用户权限
		 			psmt.setString(4,user.getJobNumber());
	 			}else if(user.getType() == 1){
	 				if(user.getFlag() == 0){
			 			psmt.setString(1,UUID.randomUUID().toString());
			 			psmt.setString(2,user.getJobNumber());
			 			psmt.setString(3,user.getUserName());
	 				}else{
			 			psmt.setString(1,user.getPassword());
			 			psmt.setString(2,user.getJobNumber());
			 			psmt.setString(3,user.getUserName());
	 				}
	 			}			
	 			psmt.execute();

	 		} catch (SQLException e) {
	 			e.printStackTrace();
	 			log.error("<<<<<<<<<<<<<<<<<<SynTaskUser>>>>>>>>>>>>>>>>>>>同步用户失败"+e.getMessage());
	 		}	finally {
	 			DbTaskPoolUtil.returnConnection(conn);
	 		} 
		}	
	}
}