package com.qfedu.controller;

import com.qfedu.entity.Order;
import com.qfedu.entity.OrderStatus;
import com.qfedu.service.OrderService;
import com.qfedu.service.impl.OrderServiceImpl;
import com.qfedu.util.BaseServlet;
import com.qfedu.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
    OrderService service=new OrderServiceImpl();
    public void findOrdersByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pNo = request.getParameter("pageNo");
        if(pNo==null){
            pNo="1";
        }
        int pageNo=Integer.parseInt(pNo);
        try {
            String username = request.getParameter("username");
            String status=request.getParameter("status");
            request.setAttribute("username",username);
            request.setAttribute("status",status);
            System.out.println(username+"--->"+status);
            StringBuffer condition=new StringBuffer();
            if(username!=null && !"".equals(username)){
                condition.append("and u.username like '%"+username+"%'");
            }
            if(status!=null && !"".equals(status)){
                condition.append(" and o.status="+status);
            }

            PageUtil page=new PageUtil(4,service.findOrderCount(condition.toString()),pageNo);
            List<Order> orderByPage = service.findOrderByPage(page, condition.toString());
            request.setAttribute("page",page);
            request.setAttribute("list",orderByPage);
            List<OrderStatus> allOrderStatus = service.findAllOrderStatus();
            request.setAttribute("orderStatus",allOrderStatus);
            request.getRequestDispatcher("/after/order_processing_list.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void deleteOrderById(HttpServletRequest request,HttpServletResponse response){
        String parameter = request.getParameter("ordersid");
        try {
            service.deleteOrderById(Integer.parseInt(parameter));
            response.sendRedirect(request.getContextPath()+"/OrderServlet?method=findOrdersByPage");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
