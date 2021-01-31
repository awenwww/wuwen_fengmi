package com.qfedu.service.impl;

import com.qfedu.dao.GoodsTypeDao;
import com.qfedu.dao.impl.GoodsTypeDaoImpl;
import com.qfedu.entity.GoodsType;
import com.qfedu.service.GoodsTypeService;
import com.qfedu.util.PageUtil;

import java.sql.SQLException;
import java.util.List;

public class GoodsTypeServiceImpl implements GoodsTypeService {
    private GoodsTypeDao dao=new GoodsTypeDaoImpl();
    @Override
    public int getCount() throws SQLException {
        return dao.getCount();
    }

    @Override
    public List<GoodsType> findGoodsTypeByPage(PageUtil pageUtil) throws SQLException {
        return dao.findGoodsTypeByPage(pageUtil);
    }

    @Override
    public void addGoodsType(String goodTypeName) throws SQLException {
        dao.addGoodsType(goodTypeName);
    }

    @Override
    public void updateGoodsType(GoodsType goodsType) throws SQLException {
        dao.updateGoodsType(goodsType);
    }

    @Override
    public GoodsType findGoodsTypeById(int id) throws SQLException {
        return dao.findGoodsTypeById(id);
    }

    @Override
    public void deleteGoodsTypeById(Integer id) throws SQLException {
        dao.deleteGoodsTypeById(id);
    }

    @Override
    public List<GoodsType> findAllGoodsType() throws SQLException {
        return dao.findAllGoodsType();
    }
}
