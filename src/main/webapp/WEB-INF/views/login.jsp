<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<title>登录</title>
<body>
<%=request.getAttribute("msg")%>
<form action="/login/check" method="post">
    邮箱:<input type="text" name="email"><br>
    密码:<input type="password" name="password"><br>
    <input type="submit" name="login" value="登录">
    <input type="reset" name="reset" value="重置">
</form>
<br><a href="/register">没有账号？去注册</a>
</body>