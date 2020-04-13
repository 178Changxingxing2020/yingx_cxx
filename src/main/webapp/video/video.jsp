<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">

    $(function(){
        pageInit();

    });
    function pageInit(){
        $("#videoTable").jqGrid(
            {

                url : "${path}/video/queryVideoByPage",
                editurl:"${path}/video/editVideo",
                datatype : "json",
                rowNum : 5,//每页展示条数
                rowList : [ 2,5, 10, 20, 30 ],
                pager : '#videoPage',
                viewrecords : true,//显示总条数
                styleUI:"Bootstrap",
                height:"auto",
                autowidth:true,
                colNames : [ 'ID', '名称', '视频', '上传时间', '描述','所属类别', '类别ID' ,'用户ID'],
                colModel : [
                    {name : 'id',align : "center",width:"30px"},
                    {name : 'title',align : "center",width:"80px",editable:true},
                    {name : 'path',align : "center",width:"400px",
                        formatter:function(cellvalue, options, rowObject){
                            return "<video id='media' src='"+cellvalue+"' controls width='400px' heigt='800px' poster='"+rowObject.cover+"'/>";
                        },editable:true,edittype:"file"
                    },
                    {name : 'publishDate',align : "center"},
                    {name : 'brief',align : "center",editable:true},
                    {name : 'category.cateName',align : "center"},
                    {name : 'categoryId',align : "center"},
                    {name : 'userId',align : "center"}
                ],

            });
        $("#videoTable").jqGrid('navGrid', '#videoPage',
            {edit : true,add : true,del : true,addtext:"添加",deltext:"删除",edittext:"修改"},
            {
                //修改
                closeAfterEdit:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${path}/video/uploadVideoFile",
                        type:"post",
                        //dataType:"json",
                        fileElementId:"path",
                        data:{id:response.responseText},
                        success:function () {
                            $("#videoTable").trigger("reloadGrid");
                            console.info(response);
                        }
                    });
                    return "hello";
                }
            },
            {
                //增加
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${path}/video/uploadVideoFile",
                        type:"post",
                        //dataType:"json",
                        fileElementId:"path",
                        data:{id:response.responseText},
                        success:function () {
                            $("#videoTable").trigger("reloadGrid");
                        }
                    });
                    return "hello";
                }
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
        <h2>视频信息</h2>
    </div>

    <div class="nav nav-tabs">
        <li class="active"><a>视频信息</a></li>
    </div>



    <table id="videoTable"></table>
    <div id="videoPage"></div>
</div>