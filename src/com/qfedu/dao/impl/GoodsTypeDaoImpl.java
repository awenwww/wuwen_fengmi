package com.qfedu.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.qfedu.dao.GoodsTypeDao;
import com.qfedu.entity.GoodsType;
import com.qfedu.util.PageUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodsTypeDaoImpl implements GoodsTypeDao {
    private QueryRunner queryRunner=new QueryRunner(new ComboPooledDataSource());
    /**
    *
    * @Param:
    * @return: null
    * @Author: Mr.Wu
    * @Description: 获取商品类别的数量
    * @Date: 2021/1/18
    */
    @Override
    public int getCount() throws SQLException {
        String sql="select count(1) from t_goodstype";
        long query = queryRunner.query(sql, new ScalarHandler<>());
        return (int)query;
    }
    /**
    *
    * @Param:
    * @return: null
    * @Author: Mr.Wu
    * @Description: 商品类别分类
    * @Date: 2021/1/19
    */
    @Override
    public List<GoodsType> findGoodsTypeByPage(PageUtil pageUtil) throws SQLException {
        String sql="select * from t_goodstype limit ?,? ";

        return queryRunner.query(sql,new BeanListHandler<>(GoodsType.class),(pageUtil.getPageNo()-1)*pageUtil.getPageSize(),pageUtil.getPageSize());
    }
    /**
    *
    * @Param: 新增商品的名字
    * @return: null
    * @Author: Mr.Wu
    * @Description: 添加一个新的商品
    * @Date: 2021/1/19
    */
    @Override
    public void addGoodsType(String goodTypeName) throws SQLException {
        String sql="insert into t_goodstype(typename) values(?)";
        queryRunner.update(sql,goodTypeName);
    }
    /**
    *
    * @Param:
    * @return: null
    * @Author: Mr.Wu
    * @Description: 根据用户id修改商品的类型
    * @Date: 2021/1/19
    */
    @Override
    public void updateGoodsType(GoodsType goodsType) throws SQLException {
        String sql="update t_goodstype set typename=? where id= ?";
        queryRunner.update(sql,goodsType.getTypename(),goodsType.getId());
    }
    /**
    *
    * @Param:
    * @return: null
    * @Author: Mr.Wu
    * @Description: 根据商品类别id然后查询到该商品
    * @Date: 2021/1/19
    */
    @Override
    public GoodsType findGoodsTypeById(int id) throws SQLException {
        String sql="select * from t_goodstype where id=?";
        GoodsType query = queryRunner.query(sql, new BeanHandler<>(GoodsType.class), id);
        return query;
    }

    @Override
    public void deleteGoodsTypeById(Integer id) throws SQLException {
        String sql="delete from t_goodstype where id=?";
        queryRunner.update(sql,id);
    }

    @Override
    public List<GoodsType> findAllGoodsType() throws SQLException {
        String sql="select * from t_goodstype";
        List<GoodsType> query = queryRunner.query(sql, new BeanListHandler<>(GoodsType.class));
        return query;
    }
}
