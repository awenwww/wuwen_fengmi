package com.qfedu.dao;

import com.qfedu.entity.GoodsType;
import com.qfedu.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public interface GoodsTypeDao {
    int getCount() throws SQLException;
    List<GoodsType> findGoodsTypeByPage(PageUtil pageUtil) throws SQLException;
    void addGoodsType(String goodTypeName) throws SQLException;
    void updateGoodsType(GoodsType goodsType) throws SQLException;
    GoodsType findGoodsTypeById(int id) throws SQLException;
    void deleteGoodsTypeById(Integer id) throws SQLException;
    List<GoodsType> findAllGoodsType() throws SQLException;
}
