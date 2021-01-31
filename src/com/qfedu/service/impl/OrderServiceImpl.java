package com.qfedu.service.impl;

import com.qfedu.dao.OrderDao;
import com.qfedu.dao.impl.OrderDaoImpl;
import com.qfedu.entity.Order;
import com.qfedu.entity.OrderStatus;
import com.qfedu.service.OrderService;
import com.qfedu.util.PageUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDao dao=new OrderDaoImpl();
    @Override
    public List<Order> findOrderByPage(PageUtil page, String condition) throws SQLException, InvocationTargetException, IllegalAccessException {
        return dao.findOrderByPage(page,condition);
    }

    @Override
    public int findOrderCount(String condition) throws SQLException {
        return dao.findOrderCount(condition);
    }

    @Override
    public void deleteOrderById(Integer id) throws SQLException {
        dao.deleteOrderById(id);
    }

    @Override
    public List<OrderStatus> findAllOrderStatus() throws SQLException {
        return dao.findAllOrderStatus();

    }
}
