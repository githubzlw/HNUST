package com.cn.hnust.component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.cn.hnust.pojo.User;
import com.cn.hnust.util.DbTaskPoolUtil;


public class SynTaskUser {

	private final static Logger log = Logger.getLogger(SynTaskUser.class);
	public static void sendRequest(User user) {
		try {		 	
			
			new Thread(new SynTaskUser().new SendHttp(user)).start();
			
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
			Connection conn = DbTaskPoolUtil.getConnection();
			String sql = "";
			
			//type：1 更新数据     type:0 插入数据
			if(user.getType() == 1){
		 		sql = "update email_user "
		 				+ " set role_no= ?,role_name=?,true_name=?,job=?,user_name=?,pwd=?,email_address=?,flag=?,phono=?,job_number=?"    
		 				+ " where id = ?;"; 
			}else if(user.getType() == 0){
				sql = "insert into email_user (id,role_no,role_name,true_name,job,user_name,pwd,email_address,flag,phono,job_number) values (?,?,?,?,?,?,?,?,?,?,?);";
			}
			
	 		
	 		try{
	 			PreparedStatement psmt = conn.prepareStatement(sql);	
	 			if(user.getType() == 0){
		 			psmt.setInt(1, user.getId());
		 			psmt.setInt(2, user.getTaskRole());
		 			psmt.setString(3, user.getRoleName());
		 			psmt.setString(4, user.getTrueName());
		 			psmt.setString(5,user.getJob());
		 			psmt.setString(6,user.getUserName());
		 			psmt.setString(7,user.getPassword());
		 			psmt.setString(8,user.getEmailAddress());
		 			psmt.setInt(9,user.getFlag());
		 			psmt.setString(10,user.getPhone());
		 			psmt.setString(11,user.getJobNumber());
	 			}else if(user.getType() == 1){
	 				psmt.setInt(1, user.getTaskRole());
		 			psmt.setString(2, user.getRoleName());
		 			psmt.setString(3, user.getTrueName());
		 			psmt.setString(4,user.getJob());
		 			psmt.setString(5,user.getUserName());
		 			psmt.setString(6,user.getPassword());
		 			psmt.setString(7,user.getEmailAddress());
		 			psmt.setInt(8,user.getFlag());
		 			psmt.setString(9,user.getPhone());
		 			psmt.setString(10,user.getJobNumber());
		 			psmt.setInt(11,user.getId());
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