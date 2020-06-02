package com.cn.hnust.component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;



import com.cn.hnust.pojo.User;
import com.cn.hnust.util.DBBonusSystemPoolUtil;


public class SynBonusSystemUser {
	private final static Logger log = Logger.getLogger(SynBonusSystemUser.class);
	public static void sendRequest(User user) {
		try {		 	
			
			new Thread(new SynBonusSystemUser().new SendHttp(user)).start();
			
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
			Connection conn = DBBonusSystemPoolUtil.getConnection();
			String sql = "";
			log.error("SynBonusSystemUser开始");
			//type：1 更新数据     type:0 插入数据
			if(user.getType() == 1){
		 		sql = "update user "
		 				+ " set job_number=?"    
		 				+ " where user_name = ?;"; 
			}else if(user.getType() == 0){
				sql = "insert into user (role_no,role_name,true_name,user_name,pwd,job_number) values (?,?,?,?,?,?);";
			}
			
			log.error(sql);
	 		try{
	 			PreparedStatement psmt = conn.prepareStatement(sql);	
	 			if(user.getType() == 0){
	 				psmt.setInt(1,user.getBonusRole());
	 				psmt.setString(2,user.getUserName());
		 			psmt.setString(3,user.getUserName());
		 			psmt.setString(4,user.getUserName());
		 			psmt.setString(5,user.getPassword());
		 			psmt.setString(6,user.getJobNumber());
	 			}else if(user.getType() == 1){
	 				psmt.setString(1,user.getJobNumber());
	 				psmt.setString(2,user.getUserName());
		 		}
	 			log.error(sql);
	 			psmt.execute();

	 		} catch (SQLException e) {
	 			e.printStackTrace();
	 			log.error("<<<<<<<<<<<<<<<<<<SynBonusSystemUser>>>>>>>>>>>>>>>>>>>同步用户失败"+e.getMessage());
	 		}	finally {
	 			DBBonusSystemPoolUtil.returnConnection(conn);
	 		} 
		}	
	}
}
