<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2021/1/19
  Time: 8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="../js/jquery-1.8.3.min.js"></script>
<nav class="navbar navbar-inverse" style="margin-bottom: 0px;">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                    aria-expanded="false">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">锋迷网管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" id="types">
                <li><a href="<%=path%>/GoodsServlet?method=findGoodsByPage">商品管理 <span
                        class="sr-only">(current)</span></a></li>
                <li><a href="<%=path%>/GoodsTypeServlet?method=findGoodsTypeByPage">商品类型管理</a></li>
                <li><a href="<%=path%>/UserServlet?method=findUserByPage">用户管理</a></li>
                <li><a href="<%=path%>/OrderServlet?method=findOrdersByPage">订单管理</a></li>
                <li><a href="${pageContext.request.contextPath}/before/index.jsp">直通锋迷网</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="<%=path%>/after/login.jsp">${admin.username}</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-log-in"
                                      style="padding-left: 0px"></span></a></li>
                <li><a href="<%=path%>/LoginOutServlet"
                       style="padding-left: 0px">退出</a></li>

            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
<script>
    $(function () {
        var text=window.location.href
        if(text.indexOf("UserServlet")>=0){
            $("#types li:eq(2)").addClass("active").siblings().removeClass("active")
        }
        if(text.indexOf("GoodsServlet")>=0){
            $("#types li:eq(0)").addClass("active").siblings().removeClass("active")
        }
        if(text.indexOf("GoodsTypeServlet")>=0){
            $("#types li:eq(1)").addClass("active").siblings().removeClass("active")
        }
        if(text.indexOf("OrderServlet")>=0){
            $("#types li:eq(3)").addClass("active").siblings().removeClass("active")
        }
    })
</script>
