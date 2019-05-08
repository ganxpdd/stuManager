package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.student;
import dao.stuDao;

public class UpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	stuDao dao = new stuDao();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ID=req.getParameter("id");
		System.out.println("id值为："+ID);
		if(ID!=null && !ID.equals("")) {
			int id =Integer.valueOf(ID);
			student stu	= dao.selectAllById(id);

			System.out.println("id值为："+id);
			req.setAttribute("stu", stu);
			req.setAttribute("id", id);
			req.setAttribute("stu_name", stu.getStu_name());
			req.setAttribute("stu_numb", stu.getStu_numb());
			req.setAttribute("stu_age", stu.getStu_age());
			req.setAttribute("stu_sex", stu.getStu_sex());
		}
		req.getRequestDispatcher("update.jsp").forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		student stu= new student();
		int id = Integer.valueOf(req.getParameter("id"));

		String name = req.getParameter("stu_name");

		String sex = req.getParameter("stu_sex");

		int age = Integer.valueOf(req.getParameter("stu_age"));

		int numb = Integer.valueOf(req.getParameter("stu_numb"));

		stu.setId(id);
		stu.setStu_name(name);
		stu.setStu_age(age);
		stu.setStu_sex(sex);	
		stu.setStu_numb(numb);/*
		if(dao.selectNumb(numb)) {*/
			if(dao.Update(stu)) {
				List<student> list = dao.selectAll(0,5);
				req.getSession().setAttribute("stuList", list);
				resp.sendRedirect("ceshi.jsp");
			}else{
				req.setAttribute("id", id);
				req.setAttribute("stu_name", name);
				req.setAttribute("stu_numb", numb);
				req.setAttribute("stu_age", age);
				req.setAttribute("stu_sex", sex);
				req.getRequestDispatcher("update.jsp?error=update_error").forward(req, resp);
				//resp.sendRedirect("update.jsp?error=update_error");
			}
		/*}else {
			req.setAttribute("id", id);
			req.setAttribute("stu_name", name);
			req.setAttribute("stu_numb", numb);
			req.setAttribute("stu_age", age);
			req.setAttribute("stu_sex", sex);
			String massage = "你修改的学号已存在！";
			req.setAttribute("numbError", massage);
			req.getRequestDispatcher("update.jsp?error=update_error").forward(req, resp);
		}*/
	}

}
