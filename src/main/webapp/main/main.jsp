<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>应学后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>
<style>
.carousel-inner > .item.active {
            left: 250px;
            -webkit-transform: translate3d(0, 0, 0);
            transform: translate3d(0, 0, 0);
        }
</style>



</head>
<body>

<!-- 导航栏 -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">应学后台管理系统</a>
        </div>
        <div>
            <!--向右对齐-->
            <ul class="nav navbar-nav navbar-right">
                <li><a><shiro:principal></shiro:principal></a></li>
                <li><a>欢迎：${admin.username}</a></li>
                <li><a href="${path}/admin/loginOut">退出登陆
                <span class="glyphicon glyphicon-log-in"></span></a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- 栅格系统 -->
<div class="container-fluid">
    <div class="row">
        <!-- 手风琴 -->
        <div class="col-xs-2">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                用户模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav">
                                <li><button class="btn btn-warning" ><a href="javascript:$('#centerLay').load('${path}/user/user.jsp')"/>用户展示</button><hr></li>
                                <li><button class="btn btn-warning" ><a href="javascript:$('#centerLay').load('./map.jsp')"/>用户统计</button><hr></li>
                                <li><button class="btn btn-warning" ><a href="javascript:$('#centerLay').load('./map.jsp')"/>用户分步</button></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                分类管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav">
                                <li><button class="btn btn-warning" ><a href="javascript:$('#centerLay').load('${path}/Category/Category.jsp')"/>分类展示</button><hr></li>

                            </ul>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">
                                视频管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav">
                                <li><button class="btn btn-info" ><a href="javascript:$('#centerLay').load('${path}/video/video.jsp')"/>视频展示</button><hr></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour">
                                日志管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav">
                                <li><button class="btn btn-info" ><a href="javascript:$('#centerLay').load('${path}/log/log.jsp')"/>日志展示</button><hr></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive">
                                轮播图模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav">
                                <li><button class="btn btn-primary" href="javascript:$('#centerLay').load('./banner.jsp')">轮播图管理</button></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseSix">
                                聊天模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapseSix" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav">
                                <li><button class="btn btn-success" href="javascript:$('#centerLay').load('./chat.jsp')">聊天室</button></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <hr>

                <shiro:hasPermission name="admin:*">
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseSeven">
                                    管理员模块
                                </a>
                            </h4>
                        </div>
                        <div id="collapseSeven" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav">
                                    <li><button  class="btn btn-warning" href="javascript:$('#centerLay').load('./chat.jsp')">管理员管理</button></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </shiro:hasPermission>
            </div>
        </div>
        <div class="col-xs-10" id="centerLay">
            <div class="jumbotron" >
                <div class="container">
                    <h3>欢迎来到应学视频APP后台管理系统！</h3>
                </div>
            </div>

            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner center-block" role="listbox">
                    <div class="item active">
                        <img src="${path}/bootstrap/img/pic1.jpg" alt="First slide">
                        <%--<div class="carousel-caption">标题 1</div>--%>
                    </div>
                    <div class="item ">
                        <img src="${pageContext.request.contextPath}/bootstrap/img/pic2.jpg" alt="Second slide">
                        <%--<div class="carousel-caption">标题 2</div>--%>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/bootstrap/img/pic3.jpg" alt="Third slide">
                        <%--<div class="carousel-caption">标题 3</div>--%>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/bootstrap/img/pic4.jpg" alt="Third slide">
                      <%--  <div class="carousel-caption">标题 4</div>--%>
                    </div>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="panel-footer">
    <h4 style="text-align: center">@百知教育 @baizhiedu.com.cn</h4>
</div>
<%--  添加模态框  --%>
<div class="modal fade"  id="addModal">
    <%--  modal-dialog  --%>
    <div class="modal-dialog modal-lg">
        <%--  modal-content  --%>
        <div class="modal-content">

            <%--  头  --%>
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <div class="modal-title"><h4>添加文章</h4></div>
            </div>
            <%--<form action="" style="" enctype="multipart/form-data">--%>
            <%--  内容  --%>
            <div class="modal-body">
                <%--  forom表单  --%>
                <form class="form-horizontal" enctype="multipart/form-data" id="kindFrom">
                    <%--  栅格系统row行  --%>
                    <div class="row">
                        <%--  form组  --%>
                        <div class="form-group" >
                            <div class="col-xs-6">
                                <input type="hidden" class="form-control" name="id" id="id"/>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">标题</label>
                            <div class="col-xs-6">
                                <input type="text" id="title" class="form-control" name="title"/>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">图片</label>
                            <div class="col-xs-6">
                                <input type="file" class="form-control" id="inputFile" name="inputFile"/>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">内容</label>
                            <div class="col-xs-6">
                                <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                                    &lt;strong&gt;HTML内容&lt;/strong&gt;
                                </textarea>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">创作时间</label>
                            <div class="col-xs-6">
                                <input type="date" id="createDate" class="form-control" name="createDate"/>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">出版时间</label>
                            <div class="col-xs-6">
                                <input type="date" id="publishDate" class="form-control" name="publishDate"/>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">状态</label>
                            <div class="col-xs-6">
                                <select  class="form-control" id="status" name="status">
                                    <%--<option value ="1">展示</option>
                                    <option value ="0">冻结</option>--%>
                                </select>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">所属上师</label>
                            <div class="col-xs-6">
                                <select  class="form-control" id="group_guru" name="guruId">

                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <%--  页脚  --%>
            <div class="modal-footer">
                <button class="btn btn-primary" id="addEmployeeBtnSave" onclick="sub()">添加</button>
                <button class="btn btn-default" data-dismiss="modal">取消</button>
            </div>

            <%--</form>--%>
        </div>
    </div>
</div>


</body>
</html>
