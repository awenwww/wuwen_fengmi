package com.qfedu.controller;

import com.qfedu.entity.Admin;
import com.qfedu.service.AdminService;
import com.qfedu.service.impl.AdminServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端数据 然后封装
        request.setCharacterEncoding("utf-8");
        Admin admin=new Admin();
        AdminService service=new AdminServiceImpl();
        try {
            BeanUtils.populate(admin,request.getParameterMap());
            Admin adminByNamePass = service.findAdminByNamePass(admin);
            request.getSession().setAttribute("admin",adminByNamePass);
            //返回值为true允许登录 跳转到user.jsp
            if(adminByNamePass!=null){

                response.sendRedirect(request.getContextPath()+"/UserServlet?method=findUserByPage");
            }else{
                //登录失败 重新跳转到登录界面
                response.sendRedirect(request.getContextPath()+"/after/login.jsp");
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
