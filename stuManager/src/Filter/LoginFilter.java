package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    public LoginFilter() {
    	
    }
	public void destroy() {
		
	}
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		//将request、response的类型强转为Http
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String path = request.getRequestURI();
        if(path.indexOf("/login.jsp") > -1) {//登录页面不过滤
        	   chain.doFilter(request, response);
        	   return;
        	  }
        Object o = session.getAttribute("isSuccess");
        
        Boolean is = o==null?false:(Boolean)o;
        if(is) {
        	 chain.doFilter(request, response);
        }
        else {
            //request.setAttribute("error", "login_error");
            response.sendRedirect("login.jsp?error=login_error");
        	//request.getRequestDispatcher("login.jsp").forward(request, response);
        } 
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
