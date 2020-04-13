<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">

    $(function(){
        pageInit();




    });
    function pageInit(){
        $("#logTable").jqGrid(
            {

                url : "${path}/log/queryLogByPage",
                editurl:"${path}/log/logUser",
                datatype : "json",
                rowNum : 5,//每页展示条数
                rowList : [ 2,5, 10, 20, 30 ],
                pager : '#logPage',
                viewrecords : true,//显示总条数
                styleUI:"Bootstrap",
                height:"auto",
                autowidth:true,
                colNames : [ 'ID', '用户名', '时间', '操作', '结果'],
                colModel : [
                    {name : 'id',align : "center",width:"30px"},
                    {name : 'adminName',align : "center",width:"80px",editable:true},
                    {name : 'time',align : "center",editable:true},
                    {name : 'operation',align : "center",width:"200px",
                      editable:true
                    },
                    {name : 'status',align : "center",editable:true},

                ],

            });
        $("#logTable").jqGrid('navGrid', '#logPage',
            {edit : false,add : false,del : false,addtext:"添加",deltext:"删除",edittext:"修改"},
            {
                //修改
                closeAfterEdit:true,

            },
            {
                //增加
                closeAfterAdd:true,

            },
            {
                //删除
                closeAfterDel:true
            }
        );
    }


</script>


<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>日志信息</h2>
    </div>

    <div class="nav nav-tabs">
        <li class="active"><a>日志管理</a></li>
    </div>


    <table id="logTable"></table>
    <div id="logPage"></div>
</div>