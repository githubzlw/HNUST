package com.cn.hnust.component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;



import com.cn.hnust.pojo.User;
import com.cn.hnust.util.DBErpUserPoolUtil;

public class SynErpUser {
	private final static Logger log = Logger.getLogger(SynErpUser.class);
	public static void sendRequest(User user) {
		try {		 	
			
			new Thread(new SynErpUser().new SendHttp(user)).start();
			
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
			Connection conn = DBErpUserPoolUtil.getConnection();
			String sql = "";
			String alertSql = "";
			
			//type：1 更新数据     type:0 插入数据
			if(user.getType() == 1){
		 		sql = "update Employee set job_number=?, EmpPWD=? where EmpEName = ?;";
			}else if(user.getType() == 0){
				sql = "insert into Employee (EmpCName,EmpEName,EmpPWD,EmpPostition,job_number) values (?,?,?,?,?);";
				alertSql = "alter table payment_maolirun add Employee_" + user.getUserName() + " float";
			}
			PreparedStatement psmt = null;
			Statement psmt1 = null;
	 		try{
	 			psmt = conn.prepareStatement(sql);
	 			if(user.getType() == 0){

		 			psmt.setString(1,user.getUserName());
		 			psmt.setString(2,user.getUserName());
		 			psmt.setString(3,user.getPassword());
		 			psmt.setInt(4,user.getErpRole());
		 			psmt.setString(5,user.getJobNumber());

	 			}else if(user.getType() == 1){
	 				psmt.setString(1,user.getJobNumber());
	 				psmt.setString(2,user.getPassword());
		 			psmt.setString(3,user.getUserName());
	 			}			
	 			psmt.execute();

	 			if(user.getType() == 0){
	 				psmt1 = conn.createStatement();
		 			psmt1.execute(alertSql);
				}

	 		} catch (SQLException e) {
	 			e.printStackTrace();
	 			log.error("<<<<<<<<<<<<<<<<<<SynErpUser>>>>>>>>>>>>>>>>>>>同步用户失败"+e.getMessage());
	 		}	finally {
	 			DBErpUserPoolUtil.returnConnection(conn);
				DBErpUserPoolUtil.closeStatement(psmt);
				DBErpUserPoolUtil.closeStatement(psmt1);
	 		} 
		}	
	}
}
