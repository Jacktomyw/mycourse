<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<title>主界面</title>
<body>
欢迎， ${sessionScope.user.getUsername()} 同学！<br>
您的个人信息：<br>
邮箱：${sessionScope.user.getEmail()}<br>
学号：${sessionScope.user.getNumber()}<br>
<a href="/student/myClasses">我的课程</a><br>
<a href="/updateInfo">修改个人信息</a><br>
<a href="/signDown">注销个人账号</a><br>
<a href="/logout">退出</a><br>
</body>