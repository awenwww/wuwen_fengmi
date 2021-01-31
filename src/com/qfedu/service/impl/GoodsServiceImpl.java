package com.qfedu.service.impl;

import com.qfedu.dao.GoodsDao;
import com.qfedu.dao.impl.GoodsDaoImpl;
import com.qfedu.entity.Goods;
import com.qfedu.service.GoodsService;
import com.qfedu.util.PageUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    GoodsDao dao=new GoodsDaoImpl();
    @Override
    public List<Goods> findGoodsByPage(PageUtil pageUtil,String condition) throws SQLException, InvocationTargetException, IllegalAccessException {

        return dao.findGoodsByPage(pageUtil,condition);
    }

    @Override
    public int findGoodsCount(String condition) throws SQLException {

        return dao.findGoodsCount(condition);
    }

    @Override
    public Goods findGoodsByGid(Integer gid) throws SQLException, InvocationTargetException, IllegalAccessException {
        return dao.findGoodsByGid(gid);
    }

    @Override
    public void updateGoods(Goods goods) throws SQLException {
        dao.updateGoods(goods);
    }

    @Override
    public void addGoods(Goods goods) throws SQLException {
        dao.addGoods(goods);
    }

    @Override
    public void deleteGoodsById(String[] ids) throws SQLException {
        for (int i = 0; i < ids.length; i++) {
            if(ids[i]!=null && !"".equals(ids[i])){
                dao.deleteGoodsById(Integer.parseInt(ids[i]));
            }
        }
    }
}
