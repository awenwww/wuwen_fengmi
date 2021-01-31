package com.qfedu.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.qfedu.dao.UserDao;
import com.qfedu.entity.User;
import com.qfedu.util.PageUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private QueryRunner queryRunner=new QueryRunner(new ComboPooledDataSource());
    /**
    *
    * @Param: condition String 拼接条件查询
    * @return: 返回查到的数据的总数
    * @Author: Mr.Wu
    * @Description:
    * @Date: 2021/1/18
    */
    @Override
    public int findUserCount(String condition) throws SQLException {
        String sql="select count(1) from t_user where 1=1 "+condition;
        long count=queryRunner.query(sql,new ScalarHandler<>());
        return (int)count;
    }
    /**
    *
    * @Param: pageUtil f分页需要的数据
    * @return: 返回根据分页信息查到的数据
    * @Author: Mr.Wu
    * @Description:
    * @Date: 2021/1/18
    */
    @Override
    public List<User> findUserByPage(PageUtil pageUtil, String condition) throws SQLException {
        String sql="select * from t_user where 1=1 "+condition+" limit ?,?";
        List<User> query = queryRunner.query(sql, new BeanListHandler<>(User.class),
                (pageUtil.getPageNo()-1)*pageUtil.getPageSize(),pageUtil.getPageSize());
        return query;
    }
    /** 
    * 
    * @Param: id 用户id 
    * @return: null 
    * @Author: Mr.Wu
    * @Description: 根据用户id 重置用户的密码为123456
    * @Date: 2021/1/18 
    */ 
    @Override
    public void updateResetPass(int id) throws SQLException {
        String sql="update t_user set password=123456 where id=?";
        int update = queryRunner.update(sql, id);

    }
    /**
    *
    * @Param: 用户id
    * @return: null
    * @Author: Mr.Wu
    * @Description: 根据用户的id删除用户
    * @Date: 2021/1/18
    */
    @Override
    public void deleteUser(int id) throws SQLException {
        String sql="delete from t_user where id=?";
        int update = queryRunner.update(sql, id);
    }
    /**
    *
    * @Param: User user
    * @return: null
    * @Author: Mr.Wu
    * @Description: 根据传过来的User 添加用户信息
    * @Date: 2021/1/18
    */
    @Override
    public void addUser(User user) throws SQLException {
        String sql="insert into t_user(username,password,phone,mail,sex) values(?,?,?,?,?)";
        int update = queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getPhone(), user.getMail(),user.getSex());
    }

    @Override
    public User findUserByNamePass(User user) throws SQLException {
        String sql="select * from t_user where username=? and password=?";
        User query = queryRunner.query(sql, new BeanHandler<>(User.class), user.getUsername(), user.getPassword());
        return query;
    }
}
