package servlet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.util.List;

import bean.student;
import dao.stuDao;
import dao.userDao;
import dao.impl.userDaoImpl;
import util.DBUtil;
import util.jdbcutil;
public class loginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	String driver = "com.mysql.jdbc.Driver";
	String uri = "jdbc:mysql://localhost:3306/stu?useUnicode=true&characterEncoding=UTF-8";

	String user = "root";
	String pwd = "123";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//转码
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String Uname = req.getParameter("username");
		String Upwd = req.getParameter("password");
		req.getSession().setAttribute("username", Uname);//首页显示用户名
		
		System.out.println("登录用户是："+Uname+"\r" + "你的密码是："+Upwd);

		userDao dao = new userDaoImpl();
		boolean isSuccess = dao.login(Uname,Upwd);

		if(isSuccess) {

			req.getSession().setAttribute("isSuccess", isSuccess);

			resp.sendRedirect("stu_list.jsp");

		}else {
			resp.sendRedirect("login.jsp?message=login_error");		
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
