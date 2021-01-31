package com.qfedu.service;

import com.qfedu.entity.Admin;

import java.sql.SQLException;

public interface AdminService {
    Admin findAdminByNamePass(Admin admin) throws SQLException;
}
