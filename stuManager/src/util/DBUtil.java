package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/stu?useUnicode=true&characterEncoding=UTF-8";
	static String name = "root";
	static String pwd = "123";

	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, name, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void release(Connection conn,Statement st,ResultSet rs){
		clossRs(rs);
		clossConn(conn);
		clossSt(st);
	}

	public static void clossRs(ResultSet rs) {

		try {
			if(rs !=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			rs = null;
		}
	}

	public static void clossSt(Statement st) {

		try {
			if(st !=null) {
				st.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			st = null;
		}
	}

	public static void clossConn(Connection conn) {

		try {
			if(conn !=null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			conn = null;
		}
	}
}
