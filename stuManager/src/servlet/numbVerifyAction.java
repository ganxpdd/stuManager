package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.stuDao;

/**
 * remote 判断numb是否存在
 * Servlet implementation class numbVerifyAction
 */
@WebServlet("/numbVerifyAction")
public class numbVerifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String stu_numb = req.getParameter("stu_numb");
		int numb = Integer.valueOf(stu_numb);
		String ID = req.getParameter("id");
		System.out.println(ID);
		int id;
		if(ID==null) {
			 id = 0;
		}else {
			id = Integer.valueOf(ID);
		}
		
		stuDao dao = new stuDao();

		if(dao.selectNumb(numb,id)){
			resp.getWriter().print(true);                                
		}
		else{
			resp.getWriter().print(false);
		}
	}

}
