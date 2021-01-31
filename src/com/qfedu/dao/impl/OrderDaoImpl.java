package com.qfedu.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.qfedu.dao.OrderDao;
import com.qfedu.entity.Order;
import com.qfedu.entity.OrderStatus;
import com.qfedu.entity.User;
import com.qfedu.util.PageUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    QueryRunner queryRunner=new QueryRunner(new ComboPooledDataSource());
    @Override
    public List<Order> findOrderByPage(PageUtil page,String condition) throws SQLException, InvocationTargetException, IllegalAccessException {
        String sql="select o.*,u.username username,s.id sid,s.order_status statusName\n" +
                "from t_order o\n" +
                "left join t_status s on o.`status`=s.id\n" +
                "left join t_user u on u.id=o.uid where 1=1 "+condition+" LIMIT ?,?";
        List<Map<String, Object>> query =
                queryRunner.query(sql, new MapListHandler(), (page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
        List<Order> list=new ArrayList<>();
        for (Map<String, Object> map : query) {
            Order order=new Order();
            User user=new User();
            OrderStatus orderStatus=new OrderStatus();
            BeanUtils.populate(order,map);
            BeanUtils.populate(user,map);
            user.setId(order.getUid());
            BeanUtils.populate(orderStatus,map);
            order.setUser(user);
            order.setOrderStatus(orderStatus);
            list.add(order);
        }
        return list;
    }

    @Override
    public int findOrderCount(String condition) throws SQLException {
        String sql="select count(1) from t_order o left join t_user u on u.id=o.uid where 1=1 "+condition;
        long query = queryRunner.query(sql, new ScalarHandler<>());
        return (int)query;
    }

    @Override
    public List<OrderStatus> findAllOrderStatus() throws SQLException {
        String sql="select id sid,order_status statusName from t_status";
        List<OrderStatus> query = queryRunner.query(sql, new BeanListHandler<>(OrderStatus.class));
        return query;
    }

    @Override
    public void deleteOrderById(Integer id) throws SQLException {
        String sql="delete from t_order where id=?";
        queryRunner.update(sql,id);
    }
}
