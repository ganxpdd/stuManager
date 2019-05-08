package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.student;
import dao.stuDao;
import util.jdbcutil;

public class showServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doPost(req, resp);

	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		stuDao dao = new stuDao();
		int Count = dao.selectAll();
		req.getSession().setAttribute("pageCount", Count);
		String pageSize = req.getParameter("pageSize");
		String pageNum = req.getParameter("pageNum");

		System.out.println("size="+pageSize+"num="+pageNum);

		int size = Integer.parseInt(pageSize);
		int num =Integer.parseInt(pageNum);
		List<student> list = dao.selectAll(num,size);
		req.getSession().setAttribute("stuList", list);

		if(size!=5) {
			
		}else {
			req.setAttribute("pageNum",pageNum);
			resp.sendRedirect("main1.jsp");
		}

	}

}
