package com.qfedu.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.qfedu.dao.GoodsDao;
import com.qfedu.entity.Goods;
import com.qfedu.entity.GoodsType;
import com.qfedu.util.PageUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GoodsDaoImpl implements GoodsDao {
    private QueryRunner queryRunner=new QueryRunner(new ComboPooledDataSource());
    @Override
    public List<Goods> findGoodsByPage(PageUtil pageUtil,String condition) throws SQLException, InvocationTargetException, IllegalAccessException {
        String sql="select g.id gid,goodsname,price,score,deployDate,imgPath,comment,t.*\n" +
                "from t_goods g\n" +
                "left join t_goodstype t\n" +
                "on g.typeId=t.id where 1=1 "+condition+"limit ?,?";
        List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler(),(pageUtil.getPageNo()-1)*pageUtil.getPageSize(),pageUtil.getPageSize());
        List<Goods> list=new ArrayList<>();
        for (Map<String, Object> map : query) {
            GoodsType goodsType = new GoodsType();
            BeanUtils.populate(goodsType,map);
            Goods goods=new Goods();
            goods.setGoodsType(goodsType);
            BeanUtils.populate(goods,map);
            list.add(goods);
        }

        return list;
    }

    @Override
    public int findGoodsCount(String condition) throws SQLException {
        String sql="select count(1) from t_goods g left join t_goodstype t on g.typeId=t.id where 1=1 "+condition;
        long count=queryRunner.query(sql,new ScalarHandler<>());
        return (int)count;
    }

    @Override
    public void updateGoods(Goods goods) throws SQLException {
    String sql="update t_goods set goodsName=?,price=?,typeId=?,comment=? where id=?";
    queryRunner.update(sql,goods.getGoodsname(),goods.getPrice(),goods.getGoodsType().getId(),goods.getComment(),goods.getGid());
    }

    @Override
    public Goods findGoodsByGid(Integer gid) throws SQLException, InvocationTargetException, IllegalAccessException {
        String sql="select g.id gid,goodsname,price,score,deployDate,imgPath,comment,t.*\n" +
                "from t_goods g\n" +
                "left join t_goodstype t\n" +
                "on g.typeId=t.id where g.id=?";
        Map<String, Object> map = queryRunner.query(sql, new MapHandler(),gid);

            Goods goods=new Goods();
            GoodsType goodsType=new GoodsType();
            BeanUtils.populate(goodsType,map);
            BeanUtils.populate(goods,map);
            goods.setGoodsType(goodsType);
       return goods;
    }

    @Override
    public void addGoods(Goods goods) throws SQLException {
        String sql="insert into t_goods(goodsName,price,typeId,comment) values(?,?,?,?)";
        queryRunner.update(sql,goods.getGoodsname(),goods.getPrice(),goods.getGoodsType().getId(),goods.getComment());
    }

    @Override
    public void deleteGoodsById(int id) throws SQLException {
        String sql="delete from t_goods where id=?";
        queryRunner.update(sql,id);
    }
}
