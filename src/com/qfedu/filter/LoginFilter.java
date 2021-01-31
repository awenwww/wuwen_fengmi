package com.qfedu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        request.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=utf-8");
        Object admin = request.getSession().getAttribute("admin");
        String requestURI = request.getRequestURI();
        if (admin!=null){
            chain.doFilter(request, response);
        }else if(requestURI.contains("LoginServlet") || requestURI.contains("login.jsp") || requestURI.contains(".css") || requestURI.contains(".png")){
            chain.doFilter(request, response);
        }else{
            response.sendRedirect("after/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
