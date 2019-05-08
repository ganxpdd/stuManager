package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.stuDao;

/**
 * Servlet implementation class utilServlet
 */
@WebServlet("/utilServlet")
public class utilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String list = request.getParameter("list");
		String Total = request.getParameter("total");
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonlist = mapper.writeValueAsString(list);
		int total = Integer.valueOf(Total);
		
		//System.out.println(jsonlist);
		String json = "{\"total\":" + total + ",\"rows\":" + jsonlist + "}";
		out.print(json);
	}

}
