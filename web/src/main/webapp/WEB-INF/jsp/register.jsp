<%--
  Created by IntelliJ IDEA.
  User: zhangjiayu
  Date: 2017/10/28
  Time: 下午9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>注册页面</title>
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
<div class="center-in-center">
    <form class="layui-form"  action="" method="post" id="login_from">
        <div class="center-in-center">
            <div class="layui-form-item">
                <div class="layui-input-inline" style="height: 45px;width: 260px">
                    <label class="layui-form-label" style="width: auto">账号名称</label>
                    <input type="text" name="account" required lay-verify="required" placeholder="请输入你的账号"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="margin-top: 30px" >
                <div class="layui-input-inline" style="height: 45px;width: 260px">
                    <label class="layui-form-label" style="width: auto">密码</label>
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入你的密码"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="margin-top: 30px">
                <div class="layui-input-inline" style="height: 45px;width: 260px">
                    <label class="layui-form-label" style="width: auto">重新输入密码</label>
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入你的密码"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="margin-top: 30px">
                <div class="layui-input-inline" style="height: 45px;width: 260px">
                    <label class="layui-form-label" style="width: auto">电话号码</label>
                    <input type="text" name="phone" required lay-verify="required" placeholder="请输入你的电话"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="margin-top: 30px">
                <div class="layui-input-inline" style="height: 45px;width: 260px">
                    <label class="layui-form-label" style="width: auto">邮箱</label>
                    <input type="email" name="phone" required lay-verify="required" placeholder="请输入你的邮箱"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item"  style="margin-top: 45px">
                <div class="layui-input-block" style="height: 45px;width: auto;margin-left: auto">
                    <button class="layui-btn" type="button" id="submit" onclick="submit()" style="height: 45px;width: 120px; font-size: 22px; background-color: #f44336">注册</button>
                    <button class="layui-btn" type="button" id="reset" style="height: 45px;width: 120px; font-size: 22px; background-color: #f44336">重置</button>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="../layui.js"/>
</body>
</html>
