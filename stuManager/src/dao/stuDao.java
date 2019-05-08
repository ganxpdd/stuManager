package dao;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

import bean.student;
import util.DBUtil;
import util.jdbcutil;

public class stuDao {

	String driver = "com.mysql.jdbc.Driver";
	String uri = "jdbc:mysql://localhost:3306/stu?useUnicode=true&characterEncoding=UTF-8";

	String user = "root";
	String pwd = "123";
	
	/**
	 * 根据limit ?,?(传入参数pageNum，pageSize)得到学生list
	 * @return
	 * */
	public  List<student> selectAll(int pageNum,int pageSize){  

		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		List<student> list = new ArrayList<student>(); 
		try {
			String sql ="select * from stu_list limit ?,?";

			Class.forName(driver);
			conn = DriverManager.getConnection(uri, user, pwd);
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pageNum);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while (rs.next()) {
				student stu = new student();
				stu.setId(rs.getInt("id"));
				stu.setStu_name(rs.getString("stu_name"));
				stu.setStu_age(rs.getInt("stu_age"));
				stu.setStu_sex(rs.getString("stu_sex"));
				stu.setStu_numb(rs.getInt("stu_numb"));
				list.add(stu);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcutil.release(conn, st, rs);
		}
		return list;
	}
	
	/**
	 * 查询所有学生 return 学生总数count
	 * @return
	 * */
	public  int selectAll(){  
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		int count = 0;
		List<student> list = new ArrayList<student>(); 
		try {
			String sql ="select * from stu_list";

			Class.forName(driver);
			conn = DriverManager.getConnection(uri, user, pwd);
			PreparedStatement pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				student stu = new student();
				stu.setId(rs.getInt("id"));
				stu.setStu_name(rs.getString("stu_name"));
				stu.setStu_age(rs.getInt("stu_age"));
				stu.setStu_sex(rs.getString("stu_sex"));
				stu.setStu_numb(rs.getInt("stu_numb"));
				list.add(stu);
			}
			count = list.size();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcutil.release(conn, st, rs);
		}
		return count;
	}
	/**
	 * 添加查询学号（numb）是否存在return boolean
	 * @return
	 * */
	public boolean selectNumb(int numb,int id) {
		Connection conn = null;
		String sql  = "select * from stu_list where stu_numb = "+numb+" and id !="+id ;
		System.out.println(sql);
		try {
			conn = DBUtil.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			boolean count=rs.next();
			if(count) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("学号不存在，可以添加！");
			return true;
		}
	}
	
	/**
	 * 添加学生 return boolean
	 * @return
	 * */
	public boolean addstu(student stu){  //添加信息
		String sql = "INSERT INTO `stu_list`(`id`,`stu_name`,`stu_age`,`stu_sex`,`stu_numb`) VALUES (?,?,?,?,?)";  
		Connection conn=null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(uri, user, pwd);
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, stu.getId());
			pst.setString(2, stu.getStu_name());
			pst.setInt(3, stu.getStu_age());
			pst.setString(4, stu.getStu_sex());
			pst.setInt(5, stu.getStu_numb());
			int count = pst.executeUpdate();
			pst.close();
			return count>0?true:false;  //是否添加的判断
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("添加失败！");
		}
		return false;
	}
	
	/**
	 * 获取学生id进行删除 return boolean
	 * @return
	 * */
	public boolean delete(int id) {//删除
		Connection conn=null;
		try {
			Class.forName(driver);
			System.out.println(id);
			conn = DriverManager.getConnection(uri, user, pwd);
			String sql = "delete from stu_list where id ='"+id+"'";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			System.out.println(sql);
			int count = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return count>0?true:false;
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * 获取学生id进行批量删除 return boolean
	 * @return
	 * *//*
	public boolean deleteAll(Integer[] ID) {//删除
		Connection conn=null;
		try {
			Class.forName(driver);
			System.out.println(ID);
			conn = DriverManager.getConnection(uri, user, pwd);
			String sql="delete from stu_list where id(0"; 
			Integer[] id=ID; 
			for(int i=0;i<id.length;i++) 
			{ 
			  sql+=","+id[i]; 
			} 
			sql+=")"; 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			
			System.out.println(sql);
			int count = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return count>0?true:false;
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	*/
	
	/**
	 * 修改学生 return boolean
	 * @return
	 * */
	public boolean Update(student stu) {
		String sql = "UPDATE stu_list SET stu_name=?,stu_age=?,stu_sex=?,stu_numb=? "+ "WHERE id =?"; 
		Connection conn=null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(uri, user, pwd);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, stu.getStu_name());
			pstmt.setInt(2, stu.getStu_age());
			pstmt.setString(3, stu.getStu_sex());
			pstmt.setInt(4, stu.getStu_numb());
			pstmt.setInt(5, stu.getId());
			int count = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return count>0?true:false;  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("修改失败！");
		}

		return false;
	}
	
	/**
	 * 用id进行查询学生 return stu
	 * 用于修改学生信息
	 * @return
	 * */
	public student selectAllById(int id) {//按id查
		Connection conn = null;
		String sql  = "select * from stu_list where id = "+id;

		System.out.println("sql:"+sql);
		System.out.println("id:"+id);
		student stu = new student();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(uri, user, pwd);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				stu.setId(rs.getInt("id"));
				stu.setStu_name(rs.getString("stu_name"));
				stu.setStu_age(rs.getInt("stu_age"));
				stu.setStu_sex(rs.getString("stu_sex"));
				stu.setStu_numb(rs.getInt("stu_numb"));
			}
			rs.close();
			pstmt.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return stu;
	}
}
