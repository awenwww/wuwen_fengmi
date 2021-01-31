package com.qfedu.dao;

import com.qfedu.entity.User;
import com.qfedu.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    int findUserCount(String condition) throws SQLException;
    List<User> findUserByPage(PageUtil pageUtil,String condition) throws SQLException;
    void updateResetPass(int id) throws SQLException;
    void deleteUser(int id) throws SQLException;
    void addUser(User user) throws SQLException;
    User findUserByNamePass(User user) throws SQLException;
}
