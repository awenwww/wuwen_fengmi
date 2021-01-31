package com.qfedu.controller;

import com.qfedu.entity.GoodsType;
import com.qfedu.service.GoodsTypeService;
import com.qfedu.service.impl.GoodsTypeServiceImpl;
import com.qfedu.util.BaseServlet;
import com.qfedu.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/GoodsTypeServlet")
public class GoodsTypeServlet extends BaseServlet {
    private GoodsTypeService service=new GoodsTypeServiceImpl();

    public void findGoodsTypeByPage(HttpServletRequest request, HttpServletResponse response){
        String pNo = request.getParameter("pageNo");
        if(pNo==null){
            pNo="1";
        }
        int pageNo=Integer.parseInt(pNo);
        PageUtil page=null;
        //查询商品类别的个数
        try {
            int count = service.getCount();
            page=new PageUtil(4,count,pageNo);
            List<GoodsType> goodsTypeByPage = service.findGoodsTypeByPage(page);
            request.setAttribute("goodsList",goodsTypeByPage);
            request.setAttribute("page",page);
            request.getRequestDispatcher("after/goods_type_list.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  toUpdateOrAddGoodsType(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        String id = request.getParameter("id");

            //如果是商品的修改的话先查到在页面上显示一下
            GoodsType goodsTypeById = service.findGoodsTypeById(Integer.parseInt(id));
            request.setAttribute("gp",goodsTypeById);
            try {
                request.getRequestDispatcher("/after/add_goods_type.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void updateOrAdd(HttpServletRequest request,HttpServletResponse response)  {
        String id = request.getParameter("id");
        String typename = request.getParameter("typename");

        if(id==null || "".equals(id)){
            try {
                service.addGoodsType(typename);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            GoodsType goodsType=new GoodsType(Integer.parseInt(id),typename);
            try {
                service.updateGoodsType(goodsType);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            response.sendRedirect(request.getContextPath()+"/GoodsTypeServlet?method=findGoodsTypeByPage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void deleteGoodsTypeById(HttpServletRequest request,HttpServletResponse response) {
        String id = request.getParameter("id");
        try {
            service.deleteGoodsTypeById(Integer.parseInt(id));
            response.sendRedirect(request.getContextPath()+"/GoodsTypeServlet?method=findGoodsTypeByPage");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
