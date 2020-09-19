<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/15
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <style>
    div{
        color: red;
    }
    </style>
    <script>
        /*点击图片切换验证码*/
        window.onload = function () {
            //获取img对象
            var img = document.getElementById("img");
            //给img绑定事件
            img.onclick = function () {
                var time = new Date().getTime();
                //点击图片重新请求
                img.src ="/day16/checkcodeServlet?time="+time;
            }
        }
    </script>
</head>
<body>
<form action="/day16/LoginServlet" method="post">
<table>
    <tr>
        <td>用户名<<input type="text" name="username" placeholder="请输入用户名"></td>
    </tr>
    <tr>
        <td>密码<<input type="password" name="password" placeholder="请输入用密码"></td>
    </tr>
    <tr>
        <td>验证码<<input type="text" name="checkcode" placeholder="请输入验证码"></td>
    </tr>
    <tr>
        <td rowspan="2" id="img"><img src="/day16/checkcodeServlet"></td>
    </tr>
    <tr>
        <td rowspan="2"><input type="submit" value="登录"></td>
    </tr>
</table>
</form>
<div><%=request.getSession().getAttribute("cc_erro") == null?"":request.getSession().getAttribute("cc_erro")%></div>
<div><%=request.getSession().getAttribute("login_erro"+"") == null?"":request.getSession().getAttribute("login_erro"+"")%></div>
</body>
</html>
