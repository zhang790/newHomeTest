<%@ page language="java"  pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>newhome登录</title>
    <link rel="stylesheet" href="../css/layui.css">
    <script type="text/javascript" src="../js/jquery/jquery-3.2.1.min.js"></script>
    <style>
        .center-in-center{
            position: absolute;
            top: 50%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
        }
    </style>
</head>
<body bgcolor="#727272">
<form class="layui-form"  action="<%=basePath%>/auth/authUser.json" method="post" id="login_from">
    <div class="center-in-center">
    <div class="layui-form-item">
        <label class="layui-form-label">输入框</label>
        <div class="layui-input-inline" style="height: 45px;width: 225px">
            <input type="text" name="account" id="account"  placeholder="请输入你的账号" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-inline" style="height: 45px;width: 225px">
            <input type="password" name="password" id="password"  placeholder="请输入你的密码" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" >
        <div class="layui-input-block" >
            <button class="layui-btn SUB"  type="button" id="submit"   style="height: 30px;width: 105px; font-size: 13px; background-color: #f44336" >登录</button>
            <button class="layui-btn REG"  type="button" id="register" style="height: 30px;width: 105px; font-size: 12px; background-color: #f44336" >注册</button>
        </div>
    </div>
    </div>
</form>
<script src="../layui.js"></script>
<script>

        var basePath = '<%=basePath%>';
        $("#submit").on("click", function () {
                $.ajax({
                    url:basePath + "/auth/authUser.json",
                    type:'POST',
                    data:{
                        account:$("#account").val(),
                        password:$("#password").valueOf()
                    },
                    success:function(datas){
                        if (datas.code == 1){
                            window.location.href=basePath + "auth/loginPage.json"
                        }else{
                            //提示密码错误
                        }
                    }

                });
            }
        );
        $("#register").on("click", function () {
            console.log(basePath);
            window.location.href = basePath + "/auth/registerPage.json";
        });
</script>
</body>
</html>
