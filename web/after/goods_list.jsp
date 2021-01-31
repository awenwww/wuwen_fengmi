<%--
  Created by IntelliJ IDEA.
  作者: 小白
  日期: 2020/6/24
  时间: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户登录</title>

    <!-- Bootstrap -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
            rel="stylesheet">
    <link
            href="https://cdn.bootcss.com/jquery-confirm/3.2.3/jquery-confirm.min.css"
            rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link
            href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css"
            rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js "></script>
    <script
            src="https://cdn.bootcss.com/jquery-confirm/3.2.3/jquery-confirm.min.js "></script>
    <script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.js"></script>
    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        #dropdownMenu2 {
            padding-right: 28px;
            background-image: url(../images/my/2.png);
            background-repeat: no-repeat;
            background-position-x: right;
        }
    </style>

    <style type="text/css">
        *{
            padding:0;
            margin:0;
        }
        .content {
            margin: 40px auto;
            width: 450px;
            height: 301px;
            border:10px solid #808080;
            overflow:hidden;
            z-index:2;
        }
        .content img {
            width: 450px;
            height: 301px;
            transition-duration:0.5s;
            z-index:1;
        }
        .content img:hover {
            transform: scale(1.2);
            -webkit-transform: scale(1.2); /*Safari 和 Chrome*/
            -moz-transform: scale(1.2); /*Firefox*/
            -ms-transform: scale(1.2); /*IE9*/
            -o-transform: scale(1.2); /*Opera*/
        }
    </style>

</head>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script
        src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

<style>
    th {
        text-align: center;
    }
</style>


<script>


    var selectNum =0;
    var allnum = 0;
    $(function(){
        $("#selectAll").click(function(){
            $("[type='checkbox']").prop("checked",this.checked);
            if(this.checked){
                selectNum = $("[type='checkbox']").length -1;

            }else{
                selectNum = 0;
            }
            $("#badge").text(selectNum);
        });
        var btn = document.getElementById('btn');
        var goodsids=document.getElementsByName("goodsid");
        $("[name='goodsid']").click(function(){

            if(this.checked){
                selectNum += 1;
                var botton = false;
                for(var i=0;i<goodsids.length-1;i++){
                    if(goodsids[i].checked){
                        botton = true;
                    }else{
                        botton = false;
                        break;
                    }
                }
                if(botton){
                    allnum = 1;
                    btn.checked=true;
                }
            }else{
                selectNum -= 1;
                allnum = 0;
                btn.checked=false;
            }
            $("#badge").text(selectNum);
        });
    });




    // 弹框初始化操作
    $(function () {
        $('[data-toggle="popover"]').popover();

    });

    function showAdd(){
        location.href="${pageContext.request.contextPath}/GoodsServlet?method=toAddGoods";
    }
    function checkAll(){
        var goodsids=document.getElementsByName("goodsid");
        if(allnum == 0){
            for(var i=0;i<goodsids.length-1;i++){
                goodsids[i].checked=true;
            }
            allnum = 1;
            selectNum = $("[type='checkbox']").length-1;
            $("#badge").text(selectNum);
        }else {
            for(var i=0;i<goodsids.length-1;i++){
                goodsids[i].checked=false;
            }
            allnum = 0;
            selectNum = $("[type='checked']").length;
            $("#badge").text(selectNum);
        }

    }

    function checkNo(){
        var stuids=document.getElementsByName("goodsid");
        for(var i=0;i<stuids.length;i++){
            stuids[i].checked=false;
        }
    }
    function delectAll(){
        if(confirm("确定删除吗？")){
            var goodsid=document.getElementsByName("goodsid");

            var ids=[];
            for (var i=0;i<goodsid.length;i++){
                if(goodsid[i].checked){
                    ids[i]=goodsid[i].value;
                }
            }
            window.location.href="${pageContext.request.contextPath}/GoodsServlet?method=deleteGoods&goodsid="+ids;
        }

    }
    function batchDelete(){
        confirm.show('溫馨提示', '您确定要刪除'+selectNum+'个商品吗？', {
            '确定' : {
                'primary' : true,
                'callback' : function() {
                    $("#deleteForm").submit();
                }
            }
        });
    }

    //解决选择下拉框内容到输入框内容的问题
    function showName(obj, id) {

        var name = $(obj).text();
        if(true){
            $("#dropdownMenu2").val(name);
        }else {
            $("#dropdownMenu2").val("展示所有商品");
        }

        $("#typeName").val(name);
        $("#typeId").val(id);
    }


    function expEXCEL(url) {
        //jquery  confirm组件弹窗按钮样式
        $.confirm({
            title: '是否确认',
            content: '确认删除吗?',
            type: 'blue',
            icon: 'glyphicon glyphicon-question-sign',
            buttons: {
                ok: {
                    text: '确认',
                    btnClass: 'btn-primary',
                    action: function() {
                        location.href = url; //指向下载资源（此处为目标文件的输出数据流）
                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'btn-primary'
                }
            }
        });
    }



</script>
<body>
<%@include file="header.jsp"%>

<div class="jumbotron" style="padding-top: 20px; padding-bottom: 25px">
    <div class="container">
        <h2 id="ac" index="0">商品管理</h2>

    </div>


</div>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <button type="button" onclick="showAdd()" id="addBtn"
                    class="btn btn-info">添加商品</button>
            <button type="button" onclick="delectAll()" class="btn btn-primary">
                批量删除<span class="badge" id="badge">0</span>
            </button>


        </div>
        <div class="col-md-6" style="text-align: right; padding-right: 25px;">
            <form class="form-inline" action="<%=path%>/GoodsServlet?method=findGoodsByPage" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="exampleInputName1"
                           placeholder="商品名称" name="goodsname">
                </div>
                <div class="form-group">
                    <input type="date" class="form-control" id="exampleInputTimw"
                           placeholder="年/月/日" name="deployDate">
                </div>

                <div class="form-group">
                    <div class="dropdown">
                        <input class="btn btn-primary dropdown-toggle" type="button"
                               id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true"
                               aria-expanded="false" id="show" value='商品类型' ><span
                            class="mycaret"></span>
                        </button>
                        <ul id="selectSpeaker" class="dropdown-menu">
                            <li><a onclick="showName(this,${type.id})">展示所有商品</a></li>
                            <c:forEach items="${typeList}" var="type">
                                <li><a onclick="showName(this,${type.id})">${type.typename}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <!-- /btn-group -->
                <input type="hidden" class="form-control" id="typeId" name="id">
                <input type="hidden" class="form-control" disabled id="typeName" name="typename" aria-label="...">
                <button type="submit" class="btn btn-info">查询</button>



    </form>
</div>
</div>
</div>

<!--以下显示表格-->
<div class="container" style="margin-top: 20px;">
    <form id="deleteForm"

          method="post">
        <table style="text-align: center; margin-right: 10px; width: 99%"
               class="table table-striped table-hover table-bordered">
            <thead style="text-align: center;">
            <tr>
                <th><input type="checkbox" id='btn' onclick="checkAll()" /></th>
                <th>序号</th>
                <th>商品名称</th>
                <th>商品价格</th>
                <th>图片</th>
                <th>上架时间</th>
                <th>类型</th>
                <th>商品描述</th>
                <th>编辑</th>
                <th>删除</th>


            </tr>
            </thead>
            <tbody>

            <c:forEach items="${goodsList}" var="goods" varStatus="obj">
                <tr>
                    <td><input type="checkbox" name="goodsid"
                               value="${goods.gid}" /></td>
                    <td>${goods.gid}</td>
                    <td>${goods.goodsname}</td>
                    <td>${goods.price}</td>
                        <%-- ${goods.imgPath} --%>
                    <td>
                        <img
                                src="${goods.imgPath}"
                                class="img-rounded" height="50px" width="50px" /></td>
                    <td>${goods.deployDate}</td>
                    <td>${goods.goodsType.typename}</td>

                    <td>
                        <button type="button" class="btn btn-primary"
                                data-toggle="popover" data-placement="top" title="商品介绍"
                                data-content="${goods.comment}！">弹详情</button>
                    </td>

                        <%-- <td><a href="<%=path%>/after/add_goods.jsp?id=${goods.id}" onclick="if(confirm('是否确定编辑'+'${goods.goodsName}'+'？')==false)return false">编辑</a></td>
            <td><a name="goodsid" href="<%=path%>/DeletGoodsServlet?goodsid=${goods.id}" onclick="if(confirm('是否确定删除'+'${goods.goodsName}'+'？')==false)return false">删除</a></td> --%>

                    <td><a href="<%=path%>/GoodsServlet?method=toUpdateGoods&gid=${goods.gid}"
                           onclick="if(confirm('是否确定编辑'+'${goods.goodsname}'+'？')==false)return false">
                        <span class="glyphicon glyphicon-edit" color="#5BC0DE"></span>
                    </a></td>

                    <td><a class="glyphicon glyphicon-trash" color="#5BC0DE"
                           style="text-decoration: none" name="goodsid"
                           href="<%=path%>/GoodsServlet?method=deleteGoods&goodsid=${goods.gid}"
                           onclick="if(confirm('是否确定删除'+'${goods.goodsname}'+'？')==false)return false"></a></td>




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
                   href="<%=path %>/GoodsServlet?method=findGoodsByPage&?pageNo=1" aria-label="Previous">
                <span aria-hidden="true">首页 </span>
            </a></li>
            <c:if test="${page.pageNo>1}">
                <li><a class=" btn-info"
                       href="<%=path %>/GoodsServlet?method=findGoodsByPage&pageNo=${page.pageNo-1}&username=${user.username}&typeId=${typeId}"
                       aria-label="Previous"> <span aria-hidden="true"> « </span></a></li>
            </c:if>

            <c:if test="${page.pageNo<=1}">
                <li class="disabled"><a href="#" aria-label="Previous">« <span
                        class="sr-only">(current) </span></a></li>
            </c:if>

           <c:if test="${page.pageCount>page.pageNo}">
               <li><a class="btn">${page.pageNo}</a></li>
               <li><a class="btn-info"
                      href="<%=path %>/GoodsServlet?method=findGoodsByPage&pageNo=${page.pageNo+1}&goodsname=${goodsname}&id=${id}&deployDate=${deployDate}"
                      aria-label="Next"><span aria-hidden="true">»</span></a></li>
               <li><a class=" btn-info"
                      href="<%=path %>/GoodsServlet?method=findGoodsByPage&pageNo=${page.pageCount}&goodsname=${goodsname}&id=${id}&deployDate=${deployDate}"
                      aria-label="Previous"> <span aria-hidden="true">尾页 </span></a></li>
           </c:if>
            <c:if test="${page.pageCount<=page.pageNo}">
                <li><a class=" btn-info">${page.pageNo}</a></li>
                <li class="disabled"><a href="#" aria-label="Previous">»<span
                        class="sr-only">(current) </span></a></li>
                <li class="disabled"><a class=" btn-info"
                       aria-label="Previous"> <span aria-hidden="true">尾页 </span></a></li>
            </c:if>
        </ul>
    </nav>
</div>

</table>
<br />



</body>
</html>
