<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>newhome登录</title>
    <link rel="stylesheet" href="../css/layui.css">
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
<form class="layui-form"  action="">
    <div class="center-in-center">
    <div class="layui-form-item">
        <label class="layui-form-label">输入框</label>
        <div class="layui-input-inline" style="height: 45px;width: 225px">
            <input type="text" name="title" required lay-verify="required" placeholder="请输入你的账号" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-inline" style="height: 45px;width: 225px">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入你的密码" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" >
        <div class="layui-input-block" >
            <button class="layui-btn" style="height: 50px;width: 225px; font-size: 20px; background-color: #f44336" lay-submit lay-filter="formDemo">登录</button>
        </div>
    </div>
    </div>
</form>
<script src="../layui.js"/>
<script>
    //Demo
    layui.use('form', function () {
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>
</body>
</html>
