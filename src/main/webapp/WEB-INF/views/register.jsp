<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
    <head>
        <title>注册</title>
    </head>
    <body>
    <%=request.getAttribute("msg")%>
        <form action="/register/check" method="post">
            邮箱：<input type="text" name="email"><br>
            姓名：<input type="text" name="username"><br>
            学号：<input type="text" name="number">（教师请留空）<br>
            密码：<input type="password" name="password"><br>
            账号类别：<select name="type">
            <option value="student">学生</option>
            <option value="teacher">教师</option></select><br>
            <input type="submit" name="register" value="注册">
            <input type="reset" name="reset" value="重置">
        </form>
    <br><a href="/login">已有账号？去登录</a>
    </body>
</html>