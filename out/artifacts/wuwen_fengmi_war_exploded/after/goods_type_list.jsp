<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%String path=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>商品类型</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

<style>
    th {
        text-align: center;
    }

</style>
<script type="text/javascript">
    //取出传回来的参数error并与yes比较
    var errori ='<%=request.getParameter("error")%>';
    if(errori=='yes'){
        alert("删除失败!");
    }

    function showList(obj, id, name) {
        var jqobj = $(obj);
        $("#dropdownMenu2").html(jqobj.text() + "<span class='caret'></span>");
        $("#typeId").val(id);
    }

    function deleteConfirm(id, name) {

        confirm.show('溫馨提示', '您确定要刪除[' + name + ']商品类型吗？', {
            '确定': {
                'primary': true,
                'callback': function () {
                    location.href = "${pageContext.request.contextPath}/GoodsTypeDeleteServlet?id=" + id;
                }
            }
        });
    }

    function addGoodsType() {
        location.href = "<%=path%>/add_goods_type.jsp";
    }

    function showEdit(id, name) {
        confirm.show('溫馨提示', '您确定要修改[' + name + ']商品吗？', {
            '确定': {
                'primary': true,
                'callback': function () {
                    location.href = "${pageContext.request.contextPath}/GoodsTypeUpdateServlet?id=" + id;
                }
            }
        });
    }
</script>
</head>
<body>

<%@ include file="header.jsp"%>

<div class="jumbotron" style="padding-top:20px;padding-bottom:25px">
    <div class="container">
        <h2 id="ac" index="1">商品类型</h2>
    </div>
</div>
<a href="<%=path%>/after/add_goods_type.jsp">
    <div class="container" >
        <div class="row">
            <div class="col-md-6">
                <button type="button"  class="btn btn-info">添加商品分类</button>
            </div>
        </div>

        `
    </div></a>
<!--以下显示表格-->
<div class="container" style="margin-top:20px;">
    <form id="deleteFrom" action="${pageContext.request.contextPath}/goods/deleteBatch" method="post">
        <table style="text-align: center;margin-right:10px;width:99%"
               class="table table-striped table-hover table-bordered">
            <thead style="text-align: center;">
            <tr>
                <th>序号</th>
                <th>类型名称</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${goodsList}" var="goodsType" varStatus="obj">

                <tr>

                    <td>${goodsType.id}</td>
                    <td>${goodsType.typename}</td>
                        <%-- <td><a href="<%=path%>/after/add_goods_type.jsp?id=${goodsType.id}" onclick="if(confirm('是否确定编辑'+'${user.username}'+'？')==false)return false">编辑</a></td>
                        <td><a href="<%=path%>/GoodsTypeDeleteServlet?id=${goodsType.id}" onclick="if(confirm('是否确定删除'+'${goodsType.typename}'+'？')==false)return false">删除</a></td> --%>

                    <td><a href="<%=path%>/GoodsTypeServlet?method=toUpdateOrAddGoodsType&id=${goodsType.id}" onclick="if(confirm('是否确定编辑'+'${goodsType.typename}'+'？')==false)return false">
                        <span class="glyphicon glyphicon-edit"  color="#5BC0DE"></span></a></td>

                    <td><a class="glyphicon glyphicon-trash" color="#5BC0DE"
                           style="text-decoration:none"
                           href="<%=path%>/GoodsTypeServlet?method=deleteGoodsTypeById&id=${goodsType.id}" onclick="if(confirm('是否确定删除'+'${goodsType.typename}'+'？')==false)return false" ></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>


<div class="container">
    <nav aria-label="..." class="navbar-right" style="margin-right: 15px">
        <ul class="pagination">
            <li><a class=" btn-info"
                   href="<%=path%>/GoodsTypeServlet?method=findGoodsTypeByPage&pageNo=1" aria-label="Previous">
                <span aria-hidden="true">首页 </span>
            </a></li>
           <c:if test="${page.pageNo>1}">
               <li><a class=" btn-info"
                      href="<%=path %>/GoodsTypeServlet?method=findGoodsTypeByPage&pageNo=${page.pageNo-1}"
                      aria-label="Previous"> <span aria-hidden="true"> « </span></a></li>
           </c:if>

           <c:if test="${page.pageNo<=1}">
               <li class="disabled"><a href="#" aria-label="Previous">« <span
                       class="sr-only">(current) </span></a></li>
           </c:if>


           <c:if test="${page.pageNo<page.pageCount}">
               <li><a class="btn">${page.pageNo}</a></li>
               <li><a class="btn-info"
                      href="<%=path %>/GoodsTypeServlet?method=findGoodsTypeByPage&pageNo=${page.pageNo+1}"
                      aria-label="Next"><span aria-hidden="true">»</span></a></li>
               <li><a class=" btn-info"
                      href="<%=path %>/GoodsTypeServlet?method=findGoodsTypeByPage&pageNo=${page.pageCount}"
                      aria-label="Previous"> <span aria-hidden="true">尾页 </span></a></li>
           </c:if>

            <c:if test="${page.pageNo>=page.pageCount}">
                <li><a class=" btn-info"
                       href="<%=path %>/GoodsTypeSelectServlet?pageNo=${page.pageNo}">${page.pageNo}</a></li>
                <li class="disabled"><a href="#" aria-label="Previous">»<span
                        class="sr-only">(current) </span></a></li>
                <li class="disabled"><a class=" btn-info"
                       href="<%=path %>/GoodsTypeServlet?method=findGoodsTypeByPage&pageNo=${page.pageCount}"
                       aria-label="Previous"> <span aria-hidden="true">尾页 </span></a></li>
            </c:if>

        </ul>
    </nav>
</div>
</body>
</html>
