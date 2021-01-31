package com.qfedu.service.impl;

import com.qfedu.dao.UserDao;
import com.qfedu.dao.impl.UserDaoImpl;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao=new UserDaoImpl();
    @Override
    public int findUserCount(String condition) throws SQLException {
        return dao.findUserCount(condition);
    }

    @Override
    public List<User> findUserByPage(PageUtil pageUtil, String condition) throws SQLException {
        return dao.findUserByPage(pageUtil,condition);
    }

    @Override
    public void updateResetPass(int id) throws SQLException {
        dao.updateResetPass(id);
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        dao.deleteUser(id);
    }

    @Override
    public void addUser(User user) throws SQLException {
        dao.addUser(user);
    }

    @Override
    public User findUserByNamePass(User user) throws SQLException {
        return dao.findUserByNamePass(user);
    }
}
