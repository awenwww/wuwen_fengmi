package com.qfedu.service;

import com.qfedu.entity.Order;
import com.qfedu.entity.OrderStatus;
import com.qfedu.util.PageUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    List<Order> findOrderByPage(PageUtil page, String condition) throws SQLException, InvocationTargetException, IllegalAccessException;
    int findOrderCount(String condition) throws SQLException;
    List<OrderStatus> findAllOrderStatus() throws SQLException;
    void deleteOrderById(Integer id) throws SQLException;
}
