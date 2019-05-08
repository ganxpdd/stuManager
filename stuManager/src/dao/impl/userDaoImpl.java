package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;
import dao.user;
import dao.userDao;

public class userDaoImpl implements userDao {

	ResultSet rs=null;
	Connection conn =null;
	PreparedStatement ps =null;
	@Override
	public boolean login( String username,String password) {
		try {
			conn = DBUtil.getConn();
			System.out.println("数据库连接状态："+conn.isClosed());

			String sql = "select * from stu_user where user_name = ? and user_pwd = ? ";
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ps.setString(1,username );
			ps.setString(2, password);
			rs = ps.executeQuery();
			return 	rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.release(conn,ps,rs);
		}

		return false;
	}

	public boolean addUser(user u) {
		try {
			conn= DBUtil.getConn();
			String sql="INSERT INTO `stu_user`(`user_name`, `user_pwd`) VALUE(?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, u.getUser_name());
			ps.setString(2, u.getUser_pwd());
			
			int flag = ps.executeUpdate();
			if(flag>0) {
				System.out.println("数据库操作成功！");
			}else {
				System.out.println("数据库操作失败！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
