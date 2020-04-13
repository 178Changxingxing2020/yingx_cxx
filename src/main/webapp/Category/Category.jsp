<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">

    $(function(){
        pageInit();
    });
    function pageInit(){
        $("#categoryTable").jqGrid(
            {
                url : "${path}/category/queryFirstCategoryByPage",
                editurl:"${path}/category/editFirstCategory?levels=1",
                datatype : "json",
                rowNum : 5,//每页展示条数
                rowList : [ 2,5, 10, 20, 30 ],
                pager : '#categoryPage',
                viewrecords : true,//显示总条数
                styleUI:"Bootstrap",
                height:"auto",
                autowidth:true,
                colNames : [ 'ID', '类别名', '级别', '父类别ID'],
                colModel : [
                    {name : 'id', width : 55},
                    {name : 'cateName',width : 90,editable:true},
                    {name : 'levels',width : 100},
                    {name : 'parentId',width : 80},

                ],
                subGrid : true,
                subGridRowExpanded : function(subgridId, rowId) {

                    //子表格
                    // we pass two parameters
                    // subgrid_id is a id of the div tag created whitin a table data
                    // the id of this elemenet is a combination of the "sg_" + id of the row
                    // the row_id is the id of the row
                    // If we wan to pass additinal parameters to the url we can use
                    // a method getRowData(row_id) - which returns associative array in type name-value
                    // here we can easy construct the flowing
                    var subgridTableId= subgridId + "_t";
                    var pagerId=subgridId+"_p";

                    $("#" + subgridId).html(
                        "<table id='" + subgridTableId + "' class='scroll'></table>" +
                        "<div id='" + pagerId + "' class='scroll'></div>");

                    $("#" + subgridTableId).jqGrid(
                        {
                            url :"${path}/category/querySecondCategory?id=" + rowId,
                            editurl:"${path}/category/editSecondCategory?levels=2&parentId=" + rowId,
                            datatype : "json",
                            rowNum : 5,//每页展示条数
                            rowList : [ 2,5, 10, 20, 30 ],
                            styleUI:"Bootstrap",
                            height:"auto",
                            viewrecords : true,//显示总条数
                            autowidth:true,
                            pager : "#"+pagerId,
                            colNames : [ 'ID', '类别名', '级别', '父类别ID'],
                            colModel : [
                                {name : 'id', width : 55},
                                {name : 'cateName',width : 90,editable:true},
                                {name : 'levels',width : 100},
                                {name : 'parentId',width : 80,align : "right"}
                            ],

                        });
                    $("#" + subgridTableId).jqGrid('navGrid',
                        "#" + pagerId, {
                            edit : true,
                            add : true,
                            del : true
                        },{closeAfterEdit:true},
                        {closeAfterAdd:true},
                        {closeAfterDel:true,
                            afterSubmit:function (response) {
                                if(response.responseText!="0"){
                                    alert("该二级类别下还有视频")
                                }
                                return "hello";
                            }

                        });
                },
                subGridRowColapsed : function(subgrid_id, row_id) {
                    // this function is called before removing the data
                    //var subgrid_table_id;
                    //subgrid_table_id = subgrid_id+"_t";
                    //jQuery("#"+subgrid_table_id).remove();
                }
            });
        $("#categoryTable").jqGrid('navGrid', '#categoryPage', {
            add : true,
            edit : true,
            del : true
        },
            {closeAfterEdit:true},
            {closeAfterAdd:true},
            {closeAfterDel:true,
                afterSubmit:function (response) {
                    if(response.responseText!="0"){
                        alert("该类别下还有二级分类")
                    }
                    return "hello";
                }
            });



    }

</script>


<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>类别管理</h2>
    </div>

    <div class="nav nav-tabs">
        <li class="active"><a>类别管理</a></li>
    </div>


    <table id="categoryTable"></table>
    <div id="categoryPage"></div>
</div>