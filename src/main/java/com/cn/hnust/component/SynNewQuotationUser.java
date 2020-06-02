package com.cn.hnust.component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import org.apache.log4j.Logger;



import com.cn.hnust.pojo.User;
import com.cn.hnust.util.DbNewQuotationPoolUtil;


public class SynNewQuotationUser {
	private final static Logger log = Logger.getLogger(SynNewQuotationUser.class);
	public static void sendRequest(User user) {
		try {		 	
			
			new Thread(new SynNewQuotationUser().new SendHttp(user)).start();
			
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
			Connection conn = DbNewQuotationPoolUtil.getConnection();
			String sql = "";
			
			//type：1 更新数据     type:0 插入数据
			if(user.getType() == 1){
		 		sql = "update user "
		 				+ " job_number=?"    
		 				+ " where name = ?;"; 
			}else if(user.getType() == 0){
				sql = "insert into user (role_no,name,pwd,create_time,job_number) values (?,?,?,now(),?);";
			}
			
	 		
	 		try{
	 			PreparedStatement psmt = conn.prepareStatement(sql);	
	 			if(user.getType() == 0){
		 			psmt.setInt(1,user.getQuotationRole());
		 			psmt.setString(2,user.getUserName());
		 			psmt.setString(3,user.getPassword());
		 			psmt.setString(4,user.getJobNumber());
	 			}else if(user.getType() == 1){
	 				
	 				psmt.setString(1,user.getJobNumber());
			 		psmt.setString(2,user.getUserName());
	 				
	 			}			
	 			psmt.execute();

	 		} catch (SQLException e) {
	 			e.printStackTrace();
	 			log.error("<<<<<<<<<<<<<<<<<<SynNewQuotationUser>>>>>>>>>>>>>>>>>>>同步用户失败"+e.getMessage());
	 		}	finally {
	 			DbNewQuotationPoolUtil.returnConnection(conn);
	 		} 
		}	
	}
}
