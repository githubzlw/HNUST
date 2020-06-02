package com.cn.hnust.component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;





import com.cn.hnust.pojo.User;
import com.cn.hnust.util.DbNBEmailPoolUtil;
import com.cn.hnust.util.DbNewQuotationPoolUtil;

public class SynNBEmailUser {
	private final static Logger log = Logger.getLogger(SynNBEmailUser.class);
	public static void sendRequest(User user) {
		try {		 	
			
			new Thread(new SynNBEmailUser().new SendHttp(user)).start();
			
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
			Connection conn = DbNBEmailPoolUtil.getConnection();
			String sql = "";
			log.error("DbNBEmailPoolUtil开始");
			//type：1 更新数据     type:0 插入数据
			if(user.getType() == 1){
		 		sql = "update email_user "
		 				+ " set job_number=?"    
		 				+ " where user_name = ?;"; 
			}else if(user.getType() == 0){
				sql = "insert into email_user (role_no,true_name,user_name,email_address,email_pwd,pwd,regist_date,job_number) values (?,?,?,?,?,?,now(),?);";
			}
			
			log.error(sql);
	 		try{
	 			PreparedStatement psmt = conn.prepareStatement(sql);	
	 			if(user.getType() == 0){
		 			psmt.setInt(1,user.getNbRole());
		 			psmt.setString(2,user.getUserName());
		 			psmt.setString(3,user.getUserName());
		 			psmt.setString(4,user.getEmailAddress());
		 			psmt.setString(5,user.getEmailPwd());
		 			psmt.setString(6,user.getPassword());
		 			psmt.setString(7,user.getJobNumber());
	 			}else if(user.getType() == 1){
	 				psmt.setString(1,user.getPassword());
			 	   
			 		psmt.setString(2,user.getUserName());
	 				
	 			}
	 			log.error(user.getUserName());
	 			psmt.execute();

	 		} catch (SQLException e) {
	 			e.printStackTrace();
	 			log.error("<<<<<<<<<<<<<<<<<<SynNBEmailUser>>>>>>>>>>>>>>>>>>>同步用户失败"+e.getMessage());
	 		}	finally {
	 			DbNBEmailPoolUtil.returnConnection(conn);
	 		} 
		}	
	}
}
