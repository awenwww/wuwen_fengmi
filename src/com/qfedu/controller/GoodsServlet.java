package com.qfedu.controller;

import com.qfedu.entity.Goods;
import com.qfedu.entity.GoodsType;
import com.qfedu.service.GoodsService;
import com.qfedu.service.GoodsTypeService;
import com.qfedu.service.impl.GoodsServiceImpl;
import com.qfedu.service.impl.GoodsTypeServiceImpl;
import com.qfedu.util.BaseServlet;
import com.qfedu.util.PageUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/GoodsServlet")
public class GoodsServlet extends BaseServlet {
    GoodsService service=new GoodsServiceImpl();
    public void findGoodsByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
       //从session中获取存储过得所有商品类别
        HttpSession session = request.getSession();
        List<GoodsType> typeList = (List<GoodsType>) session.getAttribute("typeList");
        if(typeList==null){
            GoodsTypeService goodsTypeService=new GoodsTypeServiceImpl();
            List<GoodsType> allGoodsType = null;
            try {
                allGoodsType = goodsTypeService.findAllGoodsType();
                request.setAttribute("typeList",allGoodsType);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //获取模糊查询的信息
        String goodsname = request.getParameter("goodsname");
        String deployDate = request.getParameter("deployDate");
        String id = request.getParameter("id");

        request.setAttribute("goodsname",goodsname);
        request.setAttribute("deployDate",deployDate);
        request.setAttribute("id",id);

        //判断
        StringBuffer sb=new StringBuffer();
        if(goodsname!=null && !"".equals(goodsname) && deployDate!=null && !"".equals(deployDate) && id!=null && !"".equals(id)){
            sb.append("and goodsname like '%"+goodsname+"%'"+" and deployDate='"+deployDate+"'"+" and t.id='"+id+"'");
        }else if(goodsname!=null && !"".equals(goodsname) && deployDate!=null && !"".equals(deployDate)){
            sb.append("and goodsname like '%"+goodsname+"%' "+" and deployDate='"+deployDate+"'");
        }else if(deployDate!=null && !"".equals(deployDate) && id!=null && !"".equals(id)){
            sb.append("and deployDate='"+deployDate+"'"+" and t.id='"+id+"'");
        }else if(goodsname!=null && !"".equals(goodsname) && id!=null && !"".equals(id)){
            sb.append("and goodsname like '%"+goodsname+"%'"+" and t.id='"+id+"'");
        }else if(goodsname!=null && !"".equals(goodsname)){
            sb.append("and goodsname like '%"+goodsname+"%'");
        }else if(deployDate!=null && !"".equals(deployDate)){
            sb.append("and deployDate='"+deployDate+"'");
        }else if(id!=null && !"".equals(id)){
            sb.append("and t.id='"+id+"'");
        }


        String pNo = request.getParameter("pageNo");
        if(pNo==null){
            pNo="1";
        }
        int pageNo= Integer.parseInt(pNo);
        try {
            int goodsCount = service.findGoodsCount(sb.toString());
            PageUtil pageUtil=new PageUtil(5,goodsCount,pageNo);
            List<Goods> goodsByPage = service.findGoodsByPage(pageUtil,sb.toString());
            request.setAttribute("page",pageUtil);
            request.setAttribute("goodsList",goodsByPage);
            request.getRequestDispatcher("after/goods_list.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
    *
    * @Param:
    * @return: null
    * @Author: Mr.Wu
    * @Description: 跳转到添加界面
    * @Date: 2021/1/19
    */
    public void toAddGoods(HttpServletRequest request,HttpServletResponse response){
        try {
            HttpSession session = request.getSession();
            List<GoodsType> typeList = (List<GoodsType>) session.getAttribute("typeList");
            if(typeList==null){
                GoodsTypeService goodsTypeService=new GoodsTypeServiceImpl();
                List<GoodsType> allGoodsType = null;
                try {
                    allGoodsType = goodsTypeService.findAllGoodsType();
                    request.setAttribute("typeList",allGoodsType);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            request.getRequestDispatcher("/after/add_goods.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
    *
    * @Param:
    * @return: null
    * @Author: Mr.Wu
    * @Description: 跳转到修改Goods界面
    * @Date: 2021/1/19
    */
    public void toUpdateGoods(HttpServletRequest request,HttpServletResponse response){

        try {
            String gid = request.getParameter("gid");
            HttpSession session = request.getSession();
            List<GoodsType> typeList = (List<GoodsType>) session.getAttribute("typeList");
            if(typeList==null){
                GoodsTypeService goodsTypeService=new GoodsTypeServiceImpl();
                List<GoodsType> allGoodsType = null;
                try {
                    allGoodsType = goodsTypeService.findAllGoodsType();
                    request.setAttribute("typeList",allGoodsType);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Goods goodsByGid = service.findGoodsByGid(Integer.parseInt(gid));
            request.setAttribute("goods",goodsByGid);
            request.getRequestDispatcher("/after/add_goods.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Param:
     * @return: null
     * @Author: Mr.Wu
     * @Description: 对商品进行添加或者是修改
     * @Date: 2021/1/19
     */
    public void updateOrAdd(HttpServletRequest request,HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String gid = request.getParameter("gid");
        System.out.println(gid);

        Map<String, String[]> parameterMap = request.getParameterMap();
        GoodsType goodsType=new GoodsType();
        Goods goods=new Goods();
        try {
            BeanUtils.populate(goodsType,parameterMap);
            goods.setGoodsType(goodsType);
            BeanUtils.populate(goods,parameterMap);
            System.out.println(goods);
            if(gid!=null && !"".equals(gid)){
                service.updateGoods(goods);
            }else{
                service.addGoods(goods);
            }
            response.sendRedirect(request.getContextPath()+"/GoodsServlet?method=findGoodsByPage");

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteGoods(HttpServletRequest request,HttpServletResponse response){
        String goodsid = request.getParameter("goodsid");
        System.out.println(goodsid);
        String[] split = goodsid.split(",");
        try {
            service.deleteGoodsById(split);
            response.sendRedirect(request.getContextPath()+"/GoodsServlet?method=findGoodsByPage");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
