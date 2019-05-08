package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.user;
import dao.userDao;
import dao.impl.userDaoImpl;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		user u = new user();
		userDao dao = new userDaoImpl();
		
		String name = request.getParameter("userName");
		String pwd = request.getParameter("password");
		

		u.setUser_name(name);
		u.setUser_pwd(pwd);

		dao.addUser(u);
		response.sendRedirect("login.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
