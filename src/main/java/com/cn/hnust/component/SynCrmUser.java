package com.cn.hnust.component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.cn.hnust.pojo.User;
import com.cn.hnust.util.DbCrmPoolUtil;
import com.cn.hnust.util.DbTaskPoolUtil;


public class SynCrmUser {

	private final static Logger log = Logger.getLogger(SynCrmUser.class);
	public static void sendRequest(User user) {
		try {		 	
			
			new Thread(new SynCrmUser().new SendHttp(user)).start();
			
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
			Connection conn = DbCrmPoolUtil.getConnection();
			String sql = "";
			//type：1 更新数据     type:0 插入数据
			if(user.getType() == 1){
		 		sql = "update back_user "
		 				+ " set username=?,real_name=?,pwd=?,email=?,tel=?,permission=?,remark = ?"    
		 				+ " where backuserId = ?;"; 
			}else if(user.getType() == 0){
				sql = "insert into back_user (backuserId,username,real_name,pwd,email,tel,create_time,permission,factory_id,remark) values (?,?,?,?,?,?,?,?,?,?);";
			}
			
	 		
	 		try{
	 			PreparedStatement psmt = conn.prepareStatement(sql);	
	 			if(user.getType() == 0){
		 			psmt.setString(1, user.getId().toString());
		 			psmt.setString(2, user.getUserName());
		 			psmt.setString(3, user.getTrueName());
		 			psmt.setString(4,user.getPassword());
		 			psmt.setString(5,user.getEmailAddress());
		 			psmt.setString(6,user.getPhone());
		 			psmt.setDate(7, user.getRegisterDate() != null ?  new java.sql.Date(user.getRegisterDate().getTime()) : null);
		 			psmt.setInt(8,user.getCrmRole() == 100 ? 1 : 2);
		 			psmt.setString(9,"f1010");
		 			psmt.setString(10,user.getJob());
	 			}else if(user.getType() == 1){
		 			psmt.setString(1, user.getUserName());
		 			psmt.setString(2, user.getTrueName());
		 			psmt.setString(3,user.getFlag() == 0 ? UUID.randomUUID().toString() : user.getPassword());
		 			psmt.setString(4,user.getEmailAddress());
		 			psmt.setString(5,user.getPhone());
		 			psmt.setInt(6,user.getCrmRole() == 100 ? 1 : 2);
		 			psmt.setString(7, user.getJob());
		 			psmt.setString(8,user.getId().toString());
	 			}
	 			psmt.execute();

	 		} catch (SQLException e) {
	 			e.printStackTrace();
	 			log.error("<<<<<<<<<<<<<<<<<<SynCrmUser>>>>>>>>>>>>>>>>>>>同步用户失败"+e.getMessage());
	 		}	finally {
	 			DbCrmPoolUtil.returnConnection(conn);
	 		} 
		}	
	}
}