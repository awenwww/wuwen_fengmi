package com.qfedu.dao;

import com.qfedu.entity.Goods;
import com.qfedu.util.PageUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface GoodsDao {
    List<Goods> findGoodsByPage(PageUtil pageUtil,String condition) throws SQLException, InvocationTargetException, IllegalAccessException;
    int findGoodsCount(String condition) throws SQLException;
    void updateGoods(Goods goods) throws SQLException;
    void addGoods(Goods goods) throws SQLException;
    Goods findGoodsByGid(Integer gid) throws SQLException, InvocationTargetException, IllegalAccessException;
    void deleteGoodsById(int id) throws SQLException;
}
