package com.qfedu.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.qfedu.dao.AdminDao;
import com.qfedu.entity.Admin;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
    private QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource());

    /**
     * @Param: null
     * @return: null
     * @Author: Mr.Wu
     * @Description: &#x901a;&#x8fc7;username&#x548c;password&#x67e5;&#x8be2;admin&#x5728;&#x6570;&#x636e;&#x5e93;&#x4e2d;&#x662f;&#x5426;&#x5b58;&#x5728;
     * @Date: 2021/1/18
     */
    @Override
    public Admin findAdminByNamePass(Admin admin) throws SQLException {
        String sql = "select * from admin where username=? and password=?";
        Admin query = queryRunner.query(sql, new BeanHandler<>(Admin.class),admin.getUsername(),admin.getPassword());
        return query;
    }
}
