package com.qfedu.controller;

import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.service.impl.UserServiceImpl;
import com.qfedu.util.BaseServlet;
import com.qfedu.util.PageUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    public void findUserByPage(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取当前页数
        String pNo = request.getParameter("pageNo");
        if (pNo == null) { //如果pNo等于null的话默认为1
            pNo = "1";
        }
        int pageNo = Integer.parseInt(pNo);//将字符串转换为int
        //判断模糊查询
        //准备接收用StringBuffer
        StringBuffer sb = new StringBuffer();
        String username = request.getParameter("username");
        String sex = request.getParameter("sex");
        System.out.println(username + "--->" + sex);
        //如果两个条见都有勾选
        if (username != null && !"".equals(username) && sex != null && !"".equals(sex)) {
            sb.append("and username like '%" + username + "%'" + "and sex ='" + sex + "'");
        } else if (username != null && !"".equals(username)) {
            sb.append("and username like '%" + username + "%'");
        } else if (sex != null && !"".equals(sex)) {
            sb.append("and sex ='" + sex + "'");
        }
        //将信息存起来
        request.setAttribute("fusername", username);
        request.setAttribute("fsex", sex);

        //将其转换成字符串
        String condition = sb.toString();

        PageUtil pageUtil = null;
        try {
            //查询到在该条见下有多少条数据
            int userCount = service.findUserCount(condition);
            pageUtil = new PageUtil(5, userCount, pageNo);
            //查询在该条见下的所有用户信息
            List<User> userByPage = service.findUserByPage(pageUtil, condition);
            //将信息存储起来 转发带过去
            request.setAttribute("page", pageUtil);
            request.setAttribute("list", userByPage);
            request.getRequestDispatcher("/after/user.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Param: id
     * @return: null
     * @Author: Mr.Wu
     * @Description: 根据前端传过来的id重置用户的密码为123456
     * @Date: 2021/1/18
     */
    public void resetPass(HttpServletRequest request, HttpServletResponse response) {
        try {
            service.updateResetPass(Integer.parseInt(request.getParameter("id")));
            //重置密码以后以后从新定位到展示所有的界面
            response.sendRedirect(request.getContextPath() + "/UserServlet?method=findUserByPage");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Param:
     * @return: null
     * @Author: Mr.Wu
     * @Description: 删除用户信息 根据用户的id
     * @Date: 2021/1/18
     */
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            service.deleteUser(Integer.parseInt(request.getParameter("id")));
            //删除以后从新定位到展示所有的界面
            response.sendRedirect(request.getContextPath() + "/UserServlet?method=findUserByPage");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Param:
     * @return: null
     * @Author: Mr.Wu
     * @Description: 从前端获取的数据 然后添加用户 然后从定向到所有用户信息展示界面
     * @Date: 2021/1/18
     */
    public void addUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            User user = new User();
            BeanUtils.populate(user, request.getParameterMap());
            service.addUser(user);
            //添加成功以后然后跳转界面
            response.sendRedirect(request.getContextPath() + "/UserServlet?method=findUserByPage");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void userLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
            System.out.println(user);
            User userByNamePass = service.findUserByNamePass(user);
            if (userByNamePass != null) {
                request.getSession().setAttribute("userLogin", userByNamePass);
                response.sendRedirect(request.getContextPath() + "/before/index.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/before/login.html");
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userRegister(HttpServletRequest request,HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user =new User();
            BeanUtils.populate(user,parameterMap);
            System.out.println(user);
            service.addUser(user);
            request.getRequestDispatcher("/before/success.html").forward(request,response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





