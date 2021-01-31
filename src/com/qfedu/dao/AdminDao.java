package com.qfedu.dao;

import com.qfedu.entity.Admin;

import java.sql.SQLException;

public interface AdminDao {
     Admin findAdminByNamePass(Admin admin) throws SQLException;
}
