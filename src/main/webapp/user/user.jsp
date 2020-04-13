<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">

    $(function(){
        pageInit();


        //发送验证码
        $("#sendCheckCode").click(function(){

            var phone=$("#phoneInput").val();

            $.ajax({
                url:"${path}/user/SendSmsCode",
                type:"post",
                dataType:"json",
                data:{phone:phone},
                success:function(result){
                    //刷新表单
                    alert("发送成功，验证码为"+result.code)
                    //$("#userTable").trigger("reloadGrid");
                }
            })

        });
        //发送验证码
        $("#exportUserFile").click(function(){



            $.ajax({
                url:"${path}/user/exportUserFile",
                type:"post",
                dataType:"json",
                data:{},
                success:function(result){
                    //刷新表单
                    alert("导出成功")

                }
            })

        });

    });
    function pageInit(){
        $("#userTable").jqGrid(
            {

                url : "${path}/user/queryUserByPage",
                editurl:"${path}/user/editUser",
                datatype : "json",
                rowNum : 5,//每页展示条数
                rowList : [ 2,5, 10, 20, 30 ],
                pager : '#userPage',
                viewrecords : true,//显示总条数
                styleUI:"Bootstrap",
                height:"auto",
                autowidth:true,
                colNames : [ 'ID', '用户名', '手机号', '头像', '签名','微信', '状态' ,'注册时间'],
                colModel : [
                    {name : 'id',align : "center",width:"30px"},
                    {name : 'username',align : "center",width:"80px",editable:true},
                    {name : 'phone',align : "center",editable:true},
                    {name : 'headImg',align : "center",width:"200px",
                        formatter:function(cellvalue, options, rowObject){
                        return "<img  src='${path}/upload/img/"+rowObject.headImg+" ' width='200px' height='100px' />"
                        },editable:true,edittype:"file"
                    },
                    {name : 'sign',align : "center",editable:true},
                    {name : 'wechat',align : "center",editable:true},
                    {name : 'status',
                        formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "<button onclick='uploadStatus(\""+rowObject.id+"\",\""+rowObject.status+"\")' class='btn btn-info' >冻结</button>"
                        }else {
                            return "<button onclick='uploadStatus(\""+rowObject.id+"\",\""+rowObject.status+"\")' class='btn btn-danger' >解冻</button>"
                        }

                        }
                    },
                    {name : 'createDate'}
                ],

            });
        $("#userTable").jqGrid('navGrid', '#userPage',
            {edit : true,add : true,del : true,addtext:"添加",deltext:"删除",edittext:"修改"},
            {
                //修改
                closeAfterEdit:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${path}/user/uploadUserFile",
                        type:"post",
                        //dataType:"json",
                        fileElementId:"headImg",
                        data:{id:response.responseText},
                        success:function () {
                            $("#userTable").trigger("reloadGrid");
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
                        url:"${path}/user/uploadUserFile",
                        type:"post",
                        //dataType:"json",
                        fileElementId:"headImg",
                        data:{id:response.responseText},
                        success:function () {
                            $("#userTable").trigger("reloadGrid");
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


    function uploadStatus(id,status) {
       // alert("======");
        $.ajax({
            url:"${path}/user/uploadUserStatus",
            //dataType:"json",
            data:{id:id,status:status},
            type:"post",
            success:function () {
                $("#userTable").trigger("reloadGrid");
            }
        })
    }
</script>


<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <%--发送验证码--%>
    <div class="input-group" style="width: 400px;height: 30px;float: right">
        <input id="phoneInput" type="text" class="form-control" placeholder="请输入验证码" aria-describedby="basic-addon2">
        <span class="input-group-addon" id="sendCheckCode"><a>点击发送验证码</a></span>
    </div>

    <div class="nav nav-tabs">
        <li class="active"><a>用户管理</a></li>
    </div>

    <div>
       <button class="btn btn-success" id="exportUserFile">导出用户信息</button>
       <button class="btn btn-info">导入用户信息</button>
       <button class="btn btn-primary">测试按钮</button>
    </div>

    <table id="userTable"></table>
    <div id="userPage"></div>
</div>