package com.qfedu.test;

import com.qfedu.dao.AdminDao;
import com.qfedu.dao.GoodsDao;
import com.qfedu.dao.OrderDao;
import com.qfedu.dao.UserDao;
import com.qfedu.dao.impl.AdminDaoImpl;
import com.qfedu.dao.impl.GoodsDaoImpl;
import com.qfedu.dao.impl.OrderDaoImpl;
import com.qfedu.dao.impl.UserDaoImpl;
import com.qfedu.entity.*;
import com.qfedu.util.PageUtil;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class Demo {
    @Test
    public void test1() throws SQLException {
        AdminDao dao=new AdminDaoImpl();
        Admin adminByNamePass = dao.findAdminByNamePass(new Admin("admin", "admin"));
        System.out.println(adminByNamePass);
    }
    @Test
    public void test2() throws SQLException {
        UserDao dao=new UserDaoImpl();
       /* int userCount = dao.findUserCount("");
        System.out.println(userCount);
        List<User> userByPage = dao.findUserByPage(new PageUtil(3, 2, 1), "");
        for (User user : userByPage) {
            System.out.println(user);
        }*/
       dao.updateResetPass(4);

    }
    @Test
    public void test3() throws IllegalAccessException, SQLException, InvocationTargetException {
        System.out.println(new GoodsDaoImpl().findGoodsByGid(4));
    }
    @Test
    public void test4() throws IllegalAccessException, SQLException, InvocationTargetException {
        OrderDao dao=new OrderDaoImpl();
        List<Order> orderByPage = dao.findOrderByPage(new PageUtil(3, 4, 1),"");
        for (Order order : orderByPage) {
            System.out.println(order);
        }
        /*List<OrderStatus> allOrderStatus = dao.findAllOrderStatus();
        for (OrderStatus orderStatus : allOrderStatus) {
            System.out.println(orderStatus);
        }*/
    }
}
