package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.student;
import dao.stuDao;

public class addServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");


		student stu = new student();
		stuDao dao = new stuDao();

		String name = req.getParameter("stu_name");
		String sex = req.getParameter("stu_sex");
		String age1 = req.getParameter("stu_age");
		int age = Integer.parseInt(age1);
		String numb1 = req.getParameter("stu_numb");
		int numb = Integer.parseInt(numb1);
		/*if(dao.selectNumb(numb)) {*/

			stu.setStu_name(name);
			stu.setStu_age(age);
			stu.setStu_sex(sex);
			stu.setStu_numb(numb);

			if(dao.addstu(stu)) {
				/*int Count = dao.selectAll();
				List<student> list = dao.selectAll(Count-5,5);
				req.setAttribute("stuList", list);*/
				resp.sendRedirect("ceshi.jsp");

			}else{
				req.setAttribute("stu_name", name);
				req.setAttribute("stu_numb", numb);
				req.setAttribute("stu_age", age);
				req.setAttribute("stu_sex", sex);
				resp.sendRedirect("add.jsp?error=addstu_error");
			}/*
		}else {
			req.setAttribute("stu_name", name);
			req.setAttribute("stu_numb", numb);
			req.setAttribute("stu_age", age);
			req.setAttribute("stu_sex", sex);
			String massage = "你添加的学号已存在！";
			req.setAttribute("numbError", massage);
			req.getRequestDispatcher("add.jsp?error=addstu_error").forward(req, resp);
		}*/
	}
}
