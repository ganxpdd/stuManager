package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.student;
import dao.stuDao;
import util.DBUtil;

/**
 * Servlet implementation class stuServlet
 */
@WebServlet("/stuServlet")
public class stuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * 判断请求参数是否存在，是否有数据输入
	 * @param str
	 * @return
	 */
	private boolean isNotEmpty(String str){
		if (str == null || str.equals("")) {
			return false;
		}
		return true;
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String Numb1 = req.getParameter("numb");
		String Name1 = req.getParameter("name");
		System.out.println(Numb1+Name1);
		
		String Id = req.getParameter("id");
		req.setAttribute("id", Id);
		
		String Numb=req.getParameter("numb");
		req.setAttribute("Snumb", Numb);

		String name = req.getParameter("name");
		req.setAttribute("Sname", name);

		String minAge = req.getParameter("minAge");
		req.setAttribute("MinAge", minAge);
		String maxAge = req.getParameter("maxAge");
		req.setAttribute("MaxAge", maxAge);

		String sex = req.getParameter("sex");	
		req.setAttribute("Ssex", sex);
		
		if(Numb!=""||name!=""||minAge!=null||maxAge!=""||sex!="") {
			/*String ID=req.getParameter("Sid");
			System.out.println("Sid值为："+ID);*/
			
			
			//动态查找

			Connection conn=null;
			String baseSQL="select id,stu_numb,stu_name,stu_age,stu_sex from stu_list where 1=1";
			System.out.println(baseSQL);
			List<student> list1 = new ArrayList<student>();//总条数
			List<String> params = new ArrayList<String>();

			StringBuilder builder = new StringBuilder();// 用于拼接SQL语句

			builder.append(baseSQL);

			if( isNotEmpty(Numb)){
				builder.append(" and stu_numb=? ");
				params.add("numb," + Numb);
			}
			if(isNotEmpty(name)) {
				builder.append(" and stu_name like \"%\"?\"%\"");
				params.add("name,"+name);
			}

			if(isNotEmpty(minAge)) {
				builder.append(" and stu_age>=?");
				params.add("min,"+minAge);
			}
			if(isNotEmpty(maxAge)) {
				builder.append(" and stu_age<=?");
				params.add("max,"+maxAge);
			}
			if(isNotEmpty(sex)) {
				builder.append(" and stu_sex=?");
				params.add("sex,"+sex);
			}
			//查询得出总条数；
			String sql1 = builder.toString();
			try {
				conn = DBUtil.getConn();
				PreparedStatement ps = conn.prepareStatement(sql1);
				System.out.println(sql1);

				for (int i = 0; i < params.size(); i++) {
					String str = params.get(i);
					String[] arr = str.split(",");
					if(arr[0].equals("numb")) {
						int numb = Integer.parseInt(arr[1]);
						ps.setInt(i+1, numb);
					}else if(arr[0].equals("name")) {
						ps.setString(i + 1, arr[1]);
					}else if(arr[0].equals("min")) {//最小年龄
						int min = Integer.parseInt(arr[1]);
						ps.setInt(i + 1, min);
					}else if(arr[0].equals("max")) {//最大年龄
						int max = Integer.parseInt(arr[1]);
						ps.setInt(i + 1, max);
					}else if(arr[0].equals("sex")){
						ps.setString(i + 1, arr[1]);
					}			
				}
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					student stu = new student();
					stu.setId(rs.getInt("id"));
					stu.setStu_numb(rs.getInt("stu_numb"));
					stu.setStu_name(rs.getString("stu_name"));
					stu.setStu_age(rs.getInt("stu_age"));
					stu.setStu_sex(rs.getString("stu_sex"));
					list1 .add(stu);
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			int Count = list1.size();
			if (Count == 0) {
				 resp.getWriter().write("查询失败，页面将在3秒内跳转，如不跳转请点击<a href='ceshi.jsp' color='red'>主页</a>");
			 resp.setHeader("refresh", "3;ceshi.jsp"); 
			}
			else {
				req.setAttribute("stuList", list1);
				ObjectMapper mapper = new ObjectMapper();
				String jsonlist = mapper.writeValueAsString(list1);
				String json = "{\"total\":" + Count + ",\"rows\":" + jsonlist + "}";
				out.print(json);
				//req.getRequestDispatcher("ceshi.jsp").forward(req, resp);
			}
		}else {
			
			List<student> list = new ArrayList<student>();
			try {
				Connection conn = DBUtil.getConn();
				String sql = "select * from stu_list";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					student stu = new student();
					stu.setId(rs.getInt("id"));
					stu.setStu_numb(rs.getInt("stu_numb"));
					stu.setStu_name(rs.getString("stu_name"));
					stu.setStu_age(rs.getInt("stu_age"));
					stu.setStu_sex(rs.getString("stu_sex"));
					list .add(stu);
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();
			String jsonlist = mapper.writeValueAsString(list);
			stuDao dao = new stuDao();
			int total =dao.selectAll();
			//System.out.println(jsonlist);
			String json = "{\"total\":" + total + ",\"rows\":" + jsonlist + "}";
			out.print(json);
		}
		
		
	}

}
