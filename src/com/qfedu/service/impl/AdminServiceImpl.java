package com.qfedu.service.impl;

import com.qfedu.dao.AdminDao;
import com.qfedu.dao.impl.AdminDaoImpl;
import com.qfedu.entity.Admin;
import com.qfedu.service.AdminService;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    private AdminDao dao=new AdminDaoImpl();
    @Override
    public Admin findAdminByNamePass(Admin admin) throws SQLException {
        return dao.findAdminByNamePass(admin);
    }
}
